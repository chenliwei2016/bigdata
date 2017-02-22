/**
 * @author: ChenLiwei
 * 2017-02-22
 * LoggerTest.java
 * Comments: It is to demonstrate the most popular log system slf4j and log4j.
 * we don't want to use the java default log system since log4j is more powerful than it
 * Before we use slf4j, we need add a dependency into pom.xml, and then
 * we create a log4j.properties to configure the behavior of logger at the same folder with pom.xml
 */
package win.chenliwei.javacore.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerTest {
	private static final Logger logger = LoggerFactory.getLogger(LoggerTest.class);
	public static void main(String[] args) {
		System.out.println("Test start");
		logger.info("Now, LoggerTest starts");
		logger.error("I would like to raise a error");
		logger.warn("Hey, I am warnning you");
		logger.info("It is over, finished, these are logged to file too");
		System.out.println("Test over");
	}

}
