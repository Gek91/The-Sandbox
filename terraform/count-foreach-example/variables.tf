variable vpc_count {
  type = number
  default = 2
}

variable project_id {
  type = string
  sensitive = true
}

//used in for each
variable vpcs {
  type = map(any)
  default = {
    first = {
      name = "first",
    },
    second = {
      name = "second",
    }
  }
}