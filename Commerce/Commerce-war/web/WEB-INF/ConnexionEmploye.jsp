<!--A Design by W3layouts
Author: W3layout
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<%-- 
    Document   : Inscription
    Created on : 02 fev. 2017, 18:45:06
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
        <%@include file="Header/NonConnecte.jsp" %>
        <div class="main">
            <div class="shop_top">
                <div class="container">
                    <div class="col-md-6">
                        <div class="login-page">
                            <h4 class="title">Vous êtes employés ?</h4>
                            <p> En vous connectant, vous pourrez accéder à l'interface de contrôle du site web, permettant de mettre en place différents dvds, de remplir les différentes commandes et de les recevoir. Vous pourrez également avoir accès à toutes les commandes effectués par les clients afin d'intéragir aux mieux avec notre clientèle !</p>
                            </br></br> 
                            <div class="clear"></div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="login-title">
                            <h4 class="title">Connexion ?</h4>
                            <div id="loginbox" class="loginbox">
                                <%if (((String)request.getAttribute("etat")).equals("faux")){
                                out.println("<br/><font color=\"red\">Email ou mot de passe invalide !</font><br/>");  
                                }%>
                                <form method="post" action="ControleurEmployes" name="login" id="login-form">
                                    <input type="hidden" name="action" value="connexionEmploye"/>
                                    <fieldset class="input">
                                        <p id="login-form-username">
                                            <label for="modlgn_username">Email</label>
                                            <input id="modlgn_username" type="text" name="email" class="inputbox" size="18" autocomplete="off">
                                        </p>
                                        <p id="login-form-username">
                                            <label for="modlgn_passwd">Mot de Passe</label>
                                            <input id="modlgn_passwd" type="passWord" name="passWord" class="inputbox" size="18" autocomplete="off">
                                        </p>
                                        <div class="remember">
                                            <input type="submit" name="Submit" class="button" value="Connexion"><div class="clear"></div>
                                        </div>
                                    </fieldset>
                                </form>
                            </div>
                        </div>
                        <div class="clear"></div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="Footer.jsp" %>
    </body>
</html>
