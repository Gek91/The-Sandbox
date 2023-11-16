#terraform configuration
terraform {
  required_providers {
    docker = {
      source  = "kreuzwerker/docker"
      version = "~> 3.0.2"
    }
  }
  required_version = "~>1.5.0" #all versions with major e minor version as defined. Patch version can change
}
provider "docker" {
 // host = "unix:///var/run/docker.sock"
}