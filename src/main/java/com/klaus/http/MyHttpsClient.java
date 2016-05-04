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
	    //��������  
	    URL postUrl = new URL(url);  
	  
	    HttpsURLConnection con = (HttpsURLConnection) postUrl.openConnection();//������   
	    con.setRequestMethod("POST");//post��ʽ�ύ  
	  
	    con.setDoOutput(true);//�򿪶�д���ԣ�Ĭ�Ͼ�Ϊfalse   
	    con.setDoInput(true);  
	    con.setUseCaches(false);//Post������ʹ�û���   
	    con.setInstanceFollowRedirects(true);  
	  
	    //���ͷ��Ϣ  
	 //   con.setRequestProperty("X-Bmob-Application-Id", APP_ID);  
	  //  con.setRequestProperty("X-Bmob-REST-API-Key", API_Key);  
	  //  con.setRequestProperty("Content-Type", "application/json");  
	  
	    DataOutputStream out = new DataOutputStream(con.getOutputStream());  
	  
	    //��������  
	    //String data = "{\"name\":\"tom\"}";  
	    out.writeBytes(data);  
	    out.flush();  
	    out.close();  
	  
	    //��������  
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
            // �򿪺�URL֮�������
            URLConnection connection = realUrl.openConnection();
            // ����ͨ�õ���������
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // ����ʵ�ʵ�����
            connection.connect();
            // ��ȡ������Ӧͷ�ֶ�
            Map<String, List<String>> map = connection.getHeaderFields();
            // �������е���Ӧͷ�ֶ�
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // ���� BufferedReader����������ȡURL����Ӧ
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("����GET��������쳣��" + e);
            e.printStackTrace();
        }
        // ʹ��finally�����ر�������
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
   
            // �����ص�������ת�����ַ���  
            InputStream inputStream = httpUrlConn.getInputStream();  
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");  
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);  
   
            String str = null;  
            while ((str = bufferedReader.readLine()) != null) {  
                buffer.append(str);  
            }  
            bufferedReader.close();  
            inputStreamReader.close();  
            // �ͷ���Դ  
            inputStream.close();  
            inputStream = null;  
            httpUrlConn.disconnect();  
   
        } catch (Exception e) {  
            System.out.println(e.getStackTrace());  
        }  
        return buffer.toString();  
    }  
}
