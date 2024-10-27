<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%if(request.getParameter("s") != null){%>
	<div id="avviso" class="alert alert-success">
		<p>Operazione effettuata con successo!</p>
	</div>
<%} %>

<%if(request.getParameter("e") != null){%>
	<div id="avviso" class="alert alert-danger">
		<p>Errore : Operazione non riuscita</p>
	</div>
<%} %>