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
	int idAuto = Integer.parseInt(request.getParameter("id"));
	Automobile auto = Automobile.getAutoById(idAuto);
	ArrayList<Modello> listaModelli = Modello.getModelli();
	ArrayList<Stato> listaStati = Stato.getStati();
%>
	<div class="container">
		<h1><a href="index.jsp" style="color : black; text-decoration : none">Gestione Citta</a></h1>
		
		<h3>Veicolo <%= auto!=null ? auto.getModello().getName() +" "+  auto.getTarga() : "Errore : Veicolo non trovato" %></h3>
		<jsp:include page="./includes/helloUser.jsp"></jsp:include>
		
		<% if(auto != null){ %>
			<form name="modificaAuto" action="../updateAuto?id=<%= auto.getId() %>" method="post">
				<div class="form-group">
					<label for="targaAuto">Targa</label>
					<input class="form-control" type="text" required name="targaAuto" id="targaAuto" value="<%= auto.getTarga() %>">
				</div>
				<div class="form-group">
			  		<label for="modelloAuto">Modello Veicolo</label>
			  		<select name="modelloAuto" id="modelloAuto" class="custom-select">
			  			<% for(Modello m : listaModelli){ %>
			  			<option value="<%= m.getId() %>" <%= m.getId() == auto.getModello().getId() ? "selected" : "" %>><%= m.getName() %></option>
			  			<% } %>
			  		</select>
			  	</div>
				<div class="form-group">
			  		<label for="statoAuto">Stato veicolo</label>
			  		<select name="statoAuto" id="statoAuto" class="custom-select">
			  			<% for(Stato s : listaStati){ %>
			  			<option value="<%= s.getId() %>" <%= s.getId() == auto.getStato().getId() ? "selected" : "" %>><%= s.getName() %></option>
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