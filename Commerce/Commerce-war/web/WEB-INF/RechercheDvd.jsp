<%-- 
    Document   : RechercheDvd
    Created on : 13 nov. 2016, 12:29:01
    Author     : aymeric
--%>

<%@page import="java.util.Set"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entity.Dvd"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Recherche de dvds</title>
    </head>
        <%@include file="Header/ConnecteClient.jsp" %>
        <div class="main">
            <div class="shop_top">
                <div class="container">
                    <% ArrayList<Dvd> list = (ArrayList<Dvd>) request.getAttribute("listeDvds");
                       int i = 0;
                       out.println("<div class=\"row shop_box-top\">");
                       if (list != null){ 
                            for (Dvd dvd: list){
                                if (i%4 == 0 && i != 0){
                                    out.println("<div class=\"row\">");
                                }
                                i++;
                    %>
                                <form method="POST" action="ControleurClients">
                                    <input type="hidden" name="action" value="ajouterPanier"/>
                                    <input type="hidden" name="id" value="<%=dvd.getId()%>"/>
                                
                                    <div class="col-md-3 shop_box"><a href="single.html"></a>
                                        <img src="images/pic1.jpg" class="img-responsive" alt=""/>
                                        <div class="shop_desc">
                                            <h3><%=dvd.getTitre()%></h3>
                                            <% if (dvd.getQuantite() <= 0){
                                                out.println("<h5>En rupture !</h5>");
                                            } else {
                                                out.println("<h5>En stock !</h5>");
                                            }%>
                                            <span class="actual"><%=dvd.getPrix()%>€</span><br>
                                            <div class="clear"> </div>
                                            <ul class="buttons">
                                                <li class="cart"><button type="submit" class="btn-link">Panier</button></li>
                                                <li class="shop_btn"><a href="ControleurClients?action=pageDetails&id=<%=dvd.getId()%>">Détails</a></li>
                                            </ul>
                                            <div class="clear"> </div>
                                        </div>
                                    </div> 
                                </form>
                        <%if(i%4 == 0){
                            out.println("</div>");
                        }
                        }
                        //On rajoute un /div si jamais il y avait moins qu'un multiple de 4 dvds à afficher
                    if (i%4 != 0){
                        out.println("</div>");
                    }
                }
                %>  
                </div>
            </div>
        </div>
        <%@include file="Footer.jsp" %>
    </body>
</html>
