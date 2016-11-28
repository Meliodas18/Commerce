<%-- 
    Document   : Accueil
    Created on : 6 nov. 2016, 11:59:10
    Author     : aymeric
--%>

<%@page import="session.Panier"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Accueil</title>
    </head>
    <body>
        <ul>
            <% HttpSession sess = request.getSession();
               if ((String)(sess.getAttribute("mode")) == "client"){
                   out.println("<li><a href=\"Controleur?action=pageAjouterDvd\">Ajouter des dvds</a></li>");
                   out.println("<li><a href=\"Controleur?action=pageRechercherDvd\">Rechercher des dvds</a></li>");
                   out.println("<li><a href=\"Controleur?action=pagePanier\">Panier</a></li>");
                   out.println("<li><a href=\"Controleur?action=deconnexion\">Deconnexion</a></li>");
                   out.println("<li><a href=\"Controleur?action=SupprimerClient\">Supprimer Client</a></li>");
               
               } else if((String)(sess.getAttribute("mode")) == "employe"){
                   out.println("<li><a href=\"Controleur?action=pageAjouterDvd\">Ajouter des dvds</a></li>");
                   out.println("<li><a href=\"Controleur?action=pageRechercherDvd\">Rechercher des dvds</a></li>");
                   out.println("<li><a href=\"Controleur?action=pagePanier\">Panier</a></li>");
                   out.println("<li><a href=\"Controleur?action=deconnexion\">Deconnexion</a></li>");
                   out.println("<li><a href=\"Controleur?action=SupprimerClient\">Supprimer Client</a></li>");
               
               }else{
                   out.println("<li><a href=\"Controleur?action=pageConnexion\">Connexion</a></li>"); 
                   out.println("<li><a href=\"Controleur?action=pageInscription\">Inscription</a></li>");
                    out.println("<li><a href=\"Controleur?action=pageConnexionEmploye\">Connexion Employer</a></li>"); 
               
               }
            %>

        </ul>
    </body>
</html>
