node {
    def app

    stage('Clone repository') {
        /* Let's make sure we have the repository cloned to our workspace */

        git url:'https://github.com/gsoncloud/Blog_client.git'
    }

    stage('SonarQube analysis') {
        withSonarQubeEnv('localhost') {
            sh 'mvn -B -DskipTests clean package sonar:sonar'
        } // submitted SonarQube taskId is automatically attached to the pipeline context
    }
    
    stage('Build') { 
        sh 'mvn -B -DskipTests clean package' 
    }

    stage('Build image') {
        /* This builds the actual image; synonymous to
         * docker build on the command line */

        app = docker.build("smokey-server:${env.BUILD_ID}")
    }

    stage('Push image') {
        sh "gcloud builds submit --tag gcr.io/devops-certification-lab/smokey-client:${env.BUILD_ID} ."
    }

    stage('connect to k8s cluster'){
        sh "gcloud container clusters get-credentials devops-cert-lab --zone us-central1-a --project devops-certification-lab"
    }

     stage('deploy') {
        sh "kubectl apply -f client_dep.yaml"
    }

    stage('service') {
        sh "kubectl apply -f client_service.yaml"
    }
}