<%-- 
    Document   : Connexion
    Created on : 14 nov. 2016, 19:27:51
    Author     : aymeric
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Connexion</title>
    </head>
    <body>
        <h1>Connexion</h1>
        <form method="POST" action="Controleur">
            <input type="hidden" name="action" value="connexion"/>
            <ul>
                <li><label> Email : </label> <input type="text" name="email"/></li>
                <li><label> Mot de passe : </label> <input type="text" name="passWord"/></li>
            </ul>
            <input type="submit" value="Connexion"/>        
        </form>
    </body>
</html>
