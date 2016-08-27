package com.klaus.fileutil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileTxtReader {

	public static void main(String[] args) {

		// readStringByChar("F:\\opendata.txt");
		List<String> list = getLines("F:\\opendata.txt");

		for (int i = 0; i < list.size(); i++) {

			String str = list.get(i);

			int len = str.length();

			int left = 0;
			int right = 0;
			int index = 0;
			
		//	System.out.println(str.charAt(len-1));

			for (int j = len - 1; j >= 0; j--) {
		
				
				if (str.charAt(j)==']') {

			//		System.out.println("hahah");
					
					right++;
					
					left=1;

				}

				if (str.charAt(j) == '[') {

					right--;
					
				//	System.out.println("lalalala");

				}
				
				if(right==0&&left!=0){
					
					index=j;
					left=0;
					
				}

			}
			
			//System.out.println(index);
			
				String str1=str.substring(0,index);
			String str2=str.substring(index);
			
			System.out.println(str1);
			
			System.out.println("**************************");
			
			System.out.println(str2);
			
			System.out.println("############################");

		}

	}

	public static void readStringByChar(String filePath) {

		// StringBuffer str=new StringBuffer("");

		File file = new File(filePath);

		try {

			FileReader fr = new FileReader(file);

			int ch = 0;

			while ((ch = fr.read()) != -1)

			{

				// new String(str.getBytes(),"UTF-8")

				// String str=new String((ch,"UTF-8");

				System.out.print((char) ch);

			}

			fr.close();

		} catch (IOException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

			System.out.println("File reader³ö´í");

		}

		// return str.toString();

	}

	public static List<String> getLines(String fileName) {
		List<String> lines = new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"));
			String line = null;
			while ((line = br.readLine()) != null) {

				lines.add(line);

				// System.out.println(line);

				// System.out.println("##################################");

			}
			br.close();
		} catch (FileNotFoundException e) {

		} catch (IOException e) {

		}
		return lines;
	}

}
