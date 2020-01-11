package com.dobest.appload.dao.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileManager {

	private static final Logger LOG = LoggerFactory.getLogger(FileManager.class);
	
	//将文件中的key-value放入map
	public static Map<String, String> getContent(File file) {
		
		try (FileInputStream fileInputStream = new FileInputStream(file);
				InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8")) {
			Properties properties = new Properties();
			properties.load(inputStreamReader);
			Enumeration<?> enumeration = properties.propertyNames();
			HashMap<String, String> map = new HashMap<>();
			while (enumeration.hasMoreElements()) {
				String key = (String)enumeration.nextElement();
				map.put(key, properties.getProperty(key));
			}
			return map;
		} catch (FileNotFoundException e) {
			LOG.error("", e);
			e.printStackTrace();
		} catch (IOException e) {
			LOG.error("", e);
			e.printStackTrace();
		}
		return Collections.emptyMap();
	}
}
