Simple api example

To try<br>
run <code>go run .</code> to start the server <br>
run <code>go get .</code> to retrieve dependencies<br>
API exposed at localhost:8080

To dockerize
run <code>docker build -t DOCKER_REGISTRY_PATH/IMAGE_NAME .</code><br>
Before push it's necessary authentication with Artifact registry <code>gcloud auth configure-docker REGION-ID-docker.pkg.dev</code><br>
run <code>docker push DOCKER_REGISTRY_PATH/IMAGE_NAME</code><br>