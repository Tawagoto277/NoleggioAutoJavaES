package servlet;

import java.sql.Date;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import anagrafica.Noleggio;
import db.DB;

/**
 * Servlet implementation class RestituzioneAuto
 */
@WebServlet("/restituzioneAuto")
public class RestituzioneAuto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RestituzioneAuto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int idNoleggio = Integer.parseInt(request.getParameter("id"));
		int ins = 0;
 		
 		if(idNoleggio <= 0 || !DB.idEsistente(idNoleggio, "noleggi", "ID_NOLEGGI")) {
 			response.sendRedirect(request.getContextPath() + "/app/gestione-noleggi.jsp?e=1");
 			return ;
 		}else {
 			try {
 				Noleggio noleggio = Noleggio.getNoleggiById(idNoleggio); 				
 				
 				PreparedStatement stmt = DB.getPrepareStatement(
 						"UPDATE noleggi SET data_restituzione_effettiva = ? WHERE ID_NOLEGGI = ?"
 					);

 				stmt.setDate(1, Date.valueOf(LocalDate.now()));
 				stmt.setInt(2, noleggio.getId());
				ins = stmt.executeUpdate();
 				
				if(ins > 0) {
					stmt = DB.getPrepareStatement("UPDATE automobile SET disponibile = 1 WHERE ID_AUTOMOBILE = ?");
					
					stmt.setInt(1, noleggio.getAuto().getId());
					ins = stmt.executeUpdate();
					
					response.sendRedirect(request.getContextPath() + "/app/gestione-noleggi.jsp?s=1");
				}				
 			}catch(Exception e) {
 				response.sendRedirect(request.getContextPath() + "/app/gestione-noleggi.jsp?e=1");
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
