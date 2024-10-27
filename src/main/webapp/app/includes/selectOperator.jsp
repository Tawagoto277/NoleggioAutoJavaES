<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import = "auth.User" %>
<%@page import = "java.util.*" %>
<%	ArrayList<User> listaOper = User.getOperatori(); %>


    <label for="operatorSelect">Seleziona Operatore:</label>
    <select id="operatorSelect" name="idOperator" class="custom-select">
        <option value="">Tutti gli operatori</option>
        <%for(User s : listaOper){ %>
        <option value="<%= s.getId() %>"><%=s.getNome() %> <%= s.getCognome() %></option>
        <%} %>
    </select>
    <div class="input-group-append mt-2">
   		<button class="btn btn-outline-primary" type="submit" id="selectOp">Seleziona Operatore</button>
    </div>
