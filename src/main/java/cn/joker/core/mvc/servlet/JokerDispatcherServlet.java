package cn.joker.core.mvc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;

import cn.joker.core.mvc.config.JokerConfig;
import cn.joker.core.mvc.context.ApplicationContext;
import cn.joker.core.mvc.servlet.command.DispatcherCommand;
import cn.joker.core.mvc.servlet.command.ForwardCommand;
import cn.joker.core.mvc.servlet.command.ICommand;
import cn.joker.core.mvc.servlet.command.ModelDealCommand;
import cn.joker.core.mvc.servlet.context.HttpRequestContext;

/**
 * 1、初始化责任链
 * 2、设置请求编码，交给责任链处理
 * @author dyz
 *
 */
public class JokerDispatcherServlet extends BaseDispatcherServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ICommand command;
	
	@Override
	protected void process(HttpRequestContext httpRequestContext)
			throws ServletException, IOException {
		ApplicationContext applicationContext = ApplicationContext.getInstance();
		JokerConfig jokerConfig = applicationContext.getJoketConfig();
		httpRequestContext.getRequest().setCharacterEncoding(jokerConfig.getEncode());
		httpRequestContext.getResponse().setCharacterEncoding(jokerConfig.getEncode());
		if(command!=null){
			try {
				command.process(httpRequestContext);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	protected void initServlet() throws ServletException {
		command = new DispatcherCommand();
		command.setNextCommand(new ModelDealCommand()).setNextCommand(new ForwardCommand());
	}

}
