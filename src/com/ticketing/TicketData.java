package com.ticketing;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dataconnector.DataConnector;
import com.members.UserDetails;

public class TicketData {
	
	private int ID;
	private UserDetails userdata;
	private Feedback feedback;

	private String title, department, category, subCategory, description, status, phone, email;
	private long lastModified, createdAt;
	
	TicketData(int ticketId) {
		Connection con = new DataConnector().connect();
		try {
			PreparedStatement st = con.prepareStatement("SELECT id, title, department, category, subcat, description, status, user_id, phone, email FROM tickets WHERE id = ?");
			st.setInt(1, ticketId);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				setID(rs.getInt(1));
				setTitle(rs.getString(2));
				setDepartment(rs.getString(3));
				setCategory(rs.getString(4));
				setSubCategory(rs.getString(5));
				setDescription(rs.getString(6));
				setStatus(rs.getString(7));  
				setUserdata( new UserDetails( rs.getInt(8) ) );
				setPhone(rs.getString(9)); 
				setEmail(rs.getString(10));
				setFeedback( new Feedback( getID() ) );
			}
			
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//setUserdata(new UserDetails(email));
		
	}
	
	public Feedback getFeedback() {
		return feedback;
	}

	public void setFeedback(Feedback feedback) {
		this.feedback = feedback;
	}
	
	public String getPhone() {
		return phone;
	}

	public String getEmail() {
		return email;
	}

	public long getLastModified() {
		return lastModified;
	}

	public long getCreatedAt() {
		return createdAt;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setLastModified(long lastModified) {
		this.lastModified = lastModified;
	}

	public void setCreatedAt(long createdAt) {
		this.createdAt = createdAt;
	}

	public int getID() {
		return ID;
	}

	public UserDetails getUserdata() {
		return userdata;
	}

	public String getTitle() {
		return title;
	}

	public String getDepartment() {
		return department;
	}

	public String getCategory() {
		return category;
	}

	public String getSubCategory() {
		return subCategory;
	}

	public String getDescription() {
		return description;
	}

	public String getStatus() {
		return status;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public void setUserdata(UserDetails ud) {
		this.userdata = ud;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
