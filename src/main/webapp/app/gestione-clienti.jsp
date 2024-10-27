<%@ page import = "anagrafica.*" %>
<%@page import = "java.util.*" %>
<%@page import = "auth.User" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
	
	<title>Gestionale Clienti</title>
</head>
<body>
<% 
	ArrayList<Citta> listaCitta = Citta.getCitta();
	ArrayList<Cliente> listaClienti;
	
	String idOperParam = request.getParameter("idOperator");
	System.out.println(idOperParam);
	
	if( idOperParam != null && !idOperParam.isEmpty()){
	 	int idOper = Integer.parseInt(idOperParam);
	 	listaClienti = Cliente.getClientiByOperator(idOper);		
	}else{
		listaClienti = Cliente.getClienti();
	}
%>
	<div class="container">
		<h1><a href="index.jsp" style="color : black; text-decoration : none">Gestione Clienti</a></h1>
		<jsp:include page="./includes/helloUser.jsp"></jsp:include>
	
		<jsp:include page="./includes/alert.jsp"></jsp:include>
	
		<form name="inserimentoClienti" action="../inserimentoClienti" method="POST">
			<div class="form-group mb-3">
				<div class="form-group">
					<label for="nomeCliente">Inserisci Nome Cliente</label>
				  	<input id="nomeCliente" name="nomeCliente" type="text" class="form-control" placeholder="Inserisci nome nuovo cliente" required>
				</div>
				<div class="form-group">
					<label for="cognomeCliente">Inserisci Cognome Cliente</label>
				  	<input id="cognomeCliente" name="cognomeCliente" type="text" class="form-control" placeholder="Inserisci cognome nuovo cliente" required>
				</div>
				<div class="form-group">
					<label for="CFCliente">Inserisci CF Cliente</label>
				  	<input id="CFCliente" name="CFCliente" type="text" class="form-control" maxlength="16" placeholder="Inserisci codice fiscale nuovo cliente" required>
				</div>
				<div class="form-group">
					<label for="patCliente">Cliente Patentato</label>
					<div class="form-check">
					  <input class="form-check-input" type="radio" name="patCliente" id="patentT" value="1" checked>
					  <label class="form-check-label" for="patentT">
					    Patentato
					  </label>
					</div>
					<div class="form-check">
					  <input class="form-check-input" type="radio" name="patCliente" id="patentF" value="0">
					  <label class="form-check-label" for="patentF">
					    Non Patentato
					  </label>
					</div>
				</div>
				<div class="form-group">
					<label for="emailC">Inserisci Email Cliente</label>
				  	<input id="emailC" name="emailC" type="email" class="form-control" placeholder="Inserisci email nuovo cliente" required>
				</div>
				<div class="form-group">
					<label for="telCliente">Inserisci Telefono Cliente</label>
				  	<input id="telCliente" name="telCliente" type="text" class="form-control" placeholder="Inserisci numero di telefono del nuovo cliente" required>
				</div>
			  	<div class="form-group">
			  		<label for="cittaCliente">Citta Clienti</label>
			  		<select name="cittaCliente" id="cittaCliente" class="custom-select">
			  			<option value="-1" > - Selezione la citta del noleggio - </option>
			  			<% for(Citta c : listaCitta){ %>
			  			<option value="<%= c.getId() %>" ><%= c.getNome() %></option>
			  			<% } %>
			  		</select>
			  	</div>
			  	<div class="form-group">
			  		<input class="form-control" type="hidden" name="idUserOn" value="<%= User.getUser(session).getId() %>" required>
			  	</div>
				 <div class="input-group-append">
				   <button class="btn btn-outline-primary" type="submit" id="caricaStato">Carica</button>
				 </div>
			</div>
		</form>
		
		<form id="operatorForm" action="./gestione-clienti.jsp" method="GET">
			<jsp:include page="./includes/selectOperator.jsp"></jsp:include>
		</form>

		<table class="table mt-4">
		  <thead>
		  	<tr>
		      <th scope="col">#</th>
		      <th scope="col">Nome</th>
		      <th scope="col">Cognome</th>
		      <th scope="col">Codice Fiscale</th>
		      <th scope="col">Email</th>
		      <th scope="col">Telefono</th>
		      <th scope="col">Responsabile</th>
		    </tr>
		  </thead>
		  <tbody>
		  	<% for(Cliente c : listaClienti){ %>
		    <tr>
		      <th scope="row"><%= c.getId() %></th>
		      <td><%= c.getNome() %></td>
		      <td><%= c.getCognome() %></td>
		      <td><%= c.getCf() %></td>
		      <td><%= c.getEmail() %></td>
		      <td><%= c.getTelefono() %></td>
		      <td><%= c.getUser().getCognome() %></td>
		      <td>
		      	<a href="./dettaglioClienteNoleggio.jsp?id=<%= c.getId() %>" class="btn btn-primary">Dettagli</a>
		      	<a href="./dettagliCliente.jsp?id=<%= c.getId() %>" class="btn btn-primary">Modifica</a>
		      	<a href="../eliminaCliente?id=<%= c.getId() %>" class="btn btn-danger">Elimina</a>
	      	  </td>
		    </tr>
	    	<%} %>
 			</tbody>
		</table>
	</div>



<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>
</body>
</html>