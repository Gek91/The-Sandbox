<h4>Liveness pod</h4>
Simple that show liveness pod lifecycle. The container has an api that return 500 after 30 seconds from startup<br><br>
<code>kubectl get pods</code> show no pods

<code>kubectl apply -f liveness-pod.yaml</code> to deploy the pod

<code>"kubectl get pods"</code> 
must show the new pod just created in running status

after 30 seconds the pod is deleted because of liveness probe failing.

the see the pod status timeline use<br>
<code>kubeclt describe pod livenesspod</code>

<h4>clean up<h4>
<code>kubectl delete pods livenesspod</code>