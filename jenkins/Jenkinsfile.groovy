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
        stage("Get Param Keys") {
            steps {
                script {
                    def aes1 = env.getProperty("AES")
                    def iv1 = env.getProperty("IV")
                    println aes1
                    println iv1
                    println "Chave ${AES}"
                    println "Chave ${IV}"
                    
                    

                }
            }
        }
        stage("Update Keys") {
            steps {
                script {
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

