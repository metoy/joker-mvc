package cn.joker.core.mvc.utils;

public class StringUtils {

	public static boolean isNull(String s){
		if(s==null || "".equals(s.trim())){
			return true;
		}
		return false;
	}
	
	public static boolean isFullNull(String s){
		if(s==null || "".equals(s.trim()) || "null".equalsIgnoreCase(s.trim())){
			return true;
		}
		return false;
	}
}
