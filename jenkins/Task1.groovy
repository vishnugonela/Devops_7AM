pipeline {
    agent any
    
    stages {
        stage('Get Master Server Details') {
            steps {
                script {
                    def masterNode = Jenkins.getInstance().getNode('master')
                    echo "Master Server Name: ${masterNode.name}"
                    echo "Master Server Description: ${masterNode.description}"
                    echo "Master Server Labels: ${masterNode.getLabelString()}"
                    echo "Master Server Num Executors: ${masterNode.getNumExecutors()}"
                    echo "Master Server Workspace Path: ${masterNode.getWorkspaceFor(TASK_NAME)}"
                    // Add more details as needed
                }
            }
        }
        
        stage('Get Slave Server Details') {
            steps {
                script {
                    def slaveNodes = Jenkins.getInstance().getNodes().findAll { node -> node.getNodeName() != 'master' }
                    for (def slaveNode in slaveNodes) {
                        echo "Slave Server Name: ${slaveNode.name}"
                        echo "Slave Server Description: ${slaveNode.description}"
                        echo "Slave Server Labels: ${slaveNode.getLabelString()}"
                        echo "Slave Server Num Executors: ${slaveNode.getNumExecutors()}"
                        echo "Slave Server Workspace Path: ${slaveNode.getWorkspaceFor(TASK_NAME)}"
                        // Add more details as needed
                    }
                }
            }
        }
    }
}

