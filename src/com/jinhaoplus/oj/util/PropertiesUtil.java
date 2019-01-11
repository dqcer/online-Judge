package com.jinhaoplus.oj.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
	public static String getProperty(String key) {
		String result = "";  
        InputStream inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream("global.properties");  
        Properties prop = new Properties();  
        try {  
            prop.load(inputStream);  
            result = prop.getProperty(key).trim();
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return result;  
	}

}
