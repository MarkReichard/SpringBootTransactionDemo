pipeline {
    agent any

    environment {
        DATABASE_URL='jdbc:mysql://localhost:3306/transaction_demo?allowPublicKeyRetrieval=True&useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=UTC;'
        DATABASE_USERNAME='root'
        DATABASE_PASSWORD='example'
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
                // Compiling and testing the project
                sh 'mvn clean test'
            }
        }

        stage('Build and Package') {
            steps {
                // Building and packaging the project (e.g., into a .jar or .war)
                sh 'mvn package'
            }
        }

    }
}
