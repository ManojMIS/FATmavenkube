pipeline {
    agent any

    environment {
        // Updated to match your Docker Hub and Inheritance project context
        DOCKER_IMAGE = "manojkumar2023/mavenkube:latest"
    }

    stages {
        stage('Checkout') {
            steps {
                // Pointing to your Inheritance repo
                git branch: 'main', url: 'https://github.com/ManojMIS/FATmavenkube.git'
            }
        }

        stage('Maven Build') {
            steps {
                echo "Compiling and Testing mavenkube Code..."
                bat 'mvn clean package' 
            }
        }

        stage('Docker Build') {
            steps {
                script {
                    echo "Building Docker Image..."
                    bat "docker build -t ${DOCKER_IMAGE} ."
                }
            }
        }

        stage('Docker Push') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'dockerhub-creds', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                        bat 'docker login -u %DOCKER_USER% -p %DOCKER_PASS%'
                        bat "docker push ${DOCKER_IMAGE}"
                    }
                }
            }
        }

        stage('K8s Deployment') {
            steps {
                script {
                    echo "Deploying to Kubernetes..."
                    // Using your existing Kubernetes credentials setup
                    withCredentials([file(credentialsId: 'manojkumar2023', variable: 'KUBE_CFG')]) {
                        bat 'kubectl apply -f deployment.yaml --kubeconfig="%KUBE_CFG%"'
                        // Force update the deployment to use the newly pushed image
                        bat 'kubectl rollout restart deployment/mavenkube-deployment --kubeconfig="%KUBE_CFG%"'
                    }
                }
            }
        }
    }
    
    post {
        success {
            echo "CI/CD Pipeline Complete. Your mavenkube App is live!"
        }
    }
}
