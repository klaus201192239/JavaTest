package com.klaus.myinterface;

import com.klaus.bean.User;
import org.apache.ibatis.annotations.*;

public interface UserService {

	@Insert("insert into user (name) values ( #{name});")
	public void insertUser(User user);
	
	
}
