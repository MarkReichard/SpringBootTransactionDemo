pipeline {
    agent any

    environment {
        mySqlCreds = credentials('mySqlCreds')
        DATABASE_URL='jdbc:mysql://localhost:3306/transaction_demo?allowPublicKeyRetrieval=True&useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=UTC;'
        DATABASE_USERNAME=$mySqlCreds_USR //'root'
        DATABASE_PASSWORD=$mySqlCreds_PSW//'example'
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
                // Compiling and testing the project
                sh 'mvn clean test'
                //check environment variable
                echo "RANDOM_API_SETTING: ${env.RANDOM_API_SETTING}"
                echo "DATABASE_URL: ${env.DATABASE_URL}"
                echo "DATABASE_USERNAME: ${env.DATABASE_USERNAME}"
                echo "DATABASE_PASSWORD: ${env.DATABASE_PASSWORD}"

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
