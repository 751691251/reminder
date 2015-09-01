package com.ibm.iga.reminder.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.ibm.iga.reminder.model.ReminderUserDetails;

@Repository
public interface ReminderUserMapper {

	@Insert("insert into users (username, password, enabled) values (#{user}, #{password}, #{enabled}) ")
	public long addUser (ReminderUserDetails user);
	@Insert("insert into authorities (username, authority) values (#{user}, #{authority})")
	public long addUserRole (ReminderUserDetails user);
	
	@Update("update users set password = #{password}")
	public long updatePassword (ReminderUserDetails user);
	
	@Update("update authorities set authority = #{authority}")
	public long updateRole (ReminderUserDetails user);
	
	@Select("select a.username as user, a.password, b.authority from users  a, authorities  b where a.username = b.username and a.username = #{username}")
	public ReminderUserDetails get(String username);
}
