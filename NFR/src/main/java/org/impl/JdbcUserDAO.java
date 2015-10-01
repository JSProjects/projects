package org.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.sql.DataSource;















import com.common.Util;
import com.model.OperationParameters;
import com.model.Partner;
import com.model.PartnerDAO;
import com.model.PartnerHistory;
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
				"(USERNAME,CREATEDDATE) VALUES (?,?)";
		Connection conn = null;
		java.util.Date date= new java.util.Date();
		
 
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user.getEmail());
			ps.setTimestamp(2, new java.sql.Timestamp(date.getTime()));
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
		String sql = "select UserName,createddate,EntitlementKey FROM test.manualkeys WHERE UserName= ?";
		Connection conn = null;
		

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user.getEmail());
			ResultSet rs=ps.executeQuery();
			
			
			
			 while(rs.next()){
			  	   User detail=new User();
			  	   detail.setEmail(rs.getString(1));
			  	   detail.setSubmittedDate(rs.getTimestamp(2));
			  	   detail.setEntitlementKey(rs.getString(3));
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
	public Map<String,List<PartnerHistory>> fetchforMail() {
	
		Map<String,List<PartnerHistory>> user=new HashMap<String,List<PartnerHistory>>();
		List<PartnerHistory> list=new ArrayList<PartnerHistory>();
		String sql = "SELECT partneremail, serialnumber, entitlementkey, hmid FROM ns.provision_key where mailSent='f' and active=0 and entitlementkey is not null order by partneremail asc;";
		Connection conn = null;
		

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			
			
			 while(rs.next()){
				 PartnerHistory detail=new PartnerHistory();
				 String username=rs.getString(1);
				 detail.setSerialnumber(rs.getString(2));
			  	 detail.setEk(rs.getString(3));
			  	 detail.setHmId(rs.getString(4));
			  	
			  	 
			  	 if(user.get(username)!=null && !user.get(username).isEmpty()){
			  		 user.get(username).add(detail);
			  	 }else{
			  		 list.add(detail);
			  		 user.put(username, list);
			  	 }
			  	   
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
		return user;
		
	}
	

	@Override
	public int countUser(User user) {

		String sql = "select count(*) from test.manualkeys where UserName= ? and to_days(curdate())-to_days(createddate) <345";
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
	public void updateUser(Map<String, List<PartnerHistory>> userlist) {
		String sql = "Update ns.provision_key set mailSent='t' where partneremail=? and hmid=? and serialnumber=? and entitlementkey=?;";
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			int rows_created=0;
			
			for (Entry<String, List<PartnerHistory>> entry : userlist.entrySet()) {
		
			ps.setString(1,entry.getKey());
			
			for(PartnerHistory partner: entry.getValue()){
				ps.setString(2, partner.getHmId());
				ps.setString(3, partner.getSerialnumber());
				ps.setString(4, partner.getEk());
				ps.execute();
				rows_created++;
			}		
		  }
 
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
	public void saveDetails(PartnerDAO partner, String name) {
		String sql="INSERT INTO ns.provision_key (partneremail,endusername,hmType,serialnumber,hmid,token) values( ?,?,?,?,?,?)";
		Connection conn = null;
		
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			String token=Util.randomString(6);
			for(int i=0;i<partner.getHmids().size();i++){
				ps.setString(1, name);
				ps.setString(2, partner.getEndUser());
				ps.setString(3, partner.getHmType());
				ps.setString(4, partner.getSerialnumbers().get(i).getName());
				ps.setString(5, partner.getHmids().get(i).getName());
				ps.setString(6, token);
				ps.addBatch();
			}

		    int i[]=ps.executeBatch();
		    System.out.println("###### insertion1### row   "+i.length);
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
	public List<PartnerHistory> getHistory(String name) {
		String sql="SELECT endusername,entitlementkey,serialnumber,hmid,Active,hmType FROM ns.provision_key where partneremail=?";
		List<PartnerHistory> list=new ArrayList<PartnerHistory>();
		
		Connection conn = null;
		

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs=ps.executeQuery();
			
			
			
			 while(rs.next()){
				 PartnerHistory detail=new PartnerHistory();
				 detail.setEndUser(rs.getString(1));
			  	 detail.setEk(rs.getString(2));
			  	 detail.setSerialnumber(rs.getString(3));
			  	 detail.setHmId(rs.getString(4));
			  	 detail.setStatus(rs.getString(5).equalsIgnoreCase("1") ?"InActive":"Active" );
			  	 detail.setHmType(rs.getString(6).equalsIgnoreCase("hmop") ? "On-Prem" :"HMOL");
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
	public void deactivateKeys(List<String> cancelKeys) {
		String sql = "UPDATE ns.provision_key SET Active=1 WHERE serialnumber= ? and entitlementkey=?";
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			for(String string:cancelKeys){
				String[] parts = string.split(";");
				String serialnumber = parts[0]; 
				String ek = parts[1]; 
				ps.setString(1, serialnumber);
				ps.setString(2, ek);
				ps.addBatch();
			}
 
			int i[]=ps.executeBatch();
		    System.out.println("###### insertion### row   "+i.length);
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
	public ArrayList<String> getSerialNumbers() {
		ArrayList<String> list=new ArrayList<String>();
		String sql = "select serialnumber FROM ns.serialnumbers";
		Connection conn = null;
		

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();

			 while(rs.next()){
			  	   String serialnumber=rs.getString(1);
			  	   list.add(serialnumber);
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
	public ArrayList<String> getActivatedSerialNumbers() {
		String sql="SELECT serialnumber FROM ns.provision_key where Active=0";
		ArrayList<String> list=new ArrayList<String>();
		
		Connection conn = null;
		

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();

			 while(rs.next()){
				 list.add(rs.getString(1));
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
		return  list;
	}
}
