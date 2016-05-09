package com.klaus.myinterface;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.klaus.bean.Student;


public interface StudentService {
	
	@Insert("insert into student(id,stuid,name,grade) values (#{id},#{stuId},#{name},#{grade});")
	public void insertStudent(Student student);
	
	@Select("select id from student where stuid=#{idcard}")
    public String getStudentId(String idcard);


}
