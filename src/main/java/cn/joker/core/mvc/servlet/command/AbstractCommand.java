package cn.joker.core.mvc.servlet.command;

import cn.joker.core.mvc.servlet.context.HttpRequestContext;

public abstract class AbstractCommand implements ICommand{

	protected ICommand nextCommand;
	
	public ICommand setNextCommand(ICommand nextCommand) {
		this.nextCommand=nextCommand;
		return this.nextCommand;
	}
	
	protected abstract boolean exec(HttpRequestContext httpRequestContext);
	
	public final void process(HttpRequestContext httpRequestContext){
		boolean b = exec(httpRequestContext);
		if(b && this.nextCommand!=null){
			nextCommand.process(httpRequestContext);
		}
	}
}
