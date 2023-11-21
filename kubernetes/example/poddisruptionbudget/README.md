<h4>Pod disruption budget</h4>
Simple example that uses a single deploy to create two pod using a replica set. The uses a pod disruption budget to manage minimum number of healty pods

<code>kubectl apply -f poddisruptionbudget.yaml</code> to deploy the pod

<code>kubectl get pods --show-label</code> must show the two pods just created with the label of the deploiy<br>
<code>kubectl get rs</code> must show the replica just created<br>
<code>kubectl get deployments</code> must sho the deploy just created<br>
<code>kubectl describe deployment mydeployment</code> for more information

<code>kubectl get pods --selector="app=mydeploy" --output=wide</code>

<code>kubectl get poddisruptionbudget</code> to see the pod disruption budget

the budget prevent to have less than 2 pod in case of disruption. There are cases that this constraint is ignored (like scaling manually)

<h4>clean up<h4>
<code>kubectl delete deployment mydeployment</code>
<code>kubectl delete poddisruptionbudget pdb</code>
