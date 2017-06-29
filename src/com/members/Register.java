package com.members;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dataconnector.DataConnector;
import com.dataconnector.Identifier;


/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/Register.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String first_name = request.getParameter("fname");
		String last_name = request.getParameter("lname");
		String email = request.getParameter("email");
		String password1 = request.getParameter("password");
		String password2 = request.getParameter("newpassword");
		String phone = request.getParameter("phone");
		String encryptedPassword = PasswordEncrypter.encrypt(password2);
		
		DataConnector dc = new DataConnector();
		Connection con = dc.connect();
		
		try {
			int newId = Identifier.getNextId("users");
			PreparedStatement ps = con.prepareStatement("INSERT INTO users (id, first_name, last_name, email, password, phone, is_admin) VALUES (?,?,?,?,?,?,?)");
			ps.setInt(1, newId);
			ps.setString(2, first_name);
			ps.setString(3, last_name);
			ps.setString(4, email);
			ps.setString(5, encryptedPassword);
			ps.setString(6, phone);
			ps.setInt(7, 0);
			int rows = ps.executeUpdate();
			System.out.println(rows);
			if(rows != 0) {
				response.sendRedirect("login");
			} else {
				response.sendRedirect("register");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
