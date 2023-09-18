<h4>Single pod with two container</h4>
Two containers in a single pod that comunicate.
The two container start on two different ports of the pod. This is managed using configMap applied to the consumer side

<code>kubectl get pods</code> show no pods

<code>kubectl apply -f single-pod.yaml</code> to deploy the pod

<code>"kubectl get pods"</code>
must show the new pod just created

in case of pod status "ErrImagePull" inspect pods log with
<code>kubectl logs mypod --all-containers</code>

<h4>Access the pod using proxy</h4>
to access the pod using kubernetes proxy without exposing the pod (like with a service)<br>
<code>kubectl port-forward mypod 9081:8081</code> for the consumer
<code>kubectl port-forward mypod 9080:8080</code> for the producer

it's now possible to call the container on the local machine at the specified port.
To test the comunication, after starting the port-fowrad on the consumer, use <code>http://localhost:9081/query </code>

<h4>clean up<h4>
<code>kubectl delete pods mypod</code>