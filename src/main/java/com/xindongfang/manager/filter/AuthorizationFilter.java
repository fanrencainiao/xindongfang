package com.xindongfang.manager.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.xindongfang.manager.entity.User;
import com.xindongfang.manager.utils.ResponseUtil;




@WebFilter(filterName = "authorizationfilter", urlPatterns = { "/*" }, initParams = {
		@WebInitParam(name = "enable", value = "true") })
public class AuthorizationFilter implements Filter {

	private Map<String, String> requestUriMap;
	
	private Log logger = LogFactory.getLog(AuthorizationFilter.class);

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {



		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String accessToken = request.getHeader("access_token");
		long time = NumberUtils.toLong(request.getParameter("time"), 0);
		String secret = request.getParameter("secret");
		// //是否检验接口
		// boolean falg=StringUtils.isNotBlank(secret)&&0<time;
		String requestUri = request.getRequestURI();
		//
		 if("/favicon.ico".equals(requestUri))
		 return;

		// DEBUG**************************************************DEBUG
		StringBuffer sb = new StringBuffer();
		sb.append(request.getMethod()).append(" 请求：" + request.getRequestURI());

		logger.info(sb.toString());
		// DEBUG**************************************************DEBUG
		
		// 如果访问的是控制台或资源目录
		if (requestUri.startsWith("/user")||requestUri.startsWith("/static")||requestUri.startsWith("/templates")||requestUri.startsWith("/v2") ||requestUri.startsWith("/swagger-resources")|| requestUri.startsWith("/toPage") || requestUri.endsWith(".js") || requestUri.endsWith(".html")
				||requestUri.endsWith(".txt")|| requestUri.endsWith(".css") || requestUri.endsWith(".jpg")|| requestUri.endsWith(".png")|| requestUri.endsWith(".gif")||requestUri.endsWith(".json")||requestUri.endsWith(".woff")||requestUri.endsWith(".ttf")|| requestUri.startsWith("/test")) {
			User obj =(User) request.getSession().getAttribute(LoginSign.LOGIN_USER_KEY);
			// 用户已登录或访问资源目录或访问登录页面
//			System.out.println(obj);
//			
			if ((null != obj&&obj.getId()>0)||requestUri.startsWith("/user/add")||requestUri.startsWith("/templates/pages/addUser")||requestUri.endsWith(".txt")||requestUri.startsWith("/templates/pages/login")||requestUri.startsWith("/user/login")||requestUri.startsWith("/templates/console/login.html")||requestUri.startsWith("/static")||requestUri.startsWith("/webjars")||requestUri.startsWith("/v2")|| requestUri.endsWith(".js")|| requestUri.endsWith(".css") || requestUri.endsWith(".jpg")|| requestUri.endsWith(".png")||requestUri.startsWith("/swagger-resources")||requestUri.endsWith(".woff")||requestUri.endsWith(".ttf") ||requestUri.startsWith("/swagger-ui")) {
				arg2.doFilter(arg0, arg1);
				return;
			} else
				response.sendRedirect("/templates/pages/login.html");
			
		} else {
			renderByErrorKey(response, 0, "非法请求");
			
		}
	}

	private static final String template = "{\"code\":%1$s,\"msg\":\"%2$s\"}";

	private static void renderByErrorKey(ServletResponse response, int code, String msg) {

		String s = String.format(template, code, msg);

		ResponseUtil.output(response, s);
	}

	


}
