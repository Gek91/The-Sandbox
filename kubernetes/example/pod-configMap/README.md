<h4>Pod with configmap</h4>
Single pod with config map

create configmap and pod using<br>
<code>kubectl apply -f pod-configmap.yaml</code>

enable proxy to access specific api to check config map values used in the application:<br>
<code>kubectl proxy</code><br>

access api at
<code>http://localhost:8001/api/v1/namespaces/default/pods/mypod/proxy/config-map </code>

it returns a string this some value<br>
- HELLO: load and map the full data filed of configMap in container enviroment veriable with the same name of the keys defined
- SINGLE_HELLO: load a single value from configMap and map it on a custom container enviroment
- property key1: load a property defined in the configMap as a volume ad use it as a property file in the application

<h4>clean up<h4>
<code>kubectl delete pods mypod</code>
<code>kubectl delete cm config</code>
