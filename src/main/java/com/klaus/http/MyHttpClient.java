package com.klaus.http;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;


public class MyHttpClient {

	public static String sendGet(String url, Map<?, ?> map) {

		if (map == null || map.size() == 0) {

			return sendGet(url);

		} else {

			StringBuilder sb = new StringBuilder();
			sb.append(url);
			sb.append("?");

			for (Entry<?, ?> entry : map.entrySet()) {

				sb.append(entry.getKey().toString());
				sb.append("=");
				sb.append(entry.getValue().toString());
				sb.append("&");

			}

			int len = sb.toString().length();

			return sendGet(sb.toString().substring(0, len - 1));

		}

	}

	public static String sendGet(String urlstr) {

		System.out.println(urlstr);

		String l = "";
		try {

			URL url = new URL(urlstr);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5 * 1000);
			if (conn.getResponseCode() != 200) // 从Internet获取网页,发送请求,将网页以流的形式读回来
			{
				throw new RuntimeException("请求url失败");
			}

			InputStream is = conn.getInputStream();
			InputStreamReader reader = new InputStreamReader(is, "utf-8");
			BufferedReader br = new BufferedReader(reader);
			String s = null;
			while ((s = br.readLine()) != null) {
				if (s != null) {
					s.trim();
					l = l + s;
				}
			}

			br.close();
			reader.close();
			l.trim();
			conn.disconnect();

		} catch (Exception e) {

			l = "error";
		}
		return l;
	}

	public static void sendJSONPost(String urlPath, String jsonStr) throws Exception {

		byte[] entitydata = jsonStr.getBytes();
		URL url = new URL(urlPath);
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

		}

	}

	public static void postFileNoTitle(String urlPath, String filePath,String fileName) {

		try {

			String boundary = "Boundary-b1ed-4060-99b9-fca7ff59c113";
			String Enter = "\r\n";

			// URL url = new
			// URL("http://localhost:8080/empforecast/api/rest/score/uploadfile");
			URL url = new URL(urlPath);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod("POST");
			conn.setUseCaches(false);
			conn.setInstanceFollowRedirects(true);
			conn.setRequestProperty("Charsert", "UTF-8");
			conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);

			conn.connect();

			DataOutputStream dos = new DataOutputStream(conn.getOutputStream());

			String part1 = Enter + "--" + boundary + Enter + "Content-Type: application/octet-stream" + Enter
					+ "Content-Disposition: form-data; filename=\""+fileName+"\";name=\"file\"" + Enter + Enter;

			// part 2
			// String part2 = Enter + "--" + boundary + Enter + "Content-Type:
			// text/plain" + Enter
			// + "Content-Disposition: form-data; name=\"file\"" + Enter + Enter
			// + "hkfsdfsd" + Enter + "--"
			// + boundary + "--";

			File xml = new File(filePath);
			FileInputStream fis = new FileInputStream(xml);
			byte[] xmlBytes = new byte[fis.available()];
			fis.read(xmlBytes);

			dos.writeBytes(part1);
			dos.write(xmlBytes);

			dos.flush();
			dos.close();
			fis.close();

			System.out.println("status code: " + conn.getResponseCode());

			conn.disconnect();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
