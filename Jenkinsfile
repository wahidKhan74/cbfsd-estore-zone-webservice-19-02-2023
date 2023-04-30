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
                sh 'mvn clean compile'
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
                (if  [ $(docker ps -a | grep estorezone-webservice-container | cut -d " " -f1) ]; then \
                        echo $(docker rm -f estorezone-webservice-container); \
                        echo "---------------- successfully estorezone-webservice-container ----------------"
                     else \
                    echo OK; \
                 fi;);
            	docker container run --network springboot-mysql-net -p 9070:9070 --name estorezone-webservice-container -d estorezone-webservice
            	'''
            }
        }
    }
}
