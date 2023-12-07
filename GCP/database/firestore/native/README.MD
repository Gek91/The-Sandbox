Terraform documentation https://registry.terraform.io/providers/hashicorp/google/latest/docs/resources/firestore_database?q=google_firestore

The terraform script deploys a new firestore database

Import database dump example folder in a Google Storage bucket
Import dump on firestore with the command<br>
<code>gcloud firestore import gs://PATHTOFOLDER --database=DATABASE_NAME</code>

To create indexes use <code>firebase deploy --only firestore</code> from the folder containing the index file


