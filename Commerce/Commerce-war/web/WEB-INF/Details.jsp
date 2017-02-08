<%-- 
    Document   : Details
    Created on : 29 déc. 2016, 13:11:09
    Author     : aymeric
--%>

<%@page import="entity.Categorie"%>
<%@page import="entity.Editeur"%>
<%@page import="entity.Realisateur"%>
<%@page import="entity.Auteur"%>
<%@page import="java.util.Set"%>
<%@page import="entity.Dvd"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Details</title>
        <%HttpSession sess = request.getSession();
            if ((String)(sess.getAttribute("mode")) == "client"){%>
                <%@include file="Header/ConnecteClient.jsp" %>
        <%} else {%>
                <%@include file="Header/NonConnecte.jsp" %>
        <%}
        %>
        <link href="css/mycss.css" rel='stylesheet' type='text/css' />
        <% Dvd dvd = (Dvd)request.getAttribute("dvd");
           Set<Dvd> set = (Set<Dvd>)request.getAttribute("set");
           String path = dvd.getImage().substring(91);
           Auteur auteur = dvd.getAuteur();
           Realisateur real = dvd.getRealisateur();
           Editeur edit = dvd.getEditeur();
           Categorie cat = dvd.getCategories();
        %>
        <div class="main">
            <div class="shop_top">
                <div class="container">
                    <div class="row">
                        <div class="col-md-9 single_left">
                            <div class="single_image">
                                <img class="img-for-details" src="<%=path%>"/>
                            </div>
			    <!-- end product_slider -->
			    <div class="single_right">
			      	<h2 class="overflow"><%=dvd.getTitre()%></h2>
                                <br/><br/><br/>
			       	<ul class="options">
                                    <% if (dvd.getQuantite() <= 0){
                                        out.println("<li><h4 class=\"m_12\">En rupture !</h4></li>");
                                    } else {
                                        out.println("<li><h4 class=\"m_12\">" + dvd.getQuantite() + " en stock !</h4></li>");
                                    }%>
                                </ul><br/>
                                <h4><b>Auteur : </b><%=auteur.getPrenom()%> <%=auteur.getNom()%></h4>
                                <h4><b>Réalisateur : </b><%=real.getPrenom()%> <%=real.getNom()%></h4>
                                <h4><b>Date de Sortie : </b><%=dvd.getDateSortie()%></h4>
                                <%if (cat != null){%>
                                <h4><b>Catégorie : </b><%=cat.getType()%></h4>
                                <h4><b>Editeur : </b><%=edit.getNom()%></h4>
                                <%}%>
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
                                        <input type="number" min="1" class="inputbox" value="1" name="quantite"/>
                                        </br></br></br>
                                        <button type="submit" name="Submit" class="exclusive">
                                            <span>Ajouter au panier</span>
                                        </button>
                                    </form>
                                </div>
                          
			</div>
                    </div>	
                    </br>
                    <div class="desc">
			<h4>Description</h4><br/>
			<p><%=dvd.getDescription()%></p>
                    </div> 
                </div>
            </div>
	</div>
    <%@include file="Footer.jsp" %>
</html>
