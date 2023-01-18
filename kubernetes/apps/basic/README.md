mvn clean package
docker build -t basic-api .  
docker run -p 8080:8080 basic-api

local
spring plugin
localhost:8080/hello
