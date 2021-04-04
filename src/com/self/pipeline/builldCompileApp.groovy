package com.self.pipeline

def call(Map pipelineParams) {
    sh '''
        cd CounterApp
        mvn clean install
    '''
}
