package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import security.SecurityContextHolder;
import utils.ResponseUtil;

@WebFilter("*")
public class SecurityFilter extends HttpFilter implements Filter {
       
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		String rootPath = "/servlet_study_jhj";
		String[] antMatchers = {"/auth"}; //인증이 필요없는 path들
		
		String uri = req.getRequestURI();
		
		// 인증이 필요 없는 경우
		for(String antMatcher: antMatchers) {
			if(uri.startsWith(rootPath + antMatcher)) {
				chain.doFilter(request, response); // doFilter 통과! 다음 servlet으로~				
				return;
			}
		}
		
		String token = req.getHeader("Authorization");
		
		// 					대. 소문자 구분x
		if(!req.getMethod().equalsIgnoreCase("options") && !SecurityContextHolder.isAuthenticated(token)) {
			ResponseUtil.response(resp).of(401).body("인증 실패");		
			return;
		}
		
		chain.doFilter(request, response); 
		
		
	}

}
