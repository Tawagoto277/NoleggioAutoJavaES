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
	<title>Gestionale Noleggio</title>
</head>
<body>
<% 
	ArrayList<Modello> listaModelli = Modello.getModelli();
	ArrayList<Stato> listaStati = Stato.getStati();
	ArrayList<Automobile> listaAuto = Automobile.getAutomobili();
%>
	<div class="container">
		<h1><a href="index.jsp" style="color : black; text-decoration : none">Gestione Stati Auto</a></h1>
		<jsp:include page="./includes/helloUser.jsp"></jsp:include>
	
		<jsp:include page="./includes/alert.jsp"></jsp:include>
	
		<form name="inserisciAuto" action="../inserisciAuto" method="POST">
			<div class="form-group mb-3">
				<div class="form-group">
					<label for="targaAuto">Inserisci targa nuova auto</label>
				  	<input id="targaAuto" name="targaAuto" type="text" class="form-control" placeholder="Inserisci targa nuova auto" aria-label="Inserisci targa nuova auto" required>
				</div>
			  	<div class="form-group">
			  		<label for="modelloAuto">Modelli Auto Disponibili</label>
			  		<select name="modelloAuto" id="modelloAuto" class="custom-select">
			  			<option value="-1" > - Selezione modello - </option>
			  			<% for(Modello m : listaModelli){ %>
			  			<option value="<%= m.getId() %>" ><%= m.getName() %></option>
			  			<% } %>
			  		</select>
			  	</div>
			  	<div class="form-group">
			  		<label for="statoAuto">Condizioni delle nostre auto</label>
			  		<select name="statoAuto" id="statoAuto" class="custom-select">
			  			<option value="-1" > - Selezione Condizione auto - </option>
			  			<% for(Stato s : listaStati){ %>
			  			<option value="<%= s.getId() %>" ><%= s.getName() %></option>
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

		<table class="table mt-4">
		  <thead>
		  	<tr>
		      <th scope="col">#</th>
		      <th scope="col">Modello</th>
		      <th scope="col">Stato</th>
		      <th scope="col">Targa</th>
		      <th scope="col">Responsabile</th>
		    </tr>
		  </thead>
		  <tbody>
		  	<% for(Automobile a : listaAuto){ %>
		    <tr>
		      <th scope="row"><%= a.getId() %></th>
		      <td><%= a.getModello().getName() %></td>
		      <td><%= a.getStato().getName() %></td>
		      <td><%= a.getTarga() %></td>
		      <td><%= a.getUser().getCognome() %></td>
		      <td>
		      	<a href="./dettagliAuto.jsp?id=<%= a.getId() %>" class="btn btn-primary">Modifica</a>
		      	<a href="../eliminaAuto?id=<%= a.getId() %>" class="btn btn-danger">Elimina</a>
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