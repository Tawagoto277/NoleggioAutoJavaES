package anagrafica;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import db.DB;

public class Stato {

	private int id;
	private String name;
	
	public Stato(String n) {
		this.name = n;
	}
	
	public Stato(int id, String n) {
		this(n);
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}
	
	public static Stato getStatoById(int id) throws Exception{
		ResultSet rs = null;
		
		if(id > 0 && DB.idEsistente(id, "stati", "ID_STATO")) {
			PreparedStatement stmt = DB.getPrepareStatement("SELECT * FROM stati WHERE ID_STATO = ?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				return new Stato(rs.getInt("ID_STATO"), rs.getString("Nome"));
			}
		}
		rs.close();
		return null;
	}
	
	public static ArrayList<Stato> getStati() throws Exception {
		ResultSet rs = null;
		ArrayList<Stato> listaStato = new ArrayList<>();
		
		Statement st = DB.getStmt();
		
		rs = st.executeQuery("SELECT * FROM stati");
		
		while(rs.next()) {
			listaStato.add(new Stato(rs.getInt("ID_STATO"), rs.getString("nome")));
		}
		
		return listaStato;
	}
	
}
