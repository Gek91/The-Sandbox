variable vpc_names {
  type = list(string)
  default = ["vpc1", "vpc2"]
}

variable mtu_values {
  type = map(number)
  default = {
    "vpc1" = 1400,
    "vpc2" = 1300
  }
}

variable vpc_auto_create_subnetworks {
  type = bool
  default = false
}

variable project_id {
  type = string
  sensitive = true

  #Validation on project_id value
  validation {
    condition = length(var.project_id) > 6
    error_message = "Must be at least 6 characters"
  }
}

