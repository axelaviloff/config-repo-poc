pipeline {
    agent {
        label 'java11'
    }
    tools {
        jdk 'java11'
    }
    environment {
        gradle = "./gradlew"
        
    }
    
    stages {
        stage("Update Keys") {
            steps {
                script {
                    def mydata = readYaml file: "gateway.yml"
                    mydata.aes = env.getProperty("AES")
                    mydata.iv = env.getProperty("IV")
                    writeYaml file: "gateway.yml", data: mydata, overwrite: true
                }
            }
        }
        stage("Read yml") {
            steps {
                script {
                    def mydata = readYaml file: "gateway.yml"
                    println mydata.aes
                    println mydata.iv
                    
                }
            }
        }
        
    }
    post {
        success {
            echo "sucesso!"
        }
        failure {
            echo "falhou!"
        }
    }
}

