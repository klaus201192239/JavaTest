package com.klaus.mybatis;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.klaus.bean.Course;
import com.klaus.bean.StuCourse;
import com.klaus.bean.Student;
import com.klaus.myinterface.CourseService;
import com.klaus.myinterface.StuCourseService;
import com.klaus.myinterface.StudentService;

public class MyIbatis {

	private static SqlSessionFactory sqlSessionFactory;
	private static Reader reader;

	static {
		try {
			reader = Resources.getResourceAsReader("configuration.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static SqlSessionFactory getSession() {
		return sqlSessionFactory;
	}

	public static void insert(Course course) {

		SqlSession session = sqlSessionFactory.openSession();
		try {

			CourseService userMapper = session.getMapper(CourseService.class);

			userMapper.insertCourse(course);
			// 提交事务
			session.commit();

		} finally {
			session.close();
		}

	}

	public static void insertStudent(Student student) {

		SqlSession session = sqlSessionFactory.openSession();
		try {

			StudentService userMapper = session.getMapper(StudentService.class);

			userMapper.insertStudent(student);
			// 提交事务
			session.commit();

		} finally {
			session.close();
		}

	}
	
	public static void insertStudentCourse(StuCourse stuC) {

		SqlSession session = sqlSessionFactory.openSession();
		try {

			StuCourseService userMapper = session.getMapper(StuCourseService.class);

			userMapper.insertStudentCourse(stuC);
			// 提交事务
			session.commit();

		} finally {
			session.close();
		}

	}
	
	
	public static String getCourseId(String courseid) {

		String str="";
		
		SqlSession session = sqlSessionFactory.openSession();
		try {

			CourseService userMapper = session.getMapper(CourseService.class);

			str=userMapper.getCourseId(courseid);
			

		} finally {
			session.close();
		}

		return str;
	}
	
	public static String getStudentId(String idcard) {

		String str="";
		
		SqlSession session = sqlSessionFactory.openSession();
		try {

			StudentService userMapper = session.getMapper(StudentService.class);

			str=userMapper.getStudentId(idcard);
			

		} finally {
			session.close();
		}

		return str;
	}
	

}
