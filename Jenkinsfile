#!/usr/bin/env groovy

pipeline {

    options {
        buildDiscarder(logRotator(numToKeepStr: '10', artifactNumToKeepStr: '10')
        disableConcurrentBuilds()
    }

    agent {
        label 'master'
    }

    stages {
        stage('Building image') {
            steps {
                sh -c 'docker build .'
            }
        }
    }
}