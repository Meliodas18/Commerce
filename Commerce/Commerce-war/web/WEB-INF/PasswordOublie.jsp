<%-- 
    Document   : PasswordOublie
    Created on : 29 janv. 2017, 00:01:45
    Author     : aymeric
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script>
            function show(shown, hidden, hidden2) {
                document.getElementById(shown).style.display = 'block';
                document.getElementById(hidden).style.display = 'none';
                document.getElementById(hidden2).style.display = 'none';
                return false;
            }
        </script>
        <title>Mot de passe oublié ?</title>
        <link href="css/mycss.css" rel='stylesheet' type='text/css' />
        <%@include file="Header/ConnecteClient.jsp" %>
        <div class="main">
            <div class="shop_top">
                <div class="container">
                    <div class="col-md-12">
                        <h2 class="my-line-2">Mot de passe oublié ?</h2>
                    </div>
                    <br/><br/><br/><br/><br/><br/><br/>
                    <form method="POST" action="ControleurClients?action=forgetPassword">
                        <%if (((String)request.getAttribute("email")).equals("faux")){
                            out.println("<font color=\"red\">Email inconnu !</font>");
                        }%>
                        <div class="to">
                            <label> Entrez votre email, nous vous renvoyons votre mot de passe : </label> <input type="text" name="email"/>
                        </div>
                        <br/>
                        <div class="button1">
                            <input type="submit" value="Envoyer" class="button"/>
                        </div>
                    </form>
                    <br/><br/><br/><br/><br/><br/><br/>
                </div>
            </div>
        </div>
        <%@include file="Footer.jsp" %>
    </body>
</html>
