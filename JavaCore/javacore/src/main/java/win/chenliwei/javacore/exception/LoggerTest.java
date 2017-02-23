/**
 * @author: ChenLiwei
 * 2017-02-22
 * LoggerTest.java
 * Comments: It is to demonstrate the most popular log system slf4j and log4j.
 * we don't want to use the java default log system since log4j is more powerful than it
 * Before we use slf4j, we need add a dependency into pom.xml, and then
 * we create a log4j.properties to configure the behavior of logger at the same folder with pom.xml
 * remember create a folder named resources, put the log4j.properties into it
 * and then add this folder into the project library, then classpath will include it
 */
package win.chenliwei.javacore.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerTest {
	private static final Logger logger = LoggerFactory.getLogger(LoggerTest.class);
	public static void main(String[] args) {
		logger.error("Level 1: I would like to raise a error");
		logger.warn(" Level 2: Hey, I am warnning you");
		logger.info(" Level 3: Now, LoggerTest starts");
		logger.debug("Level 4: It is only used for debug");
		logger.trace("Level 5: It is trace level");
		logger.info("It is over, finished, these are logged to file too");
		/*
		 * You could change the level and appender by configuring the log4j.properties file.
		 */
	}

}
