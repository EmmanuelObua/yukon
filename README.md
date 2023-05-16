## Yukon Spring boot, Spring data, MYSQL, JPA, Rest-API, CRUD

## Requirements

01) Java 17
02) Maven 3.8 +
03) MYSQL 8.0 

## Project setup

01) Clone the project

		git clone https://github.com/EmmanuelObua/yukon.git

02) Create MYSQL Database

		Run appropiate script or command to create a database depending on the operating system

03) Clean and build the project using maven

		open command line (CMD) in project directory and execute './mvnw spring-boot:run'
    
    If the command fails, try setting the database as in step
		
04) Open project using any editor, my case sublime text

05) Set up database configurations in application.properties file
		
	    spring.datasource.url=jdbc:mysql://localhost:3306/database_name_here
        spring.datasource.username=
        spring.datasource.password=
        spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
        spring.jpa.hibernate.ddl-auto=update
        spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
		
## Expose Rest APIs

01) Base URL http://localhost:8080
02) 
