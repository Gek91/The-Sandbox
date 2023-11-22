<h4>Deploy</h4>
Simple example that uses a single deploy to create two pod using a replica set

<code>kubectl apply -f autoscaler.yaml</code> to deploy the pod

<code>kubectl get pods --show-label</code> must show the two pods just created with the label of the deploiy<br>
<code>kubectl get rs</code> must show the replica just created<br>
<code>kubectl get deployments</code> must sho the deploy just created<br>
<code>kubectl describe deployment mydeployment</code> for more information
<code>kubectl get hpa</code> to see the autoscaler

<code>kubectl describe hpa autoscaler</code> to see details about autoscaler

<h4>clean up<h4>
<code>kubectl delete hpa autoscaler</code>
<code>kubectl delete deployment mydeployment</code>