# Spring-Boot FUSE QuickStart

This example demonstrates how you can use Apache Fuse with Spring Boot.


## Building

The example can be built with

    mvn clean compile


## Testing

The example can be tested with

    mvn clean test


## Running the example in OpenShift

It is assumed that:
- OpenShift platform is already running.
- You are connected to OpenShift, if not you can find a [Get Started Guide]

The example can be built using a single goal:

    mvn clean package fabric8:build

The example can be deployed on OpenShift using a single goal:

    mvn fabric8:deploy

When the example runs in OpenShift, you can use the OpenShift client tool to inspect the status

To list all the running pods:

    oc get pods

Then find the name of the pod that runs this quickstart, and output the logs from the running pods with:

    oc logs <name of pod>

You can also use the OpenShift to manage the running pods, and view logs and much more.


## Get Started Guide

To run the example, you have to connect to OpenShift. You can use the following commands to Openshift:

To login in to OpenShift:

    oc login --token=<token> --server=<https://api.your-openshift-server.com>

and to select a project:

    oc project <projectname>
    
    
## Running the example as a standalone application 

The example can be run as a standalone application using a single goal:

    mvn spring-boot:run

## API Documentation 

You can use the following url to see Swagger User Interface:

    http://<hostname>:8080/swagger-ui

You can use the following url to get a swagger file in JSON Format:

	http://<hostname>:8080/api/swagger/swagger.json

You can use the following url to get a swagger file in YAML Format:

	http://<hostname>:8080/api/swagger/swagger.yaml
		
## Management and monitoring


### Fuse Management

The following url provides access to web console for managing your Camel's routes:

	http://<hostname>:8081/actuator/hawtio/index.html

### JMX monitoring

The following url provides acccess to a remote JMX Server:

	http://<hostname>:8081/actuator/jolokia/read/org.apache.camel:context=*,type=routes,name=*