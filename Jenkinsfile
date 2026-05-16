pipeline {
    agent any

    environment {
        TOMCAT_HOME = 'C:\\Program Files\\Apache Software Foundation\\Tomcat 10.1'
        APACHE_HOME = 'C:\\Apache24'

        BACKEND_WAR = 'employee-api.war'
        BACKEND_APP = 'employee-api'

        REACT_HOME = 'C:\\work\\employee-react-ui'
        REACT_DIST = 'C:\\work\\employee-react-ui\\dist'
        APACHE_UI_DIR = 'C:\\Apache24\\htdocs\\employee-ui'
    }

    stages {
        stage('Checkout Backend') {
            steps {
                checkout scm
            }
        }

        stage('Build React UI') {
            steps {
                bat """
                cd /d "%REACT_HOME%"
                call npm install
                call npm run build
                """
            }
        }

        stage('Deploy React UI to Apache') {
            steps {
                bat """
                if exist "%APACHE_UI_DIR%" rmdir /S /Q "%APACHE_UI_DIR%"
                mkdir "%APACHE_UI_DIR%"
                xcopy /E /I /Y "%REACT_DIST%\\*" "%APACHE_UI_DIR%\\"
                """
            }
        }

        stage('Build Spring Boot WAR') {
            steps {
                bat """
                call mvn clean package -DskipTests
                """
            }
        }

        stage('Stop Tomcat') {
            steps {
                bat """
                net stop Tomcat10
                exit /b 0
                """
            }
        }

        stage('Deploy WAR to Tomcat') {
            steps {
                bat """
                if exist "%TOMCAT_HOME%\\webapps\\%BACKEND_WAR%" del /Q "%TOMCAT_HOME%\\webapps\\%BACKEND_WAR%"
                if exist "%TOMCAT_HOME%\\webapps\\%BACKEND_APP%" rmdir /S /Q "%TOMCAT_HOME%\\webapps\\%BACKEND_APP%"

                copy /Y "target\\%BACKEND_WAR%" "%TOMCAT_HOME%\\webapps\\%BACKEND_WAR%"
                """
            }
        }

        stage('Start Tomcat') {
            steps {
                bat """
                net start Tomcat10
                """
            }
        }

        stage('Restart Apache') {
            steps {
                bat """
                "%APACHE_HOME%\\bin\\httpd.exe" -k restart -n "Apache24"
                """
            }
        }
    }
}