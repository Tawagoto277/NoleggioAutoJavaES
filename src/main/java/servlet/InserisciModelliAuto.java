package servlet;

import java.io.IOException;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DB;

/**
 * Servlet implementation class InserisciStati
 */
@WebServlet("/inserisciModelli")
public class InserisciModelliAuto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InserisciModelliAuto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = request.getParameter("nomeModello");
		int inserimento = 0;
		
		if(nome.trim().length() == 0 || nome == null) {
			response.sendRedirect(request.getContextPath() + "/app/gestione-modelli.jsp?e=1");
			return;
		}else {
			try {
				PreparedStatement stmt = DB.getPrepareStatement("INSERT INTO modello (nome) VALUE(?)");
				stmt.setString(1, nome.trim());
				inserimento = stmt.executeUpdate();
				response.sendRedirect(request.getContextPath() + "/app/gestione-modelli.jsp?s=1");
			}catch(Exception e) {
				e.printStackTrace();	
				response.sendRedirect(request.getContextPath() + "/app/gestione-modelli.jsp?e=1");
				return;
			}
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
