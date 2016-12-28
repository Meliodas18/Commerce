<!--A Design by W3layouts
Author: W3layout
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
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
        <%@include file="Header/NonConnecte.jsp" %>
        <div class="main">
            <div class="shop_top">
                <div class="container">
                    
                    <div class="col-md-6">
                        <form method="POST" action="ControleurClients">
                            <input type="hidden" name="action" value="ajouterClient"/>
                            <div class="register-top-grid">
                                <h2>Information personnelle</h2>
                                <div>
                                    <span>Prenom<label>*</label></span>
                                    <input type="text" name="prenomClient"/> 
                                </div>
                                <div>
                                    <span>Nom<label>*</label></span>
                                    <input type="text" name="nomClient"/>
                                </div>
                                <div>
                                    <span>Adresse Email<label>*</label></span>
                                    <input type="text" name="email" class="inputbox" size="100" autocomplete="off"> 
                                </div>
                                <div class="clear"> </div>
                            </div>
                            <div class="clear"> </div>
                            <div class="register-bottom-grid">
                                <div>
                                    <span>Mot de Passe<label>*</label></span>
                                    <input type="text" name="passWord"/>
                                </div>
                                <div>
                                    <span>Confirmation du Mot de Passe<label>*</label></span>
                                    <input type="text">
                                </div>
                                <div class="clear"></div>
                                <div class="button1">
                                    <input type="submit" name="Submit" class="button" value="S'inscrire"><div class="clear"></div>
                                </div>
                            </div>
                        </form>
                    </div>
                    
                    <div class="col-md-6">
                        <br/><br/><br/>
                        <img src="images/inscription_dvds.jpg" alt="" />
                    </div>
                    
                </div>
            </div>
        </div>
        <%@include file="Footer.jsp" %>
    </body>
</html>
