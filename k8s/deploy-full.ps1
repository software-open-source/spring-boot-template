# deploy-full.ps1
Write-Host "🚀 Deploying Spring Boot Template with PostgreSQL to Kubernetes..."

# Set Minikube profile
Write-Host "🔧 Setting Minikube profile..."
minikube profile cluster-01

# Enable ingress addon
Write-Host "🔌 Enabling Ingress addon..."
minikube addons enable ingress

# Apply namespace first
Write-Host "📋 Creating namespace..."
kubectl apply -f namespace.yaml

# Wait for namespace to be ready
Start-Sleep -Seconds 2

# Deploy PostgreSQL first
Write-Host "🐘 Deploying PostgreSQL..."
kubectl apply -f postgres-configmap.yaml
kubectl apply -f postgres-secret.yaml
kubectl apply -f postgres-deployment.yaml

# Wait for PostgreSQL to be ready
Write-Host "⏳ Waiting for PostgreSQL to be ready..."
try {
    kubectl wait --for=condition=ready pod -l app=postgres -n spring-boot-template --timeout=300s
}
catch {
    Write-Host "⚠️  Timeout waiting for PostgreSQL, continuing..."
}

# Deploy Spring Boot application
Write-Host "📦 Deploying Spring Boot Template..."
kubectl apply -f springboot-configmap.yaml
kubectl apply -f springboot-secret.yaml
kubectl apply -f springboot-deployment.yaml

# Apply Ingress
Write-Host "🌐 Applying Ingress..."
kubectl apply -f ingress.yaml

# Wait for Spring Boot to be ready
Write-Host "⏳ Waiting for Spring Boot to be ready..."
try {
    kubectl rollout status deployment/spring-boot-template -n spring-boot-template --timeout=300s
}
catch {
    Write-Host "⚠️  Timeout waiting for Spring Boot rollout, continuing..."
}

# Show status
Write-Host "📊 Deployment status:"
kubectl get all -n spring-boot-template

Write-Host "✅ Deployment completed!"
Write-Host ""
Write-Host "🔧 To access your application:"
Write-Host "   Add this line to your C:\Windows\System32\drivers\etc\hosts file:"
Write-Host "   $(minikube ip) spring-boot-template.local"
Write-Host ""
Write-Host "   Then access via:"
Write-Host "   - NodePort: http://$(minikube ip):30080"
Write-Host "   - Ingress:  http://spring-boot-template.local"