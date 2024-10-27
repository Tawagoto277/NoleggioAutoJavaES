package anagrafica;

import java.sql.*;
import java.util.*;

import db.DB;

public class Citta {

	private int id;
	private String nome;
	
	public Citta(String nome) {
		this.nome = nome;
	}
	
	public Citta(int id, String nome) {
		this(nome);
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getId() {
		return id;
	}
	
	public static Citta getCittaById(int id) throws Exception{
		ResultSet rs = null;
		
		if(id > 0) {
			PreparedStatement stmt = DB.getPrepareStatement("SELECT * FROM citta WHERE ID_CITTA = ?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				return new Citta(rs.getInt("ID_CITTA"), rs.getString("Nome"));
			}
		}
		return null;
	}
	
	public static ArrayList<Citta> getCitta() throws Exception {
		ResultSet rs = null;
		ArrayList<Citta> listaCitta = new ArrayList<>();
		
		Statement st = DB.getStmt();
		
		rs = st.executeQuery("SELECT * FROM citta");
		
		while(rs.next()) {
			listaCitta.add(new Citta(rs.getInt("ID_CITTA"), rs.getString("nome")));
		}
		
		return listaCitta;
	}
}
