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
 * Servlet implementation class UpdateStato
 */
@WebServlet("/updateStato")
public class UpdateStato extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateStato() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nomeStato = request.getParameter("nomeStato");
		int idStato = Integer.parseInt(request.getParameter("id"));
		
		int ins = 0;
		
		//bisognerebbe controllare che id esiste nel db
		if(nomeStato.trim().length() == 0 || idStato <= 0 || !DB.idEsistente(idStato, "stati", "ID_STATO")) {
			response.sendRedirect(request.getContextPath() + "/app/gestione-statoAuto.jsp?e=1");
			return ;
		}else {
			try {
				PreparedStatement stmt = DB.getPrepareStatement("UPDATE stati SET Nome = ? WHERE ID_STATO = ?");
				
				stmt.setString(1, nomeStato);
				stmt.setInt(2, idStato);
				
				ins = stmt.executeUpdate();
				response.sendRedirect(request.getContextPath() + "/app/gestione-statoAuto.jsp?s=1");
			}catch(Exception e) {
				e.printStackTrace();
				response.sendRedirect(request.getContextPath() + "/app/gestione-statoAuto.jsp?e=1");
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
