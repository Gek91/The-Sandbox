<h4>Taint tolleration</h4>
Simple example that uses taint and tolerations to schedule pods on nodes

execute:
<code>kubectl taint nodes minikube key1=value1:NoSchedule</code>
all the pods without this toleration will not be scheduled

<code>kubectl get pods</code> show no pods

<code>kubectl apply -f toleration.yaml</code> to deploy the pod

<code>"kubectl get pods"</code> 
must show the new pods just created

all the pods with the right tolerations will be scheduled
<code>kubectl describe pods mypod1</code> show why the pods is not scheduled

<h4>clean up<h4>
<code>kubectl taint nodes minikube key1=value1:NoSchedule-</code><br>
<code>kubectl delete pods mypod1</code><br>
<code>kubectl delete pods mypod2</code><br>
<code>kubectl delete pods mypod3</code><br>
<code>kubectl delete pods mypod4</code><br>