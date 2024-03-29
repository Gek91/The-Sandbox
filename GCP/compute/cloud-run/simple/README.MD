Terraform documentation https://registry.terraform.io/providers/hashicorp/google/latest/docs/resources/cloud_run_v2_service#argument-reference

The terraform script deploys a simple application on cloud run. Default cloud run configuration for the authentication limits to only allowed users on the cloud project (with invoker role) to call the api.

Before deploy it's necessary to create the image to use in the example. See utils folder, contains artifact registry scripts and go web application<br>

To try it use<br>
<code>curl -H "Authorization: Bearer $(gcloud auth print-identity-token)" SERVICE_URL </code>