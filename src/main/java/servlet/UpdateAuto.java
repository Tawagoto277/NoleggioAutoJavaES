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
 * Servlet implementation class UpdateAuto
 */
@WebServlet("/updateAuto")
public class UpdateAuto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateAuto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 		// TODO Auto-generated method stub
    	int idAuto = Integer.parseInt(request.getParameter("id"));
    	
    	String targa = request.getParameter("targaAuto");
		int modello = Integer.parseInt(request.getParameter("modelloAuto"));
		int stato = Integer.parseInt(request.getParameter("statoAuto"));
 		
 		int ins = 0;
 		
 		if(targa == null || targa.trim().length() == 0 || modello <= 0 || stato <= 0 || !DB.idEsistente(idAuto, "automobile", "ID_AUTOMOBILE")) {
 			response.sendRedirect(request.getContextPath() + "/app/gestione-parcoAuto.jsp?e=1");
 			return ;
 		}else {
 			try {
 				PreparedStatement stmt = DB.getPrepareStatement(
 					    "UPDATE automobile " +
 					    "SET targa = ?, pt_modello_automobile = ? , pt_stato_automobile = ? " +
 					    "WHERE ID_AUTOMOBILE = ?"
 					);

				stmt.setString(1, targa.trim());
				stmt.setInt(2, modello);
				stmt.setInt(3, stato);
				stmt.setInt(4, idAuto);
 				
 				ins = stmt.executeUpdate();
 				response.sendRedirect(request.getContextPath() + "/app/gestione-parcoAuto.jsp?s=1");
 			}catch(Exception e) {
 				response.sendRedirect(request.getContextPath() + "/app/gestione-parcoAuto.jsp?e=1");
 				e.printStackTrace();
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
