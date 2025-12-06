#!/usr/bin/env groovy

pipeline {

    agent {
        label 'master'
    }

    options {
        buildDiscarder(logRotator(numToKeepStr: '10', artifactNumToKeepStr: '10')
        disableConcurrentBuilds()
    }

    stages {
        stage('Building image') {
            steps {
                sh -c 'docker build .'
            }
        }
    }
}