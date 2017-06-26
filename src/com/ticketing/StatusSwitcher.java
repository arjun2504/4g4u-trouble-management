package com.ticketing;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.dataconnector.DataConnector;
import com.dataconnector.Identifier;

public class StatusSwitcher {
	
	private int ID;
	private Connection con = null;
	
	StatusSwitcher(int ticketId) {
		this.ID = ticketId;
		this.con = new DataConnector().connect();
	}
	
	public void toAwaiting() {
		changeStatus("awaiting");
	}
	
	public void toClosed() {
		changeStatus("closed");
	}
	
	public int setFeedback(int rating, String comments) {
		try {
			PreparedStatement ps = this.con.prepareStatement("INSERT INTO feedback (id, ticket_id, stars, comments, feedback_added_at) VALUES (?,?,?,?,?)");
			long currentUnixTime = System.currentTimeMillis() / 1000L;
			ps.setInt(1, Identifier.getNextId("feedback"));
			ps.setInt(2, this.ID);
			ps.setInt(3, rating);
			ps.setString(4, comments);
			ps.setLong(5, currentUnixTime);
			return ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	private void changeStatus(String status) {
		PreparedStatement ps = null;
		try {
			if(status.equals("closed")) {
				ps = this.con.prepareStatement("UPDATE tickets SET status = ?, last_modified = ? WHERE id = ?");
				long currentUnixTime = System.currentTimeMillis() / 1000L;
				ps.setLong(2, currentUnixTime);
				ps.setInt(3, this.ID);
			} else {
				ps = this.con.prepareStatement("UPDATE tickets SET status = ? WHERE id = ?");
				ps.setInt(2, this.ID);
			}
			ps.setString(1, status);
			
			
			ps.executeUpdate();
			
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
