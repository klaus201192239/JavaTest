package com.klaus.main;

import java.io.IOException;

import com.klaus.office.ExcelUtil;
import com.klaus.utils.TimeUtil;

public class TestMain {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		System.out.println("Test Start!!!");

		 ExcelUtil excel=new ExcelUtil("F:/grade.xls");
		 excel.printExcel();


		System.out.println(TimeUtil.getObjectId());

		System.out.println("Test End!!!");

	}


}
