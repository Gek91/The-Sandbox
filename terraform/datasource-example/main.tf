

#simple html datasource
data "http" "simple_data" {
  url = "https://www.google.com"
}

#vcp datasource
data "google_compute_network" "my-network" {
  name = "default"
}

#resource configuration
resource "google_compute_network" "vpc_network" {
  name = "${data.google_compute_network.my-network.name}-new"
}