package cn.joker.core.mvc.utils;

import java.io.File;
import java.net.URL;
import java.util.Iterator;

import javax.xml.parsers.SAXParser;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.joker.core.mvc.config.JokerConfig;

public class XmlParseUtil {

	private static Logger logger = LoggerFactory.getLogger(XmlParseUtil.class);
	
	public static void parse(JokerConfig config,String fileName){
	
		if(fileName==null){
			throw new RuntimeException("配置文件路径不能为空");
		}
		URL classPath = XmlParseUtil.class.getClassLoader().getResource("");
		
		String rootPath = classPath.getFile();
		
		rootPath = rootPath.substring(1, rootPath.indexOf("WEB-INF"));
		
		File file = new File(rootPath+fileName);
		
		parse(config, file);
	}
	
	public static void parse(JokerConfig config,File file){
		SAXReader reader = new SAXReader();
		try {
			Document doucment = reader.read(file);
			if(doucment!=null){
				Element element = doucment.getRootElement();
				Iterator<Element> iter = element.elementIterator();
				while(iter.hasNext()){
					parse(config, iter.next());
				}
			}
		} catch (Exception e) {
			logger.error("XmlParseUtil parse xml error!");
			e.printStackTrace();
		}
	}

	private static void parse(JokerConfig config, Element element) {
		
		if(element==null){
			return;
		}
		String url = element.attributeValue("url");
		String classPath = element.attributeValue("class");
		if(url!=null && !url.equals("") && classPath!=null && !classPath.equals("")){
			config.addUrlMapping(url, classPath);
		}
		
	}
}
