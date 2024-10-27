package servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DB;

/**
 * Servlet implementation class InserimentoNoleggi
 */
@WebServlet("/inserimentoNoleggi")
public class InserimentoNoleggi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InserimentoNoleggi() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	int clienteID = Integer.parseInt(request.getParameter("cliente"));
        int autoID = Integer.parseInt(request.getParameter("auto"));
        int userID = Integer.parseInt(request.getParameter("idUserOn"));
		
        String dataNoleggio = request.getParameter("dataNoleggio");
        String dataRes= request.getParameter("dataRestituzione");
        
		int ins = 0;
		int insUp = 0;
		
		if(dataNoleggio == null || dataNoleggio.trim().length() == 0 || 
				dataRes == null || dataRes.trim().length() == 0 || 
				clienteID <= 0 || autoID <= 0 || userID <= 0) {
			response.sendRedirect(request.getContextPath() + "/app/gestione-noleggi.jsp?e=1");
			return ;
		}else {			
			try {
			    PreparedStatement stmt = DB.getPrepareStatement("INSERT INTO noleggi"
			    		+ "(`pt_noleggi_cliente`, `pt_noleggi_automobili`, `pt_noleggi_user`, `data_noleggio`, `data_restituzione`) "
			    		+ "VALUES (?, ?, ?, ?, ?)");
			    stmt.setInt(1, clienteID);
			    stmt.setInt(2, autoID);
			    stmt.setInt(3, userID);
			    stmt.setDate(4, Date.valueOf(dataNoleggio));
			    stmt.setDate(5, Date.valueOf(dataRes));			    
			    
			    ins = stmt.executeUpdate();
			    if(ins > 0) {
			    	PreparedStatement stmtUp = DB.getPrepareStatement(
			    			"UPDATE automobile SET disponibile = 0 WHERE ID_AUTOMOBILE = ?");
			    	stmtUp.setInt(1, autoID);
			    	insUp = stmtUp.executeUpdate();
			    }
			    
			    response.sendRedirect(request.getContextPath() + "/app/gestione-noleggi.jsp?s=1");
				
			} catch (Exception e) {
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
