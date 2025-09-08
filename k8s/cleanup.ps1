# cleanup.ps1
Write-Host "🧹 Cleaning up Spring Boot Template namespace..."

# Kiểm tra namespace có tồn tại không
$namespaceExists = kubectl get namespace spring-boot-template --ignore-not-found

if ($namespaceExists) {
    Write-Host "🗑️  Deleting all resources in namespace spring-boot-template..."
    
    # Xóa tất cả resources trong namespace
    kubectl delete all --all -n spring-boot-template
    
    # Xóa configmaps
    kubectl delete configmap --all -n spring-boot-template
    
    # Xóa secrets
    kubectl delete secret --all -n spring-boot-template
    
    # Xóa ingress
    kubectl delete ingress --all -n spring-boot-template
    
    # Đợi một chút để resources được xóa
    Write-Host "⏳ Waiting for resources to be deleted..."
    Start-Sleep -Seconds 5
    
    Write-Host "✅ All resources deleted from namespace spring-boot-template"
}
else {
    Write-Host "ℹ️  Namespace spring-boot-template doesn't exist yet"
}

Write-Host "🧹 Cleanup completed!"