# UOB-tokenizer
Tokenizer project for UOB

# DB setup
1. Run db/setup_DB.sql to setup the database tables.

# Build WAR
1. Go to project directory /UOB-tokenizer and execute the script 'build_war.bat' to generate a WAR file.

2. Deploy the WAR file 'uob-tokenizer-1.0.war' generated under /target folder to a server(Tomcat, Weblogic, etc). Restart the server.

3. Refer to uob-tokenizer-swagger.yaml for the REST endpoint's documentation.

# Build JAR for Ctrl-M scheduling
1. Go to project directory /UOB-tokenizer and execute the script 'build_jar.bat' to generate a JAR file.

2. Take the JAR file 'uob-tokenizer-1.0.jar' generated under /target folder to execute in Ctrl-M.

3. Execute the  JAR using command line: java -jar uob-tokenizer-1.0.jar --spring.config.name=application-batch.properties
# alt command: java -jar uob-tokenizer-1.0.jar --spring.config.name=application-batch

# Configurations for JAR file
1. Update source/destination folders: Edit below properties under 'application-batch.properties' file and then re-compile to generate new JAR.

	a. sourceDirectory=D:\DATA\CD\PATSG\RIMSG\DOWNLOAD\XXX
	b. destinationDirectory=D:\DATA\CD\PATSG\RFTSSG\UPLOAD\XXX
	
2. Update DB connection settings: Edit below properties under 'application-batch.properties' file and then re-compile to generate new   JAR.

	a. spring.datasource.url=jdbc:sqlserver://{DBURL_string} 
	b. spring.datasource.username={username}
	c. spring.datasource.password={password}

# Common configuration
1. Update logging configuration: Edit 'log4j.properties' file to modify log file path, log levels, etc and then re-compile to generate new JAR or WAR accordingly

	