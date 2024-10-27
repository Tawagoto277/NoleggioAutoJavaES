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
	ArrayList<Noleggio> listaNoleggi = Noleggio.getNoleggiForCliente(idCliente);
%>
	<div class="container">
		<h1><a href="index.jsp" style="color : black; text-decoration : none">Dettagli Cliente</a></h1>
		
		<h3>Cliente <%= cliente!=null ? cliente.getNome() + " " + cliente.getCognome() : "Errore : Cliente non trovato" %></h3>
		<jsp:include page="./includes/helloUser.jsp"></jsp:include>
		<jsp:include page="./includes/alert.jsp"></jsp:include>
	
		<% if(cliente != null){ %>
			<p><%= cliente.getCf() %></p>
			<p><%= cliente.getTelefono() %></p>
			<p><%= cliente.getEmail() %></p>
		<% } %>
	</div>

	<div class="container">
		<table class="table mt-4">
		  <thead>
		  	<tr>
		      <th scope="col">#</th>
		      <th scope="col">Vettura noleggiata</th>
		      <th scope="col">Data Noleggio</th>
		      <th scope="col">Data di Restituzione prevista</th>
		      <th scope="col">Data di Restituzione effetiva</th>
		    </tr>
		  </thead>
 			<tbody>
			  <%for(Noleggio n : listaNoleggi){ %>
			  <tr>
				  <th scope="row"><%= n.getId() %></th>
				  <td><%= n.getAuto().getModello().getName() %> | <%= n.getAuto().getTarga() %></td>
			      <td><%= n.getDataNoleggio() %></td>
			      <td><%= n.getDataRestituzione() %></td>
			      <td><%if (n.getDateRestituzioneEffetiva() == null) {%>
					        <a href="../restituzioneAuto?id=<%= n.getId() %>" class="btn btn-primary">Restituisci</a>
					    <%} else {out.print(n.getDateRestituzioneEffetiva());}%>
			      </td>
			  </tr>
	  		  <% } %>
			</tbody>
		</table>
	</div>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>
</body>
</html>