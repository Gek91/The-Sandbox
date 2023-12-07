Terraform documentation https://registry.terraform.io/providers/hashicorp/google/latest/docs/resources/cloud_run_v2_service#argument-reference

The terraform script deploys some cloud run instaces to show comunication among them in different configuration.
Default cloud run configuration for the authentication limits to only allowed users on the cloud project (with invoker role) to call the api.
It's needed also to add an Id Token to the call between the cloud run instance as indecated in https://cloud.google.com/run/docs/authenticating/service-to-service#run-service-to-service-example-go
That has been implemented in the api service logics

1) call using public endpoint<br>
Configure in the first service the environment variable URL with the base path of the second cloud run instance + /receive-call to set the endpoint to call in the example.
Use to execute the communication<br>
<code>curl -H "Authorization: Bearer $(gcloud auth print-identity-token)" SERVICE_URL/make-call-open-id </code><br>
pointing the first service. Should responde OK