package cn.joker.core.mvc.servlet.command;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import cn.joker.core.mvc.model.Model;
import cn.joker.core.mvc.servlet.context.HttpRequestContext;


public class ModelDealCommand extends AbstractCommand {

	@Override
	protected boolean exec(HttpRequestContext httpRequestContext) {
		
		HttpServletRequest request = httpRequestContext.getRequest();
		Model model = httpRequestContext.getModel();
		Iterator<String> iter = model.iteratorKey();
		while(iter.hasNext()){
			String key = iter.next();
			request.setAttribute(key, model.get(key));
		}
		
		return true;
	}

	
}
