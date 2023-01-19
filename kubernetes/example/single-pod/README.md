<h4>Single pod</h4>
Simple example that uses a single pod.

<code>kubectl get pods</code> show no pods

<code>kubectl apply -f single-pod.yaml</code> to deploy the pod

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