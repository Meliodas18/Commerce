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
        <link href="css/mycss.css" rel='stylesheet' type='text/css' />
        <%@include file="Header/ConnecteClient.jsp" %>
        <div class="main">
            <div class="shop_top">
                <div class="container">
                    <div class="col-md-12">
                        <h2 class="my-line-2">Livraisons</h2>
                    </div>
                    <br/><br/><br/><br/><br/></br>
                    <form method="POST" action="ControleurEmployes">
                        <input type="hidden" name="action" value="livraisons"/>
                        <div class="to">
                            <label> ID Dvd : </label> <input type="text" name="id"/>
                        </div>
                        <div class="to">
                            <label> Quantité : </label> <input type="text" name="quantite"/>
                        </div>
                        <div class="button1">
                            <input type="submit" value="Ajouter aux stocks"/>  
                        </div>
                        <br/><br/><br/><br/><br/><br/><br/><br/><br/>
                    </form>
                </div>
            </div>
        </div>
        <%@include file="Footer.jsp" %>
</html>
