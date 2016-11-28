<%-- 
    Document   : RechercheDvd
    Created on : 13 nov. 2016, 12:29:01
    Author     : aymeric
--%>

<%@page import="java.util.Set"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entity.Dvd"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Recherche de dvds</title>
    </head>
        <h1>Recherche de DVD</h1></br>
        <form method="POST" action="Controleur">
            <input type="hidden" name="action" value="interactiveResearch"/>   
            <label> Auteur : </label> <input type="text" name="auteur"/>
            <input type="submit" value="Rechercher"/>        
        </form>
        <% ArrayList<Dvd> arrayDvd = (ArrayList<Dvd>) request.getAttribute("setDvd");
            if (arrayDvd != null){
                for (Dvd dvd: arrayDvd){
                    out.print("<a href=\"Controleur?action=ajouterPanier&id=" + dvd.getId() + "\">" + dvd.toString() + "</a></br>");
                }
            }
        %>
        
        <h2>Voici les dvds !</h2>
        <% ArrayList<Dvd> list = (ArrayList<Dvd>) request.getAttribute("listeDvds");
           if (list != null){ 
                for (Dvd dvd: list){
                    out.print("<a href=\"Controleur?action=ajouterPanier&id=" + dvd.getId() + "\">" + dvd.toString() + "</a></br>");
                }
            }
        %>
</html>
