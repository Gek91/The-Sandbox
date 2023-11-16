


#resource configuration
resource "google_compute_network" "vpc_network" {
  name = var.vpc_name
}

check "vcp_check" {
  assert {
    condition = google_compute_network.vpc_network.name == var.vpc_name
    error_message = "error in vpc name"
  }
}


check "response" {
  data "http" "ulr_data" {
    url      = "https://www.google.com"
  }



  assert {
    condition     = data.http.ulr_data.status_code == 200
    error_message = "Terramino response is ${data.http.ulr_data.status_code}"
  }
}