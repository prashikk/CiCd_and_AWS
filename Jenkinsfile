pipeline {
    agent any

    tools {
        maven 'Maven3'
    }

    environment {
        IMAGE_NAME = "prashikk/ping-api"
        EC2_HOST = "13.239.3.227"
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Package') {
            steps {
                sh 'mvn package -DskipTests'
            }
        }

        stage('Docker Build & Push') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'dockerhub',
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                )]) {

                    sh '''
                        echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin

                        docker buildx build \
                            --platform linux/amd64 \
                            -t $IMAGE_NAME:latest \
                            --push .

                        docker logout
                    '''
                }
            }
        }

        stage('Deploy to EC2') {
            steps {
                sshagent(credentials: ['ec2-ssh']) {
                    sh '''
                        ssh -o StrictHostKeyChecking=no ubuntu@$EC2_HOST << EOF

                        docker pull prashikk/ping-api:latest

                        docker stop ping-api || true
                        docker rm ping-api || true

                        docker run -d \
                            --name ping-api \
                            -p 8080:8080 \
                            --restart unless-stopped \
                            prashikk/ping-api:latest

                        docker image prune -f

                        EOF
                    '''
                }
            }
        }
    }

    post {

        success {
            echo "Deployment Successful!"
        }

        failure {
            echo "Pipeline Failed!"
        }

        always {
            cleanWs()
        }
    }
}