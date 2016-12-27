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
        <title>Connexion</title>
    </head>
    <body>
        <%@include file="Header/NonConnecte.jsp" %>
        <div class="main">
            <div class="shop_top">
                <div class="container">
                    <div class="col-md-6">
                        <div class="login-page">
                            <h4 class="title">Nouveau Client</h4>
                            <p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis</p>
                            <div class="button1">
                                <a href="http://localhost:8080/Commerce-war/ControleurClients?action=pageInscription"><input type="submit" name="Submit" value="Créer un compte"></a>
                            </div>
                            <div class="clear"></div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="login-title">
                            <h4 class="title">Déjà Client ?</h4>
                            <div id="loginbox" class="loginbox">
                                <form method="post" action="ControleurClients" name="login" id="login-form">
                                    <input type="hidden" name="action" value="connexion"/>
                                    <fieldset class="input">
                                        <p id="login-form-username">
                                            <label for="modlgn_username">Email</label>
                                            <input id="modlgn_username" type="text" name="email" class="inputbox" size="18" autocomplete="off">
                                        </p>
                                        <p id="login-form-password">
                                            <label for="modlgn_passwd">Mot de Passe</label>
                                            <input id="modlgn_passwd" type="password" name="passWord" class="inputbox" size="18" autocomplete="off">
                                        </p>
                                        <div class="remember">
                                            <p id="login-form-remember">
                                                <label for="modlgn_remember"><a href="#">Mot de passe oublié ?</a></label>
                                            </p>
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
    </body>
</html>