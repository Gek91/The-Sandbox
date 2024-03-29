locals {
  container_image = "${var.region_id}-docker.pkg.dev/${var.project_id}/${var.image_name}"
}

resource "google_project_service" "cloud_run_api" {
  project = var.project_id
  service = "run.googleapis.com"
}

resource "google_project_service" "vpcaccess-api" {
  project = var.project_id
  service = "vpcaccess.googleapis.com"
}

resource "google_project_service" "compute-api" {
  project = var.project_id
  service = "compute.googleapis.com"
}

resource "google_project_service" "dns-api" {
  project = var.project_id
  service = "dns.googleapis.com"
}


/////

resource "google_cloud_run_v2_service" "cloud-run-invoker" {
  name     = "cloud-run-invoker"
  location = var.region_id
  ingress = "INGRESS_TRAFFIC_ALL"
  project = var.project_id

  template {
    containers {
      image = local.container_image

      startup_probe {
        initial_delay_seconds = 0
        timeout_seconds = 10
        period_seconds = 10
        failure_threshold = 3
        http_get {
          path = "/healthz"
        }
      }
      env {
        name = "URL"
      }
    }

    scaling {
      min_instance_count = 0
      max_instance_count = 1
    }

    vpc_access {
      connector = google_vpc_access_connector.serverless-connector.id
      egress = "PRIVATE_RANGES_ONLY"
    }

    service_account = google_service_account.service-invoker-sa.email
  }

  labels        = {
    "app" = "demo"
    "component" = "cloud-run-invoker"
  }

  lifecycle {
    ignore_changes = [
      template[0].containers[0].env,
      template[0].containers[0].image
    ]
  }
}

resource "google_service_account" "service-invoker-sa" {
  account_id   = "invoker-sa"
  display_name = "Service Account"
  project = var.project_id
}

/////////////////////////////

resource "google_cloud_run_v2_service" "cloud-run-open-receiver" {
  name     = "cloud-run-auth-open"
  location = var.region_id
  ingress = "INGRESS_TRAFFIC_ALL"
  project = var.project_id

  template {
    containers {
      image = local.container_image

      startup_probe {
        initial_delay_seconds = 0
        timeout_seconds = 10
        period_seconds = 10
        failure_threshold = 3
        http_get {
          path = "/healthz"
        }
      }

    }

    scaling {
      min_instance_count = 0
      max_instance_count = 1
    }

  }

  labels        = {
    "app" = "demo"
    "component" = "cloud-run-open"
  }
}

//public access, no auth needed
resource "google_cloud_run_service_iam_binding" "cloud-run-open-binding" {
  location = google_cloud_run_v2_service.cloud-run-open-receiver.location
  service  = google_cloud_run_v2_service.cloud-run-open-receiver.name
  role     = "roles/run.invoker"
  members = [
    "allUsers"
  ]
}

/////////////////////////////

resource "google_cloud_run_v2_service" "cloud-run-auth-receiver" {
  name     = "cloud-run-auth-receiver"
  location = var.region_id
  ingress = "INGRESS_TRAFFIC_ALL"
  project = var.project_id

  template {
    containers {
      image = local.container_image

      startup_probe {
        initial_delay_seconds = 0
        timeout_seconds = 10
        period_seconds = 10
        failure_threshold = 3
        http_get {
          path = "/healthz"
        }
      }

    }

    scaling {
      min_instance_count = 0
      max_instance_count = 1
    }

  }

  labels        = {
    "app" = "demo"
    "component" = "cloud-run-auth-receiver"
  }
}

//only invoker can call the service
resource "google_cloud_run_service_iam_binding" "cloud-run-auth-binding" {
  location = google_cloud_run_v2_service.cloud-run-auth-receiver.location
  service  = google_cloud_run_v2_service.cloud-run-auth-receiver.name
  role     = "roles/run.invoker"
  members = [
    "serviceAccount:${google_service_account.service-invoker-sa.email}"
  ]
}

/////////////////////////////

module "vpc_module" {
  source       = "terraform-google-modules/network/google"
  project_id   = var.project_id
  network_name = "serverless-network"
  mtu          = 1460

  subnets = [
    {
      subnet_private_access = true
      subnet_name   = "serverless-subnet"
      subnet_ip     = "10.10.10.0/28"
      subnet_region = var.region_id
    }
  ]
}

//DNS configuration zone and DNS records to call private only Cloud Run service
resource "google_dns_managed_zone" "cloud_run_private_zone" {
  name        = "private-zone"
  dns_name    = "run.app."
  description = "needed for Cloud run private communication"

  visibility = "private"

  private_visibility_config {
    networks {
      network_url = module.vpc_module.network_id
    }
  }
}

//A record to map run.app to private ips
resource "google_dns_record_set" "A_record" {
  name         = google_dns_managed_zone.cloud_run_private_zone.dns_name
  managed_zone = google_dns_managed_zone.cloud_run_private_zone.name
  type         = "A"
  ttl          = 300

  rrdatas = ["199.36.153.8", "199.36.153.9", "199.36.153.10", "199.36.153.11"]
}

//CNNAME record to map *.run.app
resource "google_dns_record_set" "CNAME_record" {
  name         = "*.${google_dns_managed_zone.cloud_run_private_zone.dns_name}"
  managed_zone = google_dns_managed_zone.cloud_run_private_zone.name
  type         = "CNAME"
  ttl          = 300

  rrdatas = ["run.app."]
}

resource "google_vpc_access_connector" "serverless-connector" {
  name          = "run-vpc-connector"
  subnet {
    name = module.vpc_module.subnets["${var.region_id}/serverless-subnet"].name
  }
  machine_type = "e2-standard-4"
  region        = var.region_id

  depends_on = [
    google_project_service.vpcaccess-api, module.vpc_module
  ]
}

resource "google_cloud_run_v2_service" "cloud-run-private-receiver" {
  name     = "cloud-run-private-receiver"
  location = var.region_id
  ingress = "INGRESS_TRAFFIC_INTERNAL_ONLY"
  project = var.project_id

  template {
    containers {
      image = local.container_image

      startup_probe {
        initial_delay_seconds = 0
        timeout_seconds = 10
        period_seconds = 10
        failure_threshold = 3
        http_get {
          path = "/healthz"
        }
      }

    }

    scaling {
      min_instance_count = 0
      max_instance_count = 1
    }

    vpc_access {
      connector = google_vpc_access_connector.serverless-connector.id
      egress = "ALL_TRAFFIC"
    }
  }

  labels        = {
    "app" = "demo"
    "component" = "cloud-run-private-receiver"
  }
}

//only invoker can call the service
resource "google_cloud_run_service_iam_binding" "cloud-run-private-binding" {
  location = google_cloud_run_v2_service.cloud-run-private-receiver.location
  service  = google_cloud_run_v2_service.cloud-run-private-receiver.name
  role     = "roles/run.invoker"
  members = [
    "serviceAccount:${google_service_account.service-invoker-sa.email}"
  ]
}