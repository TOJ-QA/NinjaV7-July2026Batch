pipeline {
    agent any

    stages {

        stage('Build') {
            steps {
                bat 'mvn clean package'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }
    }

    post {
        success {
            slackSend(
                channel: '#all-tracy-workspace',
                message: "✅ Jenkins build succeeded: ${env.JOB_NAME} #${env.BUILD_NUMBER}"
            )
        }
        failure {
            slackSend(
                channel: '#all-tracy-workspace',
                message: "❌ Jenkins build failed: ${env.JOB_NAME} #${env.BUILD_NUMBER}"
            )
        }
    }
}
