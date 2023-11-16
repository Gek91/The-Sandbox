
#apply output
output "simple_datasource" {
  value = data.http.simple_data.status_code #result of data source
}

output "vcp_name" {
  value =  google_compute_network.vpc_network.name
}