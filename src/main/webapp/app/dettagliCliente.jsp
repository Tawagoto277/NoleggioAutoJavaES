<%@ page import = "anagrafica.*" %>
<%@page import = "java.util.*" %>

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
	<title>Gestionale Noleggio</title>
</head>
<body>
<% 
	int idCliente = Integer.parseInt(request.getParameter("id"));
	Cliente cliente = Cliente.getClienteById(idCliente);
	
	ArrayList<Citta> listaCitta = Citta.getCitta();
%>
	<div class="container">
		<h1><a href="index.jsp" style="color : black; text-decoration : none">Gestione Citta</a></h1>
		
		<h3>Cliente <%= cliente!=null ? cliente.getNome() : "Errore : Cliente non trovato" %></h3>
		<jsp:include page="./includes/helloUser.jsp"></jsp:include>
	
		<% if(cliente != null){ %>
			<form name="modificaCliente" action="../updateCliente?id=<%= cliente.getId() %>" method="post">
				<div class="form-group">
					<label for="nomeCliente">Nome</label>
					<input class="form-control" type="text" required name="nomeCliente" id="nomeCliente" value="<%= cliente.getNome() %>">
				</div>
				<div class="form-group">
					<label for="cognomeCliente">Cognome</label>
					<input class="form-control" type="text" required name="cognomeCliente" id="cognomeCliente" value="<%= cliente.getCognome() %>">
				</div>
				<div class="form-group">
					<label for="CFCliente">Codice Fiscale</label>
					<input class="form-control" type="text" required name="CFCliente" maxlength="16" id="CFCliente" value="<%= cliente.getCf() %>">
				</div>
				<div class="form-group">
					<label for="patCliente">Patente</label>
					<div class="form-check">
					  <input class="form-check-input" type="radio" name="patCliente" id="patentT" value="1" <%=cliente.isPatente() ? "Checked" : "" %>>
					  <label class="form-check-label" for="patentT">
					    Patentato
					  </label>
					</div>
					<div class="form-check">
					  <input class="form-check-input" type="radio" name="patCliente" id="patentF" value="0" <%=!cliente.isPatente() ? "Checked" : "" %>>
					  <label class="form-check-label" for="patentF">
					    Non Patentato
					  </label>
					</div>
				</div>
				<div class="form-group">
					<label for="EmailCliente">Email</label>
					<input class="form-control" type="text" required name="EmailCliente" id="EmailCliente" value="<%= cliente.getEmail() %>">
				</div>
				<div class="form-group">
					<label for="TelCliente">Telefono</label>
					<input class="form-control" type="text" required name="TelCliente" id="TelCliente" value="<%= cliente.getTelefono() %>">
				</div>
				<div class="form-group">
			  		<label for="cittaCliente">Citta Clienti</label>
			  		<select name="cittaCliente" id="cittaCliente" class="custom-select">
			  			<% for(Citta c : listaCitta){ %>
			  			<option value="<%= c.getId() %>" <%= c.getId() == cliente.getCitta().getId() ? "selected" : "" %>><%= c.getNome() %></option>
			  			<% } %>
			  		</select>
			  	</div>
				<input type="submit" class="btn btn-primary" value="Modifica">
			</form>
		<% } %>
	</div>

<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>
</body>
</html>