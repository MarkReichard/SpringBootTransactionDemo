pipeline {
    agent any

    environment {
        // Environment variables can be set here
        DATABASE_URL='placeholder'
        DATABASE_USERNAME='placeholder'
        DATABASE_PASSWORD='placeholder'
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
