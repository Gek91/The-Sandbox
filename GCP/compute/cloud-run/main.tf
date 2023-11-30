resource "google_cloud_run_v2_service" "cloud-run" {
  name     = "cloudrun-demo"
  location = "europe-west3"
  ingress = "INGRESS_TRAFFIC_ALL"

  template {
    revision = "cloudrun-demo-first-revision"

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
}