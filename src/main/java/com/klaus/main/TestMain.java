package com.klaus.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class TestMain {
	
	
	//JSESSIONID=e05669a88a8a21ad60f8f4304663
	//LT_portal.dlut.edu.cn_-237570-3Q0Ckbl5HAuDEIG6YvQm
	//JSESSIONID=e07b8e94f95ea2292b78e511e60e
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		System.out.println("Test Start!!!");		

		
		// getScoreHtml();
		
		//getnews() ;
		
		
		// send();
		 
		 getCASeHtml();

		System.out.println("Test End!!!");

	}
	
	
	
	private static String getCASeHtml() throws Exception {

		String result = "";

		CloseableHttpClient httpClient = HttpClients.createDefault();

		HttpGet httpGet = new HttpGet("http://portal.dlut.edu.cn/cas.jsp?ticket=ST_portal.dlut.edu.cn_-16274-qssQwJfnVmvT5fhN0HBO");

		httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");

		httpGet.setHeader("Accept-Encoding", "gzip, deflate, sdch");

		httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.8");

		httpGet.setHeader("Connection", "keep-alive");

		httpGet.setHeader("Cookie","JSESSIONID=23b99b0c38110848b28d74041a8a");

		httpGet.setHeader("Host", "portal.dlut.edu.cn");

		httpGet.setHeader("Referer", "http://portal.dlut.edu.cn/cas/login");

		httpGet.setHeader("User-Agent",
				"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.154 Safari/537.36 LBBROWSER");

		HttpResponse response = null;

		try {

			response = httpClient.execute(httpGet);

		} catch (ClientProtocolException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

		if (response != null) {
			
			
			
			
			Header headers[] = response.getAllHeaders();

			int i = 0;

			while (i < headers.length) {

				
				
				System.out.println(headers[i].getName()+"  "+headers[i].getValue());

				i++;
				
			}
			
			

			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

				result = EntityUtils.toString(response.getEntity());
				
				
				
				System.out.println(result);


			}
		}

		httpClient.close();

		return result;

	}
	
	
	
	private static String getScoreHtml() throws Exception {

		String result = "";

		CloseableHttpClient httpClient = HttpClients.createDefault();

		HttpGet httpGet = new HttpGet("http://portal.dlut.edu.cn/cas/login?service=http%3A%2F%2Fportal.dlut.edu.cn%2Fcas.jsp");

		httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");

		httpGet.setHeader("Accept-Encoding", "gzip, deflate, sdch");

		httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.8");

		httpGet.setHeader("Connection", "keep-alive");

		//httpGet.setHeader("Cookie", cookie);

		httpGet.setHeader("Host", "portal.dlut.edu.cn");

		//httpGet.setHeader("Referer", "http://zhjw.dlut.edu.cn/gradeLnAllAction.do?type=ln&oper=sx");

		httpGet.setHeader("User-Agent",
				"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.154 Safari/537.36 LBBROWSER");

		HttpResponse response = null;

		try {

			response = httpClient.execute(httpGet);

		} catch (ClientProtocolException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

		if (response != null) {
			
			
			
			
			Header headers[] = response.getAllHeaders();

			int i = 0;

			while (i < headers.length) {

				
				
				System.out.println(headers[i].getName()+"  "+headers[i].getValue());

				i++;
				
			}
			
			

			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

				result = EntityUtils.toString(response.getEntity());
				
				
				
				System.out.println(result);


			}
		}

		httpClient.close();

		return result;

	}
	
	private static String getnews() throws Exception {

		String result = "";

		CloseableHttpClient httpClient = HttpClients.createDefault();

		HttpGet httpGet = new HttpGet("http://portal.dlut.edu.cn/portalhtml/html/dlutnews_new.jsp");

		httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");

		httpGet.setHeader("Accept-Encoding", "gzip, deflate, sdch");

		httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.8");

		httpGet.setHeader("Connection", "keep-alive");

		//httpGet.setHeader("Cookie", cookie);

		httpGet.setHeader("Host", "portal.dlut.edu.cn");

		httpGet.setHeader("Referer", "http://portal.dlut.edu.cn/cas/login?service=http%3A%2F%2Fportal.dlut.edu.cn%2Fcas.jsp");

		httpGet.setHeader("User-Agent",
				"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.154 Safari/537.36 LBBROWSER");

		HttpResponse response = null;

		try {

			response = httpClient.execute(httpGet);

		} catch (ClientProtocolException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

		if (response != null) {
			
			
			
			
			Header headers[] = response.getAllHeaders();

			int i = 0;

			while (i < headers.length) {

				
				
				System.out.println(headers[i].getName()+"  "+headers[i].getValue());

				i++;
				
			}
			
			

			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

				result = EntityUtils.toString(response.getEntity());
				
				
				
				System.out.println(result);


			}
		}

		httpClient.close();

		return result;

	}
	
	
	
	
	private static void send() throws Exception {

		//String body = "";
		
		
		
		
		String url = "http://portal.dlut.edu.cn/cas/login";
		Map<String, String> map = new HashMap<String, String>();
		map.put("encodedService", "http%3a%2f%2fportal.dlut.edu.cn%2fcas.jsp");
		map.put("service", "http://portal.dlut.edu.cn/cas.jsp");
		map.put("serviceName", "null");
		map.put("action", "DCPLogin");
		map.put("inputname", "31517035");
		map.put("selmail", "1");
		map.put("username", "31517035");
		map.put("password", "553355");
		map.put("lt", "LT_portal.dlut.edu.cn_-16169-c75AMcZy5kj5dmpcT3lo");
		map.put("userNameType", "cardID");
		
		

		CloseableHttpClient client = HttpClients.createDefault();

		HttpPost httpPost = new HttpPost(url);

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		if (map != null) {
			for (Entry<String, String> entry : map.entrySet()) {
				nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
		}

		
		
		//JSESSIONID=e05669a88a8a21ad60f8f4304663
		//LT_portal.dlut.edu.cn_-237570-3Q0Ckbl5HAuDEIG6YvQm
		//JSESSIONID=e07b8e94f95ea2292b78e511e60e




		httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));

		httpPost.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		httpPost.setHeader("Accept-Encoding", "gzip, deflate");
		httpPost.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
		httpPost.setHeader("Cache-Control", "max-age=0");
		httpPost.setHeader("Connection", "keep-alive");
		// httpPost.setHeader("Content-Length", "306");
		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
		httpPost.setHeader("Cookie", "JSESSIONID=23b97d0b6123cedd8ba62eca58cd; bdacookies=31517035; selcookies=1; JSESSIONID=23b99b0c38110848b28d74041a8a");
		httpPost.setHeader("Host", "portal.dlut.edu.cn");
		httpPost.setHeader("Origin", "http://portal.dlut.edu.cn");
		httpPost.setHeader("Referer", "http://portal.dlut.edu.cn/cas/login");
		//httpPost.setHeader("Upgrade-Insecure-Requests", "1");
		httpPost.setHeader("User-Agent",
				"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.154 Safari/537.36 LBBROWSER");

		// 执行请求操作，并拿到结果（同步阻塞）
		CloseableHttpResponse response = client.execute(httpPost);

		Header headers[] = response.getAllHeaders();

		int i = 0;

		while (i < headers.length) {

			//if (headers[i].getName().equals("Set-Cookie")) {

			//	cookie = cookie + headers[i].getValue().replace("path=/", "");

			//}
			
			System.out.println(headers[i].getName()+"  : "+ headers[i].getValue());

			i++;
		}

		HttpEntity entity = response.getEntity();
		if (entity != null) {
			String body = EntityUtils.toString(entity, "utf-8");
			
			System.out.println(body);
			
		}
		EntityUtils.consume(entity);
		// 释放链接
		response.close();
		response.close();
		
		//return body;
	}
	
	
	private static void sendll() throws Exception {

		//String body = "";
		
		String encoding="utf-8";
		
		String url = "http://portal.dlut.edu.cn/report/Report-ResultAction.do?newReport=true";
		Map<String, String> map = new HashMap<String, String>();
		map.put("xn_dummy", "2014-2015");
		map.put("xq_dummy", "");
		map.put("reportId", "439cf471-c69b-4577-9fe2-5e2fca96d1e0");
		//map.put("newReport", "DCPLogin");
		map.put("xn", "2014-2015");
		map.put("xq", "");
		
		
		

		CloseableHttpClient client = HttpClients.createDefault();

		HttpPost httpPost = new HttpPost(url);

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		if (map != null) {
			for (Entry<String, String> entry : map.entrySet()) {
				nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
		}

		httpPost.setEntity(new UrlEncodedFormEntity(nvps, encoding));

		httpPost.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		httpPost.setHeader("Accept-Encoding", "gzip, deflate");
		httpPost.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
		httpPost.setHeader("Cache-Control", "max-age=0");
		httpPost.setHeader("Connection", "keep-alive");
		//httpPost.setHeader("Content-Length", "106");
		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
		httpPost.setHeader("Cookie", "JSESSIONID=80ecc1f9bff2f0d51a20a6728396; JSESSIONID=80e44f53fd0fd87e35e845a9995d");
		httpPost.setHeader("Host", "portal.dlut.edu.cn");
		httpPost.setHeader("Origin", "http://portal.dlut.edu.cn");
		httpPost.setHeader("Referer", "http://portal.dlut.edu.cn/report/Report-EntryAction.do?reportId=439cf471-c69b-4577-9fe2-5e2fca96d1e0&ctype=1");
		//httpPost.setHeader("Upgrade-Insecure-Requests", "1");
		httpPost.setHeader("User-Agent",
				"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.154 Safari/537.36 LBBROWSER");

		// 执行请求操作，并拿到结果（同步阻塞）
		CloseableHttpResponse response = client.execute(httpPost);

		Header headers[] = response.getAllHeaders();

		int i = 0;

		while (i < headers.length) {

			//if (headers[i].getName().equals("Set-Cookie")) {

			//	cookie = cookie + headers[i].getValue().replace("path=/", "");

			//}
			
			System.out.println(headers[i].getName()+"  : "+ headers[i].getValue());

			i++;
		}

		HttpEntity entity = response.getEntity();
		if (entity != null) {
			String body = EntityUtils.toString(entity, encoding);
			
			System.out.println(body);
			
		}
		EntityUtils.consume(entity);
		// 释放链接
		response.close();
		
		//return body;
	}

}
