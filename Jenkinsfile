node {
    def app

    stage('Clone repository') {
       git url:'https://github.com/gsoncloud/Blog_client.git'
    }

    stage('SonarQube analysis') {
        withSonarQubeEnv('localhost') {
            sh 'mvn -B -DskipTests clean package sonar:sonar'
        } 
    }
    
    stage('Build') { 
        sh 'mvn -B -DskipTests clean package' 
    }

    stage('Build image') {
      	app = docker.build("smokey-server:${env.BUILD_ID}")
    }

    stage('Push image') {
        sh "gcloud builds submit --tag gcr.io/devops-certification-lab/smokey-client:${env.BUILD_ID} ."
    }

    stage('connect to k8s cluster'){
        sh "gcloud container clusters get-credentials devops-cert-lab --zone us-central1-a --project devops-certification-lab"
    }
        
    stage('Replace build number'){
    	sh 'sed -i "s/BUILD/${BUILD_NUMBER}/g" client_dep.yaml'
    }

     stage('deploy') {
        sh "kubectl apply -f client_dep.yaml"
    }

    stage('service') {
        sh "kubectl apply -f client_service.yaml"
    }
    stage('selenium') {
        sh "mvn verify"
    }
}