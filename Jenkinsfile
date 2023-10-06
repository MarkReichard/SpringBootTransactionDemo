pipeline {
    agent any

    environment {
        DATABASE_URL='jdbc:mysql://localhost:3306/transaction_demo?allowPublicKeyRetrieval=True&useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=UTC;'
        RANDOM_API_SETTING='test'
    }

    tools {
        maven 'Maven3' // Name of the Maven installation in Jenkins configuration
        jdk 'JDK17' // Name of the JDK installation in Jenkins configuration
    }

    stages {
        stage('Checkout') {
            steps {
                // Checkout the code from the source control management (SCM)
                checkout scm
            }
        }

        stage('Compile and Test') {
            steps {
                script {
                    // Load credentials into environment variables
                    withCredentials([usernamePassword(credentialsId: 'mySqlCreds', usernameVariable: 'CREDS_USR', passwordVariable: 'CREDS_PSW')]) {
                        // Exporting the environment variables so they can be accessible by Maven
                        env.DATABASE_USERNAME = "${CREDS_USR}"
                        env.DATABASE_PASSWORD = "${CREDS_PSW}"
                    }
                }

                // Now you can run Maven as usual, and it will see DATABASE_USERNAME and DATABASE_PASSWORD
                sh 'mvn clean test'

                echo "RANDOM_API_SETTING: ${env.RANDOM_API_SETTING}"
                echo "DATABASE_URL: ${env.DATABASE_URL}"
                echo "DATABASE_USERNAME: ${env.DATABASE_USERNAME}"
                // Avoid echoing passwords in logs
                // echo "DATABASE_PASSWORD: ${env.DATABASE_PASSWORD}"
            }
        }

        stage('Build and Package') {
            steps {
                sh 'mvn package'
            }
        }
    }
}
