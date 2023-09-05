package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletResponse;

// Tomcat - (http~response => ServletResponse로 업캐스팅 => filter ) -> servlet( 에서 HttpResponse로 다운캐스팅 해서 사용 )
@WebFilter("*")
public class CorsFilter extends HttpFilter implements Filter {
	// 
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		
		// origin : 요청을 보낸 쪽
		httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
		// 
		httpServletResponse.setHeader("Access-Control-Allow-Headers", "*");
		// 
		httpServletResponse.setHeader("Access-Control-Allow-Methods", "*");
		// 
		httpServletResponse.setHeader("Access-Control-Max-Age", "3600");
		
		// 전처리 -> servlet filter -> 후처리
		chain.doFilter(request, response);
	}

}
