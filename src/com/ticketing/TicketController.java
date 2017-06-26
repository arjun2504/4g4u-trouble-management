package com.ticketing;

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
 * Servlet implementation class TicketController
 */
@WebServlet("/TicketController")
public class TicketController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TicketController() {
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
			
			int ticketId = Integer.parseInt(request.getParameter("id"));
			TicketData ticket = new TicketData(ticketId);
			request.setAttribute("ticket", ticket);
			
			RequestDispatcher rd = request.getRequestDispatcher("Ticket.jsp");
			rd.forward(request, response);
		} else {
			response.sendRedirect("member/login");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int ticketId = Integer.parseInt(request.getParameter("tid"));
		StatusSwitcher ss = new StatusSwitcher(ticketId);
		
		if( request.getParameter("confirmation") != null && request.getParameter("confirm").equals("1") ) {
			ss.toAwaiting();
			
		} else if( request.getParameter("feedback") != null ) {
		
			int rating = Integer.parseInt( request.getParameter("rating"));
			String comments = request.getParameter("comments");
			System.out.println(ss.setFeedback(rating, comments));
			ss.toClosed();
			
		}
		
		doGet(request, response);
		
	}

}
