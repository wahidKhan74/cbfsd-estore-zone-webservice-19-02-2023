pipeline {
    agent any 

    triggers {
        pollSCM('* * * * *')
    }
    
    // Got permission denied while trying to connect to the Docker daemon socket at unix.
    // sudo usermod -a -G docker jenkins
    // restart jenkins server ->  sudo service jenkins restart
    
    stages {
        
        stage('Maven Compile') {
            steps {
                echo '----------------- This is a compile phase ----------'
                bat 'mvn clean compile'
            }
        }
        
            
        stage('Maven Build') {
             steps {
                echo '----------------- This is a build phase ----------'
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Docker Build') {
            steps {
                echo '----------------- This is a build docker image phase ----------'
                sh '''
                    docker image build -t estorezone-webservice .
                '''
            }
        }

        stage('Docker Deploy') {
            steps {
                echo '----------------- This is a docker deploment phase ----------'
                sh '''
            		docker container run --network springboot-mysql-net -p 9070:9070 --name estorezone-webservice-container -d estorezone-webservice
            	'''
            }
        }
    }
}
