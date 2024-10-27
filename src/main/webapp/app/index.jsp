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

	<div class="container">
		<h1>Gestione noleggio auto</h1>
		<p>Benvenuto/a <jsp:include page="./includes/helloUser.jsp"></jsp:include></p>
		<p>cosa vuoi fare?</p>
		<ul class="list-group">
	  		<li class="list-group-item"><a href="gestione-noleggi.jsp">Gestione Noleggi</a></li>
		  	<li class="list-group-item"><a href="gestione-parcoAuto.jsp">Gestione parco Auto</a></li>
		  	<li class="list-group-item"><a href="gestione-clienti.jsp">Gestione Clienti</a></li>
		  	<li class="list-group-item"><a href="gestione-statoAuto.jsp">Gestione Stati</a></li>
		  	<li class="list-group-item"><a href="gestione-modelli.jsp">Gestione Modelli</a></li>
	  		<li class="list-group-item"><a href="gestione-citta.jsp">Gestione Citta</a></li>
	  		<li class="list-group-item"><a href="../register.jsp">Registra addetto</a></li>
	  		<li class="list-group-item"><a href="../logout.jsp">Esci</a></li>
		</ul>
	</div>
	
	

<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>
</body>
</html>