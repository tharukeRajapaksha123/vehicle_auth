package com.authsys.registration;

import java.io.IOException;
import java.sql.DriverManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

@WebServlet("/RegistrataionServerlet")
public class RegistrataionServerlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RegistrataionServerlet() {
        super();
       
    }

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get values from request by using input field name
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String mobile_number = request.getParameter("mobile_number");
		String password = request.getParameter("psw");
		RequestDispatcher dispatcher = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/auth","root","");
			PreparedStatement pst 
				= (PreparedStatement) con.prepareStatement("INSERT INTO users(email,password,mobile_number,name) VALUES(?,?,?,?)");
			pst.setString(1, email);
			pst.setString(2, password);
			pst.setString(3, mobile_number);
			pst.setString(4, username);
			int rowCount = pst.executeUpdate();
			if(rowCount >0 ) {
				request.setAttribute("status", "sucess");
				request.getSession().setAttribute("name", email);
				dispatcher = request.getRequestDispatcher("home.jsp");
				
			}else {
				request.setAttribute("status", "failed");
				dispatcher = request.getRequestDispatcher("register.jsp");
			}
		
			dispatcher.forward(request, response);
		}catch (Exception e) {
			System.out.print("Insert data int database failed " + e.getMessage());
		}
	}



	

}
