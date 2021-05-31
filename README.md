# spring-boot-jwt-postgres

 0 Prerequisite 
----------------------------
To use this project, you are going to need;

- Java JDK 8 (1.8)
- Maven compatibile with JDK 8
- Any Java IDE
- PostgresSQL
- [Postman tool](https://www.getpostman.com/) (optional, will be used for testing web service)

We are going to build an app using spring boot , JWT, Maven, Postgres and postman to test the webservices.
we are going to use an embedded tomcat,using Log4j for logging. 

1 Create Spring Boot Project With Maven
----------------------------------------
What we need to setup a Spring Boot project. However there are other ways (like spring initializer),
I'll go with setting up our project with maven.

Because that we are creating a web application here, we will first create a maven project with web
application archetype, then we will add the spring boot dependencies;

We can use the https://start.spring.io/ to create a spring boot project with dependicies.


 3 Spring Boot Dependencies
---------------------------
How we make our project a Spring Boot project. We simply define a parent project in our POM file
just as below;

```
<!-- Spring Parent Project -->
<parent>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-parent</artifactId>
	<version>2.2.7.RELEASE</version>
</parent>
```

Then our dependencies aware of our Spring Boot project. Then for example, if we are going to create
a web application which is true, then we add the Spring Boot Starter dependencies. On this project,
it is vital for us to add the dependency below;

```
<!-- Spring boot starter web: integrates and auto-configures  -->
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

See that we did not use the version attribute of the dependency block. That's what Spring Boot is 
going to handle but just adding this starter dependency, we will have all what we need like Jersey,
Jackson, and the rest. Also the versioning is managed by Spring Boot, we don't have to check if
the versions of our transitive dependencies are compatible with each other or not.

Moreover, in this project we will need an Postgres database. So we are going to add the following
dependency to our dependencies block;

```
<!-- postgres Database -->
<dependency>
	<groupId>org.postgresql</groupId>
	<artifactId>postgresql</artifactId>
	<scope>runtime</scope>
</dependency>
```

We can find all the additional  dependencies in the POM file.

With this, Spring Boot configuration is complete.


4. Code checkout 

 git clone --branch sprint git@github.com:muralid9/spring-boot-jwt-postgres.git


 
5 Application Properties
-------------------------

Spring Boot solves our problem with automatic configuration as we use an embedded Tomcat and postgres
database but how are we going to specify the running port of the Tomcat container, the target database, 
connection pool parameters and so on?

Spring Boot provides a default configuration properties file called as [application.properties]
- jwt.secret=jwttest
- spring.datasource.url=jdbc:postgresql://localhost:5432/postgres 
- spring.datasource.username=postgres 
- spring.datasource.password=postgres 
- spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect 
- spring.jpa.show-sql=true 

- Hibernate ddl auto (create, create-drop, validate, update)
- spring.jpa.hibernate.ddl-auto = update 



6 Postgres Database Preparation
--------------------------
Before using our database implementation instead of using our stub implementation, we need to prepare our table
and initial entries within the database. We have two things to do;

1. We need to create a CONSULTANT table to store our consultant model, defined in [scripts.sql] 

Now our database is ready to go!



7 Sending And Receiving JSONs With Postman
-------------------------------------------
Sample JSON


8 Building And Running The Standalone Application
---------------------------------------------------
Now we can demonstrate how to run our api as a standalone application. First we must build with the following
maven command;

```
mvn package
```

This command is going to collect all the needed jars and pack them into an spring-boot-jwt Jar (a.k.a. fat jar). We can find this
Jar under the "target" folder. 

We are going to take this file and copy it to another arbitrary folder. Remember that we also need two configuration files,
those that located under the config file. We will also copy those config files to our arbitrary folder.

```
D:\api>java -jar spring-boot-jwt-1.0-SNAPSHOT.jar
