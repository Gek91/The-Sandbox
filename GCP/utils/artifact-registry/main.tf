
resource "google_project_service" "gcp_service_apis" {
  project            = var.project_id
  service            = "artifactregistry.googleapis.com"
}

resource "google_artifact_registry_repository" "artifact-repository" {
  project = var.project_id
  location      = "europe-west1"
  repository_id = "artifact-repository"
  format        = "DOCKER"
  labels        = {
    "app" = "demo"
  }
}