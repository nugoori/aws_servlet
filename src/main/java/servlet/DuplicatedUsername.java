package servlet;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.ResponseUtil;

@WebServlet("/auth/signup/duplicated/username")
public class DuplicatedUsername extends HttpServlet {
	
	private String[] usernames = {"aaa","bbb","ccc"};
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		
		for(int i = 0; i < usernames.length; i++) {
			// usernames배열과 parameter로 받은 username을 비교할때 null이 들어오면 nullpointException이 발생 함
			// 그런 경우를 피하기 위해 Objects를 사용
			if(Objects.equals(usernames[i], username)) {
				ResponseUtil.response(response).of(400).body(true);
				return;
			}
		}
		
		ResponseUtil.response(response).of(200).body(false);
	}


}
