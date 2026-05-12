pipeline {
    agent any

    environment {
        // Tomcat の場所
        TOMCAT_HOME = 'C:\\Program Files\\Apache Software Foundation\\Tomcat 10.1'

        // Spring Boot API
        WAR_NAME = 'employee-api.war'
        APP_NAME = 'employee-api'

        // React UI
        REACT_HOME = 'C:\\work\\employee-react-ui'
        UI_APP_NAME = 'employee-ui'
    }

    stages {

        stage('Build React') {
            steps {
                bat '''
                echo ===== Build React =====

                cd /d "%REACT_HOME%"

                call npm install
                call npm run build
                '''
            }
        }

        stage('Build WAR') {
            steps {
                bat '''
                echo ===== Build WAR =====

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

        stage('Deploy React') {
            steps {
                bat '''
                echo ===== Deploy React =====

                set "SRC=%REACT_HOME%\\dist"
                set "DST=%TOMCAT_HOME%\\webapps\\%UI_APP_NAME%"

                if not exist "%SRC%" (
                    echo ERROR: %SRC% not found
                    exit /b 1
                )

                if exist "%DST%" (
                    rmdir /s /q "%DST%"
                )

                mkdir "%DST%"

                xcopy "%SRC%\\*" "%DST%\\" /E /I /Y
                '''
            }
        }
    }
}