package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/category")
public class CategoryList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String[] categoryArray = {
			"한식",
			"체험관광",
			"카페",
			"자연명소",
			"양식",
			"문화예술"
	};
	
	private class Feed {
		private String feedName;
		private String categoryName; 
		
		public Feed(String feedName, String categoryName) {
			this.feedName = feedName;
			this.categoryName = categoryName;
		}
		
		public String getCategoryName() {
			return categoryName;
		}
		
		public String getFeedInfo() {
			return "feedName: " + feedName + ", categoryName: " + categoryName + "\n";
		}
	}
	
	private Feed[] feedArray = {
			new Feed("1번피드", "한식"),
			new Feed("2번피드", "한식"),
			new Feed("3번피드", "한식"),
			new Feed("4번피드", "체험관광"),
			new Feed("5번피드", "체험관광"),
			new Feed("6번피드", "문화예술"),
			new Feed("7번피드", "한식"),
			new Feed("8번피드", "카페"),
			new Feed("9번피드", "자연명소"),
			new Feed("10번피드", "한식"),
			new Feed("11번피드", "카페"),
			new Feed("12번피드", "한식"),
			new Feed("13번피드", "자연명소"),
			new Feed("14번피드", "한식"),
			new Feed("15번피드", "문화예술")
	};
	// void >> 메소드에서 대이터를 return하는게 아니라 response객체에 데이터를 넣어서 전송하여 응답
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 요청이 오면 TomcatServer에서 HttpServletRequest, HttpServletResponse 객체 생성
		System.out.println(request.getRequestURI());
		System.out.println(request.getMethod());
		String categoryName = request.getParameter("categoryName");
		
		if(!checkCategory(categoryName)) {
			// 한글 인코딩
			response.setCharacterEncoding("UTF-8");
			response.setStatus(400);
			response.getWriter().println("해당 카테고리는 존재하지 않는 카테고리 입니다.");
			return;
		}
		
		// 람다 안에서 밖에있는 주소를 바꾸기 위해 주소를 전역상태로 인식?
//		AtomicReference<String> responseData = new AtomicReference<String>("");
//		
//		findFeedByCategoryName(categoryName).forEach(feed -> {
//			responseData.set(responseData.get() + feed.getFeedInfo());
//		});
		
		Gson gson = new Gson();
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json"); // http통신에서 JSON객체로 보내는것을 표시하기 위해 사용
		//    전송하기위한 메소드   >   응답
		response.getWriter().println(gson.toJson(findFeedByCategoryName(categoryName)).toString());
	}
	
	private boolean checkCategory(String categoryName) {
		for(int i = 0; i < categoryArray.length; i++) {
			if(categoryArray[i].equals(categoryName)) {
				return true;
			}
		}
		return false;
	}
	
	private List<Feed> findFeedByCategoryName(String categoryName) {
		List<Feed> feeds = new ArrayList();
		
		for(int i = 0; i < feedArray.length; i++) {
			if(feedArray[i].getCategoryName().equals(categoryName)) {
				feeds.add(feedArray[i]);
			}
		}
		
		return feeds;
	}

}
