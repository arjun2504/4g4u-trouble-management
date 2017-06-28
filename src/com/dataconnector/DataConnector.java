package com.dataconnector;

import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.Connection;

public class DataConnector {
	
	private Connection con;
	
	public DataConnector() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			this.con = (Connection) DriverManager.getConnection("jdbc:oracle:thin:@10.53.110.165:1521:xe","system","system");
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
