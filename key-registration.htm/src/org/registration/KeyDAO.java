package org.registration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ws.rs.core.Response;

public class KeyDAO {

	public Key createKey(Key key) {
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionHelper.getConnection();
			ps = c.prepareStatement(
					"INSERT INTO ns.key (firstname,lastname,email,companyname,serialnumber) VALUES (?, ?, ?, ?, ?)",
					new String[] { "id" });
			ps.setString(1, key.getFirstName());
			ps.setString(2, key.getLastName());
			ps.setString(3, key.getEmail());
			ps.setString(4, key.getCompany());
			ps.setString(5, key.getSerialNumber());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			int id = rs.getInt(1);
			key.setID(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			ConnectionHelper.close(c);
		}
		return key;
	}

	public Integer validate(String serialnumber) {
		Connection c = null;
		PreparedStatement ps = null;
		PreparedStatement p = null;
		Integer count = 0;
		try {
			c = ConnectionHelper.getConnection();
			ps = c.prepareStatement("SELECT * FROM  ns.key WHERE serialnumber= ?");
			ps.setString(1, serialnumber);
			ResultSet rs = ps.executeQuery();
			p = c.prepareStatement("SELECT * FROM  ns.freeap WHERE serialnumber= ?");
			p.setString(1, serialnumber);
			ResultSet r = p.executeQuery();
			if (r.next()) {
				if (rs.next()) {
					count = 1;
				}
			} else {
				count = 2;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			ConnectionHelper.close(c);
		}
		return count;
	}

	public void save(List<String> sn) throws SQLException {
		System.out.println("Save Serial Numbers  Method is calling "
				+ sn.size());
		Connection con = null;
		PreparedStatement ps = null;
		con= ConnectionHelper.getConnection();

		StringBuffer sql = new StringBuffer();

		sql.append("INSERT INTO ns.freeap (serialnumber) ");
		sql.append(" VALUES (?); ");
		try {
			ps = con.prepareStatement(sql.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (String serialnumber : sn) {

			try {
				ps.setString(1, serialnumber);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				ps.addBatch();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		int i[];
		try {
			i = ps.executeBatch();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.getNextException().printStackTrace();
		}
		try {
			con.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public List<Key> fetchforMail() {
		List<Key> list=new ArrayList<Key>();
		String sql = "select email,serialnumber,entitlementkey FROM ns.key WHERE  MAILSENT=false "
				+ " and entitlementkey is not null";
		
		Connection con = null;
		try {

			PreparedStatement ps = null;
			con= ConnectionHelper.getConnection();
			
			
			ps = con.prepareStatement(sql.toString());
			ResultSet rs=ps.executeQuery();
			 while(rs.next()){
			  	   Key detail=new Key();
			  	   detail.setEmail(rs.getString(1));
			  	   detail.setSerialNumber(rs.getString(2));
			  	   detail.setEntitlementkey(rs.getString(3));
			  	   list.add(detail);
			  }
		} catch (SQLException e) {
			throw new RuntimeException(e);
 
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {}
			}
		}
		return list;
		
	}

	public void updateUser(Key key) {
		String sql = "UPDATE ns.key SET mailsent=true WHERE email= ? and serialnumber =?";
		Connection conn = null;
		
		try {
			Connection con = null;
			PreparedStatement ps = null;
			con= ConnectionHelper.getConnection();
			ps = con.prepareStatement(sql.toString());
			ps.setString(1, key.getEmail());
			ps.setString(2, key.getSerialNumber());
			ps.executeQuery();
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

	public Calendar getLastSyncTime() {
		String sql = "select version from ns.freeap order by version desc LIMIT 1";
		Connection conn = null;
		Calendar c=null;
		try {
			Connection con = null;
			PreparedStatement ps = null;
			con= ConnectionHelper.getConnection();
			ps = con.prepareStatement(sql.toString());
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
					rs.getTime(1,c);
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
		return c;
	}

}
