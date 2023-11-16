Create the bucket for the state<br>
<code> terraform init </code> to create terraform project locally<br>

<code> terraform apply </code> to deploy the infrastructure<br>

Decomment the vpc in main.tf <br>
comment the bucket in bucket.tg <br>
delete the .tfstate file <br> 
decomment the remote backend configuration using the correct name of the bucket<br>

<code> terraform apply </code> to deploy the infrastructure<br>

now the tfstate is created in the bucket.<br>

<code> terraform destroy</code> to clean up<br>