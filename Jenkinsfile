// 所有脚本命令包含在pipeline{}中
pipeline {
    // 指定任务在哪个节点执行（Jenkins支持分布式）
    agent any

    // 声明全局环境变量方便后面使用，key = 'value'形式，指定变量名=变量值信息
    environment{
        harborUser = 'admin'
        harborPassword = 'Harbor12345'
        harborAddress = '47.109.146.41:8099'
        harborRepo = 'repo'
    }

    // 存放所有任务的合集
    stages {
        // 单个任务
        stage('checkout') {
            // 实现任务的具体流程
            steps {
                checkout scmGit(branches: [[name: '${tag}']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/longyulan/practise.git']])
            }
        }
        // 单个任务
        stage('maven打包') {
            // 实现任务的具体流程
            steps {
                sh '/Users/longyulan/apache-maven-3.6.3/bin/mvn clean package -DskipTest'
            }
        }
        stage('docker制作镜像') {
            steps {
                sh '''mv /Users/longyulan/.jenkins/workspace/pipeline/practise-bootstrap/target/practise-bootstrap-1.0-SNAPSHOT.jar ./
                docker build -t ${JOB_NAME}:${tag} .'''
            }
        }
        stage('将自定义镜像推送到harbor') {
            steps {
                sh '''docker login ${harborAddress} -u ${harborUser} -p ${harborPassword}
                docker tag ${JOB_NAME}:${tag} ${harborAddress}/${harborRepo}/${JOB_NAME}:${tag}
                docker push ${harborAddress}/${harborRepo}/${JOB_NAME}:${tag}'''
            }
        }
        stage('通过publish over ssh通知目标服务器') {
            steps {
                sshPublisher(publishers: [sshPublisherDesc(configName: 'test', transfers: [sshTransfer(cleanRemote: false, excludes: '', execCommand: "deploy.sh $harborAddress $harborRepo $JOB_NAME $tag $container_port $host_port", execTimeout: 120000, flatten: false, makeEmptyDirs: false, noDefaultExcludes: false, patternSeparator: '[, ]+', remoteDirectory: '', remoteDirectorySDF: false, removePrefix: '', sourceFiles: '')], usePromotionTimestamp: false, useWorkspaceInPromotion: false, verbose: false)])
            }
        }
    }
}
