powershell -Command "(gc pom.xml) -replace '<packaging>[a-z]{3}</packaging>', '<packaging>jar</packaging>' | Out-File pom.xml"
mvn -e clean install
