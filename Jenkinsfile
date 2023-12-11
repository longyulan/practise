// 所有脚本命令包含在pipeline{}中
pipeline {
    // 指定任务在哪个节点执行（Jenkins支持分布式）
    agent any

    // 声明全局环境变量方便后面使用，key = 'value'形式，指定变量名=变量值信息
    environment{
        host = '192.168.2.211'
    }

    // 存放所有任务的合集
    stages {
        // 单个任务
        stage('任务1：拉取git仓库代码') {
            // 实现任务的具体流程
            steps {
                checkout scmGit(branches: [[name: '${tag}']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/longyulan/practise.git']])
            }
        }
        // 单个任务
        stage('任务2：通过maven构建项目') {
            // 实现任务的具体流程
            steps {
                echo '通过maven构建项目-SUCCESS'
            }
        }
        stage('任务3：通过sonarqube做代码质量检测') {
            steps {
                echo '通过sonarqube做代码质量检测-SUCCESS'
            }
        }
        stage('任务4：通过docker制作自定义镜像') {
            steps {
                echo '通过docker制作自定义镜像-SUCCESS'
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
