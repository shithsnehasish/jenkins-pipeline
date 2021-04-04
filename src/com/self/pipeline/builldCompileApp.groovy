package com.self.pipeline

def call(Map pipelineParams) {
    env.REPO_NAME = pipelineParams.REPO_NAME
    sh '''
        cd ${REPO_NAME}
        mvn clean install
    '''
}
