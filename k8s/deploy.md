1. kubectl create configmap spring-boot-template-config --from-env-file=.env
2. kubectl apply -f app-deployment.yaml
3. kubectl create configmap spring-boot-template-config --from-env-file=.env -n spring-boot-template
4. kubectl delete -f app-deployment.yaml -n spring-boot-template
5. kubectl apply -f app-deployment.yaml -n spring-boot-template
