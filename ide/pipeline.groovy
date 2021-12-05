pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Get some code from a GitHub repository
                git url: "https://github.com/moctezumaerik19/jgsu-spring-petclinic.git", branch: "main"
            }
        }
        stage('Build') {
            steps{
                bat "mvnw.cmd clean package"
            }
        

            post {
                always {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
    }
}
