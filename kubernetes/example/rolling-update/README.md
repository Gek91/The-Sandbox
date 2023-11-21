<h4>Deploy</h4>
Simple example that uses a single deploy to create two pod using a replica set

<code>kubectl apply -f deploy.yaml</code> to deploy the pod

<code>kubectl get pods --show-label</code> must show the two pods just created with the label of the deploiy<br>
<code>kubectl get rs</code> must show the replica just created<br>
<code>kubectl get deployments</code> must sho the deploy just created<br>
<code>kubectl describe deployment mydeployment</code> for more information

<code>kubectl get pods --selector="app=mydeploy" --output=wide</code>

once activated the proxy it is possible to use it to check one of the pods response <br>
<code>kubectl proxy</code> </br>
<code>http://localhost:8001/api/v1/namespaces/default/pods/INSERT_ONE_OF_THE_POD_NAME/proxy/hello </code></br>


To execute a rolling update it must be changed the version of the image of the deployment. After that it's needed to apply the configuration
<code>kubectl apply -f deploy.yaml</code>

<code>kubectl get rs</code> must show the now there is two replica set, the new one and the old one<br>
The pod of the old replica set have been deleted and the pods of the new replica set have been created.<br>

Now check the response of one of the new pods
<code>http://localhost:8001/api/v1/namespaces/default/pods/INSERT_ONE_OF_THE_POD_NAME/proxy/hello </code></br>
it should respond with the new version of the container image

now we can rollback the deployment to the old replicaset
<code>kubectl rollout history deploy/mydeployment</code> shows the deployment version history<br>
we can use a specific version to rollback the deployment to it. To know the specific version linked with a replicaset use <code>kubectl describe rs REPLICA_SET_NAME</code> and find the revision value in annotation field<br>
<code>kubectl rollout undo deploy/mydeployment --to-revision=1</code>

<h4>clean up<h4>
<code>kubectl delete deployment mydeployment</code