package cn.joker.core.mvc.servlet.command;

import cn.joker.core.mvc.context.ApplicationContext;
import cn.joker.core.mvc.controller.IController;
import cn.joker.core.mvc.servlet.context.HttpRequestContext;
import cn.joker.core.mvc.view.View;

public class DispatcherCommand extends AbstractCommand {

	@Override
	protected boolean exec(HttpRequestContext httpRequestContext) {
		
		ApplicationContext applicationContext = ApplicationContext.getInstance();
		
		IController controller = applicationContext.getControllerInstance(httpRequestContext.getRequestUri());
		
		View view = null;
		
		if(controller !=null){
			view = controller.execute(httpRequestContext.getRequest(), httpRequestContext.getResponse(), httpRequestContext.getModel());
			httpRequestContext.setView(view);
		}
		return view == null?false:true;
	}

	

}
