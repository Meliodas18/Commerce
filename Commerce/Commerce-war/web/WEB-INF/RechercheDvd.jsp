<%-- 
    Document   : RechercheDvd
    Created on : 13 nov. 2016, 12:29:01
    Author     : aymeric
--%>

<%@page import="entity.Categorie"%>
<%@page import="java.util.List"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entity.Dvd"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Recherche de dvds</title>
        <link href="css/mycss.css" rel='stylesheet' type='text/css' />
        <script>
            function show(shown, hidden) {
                document.getElementById(shown).style.display = 'block';
                document.getElementById(hidden).style.display = 'none';
                return false;
            }
            
            function show2(shown, hidden,shown2,hidden2) {
                document.getElementById(shown).style.display = 'block';
                document.getElementById(hidden).style.display = 'none';
                document.getElementById(shown2).style.display = 'block';
                document.getElementById(hidden2).style.display = 'none';
                return false;
            }
        </script>
        <%HttpSession sess = request.getSession();
            if ((String)(sess.getAttribute("mode")) == "client"){%>
                <%@include file="Header/ConnecteClient.jsp" %>
        <%} else {%>
                <%@include file="Header/NonConnecte.jsp" %>
        <%}
        %>
        <div class="main">
            <div class="shop_top">
                <div class="container">
                    <div class="col-md-12">
                        <h2 class="my-line-2">Nos dvds !</h2>
                    </div>
                    <br/><br/><br/><br/><br/>
                    <h3 class="h3-for-recherche">Votre recherche</h3><br/>
                    <div id="Page1" style="display:none">
                        <div class="borderDown">
                            <a href="#" onclick="return show('Page2', 'Page1');">Cacher</a>
                        </div>
                        <br/>
                        <form method="POST" action="ControleurClients">
                            <input type="hidden" name="action" value="interactiveResearch"/>
                            <div id="pp2" class="to">
                                <h3 class="h3-for-recherche">Entrez l'auteur : </h3>
                                <div class="col-md-6">
                                    <input type="text" name="auteurP" value="Prénom" onclick="this.value='';"/>
                                </div>
                                <div class="col-md-6">
                                    <input type="text" name="auteurN" value="Nom" onclick="this.value='';"/>
                                </div>
                            </div>
                            <div id="pp4" class="to">
                                <h3 class="h3-for-recherche">Entrez le réalisateur : </h3><div class="col-md-6">
                                    <input type="text" name="realisateurP" value="Prénom" onclick="this.value='';"/>
                                </div>
                                <div class="col-md-6">
                                    <input type="text" name="realisateurN" value="Nom" onclick="this.value='';"/>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="box-info-product1">
                                    <ul class="prosuct-qty">
                                        <h3 class="h3-for-recherche">Choisissez la catégorie : </h3>
                                        <select name="categorie">
                                            <%List<Categorie> myList = (List<Categorie>)request.getAttribute("Cat");
                                            for (Categorie c : myList){
                                                out.println("<option>" + c.getType() + "</option>");
                                            }
                                            %>
                                        </select>
                                    </ul>
                                </div>
                            </div>
                            <div id="pp8" class="to">
                                <h3 class="h3-for-recherche">Entrez l' éditeur: </h3><input type="text" name="editeur" value="Nom" onclick="this.value='';"/><br/>
                            </div>
                            <div id="pp10" class="to">
                                <h3 class="h3-for-recherche">Entrez le titre : </h3><input type="text" name="titre" value="Titre" onclick="this.value='';"/><br/>
                            </div>
                            <br/><br/><br/><br/>
                            <div class="button2">
                                <input type="submit" value="Rechercher">
                            </div>
                            <br/><br/><br/><br/><br/><br/><br/><br/>
                        </form>
                    </div>
                    
                    <div id="Page2">
                        <a href="#" onclick="return show('Page1', 'Page2');">Editer</a>
                    </div>
                    
                    <div class="my-line-1">
                        </br>
                    </div>                   
                    <% ArrayList<Dvd> list = (ArrayList<Dvd>) request.getAttribute("listeDvds");
                       ArrayList<Dvd> set = (ArrayList<Dvd>) request.getAttribute("setDvd");
                       DecimalFormat df = new DecimalFormat("0.00");
                       if (!set.isEmpty()){
                           list.clear();
                           list = set;
                       }
                       for (Dvd dvd : list){
                       String path = dvd.getImage().substring(91);
                       
                    %>
                    <div class="col-md-1">
                        <img src="<%=path%>" class="img-for-cart"/>
                    </div>
                    <div class="col-md-4">
                        <p class="all-for-cart-2"><%=dvd.getTitre()%></p><br/>
                        <%if (dvd.getQuantite() > -1){ //si la quantite de dvds au moment où on a passé la commande est supérieur à la quantité demandée
                            out.print("<font class=\"font-for-cart\" color=\"red\">En stock !</font>");
                        } else {
                            out.print("<font class=\"font-for-cart\" color=\"red\">A commander !</font>");
                        }%>
                        <br/>
                        <a class="a-for-cart"><%=dvd.getCategories().getType()%></a>
                    </div>
                    <div class="col-md-5">
                        </br></br>
                        <h2><%=df.format(dvd.getPrix())%> €</h2>
                    </div>
                    <div class="col-md-2">
                        </br></br>
                        <a href="ControleurClients?action=pageDetails&id=<%=dvd.getId()%>">En savoir plus !</a>
                    </div>
                    <div class="my-line-1">
                        </br></br></br></br></br>
                    </div> 
                    <%}%>
                </div>
            </div>
        </div>
        <%@include file="Footer.jsp" %>
</html>
