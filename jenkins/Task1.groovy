pipeline{
    agent any
    
    stages{
        stage('server details'){
            steps{
                    #!/bin/bash
                sh 'echo "Hostname: $(hostname)"'
            }
        }
        
       
        }
        
        }
    
