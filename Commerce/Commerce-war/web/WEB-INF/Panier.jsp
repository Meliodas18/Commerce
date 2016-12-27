<%-- 
    Document   : Panier
    Created on : 6 nov. 2016, 11:58:29
    Author     : aymeric
--%>

<%@page import="java.util.HashMap"%>
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
                if (!confirm("Voulez-vous confirmer la commande ?")) { 
                    document.location.href="http://localhost:8080/Commerce-war/ControleurClients?action=pagePanier";
                } else {
                     document.location.href="http://localhost:8080/Commerce-war/ControleurClients?action=confirmOrder&ok=Terminer la commande";
                }
            }
            function Cancel(){
                document.location.href="http://localhost:8080/Commerce-war/ControleurClients?action=confirmOrder&ok=Annuler";
            }
        </SCRIPT>
    </head>
    <body>
        <h1>Contenu du panier !</h1>
        <% HashMap<Dvd,Integer> contenu = (HashMap<Dvd,Integer>) request.getAttribute("panier");
           for (Dvd d : contenu.keySet()){
                out.print(d + ", Quantité : " + contenu.get(d) + "</br>");
           }
        %>
        <input onClick="ConfirmMessage()" type="button" value="Terminer la commande"/>
        <input onClick="Cancel()" type="button" value="Annuler"/>
    </body>
</html>
