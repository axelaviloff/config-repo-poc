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
                    println AES
                    println IV
                    def mydata = readYaml file: "gateway.yml"
                    mydata.aes = AES
                    mydata.iv = IV
                    writeYaml file: "gateway.yml", data: mydata, overwrite: true
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

