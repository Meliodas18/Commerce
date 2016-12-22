<%-- 
    Document   : Livraisons
    Created on : 2 déc. 2016, 22:16:48
    Author     : aymeric
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Livraisons</title>
    </head>
    <body>
        <h1>Livraisons du fournissuer</h1>
        <form method="POST" action="ControleurEmployes">
            <input type="hidden" name="action" value="livraisons"/>
            <ul>
                <li><label> ID Dvd : </label> <input type="text" name="id"/></li>
                <li><label> Quantité : </label> <input type="text" name="quantite"/></li>
            </ul>
            <input type="submit" value="Ajouter aux stocks"/>        
        </form>
    </body>
</html>
