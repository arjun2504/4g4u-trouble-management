package com.members;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ListIterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ticketing.TicketData;

/**
 * Servlet implementation class Profile
 */
@WebServlet("/Profile")
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Profile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession hs = request.getSession();
		String email = (String) hs.getAttribute("email");
		String prof = request.getParameter("id");		
		if(email != null) {
			UserDetails userdata = null;
			userdata = new UserDetails(email);
			request.setAttribute("userdata", userdata);
			ArrayList<TicketData> allTickets = new ArrayList<TicketData>();
			
			if(request.getParameter("id") != null) {
				int userId = Integer.parseInt( (String) request.getParameter("id") );
				UserDetails profileData = new UserDetails( userId );
				allTickets = TicketData.getTickets( userId );
				request.setAttribute("profile", profileData);
			}
			else {
				allTickets = TicketData.getTickets(userdata.getID());
				request.setAttribute("profile", userdata);
			}
				
			request.setAttribute("tickets", allTickets);
			int totalCount = 0, openCount = 0, pendingCount = 0, closedCount = 0;
			ListIterator<TicketData> li = allTickets.listIterator();
			while(li.hasNext()) {
				TicketData td = li.next();
				if(td.getStatus().equals("open")) {
					openCount++;
				} else if(td.getStatus().equals("awaiting")) {
					pendingCount++;
				} else {
					closedCount++;
				}
			}
			
			request.setAttribute("openCount", openCount);
			request.setAttribute("pendingCount", pendingCount);
			request.setAttribute("closedCount", closedCount);
			
			RequestDispatcher rd = request.getRequestDispatcher("Profile.jsp");
			rd.forward(request, response);
		} else {
			if(prof != null)
				response.sendRedirect("member/login?next=profile?id=" + prof);
			else
				response.sendRedirect("member/login?next=profile");
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
