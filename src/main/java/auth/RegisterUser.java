package auth;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DB;

/**
 * Servlet implementation class RegisterUser
 */
@WebServlet("/registerUser")
public class RegisterUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterUser() {
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

		String nome = request.getParameter("nomeUser");
		String cognome = request.getParameter("cognomeUser");
		String email = request.getParameter("emailUser");
		String pwd = request.getParameter("passwordUser");
		
		int ins = 0;
		
		if(email == null || 
				email.trim().length() == 0 || 
				nome == null || nome.trim().length() == 0 || 
				cognome == null || cognome.trim().length() == 0 || 
				pwd == null || pwd.trim().length() == 0 || 
				pwd.trim().length() <= 8) {
			response.sendRedirect(request.getContextPath() + "/register.jsp?e=1");
			System.out.println("Errore controllo");
		}else {
			try {
				PreparedStatement stmt = DB.getPrepareStatement("SELECT ID_USER FROM user WHERE email = ?");
				stmt.setString(1, email);
				ResultSet rs = stmt.executeQuery();
				
				if(!rs.next()) {
					PreparedStatement stmtIns = DB.getPrepareStatement("INSERT INTO user (Nome, Cognome, email, password, data_registrazione) "
							+ "VALUES(?, ?, ?, ?, ?)");
					
					stmtIns.setString(1, nome);
					stmtIns.setString(2, cognome);
					stmtIns.setString(3, email);
					stmtIns.setString(4, Authentication.MD5(pwd));
					stmtIns.setDate(5, Date.valueOf(LocalDate.now()));
					
					ins = stmtIns.executeUpdate();
					
					if(ins!=1) {
						System.out.println("Errore 12");
						response.sendRedirect(request.getContextPath() + "/register.jsp?e=1");
						throw new Exception();
					}
					response.sendRedirect(request.getContextPath() + "/login.jsp?s=1");

				}else {
					System.out.println("Gia registrato");
					response.sendRedirect(request.getContextPath() + "/login.jsp?");
				}
					
			} catch (Exception e) {
				response.sendRedirect(request.getContextPath() + "/register.jsp?e=1");
				e.printStackTrace();
				System.out.println("Errore : " + e);
				return ;
			}
		}
	}
}
