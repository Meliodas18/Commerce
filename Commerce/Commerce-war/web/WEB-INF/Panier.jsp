<%-- 
    Document   : Panier
    Created on : 6 nov. 2016, 11:58:29
    Author     : aymeric
--%>

<%@page import="entity.Dvd"%>
<%@page import="java.util.LinkedList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Panier</title>
         <SCRIPT language=javascript>
   function ConfirmMessage() {
       if (confirm("Voulez-vous confirmer la commande ?")) { 
           document.bgColor="silver";
       }
   }
        </SCRIPT>
    </head>
    <body>
        <h1>Contenu du panier !</h1>
        <% LinkedList<Dvd> contenu = (LinkedList<Dvd>) request.getAttribute("panier");
           for (Dvd d : contenu){
                out.print(d + "</br>");
           }
        %>
        <form method="GET" action="Controleur">
            <input type="hidden" name="action" value="confirmOrder"/>
            <input type="submit" name="ok" value="Terminer la commande" onClick="ConfirmMessage()"/>
            <input type="submit" name="ok" value="Annuler" />
        </form>
    </body>
</html>
