package auth;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import anagrafica.Automobile;
import anagrafica.Citta;
import anagrafica.Cliente;
import anagrafica.Modello;
import anagrafica.Noleggio;
import anagrafica.Stato;
import db.DB;

public class User {

	static String sessionKey = "USERToken";
	private int id;
	private String nome;
	private String cognome;
	private String email;
	private Date dataReg;
	
	public User(String e, Date data, String n, String c) {
		this.email = e;
		this.dataReg = data;
		this.nome = n;
		this.cognome = c;
	}

	public User(int id, String e, Date data, String n, String c) {
		this(e, data, n, c);
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDataReg() {
		return dataReg;
	}

	public void setDataReg(Date dataReg) {
		this.dataReg = dataReg;
	}

	public int getId() {
		return id;
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
	
	public static User getUser(HttpSession session) {
		try {
			User u = (User)session.getAttribute(sessionKey);
			return u;
		}catch(ClassCastException e) {
			return null;
		}
	}
	
	public static boolean isLogged(HttpSession session) {
		return getUser(session)!=null;
	}

	public static ArrayList<User> getOperatori() throws Exception {
		ResultSet rs = null;
		ArrayList<User> listaOperatori = new ArrayList<>();
		
		Statement st = DB.getStmt();
		
		rs = st.executeQuery("SELECT * FROM user");
		
		while(rs.next()) {
			listaOperatori.add(new User(
									rs.getInt("ID_USER"),
									rs.getString("user.email"),
									rs.getDate("data_registrazione"),
									rs.getString("user.nome"),
									rs.getString("user.cognome"))
							);
		}
		
		return listaOperatori;
	}
}
