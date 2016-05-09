package com.klaus.myinterface;

import org.apache.ibatis.annotations.Insert;
import com.klaus.bean.StuCourse;

public interface StuCourseService {
	
	@Insert("insert into stucourse(id,stuid,courseid,score) values (#{id},#{stuid},#{courseid},#{score});")
	public void insertStudentCourse(StuCourse stuC);

}
