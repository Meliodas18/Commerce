/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import entity.Auteur;
import entity.Client;
import entity.Dvd;
import entity.Employe;
import entity.Realisateur;
import session.SessionTest;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.AuteurFacade;
import session.ClientFacade;
import session.DvdFacade;
import session.Panier;
import session.EmployeFacade;
import session.RealisateurFacade;

/**
 *
 * @author aymeric
 */
@WebServlet(name = "Controleur", urlPatterns = {"/Controleur"})
public class Controleur extends HttpServlet {
    
    @EJB
    private SessionTest st;
    
    @EJB
    private AuteurFacade auteurf;
    
    @EJB
    private DvdFacade dvdf;
    
    @EJB
    private ClientFacade clientf;
    
    @EJB
    private EmployeFacade employef;
    
    @EJB
    private RealisateurFacade realisateurf;
    
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.lang.NoSuchMethodException
     * @throws java.lang.IllegalAccessException
     * @throws java.lang.reflect.InvocationTargetException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        response.setContentType("text/html;charset=UTF-8");
        
        //Récupération du panier
        Panier panierClient = (Panier)request.getSession().getAttribute("panier");

        //Si le panier n'existe pas -- on le crée
        if (panierClient == null){
            try {
                InitialContext ic = new InitialContext();
                panierClient = (Panier) ic.lookup("java:global/Commerce/Commerce-ejb/Panier");
            } catch (NamingException ex) {
                Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.getSession().setAttribute("panier", panierClient);
        }
                
        String action = request.getParameter("action");
        switch (action) {
            case "accueil":
                accueil(request,response);
                break;
            case "pageAjouterDvd":
                pageAjouterDvd(request,response);
                break;
            case "ajouterDvd":
                ajouterDvd(request,response);
                break;
            case "pageRechercherDvd":
                pageRechercherDvd(request,response);
                break;
            case "ajouterPanier":
                ajouterPanier(request, response, panierClient);
                break;
            case "pagePanier":
                pagePanier(request, response, panierClient);
                break;
            case "ajouterClient":
                ajouterClient(request, response);
                break;
            case "pageInscription":
                pageInscription(request,response);
                break;
            case "pageConnexion":
                pageConnexion(request,response);
                break;
            case "pageConnexionEmploye":
                pageConnexionEmploye(request,response);
                break;
            case "connexion":
                connexion(request,response);
                break;
             case "connexionEmploye":
                connexionEmploye(request,response);
                break;
            case "confirmOrder":
                confirmOrder(request,response,panierClient);
                break;
            case "SupprimerClient":
                supprimerClient(request,response);
                break;
            case "supprimerLeClient":
                supprimerLeClient(request,response);
                break;
            case "deconnexion":
                deconnexion(request,response,panierClient);
                break;
            case "interactiveResearch":
                interactiveResearch(request,response);
                break;
            default:
                break;
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            processRequest(request, response);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void pageAjouterDvd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/AjoutDvds.jsp").forward(request, response);
    }

    private void accueil(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/Accueil.jsp").forward(request, response);
    }

    //Attention aux exceptions -- doivent pas etre lancées ici à priori
    private void ajouterDvd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Dvd dvd = new Dvd(request.getParameter("titre"), request.getParameter("description"), Double.parseDouble(request.getParameter("prix")), request.getParameter("dateSortie"), Integer.parseInt(request.getParameter("quantite")));
        String[] paramDvd = {"titre","description","prix","dateSortie"};
        String[] paramAuteur = {"prenom","nom"};
        String[] paramRealisateur = {"prenomRealisateur","nomRealisateur"};
        
        //Si le dvd n'existe pas, on crée le dvd
        if (dvdf.getId(dvd, paramDvd).isEmpty()){
            dvdf.create(dvd);
            Auteur auteur = new Auteur(request.getParameter("prenomAuteur"),request.getParameter("nomAuteur"));
            Realisateur realisateur = new Realisateur(request.getParameter("prenomRealisateur"),request.getParameter("nomRealisateur"));
            
            //Si l'auteur existe, il faut le recuperer et remerger après avoir ajouter le dvd
            if (auteurf.getId(auteur, paramAuteur).isEmpty()){
                auteur.addDvds(dvd);
                auteurf.create(auteur);
                realisateur.addDvds(dvd);
                realisateurf.create(realisateur);
            } else {
                auteur = auteurf.find(auteurf.getId(auteur, paramAuteur).get(0));
                realisateur = realisateurf.find(realisateurf.getId(realisateur, paramRealisateur).get(0));
                auteur.addDvds(dvd);
                realisateur.addDvds(dvd);
                auteurf.edit(auteur);
                realisateurf.edit(realisateur);
            }
        }
        getServletContext().getRequestDispatcher("/WEB-INF/Accueil.jsp").forward(request, response);

    }

