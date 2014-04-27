package cn.joker.core.mvc.servlet.command;

import cn.joker.core.mvc.servlet.context.HttpRequestContext;

public interface ICommand {

	public ICommand setNextCommand(ICommand nextCommand);
	
	public void process(HttpRequestContext httpRequestContext);

}
