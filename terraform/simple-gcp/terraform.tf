#terraform configuration
terraform {
  required_providers {
    google = {
      source = "hashicorp/google"
      version = "4.51.0" #specific provider version
    }
  }
  required_version = "~>1.5.0" #all versions with major e minor version as defined. Patch version can change
}

#provider configuration
provider "google" {
  project = var.project_id
  region  = "us-central1"
  zone    = "us-central1-c"
}