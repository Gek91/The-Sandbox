
module "local_module_1" {
  source = "./modules/local-module"

  project_id = var.project_id #pass var value to required module input
}

module "local_module_2" {
  source = "./modules/local-module"

  vpc_name = "vpc2"

  project_id = var.project_id #pass var value to required module input
}