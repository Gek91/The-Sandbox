Create detached resource<br>
<code>docker run --name hashicorp-learn --detach --publish "0.0.0.0:8080:80" nginx:latest</code>


<code> terraform init </code> to create terraform project locally<br>


Get docker specific identifier<br>
<code>docker inspect --format="{{.ID}}" hashicorp-learn</code><br>
save the value as value in main


To generate the configuration use the command<br>
<code>terraform plan -generate-config-out=generated.tf</code><br>
prune eventually the configuration from attributes. Check the name of the resource generated

apply the configuration<br>
<code>terraform apply</code>

<code> terraform destroy</code> to clean up<br>