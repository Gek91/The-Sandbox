#locals variable
locals {
  network_1_name = var.vpc_names[0]
  network_2_name = var.vpc_names[1]
}

#resource configuration
resource "google_compute_network" "vpc_network_1" {
  name = "${local.network_1_name}-vpc" #string interpolation using local variable
  auto_create_subnetworks = var.vpc_auto_create_subnetworks
  mtu = var.mtu_values[local.network_1_name] #local variable as key map
}

resource "google_compute_network" "vpc_network_2" {
  name = "${local.network_2_name}-vpc"
  auto_create_subnetworks = var.vpc_auto_create_subnetworks
  mtu = var.mtu_values[local.network_2_name]
}