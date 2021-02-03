 mvn clean spring-boot:run
 
 sudo docker login --username=ndigrazia
 
 ver:

 $HOME/.docker/config.json
 
 config.json:
 
 {
        "auths": {
                "https://index.docker.io/v1/": {
                        "auth": "bmRpZ3JhemlhOk5pTmEqOTg1MA=="
                },
                "https://hub.docker.com/v1/": {
                        "auth": "bmRpZ3JhemlhOk5pTmEqOTg1MA=="
                }
        },
        "HttpHeaders": {
                "User-Agent": "Docker-Client/19.03.13 (linux)"
        }
}
 
Asegurarme que los valores de auth estan en base64.
 
  
 Fabric8 plugin
 
 
 pom.xml:
 
<plugin>
	<groupId>io.fabric8</groupId>
	<artifactId>docker-maven-plugin</artifactId>
	<version>${docker.maven.plugin.version}</version>
	<configuration>
	  <images>
		<image>
		  <name>ndigrazia/spring-docker</name>
		  <build>
			<from>openjdk:latest</from>
			<assembly>
			  <descriptorRef>artifact</descriptorRef>
			</assembly>
			<!-- command to run the uber jar -->
			<cmd>java -jar /maven/${project.artifactId}-${project.version}.jar</cmd>
		  </build>
		</image>
	  </images>
	</configuration>
</plugin>

/root/.m2/settings.xml

Asegurarme que el archivo de Maven settings.xml tiene el servidor de registry Docker:

settings.xml:

<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 https://maven.apache.org/xsd/settings-1.0.0.xsd">
        <servers>
                <server>
                        <username>ndigrazia</username>
                        <password>NiNa*9850</password>
                        <id>docker.io</id>
                        <configuration>
                                <email>nelson.digrazia@gmail.com</email>
                        </configuration>
                </server>
        </servers>
</settings>

Compilar y generar la imagen: sudo mvn install docker:build docker:push
 
