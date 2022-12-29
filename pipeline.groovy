#!/usr/bin/env groovy
def call(String gitrep, String gitbranch) {
pipeline {
    agent any

    stages {
        stage('Hello') {
            steps {
                echo 'Hello World'
            }
        }
        stage('Checkout') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: "*/${gitbranch}"]], extensions: [], userRemoteConfigs: [[url: "${gitrep}"]]])
            }
        }
        stage('print') {
            steps {
                sh 'ls'
            }
        }
        stage('build') {
            steps {
                sh "mvn clean verify"
            }
        }
    }
}
}
