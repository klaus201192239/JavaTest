package com.klaus.http;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class MyHttpsClient {

	
	public static void add(String url,String data) throws Exception {  
	    //构建请求  
	    URL postUrl = new URL(url);  
	  
	    HttpsURLConnection con = (HttpsURLConnection) postUrl.openConnection();//打开连接   
	    con.setRequestMethod("POST");//post方式提交  
	  
	    con.setDoOutput(true);//打开读写属性，默认均为false   
	    con.setDoInput(true);  
	    con.setUseCaches(false);//Post请求不能使用缓存   
	    con.setInstanceFollowRedirects(true);  
	  
	    //添加头信息  
	 //   con.setRequestProperty("X-Bmob-Application-Id", APP_ID);  
	  //  con.setRequestProperty("X-Bmob-REST-API-Key", API_Key);  
	  //  con.setRequestProperty("Content-Type", "application/json");  
	  
	    DataOutputStream out = new DataOutputStream(con.getOutputStream());  
	  
	    //发送请求  
	    //String data = "{\"name\":\"tom\"}";  
	    out.writeBytes(data);  
	    out.flush();  
	    out.close();  
	  
	    //接收数据  
	    BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));  
	    String line;  
	    StringBuffer responseText = new StringBuffer();  
	    while ((line = reader.readLine()) != null) {  
	        responseText.append(line).append("\r\n");  
	    }  
	    reader.close();  
	    con.disconnect();  
	    System.out.println(responseText.toString());  
	}  
	
	
	
	public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
           // String urlNameString = url + "?" + param;
        	
        	String urlNameString = "https://wild-snake-96493.wilddogio.com/java/test.json";
        	
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

	public static String httpRequest(String req_url) {
        StringBuffer buffer = new StringBuffer();  
        try {  
            URL url = new URL(req_url);  
            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();  
   
            httpUrlConn.setDoOutput(false);  
            httpUrlConn.setDoInput(true);  
            httpUrlConn.setUseCaches(false);  
   
            httpUrlConn.setRequestMethod("GET");  
            httpUrlConn.connect();  
   
            // 将返回的输入流转换成字符串  
            InputStream inputStream = httpUrlConn.getInputStream();  
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");  
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);  
   
            String str = null;  
            while ((str = bufferedReader.readLine()) != null) {  
                buffer.append(str);  
            }  
            bufferedReader.close();  
            inputStreamReader.close();  
            // 释放资源  
            inputStream.close();  
            inputStream = null;  
            httpUrlConn.disconnect();  
   
        } catch (Exception e) {  
            System.out.println(e.getStackTrace());  
        }  
        return buffer.toString();  
    }  
}
