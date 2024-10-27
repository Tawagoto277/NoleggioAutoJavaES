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
	
	<title>Gestionale Clienti</title>
</head>
<body>
	<div class="container">
		<h1><a href="login.jsp" style="color : black; text-decoration : none">Gestione Noleggi Registrazioni</a></h1>
	
		<jsp:include page="app/includes/alert.jsp"></jsp:include>
	
		<form name="registerUser" action="./registerUser" method="POST">
			<div class="form-group mb-3">
			  	<div class="form-group">
					<label for="nomeUser">Inserisci Nome</label>
				  	<input id="nomeUser" name="nomeUser" type="text" class="form-control" placeholder="Nome User" required>
				</div>
				<div class="form-group">
					<label for="cognomeUser">Inserisci Cognome</label>
				  	<input id="cognomeUser" name="cognomeUser" type="text" class="form-control" placeholder="Cognome User"  required>
				</div>
			  	<div class="form-group">
					<label for="emailUser">Inserisci Email</label>
				  	<input id="emailUser" name="emailUser" type="email" class="form-control" placeholder="Email" required>
				</div>
			  	<div class="form-group">
					<label for= "passwordUser">Inserisci Password</label>
				  	<input id="passwordUser" name="passwordUser" type="password" minlength="8" class="form-control" placeholder="*****" required>
					<small class="form-text text-muted">
		                La password deve essere di almeno 8 caratteri.
		            </small>
				</div>
				<div class="input-group-append">
				   <button class="btn btn-primary mr-3" type="submit" id="registaUser">Registrati</button>
				   <a href="./login.jsp" class="btn btn-outline-primary" id="accediUser">Accedi</a>
				 </div>
			</div>
		</form>
	</div>



<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>
</body>
</html>