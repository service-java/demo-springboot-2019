<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
session.setAttribute("information","first(data) -:session-> second(data)");
response.sendRedirect("redirect-second.jsp");
%>
