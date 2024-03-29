locals {
  container_image = format("%s-docker.pkg.dev/%s/%s",var.region_id, var.project_id, var.image_name)
}

resource "google_project_service" "cloud_run_api" {
  project            = var.project_id
  service = "run.googleapis.com"
}

resource "google_cloud_run_v2_service" "cloud-run" {
  project = var.project_id
  name     = "cloudrun-demo"
  location = "europe-west3"
  ingress = "INGRESS_TRAFFIC_ALL"

  template {
    revision = "cloudrun-demo-first-revision"

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

      liveness_probe {
        initial_delay_seconds = 10
        http_get {
          path = "/healthz"
        }
      }
    }

    scaling {
      min_instance_count = 0
      max_instance_count = 5
    }


  }

  labels        = {
    "app" = "demo"
  }

  depends_on = [

  ]
}
