@rem
@rem Copyright 2015 the original author or authors.
@rem
@rem Licensed under the Apache License, Version 2.0 (the "License");
@rem you may not use this file except in compliance with the License.
@rem You may obtain a copy of the License at
@rem
@rem      https://www.apache.org/licenses/LICENSE-2.0
@rem
@rem Unless required by applicable law or agreed to in writing, software
@rem distributed under the License is distributed on an "AS IS" BASIS,
@rem WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@rem See the License for the specific language governing permissions and
@rem limitations under the License.
@rem

@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  vertx-playground startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Resolve any "." and ".." in APP_HOME to make it shorter.
for %%i in ("%APP_HOME%") do set APP_HOME=%%~fi

@rem Add default JVM options here. You can also use JAVA_OPTS and VERTX_PLAYGROUND_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto execute

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto execute

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\vertx-playground-1.0-SNAPSHOT.jar;%APP_HOME%\lib\protobuf-java-util-3.21.4.jar;%APP_HOME%\lib\vertx-web-graphql-4.3.5.jar;%APP_HOME%\lib\vertx-web-4.3.5.jar;%APP_HOME%\lib\vertx-web-client-4.3.5.jar;%APP_HOME%\lib\vertx-lang-kotlin-4.3.5.jar;%APP_HOME%\lib\vertx-lang-kotlin-coroutines-4.3.5.jar;%APP_HOME%\lib\vertx-rx-java3-4.1.8.jar;%APP_HOME%\lib\vertx-web-common-4.3.5.jar;%APP_HOME%\lib\vertx-auth-common-4.3.5.jar;%APP_HOME%\lib\vertx-bridge-common-4.3.5.jar;%APP_HOME%\lib\vertx-uri-template-4.3.5.jar;%APP_HOME%\lib\vertx-grpc-server-4.3.5.jar;%APP_HOME%\lib\vertx-grpc-common-4.3.5.jar;%APP_HOME%\lib\vertx-rx-java3-gen-4.1.8.jar;%APP_HOME%\lib\vertx-rx-gen-4.1.8.jar;%APP_HOME%\lib\vertx-core-4.3.5.jar;%APP_HOME%\lib\vertx-codegen-4.3.5.jar;%APP_HOME%\lib\grpc-kotlin-stub-1.3.0.jar;%APP_HOME%\lib\kotlinx-coroutines-core-jvm-1.6.4.jar;%APP_HOME%\lib\kotlin-stdlib-jdk8-1.7.10.jar;%APP_HOME%\lib\jackson-databind-2.14.0.jar;%APP_HOME%\lib\jackson-core-2.14.0.jar;%APP_HOME%\lib\jackson-annotations-2.14.0.jar;%APP_HOME%\lib\jackson-module-kotlin-2.14.0.jar;%APP_HOME%\lib\grpc-stub-1.50.2.jar;%APP_HOME%\lib\grpc-protobuf-1.50.2.jar;%APP_HOME%\lib\protobuf-kotlin-3.21.4.jar;%APP_HOME%\lib\grpc-netty-1.51.0.jar;%APP_HOME%\lib\proto-google-common-protos-2.9.0.jar;%APP_HOME%\lib\protobuf-java-3.21.7.jar;%APP_HOME%\lib\grpc-core-1.51.0.jar;%APP_HOME%\lib\grpc-protobuf-lite-1.50.2.jar;%APP_HOME%\lib\grpc-api-1.51.0.jar;%APP_HOME%\lib\guava-31.1-android.jar;%APP_HOME%\lib\error_prone_annotations-2.14.0.jar;%APP_HOME%\lib\j2objc-annotations-1.3.jar;%APP_HOME%\lib\jsr305-3.0.2.jar;%APP_HOME%\lib\gson-2.9.0.jar;%APP_HOME%\lib\netty-handler-proxy-4.1.85.Final.jar;%APP_HOME%\lib\netty-codec-http2-4.1.85.Final.jar;%APP_HOME%\lib\netty-codec-http-4.1.85.Final.jar;%APP_HOME%\lib\netty-resolver-dns-4.1.85.Final.jar;%APP_HOME%\lib\netty-handler-4.1.85.Final.jar;%APP_HOME%\lib\netty-transport-native-unix-common-4.1.85.Final.jar;%APP_HOME%\lib\netty-codec-socks-4.1.85.Final.jar;%APP_HOME%\lib\netty-codec-dns-4.1.85.Final.jar;%APP_HOME%\lib\netty-codec-4.1.85.Final.jar;%APP_HOME%\lib\netty-transport-4.1.85.Final.jar;%APP_HOME%\lib\netty-buffer-4.1.85.Final.jar;%APP_HOME%\lib\netty-resolver-4.1.85.Final.jar;%APP_HOME%\lib\netty-common-4.1.85.Final.jar;%APP_HOME%\lib\graphql-java-19.2.jar;%APP_HOME%\lib\rxjava-3.0.13.jar;%APP_HOME%\lib\reactive-streams-1.0.3.jar;%APP_HOME%\lib\kotlin-stdlib-jdk7-1.7.10.jar;%APP_HOME%\lib\kotlin-reflect-1.5.32.jar;%APP_HOME%\lib\kotlin-stdlib-1.7.10.jar;%APP_HOME%\lib\javax.annotation-api-1.3.2.jar;%APP_HOME%\lib\perfmark-api-0.25.0.jar;%APP_HOME%\lib\failureaccess-1.0.1.jar;%APP_HOME%\lib\listenablefuture-9999.0-empty-to-avoid-conflict-with-guava.jar;%APP_HOME%\lib\checker-qual-3.12.0.jar;%APP_HOME%\lib\java-dataloader-3.2.0.jar;%APP_HOME%\lib\slf4j-api-1.7.35.jar;%APP_HOME%\lib\kotlin-stdlib-common-1.7.10.jar;%APP_HOME%\lib\annotations-13.0.jar;%APP_HOME%\lib\annotations-4.1.1.4.jar;%APP_HOME%\lib\animal-sniffer-annotations-1.21.jar;%APP_HOME%\lib\grpc-context-1.51.0.jar


@rem Execute vertx-playground
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %VERTX_PLAYGROUND_OPTS%  -classpath "%CLASSPATH%" org.roksui.MainKt %*

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable VERTX_PLAYGROUND_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%VERTX_PLAYGROUND_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
