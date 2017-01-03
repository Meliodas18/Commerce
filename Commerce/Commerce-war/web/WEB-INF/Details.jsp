<%-- 
    Document   : Details
    Created on : 29 déc. 2016, 13:11:09
    Author     : aymeric
--%>

<%@page import="java.util.Set"%>
<%@page import="entity.Dvd"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Details</title>
    </head>
        <%@include file="Header/ConnecteClient.jsp" %>
        <% Dvd dvd = (Dvd)request.getAttribute("dvd");
           Set<Dvd> set = (Set<Dvd>)request.getAttribute("set");%>
        <div class="main">
            <div class="shop_top">
                <div class="container">
                    <div class="row">
                        <div class="col-md-9 single_left">
                            <div class="single_image">
                                <img class="etalage_source_image" src="images/pic1.jpg" />
                            </div>
			    <!-- end product_slider -->
			    <div class="single_right">
			      	<h2><%=dvd.getTitre()%></h2>
			       	<ul class="options">
                                    <% if (dvd.getQuantite() <= 0){
                                        out.println("<li><h4 class=\"m_12\">En rupture !</h4></li>");
                                    } else {
                                        out.println("<li><h4 class=\"m_12\">" + dvd.getQuantite() + " en stock !</h4></li>");
                                    }%>
                                </ul>
                                <h4><b>Auteurs : </b><a href="ControleurClients?action=pageAuteur&id=<%=dvd.getAuteurs()%>" class="my-class-for-a"><%=dvd.getAuteurs()%></a></h4>
                                <h4><b>Réalisateur : </b><a href="ControleurClients?action=pageRealisateur&id=<%=dvd.getRealisateur().getId()%>" class="my-class-for-a"><%=dvd.getRealisateur()%></a></h4>
                                <h4><b>Editeur : </b><a href="ControleurClients?action=pageEditeur&id=<%=dvd.getEditeur().getId()%>" class="my-class-for-a"><%=dvd.getEditeur()%></a></h4>
                                <h4><b>Date de Sortie : </b><%=dvd.getDateSortie()%></h4>
				<div class="clear"> </div>
                            </div>
			</div>
                        <div class="col-md-3">
                                <div class="box-info-product">
                                    <form method="post" action="ControleurClients"/>
                                        <input type="hidden" value="ajouterPanier" name="action"/>
                                        <input type="hidden" value="<%=dvd.getId()%>" name="id"/>
                                        <p class="price2"><%=dvd.getPrix()%> €</p>
                                        <span>Quantité : </br></br></span>
                                        <input type="number" min="0" class="inputbox" value="1" name="quantite"/>
                                        </br></br></br>
                                        <button type="submit" name="Submit" class="exclusive">
                                            <span>Ajouter au panier</span>
                                        </button>
                                    </form>
                                </div>
                          
			</div>
                    </div>		
                    <div class="desc">
			<h4>Description</h4>
			<p><%=dvd.getDescription()%></p>
                    </div>
                    <div class="row">
                        <h4 class="m_11">Films du même réalisateur</h4>
                        <% for (Dvd myDvd : set){%>
                        <div class="col-md-3 shop_box"><a href="single.html"></a>
                            <img src="images/pic1.jpg" class="img-responsive" alt=""/>
                            <div class="shop_desc">
                                <h3><%=myDvd.getTitre()%></h3>
                                <% if (myDvd.getQuantite() <= 0){
                                    out.println("<h5>En rupture !</h5>");
                                } else {
                                    out.println("<h5>En stock !</h5>");
                                }%>
                                <span class="actual"><%=myDvd.getPrix()%> €</span><br>
                                <div class="clear"> </div>
                            </div>
                        </div> 
                        <%}%>        
                    </div>	
                </div>
            </div>
	</div>
    <%@include file="Footer.jsp" %>
</html>
