#!/usr/bin/env groovy

pipeline {

    agent {
        docker {
            image 'DemoQA'
            args '-u root'
        }
    }

    stages {

        stage('Test') {
            steps {
                echo 'Testing...'
            }
        }
    }
}