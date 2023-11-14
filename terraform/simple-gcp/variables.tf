variable vpc_name {
  type = string
  default = "my-vpc"
}

#variable credential_file {
#  type = string
#}

variable project_id {
  type = string
  sensitive = true
}