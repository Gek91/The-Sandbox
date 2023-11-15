Example of logic executed by a CI/CD pipeline. In this case execvute manually<br>

<code>terraform init </code> to create terraform project locally<br>

The Terraform code is ready to be deployed, it's needed to define the valuee of the variables using a tfvars files-<br>

Run <code>terraform plan -out=plan.file</code> to validate the changes to be applied and save them in a binary file<br>

it's possible to read this file using <code>terraform show plan.file</code> or also convert it in json using the -json option<br>

Once the plan file is ready, it can be approved and applied with <code> terraform apply plan.file</code><br>

To clean up use <code> terraform destroy</code><br>