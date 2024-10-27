package anagrafica;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import db.DB;

public class Modello {

	int id;
	String name;
	
	public Modello(String n) {
		this.name = n;
	}
	
	public Modello(int id, String n) {
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
	
	public static Modello getModelloById(int id) throws Exception{
		ResultSet rs = null;		
		
		if(id > 0) {
			PreparedStatement stmt = DB.getPrepareStatement("SELECT * FROM modello WHERE ID_MODELLO = ?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			
			if(rs.next())
				return new Modello(rs.getInt("ID_MODELLO"), rs.getString("Nome"));
		}
		return null;
	}
	
	public static ArrayList<Modello> getModelli() throws Exception {
		ResultSet rs = null;
		ArrayList<Modello> listaModelli = new ArrayList<>();
		
		Statement st = DB.getStmt();
		
		rs = st.executeQuery("SELECT * FROM modello");
		
		while(rs.next()) {
			listaModelli.add(new Modello(rs.getInt("ID_MODELLO"), rs.getString("nome")));
		}
		
		return listaModelli;
	}
}
