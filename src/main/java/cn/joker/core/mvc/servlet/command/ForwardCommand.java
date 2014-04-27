package cn.joker.core.mvc.servlet.command;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import cn.joker.core.mvc.context.ApplicationContext;
import cn.joker.core.mvc.servlet.context.HttpRequestContext;
import cn.joker.core.mvc.view.View;

public class ForwardCommand extends AbstractCommand {

	@Override
	protected boolean exec(HttpRequestContext httpRequestContext) {
		
		View view = httpRequestContext.getView();
		
		ServletContext servletContext = ApplicationContext.getInstance().getServletContext();
		
		try {
			servletContext.getRequestDispatcher(view.getUrl()).forward(httpRequestContext.getRequest(), httpRequestContext.getResponse());
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return true;
	}

}
