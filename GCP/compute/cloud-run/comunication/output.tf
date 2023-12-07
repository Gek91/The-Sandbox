
#apply output
output "id-1" {
  value = google_cloud_run_v2_service.cloud-run-1.id
}

output "id-2" {
  value = google_cloud_run_v2_service.cloud-run-2.id
}