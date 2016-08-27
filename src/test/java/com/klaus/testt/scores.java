package com.klaus.testt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class scores {

	public static void main(String[] args) {

		try {

			
			String filePath="F:/asd.xls";
			
			abc(filePath);

		} catch (Exception e) {
		}

	}

	public static void abc(String filePath) throws Exception {

		File excelFile = new File(filePath); // 创建文件对象
		FileInputStream is = new FileInputStream(excelFile); // 文件流
		Workbook workbook = WorkbookFactory.create(is); // 这种方式 Excel
														// 2003/2007/2010
		// 都是可以处理的

		int sheetCount = workbook.getNumberOfSheets(); // Sheet的数量

		// 遍历每个Sheet
		for (int s = 0; s < sheetCount; s++) {

			Sheet sheet = workbook.getSheetAt(s);

			if (sheet != null) {

				int rowCount = sheet.getPhysicalNumberOfRows(); // 获取总行数

				
				int h=1;
				
				// 遍历每一行
				for (int r = 3; r < rowCount; r++) {

					
					
					
					
					Row row = sheet.getRow(r);

					if (row != null) {

						
						System.out.print("H"+h+":");
						
						h++;
						
						Cell cell9 = row.getCell(9);
						Cell cell11 = row.getCell(11);
						Cell cell12 = row.getCell(12);
						Cell cell16 = row.getCell(16);
						
						Cell cell14 = row.getCell(14);
						Cell cell17 = row.getCell(17);
						
						
						int cellType9 = cell9.getCellType();
						int cellType11 = cell11.getCellType();
						int cellType12 = cell12.getCellType();
						int cellType16 = cell16.getCellType();
						
						//int cellTyp14e = cell14.getCellType();
						//int cellType17 = cell17.getCellType();
						
						
						
						
						String cellValue9 = getCellValues(cellType9, cell9).replaceAll("\\s*", "");
						String cellValue11 = getCellValues(cellType11, cell11).replaceAll("\\s*", "");
						String cellValue12 = getCellValues(cellType12, cell12).replaceAll("\\s*", "");
						String cellValue16 = getCellValues(cellType16, cell16).replaceAll("\\s*", "");
						
						
						System.out.print(cellValue9+"  ");
						System.out.print(cellValue11+"  ");
						System.out.print(cellValue12+"  ");
						System.out.print(cellValue16+"  ");
						
						
						
						
						int s1=Integer.parseInt(cellValue9)+Integer.parseInt(cellValue11)+Integer.parseInt(cellValue12);
						
						double s2=(s1+0.00)+(Integer.parseInt(cellValue16)*0.60);
						
						System.out.print(s1+"  ");
						System.out.print(s2+"  ");
						
						
						cell14.setCellValue(s1);
						cell17.setCellValue(s2);
						
						//int cellCount = row.getPhysicalNumberOfCells(); // 获取总列数

						// 遍历每一列
						//for (int c = 0; c < cellCount; c++) {

							//Cell cell = row.getCell(c);

							//if (cell != null) {

								//int cellType = cell.getCellType();

								//String cellValue = getCellValues(cellType, cell).replaceAll("\\s*", "");

							//	System.out.print(cellValue + "("+r+","+c+")");

						//	}

					//	}

						System.out.println();

					}

				}

			}
		}
		
		
		
		FileOutputStream os = new FileOutputStream("F:/asnew.xls");  
		workbook.write(os);  
	    os.close();  

	}

	private static String getCellValues(int cellType, Cell cell) {

		String cellValue = "";

		switch (cellType) {
		case Cell.CELL_TYPE_STRING: // 文本
			cellValue = cell.getStringCellValue();
			break;
		case Cell.CELL_TYPE_NUMERIC: // 数字、日期
			if (DateUtil.isCellDateFormatted(cell)) {

				SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

				cellValue = fmt.format(cell.getDateCellValue()); // 日期型
			} else {

				DecimalFormat df = new DecimalFormat("0");

				cellValue = df.format(cell.getNumericCellValue());
			}
			break;
		case Cell.CELL_TYPE_BOOLEAN: // 布尔型
			cellValue = String.valueOf(cell.getBooleanCellValue());
			break;

		case Cell.CELL_TYPE_BLANK: // 空白
			cellValue = cell.getStringCellValue();
			break;
		case Cell.CELL_TYPE_ERROR: // 错误
			cellValue = "错误";
			break;
		case Cell.CELL_TYPE_FORMULA: // 公式
			cellValue = "错误";
			break;
		default:
			cellValue = "错误";
		}

		return cellValue;
	}

}
