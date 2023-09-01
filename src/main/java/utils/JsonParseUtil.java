package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

import com.google.gson.Gson;

public class JsonParseUtil {
	
//	Client - 데이터 전송 stream(통로?)생성? -> Server / 반대되면 outputStream 이겠지?
	public static Map<String, Object> toMap(InputStream inputStream) { 
		
		StringBuilder jsonData = new StringBuilder("");
		
		BufferedReader bufferedReader = null;
		
		if(inputStream == null) {
			return null;
		}
		
		bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		
		while(true) {
			try {
				String data = bufferedReader.readLine();
				if(data == null) {
					break;
				}
				jsonData.append(data);
				
			} catch (IOException e) {
				return null;
			}						
		}
		
		Gson gson = new Gson();
//							json문자열          to Map객체
		return gson.fromJson(jsonData.toString(), Map.class);
	}
	
	public static String toJson(Object object) {
		Gson gson = new Gson();
		
		return gson.toJson(object);
	}
 	
}
