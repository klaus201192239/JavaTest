package com.klaus.main;

import com.klaus.office.ExcelUtil;

public class TestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("Test Start!!!");
		
		
		ExcelUtil excel=new ExcelUtil("F:/gradeq.xls");
		excel.printExcel();
		

		System.out.println("Test End!!!");
		
	}

}
