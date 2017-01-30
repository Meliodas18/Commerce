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
        <link href="css/mycss.css" rel='stylesheet' type='text/css' />
        <%@include file="Header/ConnecteClient.jsp" %>
        <div class="main">
            <div class="shop_top">
                <div class="container">
                    <div class="col-md-12">
                        <h2 class="my-line-2">Envoi des colis</h2>
                    </div>
                    <br/><br/><br/><br/><br/></br>
                    <form method="POST" action="ControleurEmployes">
                        <input type="hidden" name="action" value="envoiColis"/>
                        <%if (((String)request.getAttribute("etat")).equals("impossible")){
                        out.println("<font color=\"red\">Cette commande ne peut-être envoyée : elle est dans l'état \"En Attente\" !</font>");    
                        } else if (((String)request.getAttribute("etat")).equals("faux")){
                        out.println("<font color=\"red\">Le numéro ne correspond à aucune commande !</font>");    
                        }%>
                        <div class="to">
                            <label> ID Commande : </label> <input class="text" type="text" name="id"/>
                        </div>
                        </br>
                        <div class="button1">
                            <input type="reset" value="Réinitialiser"/>
                            <input type="submit" value="Valider le traitement de la commande"/> 
                        </div>
                        </br></br></br></br></br></br></br>
                    </form>
                </div>
            </div>
        </div>
        <%@include file="Footer.jsp" %>
</html>
