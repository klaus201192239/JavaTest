package com.klaus.office;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {

	private Workbook workbook;

	public ExcelUtil() {

		workbook = new HSSFWorkbook();

	}

	public ExcelUtil(String fileUri) {

		try {

			File excelFile = new File(fileUri); // �����ļ�����
			FileInputStream is = new FileInputStream(excelFile); // �ļ���
			this.workbook = WorkbookFactory.create(is); // ���ַ�ʽ Excel 2003/2007/2010 ���ǿ��Դ����
			
			

		} catch (Exception e) {

		}

	}

	public Workbook getWorkbook() {
		return workbook;
	}

	public void setWorkbook(Workbook workbook) {
		this.workbook = workbook;
	}

	public void printExcel()
	{  
	    
	    
	    int sheetCount = workbook.getNumberOfSheets();  //Sheet������   
	    
	  //����ÿ��Sheet  
	    for (int s = 0; s < sheetCount; s++) {
	    	
	    	Sheet sheet = workbook.getSheetAt(s);  
	    	
	    	if(sheet!=null){
	    		
	    		int rowCount = sheet.getPhysicalNumberOfRows(); //��ȡ������  
		    	
		    	//����ÿһ��  
	            for (int r = 0; r < rowCount; r++) {
	            	
	            	Row row = sheet.getRow(r);  
	            	
	            	if(row!=null){
	            		
	            		int cellCount = row.getPhysicalNumberOfCells(); //��ȡ������  

		                //����ÿһ��  
		                for (int c = 0; c < cellCount; c++) {

		                	Cell cell = row.getCell(c);
		                	
		                	if(cell!=null){
		                		
		                		int cellType = cell.getCellType();  
			                    
			                    String cellValue = getCellValues(cellType,cell);
			                    
			                    System.out.print(cellValue + "("+r+","+c+")   ");  
		                		
		                	}

		                }
		                
		                System.out.println();  
		            	
	            		
	            	}
	            	
	                
	            }
	    		
	    	}
	    	
	    	
	    	
	    }
	  
	}

	private String getCellValues(int cellType, Cell cell) {

		String cellValue = "";

		switch (cellType) {
			case Cell.CELL_TYPE_STRING: // �ı�
				cellValue = cell.getStringCellValue();
				break;
			case Cell.CELL_TYPE_NUMERIC: // ���֡�����
				if (DateUtil.isCellDateFormatted(cell)) {

					SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

					cellValue = fmt.format(cell.getDateCellValue()); // ������
				} else {
					cellValue = String.valueOf(cell.getNumericCellValue()); // ����
				}
				break;
			case Cell.CELL_TYPE_BOOLEAN: // ������
				cellValue = String.valueOf(cell.getBooleanCellValue());
				break;
			
			case Cell.CELL_TYPE_BLANK: // �հ�
				cellValue = cell.getStringCellValue();
				break;
			case Cell.CELL_TYPE_ERROR: // ����
				cellValue = "����";
				break;
			case Cell.CELL_TYPE_FORMULA: // ��ʽ
				cellValue = "����";
				break;
			default:
				cellValue = "����";		
		}

		return cellValue;
	}

}
