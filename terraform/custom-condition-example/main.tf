
#resource configuration
resource "google_compute_network" "vpc_network" {
  name = var.vpc_name

  lifecycle {
    precondition {
      condition = length(var.vpc_name) > 5
      error_message = "vpc name must be at least 5 characters"
    }

    postcondition {
      condition = self.name == var.vpc_name
      error_message = "vpc name must be te one passed with variable"
    }
  }

}