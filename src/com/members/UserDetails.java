package com.members;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.dataconnector.DataConnector;

public class UserDetails {
	
	private int ID;
	private String first_name, last_name, email, phone;
	private boolean isAdmin = false;
	
	public UserDetails(String email) {
		Connection con = new DataConnector().connect();
		try {
			PreparedStatement ps = con.prepareStatement("SELECT id, first_name, last_name, phone, is_admin, email FROM users WHERE email = ?");
			ps.setString(1, email);
			setDetails(ps);
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public UserDetails(int userId) {
		Connection con = new DataConnector().connect();
		
		try {
			PreparedStatement ps = con.prepareStatement("SELECT id, first_name, last_name, phone, is_admin, email FROM users WHERE id = ?");
			ps.setInt(1, userId);
			setDetails(ps);
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void setDetails(PreparedStatement st) {
		
		try {
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				setID(rs.getInt(1));
				setFirstName(rs.getString(2));
				setLastName(rs.getString(3));
				setPhone(rs.getString(4));
				setIsAdmin(rs.getInt(5));
				setEmail(rs.getString(6));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int updateProfile(String fname, String lname, String ph, String em) {
		Connection con = new DataConnector().connect();
		try {
			Statement st = con.createStatement();
			int numOfRows = st.executeUpdate("UPDATE users SET first_name = '" + fname + "', last_name = '" + lname + "', phone = '" + ph + "', email = '" + em + "' WHERE id = " + getID());
			con.close();
			return numOfRows;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public boolean isAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(int isAdmin) {
		this.isAdmin = (isAdmin == 1) ? true : false;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public String getEncryptedEmail() {
		return PasswordEncrypter.encrypt(this.email);
	}

	public int getID() {
		return ID;
	}

	public String getFirstName() {
		return first_name;
	}

	public String getLastName() {
		return last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public void setFirstName(String first_name) {
		this.first_name = first_name;
	}

	public void setLastName(String last_name) {
		this.last_name = last_name;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
