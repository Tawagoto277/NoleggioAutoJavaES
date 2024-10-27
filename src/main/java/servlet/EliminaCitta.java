package servlet;

import java.sql.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DB;

/**
 * Servlet implementation class EliminaCitta
 */
@WebServlet("/eliminaCitta")
public class EliminaCitta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminaCitta() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub		
		String idParam = request.getParameter("id");
		if(idParam == null || idParam.trim().isEmpty() || !DB.idEsistente(Integer.parseInt(idParam), "citta", "ID_CITTA")) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID città mancante");
			response.sendRedirect(request.getContextPath() + "/app/gestione-parcoAuto.jsp?e=1");
			return;
		}
		
		int eliminato = 0;
		int id = Integer.parseInt(idParam);
		
		try {
			PreparedStatement stmt = DB.getPrepareStatement("SELECT COUNT(*) AS count FROM clienti WHERE pt_citta_cliente = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			int count = 0 ;
			if(rs.next())
				count = rs.getInt("count");
			rs.close();
			
			if(count == 0) {
				stmt = DB.getPrepareStatement("DELETE FROM citta WHERE ID_CITTA = ?");			
				stmt.setInt(1, id);
				eliminato = stmt.executeUpdate();
				response.sendRedirect(request.getContextPath() + "/app/gestione-citta.jsp?s=1");
			}else
				response.sendRedirect(request.getContextPath() + "/app/gestione-citta.jsp?e=1");
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
