
#resource configuration
resource "google_compute_network" "vpc_network" {
  name = var.vpc_name

  lifecycle {
    prevent_destroy       = true #prevent resource destruction
    create_before_destroy = true #create a new instance before deleting the old one
    ignore_changes        = [name] #ignore name change in the effective infrastructure
  }
}