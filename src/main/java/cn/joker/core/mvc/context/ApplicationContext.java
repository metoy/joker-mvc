package cn.joker.core.mvc.context;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.joker.core.mvc.config.JokerConfig;
import cn.joker.core.mvc.controller.IController;
import cn.joker.core.mvc.utils.StringUtils;

public class ApplicationContext {

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	private ApplicationContext(){}
	
	private static ApplicationContext context = new ApplicationContext();
	
	private Map<String , IController> cache = new HashMap<String, IController>();
	
	private JokerConfig jokerConfig;
	
	private ServletContext servletContext;
	
	public static ApplicationContext getInstance(){
		return context;
	}
	
	public JokerConfig getJoketConfig(){
		return jokerConfig;
	}
	
	public void loadJokerConfig(JokerConfig jokerConfig){
		if(jokerConfig!=null){
			if(logger.isInfoEnabled()){
				logger.info("Loading JokeConfig Controller started");
			}
			long startTime = System.currentTimeMillis();
			
			boolean isSuc = false;
			
			Iterator<String> iterator = jokerConfig.iteratorKey();
			
			String url = null;
			
			String classPath = null;
			
			while(iterator.hasNext()){
				isSuc=false;
				url = iterator.next();
				classPath = jokerConfig.getClassPath(url);
				Class clazz = null;
				IController controller = null;
				try {
					clazz = Class.forName(classPath);
					controller = (IController) clazz.newInstance();
					isSuc=true;
				} catch (Exception e) {
					this.logger.error("controller class instance errror,class name is "+classPath);
					e.printStackTrace();
				}
				if(isSuc){
					if(this.logger.isInfoEnabled()){
						this.logger.info(classPath+" is loaded success");
					}
					this.cache.put(classPath, controller);
				}
			}
			this.jokerConfig=jokerConfig;
			
		}
	}

	public ServletContext getServletContext() {
		return servletContext;
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	
	public IController getControllerInstance(String url){
		String classPath = this.jokerConfig.getClassPath(url);
		IController controller = null;
		if(!StringUtils.isNull(classPath)){
			if(cache.containsKey(classPath)){
				controller = cache.get(classPath);
			}else{
				try {
					Class clazz = Class.forName(classPath);
					controller = (IController) clazz.newInstance();
					this.cache.put(classPath, controller);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return controller;
	}
	
}
