<h4>Namespaces</h4>
Simple example twith namespaces

<code>kubectl get ns</code> to see the list of namespaces already created in the cluster.

<code>kubectl create namespace test</code>

<code>kubectl get pods --namespace=test</code> show no pods

<code>kubectl apply -f single-pod.yaml</code> to deploy the pod

<code>"kubectl get pods --namespace=test"</code> 
must show the new pod just created

in case of pod status "ErrImagePull" inspect pods log with
<code>kubectl logs mypod --all-containers --namespaces=test</code>

<h4>Access the pod using proxy</h4>
to access the pod using kubernetes proxy without exposing the pod (like with a service)<br>
<code>kubectl proxy</code><br>
expose reverse proxy on localhost:8001. Using a special path from browser is possible to access the pod api
<code>http://localhost:8001/api/v1/namespaces/test/pods/mypod/proxy/hello </code><br>
with mypods as the name of the pod, test as the name of the namespace and hello the api path

<h4>clean up<h4>
<code>kubectl delete pods mypod</code>
<code>kubectl delete namespace test</code>