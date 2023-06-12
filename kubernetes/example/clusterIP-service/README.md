<h4>ClusterIp Service</h4>
Simple example that crates a service with cluster local ip

<code>kubectl apply -f cluster-ip-service.yaml</code> to deploy the pod

<code>kubectl get pods --show-label</code> must show the two pods just created with the label of the deploiy<br>
<code>kubectl get deployments</code> must show the deploy just created<br>
<code>kubectl get services</code> must show the service just created<br>
<code>kubectl describe service myservice</code> for more information

once activated the proxy it is possible to use the service to invoke pods api<br>
<code>kubectl proxy</code> </br>
<code>http://localhost:8001/api/v1/namespaces/default/services/myservice/proxy/hello </code></br>

<h4>clean up<h4>
<code>kubectl delete deployment mydeployment</code></br>
<code>kubectl delete service myservice</code>