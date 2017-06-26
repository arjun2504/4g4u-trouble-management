package com.ticketing;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dataconnector.DataConnector;

public class Feedback {
	private int ID, rating, ticketId;
	private String comment;
	private long feedbackTime;
	
	Feedback(int ticketId) {
		Connection con = new DataConnector().connect();
		try {
			PreparedStatement ps = con.prepareStatement("SELECT id, ticket_id, stars, comments, feedback_added_at FROM feedback WHERE ticket_id = ?");
			ps.setInt(1, ticketId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				setID(rs.getInt(1));
				setTicketId(rs.getInt(2));
				setRating(rs.getInt(3));
				setComment(rs.getString(4));
				setFeedbackTime(rs.getLong(5));
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getRating() {
		return rating;
	}

	public int getTicketId() {
		return ticketId;
	}

	public String getComment() {
		return comment;
	}

	public long getFeedbackTime() {
		return feedbackTime;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setFeedbackTime(long feedbackTime) {
		this.feedbackTime = feedbackTime;
	}
}
