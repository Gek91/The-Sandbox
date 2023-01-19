<h4>Node port service</h4>
Simple example that expose a service as a port on the nodes 

<code>kubectl apply -f node-port-service.yaml</code> to deploy the pod

<code>kubectl get pods --show-label</code> must show the two pods just created with the label of the deploiy<br>
<code>kubectl get deployments</code> must show the deploy just created<br>ù
<code>kubectl get services</code> must show the service just created<br>
<code>kubectl describe service myservice</code> for more information like the port of the nodePOrt

To find the minikube node ip<br>
<code>minikube ip</code>

in a normal cluster you can access the service and the selected pod using node ip and nodeport defined port
ip:port

in minikube you can create a tunnel to the service on the minikube vm using
<code>minikube service myservice</code><br>
it shows the tunnel ip to access the service on localhost


<h4>clean up<h4>
<code>kubectl delete deployment mydeployment</code>
<code>kubectl delete service myservice</code>