pipeline {
    agent {
        label 'java11'
    }
    tools {
        jdk 'java11'
    }
    environment {
        gradle = "./gradlew"
        AES = ""
        IV = ""
    }
    
    stages {
        stage("Get Param Keys") {
            steps {
                script {
                    AES = env.getProperty("AES")
                    IV = env.getProperty("IV")

                }
            }
        }
        stage("Update Keys") {
            steps {
                script {
                    def mydata = readYaml file: "mib-gateway.yml"
                    mydata.default-aes-key = AES
                    mydata.default-aes-iv = IV
                    writeYaml file: "gateway.yml", data: mydata
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

