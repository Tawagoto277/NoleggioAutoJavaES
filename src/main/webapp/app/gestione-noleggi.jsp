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
	ArrayList<Cliente> listaClienti = Cliente.getClienti();
	ArrayList<Automobile> listaAuto = Automobile.getAutomobiliDisponibli();

	ArrayList<Noleggio> listaNoleggi;
	String idOperParam = request.getParameter("idOperator");
	System.out.println(idOperParam);
	
	if( idOperParam != null && !idOperParam.isEmpty()){
	 	int idOper = Integer.parseInt(idOperParam);
	 	listaNoleggi = Noleggio.getNoleggiByOperator(idOper);		
	}else{
		listaNoleggi = Noleggio.getNoleggi();
	}
%>
	<div class="container">
		<h1><a href="index.jsp" style="color : black; text-decoration : none">Gestione Noleggi</a></h1>
		
		<jsp:include page="./includes/helloUser.jsp"></jsp:include>
	
		<jsp:include page="./includes/alert.jsp"></jsp:include>
	
		<form name="inserimentoNoleggi" action="../inserimentoNoleggi" method="POST">
			<div class="form-group mb-3">
			  	<div class="form-group">
			  		<label for="cliente">Clienti</label>
			  		<select name="cliente" id="cliente" class="custom-select">
			  			<option value="-1" > - Selezione il cliente - </option>
			  			<% for(Cliente c : listaClienti){ %>
			  				<%if(c.isPatente()){ %>
			  			<option value="<%= c.getId() %>" ><%= c.getNome() %> <%= c.getCognome() %> - <%= c.getCf() %></option>
			  			<% } } %>
			  		</select>
			  	</div>
			  	<div class="form-group">
			  		<label for="auto">Vetture disponibili</label>
			  		<select name="auto" id="auto" class="custom-select">
			  			<option value="-1" > - Selezione la vettura - </option>
			  			<% for(Automobile a : listaAuto){ %>
			  			<option value="<%= a.getId() %>" ><%= a.getModello().getName() %> <%= a.getTarga() %></option>
			  			<% } %>
			  		</select>
			  	</div>
			  	<div class="form-group">
			  		<label for="dataNoleggio">Data Noleggio</label>
			  		<input class="form-control" type="date" name="dataNoleggio" required>
			  	</div>
			  	<div class="form-group">
			  		<label for="dataRestituzione">Data Restituzione</label>
			  		<input class="form-control" type="date" name="dataRestituzione" required>
			  	</div>
			  	<div class="form-group">
			  		<input class="form-control" type="hidden" name="idUserOn" value="<%= User.getUser(session).getId() %>" required>
			  	</div>
				<div class="input-group-append">
				   <button class="btn btn-outline-primary" type="submit" id="caricaStato">Carica</button>
				</div>
			</div>
		</form>
	
		<form id="operatorForm" action="./gestione-noleggi.jsp" method="GET">
		    <jsp:include page="./includes/selectOperator.jsp"></jsp:include>
		</form>

		<table class="table mt-4">
		  <thead>
		  	<tr>
		      <th scope="col">#</th>
		      <th scope="col">Operatore</th>
		      <th scope="col">Cliente</th>
		      <th scope="col">Telefono</th>
		      <th scope="col">Vettura noleggiata</th>
		      <th scope="col">Data Noleggio</th>
		      <th scope="col">Data di Restituzione prevista</th>
		      <th scope="col">Restituzione</th>
		    </tr>
		  </thead>
		  <tbody>
		  	<% 
	  			Date oggi = new Date();
	  			long millisecondiIn24Ore = 24 * 60 * 60 * 1000; //ore * minuti * secondi * millisecondi
		  		for(Noleggio n : listaNoleggi){ 
		  			String rowClass = "bg-success";
		  			
		  	      	if (n.getDateRestituzioneEffetiva() == null) { 
		  	          long differenza = n.getDataRestituzione().getTime() - oggi.getTime();
		  	          
		  	          if (differenza < 0) {
		  	            rowClass = "bg-danger";
		  	          } else if (differenza <= millisecondiIn24Ore) {
		  	            rowClass = "bg-warning";
		  	          }else{
		  	        	rowClass = "";
		  	          }
		  	        }%>
		    <tr class="<%= rowClass %>">
		      <th scope="row"><%= n.getId() %></th>
		      <td><%= n.getUser().getNome() %> <%= n.getUser().getCognome() %></td>
		      <td><%= n.getCliente().getNome() %> <%= n.getCliente().getCognome() %></td>
		      <td><%= n.getCliente().getTelefono() %></td>
		      <td><%= n.getAuto().getTarga() %> | <%= n.getAuto().getModello().getName() %></td>
		      <td><%= n.getDataNoleggio() %></td>
		      <td><%= n.getDataRestituzione() %></td>
		      <td><%if (n.getDateRestituzioneEffetiva() == null) {%>
    		 		 <a href="../restituzioneAuto?id=<%= n.getId() %>" class="btn btn-primary">Riconsegna</a>
			      <%} else {out.print(n.getDateRestituzioneEffetiva());}%>
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