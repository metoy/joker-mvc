package cn.joker.core.mvc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;

import cn.joker.core.mvc.config.JokerConfig;
import cn.joker.core.mvc.context.ApplicationContext;
import cn.joker.core.mvc.model.Model;
import cn.joker.core.mvc.servlet.context.HttpRequestContext;


/**
 * 1、解析配置文件，放到jokerconfig中，实例化controller放到applicationContext中
 * 2、封装请求道HttpRequestContext中
 * @author dyz
 *
 */
public abstract class BaseDispatcherServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected final Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());
	
	protected abstract void process(HttpRequestContext httpRequestContext)throws ServletException,IOException;
	
	protected final void doService(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		
		String reqUri=request.getRequestURI();
		
		String contextUri=request.getContextPath();
		
		reqUri=reqUri.substring(contextUri.length());
		
		HttpRequestContext requestContext = new HttpRequestContext();
		
		requestContext.setRequest(request);
		
		requestContext.setResponse(response);
		
		requestContext.setRequestUri(reqUri);
		
		requestContext.setModel(Model.getModelInstance());
		
		process(requestContext);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doService(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doService(req, resp);
	}
	

	protected void initServlet()throws ServletException{
		
	}
	
	@Override
	public final void init() throws ServletException {
		
		getServletContext().log("init basedispatcherServlet--"+this.getServletName());
		
		if(this.logger.isInfoEnabled()){
			this.logger.info("'BaseDispatcherServlet'"+getServletName()+"Init started");
		}
		
		long startTime = System.currentTimeMillis();
		
		String configFileName = getServletConfig().getInitParameter("config");
		
		String encode = getServletConfig().getInitParameter("encode");
		
		JokerConfig jokerConfig = new JokerConfig(configFileName);
		
		if(encode!=null && encode.length()>0){
			jokerConfig.setEncode(encode);
		}
		
		jokerConfig.parse();
		
		ApplicationContext applicationContext =ApplicationContext.getInstance();
		applicationContext.loadJokerConfig(jokerConfig);
		applicationContext.setServletContext(getServletContext());
		
		initServlet();
		
		if(this.logger.isInfoEnabled()){
			long elapseTime = System.currentTimeMillis()-startTime;
			this.logger.info("'BaseDispatcherServlet'--"+getServletName()+"initialization completed in "+elapseTime+"ms");
		}
	}
}
