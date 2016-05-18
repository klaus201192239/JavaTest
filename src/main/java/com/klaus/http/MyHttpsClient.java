package com.klaus.http;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;

public class MyHttpsClient {

	public static void uploadFile(String fileName) {
		try {
			// 换行符
			final String newLine = "\r\n";
			final String boundaryPrefix = "--";
			// 定义数据分隔线
			String BOUNDARY = "========7d4a6d158c9";
			// 服务器的域名
			URL url = new URL("http://localhost:8080/abcd/rest/files/upload");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// 设置为POST情
			conn.setRequestMethod("POST");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			// 设置请求头参数
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("Charsert", "UTF-8");
			conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
			OutputStream out = new DataOutputStream(conn.getOutputStream());
			// 上传文件
			File file = new File(fileName);
			StringBuilder sb = new StringBuilder();
			sb.append(boundaryPrefix);
			sb.append(BOUNDARY);
			sb.append(newLine);
			// 文件参数,photo参数名可以随意修改
			sb.append("Content-Disposition: form-data;name=\"file\";filename=\"as.txt\"" + newLine);
			sb.append("Content-Type:application/octet-stream");
			// 参数头设置完以后需要两个换行，然后才是参数内容
			sb.append(newLine);
			sb.append(newLine);
			// 将参数头的数据写入到输出流中
			out.write(sb.toString().getBytes());
			// 数据输入流,用于读取文件数据
			DataInputStream in = new DataInputStream(new FileInputStream(file));
			byte[] bufferOut = new byte[1024];
			int bytes = 0;
			// 每次读1KB数据,并且将文件数据写入到输出流中
			while ((bytes = in.read(bufferOut)) != -1) {
				out.write(bufferOut, 0, bytes);
			}
			// 最后添加换行
			out.write(newLine.getBytes());
			in.close();
			// 定义最后数据分隔线，即--加上BOUNDARY再加上--。
			byte[] end_data = (newLine + boundaryPrefix + BOUNDARY + boundaryPrefix + newLine).getBytes();
			// 写上结尾标识
			out.write(end_data);
			out.flush();
			out.close();
			// 定义BufferedReader输入流来读取URL的响应
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
		} catch (Exception e) {
			System.out.println("发送POST请求出现异常！" + e);
			e.printStackTrace();
		}
	}

	public static boolean sendJSONPost(String path, String json) throws Exception {

		// StringBuilder sb = new StringBuilder();
		// sb.append(json);

		byte[] entitydata = json.getBytes();
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setConnectTimeout(5 * 1000);
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestProperty("Content-Length", String.valueOf(entitydata.length));
		OutputStream os = conn.getOutputStream();
		os.write(entitydata);

		os.flush();
		os.close();
		if (conn.getResponseCode() == 200) {

			InputStream inputStream = (InputStream) conn.getInputStream();

			BufferedReader in = null;
			in = new BufferedReader(new InputStreamReader(inputStream));

			String line;
			String result = "";
			while ((line = in.readLine()) != null) {
				result += line;
			}

			System.out.println(result);

			return true;
		}

		System.out.println("no");

		return false;

	}

	public static boolean sendFilePost() {
		return true;
	}

	public static boolean sendPost(String path, Map<String, String> params) throws IOException {
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, String> entry : params.entrySet()) {
			// sb.append(entry.getKey()).append('=').append(entry.getValue()).append('&');
			sb.append(entry.getKey()).append('=').append(URLEncoder.encode(entry.getValue(), "UTF-8")).append('&');
		}
		sb.deleteCharAt(sb.length() - 1);

		String str = "klaus=dfddfdfdfdf";

		byte[] entitydata = str.getBytes();
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setConnectTimeout(5 * 1000);
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		conn.setRequestProperty("Content-Length", String.valueOf(entitydata.length));
		OutputStream os = conn.getOutputStream();
		os.write(entitydata);
		os.flush();
		os.close();
		if (conn.getResponseCode() == 200) {

			InputStream inputStream = (InputStream) conn.getInputStream();

			BufferedReader in = null;
			in = new BufferedReader(new InputStreamReader(inputStream));

			String line;
			String result = "";
			while ((line = in.readLine()) != null) {
				result += line;
			}

			System.out.println(result);

			return true;
		}
		return false;
	}

	public static void add(String url, String data) throws Exception {
		// 构建请求
		URL postUrl = new URL(url);

		HttpsURLConnection con = (HttpsURLConnection) postUrl.openConnection();// 打开连接
		con.setRequestMethod("POST");// post方式提交

		con.setDoOutput(true);// 打开读写属性，默认均为false
		con.setDoInput(true);
		con.setUseCaches(false);// Post请求不能使用缓存
		con.setInstanceFollowRedirects(true);

		con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		// 添加头信息
		// con.setRequestProperty("X-Bmob-Application-Id", APP_ID);
		// con.setRequestProperty("X-Bmob-REST-API-Key", API_Key);
		// con.setRequestProperty("Content-Type", "application/json");

		DataOutputStream out = new DataOutputStream(con.getOutputStream());

		// 发送请求
		// String data = "{\"name\":\"tom\"}";

		data = "klaus=dfddfdfdfdf";

		out.writeBytes(data);
		out.flush();
		out.close();

		// 接收数据
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

	public static String sendGet(String url, String paramm) {
		String result = "";
		BufferedReader in = null;
		try {
			// String urlNameString = url + "?" + param;

			// String urlNameString =
			// "https://wild-snake-96493.wilddogio.com/java/test.json";

			// URL realUrl = new URL(urlNameString);
			URL realUrl = new URL(url);

			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段
			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
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
