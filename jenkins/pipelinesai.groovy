pipeline{
     agent any


     stages{
         stage('server details'){
	     steps{
	            
		  sh 'echo "Hostname: $(hostname)"'
	     }
	 }
     }
      


     }
