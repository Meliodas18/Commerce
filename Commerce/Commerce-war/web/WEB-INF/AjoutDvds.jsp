<%-- 
    Document   : AjoutDvds
    Created on : 12 nov. 2016, 22:23:32
    Author     : aymeric
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ajout de dvds</title>
    </head>
    <body>
        <h1>Ajouter des dvds !</h1>
        <form method="POST" action="Controleur">
            <input type="hidden" name="action" value="ajouterDvd"/>
            <h2> DVD </h2>
            <ul>
                <li><label> Titre : </label> <input type="text" name="titre"/></li>
                <li><label> Description : </label> <input type="text" name="description"/></li>
                <li><label> Prix : </label> <input type="text" name="prix"/></li>
                <li><label> Date de sortie : </label> <input type="text" name="dateSortie"/></li>
                <li><label> Quantité : </label> <input type="text" name="quantite"/></li>
            </ul>    
            <h2> Auteur </h2>
            <ul>
                <li><label> Nom : </label> <input type="text" name="nomAuteur"/></li>
                <li><label> Prénom : </label> <input type="text" name="prenomAuteur"/></li>
            </ul>
            
            <h2> Realisateur </h2>
            <ul>
                <li><label> Nom : </label> <input type="text" name="nomRealisateur"/></li>
                <li><label> Prénom : </label> <input type="text" name="prenomRealisateur"/></li>
            </ul>
            <input type="submit" value="Ajouter le dvd"/>        
        </form>
    </body>
</html>
