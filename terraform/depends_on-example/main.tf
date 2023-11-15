
#define explicit resource creation order, without the creation it will be parallel

resource "google_compute_network" "vpc_network_1" {
  name = "${var.vpc_name}-1"
}

resource "google_compute_network" "vpc_network_2" {
  name = "${var.vpc_name}-2"
  depends_on = [google_compute_network.vpc_network_1] #wait vpc_network_1
}

resource "google_compute_network" "vpc_network_3" {
  name = "${var.vpc_name}-3"
  depends_on = [google_compute_network.vpc_network_1, google_compute_network.vpc_network_2] #wait vpc_network_1 and vpc_network_2
}

