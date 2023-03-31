<h4>Ingress</h4>
Simple example that uses a ingress

enable NGINX ingress controller:<br>
<code>minikube addons enable ingress</code><br>
verify ingress controller is running<br>
<code>kubectl get pods -n ingress-nginx</code>

<code>kubectl apply -f ingress.yaml</code> to deploy the pod

<code>kubectl get pods --show-label</code> must show the two pods just created with the label of the deploiy<br>
<code>kubectl get deployments</code> must show the deploy just created<br>
<code>kubectl get services</code> must show the service just created<br>
<code>kubectl get ingress</code> must shot the ingress just created<br>

As specied at ingress addon startup, to access ingress it is necessary to start a minikube tunnel<br>
<code>minikube tunnel</code></br>
then then ingress is availabel at 127.0.0.1 (localhost)<br>
<code>http://127.0.0.1/hello </code>

<h4>clean up<h4>
<code>kubectl delete deployment mydeployment</code><br>
<code>kubectl delete service myservice</code><br>
<code>kubectl delete ingress myingress</code>