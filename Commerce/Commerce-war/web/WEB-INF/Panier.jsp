<%-- 
    Document   : Panier
    Created on : 6 nov. 2016, 11:58:29
    Author     : aymeric
--%>

<%@page import="java.util.HashMap"%>
<%@page import="entity.Dvd"%>
<%@page import="java.util.LinkedList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Panier</title>
        <SCRIPT language=javascript>
            function ConfirmMessage() {
                if (!confirm("Voulez-vous confirmer la commande ?")) { 
                    document.location.href="http://localhost:8080/Commerce-war/ControleurClients?action=pagePanier";
                } else {
                    document.location.href="http://localhost:8080/Commerce-war/ControleurClients?action=confirmOrder&ok=Terminer la commande";
                }
            }
            function Cancel(){
                document.location.href="http://localhost:8080/Commerce-war/ControleurClients?action=confirmOrder&ok=Annuler";
            }
        </SCRIPT>
        <link href="css/mycss.css" rel='stylesheet' type='text/css' />
        <%@include file="Header/ConnecteClient.jsp" %>
        <div class="main">
            <div class="shop_top">
                <div class="container">
                    <div class="col-md-11">
                <% HashMap<Dvd,Integer> contenu = (HashMap<Dvd,Integer>) request.getAttribute("panier");
                   double toPay = (double) request.getAttribute("toPay");
                   if (contenu.isEmpty()){%>
                        </br></br></br></br></br></br>
                        <h2 class="my-line-2">Votre panier est vide</h2>
                        </br></br></br></br></br></br>
                    </div>
                    <%} else {
                        out.println("<h2 class=\"my-line-2\">Votre panier</h2>");
                        for (Dvd dvd : contenu.keySet()){
                            String path = dvd.getImage().substring(49);
                            int quantite = contenu.get(dvd);
                    %>
                    
                    <div class="col-md-1">
                        <img src="<%=path%>" class="img-for-cart"/>
                    </div>
                    <div class="col-md-4">
                        <p class="all-for-cart"><%=dvd.getTitre()%></p>
                        <%if (dvd.getQuantite() - contenu.get(dvd) > -1){ //si la quantite de dvds au moment où on a passé la commande est supérieur à la quantité demandée
                            out.print("<p class=\"all-for-cart\">En stock !<p>");
                        } else {
                            out.print("<p class=\"all-for-cart\">A commander !<p>");
                        }%>
                        </br>
                        <a href="ControleurClients?action=removeCart&id=<%=dvd.getId()%>&quantite=<%=quantite%>" class="all-for-cart">Supprimer</a>
                    </div>
                    <div class="col-md-5">
                        </br></br>
                        <h2><%=dvd.getPrix()%> €</h2>
                    </div>

                    <div class="col-md-2">
                        </br></br>
                        <input type="number" min="0" class="inputbox" value="<%=quantite%>" name="quantite"/>
                    </div>
                    <div class="my-line-1">
                        </br></br></br></br></br>
                    </div>                    
            <%}%>
            <!-- Dernière ligne : boutons terminer commande et annuler ainsi que le montant à payer-->
            <div class="clear"></div>
                    <div class="col-md-9">
                        <div class="button1">
                            <input onClick="Cancel()" type="submit" value="Annuler" class="button"/>
                            <input onClick="ConfirmMessage()" type="submit" value="Terminer la commande" class="button"/>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <h2 class="cart-top-8">Total : <%=toPay%> €</h2>
                    </div>

            <%}
        %>
                </div>
            </div>
        </div>
        <%@include file="Footer.jsp" %>
</html>
