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
                                <h4><b>Auteurs : </b><%=dvd.getAuteurs()%></h4>
                                <h4><b>Réalisteurs : </b><%=dvd.getRealisateur()%></h4>
                                <h4><b>Editeur : </b><%=dvd.getEditeur()%></h4>
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
			<div class="col-md-4 product1">
                            <img src="images/pic1.jpg" class="img-responsive" alt=""/> 
                            <div class="shop_desc">
                                <h4>aliquam volutp</h4>
				<p>Lorem ipsum consectetuer adipiscing </p>
				<span class="reducedfrom">$66.00</span>
				<span class="actual">$12.00</span><br>
				<ul class="buttons">
                                    <li class="cart"><a href="#">Add To Cart</a></li>
                                    <li class="shop_btn"><a href="#">Read More</a></li>
				</ul>
                                <div class="clear"> </div>
                            </div>
			</div>
                        <div class="col-md-4 product1">
                            <img src="images/pic1.jpg" class="img-responsive" alt=""/> 
                            <div class="shop_desc"><a href="single.html"></a>
                                <h3><a href="single.html"></a><a href="#">aliquam volutp</a></h3>
                                <p>Lorem ipsum consectetuer adipiscing </p>
				<span class="reducedfrom">$66.00</span>
				<span class="actual">$12.00</span><br>
				<ul class="buttons">
                                    <li class="cart"><a href="#">Add To Cart</a></li>
                                    <li class="shop_btn"><a href="#">Read More</a></li>
				</ul>
                                <div class="clear"> </div>
                            </div>
			</div>
			<div class="col-md-4">
                            <img src="images/pic1.jpg" class="img-responsive" alt=""/> 
                            <div class="shop_desc"><a href="single.html"></a>
                                <h3><a href="single.html"></a><a href="#">aliquam volutp</a></h3>
                                <p>Lorem ipsum consectetuer adipiscing </p>
                                <span class="reducedfrom">$66.00</span>
                                <span class="actual">$12.00</span><br>
				<ul class="buttons">
                                    <li class="cart"><a href="#">Add To Cart</a></li>
                                    <li class="shop_btn"><a href="#">Read More</a></li>
				</ul>
                                <div class="clear"> </div>
                            </div>
			</div>
                    </div>	
                </div>
            </div>
	</div>
    <%@include file="Footer.jsp" %>
</html>
