<%-- 
    Document   : EnvoiColis
    Created on : 3 déc. 2016, 10:26:59
    Author     : aymeric
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Envoi des colis</title>
    </head>
    <body>
        <h1>Colis à envoyer</h1>
        <form method="POST" action="ControleurEmployes">
            <input type="hidden" name="action" value="envoiColis"/>
            <ul>
                <li><label> ID Commande : </label> <input type="text" name="id"/></li>
            </ul>
            <input type="submit" value="Valider le traitement de la commande"/> 
            <input type="reset"/>
        </form>
    </body>
</html>
