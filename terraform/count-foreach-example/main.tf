
#count example
resource "google_compute_network" "vpc_network" {
  name = "vpc-${count.index}" #count.index incrementing value

  count = var.vpc_count #define the number of instances of the resource
}

#for each example
resource "google_compute_network" "vpc_network_each" {
  for_each = var.vpcs #vpcs is a map containing elements that are vpc configuration

  name = each.value.name #get value from map element
}