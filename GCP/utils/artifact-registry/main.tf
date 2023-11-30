
resource "google_artifact_registry_repository" "artifact-repository" {
  location      = "europe-west3"
  repository_id = "artifact-repository"
  format        = "DOCKER"
  labels        = {
    "app" = "demo"
  }
}