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
                echo 'Building..'
            }
        }

        stage('Test') {
            steps {
                echo 'Testing...'
            }
        }
    }
}