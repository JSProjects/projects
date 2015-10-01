package org.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.sql.DataSource;


import com.model.User;
import com.model.UserDAO;

 
public class JdbcUserDAO implements UserDAO
{
	private DataSource dataSource;
 
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
 
	public void insert(User user){
 
		String sql = "INSERT INTO MANUALKEYS " +
				"(USERNAME,CREATEDDATE,HMTYPE) VALUES (?,?,?)";
		Connection conn = null;
		java.util.Date date= new java.util.Date();
		
 
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user.getEmail());
			ps.setTimestamp(2, new java.sql.Timestamp(date.getTime()));
			ps.setString(3, user.getHmType());
			ps.executeUpdate();
			ps.close();
 
		} catch (SQLException e) {
			throw new RuntimeException(e);
 
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}

	@Override
	public List<User> fetch(User user) {
		
		List<User> list=new ArrayList<User>();
		String sql = "select UserName,createddate,EntitlementKey,hmType FROM test.manualkeys WHERE UserName like ?";
		Connection conn = null;
		

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,"%"+ user.getEmail()+"%");
			ResultSet rs=ps.executeQuery();
			
			
			
			 while(rs.next()){
			  	   User detail=new User();
			  	   detail.setEmail(rs.getString(1));
			  	   detail.setSubmittedDate(rs.getTimestamp(2));
			  	   detail.setEntitlementKey(rs.getString(3));
			  	   detail.setHmType(rs.getString(4));
			  	   list.add(detail);
			  }
		} catch (SQLException e) {
			throw new RuntimeException(e);
 
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
		return list;
	}
 
	@Override
	public List<User> fetchforMail() {
	
		List<User> list=new ArrayList<User>();
		String sql = "select UserName,EntitlementKey,HmType FROM test.manualkeys WHERE  MAILSENT=0 "
				+ " and EntitlementKey is not null";
		Connection conn = null;
		

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			
			
			 while(rs.next()){
			  	   User detail=new User();
			  	   detail.setEmail(rs.getString(1));
			  	   detail.setEntitlementKey(rs.getString(2));
			  	   detail.setHmType(rs.getString(3));
			  	   list.add(detail);
			  }
		} catch (SQLException e) {
			throw new RuntimeException(e);
 
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
		return list;
		
	}
	

	@Override
	public int countUser(User user) {
		String sql = null;
		if(user.getHmType().equalsIgnoreCase("Cloud")){
			sql="select count(*) from test.manualkeys where UserName= ? and hmType='Cloud' and to_days(curdate())-to_days(createddate) <345";
		}else{
			sql="select count(*) from test.manualkeys where UserName= ? and hmType='VA'";
		}
		
		Connection conn = null;
		int count=0;
		

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user.getEmail());
			ResultSet rs=ps.executeQuery();
			
			 while(rs.next()){
			  	  count=rs.getInt(1); 
			  }
		} catch (SQLException e) {
			throw new RuntimeException(e);
 
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
		return count;
	}

	@Override
	public void updateUser(User user) {
		String sql = "UPDATE test.MANUALKEYS SET MAILSENT=1 WHERE USERNAME= ? and HMTYPE =?";
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user.getEmail());
			ps.setString(2, user.getHmType());
			ps.executeUpdate();
			ps.close();
 
		} catch (SQLException e) {
			throw new RuntimeException(e);
 
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
		
	}
}
