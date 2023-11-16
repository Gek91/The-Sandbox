
variables {
  vpc_name = "ciao-vpn!"
}

run "create_vpn" {
  command = apply

  assert {
    condition = google_compute_network.vpc_network.name == "ciao-vpn"
    error_message = "invalid name"
  }

  assert {
    condition = false
    error_message = "always false"
  }
}