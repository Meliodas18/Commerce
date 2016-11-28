<%-- 
    Document   : ConnexionEmploye
    Created on : 25 nov. 2016, 18:22:38
    Author     : huang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Connexion Employé </title>
                <link rel="stylesheet" href="assets/css/main.css" />

    </head>
    <body id="top">
        <div class="inner">
            <header>
                
             <h1>Connexion Employé</h1>
            </header>
        </div>
       
        <form method="POST" action="Controleur">
            <input type="hidden" name="action" value="connexionEmploye"/>
            <ul>
                <li><label> Email : </label> <input type="text" name="email"/></li>
                <li><label> Mot de passe : </label> <input type="text" name="passWord"/></li>
            </ul>
            <input type="submit" value="Connexion"/>        
        </form>
    </body>
</html>

