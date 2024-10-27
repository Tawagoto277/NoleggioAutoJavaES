package auth;

import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.DB;

/**
 * Servlet implementation class LoginUser
 */
@WebServlet("/loginUser")
public class LoginUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = request.getParameter("emailUser");
		String pwd = request.getParameter("passwordUser");
		
		int ins = 0;
		
		if(email == null || 
				email.trim().length() == 0 || 
				pwd == null || pwd.trim().length() == 0 || 
				pwd.trim().length() <= 8) {
			response.sendRedirect(request.getContextPath() + "/login.jsp?e=1");
		}else {
			try {
				PreparedStatement stmt = DB.getPrepareStatement("SELECT * FROM user WHERE email = ? AND password = ?");
				
				stmt.setString(1, email);
				stmt.setString(2, Authentication.MD5(pwd));
				
				ResultSet rs = stmt.executeQuery();
				
				if(rs.next()) {
					User user = new User(
							rs.getInt("ID_USER"),
							rs.getString("email"),
							rs.getDate("data_registrazione"),
							rs.getString("nome"),
							rs.getString("cognome"));
					
					HttpSession session = request.getSession();
					session.setAttribute(User.sessionKey, user);
					
					response.sendRedirect(request.getContextPath() + "/app");					
				}else {
					response.sendRedirect(request.getContextPath() + "/login.jsp?");
				}
					
			} catch (Exception e) {
				response.sendRedirect(request.getContextPath() + "/login.jsp?e=1");
				e.printStackTrace();
				System.out.println("Errore : " + e);
				return ;
			}
		}
	}

}
