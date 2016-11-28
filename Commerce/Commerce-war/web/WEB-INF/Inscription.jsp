<%-- 
    Document   : Inscription
    Created on : 14 nov. 2016, 18:45:06
    Author     : aymeric
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inscription</title>
    </head>
    <body>
        <h1>Inscription</h1>
        <form method="POST" action="Controleur">
            <input type="hidden" name="action" value="ajouterClient"/>
            <h2> Informations </h2>
            <ul>
                <li><label> Nom : </label> <input type="text" name="nomClient"/></li>
                <li><label> Prénom : </label> <input type="text" name="prenomClient"/></li>
                <li><label> Email : </label> <input type="text" name="email"/></li>
                <li><label> Mot de passe : </label> <input type="text" name="passWord"/></li>
            </ul>
            <input type="submit" value="S'inscrire"/>        
        </form>
    </body>
</html>