    private void pageRechercherDvd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Dvd> list = (ArrayList<Dvd>) dvdf.findAll();
        request.setAttribute("listeDvds", list);
        getServletContext().getRequestDispatcher("/WEB-INF/RechercheDvd.jsp").forward(request, response);
    }

    private void ajouterPanier(HttpServletRequest request, HttpServletResponse response, Panier panierClient) throws ServletException, IOException {
        panierClient.addDvd(dvdf.find(Integer.toUnsignedLong(Integer.parseInt(request.getParameter("id")))),1);
        getServletContext().getRequestDispatcher("/WEB-INF/Accueil.jsp").forward(request, response);
    }

    private void pagePanier(HttpServletRequest request, HttpServletResponse response, Panier panierClient) throws ServletException, IOException {
        request.setAttribute("panier",panierClient.getDvd());
        getServletContext().getRequestDispatcher("/WEB-INF/Panier.jsp").forward(request, response);
    }

    private void ajouterClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        clientf.create(new Client(request.getParameter("nomClient"), request.getParameter("prenomClient"), request.getParameter("passWord"),request.getParameter("email")));
        getServletContext().getRequestDispatcher("/WEB-INF/Accueil.jsp").forward(request, response);
    }

    private void pageInscription(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       getServletContext().getRequestDispatcher("/WEB-INF/Inscription.jsp").forward(request, response);
    }

    private void pageConnexion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/Connexion.jsp").forward(request, response);
    }

    private void connexion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        
        String[] parametres = {"email","motDePasse"};
        ArrayList<Long> findId = clientf.getId(new Client(request.getParameter("nomClient"), request.getParameter("prenomClient"), request.getParameter("passWord"),request.getParameter("email")), parametres);
        if (findId.isEmpty()){
            request.setAttribute("erreur", true);
            getServletContext().getRequestDispatcher("/WEB-INF/Connexion.jsp").forward(request, response);
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("email", request.getParameter("email"));
            session.setAttribute("passWord", request.getParameter("passWord"));
            session.setAttribute("mode","client");
            getServletContext().getRequestDispatcher("/WEB-INF/Accueil.jsp").forward(request,response);
        }
    }

    private void confirmOrder(HttpServletRequest request, HttpServletResponse response, Panier panierClient) throws ServletException, IOException {
        if (request.getParameter("ok").equals("Annuler")){
            panierClient.removeAll();
        } else {
            panierClient.confirmOrder();
            request.getSession().removeAttribute("panier"); //On retire le panier de la request car il n'existe plus
        }
        getServletContext().getRequestDispatcher("/WEB-INF/Accueil.jsp").forward(request, response);
    }

    private void deconnexion(HttpServletRequest request, HttpServletResponse response, Panier panierClient) throws ServletException, IOException {
        request.getSession().invalidate();
        getServletContext().getRequestDispatcher("/WEB-INF/Accueil.jsp").forward(request, response);
    }

    //Fonction de recherche
    private void interactiveResearch(HttpServletRequest request, HttpServletResponse response) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ServletException, IOException {
        
        //Déclaration des paramètres et récupération des données de recherche
        String[] values = request.getParameter("auteur").split(" ");
        String[] parametres = {"prenom","nom"};
        ArrayList<Long> array = new ArrayList<>();
        ArrayList<Dvd> arrayDvd = new ArrayList<>();
        
        
        /*Recherche en fonction de l'auteur
        Si un mot on regarde les deux cas : c'est le nom ou c'est le prénom
        Sinon, il faut tester les différentes combinaisons possibles
        */
        if (values.length >= 2){
            for (int i = 0 ; i < values.length; i++){
                array.addAll(auteurf.getIdForResearch(new Auteur(values[i],values[(i+1)%values.length]), parametres));
            }
        } else if (values.length == 1 && !values[0].equals("")){
            array.addAll(auteurf.getIdForResearch(new Auteur(values[0],""),parametres));
            array.addAll(auteurf.getIdForResearch(new Auteur("",values[0]),parametres));    
        }
        
        //Si on a trouvé des correspondances pour la recherche
        if (!array.isEmpty()){
            for (Long id : array){
                arrayDvd.addAll(auteurf.find(id).getDvds());
            }
            request.setAttribute("setDvd", arrayDvd);
        }
        pageRechercherDvd(request,response);
    }
    
    private void pageConnexionEmploye(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     getServletContext().getRequestDispatcher("/WEB-INF/ConnexionEmploye.jsp").forward(request, response);
    }
    
    private void connexionEmploye(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        
        String[] parametres = {"email","motDePasse"};
        ArrayList<Long> findId = employef.getId(new Employe(request.getParameter("nomEmploye"), request.getParameter("prenomEmploye"), request.getParameter("passWord"),request.getParameter("email")), parametres);
        if (findId.isEmpty()){
            request.setAttribute("erreur", true);
            getServletContext().getRequestDispatcher("/WEB-INF/ConnexionEmploye.jsp").forward(request, response);
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("email", request.getParameter("email"));
            session.setAttribute("passWord", request.getParameter("passWord"));
            session.setAttribute("mode","employe");
            getServletContext().getRequestDispatcher("/WEB-INF/Accueil.jsp").forward(request,response);
        }
    }

    private void supprimerClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Client> list = (ArrayList<Client>) clientf.findAll();
        request.setAttribute("listeClient", list); 
        getServletContext().getRequestDispatcher("/WEB-INF/SupprimerClient.jsp").forward(request,response);
       }
    
    private void supprimerLeClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         
        clientf.remove(clientf.find(Integer.toUnsignedLong(Integer.parseInt(request.getParameter("id")))));
        getServletContext().getRequestDispatcher("/WEB-INF/Accueil.jsp").forward(request, response);
        
        
    }
    
 
}
