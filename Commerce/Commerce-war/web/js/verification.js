/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function surligne(champ, erreur)
{
   if(erreur)
      champ.style.backgroundColor = "#fba";
   else
      champ.style.backgroundColor = "";
}

function verif(champ)
{
   if(champ.value.length < 1 || champ.value.length > 25)
   {
      surligne(champ, true);
      return false;
   }
   else
   {
      surligne(champ, false);
      return true;
   }
}

function verifnumb(champ)
{
    var numb = parseInt(champ.value);
   if(numb < 1 )
   {
      surligne(champ, true);
      return false;
   }
   else
   {
      surligne(champ, false);
      return true;
   }
}

function verifForm(f)
{
   var prenomClient = verif(f.prenomClient);
   var nomClient = verif(f.nomClient);
   var email = verif(f.email);
   var passWord = verif(f.passWord);
   var ConfirmPass = verif(f.ConfirmPass);
   
   if(prenomClient && nomClient && email && passWord && ConfirmPass )
      return true;
   else
   {
      alert("Veuillez remplir correctement tous les champs");
      return false;
   }
}


function verifFormDvd(f)
{
   var titre = verif(f.titre);
   var description = verif(f.description);
   var prix = verifnumb(f.prix);
   var dateSortie = verif(f.dateSortie);
   var quantite = verifnumb(f.quantite);
   var prenomAuteur = verif(f.prenomAuteur);
   var nomAuteur = verif(f.nomAuteur);
   var nomRealisateur = verif(f.nomRealisateur);
   var prenomRealisateur = verif(f.prenomRealisateur);
   var nomEditeur = verif(f.nomEditeur);
   if(nomEditeur && prenomRealisateur && nomRealisateur && nomAuteur && prenomAuteur
      &&  quantite && dateSortie &&  prix && description && titre )
      return true;
   else
   {
      alert("Veuillez remplir correctement tous les champs");
      return false;
   }
}






