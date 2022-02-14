package com.cg.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig {

	Properties properties;

	public ReadConfig() throws IOException {
		properties = new Properties();
		File filepath = new File("config.properties");
		FileInputStream fin = new FileInputStream(filepath);
		properties.load(fin);
	}

	public String getBaseUrl() {
		String baseurl = properties.getProperty("baseurl");
		return baseurl;
	}

	public String getChromePath() {
		String chromepath = properties.getProperty("chromepath");
		return chromepath;
	}

	public String getEdgePath() {
		String edgepath = properties.getProperty("edgepath");
		return edgepath;
	}
}
