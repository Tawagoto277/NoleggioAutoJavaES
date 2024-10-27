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
 * Servlet implementation class UpdateCitta
 */
@WebServlet("/updateCitta")
public class UpdateCitta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCitta() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nomeCitta = request.getParameter("nomeCitta");
		int idCitta = Integer.parseInt(request.getParameter("id"));
		
		int ins = 0;
		
		//bisognerebbe controllare che id esiste nel db
		if(nomeCitta.trim().length() == 0 || idCitta <= 0 || !DB.idEsistente(idCitta, "citta", "ID_CITTA")) {
			response.sendRedirect(request.getContextPath() + "/app/gestione-citta.jsp?e=1");
			return ;
		}else {
			try {
				PreparedStatement stmt = DB.getPrepareStatement("UPDATE citta SET Nome = ? WHERE ID_CITTA = ?");
				
				stmt.setString(1, nomeCitta);
				stmt.setInt(2, idCitta);
				
				ins = stmt.executeUpdate();
				response.sendRedirect(request.getContextPath() + "/app/gestione-citta.jsp?s=1");
			}catch(Exception e) {
				e.printStackTrace();
				response.sendRedirect(request.getContextPath() + "/app/gestione-citta.jsp?e=1");
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
