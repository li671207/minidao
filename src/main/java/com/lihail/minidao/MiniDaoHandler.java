package com.lihail.minidao;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.lihail.annotation.DynamicSql;
import com.lihail.annotation.MyParam;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class MiniDaoHandler implements InvocationHandler{
	
	private static final Log log = LogFactory.getLog(MiniDaoHandler.class);  
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		String sql = "";
		Class<?> returnType = method.getReturnType();
		
		if (method.isAnnotationPresent(DynamicSql.class)) {
			sql = method.getAnnotation(DynamicSql.class).value();
			if (!ObjectUtils.isEmpty(sql)) {
				String key = "";
				String regEx = ":[ tnx0Bfr]*[0-9a-z.A-Z_]+"; // 表示以：开头，[0-9或者.或者A-Z大小都写]的任意字符，超过一个
				Pattern pat = Pattern.compile(regEx);
				Matcher m = pat.matcher(sql);
				Map<String,Object> paramMap = new HashMap<String, Object>();
				while (m.find()) {
					log.info(" Match [" + m.group() + "] at positions " + m.start() + "-" + (m.end() - 1));
					key = m.group().replace(":", "").trim();
					log.info(" --- minidao --- 解析参数 --- " + key);
					Annotation[][] parameterAnnotations = method.getParameterAnnotations();
					for (int i = 0; i < parameterAnnotations.length; i++) {
						Annotation[] annotations = parameterAnnotations[i];
						for (Annotation annotation : annotations) {
							if (annotation.annotationType().equals(MyParam.class)) {
								MyParam myParam = (MyParam)annotation;
								String value = myParam.value();
								if (value.equals(key)) {
									paramMap.put(key, args[i]);
								}
							}
						}
					}
				}
				
				System.out.println(method.getGenericReturnType().getTypeName());
				
				BeanPropertyRowMapper<?> rowMapper = BeanPropertyRowMapper.newInstance(returnType);
				
				if (returnType.isPrimitive()) {
					if (!CollectionUtils.isEmpty(paramMap)) {
						return namedParameterJdbcTemplate.query(sql, paramMap, rowMapper);
					} else {
						return jdbcTemplate.queryForObject(sql, rowMapper);
					}
				} else if (returnType.isAssignableFrom(List.class)) {
					String typeName = method.getGenericReturnType().getTypeName();
					String className = typeName.substring(typeName.indexOf("<")+1, typeName.indexOf(">"));
					
					if (className.indexOf("Map") > 0) {
						return jdbcTemplate.queryForList(sql, paramMap);
					}
					
					BeanPropertyRowMapper<?> beanPropertyRowMapper = BeanPropertyRowMapper.newInstance(Class.forName(className));
					if (!CollectionUtils.isEmpty(paramMap)) {
						return namedParameterJdbcTemplate.query(sql, paramMap, beanPropertyRowMapper);
					}else {
						return jdbcTemplate.queryForList(sql, beanPropertyRowMapper);
					}
				} else {
					if (!CollectionUtils.isEmpty(paramMap)) {
						return namedParameterJdbcTemplate.queryForObject(sql, paramMap, rowMapper);
					}else {
						return jdbcTemplate.queryForObject(sql, rowMapper);
					}
				}
			} else {
				sql = getSql(method);
			}
		} else {
			sql = getSql(method);
		}
		
		return jdbcTemplate.queryForObject(sql, returnType);
	}

	/**
	 * @param method
	 * @return
	 * @throws IOException
	 * @throws TemplateException
	 */
	private String getSql(Method method) throws IOException, TemplateException {
		Class<?> clazz = method.getDeclaringClass();
		String sqlName = clazz.getSimpleName()+"_"+method.getName()+".sql";
		Configuration config = new Configuration();
		
		config.setDirectoryForTemplateLoading(new File(clazz.getResource("").getFile()+File.separator+"sql"));
		
		Template template = config.getTemplate(sqlName);
		
		StringWriter writer = new StringWriter();
		template.process(null, writer);
		return writer.toString();
	}
}
