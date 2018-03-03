package Filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class Filter1
 */
@WebFilter({ "/Filter1", "/SIndex.jsp","/study.jsp","/detail.jsp" })
public class Filter1 implements Filter {

    /**
     * Default constructor. 
     */
    public Filter1() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");      
		// pass the request along the filter chain
        HttpServletRequest req = (HttpServletRequest) request;
	    HttpServletResponse res = (HttpServletResponse) response;
	    
	    //HttpSession session = req.getSession(true);

	    //从session里取的用户名信息
	    String username = req.getSession().getAttribute("name").toString();
	    System.out.print("==="+username);
	    //判断如果没有取到用户信息,就跳转到登陆页面
	    if (username == null || "".equals(username)) {
	      //跳转到登陆页面
	    	try {
	    		response.getWriter().print("<script language='javascript'>alert('请先登录再访问这些页面');</script>");
	    	} catch (IOException e) {
	    		// TODO Auto-generated catch block
	    		e.printStackTrace();
	    	}
	    	  ((HttpServletResponse) response).setHeader("refresh", "1,URL=index.jsp");
	    }
	    
	    else {
	      //已经登陆,继续此次请求
	      chain.doFilter(request,response);
	    }
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
