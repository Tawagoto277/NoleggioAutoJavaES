package anagrafica;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import auth.User;
import db.DB;

public class Cliente {

	private int id;
	private String nome, cognome, cf;
	private boolean patente;
	private String email, telefono;
	private Citta citta;
	private User user;
	
	public Cliente (String n, String c, String cf, boolean p, String e, String t, User u) {
		this.nome = n;
		this.cognome = c;
		this.cf = cf;
		this.patente = p;
		this.email = e;
		this.telefono = t;
		this.user = u;
	}

	public Cliente (int id, String n, String c, String cf, boolean p, String e, String t, Citta citta, User u) {
		this(n, c, cf, p, e, t, u);
		this.id = id;
		this.citta = citta;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getCf() {
		return cf;
	}

	public void setCf(String cf) {
		this.cf = cf;
	}

	public boolean isPatente() {
		return patente;
	}

	public void setPatente(boolean patente) {
		this.patente = patente;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String emial) {
		this.email = emial;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Citta getCitta() {
		return citta;
	}

	public void setCitta(Citta citta) {
		this.citta = citta;
	}

	public int getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public static Cliente getClienteById(int id) throws Exception {
		if(id > 0 && DB.idEsistente(id, "clienti", "ID_CLIENTE")) {
			PreparedStatement stmt = DB.getPrepareStatement("SELECT * FROM clienti"
					+ " INNER JOIN citta on clienti.pt_citta_cliente = ID_CITTA"
					+ " INNER JOIN user ON clienti.pt_clienti_user = ID_USER"
					+ " WHERE ID_CLIENTE = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				return new Cliente(
						rs.getInt("ID_CLIENTE"), 
						rs.getString("nome"),
						rs.getString("cognome"),
						rs.getString("cfiscale"),
						rs.getInt("patente") == 1 ? true : false,
						rs.getString("email"),
						rs.getString("telefono"),
						new Citta(rs.getInt("id_citta"), rs.getString("nome")),
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
	
	public static ArrayList<Cliente> getClientiByOperator(int id) throws Exception {
		if(id > 0 && DB.idEsistente(id, "USER", "ID_USER")) {
			ArrayList<Cliente> listaClienti = new ArrayList<>();
			
			PreparedStatement stmt = DB.getPrepareStatement("SELECT * FROM clienti"
					+ " INNER JOIN citta on clienti.pt_citta_cliente = ID_CITTA"
					+ " INNER JOIN user ON clienti.pt_clienti_user = ID_USER"
					+ " WHERE ID_USER = ?");
			
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				listaClienti.add(new Cliente(
									rs.getInt("ID_CLIENTE"), 
									rs.getString("nome"),
									rs.getString("cognome"),
									rs.getString("cfiscale"),
									rs.getInt("patente") == 1 ? true : false,
											rs.getString("email"),
											rs.getString("telefono"),
											new Citta(rs.getInt("id_citta"), rs.getString("nome")),
											new User(rs.getInt("ID_USER"),
													rs.getString("user.email"),
													rs.getDate("data_registrazione"),
													rs.getString("user.nome"),
													rs.getString("user.cognome")))
								);
			}
			rs.close();
			return listaClienti;
		}
		return null;
	}
	
	public static ArrayList<Cliente> getClienti() throws Exception {
		ArrayList<Cliente> listaClienti = new ArrayList<>();
		ResultSet rs = null;
		Statement st = DB.getStmt();
		rs = st.executeQuery("SELECT * FROM clienti "
				+ "INNER JOIN citta on clienti.pt_citta_cliente = ID_CITTA "
				+ "INNER JOIN user ON clienti.pt_clienti_user = ID_USER");
		
		while(rs.next()) {
			listaClienti.add(new Cliente(
								rs.getInt("ID_CLIENTE"), 
								rs.getString("nome"),
								rs.getString("cognome"),
								rs.getString("cfiscale"),
								rs.getInt("patente") == 1 ? true : false,
								rs.getString("email"),
								rs.getString("telefono"),
								new Citta(rs.getInt("id_citta"), rs.getString("nome")),
								new User(rs.getInt("ID_USER"),
										rs.getString("user.email"),
										rs.getDate("data_registrazione"),
										rs.getString("user.nome"),
										rs.getString("user.cognome")))
							);
		}
		rs.close();
		return listaClienti;
	}
}
