package com.netbanking.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
	
	Properties pro;
	
	public ReadConfig()
	{
		File src = new File("./Configuration/config.properties");
		
		try {
			FileInputStream fis = new FileInputStream(src);
			pro = new Properties();
			pro.load(fis);
		}catch(Exception e){
			System.out.println("Exception is "+ e.getLocalizedMessage());
		}
	}
	public String getApplicationURL(){
		String url = pro.getProperty("baseURL");
		return url;
	}
	public String getuserName(){
		String username = pro.getProperty("username");
		return username;
	}
	public String getPassword(){
		String password = pro.getProperty("password");
		return password;
	}
	public String getchromeDriverPath() {
		String schromedriverpath = pro.getProperty("chromepath");
		return schromedriverpath;
	}
}
