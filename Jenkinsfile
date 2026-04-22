pipeline {
    agent any

    environment {
        TOMCAT_HOME = 'C:\\Program Files\\Apache Software Foundation\\Tomcat 10.1'
        WAR_NAME    = 'employee-api.war'
        APP_NAME    = 'employee-api'
    }

    stages {
        stage('Build WAR') {
            steps {
                bat '''
                call mvn clean package -DskipTests
                '''
            }
        }

        stage('Deploy WAR') {
            steps {
                bat '''
                echo ===== Deploy WAR =====

                if not exist target\\%WAR_NAME% (
                    echo ERROR: target\\%WAR_NAME% not found
                    exit /b 1
                )

                if exist "%TOMCAT_HOME%\\webapps\\%WAR_NAME%" (
                    del /f /q "%TOMCAT_HOME%\\webapps\\%WAR_NAME%"
                )

                if exist "%TOMCAT_HOME%\\webapps\\%APP_NAME%" (
                    rmdir /s /q "%TOMCAT_HOME%\\webapps\\%APP_NAME%"
                )

                copy /y target\\%WAR_NAME% "%TOMCAT_HOME%\\webapps\\"
                '''
            }
        }
    }
}