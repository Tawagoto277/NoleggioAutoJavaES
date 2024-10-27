package db;

import java.sql.*;

public class DB {

	private static Connection connection;
	
	public static Connection getDB() throws Exception {
		
		//pattern singleton, se la connessione non esiste la crea
		if(connection == null) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/noleggio",
					"root",
					"");
		};
		
		//restituisce la connessine
		return connection;
	};
	
	//metodo per ottenere uno statement 
	public static Statement getStmt() throws Exception {
		return getDB().createStatement();
	};
	
	//metodo per creare un prepared statement
	public static PreparedStatement getPrepareStatement (String query) throws Exception {
		return getDB().prepareStatement(query);
	};
	
	public static boolean idEsistente(int id, String nomeTb, String nomeColonnaId) {
		boolean esiste = false;
		
		try {
			PreparedStatement stmt = DB.getPrepareStatement("SELECT COUNT(*) AS count FROM " + nomeTb + " WHERE " + nomeColonnaId + " = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next())
                esiste = rs.getInt(1) > 0;
                
            rs.close();
		}catch(Exception e) {
			e.printStackTrace();
			return esiste;
		}
		return esiste;
	}
}
