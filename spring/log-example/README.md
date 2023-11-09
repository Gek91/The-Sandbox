Default configuration uses Logback, zero modification needed. Default level is INFO.

Possible change level configuration passing argiment (--debug, --trace)

alternative can set logging level using VM Option<br>
-Dlogging.level.org.springframework=TRACE

Or passing the settings through the command line
mvn spring-boot:run -Dspring-boot.run.arguments=--logging.level.org.springframework=TRACE

We can also change the verbosity in application.properties (logging.level.<logger-name>=<level>)
logging.level.root=warn
logging.level.org.springframework.web=debug
logging.level.org.hibernate=error


logback-spring.xml or logback.xml define the logback configuration. Without these file the default configuration is used.