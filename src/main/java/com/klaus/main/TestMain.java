package com.klaus.main;

import org.json.JSONArray;
import org.json.JSONObject;
import com.klaus.http.MyHttpClient;


public class TestMain {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		System.out.println("Test Start!!!");
		
		
		/* File ff= new File("F:/as.txt");
		HttpPostUtil u = new HttpPostUtil("http://localhost:8080/empforecast/api/rest/uploadfile");*/

		JSONArray array=new JSONArray();
		
		
		for(int i=0;i<5;i++){
			
			JSONObject json=new JSONObject();
			
			json.put(i+"s", i+"e"+i);
			
			array.put(json);
			
		}
		
		MyHttpClient.sendJSONPost("http://localhost:8080/empforecast/api/rest/ability/addindex", array.toString());
		
		
		
		System.out.println("Test End!!!");

	}

}
