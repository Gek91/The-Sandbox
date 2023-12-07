resource "google_cloud_run_v2_service" "cloud-run-1" {
  name     = "cloudrun-demo-1"
  location = "europe-west3"
  ingress = "INGRESS_TRAFFIC_ALL"

  template {
    containers {
      image = var.image_name

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
    "component" = "cloud-run-1"
  }
}

resource "google_cloud_run_v2_service" "cloud-run-2" {
  name     = "cloudrun-demo-2"
  location = "europe-west3"
  ingress = "INGRESS_TRAFFIC_ALL"

  template {
    containers {
      image = var.image_name

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
    "component" = "cloud-run-2"
  }
}