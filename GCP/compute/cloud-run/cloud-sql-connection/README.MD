Terraform documentation https://registry.terraform.io/providers/hashicorp/google/latest/docs/resources/cloud_run_v2_service#argument-reference

The terraform script deploys some cloud run instances to show communication among them in different configuration.

Before deploy it's necessary to create the image to use in the example. See utils folder, contains artifact registry scripts and go web application<br>

Configure in the invoker service the environment variable URL with the base path of one of the receiver cloud run instance + /receive-call to call to set the endpoint to call in the example.
Use to execute the test<br>
<code>curl -H "Authorization: Bearer $(gcloud auth print-identity-token)" INVOKER_SERVICE_URL/api </code><br>
pointing the first service. Should respond OK

1) call open public endpoint <br>
The cloud run is invocable by everyone on the internet. Can be used to expose public API.
In this case we can use the make-call invoker endpoint to test this case

2) call authenticated public endpoint<br>
In this case we use the default cloud run configuration that limits the capability to invoke the service to only users on the cloud project with invoker role.
It's needed to add an Id Token to the call between the cloud run instance as indicated in https://cloud.google.com/run/docs/authenticating/service-to-service#run-service-to-service-example-go
The make-call-open-id implement this logic and is needed to test this case
Using make-call the service should decline the call with unauthenticated error


3) call private endpoint <br>
In this case the communication is in the VPC. The receiver receives only traffic from VPC so we need the two services to be connected to the VPC using serverless connector.
In sender configuration we stated that only private ip traffic goes through vpc connector, to be able to run the other cases.
As stated in https://cloud.google.com/run/docs/securing/private-networking, we need to make another configuration step.
to make sure that Cloud Run traffic route correctly to the receiver we need to enable Private Google Access in the subnet associated with the invoker 
and configure DNS to resolve run.app URL to the private.googleapis.com or restricted.googleapis.com and be routed to the VPC (https://cloud.google.com/vpc/docs/configure-private-google-access#config-domain)
Also in this case we use the make-call-open-id enpoint of the invoker to test the configuration

