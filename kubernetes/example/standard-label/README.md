<h4>Standard label</h4>
Simple example of full deployment using standard defined labels on the application component

<code>kubectl apply -f standard-label-full-app.yaml</code> to deploy the pod

<code>kubectl get pods --show-labels</code> must show the two pods just created with the label of the deploiy<br>
<code>kubectl get deployments --show-labels</code> must show the deploy just created<br>
<code>kubectl get services --show-labels</code> must show the service just created<br>
<code>kubectl describe service myservice</code> more information on service like the endpoint exposed ip

in a normal cluster you can access the service and the selected pod using the exposed ip of the service

in minikube you can find the service on the minikube vm using <br>
<code>minikube service myservice --url</code><br>
it shows the minikube tunnel ip and port to access the service


<h4>clean up<h4>
<code>kubectl delete deployment mydeployment</code> <br>
<code>kubectl delete service myservice</code>