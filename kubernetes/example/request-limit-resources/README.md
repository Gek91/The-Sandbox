<h4>Request and limit container resources</h4>
define upper and lower limits of container resources usage
- request: used to define the minimum resource needed by the container. It's used to select the node with the available resources in which deploy the pod with the container
- limit: used to define the maximum of resource that the container can use. It's used to select the node with the available resources in which deploy the pod with the container

<code>kubectl get pods</code> show no pods

<code>kubectl apply -f resource-limit-pod.yaml</code> to deploy the pod

<code>"kubectl get pods"</code> 
must show the new pod just created

in case of pod status "ErrImagePull" inspect pods log with
<code>kubectl logs mypod --all-containers</code>

<h4>Access the pod using proxy</h4>
to access the pod using kubernetes proxy without exposing the pod (like with a service)<br>
<code>kubectl proxy</code><br>
expose reverse proxy on localhost:8001. Using a special path from browser is possible to access the pod api
<code>http://localhost:8001/api/v1/namespaces/default/pods/mypod/proxy/hello </code><br>
with mypods as the name of the pod and hello the api path

<h4>clean up<h4>
<code>kubectl delete pods mypod</code>