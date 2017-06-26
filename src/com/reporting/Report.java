package com.reporting;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.dataconnector.DataConnector;

public class Report {
	
	Connection con = null;
	
	Report() {
		this.con = new DataConnector().connect();
	}
	
	public void generateReport() {
		
		try {
			Statement st = this.con.createStatement();
			ResultSet rs = st.executeQuery("SELECT last_modified FROM tickets WHERE status = 'awaiting' OR status = 'closed'");
			
			while(rs.next()) {
				long unixTimestamp = rs.getLong(1);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
}
