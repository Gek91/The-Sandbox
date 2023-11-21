<h4>Canary deployment</h4>
Create a service pointing two different deployment

<code>kubectl apply -f canary.yaml</code> to deploy the pod

<code>kubectl get pods --show-label</code> must show the  pods just created with the label of the deployments<br>
<code>kubectl get deployments</code> must show the deployments just created<br>
<code>kubectl get services</code> must show the service just created<br>
<code>kubectl describe service myservice</code> more information on service like the endpoint exposed ip

in a normal cluster you can access the service and the selected pod using the exposed ip of the service

in minikube you can find the service on the minikube vm using <br>
<code>minikube service myservice --url</code><br>
it shows the minikube tunnel ip and port to access the service

the service sometime will respond with one and sometime with the other deploy

<h4>clean up<h4>
<code>kubectl delete deployment mydeployment</code> <br>
<code>kubectl delete deployment mydeployment-canary</code> <br>
<code>kubectl delete service myservice</code>