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
        <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
        <script src="js/jquery.min.js"></script>
                                                     
        <% HttpSession sess = request.getSession();
            if ((String)(sess.getAttribute("mode")) == "client"){ %>
                 <%@include file="Header/ConnecteClient.jsp" %>
            <%
            } else if((String)(sess.getAttribute("mode")) == "employe"){
             %>
                 <%@include file="Header/ConnecteEmployé.jsp" %>
            <%
            } else {

            %>
                 <%@include file="Header/NonConnecte.jsp" %>
            <%
            }%>
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
                                <h1 class="title">Dvd Grenoble</h1>
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
                                <h1 class="title">Dvd Grenoble</h1>
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
    <%@include file="Footer.jsp" %>
</html>
