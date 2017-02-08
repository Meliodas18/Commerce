<%-- 
    Document   : AjoutDvds
    Created on : 12 nov. 2016, 22:23:32
    Author     : aymeric
--%>
<%@page import="entity.Categorie"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="js/verification.js"></script>
        <script>
            function show(shown, hidden) {
                document.getElementById(shown).style.display = 'block';
                document.getElementById(hidden).style.display = 'none';
                return false;
            }
        </script>
        <title>Gestion des dvds</title>
        <link href="css/mycss.css" rel='stylesheet' type='text/css' />
        <%@include file="Header/ConnecteEmployé.jsp" %>
        <div class="main">
            <div class="shop_top">
                <div class="container">
                    <div class="col-md-12">
                        <h2 class="my-line-2">Ajoutez un dvd !</h2>
                    </div>
                    <br/><br/><br/><br/><br/>
                    <p><a href="#" class="a-for-commandes" onclick="return show('Page1', 'Page2');">Ajout Dvd</a>
                           <a href="#" class="a-for-commandes" onclick="return show('Page2', 'Page1');">Gestion des Quantités</a>
                    </p>
                    <br/><br/>
                    <div id='Page1' style="display:none">
                        <form method="POST" action="ControleurEmployes?action=ajouterDvd" id="login-form" enctype="multipart/form-data" onsubmit="return verifFormDvd(this)">
                            <h4 class="title">Caractéristiques du dvd</h4>
                            <%if (((String)request.getAttribute("prix")).equals("faux")){%>
                            <font color="red">Le format du prix est invalide ! Exemple de prix : 10.00</font><br/>    
                            <%}%>
                            <div class="to">
                                <label> Titre : </label> <input type="text" name="titre" onblur="verif(this)"/>
                            </div>
                            <div class="to">
                                <label> Date de sortie : </label> <input type="text" name="dateSortie" onblur="verif(this)"/>
                            </div>
                            <div class="text">
                                <label> Description : </label><textarea name="description" onblur="verifdes(this)"></textarea>
                            </div>
                            <div class="to">
                                <label> Prix : </label> <input type="text" name="prix"  onblur="verifnumbprix(this)"/>
                            </div>
                            <div class="col-md-6">
                                <div class="box-info-product1">
                                    <ul class="prosuct-qty">
                                        <label>Catégorie :</label>
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
                            <div class="to">
                                <label> Quantité : </label> <input type="text" name="quantite"  onblur="verifnumb(this)"/>
                            </div>
                            <div class="to">
                                <label> Affiche du film : </label></br><input type="file" name="file" id="file" value="" onchange="verif_extension(file.value)"/>
                            </div>
                            </br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br>
                            <h4 class="title"> Auteurs </h4>
                            <div class="to">
                                <label>Nom : </label> <input type="text" name="nomAuteur" onblur="verif(this)"/>
                            </div>
                            <div class="to">
                                <label> Prénom : </label> <input type="text" name="prenomAuteur" onblur="verif(this)"/>
                            </div>
                            </br></br></br></br></br></br>
                            <h4 class="title"> Réalisateur </h4>
                            <div class="to">
                                <label> Nom : </label> <input type="text" name="nomRealisateur" onblur="verif(this)"/>
                            </div>
                            <div class="to">
                                <label> Prénom : </label> <input type="text" name="prenomRealisateur" onblur="verif(this)"/>
                            </div>
                            </br></br></br></br></br></br>
                            <h4 class="title"> Editeur </h4>
                            <div class="to">
                                <label> Nom : </label> <input type="text" name="nomEditeur" onblur="verif(this)"/>
                            </div>
                            <div class="to">
                                <label>Adresse Email : </label>
                                <input type="text" name="emailEditeur" size="100" autocomplete="off" onblur="verif(this)"/> 
                            </div>
                            </br></br></br></br></br></br>
                            <div class="button1">
                                <input type="submit" value="Ajouter le dvd" class="button"/>
                            </div>
                        </form> 
                    </div>
                    <div id='Page2'>
                        <form method="POST" action="ControleurEmployes">
                            <input type="hidden" name="action" value="rajouterDvd"/>
                            <div class="to">
                                <label> Numéro de Dvd : </label> <input class="text" type="text" name="id" onblur="verifnumb(this)"/>
                            </div>
                            <div class="to">
                                <label> Quantité : </label> <input class="text" type="text" name="quantite" onblur="verifnumb(this)"/>
                            </div>
                            </br>
                            <%if (((String)request.getAttribute("etat")).equals("faux")){
                                out.println("<font color=\"red\">Ce dvd n'existe pas !</font><br/>");    
                            }%>
                            <div class="button2">
                                <input type="submit" value="Valider"/> 
                            </div>
                            <br/><br/><br/><br/><br/><br/><br/><br/><br/>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="Footer.jsp" %>
