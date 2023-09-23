pipeline{
    agent any
    stages{
        stage("checkout"){
            steps{
                sh "git clone https://github.com/shubh1sinha/employee-attendance-amangement-application.git"
            }
        }
        
        stage("Package-Application"){
            steps{
			dir('employee-attendance-management-application'){
			sh "cd"
			    dir('employee-common-model'){
				sh "/home/azureuser/apache-maven-3.8.6/bin/mvn clean"
				sh "/home/azureuser/apache-maven-3.8.6/bin/mvn clean install"
                sh "pwd"
            }
			dir('employee-attendance-management-application'){
			sh "cd"
			    dir('employee-tracking-system'){
				sh "/home/azureuser/apache-maven-3.8.6/bin/mvn clean package"
                sh "pwd"
            }
			dir('employee-attendance-management-application'){
			sh "cd"
			    dir('attendance-computing-system'){
				sh "/home/azureuser/apache-maven-3.8.6/bin/mvn clean package"
                sh "pwd"
            }
		   }
		  }
        }
		
        stage("Dockerize Employee-Tracking-Application"){
            steps{
			dir('employee-attendance-management-application'){
				 sh "cd"
			    dir('employee-tracking-system'){
				    sh "cd"
                    sh "pwd"
                    sh "sudo docker build -t shubh1sinha/employee-tracking-system:1.1 ."
            }
			    dir('employee-attendance-management-application') {
				    sh "cd"
                    sh "pwd"
                }
            }
        }
		
		stage("Dockerize Attendance-Computing-Microservice"){
            steps{
			    dir('attendance-computing-system') {
				    sh "cd"
                    sh "pwd"
                    sh "sudo docker build -t shubh1sinha/attendace-computing-system:1.1 ."
                }
			    dir('employee-attendance-management-application') {
				    sh "cd"
                    sh "pwd"
                }
            }
        }

		stage("Pushing Images"){
            steps{
                sh " sudo docker push shubh1sinha/employee-tracking-system:1.1"
                sh " sudo docker push shubh1sinha/attendace-computing-system:1.1"
            }
        }

        stage("helm-chart"){
            steps{
                        sh 'pwd'
                        sh 'cp -R helm/* .'
						sh 'ls -ltr'
                        sh 'pwd'
                        sh '/usr/local/bin/helm upgrade --install employee-app employee'
						sh '/usr/local/bin/helm upgrade --install attendance-app attendace'
            }
        }
		
		stage("Kubernetes-check-pods"){
            steps{
				sh 'sudo docker image ls'
                sh 'helm list'
                sh 'kubectl get pods'
                sh 'kubectl get svc'
            }
        }
		
    }
 }