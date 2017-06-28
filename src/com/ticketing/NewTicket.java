package com.ticketing;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dataconnector.DataConnector;
import com.dataconnector.Identifier;
import com.members.UserDetails;

/**
 * Servlet implementation class NewTicket
 */
@WebServlet("/NewTicket")
public class NewTicket extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewTicket() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("email") != null) {
			UserDetails userdetail = new UserDetails((String) session.getAttribute("email"));
			request.setAttribute("userdata", userdetail);
			RequestDispatcher rd = request.getRequestDispatcher("/NewTicket.jsp");
			rd.forward(request, response);
		} else {
			response.sendRedirect("../member/login?next=ticket/create");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String dept = request.getParameter("dept");
		String cat = request.getParameter("cat");
		String subcat = request.getParameter("subcat");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String descr = request.getParameter("descr");
		HttpSession session = request.getSession();
		String sessionEmail = (String) session.getAttribute("email");
		int userId = new UserDetails(sessionEmail).getID();
		Connection dc = new DataConnector().connect();
		try {
			PreparedStatement ps = dc.prepareStatement("INSERT INTO tickets (id, user_id, title, department, category, subcat, description, status, created_at, phone, email) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
			ps.setInt(1, Identifier.getNextId("tickets") );
			ps.setInt(2, userId);
			ps.setString(3, title);
			ps.setString(4, dept);
			ps.setString(5, cat);
			ps.setString(6, subcat);
			ps.setString(7, descr);
			ps.setString(8, "open");
			Calendar calendar = Calendar.getInstance();
			Date now = calendar.getTime();
			Timestamp currentTimestamp = new Timestamp(now.getTime());
			ps.setTimestamp(9, currentTimestamp);
			//ps.setTimestamp(10, currentTimestamp);
			ps.setString(10, phone);
			ps.setString(11, email);
			int rows = ps.executeUpdate();
			
			if(rows > 0) {
				response.sendRedirect("../dashboard");
			} else {
				response.sendRedirect("/ticket/create");
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
