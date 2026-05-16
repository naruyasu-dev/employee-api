# employee-api

Spring Bootで作成した社員管理API

## 機能
- 社員一覧取得
- 社員追加
- 削除

GitHubトークン更新確認

# Employee Management App

## 概要

このプロジェクトは、社員管理システムです。

構成は以下です。

```text
Browser
  ↓
Apache HTTP Server :80
  ├─ /employee-ui/   → React UI
  └─ /employee-api/  → Tomcat :8080 へリバースプロキシ
                         ↓
                    Spring Boot API
                         ↓
                       MySQL
```

---

## 使用技術

### Backend

- Java 17
- Spring Boot 3.3.5
- MyBatis
- MySQL
- Maven
- WAR
- Apache Tomcat 10.1

### Frontend

- React
- Vite
- TypeScript

### Server

- Apache HTTP Server
- Apache Tomcat 10.1

### CI/CD

- GitHub
- Jenkins

---

## URL

### React UI

```text
http://localhost/employee-ui/
```

### 社員一覧画面

```text
http://localhost/employee-ui/employees
```

### Apache 経由 API

```text
http://localhost/employee-api/employees
```

### Tomcat 直接 API

```text
http://localhost:8080/employee-api/employees
```

---

## ディレクトリ構成

### Backend

```text
C:\work\employee-api
```

### Frontend

```text
C:\work\employee-react-ui
```

### Apache

```text
C:\Apache24
```

### Apache UI 配置先

```text
C:\Apache24\htdocs\employee-ui
```

### Tomcat

```text
C:\Program Files\Apache Software Foundation\Tomcat 10.1
```

### Tomcat WAR 配置先

```text
C:\Program Files\Apache Software Foundation\Tomcat 10.1\webapps\employee-api.war
```

---

## Apache 設定

設定ファイル：

```text
C:\Apache24\conf\httpd.conf
```

### 有効化するモジュール

以下のコメントアウトを外す。

```apache
LoadModule rewrite_module modules/mod_rewrite.so
LoadModule proxy_module modules/mod_proxy.so
LoadModule proxy_http_module modules/mod_proxy_http.so
```

### ServerName

```apache
ServerName localhost:80
```

### Employee App 設定

`httpd.conf` の末尾に以下を追加する。

```apache
# =========================================================
# Employee App
# Apache + Tomcat Configuration
# =========================================================

ProxyRequests Off
ProxyPreserveHost On

Alias /employee-ui/ "C:/Apache24/htdocs/employee-ui/"

<Directory "C:/Apache24/htdocs/employee-ui/">
    Options Indexes FollowSymLinks
    AllowOverride None
    Require all granted

    RewriteEngine On
    RewriteBase /employee-ui/

    RewriteCond %{REQUEST_FILENAME} -f [OR]
    RewriteCond %{REQUEST_FILENAME} -d
    RewriteRule ^ - [L]

    RewriteRule . /employee-ui/index.html [L]
</Directory>

ProxyPass        /employee-api/ http://localhost:8080/employee-api/
ProxyPassReverse /employee-api/ http://localhost:8080/employee-api/
```

---

## Apache 設定確認

```bat
cd C:\Apache24\bin
httpd.exe -t
```

正常な場合：

```text
Syntax OK
```

Apache 再起動：

```bat
cd C:\Apache24\bin
httpd.exe -k restart -n "Apache24"
```

---

## React 側設定

### vite.config.ts

```ts
import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

export default defineConfig({
  plugins: [react()],
  base: '/employee-ui/'
})
```

### API URL

React 側では、API URL に `http://localhost:8080` を書かない。

社員 API：

```ts
const API_URL = '/employee-api/employees'
```

部署 API：

```ts
const API_URL = '/employee-api/departments'
```

理由：

```text
React
  ↓
/employee-api/employees
  ↓
Apache
  ↓
Tomcat :8080
  ↓
Spring Boot API
```

---

## React 手動ビルド

```bat
cd C:\work\employee-react-ui
npm install
npm run build
```

ビルド成果物：

```text
C:\work\employee-react-ui\dist
```

Apache へ配置：

```bat
rmdir /S /Q C:\Apache24\htdocs\employee-ui
mkdir C:\Apache24\htdocs\employee-ui
xcopy /E /I /Y C:\work\employee-react-ui\dist\* C:\Apache24\htdocs\employee-ui\
```

---

## Spring Boot 手動ビルド

```bat
cd C:\work\employee-api
mvn clean package -DskipTests
```

