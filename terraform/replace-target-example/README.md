Before, it's needed to define the valuee of the variables using a tfvars files or by command line arguments

<code> terraform init </code> to create terraform project locally<br>

<code> terraform plan</code> to see the terraform operation<br>

<code> terraform apply </code> to deploy the infrastructure<br>

with the flag -replace it's possible to recreate the specific resources using the same configuration. Useful in case of malfunctioning resources<br>
To obtain the values used to specify the resource it's used <code>terraform state list</code> that return the list of the resources in the terraform state
<code>terraform apply -replace=google_compute_network.vpc_network</code> to redeploy the resource

In case the terraform configuration is changed the apply operation execute the entire plan. If it is needed to execute only part of it it will be used the option -target. Also in this case it's needed to specify the resources to modify<br>
change some resource attribute, use <code>terraform plan</code> to see the operations that terraform will perform and then execute <code>terraform apply -target=google_compute_network.vpc_network</code><br>


<code> terraform destroy</code> to clean up<br>