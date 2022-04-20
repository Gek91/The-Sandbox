package trying;


import lombok.extern.log4j.Log4j;

@lombok.extern.java.Log
public class Log {

	public static void main(String... args) {
		log.warning("Something else is wrong here");
	}
}

@lombok.extern.slf4j.Slf4j
class LogSlf4j {

	public static void main(String... args) {
		log.warn("Something else is wrong here");
	}
}

@Log4j
class LogLog4J {

	public static void main(String... args) {
		log.warn("Something else is wrong here");
	}
}
