<%-- 
    Document   : Panier
    Created on : 6 nov. 2016, 11:58:29
    Author     : aymeric
--%>

<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.HashMap"%>
<%@page import="entity.Dvd"%>
<%@page import="java.util.LinkedList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Panier</title>
        <script src="js/jquery.min.js"></script>
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
            function getXMLHttpRequest() {

                    var xhr = null;
                    if (window.XMLHttpRequest || window.ActiveXObject) {
                        if (window.ActiveXObject) {
                            try {
                                xhr = new ActiveXObject("Msxml2.XMLHTTP");
                            } catch(e) {
                                xhr = new ActiveXObject("Microsoft.XMLHTTP");
                            }
                        } else {
                            xhr = new XMLHttpRequest(); 
                        }
                    } else {
                        alert("Votre navigateur ne supporte pas l'objet XMLHTTPRequest...");
                        return null;
                    }
                    return xhr;
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
                   DecimalFormat df = new DecimalFormat("0.00");
                   if (contenu.isEmpty()){%>
                        </br></br></br></br></br></br>
                        <h2 class="my-line-2">Votre panier est vide</h2>
                        </br></br></br></br></br></br>
                    </div>
                    <%} else {
                        out.println("<h2 class=\"my-line-2\">Votre panier</h2>");
                        for (Dvd dvd : contenu.keySet()){
                            String path = dvd.getImage().substring(91);
                            int quantite = contenu.get(dvd);
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
                        </br>
                        <a href="ControleurClients?action=removeCart&id=<%=dvd.getId()%>" class="all-for-cart">Supprimer</a>
                    </div>
                    <div class="col-md-5">
                        </br></br>
                        <h2><%=df.format(dvd.getPrix())%> €</h2>
                    </div>

                    <div class="col-md-2">
                        </br></br>
                        <input type="number" id="<%=dvd.getId()%>" min="1" class="inputbox"  value="<%=quantite%>" name="quantite"/>
                    </div>
                    <script>
                                  
    
                                    function request(callback, bool, id) {
                                       
                                    var xhr = getXMLHttpRequest();
                                    if(bool){
                                         xhr.onreadystatechange = function() {
                                            if (xhr.readyState == 4 && (xhr.status == 200 || xhr.status == 0)) {
                                                    callback(xhr.responseXML);
                                            }
                                    };
                                            
                                                       xhr.open("GET", "ControleurClients?action=actualiserPanier&id="+id, true);
                                                       xhr.send(null);
                                    }else{
                                         xhr.onreadystatechange = function() {
                                            if (xhr.readyState == 4 && (xhr.status == 200 || xhr.status == 0)) {
                                                    callback(xhr.responseXML);
                                            }
                                    };
                                            
                                                       xhr.open("GET", "ControleurClients?action=actualiserPanierDecrease&id="+id, true);
                                                       xhr.send(null);
                                        
                                    }
                                   
                                    };

                                function readData(sData) {
                                       var messageTag = sData.getElementsByTagName("message")[0];
                                        message = messageTag.childNodes[0].nodeValue;
                                        mdiv = document.getElementById("total");
                                        mdiv.innerHTML = "Total : "+ message + " €";
                                        
                                         
                                };
                        
                                $(function() {
                                   $('#<%=dvd.getId()%>').change( function() {
                                    var direction = this.defaultValue - this.value;
                                    this.defaultValue = this.value;
                                    var id = <%=dvd.getId()%>;
                                    if(direction < 0)
                                    request(readData,true,id);
                                    else request(readData,false,id);



                                   });
                                   });
                    </script>
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
                        <h2 id="total" class="cart-top-8">Total : <%=df.format(toPay)%> €</h2>
                    </div>
            <%}
        %>
                </div>
            </div>
        </div>
    
       
        <%@include file="Footer.jsp" %>
</html>
