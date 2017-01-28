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
        <%@include file="Header/ConnecteClient.jsp" %>
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
                        <div class="col-md-4">
                            <div id="p1" style="display:none">
                                <h3><a href="#" onclick="return show2('p2','p1','pp2','pp1');">Par auteur</a></h3>
                            </div>
                            <div id="p2">
                                <h3><a href="#" onclick="return show2('p1','p2','pp1','pp2');" class="toMe">Par auteur</a></h3>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div id="p3">
                                <h3><a href="#" onclick="return show2('p4','p3','pp4','pp3');">Par réalisateur</a></h3>
                            </div>
                            <div id="p4" style="display:none">
                                <h3><a href="#" onclick="return show2('p3','p4','pp3','pp4');" class="toMe">Par réalisateur</a></h3>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div id="p5">
                                <h3><a href="#" onclick="return show2('p6','p5','pp6','pp5');">Par catégories</a></h3>
                            </div>
                            <div id="p6" style="display:none">
                                <h3><a href="#" onclick="return show2('p5','p6','pp5','pp6');" class="toMe">Par catégories</a></h3>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div id="p7">
                                <h3><a href="#" onclick="return show2('p8','p7','pp8','pp7');">Par editeur</a></h3>
                            </div>
                            <div id="p8"  style="display:none">
                                <h3><a href="#" onclick="return show2('p7','p8','pp7','pp8');" class="toMe">Par editeur</a></h3>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div id="p9">
                                <h3><a href="#" onclick="return show2('p10','p9','pp10','pp9');">Par titre</a></h3>
                            </div>
                            <div id="p10" style="display:none">
                                <h3><a href="#" onclick="return show2('p9','p10','pp9','pp10');" class="toMe">Par titre</a></h3>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div id="p11">
                                <h3><a href="#" onclick="return show2('p12','p11','pp12','pp11');">Par année</a></h3>
                            </div>
                            <div id="p12" style="display:none">
                                <h3><a href="#" onclick="return show2('p11','p12','pp11','pp12');" class="toMe">Par année</a></h3>
                            </div>
                        </div>
                        <br/><br/>
                        <div id="pp1" style="display:none">
                        </div>
                        <div id="pp2" class="to">
                            <h3 class="h3-for-recherche">Entrez l'auteur : </h3><input type="text" name="auteur"/><br/>
                        </div>
                        <div id="pp3">
                        </div>
                        <div id="pp4" class="to" style="display:none">
                            <h3 class="h3-for-recherche">Entrez le réalisateur : </h3><input type="text" name="realisateur"/><br/>
                        </div>
                        <div id="pp5">
                        </div>
                        <div id="pp6" class="to"  style="display:none">
                            <h3 class="h3-for-recherche">Choisissez les catégories : </h3><input type="text" name="categories"/><br/>
                        </div>
                        <div id="pp7">
                        </div>
                        <div id="pp8" class="to"  style="display:none">
                            <h3 class="h3-for-recherche">Entrez les éditeurs: </h3><input type="text" name="editeur"/><br/>
                        </div>
                        <div id="pp9">
                        </div>
                        <div id="pp10" class="to" style="display:none">
                            <h3 class="h3-for-recherche">Entrez le titre : </h3><input type="text" name="titre"/><br/>
                        </div>
                        <div id="pp11">
                        </div>
                        <div id="pp12" class="to"  style="display:none">
                            <h3 class="h3-for-recherche">Entrez l'année : </h3><input type="text" name="annee"/><br/>
                        </div>
                    </div>
                    <div id="Page2">
                        <a href="#" onclick="return show('Page1', 'Page2');">Editer</a>
                    </div>
                    <br/><br/>                    
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
