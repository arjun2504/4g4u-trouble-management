package com.reporting;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.members.UserDetails;

/**
 * Servlet implementation class ReportController
 */
@WebServlet("/ReportController")
public class ReportController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if( session.getAttribute("email") != null) {
			UserDetails ud = new UserDetails((String) session.getAttribute("email"));
			if(ud.isAdmin()) {
				request.setAttribute("userdata", ud);
				RequestDispatcher rd = request.getRequestDispatcher("Report.jsp");
				rd.forward(request, response);
			} else {
				response.sendRedirect("dashboard");
			}
		} else {
			response.sendRedirect("member/login?next=report");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String rtype = request.getParameter("reporttype");
		
		Report report = new Report();
		
		if(rtype.equals("day") || rtype.equals("month") || rtype.equals("year")) {
			request.setAttribute("reportList", report.generateReport(rtype) );
		} else if(rtype.equals("slnviolation")) {
			request.setAttribute("slnreport", report.slnViolationReport() );
		}
		
		doGet(request, response);
	}

}
