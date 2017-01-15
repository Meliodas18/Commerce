<%-- 
    Document   : Commande
    Created on : 6 nov. 2016, 11:59:38
    Author     : aymeric
--%>

<%@page import="entity.Commande"%>
<%@page import="java.util.List"%>
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
        <title>Mes Commandes</title>
        <link href="css/mycss.css" rel='stylesheet' type='text/css' />
        <%@include file="Header/ConnecteClient.jsp" %>
        <div class="main">
            <div class="shop_top">
                <div class="container">
                    <div class="col-md-12">
                        <h2 class="my-line-2">Commandes</h2>
                    </div>
                    <br/><br/><br/><br/><br/>
                    <div id="Page1">
                        <p><a href="#" class="a-for-commandes" onclick="return show('Page1', 'Page2','Page3');">En Attente</a>
                           <a href="#" class="a-for-commandes" onclick="return show('Page2', 'Page1','Page3');">En Cours</a>
                           <a href="#" class="a-for-commandes" onclick="return show('Page3', 'Page1','Page2');">Effectuées</a></p>
                        <br/><br/>
                        <% List<Commande> attente = (List<Commande>) request.getAttribute("attente");
                        if (attente.isEmpty()){
                            out.println("<h3> Vous n'avez pas de commandes en attente !</h3>");
                        } else {
                            out.println("<ul>");
                            for (Commande c : attente){%>
                                <li><p>Commande n°<%=c.getId()%>, montant de <%=c.getMontant()%> €, passée le <%=c.getDate().substring(0,11)%> à <%=c.getDate().substring(11)%></p></li>
                            <%}
                        }
                        out.println("</ul>");
                        %>
                    </div>
                    <div id="Page2" style="display:none">
                        <p><a href="#" class="a-for-commandes" onclick="return show('Page1', 'Page2','Page3');">En Attente</a>
                           <a href="#" class="a-for-commandes" onclick="return show('Page2', 'Page1','Page3');">En Cours</a>
                           <a href="#" class="a-for-commandes" onclick="return show('Page3', 'Page1','Page2');">Effectuées</a></p>
                        <br/><br/>
                        <% List<Commande> cours = (List<Commande>) request.getAttribute("cours");
                        if (cours.isEmpty()){
                            out.println("<h3> Vous n'avez pas de commandes en cours !</h3>");
                        } else {
                            out.println("<ul>");
                            for (Commande c : cours){%>
                                <li><p>Commande n°<%=c.getId()%>, montant de <%=c.getMontant()%> €, passée le <%=c.getDate().substring(0,11)%> à <%=c.getDate().substring(11)%></p></li>
                            <%}
                        }
                        out.println("</ul>");
                        %>
                    </div>
                    <div id="Page3" style="display:none">
                        <p><a href="#" class="a-for-commandes" onclick="return show('Page1', 'Page2','Page3');">En Attente</a>
                           <a href="#" class="a-for-commandes" onclick="return show('Page2', 'Page1','Page3');">En Cours</a>
                           <a href="#" class="a-for-commandes" onclick="return show('Page3', 'Page1','Page2');">Effectuées</a></p>
                        <br/><br/>
                        <% List<Commande> effectuee = (List<Commande>) request.getAttribute("effectuee");
                        if (effectuee.isEmpty()){
                            out.println("<h3> Vous n'avez pas de commandes effectuées !</h3>");
                        }else {
                            out.println("<ul>");
                            for (Commande c : effectuee){%>
                                <li><p>Commande n°<%=c.getId()%>, montant de <%=c.getMontant()%> €, passée le <%=c.getDate().substring(0,11)%> à <%=c.getDate().substring(11)%></p></li>
                            <%}
                        }
                        out.println("</ul>");
                        %>
                    </div>
                </div>
            </div>
        </div>
</html>
