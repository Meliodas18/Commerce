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
    <%@include file="Header/ConnecteClient.jsp" %>
    <body>
        </br>
        <h4 class="title">Ajouter des dvds !</h4>
        </br>
        <form method="POST" action="ControleurEmployes?action=ajouterDvd" id="login-form" enctype="multipart/form-data">
                
                <h4 class="title"> Image pour le Dvd </h4>
                <input type="file" name="file" id="file" />
                </br>
                <h4 class="title"> DVD </h4>
                <ul>
                    <li><label> Titre : </label> <input type="text" name="titre"/></li>
                    <li><label> Description : </label> <input type="text" name="description"/></li>
                    <li><label> Prix : </label> <input type="text" name="prix"/></li>
                    <li><label> Date de sortie : </label> <input type="text" name="dateSortie"/></li>
                    <li><label> Quantité : </label> <input type="text" name="quantite"/></li>
                </ul>    
                </br>
                <h4 class="title"> Auteur </h4>
                <ul>
                    <li><label>Nom : </label> <input type="text" name="nomAuteur"/></li>
                    <li><label> Prénom : </label> <input type="text" name="prenomAuteur"/></li>
                </ul>
                </br>
                <h4 class="title"> Realisateur </h4>
                <ul>
                    <li><label> Nom : </label> <input type="text" name="nomRealisateur"/></li>
                    <li><label> Prénom : </label> <input type="text" name="prenomRealisateur"/></li>
                </ul>
                </br>
                <h4 class="title"> Editeur </h4>
                <ul>
                    <li><label> Nom : </label> <input type="text" name="nomEditeur"/></li>
                </ul>
                </br>
                
                <center><input type="submit" value="Ajouter le dvd" class="button" /></center>
            
        </form> 
    </body>
</html>
