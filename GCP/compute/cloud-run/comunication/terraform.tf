#terraform configuration
terraform {
  required_providers {
    google = {
      source = "hashicorp/google"
      version = "4.51.0"
    }
  }
  required_version = "~>1.5.0"
}

provider "google" {
  project = var.project_id
  region  = "europe-west3"
  zone    = "europe-west3-c"
}