package servlet;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DB;

/**
 * Servlet implementation class InserimentoCitta
 */
@WebServlet("/inserimentoCitta")
public class InserimentoCitta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InserimentoCitta() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = request.getParameter("nomeCitta");
		int inserito = 0;
		
		if(nome.trim().length() == 0 || nome == null)
			response.sendRedirect(request.getContextPath() + "/app/gestione-citta.jsp?e=1");
		else
			try {
				PreparedStatement stmt = DB.getPrepareStatement("INSERT INTO citta (nome) VALUE(?)");
				stmt.setString(1, nome.trim());
				inserito = stmt.executeUpdate();
				response.sendRedirect(request.getContextPath() + "/app/gestione-citta.jsp?s=1");
			}catch(Exception e) {
				e.printStackTrace();	
				response.sendRedirect(request.getContextPath() + "/app/gestione-citta.jsp?e=1");
				return;
			}
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
