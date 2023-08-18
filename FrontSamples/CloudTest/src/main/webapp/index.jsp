<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
<%
String name = request.getParameter("name");
String address = request.getParameter("address");

if(name != null && address != null){
	String json = "{";
	json += "name:" + name.toUpperCase() + ", ";
	json += "address:" + address.toLowerCase();
	json += "}";
	
	out.println(json);
}
%>