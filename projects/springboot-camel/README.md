# 1. Spring Boot con Camel

<!-- TOC -->

- [1. Spring Boot con Camel](#1-spring-boot-con-camel)
  - [1.1. Introducción](#11-introducción)
  - [1.2. Ejemplo de ejecución local del proyecto](#12-ejemplo-de-ejecución-local-del-proyecto)
  - [1.3. Ejemplo de ejecución del proyecto en OpenShift](#13-ejemplo-de-ejecución-del-proyecto-en-openshift)
  - [1.4. Endpoints activados](#14-endpoints-activados)
  - [1.5 Autor](#15-autor)

<!-- /TOC -->

## 1.1. Introducción

Este módulo contiene la estructura y los artefactos para el desarrollo de aplicaciones Spring Boot con Apache Camel

## 1.2. Ejemplo de ejecución local del proyecto

Para comenzar, ejecutar:

`mvn clean spring-boot:run`

Luego, realizar un pedido GET a:

`curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://localhost:8080/camel/hello/camel`

Ud. obtendrá un código de respuesta HTTP 200 y la respuesta:

HTTP/1.1 200
accept: application/json
id: camel
user-agent: curl/7.29.0
Content-Type: application/json
Transfer-Encoding: chunked
Date: Thu, 12 Nov 2020 17:41:42 GMT

{"message":"hello World!, camel"}

## 1.3. Ejemplo de ejecución del proyecto en OpenShift

Inicie sesión en OpenShift con su token:

`oc login --token=<token> --server=<api-openshift>`

Acceda al proyecto para alojar la aplicación:

`oc project <nombre_ proyecto>`

Construya la imagen del proyecto sobre OpenShift:

`mvn clean`
`mvn package fabric8:build`

Despliegue los componentes en OpenShift:

`cd .\src\main\resources\yml\`

`oc apply -f demo-deployment.yml -n <nombre_ proyecto>`
`oc apply -f demo-service.yml -n <nombre_ proyecto>`
`oc apply -f demo-route.yml -n <nombre_ proyecto>`

Luego, realizar un pedido GET a:

`curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://<ruta_opensihft>:8080/camel/hello/camel`

Ud. obtendrá un código de respuesta HTTP 200 y la respuesta:

HTTP/1.1 200
accept: application/json
id: camel
user-agent: curl/7.29.0
Content-Type: application/json
Transfer-Encoding: chunked
Date: Thu, 12 Nov 2020 17:41:42 GMT

{"message":"hello World!, camel"}

## 1.4. Endpoints activados

Por defecto los siguientes endpoints están activados:

    http://localhost:8080/actuator/metrics
  
  Muestra información de varias métricas útiles, como la memoria JVM utilizada, el uso de la CPU del sistema, los archivos abiertos y mucho más.

    http://localhost:8080/actuator/health
  Devuelve la información de salud de la aplicación.

    http://localhost:8080/actuator/jolokia/read/org.apache.camel:context=*,type=routes,name=*
  Devuelve la infomación de las rutas que conforman la aplicación Camel

     http://localhost:8080/actuator/env
Devuelve la lista de propiedades en el entorno actual.

## 1.5 Autor

* Nelson O. Di Grazia (<nelson.digrazia@telefonica.com>)