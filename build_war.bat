powershell -Command "(gc pom.xml) -replace '<packaging>[a-z]{3}</packaging>', '<packaging>war</packaging>' | Out-File pom.xml"
mvn -e clean install
