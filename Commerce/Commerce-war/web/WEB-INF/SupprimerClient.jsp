<%-- 
    Document   : SupprimerClient
    Created on : 25 nov. 2016, 18:53:04
    Author     : huang
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="entity.Client"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% ArrayList<Client> list = (ArrayList<Client>) request.getAttribute("listeClient");
            for (Client client: list){
                out.print("<a href=\"Controleur?action=supprimerLeClient&id=" + client.getId() + "\">" + client.toString() + "</a></br>");
            }
        %>
    </body>
</html>
