package com.atlassian.test.utils;

import java.io.FileInputStream;
import java.util.Enumeration;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Env {

	private static final String SRC_RESOURCE = "src/com/atlassian/test/conf/";
	static Logger log = (Logger) LoggerFactory.getLogger(Thread.currentThread().getStackTrace()[1].getClassName());
	public final static String LOCAL = "localhost";

	private static Env conf = null;
	Properties props = null;

	public static Env instance() {
		if (conf == null) {
			conf = new Env();
			try {
				String testProp = "atlassianTest.properties";
				testProp = SRC_RESOURCE + testProp;
				log.info("-DtestProperties=" + testProp);
				String testSuite = "testng";
				log.info("-DtestSuite=" + testSuite);
				conf.props = loadProps(testProp);
				bouncerFrontEnd = getEnv("FRONTEND_URL");
				selHost = getEnv("SELENIUM_HUB_HOST");
				user1 = getEnv("USER1");
				user1key = getEnv("USERKEY1");
				username1 = getEnv("USERNAME1");
				user2 = getEnv("USER2");
				user2key = getEnv("USERKEY2");
				username2 = getEnv("USERNAME2");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return conf;
	}

	public static String bouncerFrontEnd = null;
	public static String selHost = null;
	public static String user1 = null;
	public static String user1key = null;
	public static String username1 = null;
	public static String user2 = null;
	public static String user2key = null;
	public static String username2 = null;
	public static int waitMultiplier = 1;

	public static String getEnv(String key) {
		String val = conf.props.getProperty(key);
		if (val == null || val.length() == 0) {
			return val;
		}
		val = val.trim();
		return val;
	}

	public static String getEnv(String key, String defValue) {
		String val = conf.props.getProperty(key, defValue);
		val.trim();
		if (val == null || val.length() == 0) {
			val = defValue;
		}
		return val;
	}

	public static void printProps(Properties props) {
		Enumeration<Object> keys = props.keys();
		while (keys.hasMoreElements()) {
			String key = (String) keys.nextElement();
			String value = (String) props.get(key);
			log.info(key + "==" + value);
		}
	}

	public static Properties loadProps(String file) throws Exception {
		log.info("loadProps = " + file);
		Properties p = new Properties();
		if (file != null && !file.equals("")) {
			p.load(new FileInputStream(file));
		}
		return p;
	}

	Properties getProps() {
		return conf.props;
	}
}

