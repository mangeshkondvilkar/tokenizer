sed -i -E 's/<packaging>(.+)<\/packaging>/<packaging>jar<\/packaging>/g' pom.xml
mvn -e clean install
