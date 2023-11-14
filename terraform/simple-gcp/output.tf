
#apply output
output "id" {
  value = google_compute_network.vpc_network.id #get value from deployed infrastructure
}