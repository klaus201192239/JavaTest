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
import com.klaus.bean.StuCourse;
import com.klaus.bean.Student;
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
	    	
	   // 	forecastCourseBean bean=listCourse.get(i);
	    	
	    //	Course course=new Course();
	    //	course.setId(TimeUtil.getObjectId());
	    //	course.setCourseId(bean.getCourseId());
	    //	course.setCourseName(bean.getCourseName());
	    //	course.setCourseGrade(Double.parseDouble(bean.getCourseGrade()));
	    	
	    //	MyIbatis.insert(course);
	    	
	    	
	    	
	    }
	    
	    
	    for(int i=0;i<listCourseGrade.size();i++){
	    	
	    	Map<String,String> tempMap=listCourseGrade.get(i);
	    	
	     System.out.println(tempMap.get("StudentId"));
	    	
	    	String studentid=MyIbatis.getStudentId(tempMap.get("StudentId"));
	    	
	    	for (Map.Entry<String, String> entry : tempMap.entrySet()) {  
	    		  
	    	  //  System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue()); 
	    	    
	    	    String key= entry.getKey();
	    	    
	    	    if("StudentName".equals(key)||"StudentId".equals(key)||"StudentGrade".equals(key)){

	    	    	
	    	    	
	    	    }else{
	    	    	
	    	    	System.out.println(studentid+"   "+MyIbatis.getCourseId(key)+"   "+entry.getValue());
	    	    	
	    	    	
	    	    	StuCourse stuC=new StuCourse();
	    	    	stuC.setCourseid(MyIbatis.getCourseId(key));
	    	    	stuC.setId(TimeUtil.getObjectId());
	    	    	stuC.setScore(entry.getValue());
	    	    	stuC.setStuid(studentid);
	    	    	
	    	    	MyIbatis.insertStudentCourse(stuC);
	    	    	
	    	    	
	    	    }
	    	  
	    	}  
	    	
	    	
	       // System.out.println(tempMap.size()+"-----"+ tempMap.toString());
	        
	        
//	        Student student=new Student();

//	        student.setName(tempMap.get("StudentName"));
//	        student.setStuId(tempMap.get("StudentId"));
//	        student.setId(TimeUtil.getObjectId());
//        student.setGrade(Integer.parseInt(tempMap.get("StudentGrade").substring(0, 5).replace(".","")));
	       
//	        MyIbatis.insertStudent(student);
	        
//	        System.out.println(student.getName()+"-----"+ student.getStuId()+"-----"+student.getGrade());
	    	
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
	        	map.put("StudentGrade",cellValue);

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

	        	if(map.size()==(listCourse.size()+3)){
	        		
	        		listCourseGrade.add(map);

	        		map=new HashMap<String,String>();
	        		
	        	}

	        }	

		}

	}
	

}
