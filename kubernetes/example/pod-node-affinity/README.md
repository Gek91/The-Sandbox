<h4>Pod and node affinity</h4>
Simple example for node and pod affinity on pods

<code>kubectl get pods</code> show no pods

<code>kubectl apply -f pod-affinity.yaml</code> to deploy the pod

all the three pods are created but mypod2 is not deployed.<br>
<code>kubectl describe pods mypod2</code> shows that there is no node that satisfy node constraints of the pod affinity

<h4>clean up<h4>
<code>kubectl delete pods mypod1</code><br>
<code>kubectl delete pods mypod2</code><br>
<code>kubectl delete pods mypod3</code><br>