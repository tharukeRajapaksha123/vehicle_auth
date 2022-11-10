package com.authsys.registration;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

/**
 * Servlet implementation class LoginServerlet
 */
@WebServlet("/LoginServerlet")
public class LoginServerlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get email and password from request
		String email  = request.getParameter("email");
		String password  = request.getParameter("psw");
		
		//create session object to save user login status in local storage
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher = null;
		try {
			//create sql connection
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/auth","root","");
			PreparedStatement pst =(PreparedStatement) con.prepareStatement("select * from users where email = ? and password = ?");
			pst.setString(1, email);
			pst.setString(2, password);
			
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				System.out.print("Login sucess");
				session.setAttribute("name", rs.getString("email"));
				dispatcher = request.getRequestDispatcher("home.jsp");
			}else {
				System.out.print("Login failed");
				request.setAttribute("status", "failed");
				dispatcher = request.getRequestDispatcher("index.jsp");
			}
			dispatcher.forward(request, response);
		}catch (Exception err) {
			System.out.print(err.getMessage());
		}
	}

}
