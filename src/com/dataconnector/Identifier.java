package com.dataconnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Identifier {
	public static int getNextId(String table) {
		Connection con = new DataConnector().connect();
		Statement st;
		int newId = 0;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT id FROM " + table + " ORDER BY id DESC");
			if(rs.next())
				newId = rs.getInt(1) + 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return newId;
		
	}
}
