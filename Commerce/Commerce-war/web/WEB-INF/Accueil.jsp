<%-- 
    Document   : Accueil
    Created on : 6 nov. 2016, 11:59:10
    Author     : aymeric
--%>

<%@page import="session.Panier"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Accueil</title>
        <link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
<link href="css/style.css" rel='stylesheet' type='text/css' />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,800' rel='stylesheet' type='text/css'>
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<script src="js/jquery.min.js"></script>
<!--<script src="js/jquery.easydropdown.js"></script>-->
<!--start slider -->
<link rel="stylesheet" href="css/fwslider.css" media="all">
<script src="js/jquery-ui.min.js"></script>
<script src="js/fwslider.js"></script>
<!--end slider -->
<script type="text/javascript">
        $(document).ready(function() {
            $(".dropdown img.flag").addClass("flagvisibility");

            $(".dropdown dt a").click(function() {
                $(".dropdown dd ul").toggle();
            });
                        
            $(".dropdown dd ul li a").click(function() {
                var text = $(this).html();
                $(".dropdown dt a span").html(text);
                $(".dropdown dd ul").hide();
                $("#result").html("Selected value is: " + getSelectedValue("sample"));
            });
                        
            function getSelectedValue(id) {
                return $("#" + id).find("dt a span.value").html();
            }

            $(document).bind('click', function(e) {
                var $clicked = $(e.target);
                if (! $clicked.parents().hasClass("dropdown"))
                    $(".dropdown dd ul").hide();
            });


            $("#flagSwitcher").click(function() {
                $(".dropdown img.flag").toggleClass("flagvisibility");
            });
        });
     </script>
    </head>
    <body>
        
        
	<div class="header">
		<div class="container">
			<div class="row">
			  <div class="col-md-12">
				 <div class="header-left">
					 <div class="logo">
						<a href="Controleur?action=accueil"><img src="images/logo.png" alt=""/></a>
					 </div>
					 <div class="menu">
						  <a class="toggleMenu" href="#"><img src="images/nav.png" alt="" /></a>
						    <ul class="nav" id="nav">
                                                        
                                                        <% HttpSession sess = request.getSession();
               if ((String)(sess.getAttribute("mode")) == "client"){ %>
               
               
                   <li><a href="Controleur?action=pageAjouterDvd">Ajouter des dvds</a></li>
                   <li><a href="Controleur?action=pageRechercherDvd">Rechercher des dvds</a></li>
                   <li><a href="Controleur?action=pagePanier">Panier</a></li>
                   <li><a href="Controleur?action=deconnexion">Deconnexion</a></li>
                   <li><a href="Controleur?action=SupprimerClient">Supprimer Client</a></li>
                   <li><a href="Controleur?action=pageCommandes">Commandes</a></li>
                   <li><a href="Controleur?action=pageLivraisons">Livraisons</a></li>
                   <li><a href="Controleur?action=pageEnvoiColis">Envoi Colis</a></li>
               <%
               } else if((String)(sess.getAttribute("mode")) == "employe"){
                %>
                   <li><a href="Controleur?action=pageAjouterDvd">Ajouter des dvds</a></li>
                   <li><a href="Controleur?action=pageRechercherDvd">Rechercher des dvds</a></li>
                   <li><a href="Controleur?action=pagePanier">Panier</a></li>
                   <li><a href="Controleur?action=deconnexion">Deconnexion</a></li>
                   <li><a href="Controleur?action=SupprimerClient">Supprimer Client</a></li>
                   <li><a href="Controleur?action=pageCommandes">Commandes</a></li>
                   <li><a href="Controleur?action=pageLivraisons">Livraisons</a></li>
                   <li><a href="Controleur?action=pageEnvoiColis">Envoi Colis</a></li>
                   <%
               } else {
               %>
                   
                                                        <li><a href="Controleur?action=pageInscription">Inscription</a></li>
						    	<li><a href="Controleur?action=pageConnexion">Connexion</a></li>
							<li><a href="Controleur?action=pageConnexionEmploye">Employé</a></li>
                                                         <li><a href="Controleur?action=email">Email</a></li>
              
								<div class="clear"></div>
                    <%
               }
            %>
						    	
                                                                
                                                                
							</ul>
							<script type="text/javascript" src="js/responsive-nav.js"></script>
				    </div>							
	    		    <div class="clear"></div>
	    	    </div>
	            <div class="header_right">
	    		  <!-- start search-->
				      <div class="search-box">
							<div id="sb-search" class="sb-search">
								<form>
									<input class="sb-search-input" placeholder="Enter your search term..." type="search" name="search" id="search">
									<input class="sb-search-submit" type="submit" value="">
									<span class="sb-icon-search"> </span>
								</form>
							</div>
						</div>
						<!----search-scripts---->
						<script src="js/classie.js"></script>
						<script src="js/uisearch.js"></script>
						<script>
							new UISearch( document.getElementById( 'sb-search' ) );
						</script>
						<!----//search-scripts---->
				    
		           <div class="clear"></div>
	       </div>
	      </div>
		 </div>
	    </div>
	</div>
	<div class="banner">
	<!-- start slider -->
       <div id="fwslider">
         <div class="slider_container">
            <div class="slide"> 
                <!-- Slide image -->
               <img src="images/slider1.jpg" class="img-responsive" alt=""/>
                <!-- /Slide image -->
                <!-- Texts container -->
                <div class="slide_content">
                    <div class="slide_content_wrap">
                        <!-- Text title -->
                        <h1 class="title">Achetez<br>Nos DVDS</h1>
                        <!-- /Text title -->
                        </div>
                </div>
               <!-- /Texts container -->
            </div>
            <!-- /Duplicate to create more slides -->
            <div class="slide">
               <img src="images/slider2.jpg" class="img-responsive" alt=""/>
                <div class="slide_content">
                    <div class="slide_content_wrap">
                        <h1 class="title">Achetez<br>Nos DVDS</h1>
                       	
                    </div>
                </div>
            </div>
            <!--/slide -->
        </div>
        <div class="timers"></div>
        <div class="slidePrev"><span></span></div>
        <div class="slideNext"><span></span></div>
       </div>
       <!--/slider -->
      </div>
        
        
            
            <div class="footer">
			<div class="container">
				<div class="row">
					<div class="col-md-3">
						<ul class="footer_box">
							<h4>Produits</h4>
							<li><a href="#">DVD</a></li>
						</ul>
					</div>
					<div class="col-md-3">
						<ul class="footer_box">
							<h4>A propos</h4>
							<li><a href="#">Sponsers</a></li>
							<li><a href="#">team</a></li>
						</ul>
					</div>
					<div class="col-md-3">
						<ul class="footer_box">
							<h4>Support</h4>
							<li><a href="#">Contact Us</a></li>
							</ul>
					</div>
					<div class="col-md-3">
						<ul class="footer_box">
							<h4>Email</h4>
							<div class="footer_search">
				    		   <form>
				    			<input type="text" value="Enter your email" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Enter your email';}">
				    			<input type="submit" value="Go">
				    		   </form>
					        </div>
							<ul class="social">	
							  <li class="facebook"><a href="#"><span> </span></a></li>
							  <li class="twitter"><a href="#"><span> </span></a></li>
							  <li class="instagram"><a href="#"><span> </span></a></li>	
							  <li class="pinterest"><a href="#"><span> </span></a></li>	
							  <li class="youtube"><a href="#"><span> </span></a></li>										  				
						    </ul>
		   					
						</ul>
					</div>
				</div>
			</div>
		</div>
    </body>
</html>
