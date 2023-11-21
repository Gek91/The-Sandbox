<h4>priority class</h4>
Simple example that uses a single pod.

<code>kubectl get pods</code> show no pods

<code>kubectl apply -f priority-class.yaml</code> to deploy the pod

<code>"kubectl get pods"</code> 
must show the new pod just created

<code>kubectl get priorityclass</code> get priority class list

<code>kubectl describe pod mypod</code> to see pod priority class

<h4>clean up<h4>
<code>kubectl delete pods mypod</code><br>
<code>kubectl delete priority-class high-priority-nonpreempting</code><br>
<code>kubectl delete priority-class high-priority</code><br>
