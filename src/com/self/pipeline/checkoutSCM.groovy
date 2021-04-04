package com.self.pipeline

def call(Map pipelineParams) {
    SCM_URL="https://github.com/shithsnehasish/"+pipelineParams.REPO_NAME
    echo "Code checkout from SCM Repo"
    checkout([$class: 'GitSCM', branches: [[name: "${pipelineParams.branch}"]],
        doGenerateSubmoduleConfigurations: false,
        extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: "${pipelineParams.REPO_NAME}"],
        [$class: 'CheckoutOption', timeout: 10]],
        submoduleCfg: [],
        userRemoteConfigs: [[url: "${SCM_URL}"]]])
    echo "Checkout is completed!"
}