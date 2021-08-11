package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.bean.UserBean;

@Repository
public class UserDao {
	@Autowired
	JdbcTemplate stmt;
	public void saveUser(UserBean user) {
		// exeu -> state -->
		// exeq --> read

		stmt.update("insert into user(name,email,phone,password,role,lastname) values (?,?,?,?,1,?)", user.getFirstName(), user.getEmail(),
				user.getPhone(),user.getPassword(),user.getLastName());

	}
	
	public UserBean finduser(UserBean user) {
		System.out.print("finduser");
		String email=user.getEmail();
		String password=user.getPassword();
		String sql="select * from user where email=\""+email+"\" and password=\""+password+"\"";
		return stmt.query(sql, new ResultSetExtractor<UserBean>() {
			public UserBean extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	            	UserBean contact = new UserBean();
	                contact.setUserId(rs.getInt("id"));
	                contact.setFirstName(rs.getString("name"));
	                contact.setEmail(rs.getString("email"));
	                contact.setLastName(rs.getString("lastname"));
	                contact.setPhone(rs.getLong("phone"));
	          
	                contact.setPassword(rs.getString("password"));
	                return contact;
	            }
	            		return null;
		
			}
		});

	}
}
