package anagrafica;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import auth.User;
import db.DB;

public class Automobile {

	private int id;
	private Modello modello;
	private Stato stato;
	private User user;
	private String targa;
	
	public Automobile(Modello m, Stato s, String t, User u) {
		this.modello = m;
		this.stato = s;
		this.targa = t;
		this.user = u;
	}

	public Automobile(int id, Modello m, Stato s, String t, User u) {
		this(m,s,t, u);
		this.id = id;
	}

	public Modello getModello() {
		return modello;
	}

	public void setModello(Modello modello) {
		this.modello = modello;
	}

	public Stato getStato() {
		return stato ;
	}

	public void setStato(Stato stato) {
		this.stato = stato;
	}

	public String getTarga() {
		return targa;
	}


	public void setTarga(String targa) {
		this.targa = targa;
	}

	public int getId() {
		return id;
	}
	
	public static Automobile getAutoById(int id) throws Exception{
		
		if(id > 0 && DB.idEsistente(id, "automobile", "ID_AUTOMOBILE")) {
			PreparedStatement stmt = DB.getPrepareStatement("SELECT * FROM automobile"
					+ " INNER JOIN modello ON automobile.pt_modello_automobile = ID_MODELLO"
					+ " INNER JOIN stati ON automobile.pt_stato_automobile = ID_STATO"
					+ " INNER JOIN user ON automobile.pt_user_automobile = ID_USER"
					+ " WHERE ID_AUTOMOBILE = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				return new Automobile (
						rs.getInt("ID_AUTOMOBILE"), 
						new Modello(rs.getInt("ID_MODELLO"), rs.getString("nome")), 
						new Stato(rs.getInt("ID_STATO"), rs.getString("nome")), 
						rs.getString("targa"),
						new User(
								rs.getInt("ID_USER"),
								rs.getString("user.email"),
								rs.getDate("data_registrazione"),
								rs.getString("user.nome"),
								rs.getString("user.cognome"))
						);
			}
			rs.close();
		}
		return null;
	}
	
	public static ArrayList<Automobile> getAutomobiliDisponibli() throws Exception {
		ResultSet rs = null;
		ArrayList<Automobile> listaAuto = new ArrayList<>();
		
		Statement st = DB.getStmt();
		
		rs = st.executeQuery("SELECT * FROM automobile"
				+ " INNER JOIN modello on automobile.pt_modello_automobile = ID_MODELLO "
				+ " INNER JOIN stati on automobile.pt_stato_automobile = ID_STATO"
				+ " INNER JOIN user ON automobile.pt_user_automobile = ID_USER"
				+ " WHERE automobile.disponibile = 1");
		
		while(rs.next()) {
			listaAuto.add(new Automobile (
					rs.getInt("ID_AUTOMOBILE"), 
					new Modello(rs.getInt("ID_MODELLO"), rs.getString("nome")), 
					new Stato(rs.getInt("ID_STATO"), rs.getString("nome")), 
					rs.getString("targa"),
					new User(
							rs.getInt("ID_USER"),
							rs.getString("user.email"),
							rs.getDate("data_registrazione"),
							rs.getString("user.nome"),
							rs.getString("user.cognome"))
					));
		}
		
		return listaAuto;
	}

	public static ArrayList<Automobile> getAutomobili() throws Exception {
		ResultSet rs = null;
		ArrayList<Automobile> listaAuto = new ArrayList<>();
		
		Statement st = DB.getStmt();
		
		rs = st.executeQuery("SELECT * FROM automobile"
				+ " INNER JOIN modello on automobile.pt_modello_automobile = ID_MODELLO "
				+ " INNER JOIN stati on automobile.pt_stato_automobile = ID_STATO"
				+ " INNER JOIN user ON automobile.pt_user_automobile = ID_USER");
		
		while(rs.next()) {
			listaAuto.add(new Automobile (
					rs.getInt("ID_AUTOMOBILE"), 
					new Modello(rs.getInt("ID_MODELLO"), rs.getString("modello.nome")), 
					new Stato(rs.getInt("ID_STATO"), rs.getString("stati.nome")), 
					rs.getString("targa"),
					new User(
							rs.getInt("ID_USER"),
							rs.getString("user.email"),
							rs.getDate("data_registrazione"),
							rs.getString("user.nome"),
							rs.getString("user.cognome"))
					));
		}
		
		return listaAuto;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
