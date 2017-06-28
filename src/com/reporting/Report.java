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
	int count, id, diffDays;
	String department, category, subcat, status, email, title;
	long phone;
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	String reportType;

	Report() {
		this.con = new DataConnector().connect();
	}
	
	public ArrayList<Report> slnViolationReport() {
		ArrayList<Report> report = new ArrayList<Report>();
		
		try {
			Statement st = this.con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM (SELECT id, title, department, category, subcat, status, email, phone, TO_DATE(TO_CHAR(last_modified,'DD-MM-YYYY'), 'DD-MM-YYYY') - TO_DATE(TO_CHAR(created_at,'DD-MM-YYYY'), 'DD-MM-YYYY') AS diff1 FROM tickets WHERE last_modified IS NOT NULL) m FULL JOIN (SELECT id, title, department, category, subcat, status, email, phone, TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'), 'DD-MM-YYYY') - TO_DATE(TO_CHAR(created_at,'DD-MM-YYYY'), 'DD-MM-YYYY') AS diff2 FROM tickets WHERE last_modified IS NULL) n ON m.id = n.id");
			
			while(rs.next()) {
				Report slnReport = new Report();
				
				if(rs.getString(1) != null) {
					slnReport.setId(rs.getInt(1));
					slnReport.setTitle(rs.getString(2));
					slnReport.setDepartment(rs.getString(3));
					slnReport.setCategory(rs.getString(4));
					slnReport.setSubcat(rs.getString(5));
					slnReport.setStatus(rs.getString(6));
					slnReport.setEmail(rs.getString(7));
					slnReport.setPhone(rs.getLong(8));
					slnReport.setDiffDays(rs.getInt(9));
				} else {
					slnReport.setId(rs.getInt(10));
					slnReport.setTitle(rs.getString(11));
					slnReport.setDepartment(rs.getString(12));
					slnReport.setCategory(rs.getString(13));
					slnReport.setSubcat(rs.getString(14));
					slnReport.setStatus(rs.getString(15));
					slnReport.setEmail(rs.getString(16));
					slnReport.setPhone(rs.getLong(17));
					slnReport.setDiffDays(rs.getInt(18));
				}
				
				if(slnReport.getDiffDays() > 2)
				report.add(slnReport);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		return report;
	}
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getSubcat() {
		return subcat;
	}

	public void setSubcat(String subcat) {
		this.subcat = subcat;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public int getDiffDays() {
		return diffDays;
	}

	public void setDiffDays(int diffDays) {
		this.diffDays = diffDays;
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
