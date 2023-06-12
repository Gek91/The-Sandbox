<h3>Local cluster using minikube</h3>

Need of load local docker images in minikube docker registry to use them in the cluster<br>
<code>minikube image load <IMAGE_NAME></code>

To point the docker client to Minikube docker deamon<br>
<code>eval $(minikube docker-env)</code><br>
To Undo this settings use
<code>eval $(minikube docker-env --unset)</code>

<h3>Enable dashboard</h3>
Follow instructions<br>
https://kubernetes.io/docs/tasks/access-application-cluster/web-ui-dashboard/
https://github.com/kubernetes/dashboard/blob/master/docs/user/access-control/creating-sample-user.md

<h4>Apply files using</h4>
kubectl apply -f filename.yaml

<h4>Get token using</h4>
kubectl -n kubernetes-dashboard create token admin-user

<h4>Dashboard URL:</h4>
http://localhost:8001/api/v1/namespaces/kubernetes-dashboard/services/https:kubernetes-dashboard:/proxy/#/workloads?namespace=default