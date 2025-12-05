#!/usr/bin/env groovy

pipeline {

    agent {
        docker {
            image 'DemoQA'
            args '-u root'
        }
    }

    stages {
        stage('Build') {
            steps {
                docker build -t DemoQA .
            }
        }

        stage('Test') {
            steps {
                echo 'Testing...'
            }
        }
    }
}