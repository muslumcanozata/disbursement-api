pipeline{
    agent any
    environment {
        IMAGE_NAME = 'disbursement-api'
        VERSION = '0.0.1-SNAPSHOT'
        CONTAINER_NAME = 'disbursement-api:0.0.1-SNAPSHOT'
        DOCKERHUB_USERNAME = 'muslumcanozata'
    }
    stages{
        stage("Build Project") {
            agent {
                docker{
                    image 'openjdk:20-jdk-slim'
                }
            }
            steps{
                script{
                    sh '''
                        chmod +x ./gradlew
                        ./gradlew build
                    '''
                }
            }
        }
        stage("Destroy Old Container") {
            agent any
            steps{
                script{
                    sh '''
                        CONTAINER_ID=$(docker ps -a | grep ${CONTAINER_NAME} | awk '{ print $1 }')
                        if [ -n "$CONTAINER_ID" ]
                        then
                            docker stop $CONTAINER_ID
                            docker rm -f $CONTAINER_ID
                        fi
                    '''
                }
            }
        }
        stage("Docker Build & Docker Push"){
            agent any
            steps{
                script{
                    withCredentials([usernamePassword(credentialsId: 'docker-hub', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
                        sh '''
                            docker build -t ${DOCKERHUB_USERNAME}/${CONTAINER_NAME} .
                            docker login -u $USERNAME -p $PASSWORD
                            docker push ${DOCKERHUB_USERNAME}/${CONTAINER_NAME}
                        '''
                    }
                }
            }
        }
        stage("Docker Run"){
            agent any
            steps{
                script{
                    sh '''
                        docker run -d -p 8087:8087 ${DOCKERHUB_USERNAME}/${CONTAINER_NAME}
                    '''
                }
            }
        }
    }
}