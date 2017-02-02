<%-- 
    Document   : NonConnecte
    Created on : 28 déc. 2016, 11:07:15
    Author     : aymeric
--%>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Accueil</title>
        <link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
        <link href="css/style.css" rel='stylesheet' type='text/css' />
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
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
        <script>
                        
            function makePostRequest(url, name, value) {
                            
                var form = document.getElementById("test");
                form.innerHTML= "<form action="+url+" method='post' id='form'>"+"<input type='hidden' name='"+name +"' value='" +value+"' />"  +"</form>";
                document.getElementById("form").submit();
                        
            }
    



    </script>
    </head>
<body>
    <div class="header">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="header-left">
                        <div class="logo">
                            <a href="index.jsp"><img src="images/logo.png" alt=""/></a>
                        </div>
                        <div id="test">
                            <form method="post" ></form>
                        </div>
                        <div class="menu">
                            <a class="toggleMenu" href="#"><img src="images/nav.png" alt="" /></a>
                                <ul class="nav" id="nav">
                                    <li><a href="index.jsp">Accueil</a></li>
                                    <li><a href='#' onclick="makePostRequest('ControleurClients','action','pageRechercherDvd')" >Rechercher des dvds</a></li>
                                    <li><a href='#' onclick="makePostRequest('ControleurClients','action','pageCommandes')">Mes Commandes</a></li>
                                    <li><a href='#' onclick="makePostRequest('ControleurClients','action','deconnexion')" >Deconnexion</a></li>      
                                </ul>
                                <script type="text/javascript" src="js/responsive-nav.js"></script>
                        </div>							
                        <div class="clear"></div>
                    </div>
                    
	            <div class="header_right">
	    		<ul class="icon1 sub-icon1 profile_img">
                            <li><a class="active-icon c1" href="ControleurClients?action=pagePanier"></a></li>
			</ul>
		        <div class="clear"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
