resource "google_project_service" "firestore" {
  project = var.project_id
  service = "firestore.googleapis.com"
}

resource "google_firestore_database" "database" {
  project     = var.project_id
  name        = var.firestore_name
  location_id = "europe-west3"
  type        = "DATASTORE_MODE"

  depends_on = [google_project_service.firestore]
}