<h4>Load balancer Service</h4>
Simple example expose a service as an external endpoints 

<code>kubectl apply -f load-balancer-service.yaml</code> to deploy the pod

<code>kubectl get pods --show-label</code> must show the two pods just created with the label of the deploiy<br>
<code>kubectl get deployments</code> must show the deploy just created<br>ù
<code>kubectl get services</code> must show the service just created<br>
<code>kubectl describe service myservice</code> more information on service like the endpoint exposed ip

in a normal cluster you can access the service and the selected pod using the exposed ip of the service

in minikube you must can find a the service on the minikube vm using
<code>minikube service myservice --url</code><br>
it shows the minikube tunnel ip and port to access the service


<h4>clean up<h4>
<code>kubectl delete deployment mydeployment</code>
<code>kubectl delete service myservice</code>