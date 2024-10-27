<%@ page import = "anagrafica.Stato" %>
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
	ArrayList<Stato> listaStati = Stato.getStati();
%>
	<div class="container">
		<h1><a href="index.jsp" style="color : black; text-decoration : none">Gestione Stati Auto</a></h1>
		<jsp:include page="./includes/helloUser.jsp"></jsp:include>
	
		<jsp:include page="./includes/alert.jsp"></jsp:include>
	
		<form name="inserisciStati" action="../inserisciStati" method="POST">
			<div class="input-group mb-3">
			  <input id="nomeStato" name="nomeStato" type="text" class="form-control" placeholder="Inserisci nuovo stato" aria-label="Inserisci nuovo stato" required>
			  
			  <div class="input-group-append">
			    <button class="btn btn-outline-primary" type="submit" id="caricaStato">Carica</button>
			  </div>
			  
			</div>
		</form>

		<table class="table mt-4">
		  <thead>
		  	<tr>
		      <th scope="col">#</th>
		      <th scope="col" colspan="2">Nome</th>
		    </tr>
			  </thead>
			  <tbody>
			  	<% for(Stato s : listaStati){ %>
			    <tr>
			      <th scope="row"><%= s.getId() %></th>
			      <td><%= s.getName() %></td>
			      <td>
			      	<a href="./dettaglioStato.jsp?id=<%= s.getId() %>" class="btn btn-primary">Modifica</a>
			      	<a href="../eliminaStato?id=<%= s.getId() %>" class="btn btn-danger">Elimina</a>
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