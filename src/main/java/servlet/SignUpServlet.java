package servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.JsonParseUtil;
import utils.ResponseUtil;

/** 
 * 회원가입 -> 사용자 정보 데이터의 추가
 * 추가 -> create, DB에 정보를 insert -> POST요청
 * POST 메소드의 특징
 * 1. 요청시 서버로 전달되어지는 데이터가 주소창에 표시되지 않는다.
 *  -> GET요청처럼 http://localhost:8080category?categoryName=한식이런식으로 주소창에 나타나지 않음 (?Params)
 *  -> POST는 http://localhost:8080category (BODY에 데이터를 담아서 서버로 전송)
 * 2. 전송 데이터의 크기 제한이 없다.
 *
*/

@WebServlet("/auth/signup")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map<String, Object> userMap = JsonParseUtil.toMap(request.getInputStream());
		
//		System.out.println(userMap.get("username"));
//		System.out.println(userMap.get("password"));
//		System.out.println(userMap.get("name"));
//		System.out.println(userMap.get("email"));
		System.out.println("회원가입");
		
		ResponseUtil.response(response).of(200).body("회원가입 성공");
	}

	
}









