<h4>ClusterIp Service</h4>
Simple example that crates a service and a headless service.
The headless service does not loadbalance the pods, create an endpoint that point directly to the pod<br>
Resolving headless service DNS name will return all the Pods ip (multiple DNS A records). Useful if you need to get all pod IPs<br>

<code>kubectl apply -f cluster-ip-service.yaml</code> to deploy the pod

<code>kubectl get pods --show-label</code> must show the two pods just created with the label of the deploiy<br>
<code>kubectl get deployments</code> must show the deploy just created<br>
<code>kubectl get services</code> must show the services just created, the headless service <br>
<code>kubectl describe service myservice</code> for more information
<code>kubectl describe service myservice-headless</code> for more information

using the pod created only to be used in this command we execute DNS lookup to see the enpoint (endpointslice) linked to the services

<code>kubectl exec -i -t mypod -- nslookup myservice</code> return the single DNS record relative to the clusterIP of the service. The same obtained in <code>kubectl get services</code> <br>
<code>kubectl exec -i -t mypod -- nslookup myservice-headless</code> <br> return multiple DNS record, each point to a single pods. The same returned in <code>kubectl get pod -o wide</code>

<h4>clean up<h4>
<code>kubectl delete deployment mydeployment</code></br>
<code>kubectl delete service myservice</code> </br>
<code>kubectl delete service myservice-headless</code> </br>
<code>kubectl delete pod mypod</code> </br>