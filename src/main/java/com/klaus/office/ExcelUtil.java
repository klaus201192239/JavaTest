package com.klaus.office;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import com.klaus.bean.Course;
import com.klaus.bean.forecastCourseBean;
import com.klaus.mybatis.MyIbatis;
import com.klaus.security.SecurityCoder;
import com.klaus.utils.TimeUtil;

public class ExcelUtil {

	private Workbook workbook;
	private List<forecastCourseBean> listCourse=new ArrayList<forecastCourseBean>();
	private List<Map<String,String>> listCourseGrade=new ArrayList<Map<String,String>>();

	Map<String,String> map=new HashMap<String,String>();
	
	
	public ExcelUtil() {

		workbook = new HSSFWorkbook();

	}

	public ExcelUtil(String fileUri) {

		try {

			File excelFile = new File(fileUri); // 创建文件对象
			FileInputStream is = new FileInputStream(excelFile); // 文件流
			this.workbook = WorkbookFactory.create(is); // 这种方式 Excel 2003/2007/2010 都是可以处理的
			
			

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
	    
	    
	    int sheetCount = workbook.getNumberOfSheets();  //Sheet的数量   
	    
	  //遍历每个Sheet  
	    for (int s = 0; s < sheetCount; s++) {
	    	
	    	Sheet sheet = workbook.getSheetAt(s);  
	    	
	    	if(sheet!=null){
	    		
	    		int rowCount = sheet.getPhysicalNumberOfRows(); //获取总行数  
		    	
		    	//遍历每一行  
	            for (int r = 0; r < rowCount; r++) {
	            	
	            	Row row = sheet.getRow(r);  
	            	
	            	if(row!=null){
	            		
	            		int cellCount = row.getPhysicalNumberOfCells(); //获取总列数  

		                //遍历每一列  
		                for (int c = 0; c < cellCount; c++) {

		                	Cell cell = row.getCell(c);
		                	
		                	if(cell!=null){
		                		
		                		int cellType = cell.getCellType();  
			                    
			                    String cellValue = getCellValues(cellType,cell).replaceAll("\\s*", "");  ;
			                    
			                  //  System.out.print(cellValue + "("+r+","+c+")   ");  
			                    
			                    
			                    getCousrInfo( r,c,cellValue);
			                    
			                    getCourseGrade( r,c,cellValue);
			               //     
			                    
			                    
		                		
		                	}

		                }
		                
		              //  System.out.println();  
		            		
	            	}
	                
	            }
	    		
	    	}
	    }
	    
	    
	//    List<Map<String,String>> listTem=new ArrayList<Map<String,String>>();
	    
	    
	    for(int i=0;i<listCourse.size();i++){
	    	
	    	forecastCourseBean bean=listCourse.get(i);
	    	
	    	Course course=new Course();
	    	course.setId(TimeUtil.getObjectId());
	    	course.setCourseId(bean.getCourseId());
	    	course.setCourseName(bean.getCourseName());
	    	course.setCourseGrade(Double.parseDouble(bean.getCourseGrade()));
	    	
	    //	MyIbatis.insert(course);
	    	
	    	
	    	
	    }
	    
	    
	    for(int i=0;i<listCourseGrade.size();i++){
	    	
	    	Map<String,String> tempMap=listCourseGrade.get(i);
	    	
	        System.out.println(tempMap.size()+"-----"+ tempMap.toString());
	    	
	    }
	    
	  
	}

	private String getCellValues(int cellType, Cell cell) {

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
					cellValue = String.valueOf(cell.getNumericCellValue()); // 数字
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
	
	
	private void getCousrInfo(int r,int c,String cellValue){
		
		if(r==1&&c>=5){
        	
        	if(cellValue.length()!=0){
        		
        		forecastCourseBean bean=new forecastCourseBean();
            	bean.setId(c);
            	bean.setCourseId(cellValue);
            	
            	listCourse.add(bean);
        		
        	}

        }
		
		
		if(r==2&&c>=5){
        	

        	if(cellValue.length()!=0){
        		for(int i=0;i<listCourse.size();i++){
            		
            		forecastCourseBean beanOld=listCourse.get(i);
            		
            		if(beanOld.getId()==c){
            			
            			beanOld.setCourseName(cellValue);
            			
            		}
            		
            	}
        	}
        }
		
		if(r==3&&c>=5){
        	

        	if(cellValue.length()!=0){
        		
        		for(int i=0;i<listCourse.size();i++){
            		
            		forecastCourseBean beanOld=listCourse.get(i);
            		
            		if(beanOld.getId()==c){
            			
            			beanOld.setCourseGrade(cellValue);
            			
            		}
            		
            	}
        		
        	}
        }
		
	}
	
	private String getCourseID(int clounmNumber){
		
		for(int i=0;i<listCourse.size();i++){
    		
    		forecastCourseBean beanOld=listCourse.get(i);
    		
    		if(beanOld.getId()==clounmNumber){
    			
    			return beanOld.getCourseId();
    			
    		}
    		
    	}
		return null;
		
	}
	
	
	private void getCourseGrade(int r,int c,String cellValue){

		if(r>=5){
			
			if(c==1){					
				
				String str=SecurityCoder.encryptSHA(cellValue);
				
				//System.out.println(str);
				
	        	map.put("StudentId",str);

	        }
			

			if(c==4){
				
				String str=SecurityCoder.encryptSHA(cellValue);
				
	        	map.put("StudentName",str);

	        }
				
			if(c>=5){
				
				String courseID=getCourseID(c);
				
				if(courseID!=null){
					
					map.put(courseID,cellValue);
					
				}

	        	if(map.size()==(listCourse.size()+2)){
	        		
	        		listCourseGrade.add(map);

	        		map=new HashMap<String,String>();
	        		
	        	}

	        }	

		}

	}
	

}
