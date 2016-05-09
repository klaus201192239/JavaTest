package com.klaus.myinterface;



import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.klaus.bean.Course;

public interface CourseService {

	@Insert("insert into course(id,courseid,coursename,coursegrade) values (#{id},#{courseId},#{courseName},#{courseGrade});")
	public void insertCourse(Course course);
	
	@Select("select id from course where courseid=#{courseid}")
    public String getCourseId(String courseid);

	
}
