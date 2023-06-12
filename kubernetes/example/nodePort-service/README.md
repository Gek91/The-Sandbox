<h4>Node port service</h4>
Simple example that expose a service as a port on the nodes 

<code>kubectl apply -f node-port-service.yaml</code> to deploy the pod

<code>kubectl get pods --show-label</code> must show the two pods just created with the label of the deploiy<br>
<code>kubectl get deployments</code> must show the deploy just created<br>
<code>kubectl get services</code> must show the service just created<br>
<code>kubectl describe service myservice</code> for more information like the port of the nodePort

you can access the service to test it using port forwarding feature
<code>kubectl port-forward service/myservice 8080:8080</code> and access the service on the local machine on localhost:8080<br>
or using the proxy using <code> kubectl proxy</code> on the url
http://localhost:8001/api/v1/namespaces/default/services/myservice/proxy/hello

alternative in minikube you can create a tunnel to the service on the minikube vm using
<code>minikube service myservice</code><br>
it shows the tunnel ip to access the service on localhost

<h4>clean up<h4>
<code>kubectl delete deployment mydeployment</code>
<code>kubectl delete service myservice</code>
