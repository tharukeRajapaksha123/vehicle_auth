

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

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
 * Servlet implementation class DeleteUserServerlet
 */
@WebServlet("/DeleteUserServerlet")
public class DeleteUserServerlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = String.valueOf(request.getSession().getAttribute("name"));
		//delete user from db
		String query = "DELETE FROM users WHERE email = ?";
		try {
			//create sql connection
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/auth","root","");
			PreparedStatement pst =(PreparedStatement) con.prepareStatement(query);
			pst.setString(1, email);
			HttpSession session = request.getSession();
			RequestDispatcher dispatcher = null;
			
			int rs = pst.executeUpdate();
			if(rs > 0) {
				System.out.print("Delte user sucess");
				session.setAttribute("name", null);
				dispatcher = request.getRequestDispatcher("index.jsp");
			}
			dispatcher.forward(request, response);
		}catch (Exception err) {
			System.out.print(err.getMessage());
		}
		
		
		
	}

}
