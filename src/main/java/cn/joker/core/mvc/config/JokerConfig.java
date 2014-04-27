package cn.joker.core.mvc.config;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.joker.core.mvc.utils.XmlParseUtil;

public class JokerConfig {

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	private String configFileName;
	
	private String encode = "UTF-8";
	
	private Map<String, String> urlMapping = new HashMap<String, String>();
	
	public JokerConfig(String configFileName){
		this.configFileName=configFileName;
	}
	
	public void setEncode(String encode){
		this.encode=encode;
	}
	
	public String getEncode(){
		return this.encode;
	}
	
	public void parse(){
		if(configFileName==null || this.configFileName.length()<1){
			if(this.logger.isErrorEnabled()){
				this.logger.error("Parse XML JokerConfig failed ! the config file name is null!");
			}
		}else{
			if(this.logger.isInfoEnabled()){
				this.logger.info("Parse XML JokerConfig ["+this.configFileName+"]");
			}
		}
		long startTime = System.currentTimeMillis();
		
		XmlParseUtil.parse(this, this.configFileName);
		
		if(this.logger.isErrorEnabled()){
			long elapseTime =System.currentTimeMillis()-startTime;
			logger.info("JokerConfig Parse xml completed in"+elapseTime+"ms");
		}
	}
	
	public void addUrlMapping(String url,String classPath){
		if(url!=null && classPath!=null){
			urlMapping.put(url, classPath);
		}
	}
	
	public String getClassPath(String url){
		if(url!=null){
			return urlMapping.get(url);
		}
		return null;
	}
	
	public Iterator<String> iteratorKey(){
		return this.urlMapping.keySet().iterator();
	}
}