WAR 出力先：

```text
C:\work\employee-api\target\employee-api.war
```

Tomcat へ配置：

```bat
copy /Y C:\work\employee-api\target\employee-api.war "C:\Program Files\Apache Software Foundation\Tomcat 10.1\webapps\employee-api.war"
```

---

## Jenkinsfile

場所：

```text
C:\work\employee-api\Jenkinsfile
```

内容：

```groovy
pipeline {
    agent any

    environment {
        TOMCAT_HOME = 'C:\\Program Files\\Apache Software Foundation\\Tomcat 10.1'

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

        stage('Deploy WAR to Tomcat') {
            steps {
                bat """
                if exist "%TOMCAT_HOME%\\webapps\\%BACKEND_WAR%" del /Q "%TOMCAT_HOME%\\webapps\\%BACKEND_WAR%"
                if exist "%TOMCAT_HOME%\\webapps\\%BACKEND_APP%" rmdir /S /Q "%TOMCAT_HOME%\\webapps\\%BACKEND_APP%"

                copy /Y "target\\%BACKEND_WAR%" "%TOMCAT_HOME%\\webapps\\%BACKEND_WAR%"
                """
            }
        }
    }

    post {
        success {
            echo 'Deploy completed successfully.'
        }
        failure {
            echo 'Deploy failed. Check Console Output.'
        }
    }
}
```

---

## Jenkinsfile の処理内容

```text
1. GitHub から employee-api を取得
2. React をビルド
3. React の dist を Apache に配置
4. Spring Boot を WAR ビルド
5. employee-api.war を Tomcat に配置
```

---

## pom.xml の方針

`pom.xml` は Spring Boot の WAR を作るだけにする。

React / Angular のビルド処理は `pom.xml` に入れない。

不要なもの：

```xml
<id>angular npm install</id>
<id>angular build</id>
<id>copy angular build to tomcat</id>
```

不要なプラグイン例：

```xml
<plugin>
    <groupId>org.codehaus.mojo</groupId>
    <artifactId>exec-maven-plugin</artifactId>
</plugin>
```

---

## pom.xml 最終版

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="
             http://maven.apache.org/POM/4.0.0
             https://maven.apache.org/xsd/maven-4.0.0.xsd">

    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.3.5</version>
        <relativePath/>
    </parent>

    <groupId>com.example</groupId>
    <artifactId>employee-api</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>employee-api</name>
    <description>Employee Management API</description>

    <packaging>war</packaging>

    <properties>
        <java.version>17</java.version>
    </properties>

    <dependencies>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-tomcat</artifactId>
            <scope>provided</scope>
        </dependency>

        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>3.0.3</version>
        </dependency>

        <dependency>
            <groupId>com.mysql</groupId>
            <artifactId>mysql-connector-j</artifactId>
            <scope>runtime</scope>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>

        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter-test</artifactId>
            <version>3.0.3</version>
            <scope>test</scope>
        </dependency>

    </dependencies>

    <build>
        <finalName>employee-api</finalName>

        <plugins>

            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-war-plugin</artifactId>
            </plugin>

            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <excludes>
                        <exclude>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>

        </plugins>
    </build>

</project>
```

---

## Jenkins 実行

Jenkins URL：

```text
http://localhost:8081/
```

対象ジョブ：

```text
employee-api
```

実行：

```text
Build Now
```

成功時：

```text
Finished: SUCCESS
```

---

## 動作確認

### Apache トップ

```text
http://localhost/
```

### React UI

```text
http://localhost/employee-ui/
```

### 社員一覧

```text
http://localhost/employee-ui/employees
```

### Apache 経由 API

```text
http://localhost/employee-api/employees
```

### Tomcat 直接 API

```text
http://localhost:8080/employee-api/employees
```

---

## 注意点

Jenkins から Tomcat サービスを停止・起動するには管理者権限が必要。

権限がない場合、以下は Jenkinsfile に入れない。

```bat
net stop Tomcat10
net start Tomcat10
```

Jenkins で以下のエラーが出る場合は、Windows サービス操作権限が不足している。

```text
システム エラー 5
アクセスが拒否されました
```

---

## 完成構成

```text
GitHub
  ↓
Jenkins
  ├─ React build
  ├─ React deploy to Apache
  ├─ Spring Boot WAR build
  └─ WAR deploy to Tomcat

Apache
  ├─ /employee-ui/
  └─ /employee-api/ → Tomcat

Tomcat
  └─ employee-api.war

MySQL
  └─ employee_db
```