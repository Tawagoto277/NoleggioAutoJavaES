<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import = "auth.User" %>
    
<p>Responsabile : <%= User.getUser(session).getNome() %> <%= User.getUser(session).getCognome() %></p>