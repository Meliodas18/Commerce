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
        <%@include file="Header/ConnecteClient.jsp" %>
        <div class="main">
            <div class="shop_top">
                <div class="container">
                    <% ArrayList<Dvd> list = (ArrayList<Dvd>) request.getAttribute("listeDvds");
                       int i = 0;
                       out.println("<div class=\"row shop_box-top\">");
                       if (list != null){ 
                            for (Dvd dvd: list){
                                String path = dvd.getImage().substring(49);
                                if (i%4 == 0 && i != 0){
                                    out.println("<div class=\"row\">");
                                }
                                i++;
                    %>
                    <div class="col-md-3 shop_box">
                        <div class="shop_desc">
                            <a href="ControleurClients?action=pageDetails&id=<%=dvd.getId()%>"/>
                                <img src="<%=path%>" class="img-responsive" alt="" width="120" height="200"/>
                                </br>
                                <h4><%=dvd.getTitre()%></h4>
                                <% if (dvd.getQuantite() <= 0){
                                    out.println("<h5>En rupture !</h5>");
                                } else {
                                    out.println("<h5>En stock !</h5>");
                                }%>
                                <h5><%=dvd.getPrix()%> €</h5>
                            </a>
                        </div>
                    </div> 
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
