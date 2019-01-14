package com.lihail.util;

import java.io.File;
import java.io.StringWriter;
import java.util.Map;

import org.apache.log4j.Logger;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class FreeMarkeUtil {
	private static final Logger LOGGER = Logger.getLogger(FreeMarkeUtil.class);
	
	public static String getSql(String path, String sqlName, Map<String, Object> map) throws Exception {
		Configuration config = new Configuration();
		
		config.setDirectoryForTemplateLoading(new File(path));
		
		Template template = config.getTemplate(sqlName);
		
		StringWriter writer = new StringWriter();
		template.process(map, writer);
		
		return writer.toString();
	}

}
