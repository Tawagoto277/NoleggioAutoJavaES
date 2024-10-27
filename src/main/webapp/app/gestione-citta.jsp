<%@ page import = "anagrafica.Citta" %>
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
	ArrayList<Citta> listaCitta = Citta.getCitta();
%>
	<div class="container">
		<h1><a href="index.jsp" style="color : black; text-decoration : none">Gestione noleggio auto</a></h1>
		<jsp:include page="./includes/helloUser.jsp"></jsp:include>
		
		<jsp:include page="./includes/alert.jsp"></jsp:include>
		
		<h3>Citta' dove operiamo (<%= listaCitta.size() %>)</h3>
		<div class="row">
			<!--inizio tabella -->
			<div class="col">
				<table class="table">
				  <thead>
				  	<tr>
				      <th scope="col">#</th>
				      <th scope="col" >Nome</th>
				    </tr>
					  </thead>
					  <tbody>
					  	<% for(Citta c: listaCitta){ %>
					    <tr>
					      <th scope="row"><%= c.getId() %></th>
					      <td><%= c.getNome() %></td>
					      <td>
					      	<a href="./dettaglioCitta.jsp?id=<%= c.getId() %>" class="btn btn-primary">Modifica</a>
					      	<a href="../eliminaCitta?id=<%= c.getId() %>" class="btn btn-danger">Elimina</a>
			      		  </td>
					    </tr>
				    	<%} %>
		  			</tbody>
				</table>
			</div>
			<!--Fine tabella -->
			<div class="col">
			<!--Inizio Form -->
				<form name="inserimento" action="../inserimentoCitta" method="POST">
					<div class="form-group">
						<label for="nomeCitta">Nome nuova citta</label>
						<input class="form-control" type="text" name="nomeCitta" id="nomeCitta" required>
					</div>
					<input class="btn btn-primary" type="submit" value="Carica">
				</form>
			<!--Fine Form -->
			</div>
		</div>
	</div>

<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>
</body>
</html>