package com.members;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class EditProfile
 */
@WebServlet("/EditProfile")
public class EditProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession session = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditProfile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.session = request.getSession();
		if(this.session.getAttribute("email") != null) {
			String email = (String) this.session.getAttribute("email");
			UserDetails userdata = new UserDetails(email);
			request.setAttribute("userdata", userdata);
			
			RequestDispatcher rd = request.getRequestDispatcher("/EditProfile.jsp");
			rd.forward(request, response);
		} else {
			response.sendRedirect("../member/login?next=profile/edit");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.session = request.getSession();
		if(this.session.getAttribute("email") != null) {
			String fname = request.getParameter("fname");
			String lname = request.getParameter("lname");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			String sessionEmail = (String) this.session.getAttribute("email");
			
			UserDetails ud = new UserDetails(sessionEmail);
			int userId = ud.getID();
			
			UserDetails edited = new UserDetails(ud.getID());
			int flag = 0;
			if(!email.equals(sessionEmail)) {
				UserDetails ud1 = new UserDetails(email);
				if(ud1.getFirstName() == null) {
					//new email is not present, hence we can go for next step
					this.session.setAttribute("email", email);
					flag = edited.updateProfile(fname, lname, phone, email);
				}
			} else if(email.equals(sessionEmail)) {
				flag = edited.updateProfile(fname, lname, phone, email);
			}
			UserDetails newDetails = new UserDetails((String) this.session.getAttribute("email"));
			request.setAttribute("userdata", newDetails);
			
			if(flag > 0) {
				request.setAttribute("flag", 1);
			} else {
				request.setAttribute("flag", 0);
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("../EditProfile.jsp");
			rd.forward(request, response);
		}
		else {
			response.sendRedirect("../member/login?next=profile/edit");
		}
		
	}

}
