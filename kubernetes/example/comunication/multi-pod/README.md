<h4>Single pod with two container</h4>
Two containers in a single different pod that comunicate.
They are exposed using service inside the cluster (clusterIP)

<code>kubectl get pods</code> show no pods

<code>kubectl apply -f multy-pod.yaml</code> to deploy the infrastructure

<code>"kubectl get all"</code>
must show the resources created

in case of pod status "ErrImagePull" inspect pods log with
<code>kubectl logs mypod --all-containers</code>

<h4>Access the pod using proxy</h4>
to access the pod using kubernetes proxy without exposing the pod (like with a service)<br>
<code>kubectl port-forward podname 9081:8080</code> for the consumer
<code>kubectl port-forward podname 9080:8080</code> for the producer

it's now possible to call the container on the local machine at the specified port.
To test the comunication, after starting the port-fowrad on the consumer, use <code>http://localhost:9081/query </code>

<h4>clean up</h4>
Delete all the created resources