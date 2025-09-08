# full-deploy.ps1
Write-Host "🔄 Full deployment: Clean up and Deploy..."

# Chạy cleanup
Write-Host "🧹 Running cleanup..."
.\cleanup.ps1

# Đợi một chút
Start-Sleep -Seconds 3

# Chạy deploy
Write-Host "🚀 Running deployment..."
.\deploy-full.ps1

Write-Host "🎉 Full deployment completed!"