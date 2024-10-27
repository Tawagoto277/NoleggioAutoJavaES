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
 * Servlet implementation class UpdateModelli
 */
@WebServlet("/updateModelli")
public class UpdateModelli extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateModelli() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nomeModello = request.getParameter("nomeModello");
		int idModello = Integer.parseInt(request.getParameter("id"));
		
		int ins = 0;
		
		//bisognerebbe controllare che id esiste nel db
		if(nomeModello == null || 
				nomeModello.trim().length() == 0 || 
				idModello <= 0 || !DB.idEsistente(idModello, "modello", "ID_MODELLO")) {
			response.sendRedirect(request.getContextPath() + "/app/gesione-modelli.jsp?e=1");
			return ;
		}else {
			try {
				PreparedStatement stmt = DB.getPrepareStatement("UPDATE modello SET Nome = ? WHERE ID_MODELLO = ?");
				
				stmt.setString(1, nomeModello);
				stmt.setInt(2, idModello);
				
				ins = stmt.executeUpdate();
				response.sendRedirect(request.getContextPath() + "/app/gestione-modelli.jsp?s=1");
			}catch(Exception e) {
				e.printStackTrace();
				response.sendRedirect(request.getContextPath() + "/app/gesione-modelli.jsp?e=1");
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
