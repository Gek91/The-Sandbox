
#apply output
output "ids" {
  value = google_compute_network.vpc_network.*.id
}

output "id1" {
  value = google_compute_network.vpc_network[0].id
}

output "id2" {
  value = google_compute_network.vpc_network[1].id
}