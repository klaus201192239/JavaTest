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
			// ���з�
			final String newLine = "\r\n";
			final String boundaryPrefix = "--";
			// �������ݷָ���
			String BOUNDARY = "========7d4a6d158c9";
			// ������������
			URL url = new URL("http://localhost:8080/abcd/rest/files/upload");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// ����ΪPOST��
			conn.setRequestMethod("POST");
			// ����POST�������������������
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			// ��������ͷ����
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("Charsert", "UTF-8");
			conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
			OutputStream out = new DataOutputStream(conn.getOutputStream());
			// �ϴ��ļ�
			File file = new File(fileName);
			StringBuilder sb = new StringBuilder();
			sb.append(boundaryPrefix);
			sb.append(BOUNDARY);
			sb.append(newLine);
			// �ļ�����,photo���������������޸�
			sb.append("Content-Disposition: form-data;name=\"file\";filename=\"as.txt\"" + newLine);
			sb.append("Content-Type:application/octet-stream");
			// ����ͷ�������Ժ���Ҫ�������У�Ȼ����ǲ�������
			sb.append(newLine);
			sb.append(newLine);
			// ������ͷ������д�뵽�������
			out.write(sb.toString().getBytes());
			// ����������,���ڶ�ȡ�ļ�����
			DataInputStream in = new DataInputStream(new FileInputStream(file));
			byte[] bufferOut = new byte[1024];
			int bytes = 0;
			// ÿ�ζ�1KB����,���ҽ��ļ�����д�뵽�������
			while ((bytes = in.read(bufferOut)) != -1) {
				out.write(bufferOut, 0, bytes);
			}
			// �����ӻ���
			out.write(newLine.getBytes());
			in.close();
			// ����������ݷָ��ߣ���--����BOUNDARY�ټ���--��
			byte[] end_data = (newLine + boundaryPrefix + BOUNDARY + boundaryPrefix + newLine).getBytes();
			// д�Ͻ�β��ʶ
			out.write(end_data);
			out.flush();
			out.close();
			// ����BufferedReader����������ȡURL����Ӧ
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
		} catch (Exception e) {
			System.out.println("����POST��������쳣��" + e);
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
		// ��������
		URL postUrl = new URL(url);

		HttpsURLConnection con = (HttpsURLConnection) postUrl.openConnection();// ������
		con.setRequestMethod("POST");// post��ʽ�ύ

		con.setDoOutput(true);// �򿪶�д���ԣ�Ĭ�Ͼ�Ϊfalse
		con.setDoInput(true);
		con.setUseCaches(false);// Post������ʹ�û���
		con.setInstanceFollowRedirects(true);

		con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		// ���ͷ��Ϣ
		// con.setRequestProperty("X-Bmob-Application-Id", APP_ID);
		// con.setRequestProperty("X-Bmob-REST-API-Key", API_Key);
		// con.setRequestProperty("Content-Type", "application/json");

		DataOutputStream out = new DataOutputStream(con.getOutputStream());

		// ��������
		// String data = "{\"name\":\"tom\"}";

		data = "klaus=dfddfdfdfdf";

		out.writeBytes(data);
		out.flush();
		out.close();

		// ��������
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

			// �򿪺�URL֮�������
			URLConnection connection = realUrl.openConnection();
			// ����ͨ�õ���������
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// ����ʵ�ʵ�����
			connection.connect();
			// ��ȡ������Ӧͷ�ֶ�
			Map<String, List<String>> map = connection.getHeaderFields();
			// �������е���Ӧͷ�ֶ�
			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}
			// ���� BufferedReader����������ȡURL����Ӧ
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
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
