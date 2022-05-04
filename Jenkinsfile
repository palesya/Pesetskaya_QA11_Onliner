pipeline {
    agent any

    tools {
        maven "MAVEN"
        jdk "JDK"
    }
    environment {
        SUITE = "src/test/resources/${params.Suite}.xml"
        env.PATH = "C:\Windows\System32"
    }

    stages {
        stage('Build') {
            steps {
                bat 'mvn clean'
            }
        }
        stage('Test run') {
            steps {
                echo "---------------------------------------------Started ${env.SUITE} -----------------------------------------------------"
                bat 'mvn test -Dsuite=${env.SUITE}'
            }
        }
        stage('Reports') {
            steps {
                script {
                    allure([
                        includeProperties: false,
                        jdk: '',
                        properties: [],
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: 'target/allure-results']]
                    ])
                }
            }
        }
    }
}