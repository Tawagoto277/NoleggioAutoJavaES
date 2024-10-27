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
 * Servlet implementation class UpdateCliente
 */
@WebServlet("/updateCliente")
public class UpdateCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCliente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 		// TODO Auto-generated method stub
		String nome = request.getParameter("nomeCliente");
		String cognome = request.getParameter("cognomeCliente");
		String cf = request.getParameter("CFCliente");
		String email = request.getParameter("EmailCliente");
		String cel = request.getParameter("TelCliente");
		
		int idCliente = Integer.parseInt(request.getParameter("id"));
        int patente = safeParseInt(request.getParameter("patCliente"));
        int citta = safeParseInt(request.getParameter("cittaCliente"));
 		
 		int ins = 0;
 		
 		if(isInvalidParam(nome) || 
				isInvalidParam(cognome) ||
				isInvalidParam(cf) || cf.trim().length() != 16 ||
				isInvalidParam(email) ||
				isInvalidParam(cel) || 
				patente <= 0 || citta <= 0 || idCliente <= 0 || !DB.idEsistente(idCliente, "clienti", "ID_CLIENTE")) {
 			response.sendRedirect(request.getContextPath() + "/app/gestione-clienti.jsp?e=1");
 			return ;
 		}else {
 			try {
 				PreparedStatement stmt = DB.getPrepareStatement(
 					    "UPDATE clienti " +
 					    "SET nome = ?, cognome = ?, cfiscale = ?, patente = ?, email = ?, telefono = ?, pt_citta_cliente = ? " +
 					    "WHERE ID_CLIENTE = ?"
 					);

				stmt.setString(1, nome.trim());
				stmt.setString(2, cognome.trim());
				stmt.setString(3, cf.trim().toUpperCase());
				stmt.setInt(4, patente);
				stmt.setString(5, email.trim());
				stmt.setString(6, cel.trim());
				stmt.setInt(7, citta);
				stmt.setInt(8, idCliente);
 				
 				ins = stmt.executeUpdate();
 				response.sendRedirect(request.getContextPath() + "/app/gestione-clienti.jsp?s=1");
 			}catch(Exception e) {
 				response.sendRedirect(request.getContextPath() + "/app/gestione-clienti.jsp?e=1");
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

	private boolean isInvalidParam(String param) {
		return param == null || param.trim().length() <= 0; 
	}
	
	private int safeParseInt(String param) {
        try {
            return Integer.parseInt(param);
        } catch (NumberFormatException | NullPointerException e) {
            return -1;
        }
    }
}
