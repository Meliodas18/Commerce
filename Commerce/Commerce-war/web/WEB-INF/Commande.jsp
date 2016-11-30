<%-- 
    Document   : Commande
    Created on : 6 nov. 2016, 11:59:38
    Author     : aymeric
--%>

<%@page import="entity.Commande"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Commandes</title>
    </head>
    <body>
        <h1>Commandes en Stock</h1>
         <% List<Commande> contenu = (List<Commande>) request.getAttribute("listCommandes");
           for (Commande c : contenu){
                out.print(c + "</br>");
           }
         %>
    </body>
</html>
