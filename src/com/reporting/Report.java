package com.reporting;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.dataconnector.DataConnector;

public class Report {
	
	Connection con = null;
	String date;
	int count;
	String reportType;

	Report() {
		this.con = new DataConnector().connect();
	}
	
	public ArrayList<Report> generateReport(String type) {
		ArrayList<Report> report = new ArrayList<Report>();
		setReportType(type);
		
		try {
			Statement st = this.con.createStatement();
			ResultSet rs = null;
			
			if(type.equals("day"))
				rs = st.executeQuery("SELECT TO_CHAR(last_modified, 'DD MONTH, YYYY') AS dd, count(*) FROM tickets  GROUP BY TO_CHAR(last_modified, 'DD MONTH, YYYY') HAVING TO_CHAR(last_modified, 'DD MONTH, YYYY') IS NOT NULL");
			else if(type.equals("month")) 
				rs = st.executeQuery("SELECT TO_CHAR(last_modified, 'MONTH, YYYY') AS dd, count(*) FROM tickets GROUP BY TO_CHAR(last_modified, 'MONTH, YYYY') HAVING TO_CHAR(last_modified, 'MONTH, YYYY') IS NOT NULL");
			else
				rs = st.executeQuery("SELECT TO_CHAR(last_modified, 'YYYY') AS dd, count(*) FROM tickets GROUP BY TO_CHAR(last_modified, 'YYYY') HAVING TO_CHAR(last_modified, 'YYYY') IS NOT NULL");
					
			while(rs.next()) {
				Report oneRep = new Report();
				oneRep.setReportDate(rs.getString(1));
				oneRep.setCount(rs.getInt(2));
				report.add(oneRep);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return report;

	}
	
	public String getDate() {
		/*System.out.println("grt: " + getReportType());
		if(getReportType().equals("month")) {
			
			SimpleDateFormat sdfSource = new SimpleDateFormat("MM-yyyy");
			Date dt = new Date();
		    try {
				dt = sdfSource.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		    SimpleDateFormat sdfDestination = new SimpleDateFormat("MMMM, yyyy");
		    return sdfDestination.format(dt);
		    
		}*/
		
		/*System.out.println(getReportType());*/
		
		return date;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public void setReportDate(String date) {
		this.date = date;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
}
