package com.dataconnector;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class DataConnector {
	
	private Connection con;
	
	public DataConnector() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/4g4u", "root", "");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Connection connect() {
		return this.con;
	}
}
