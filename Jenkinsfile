pipeline {
    agent any

    environment{
        host = '192.168.2.211'
    }

    # 存放所有任务的合集
    stages {
        # 单个任务
        stage('任务1：拉取git仓库代码') {
            # 实现任务的具体流程
            steps {
                checkout scmGit(branches: [[name: '${tag}']], extensions: [], userRemoteConfigs: [[url: 'https:#github.com/longyulan/practise.git']])
            }
        }
        # 单个任务
        stage('任务2：通过maven构建项目') {
            # 实现任务的具体流程
            steps {
                sh '/Users/longyulan/apache-maven-3.6.3/bin/mvn clean package -DskipTest'
            }
        }
        stage('任务3：通过sonarqube做代码质量检测') {
            steps {
#                sh '/var/jenkins_home/sonar-scanner/bin/sonar-scanner -Dsonar.sources=./ -Dsonar.projectname=${JOB_NAME} -Dsonar.projectKey=${JOB_NAME} -Dsonar.java.binaries=./target/ -Dsonar.login=7e47cd48d08789e76ba106dd772f9bfaa15d46ff'
            }
        }
        stage('任务4：通过docker制作自定义镜像') {
            steps {
                sh '''mv ./target/*.jar ./
                docker build -t ${JOB_NAME}:${tag} .'''
            }
        }
        stage('任务5：将自定义镜像推送到harbor') {
            steps {
                echo '将自定义镜像推送到harbor-SUCCESS'
            }
        }
        stage('任务6：通过publish over ssh通知目标服务器') {
            steps {
                echo '通过publish over ssh通知目标服务器-SUCCESS'
            }
        }
    }
}
