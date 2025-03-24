pipeline {
    agent any

    environment {
        PATH = "/usr/local/bin:/opt/homebrew/bin:$PATH"
        IMAGE_NAME_BACKEND = "starwars-project-backend"
        IMAGE_NAME_FRONTEND = "starwars-project-frontend"
    }

    stages {
        stage('Clone Repository') {
            steps {
                 git branch: 'main', url: 'https://github.com/inventorjii/Starwars.git'
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
                docker build -t $IMAGE_NAME_BACKEND backend/starwars
                docker build -t $IMAGE_NAME_FRONTEND frontend/
                '''
            }
        }

        stage('Deploy to Minikube') {
            steps {
                sh '''
                kubectl apply -f backend/starwars/backend-deployment.yaml
                kubectl apply -f frontend/frontend-deployment.yaml
                '''
            }
        }

        stage('Restart Pods') {
            steps {
                sh '''
                kubectl rollout restart deployment starwars-backend
                kubectl rollout restart deployment starwars-frontend
                '''
            }
        }
    }
}
