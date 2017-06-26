package com.members;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dataconnector.DataConnector;
/**
 * Servlet implementation class MembersRedirect
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	
	HttpSession session;
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
    }

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd;
		session = req.getSession();
		
		if(session.getAttribute("email") != null) {
			resp.sendRedirect("../dashboard");
		} else {
			rd = req.getRequestDispatcher("/Login.jsp");
			rd.forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = PasswordEncrypter.encrypt(req.getParameter("password"));
		
		if(authenticate(email, password)) {
			session = req.getSession();
			session.setAttribute("email", email);
			resp.sendRedirect("../dashboard");
		} else {
			resp.sendRedirect("login");
		}
	}

	private boolean authenticate(String email, String password) {
		Connection con = new DataConnector().connect();
		PreparedStatement st;
		boolean isValid = false;
		try {
			st = con.prepareStatement("SELECT id FROM users WHERE email=? AND password=?");
			st.setString(1, email);
			st.setString(2, password);
			ResultSet rs = st.executeQuery();
			rs.last();
			int numOfRows = rs.getRow();
			isValid = (numOfRows > 0) ? true : false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return isValid;
	}
}
