package ioedata.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class CoapInterceptor extends HandlerInterceptorAdapter {

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("COME INTO POSTHANDLER..." + request.getRequestURL());
		System.out.println(request.getLocalAddr() + ": " + request.getLocalPort());
		System.out.println(request.getRemoteAddr() + ": " + request.getRemotePort());
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("COME INTO PREHANDLER...");
		return super.preHandle(request, response, handler);
	}

}
