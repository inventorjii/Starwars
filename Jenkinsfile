pipeline {
    agent any

    environment {
        IMAGE_NAME_BACKEND = "starwars-project-backend"
        IMAGE_NAME_FRONTEND = "starwars-project-frontend"
    }

    stages {
        stage('Clone Repository') {
            steps {
                git 'https://github.com/inventorjii/Starwars.git'
            }
        }

        stage('Build Backend') {
            steps {
                sh 'cd backend/starwars && mvn clean package -DskipTests'
            }
        }

        stage('Build Frontend') {
            steps {
                sh 'cd frontend && npm install && npm run build'
            }
        }

        stage('Build Docker Images') {
            steps {
                sh '''
                eval $(minikube docker-env)
                docker build -t $IMAGE_NAME_BACKEND backend/
                docker build -t $IMAGE_NAME_FRONTEND frontend/
                '''
            }
        }

        stage('Deploy to Minikube') {
            steps {
                sh '''
                kubectl apply -f k8s/backend-deployment.yaml
                kubectl apply -f k8s/frontend-deployment.yaml
                '''
            }
        }

        stage('Restart Pods') {
            steps {
                sh '''
                kubectl rollout restart deployment backend-deployment
                kubectl rollout restart deployment frontend-deployment
                '''
            }
        }
    }
}
