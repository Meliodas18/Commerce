<h1>Recherche de DVD</h1></br>
        <form method="POST" action="ControleurClients">
            <input type="hidden" name="action" value="interactiveResearch"/>   
            <label> Auteur : </label> <input type="text" name="auteur"/>
            <input type="submit" value="Rechercher"/>        
        </form>
        <% ArrayList<Dvd> arrayDvd = (ArrayList<Dvd>) request.getAttribute("setDvd");
            if (arrayDvd != null){
                for (Dvd dvd: arrayDvd){
                    out.print("<a href=\"ControleurClients?action=ajouterPanier&id=" + dvd.getId() + "\">" + dvd.toString() + "</a></br>");
                }
            }
        %>
<div class="to">
                            <label> Prix : </label> <input type="text" name="prix"/>
                        </div>
                        <div class="to">
                            <label> Quantité : </label> <input type="text" name="quantite"/>
                        </div>