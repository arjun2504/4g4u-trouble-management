package com.ticketing;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dataconnector.DataConnector;
import com.members.UserDetails;

/**
 * Servlet implementation class Dashboard
 */
@WebServlet("/Dashboard")
public class Dashboard extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Dashboard() {
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
			
			Connection con = new DataConnector().connect();
			ArrayList<TicketData> ticketList = new ArrayList<TicketData>();
			try {
				Statement st = con.createStatement();
				ResultSet rs = null;
				if(userdetail.isAdmin()) {
					rs = st.executeQuery("SELECT id FROM tickets ORDER BY created_at DESC");
				} else {
					rs = st.executeQuery("SELECT id FROM tickets WHERE user_id = " + userdetail.getID() + " ORDER BY created_at DESC");
				}
				int openCount = 0;
				int totalCount = 0;
				int pendingCount = 0;
				int closedCount = 0;
				while(rs.next()) {
					
					TicketData ticket = new TicketData(rs.getInt(1));
					
					if(ticket.getStatus().equals("open")) {
						openCount++;
					} else if(ticket.getStatus().equals("closed")) {
						closedCount++;
					} else if(ticket.getStatus().equals("awaiting")) {
						pendingCount++;
					}

					totalCount++;
					
					ticketList.add(ticket);

				}
				

				request.setAttribute("tickets", ticketList);
				request.setAttribute("totalTickets", totalCount);
				request.setAttribute("openTickets", openCount);
				request.setAttribute("pendingTickets", pendingCount);
				request.setAttribute("closedTickets", closedCount);
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			RequestDispatcher rd = request.getRequestDispatcher("Dashboard.jsp");
			rd.forward(request, response);
		}
		else {
			response.sendRedirect("member/login");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
