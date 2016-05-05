package com.klaus.mybatis;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.klaus.bean.Course;
import com.klaus.myinterface.CourseService;

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
	
	public static void insert(Course course){
		
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
	
	

}
