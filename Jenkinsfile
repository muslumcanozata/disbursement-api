pipeline{
    agent any
    environment {
        IMAGE_NAME = 'disbursement-api'
        VERSION = '0.0.1-SNAPSHOT'
        CONTAINER_NAME = 'disbursement-api:0.0.1-SNAPSHOT'
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
                    sh '''
                        docker build -t ${CONTAINER_NAME} .
                    '''
                }
            }
        }
        stage("Docker Run"){
            agent any
            steps{
                script{
                    sh '''
                        docker run -d -p 8087:8087 ${CONTAINER_NAME}
                    '''
                }
            }
        }
    }
}