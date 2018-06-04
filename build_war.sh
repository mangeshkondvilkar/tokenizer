sed -i -E 's/<packaging>(.+)<\/packaging>/<packaging>war<\/packaging>/g' pom.xml
mvn -e clean install
