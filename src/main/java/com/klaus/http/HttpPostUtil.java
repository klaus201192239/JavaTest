package com.klaus.http;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpPostUtil {
	public static void maini() {
		try {
			String boundary = "Boundary-b1ed-4060-99b9-fca7ff59c113"; 
	//		String Enter = "\r\n";

			File xml = new File("F:\\gradeq.xls");
			FileInputStream fis = new FileInputStream(xml);

//			URL url = new URL("http://localhost:8080/empforecast/api/rest/score/upload");
			URL url = new URL("http://localhost:8080/empforecast/api/rest/score/uploadfile");
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

		/*	String part =Enter +  "--" + boundary + Enter  
                    + "Content-Type:application/octet-stream" + Enter  
                    + "Content-Disposition: form-data; name=\"file\"" + Enter + Enter;  
			
			String part1 = Enter +  "--" + boundary + Enter  
                    + "Content-Type: application/octet-stream" + Enter  
                    + "Content-Disposition: form-data; filename=\"as.txt\"; name=\"file\"" + Enter + Enter;  
		
			// part 2
			String part2 = Enter + "--" + boundary + Enter + "Content-Type: text/plain" + Enter
					+ "Content-Disposition: form-data; name=\"file\"" + Enter + Enter + "hkfsdfsd" + Enter + "--"
					+ boundary + "--";
		*/

			byte[] xmlBytes = new byte[fis.available()];
			fis.read(xmlBytes);

		//	dos.writeBytes(part3);
		//	dos.writeBytes(part1);
			dos.write(xmlBytes);
			
			
		//	dos.write(part3);
			

			dos.flush();
			dos.close();
			fis.close();

			System.out.println("status code: " + conn.getResponseCode());

			conn.disconnect();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	
	public static void main(String[] args) throws Exception {
		
		maini();

	}

}