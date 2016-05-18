package com.klaus.fileutil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class FileSaveDele {
	
	
	public static void main(String[] args) {
		
		
		FileSaveDele f=new FileSaveDele();
		
		try {
			f.uploadFile();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void uploadFile() throws Exception {
		
		
		InputStream fileInputStream = new FileInputStream(new File("F:/as.txt"));  
		
		System.out.println("aaaaaaaaaaaaaaaa");
		

		String filePath = "E:/as.txt" ;
		
		System.out.println(filePath);

		// save the file to the server
		saveFile(fileInputStream, filePath);

		System.out.println("bbbbbbbbbbbbbbbbb");

		return ;

	}

	// save uploaded file to a defined location on the server
	private void saveFile(InputStream uploadedInputStream, String serverLocation) {

		
		System.out.println("cccccccccccccccccccc");
		
		try {
			OutputStream outpuStream = new FileOutputStream(new File(serverLocation));
			
			
			System.out.println("dddddddddddddddddddd");
			
			int read = 0;
			byte[] bytes = new byte[1024];
			
			
			System.out.println("eeeeeeeeeeeeeeeee");

			outpuStream = new FileOutputStream(new File(serverLocation));
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				outpuStream.write(bytes, 0, read);
			}
			
			
			System.out.println("ffffffffffffffffffff");
			
			outpuStream.flush();
			outpuStream.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}
	
}
