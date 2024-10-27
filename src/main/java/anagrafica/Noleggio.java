package anagrafica;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import auth.User;
import db.DB;

public class Noleggio {

	private int id;
	private Cliente cliente;
	private Automobile auto;
	private User user;
	private Date dataNoleggio;
	private Date dataRestituzione;
	private Date dateRestituzioneEffetiva;
	
	public Noleggio(Cliente c, Automobile a, User u, Date dataN, Date dataR) {
		this.cliente = c;
		this.auto = a;
		this.user = u;
		this.dataNoleggio = dataN;
		this.dataRestituzione = dataR;
	}

	public Noleggio(Cliente c, Automobile a, User u, Date dataN, Date dataR, Date dataRE) {
		this(c, a, u, dataN, dataR);
		this.dateRestituzioneEffetiva = dataRE;
	}

	public Noleggio(int id, Cliente c, Automobile a, User u, Date dataN, Date dataR, Date dataRE) {
		this(c, a, u, dataN, dataR, dataRE);
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Automobile getAuto() {
		return auto;
	}

	public void setAuto(Automobile auto) {
		this.auto = auto;
	}

	public Date getDataNoleggio() {
		return dataNoleggio;
	}

	public void setDataNoleggio(Date dataNoleggio) {
		this.dataNoleggio = dataNoleggio;
	}

	public Date getDataRestituzione() {
		return dataRestituzione;
	}

	public void setDataRestituzione(Date dataRestituzione) {
		this.dataRestituzione = dataRestituzione;
	}

	public int getId() {
		return id;
	}
	
	public Date getDateRestituzioneEffetiva() {
		return dateRestituzioneEffetiva;
	}

	public void setDateRestituzioneEffetiva(Date dateRestituzioneEffetiva) {
		this.dateRestituzioneEffetiva = dateRestituzioneEffetiva;
	}
	
	public static ArrayList<Noleggio> getNoleggiForCliente(int idCliente) throws Exception {
		ResultSet rs = null;
		ArrayList<Noleggio> listaNoleggi = new ArrayList<>();
		
		if(idCliente > 0 && DB.idEsistente(idCliente, "clienti", "ID_CLIENTE")){
			
			PreparedStatement st = DB.getPrepareStatement("SELECT * FROM noleggi "
					+ "INNER JOIN clienti ON noleggi.pt_noleggi_cliente = ID_CLIENTE "
					+ "INNER JOIN citta ON clienti.pt_citta_cliente = ID_CITTA "
					+ "INNER JOIN automobile ON noleggi.pt_noleggi_automobili = ID_AUTOMOBILE "
					+ "INNER JOIN user ON noleggi.pt_noleggi_user = ID_USER "
					+ "INNER JOIN modello ON automobile.pt_modello_automobile = ID_MODELLO "
					+ "INNER JOIN stati ON automobile.pt_stato_automobile = ID_STATO "
					+ "WHERE ID_CLIENTE = ?");
			
			st.setInt(1, idCliente);
			rs = st.executeQuery();
			
			while(rs.next()) {
				listaNoleggi.add(new Noleggio (rs.getInt("ID_NOLEGGI"),
								new Cliente(
										rs.getInt("ID_CLIENTE"), 
										rs.getString("nome"), 
										rs.getString("cognome"),
										rs.getString("cfiscale"), 
										rs.getInt("patente") == 1,
										rs.getString("email"),
										rs.getString("telefono"),
										new Citta(rs.getInt("ID_CITTA"), rs.getString("citta.nome")),
										new User(
												rs.getInt("ID_USER"),
												rs.getString("user.email"),
												rs.getDate("data_registrazione"),
												rs.getString("user.nome"),
												rs.getString("user.cognome"))),
								new Automobile (
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
										),
								new User(
										rs.getInt("ID_USER"),
										rs.getString("user.email"),
										rs.getDate("data_registrazione"),
										rs.getString("user.nome"),
										rs.getString("user.cognome")),
								rs.getDate("data_noleggio"),
								rs.getDate("data_restituzione"),
								rs.getDate("data_restituzione_effettiva")));
			}
			
			return listaNoleggi;
		}
		return null;
	}

	public static ArrayList<Noleggio> getNoleggiForAuto(int idAuto) throws Exception {
		ResultSet rs = null;
		ArrayList<Noleggio> listaNoleggi = new ArrayList<>();
		
		if(idAuto > 0 && DB.idEsistente(idAuto, "automobile", "ID_AUTOMOBILE")){
			
			PreparedStatement st = DB.getPrepareStatement("SELECT * FROM noleggi "
					+ "INNER JOIN clienti ON noleggi.pt_noleggi_cliente = ID_CLIENTE "
					+ "INNER JOIN citta ON clienti.pt_citta_cliente = ID_CITTA "
					+ "INNER JOIN automobile ON noleggi.pt_noleggi_automobili = ID_AUTOMOBILE "
					+ "INNER JOIN user ON noleggi.pt_noleggi_user = ID_USER "
					+ "INNER JOIN modello ON automobile.pt_modello_automobile = ID_MODELLO "
					+ "INNER JOIN stati ON automobile.pt_stato_automobile = ID_STATO "
					+ "WHERE ID_AUTOMOBILE= ?");
			
			st.setInt(1, idAuto);
			rs = st.executeQuery();
			
			while(rs.next()) {
				listaNoleggi.add(new Noleggio (rs.getInt("ID_NOLEGGI"),
						new Cliente(
								rs.getInt("ID_CLIENTE"), 
								rs.getString("nome"), 
								rs.getString("cognome"),
								rs.getString("cfiscale"), 
								rs.getInt("patente") == 1,
								rs.getString("email"),
								rs.getString("telefono"),
								new Citta(rs.getInt("ID_CITTA"), rs.getString("citta.nome")),
								new User(
										rs.getInt("ID_USER"),
										rs.getString("user.email"),
										rs.getDate("data_registrazione"),
										rs.getString("user.nome"),
										rs.getString("user.cognome"))),
						new Automobile (
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
								),
						new User(
								rs.getInt("ID_USER"),
								rs.getString("user.email"),
								rs.getDate("data_registrazione"),
								rs.getString("user.nome"),
								rs.getString("user.cognome")),
						rs.getDate("data_noleggio"),
						rs.getDate("data_restituzione"),
						rs.getDate("data_restituzione_effettiva")));
			}
			
			return listaNoleggi;
		}
		return null;
	}

	public static Noleggio getNoleggiById(int idNoleggio) throws Exception {
		if(idNoleggio > 0 && DB.idEsistente(idNoleggio, "noleggi", "ID_NOLEGGI")){
			
			PreparedStatement st = DB.getPrepareStatement("SELECT * FROM noleggi "
					+ "INNER JOIN clienti ON noleggi.pt_noleggi_cliente = ID_CLIENTE "
					+ "INNER JOIN citta ON clienti.pt_citta_cliente = ID_CITTA "
					+ "INNER JOIN automobile ON noleggi.pt_noleggi_automobili = ID_AUTOMOBILE "
					+ "INNER JOIN user ON noleggi.pt_noleggi_user = ID_USER "
					+ "INNER JOIN modello ON automobile.pt_modello_automobile = ID_MODELLO "
					+ "INNER JOIN stati ON automobile.pt_stato_automobile = ID_STATO "
					+ "WHERE ID_NOLEGGI = ?");
			
			st.setInt(1, idNoleggio);
			ResultSet rs = st.executeQuery();
			
			if(rs.next()) {
				return new Noleggio (rs.getInt("ID_NOLEGGI"),
						new Cliente(
								rs.getInt("ID_CLIENTE"), 
								rs.getString("nome"), 
								rs.getString("cognome"),
								rs.getString("cfiscale"), 
								rs.getInt("patente") == 1,
								rs.getString("email"),
								rs.getString("telefono"),
								new Citta(rs.getInt("ID_CITTA"), rs.getString("citta.nome")),
								new User(
										rs.getInt("ID_USER"),
										rs.getString("user.email"),
										rs.getDate("data_registrazione"),
										rs.getString("user.nome"),
										rs.getString("user.cognome"))),
						new Automobile (
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
								),
						new User(
								rs.getInt("ID_USER"),
								rs.getString("user.email"),
								rs.getDate("data_registrazione"),
								rs.getString("user.nome"),
								rs.getString("user.cognome")),
						rs.getDate("data_noleggio"),
						rs.getDate("data_restituzione"),
						rs.getDate("data_restituzione_effettiva"));
			}
			rs.close();
		}
		return null;
	}

	public static ArrayList<Noleggio> getNoleggiByOperator(int idOperator) throws Exception {
		
		ResultSet rs = null;
		ArrayList<Noleggio> listaNoleggi = new ArrayList<>();
		
		if(idOperator > 0 && DB.idEsistente(idOperator, "user", "ID_USER")){
			
			PreparedStatement st = DB.getPrepareStatement("SELECT * FROM noleggi "
					+ "INNER JOIN clienti ON noleggi.pt_noleggi_cliente = ID_CLIENTE "
					+ "INNER JOIN citta ON clienti.pt_citta_cliente = ID_CITTA "
					+ "INNER JOIN automobile ON noleggi.pt_noleggi_automobili = ID_AUTOMOBILE "
					+ "INNER JOIN user ON noleggi.pt_noleggi_user = ID_USER "
					+ "INNER JOIN modello ON automobile.pt_modello_automobile = ID_MODELLO "
					+ "INNER JOIN stati ON automobile.pt_stato_automobile = ID_STATO "
					+ "WHERE ID_USER = ?");
			
			st.setInt(1, idOperator);
			rs = st.executeQuery();
			
			while(rs.next()) {
				listaNoleggi.add(new Noleggio (rs.getInt("ID_NOLEGGI"),
								new Cliente(
										rs.getInt("ID_CLIENTE"), 
										rs.getString("nome"), 
										rs.getString("cognome"),
										rs.getString("cfiscale"), 
										rs.getInt("patente") == 1,
										rs.getString("email"),
										rs.getString("telefono"),
										new Citta(rs.getInt("ID_CITTA"), rs.getString("citta.nome")),
										new User(
												rs.getInt("ID_USER"),
												rs.getString("user.email"),
												rs.getDate("data_registrazione"),
												rs.getString("user.nome"),
												rs.getString("user.cognome"))),
								new Automobile (
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
										),
								new User(
										rs.getInt("ID_USER"),
										rs.getString("user.email"),
										rs.getDate("data_registrazione"),
										rs.getString("user.nome"),
										rs.getString("user.cognome")),
								rs.getDate("data_noleggio"),
								rs.getDate("data_restituzione"),
								rs.getDate("data_restituzione_effettiva")));
			}
			return listaNoleggi;
		}
		return null;
	}
	
	public static ArrayList<Noleggio> getNoleggi() throws Exception {
		ResultSet rs = null;
		ArrayList<Noleggio> listaNoleggi = new ArrayList<>();
		
		Statement st = DB.getStmt();
		
		rs = st.executeQuery("SELECT * FROM noleggi "
				+ "INNER JOIN clienti ON noleggi.pt_noleggi_cliente = ID_CLIENTE "
				+ "INNER JOIN citta ON clienti.pt_citta_cliente = ID_CITTA "
				+ "INNER JOIN automobile ON noleggi.pt_noleggi_automobili = ID_AUTOMOBILE "
				+ "INNER JOIN user ON noleggi.pt_noleggi_user = ID_USER "
				+ "INNER JOIN modello ON automobile.pt_modello_automobile = ID_MODELLO "
				+ "INNER JOIN stati ON automobile.pt_stato_automobile = ID_STATO ");
		
		while(rs.next()) {
			listaNoleggi.add(new Noleggio (rs.getInt("ID_NOLEGGI"),
							new Cliente(
									rs.getInt("ID_CLIENTE"), 
									rs.getString("nome"), 
									rs.getString("cognome"),
									rs.getString("cfiscale"), 
									rs.getInt("patente") == 1,
									rs.getString("email"),
									rs.getString("telefono"),
									new Citta(rs.getInt("ID_CITTA"), rs.getString("citta.nome")),
									new User(
											rs.getInt("ID_USER"),
											rs.getString("user.email"),
											rs.getDate("data_registrazione"),
											rs.getString("user.nome"),
											rs.getString("user.cognome"))),
							new Automobile (
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
									),
							new User(
									rs.getInt("ID_USER"),
									rs.getString("user.email"),
									rs.getDate("data_registrazione"),
									rs.getString("user.nome"),
									rs.getString("user.cognome")),
							rs.getDate("data_noleggio"),
							rs.getDate("data_restituzione"),
							rs.getDate("data_restituzione_effettiva")));
		}
		
		return listaNoleggi;
	}

	public User getUser() {
		return user;
	}

}
