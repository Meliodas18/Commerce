/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function surligne(champ, erreur)
{
    if (erreur)
        champ.style.backgroundColor = "#fba";
    else
        champ.style.backgroundColor = "";
}

function verif(champ)
{
    if (champ.value.length < 1 || champ.value.length > 300)
    {
        surligne(champ, true);
        return false;
    } else
    {
        surligne(champ, false);
        return true;
    }
}

function verifnumb(champ)
{
    if ((parseFloat(champ) == parseInt(champ)) && !isNaN(value)) {

        if (parseInt(champ) < 1)
        {
            surligne(champ, true);
            return false;
        } else
        {
            surligne(champ, false);
            return true;
        }
    } else {
        surligne(champ, true);
        return false;
    }
}

function is_int(value) {
    if ((parseFloat(value) == parseInt(value)) && !isNaN(value)) {
        return true;
    } else {
        return false;
    }
}

function verifForm(f)
{
    var prenomClient = verif(f.prenomClient);
    var nomClient = verif(f.nomClient);
    var email = verif(f.email);
    var passWord = verif(f.passWord);
    var ConfirmPass = verif(f.ConfirmPass);

    if (prenomClient && nomClient && email && passWord && ConfirmPass)
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
    if (nomEditeur && prenomRealisateur && nomRealisateur && nomAuteur && prenomAuteur
            && quantite && dateSortie && prix && description && titre)
        return true;
    else
    {
        alert("Veuillez remplir correctement tous les champs");
        return false;
    }
}


function clearFileInput()
{
    var oldInput = document.getElementById("image0");
    var newInput = document.createElement("input");
    newInput.type = "file";
    newInput.id = oldInput.id;
    newInput.name = oldInput.name;
    oldInput.parentNode.replaceChild(newInput, oldInput);
}
function recup_extension(fichier)
{
    if (fichier != "")
    {
        nom_fichier = fichier;
        nbchar = nom_fichier.length;
        extension = nom_fichier.substring(nbchar - 4, nbchar);
        extension = extension.toLowerCase();
        return extension;
    }
}

function verif_extension(fichier)// fonction vérification de l'extension aprés avoir choisi le fichier
{
    ext = recup_extension(fichier);// on appelle la fonction de récupération de l'extension et on récupere l'extension

    if (ext == ".jpg" || ext == ".png" || ext == ".jpeg") {
    }// si extension = a une des extension suivante alors tout est ok donc ... pas d'erreur
    else
    {
        alert("Attention les images au format '" + extension + "' ne sont pas autorisées !\n");
        clearFileInput();
    }
}






