package servlet;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DB;

/**
 * Servlet implementation class EliminaSatato
 */
@WebServlet("/eliminaModello")
public class EliminaModello extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminaModello() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idParam = request.getParameter("id");
		if(idParam == null || idParam.trim().isEmpty()) {
			response.sendRedirect(request.getContextPath() + "/app/gestione-modelli.jsp?e=1");
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID modello mancante");
			return;
		}
		
		int eliminato = 0;
		int id = Integer.parseInt(idParam);
		
		try {
			PreparedStatement stmt = DB.getPrepareStatement("SELECT COUNT(*) AS count FROM automobile WHERE pt_modello_automobile = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			int count = 0;
			if(rs.next())
				count = rs.getInt("count");
			rs.close();
			
			if(count == 0) {
				stmt = DB.getPrepareStatement("DELETE FROM modello WHERE ID_MODELLO = ?");			
				stmt.setInt(1, id);
				eliminato = stmt.executeUpdate();			
				response.sendRedirect(request.getContextPath() + "/app/gestione-modelli.jsp?s=1");			
			}else {
				response.sendRedirect(request.getContextPath() + "/app/gestione-modelli.jsp?e=1");
			}
			
		}catch(Exception e) {
			response.sendRedirect(request.getContextPath() + "/app/gestione-modelli.jsp?e=1");
			e.printStackTrace();
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
