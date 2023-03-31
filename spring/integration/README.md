the example comprehends two applications
- consumer -> spring integration-consumer
  - start consumer, api on port 8080 -> java -jar consumer.jar
  - two api methods GET: "do-get" and "do-post" they call producer api methods
  
- producer -> spring-integration-producer
    - start producer, api on port 8090 -> java -jar producer.jar