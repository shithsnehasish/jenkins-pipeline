import com.self.pipeline.checkoutSCM;
import com.self.pipeline.builldCompileApp;
import com.self.pipeline.deployApp;
import com.self.pipeline.heathCheck;
import com.self.pipeline.environmentVars;

def call(Map pipelineParams) {

    try{
        timeout(time: 60, unit: 'MINUTES') {
            pipeline {
                new environmentVars().call(pipelineParams)
                node {
                    stage("Code Checkout") {
                        new checkoutSCM().call(pipelineParams)
                    }
                    stage("Build and Compile") {
                        new builldCompileApp().call(pipelineParams)
                    }
                    stage("Deploy") {
                        new deployApp().call(pipelineParams)
                    }
                    stage("Health Check") {
                        new heathCheck().call(pipelineParams)
                    }
                }
                if(pipelineParams.EMAIL_TO_LIST?.trim()){   
                            echo "email send enabled"   
                         sendEmail(pipelineParams,"SUCCESS")   
                }  
            }
        }
    } catch (err) {
        echo "in catch block" 
        echo "Caught: ${err}" 
        currentBuild.result = 'FAILURE' 
        if(pipelineParams.EMAIL_TO_LIST?.trim()){   
                        echo "email send enabled"   
                        sendEmail(pipelineParams,"FAILURE")   
            }  
        throw err
    }
}
