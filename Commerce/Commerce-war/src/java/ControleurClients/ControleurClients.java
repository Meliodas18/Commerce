/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControleurClients;

import Controleur.Controleur;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import entity.Client;
import entity.Commande;
import entity.Dvd;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import static java.lang.Integer.min;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
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
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.util.Map;
import java.util.Map.Entry;
import session.AuteurFacade;
import session.ClientFacade;
import session.CommandeFacade;
import session.DvdFacade;
import static session.EmailSessionBean.sendEmail;
import session.Panier;

/**
 *
 * @author aymeric
 */
@WebServlet(name = "ControleurClients", urlPatterns = {"/ControleurClients"})
public class ControleurClients extends HttpServlet {

    @EJB
    private ClientFacade clientf;

    @EJB
    private AuteurFacade auteurf;

    @EJB
    private DvdFacade dvdf;
    
    @EJB
    private CommandeFacade commandef;

    private Client clientConnect = new Client();

    private void actualiserPanier(HttpServletRequest request, HttpServletResponse response, Panier panierClient) throws ServletException, IOException {
        Dvd dvd = dvdf.find(Integer.toUnsignedLong(Integer.parseInt(request.getParameter("id"))));

            panierClient.addDvd(dvd, 1);

        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        response.getWriter().write("<message>" + panierClient.toPay() + "</message>");

    }
    
    private void actualiserPanierDecrease(HttpServletRequest request, HttpServletResponse response, Panier panierClient) throws ServletException, IOException {
        Dvd dvd = dvdf.find(Integer.toUnsignedLong(Integer.parseInt(request.getParameter("id"))));

           panierClient.removeDvd(dvd, 1);

        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        response.getWriter().write("<message>" + panierClient.toPay() + "</message>");

    }

    private void ajouterClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Client tempClient = new Client();
        tempClient.setEmail(request.getParameter("email"));
        String[] myParam = {"email"};
        if (clientf.getId(tempClient, myParam).isEmpty()){
            request.setAttribute("etat","vrai");
            clientf.create(new Client(request.getParameter("nomClient"), request.getParameter("prenomClient"), request.getParameter("passWord"), request.getParameter("email")));
            this.connexion(request, response);
        } else {
            request.setAttribute("etat","faux");
            getServletContext().getRequestDispatcher("/WEB-INF/Inscription.jsp").forward(request, response);
        }
    }

    private void ajouterPanier(HttpServletRequest request, HttpServletResponse response, Panier panierClient) throws ServletException, IOException {
        panierClient.addDvd(dvdf.find(Integer.toUnsignedLong(Integer.parseInt(request.getParameter("id")))), Integer.parseInt(request.getParameter("quantite")));
        getServletContext().getRequestDispatcher("/WEB-INF/Accueil.jsp").forward(request, response);
    }

    private void confirmOrder(HttpServletRequest request, HttpServletResponse response, Panier panierClient) throws ServletException, IOException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (request.getParameter("ok").equals("Annuler")) {
            panierClient.removeAll();
        } else {
            panierClient.confirmOrder(clientConnect);
            request.getSession().removeAttribute("panier"); //On retire le panier de la request car il n'existe plus
        }
        getServletContext().getRequestDispatcher("/WEB-INF/Accueil.jsp").forward(request, response);
    }
    private void connexion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        String[] parametres = {"email", "motDePasse"};
        ArrayList<Long> findId = clientf.getId(new Client(request.getParameter("nomClient"), request.getParameter("prenomClient"), request.getParameter("passWord"), request.getParameter("email")), parametres);
        if (findId.isEmpty()) {
            request.setAttribute("etat","faux");
            getServletContext().getRequestDispatcher("/WEB-INF/Connexion.jsp").forward(request, response);
        } else {
            request.getSession().setAttribute("mode", "client");
            clientConnect.setEmail(request.getParameter("email"));
            clientConnect.setMotDePasse(request.getParameter("passWord"));
            clientConnect = clientf.find(clientf.getId(clientConnect, parametres).get(0));
            getServletContext().getRequestDispatcher("/WEB-INF/Accueil.jsp").forward(request, response);
        }
    }

    private void deconnexion(HttpServletRequest request, HttpServletResponse response, Panier panierClient) throws ServletException, IOException {
        panierClient.removeAll();
        request.getSession().invalidate();
        getServletContext().getRequestDispatcher("/WEB-INF/Accueil.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(ControleurClients.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(ControleurClients.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(ControleurClients.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(ControleurClients.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, DocumentException {

        //Récupération du panier
        Panier panierClient = (Panier) request.getSession().getAttribute("panier");

        //Si le panier n'existe pas -- on le crée
        if (panierClient == null) {
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
            case "pageRechercherDvd":
                pageRechercherDvd(request, response);
                break;
            case "ajouterPanier":
                ajouterPanier(request, response, panierClient);
                break;
            case "pagePanier":
                pagePanier(request, response, panierClient);
                break;
            case "actualiserPanier":
                actualiserPanier(request, response, panierClient);
                break;
            case "actualiserPanierDecrease":
                actualiserPanierDecrease(request, response, panierClient);
                break;
            case "ajouterClient":
                ajouterClient(request, response);
                break;
            case "pageInscription":
                pageInscription(request, response);
                break;
            case "pageConnexion":
                pageConnexion(request, response);
                break;
            case "connexion":
                connexion(request, response);
                break;
            case "confirmOrder":
                confirmOrder(request, response, panierClient);
                break;
            case "deconnexion":
                deconnexion(request, response, panierClient);
                break;
            case "interactiveResearch":
                interactiveResearch(request, response);
                break;
            case "pageDetails":
                pageDetails(request, response);
                break;
            case "removeCart":
                removeCart(request, response, panierClient);
                break;
            case "pageForgetPassword":
                pageForgetPassword(request,response);
                break;
            case "forgetPassword":
                forgetPassword(request,response);
                break;
            case "pageCommandes":
                pageCommandes(request,response);
                break;
            default:
                break;
        }
    }

    private void forgetPassword(HttpServletRequest request, HttpServletResponse response) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ServletException, IOException {
        Client tempClient = new Client();
        tempClient.setEmail(request.getParameter("email"));
        String[] tempParam = {"email"};
        if (clientf.getId(tempClient, tempParam).isEmpty()){
            request.setAttribute("email", "faux");
            getServletContext().getRequestDispatcher("/WEB-INF/PasswordOublie.jsp").forward(request, response);
        }else{
            tempClient = clientf.find(clientf.getId(tempClient, tempParam).get(0));
            sendEmail(tempClient.getEmail(), "[Projet Jboss EJB] Votre mot de passe", "Bonjour,\nVoici votre mot de passe : " + tempClient.getMotDePasse() + ".\nCordialement,\nL'équipe Projet EJB");
            pageConnexion(request,response);
        }
    }
    
    //Fonction de recherche
    private void interactiveResearch(HttpServletRequest request, HttpServletResponse response) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ServletException, IOException {
        
        //Déclaration des paramètres et récupération des données de recherche
        String[] parametres = {"prenom", "nom"};
        List<Dvd> allDvd = dvdf.findAll();
        List<Dvd> arrayDvd = new ArrayList<>();
        
        //On obtient un array -- on doit maintenant vérifier critère par critère
        for (Dvd dvd : allDvd){
            String temp1 = request.getParameter("titre");
            if (!temp1.equals("") && !dvd.getTitre().equals(temp1)){
                continue;
            }
            temp1 = request.getParameter("realisateurN");
            if (!temp1.equals("") && !dvd.getRealisateur().getNom().equals(temp1)){
                continue;
            }
            temp1 = request.getParameter("realisateurP");
            if (!temp1.equals("") && !dvd.getRealisateur().getPrenom().equals(temp1)){
                continue;
            }
            arrayDvd.add(dvd);
        }
        request.setAttribute("setDvd", arrayDvd);
        pageRechercherDvd(request, response);
    }

    private void pageCommandes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DocumentException{
        request.setAttribute("attente",commandef.getAttente(clientConnect));
        request.setAttribute("cours",commandef.getCours(clientConnect));
        request.setAttribute("effectuee",commandef.getEffectuee(clientConnect));
        
        List<Commande> attente = commandef.getAttente();
        List<Commande> cours = commandef.getCours();
        List<Commande> effectue = commandef.getEffectuee();
        if(!attente.isEmpty()){
            for(Commande c : attente ){
                Document document1 = new Document();
                PdfWriter.getInstance(document1, new FileOutputStream("/home/aymeric/Commerce/Commerce/Commerce-war/web/pdf/attente"+c.getId().toString()+".pdf"));

                document1.open();
                Paragraph text = new Paragraph();
                text.add(new Paragraph("Grenoble Dvd", new Font(Font.FontFamily.TIMES_ROMAN,24,Font.BOLD))); 
                text.add(new Paragraph(" "));
                text.add(new Paragraph(" "));
                text.add(new Paragraph(" "));
                text.add(new Paragraph(" "));
                text.add(new Paragraph("Facture", new Font(Font.FontFamily.TIMES_ROMAN,24,Font.BOLD))); 
                text.add(new Paragraph(" "));
                text.add(new Paragraph(" "));
                text.add(new Paragraph(" "));
                text.add(new Paragraph(" "));
                text.add(new Paragraph("En attente : Commande n°"+c.getId().toString(), new Font(Font.FontFamily.TIMES_ROMAN,18,Font.BOLD))); 
                text.add(new Paragraph(" "));
                text.add(new Paragraph(" "));
                
                document1.add(text);
                
                Paragraph p = new Paragraph(c.getDate(), new Font(Font.FontFamily.TIMES_ROMAN,14,Font.ITALIC));
                p.setAlignment(Element.ALIGN_RIGHT);
                p.add(new Paragraph(" "));
                p.add(new Paragraph(" "));
                document1.add(p);
               
                Paragraph text1= new Paragraph();
                text1.add(new Paragraph("Cher " +c.getClient().getNom() + " " +c.getClient().getPrenom(), new Font(Font.FontFamily.TIMES_ROMAN,14,Font.ITALIC)));
                text1.add(new Paragraph("Email: "+c.getClient().getEmail(), new Font(Font.FontFamily.TIMES_ROMAN,14,Font.ITALIC)));
                
                text1.add(new Paragraph(" "));
                text1.add(new Paragraph(" "));
                text1.add(new Paragraph(" "));
                text1.add(new Paragraph(" "));
                text1.add(new Paragraph(" "));
                
                document1.add(text1);
                
                PdfPTable table = new PdfPTable(3);
                //On créer l'objet cellule.
                PdfPCell cell;
                        
                cell = new PdfPCell(new Phrase("Facture"));
                cell.setColspan(3);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase("Nom du Dvd"));
                cell.setRowspan(1);
                table.addCell(cell);
                //contenu du tableau.
                table.addCell("Quantité");
                table.addCell("Prix");
                
                for (Entry<Dvd, Integer> entry : c.getDvds().entrySet()){
                    
                    Dvd dvd = (Dvd) entry.getKey();
                    Object value = entry.getValue();
                    //Image dvdimage = Image.getInstance(dvd.getImage());
                    //On créer un objet table dans lequel on intialise ça taille.
                    
                    cell = new PdfPCell(new Phrase(dvd.getTitre()));
                    cell.setRowspan(1);
                    table.addCell(cell);
                    
                    table.addCell(String.valueOf(value));
                    table.addCell(String.valueOf(dvd.getPrix()));
                    
                }
                
                cell = new PdfPCell(new Phrase("Total: " + String.valueOf(c.getMontant())));
                cell.setColspan(3);
                
                table.addCell(cell);
                    
                document1.add(table);
                Paragraph p1 = new Paragraph();
                p1.add(new Paragraph(" "));
                p1.add(new Paragraph(" "));
                p1.add(new Paragraph(" "));
                p1.add(new Paragraph(" "));
                p1.add(new Paragraph(" Grenoble Dvd: 12 avenue des ensimag 38000 Grenoble", new Font(Font.FontFamily.TIMES_ROMAN,14,Font.ITALIC)));
                p1.setAlignment(Element.ALIGN_RIGHT);
                document1.add(p1);
                document1.close();
            }  
        }
        
        if(!cours.isEmpty()){
            for(Commande c : cours ){
                Document document1 = new Document();
                PdfWriter.getInstance(document1, new FileOutputStream("/home/aymeric/Commerce/Commerce/Commerce-war/web/pdf/cours"+c.getId().toString()+".pdf"));

                document1.open();
                Paragraph text = new Paragraph();
                text.add(new Paragraph("Grenoble Dvd", new Font(Font.FontFamily.TIMES_ROMAN,24,Font.BOLD))); 
                text.add(new Paragraph(" "));
                text.add(new Paragraph(" "));
                text.add(new Paragraph(" "));
                text.add(new Paragraph(" "));
                text.add(new Paragraph("Facture", new Font(Font.FontFamily.TIMES_ROMAN,24,Font.BOLD))); 
                text.add(new Paragraph(" "));
                text.add(new Paragraph(" "));
                text.add(new Paragraph(" "));
                text.add(new Paragraph(" "));
                text.add(new Paragraph("En cours : Commande n°"+c.getId().toString(), new Font(Font.FontFamily.TIMES_ROMAN,18,Font.BOLD))); 
                text.add(new Paragraph(" "));
                text.add(new Paragraph(" "));
                
                document1.add(text);
                
                Paragraph p = new Paragraph(c.getDate(), new Font(Font.FontFamily.TIMES_ROMAN,14,Font.ITALIC));
                p.setAlignment(Element.ALIGN_RIGHT);
                p.add(new Paragraph(" "));
                p.add(new Paragraph(" "));
                document1.add(p);
               
                Paragraph text1= new Paragraph();
                text1.add(new Paragraph("Cher "+c.getClient().getNom() + " " +c.getClient().getPrenom(), new Font(Font.FontFamily.TIMES_ROMAN,14,Font.ITALIC)));
                text1.add(new Paragraph("Email: "+c.getClient().getEmail(), new Font(Font.FontFamily.TIMES_ROMAN,14,Font.ITALIC)));
                
                text1.add(new Paragraph(" "));
                text1.add(new Paragraph(" "));
                text1.add(new Paragraph(" "));
                text1.add(new Paragraph(" "));
                text1.add(new Paragraph(" "));
                
                document1.add(text1);
                
                PdfPTable table = new PdfPTable(3);
                //On créer l'objet cellule.
                PdfPCell cell;
                        
                cell = new PdfPCell(new Phrase("Facture"));
                cell.setColspan(3);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase("Nom du Dvd"));
                cell.setRowspan(1);
                table.addCell(cell);
                //contenu du tableau.
                table.addCell("Quantité");
                table.addCell("Prix");
                
                for (Entry<Dvd, Integer> entry : c.getDvds().entrySet()){
                    
                    Dvd dvd = (Dvd) entry.getKey();
                    Object value = entry.getValue();
                    //Image dvdimage = Image.getInstance(dvd.getImage());
                    //On créer un objet table dans lequel on intialise ça taille.
                    
                    cell = new PdfPCell(new Phrase(dvd.getTitre()));
                    cell.setRowspan(1);
                    table.addCell(cell);
                    
                    table.addCell(String.valueOf(value));
                    table.addCell(String.valueOf(dvd.getPrix()));
                    
                }
                
                cell = new PdfPCell(new Phrase("Total: " + String.valueOf(c.getMontant())));
                cell.setColspan(3);
                
                table.addCell(cell);
                    
                document1.add(table);
                Paragraph p1 = new Paragraph();
                p1.add(new Paragraph(" "));
                p1.add(new Paragraph(" "));
                p1.add(new Paragraph(" "));
                p1.add(new Paragraph(" "));
                p1.add(new Paragraph(" Grenoble Dvd: 12 avenue des ensimag 38000 Grenoble", new Font(Font.FontFamily.TIMES_ROMAN,14,Font.ITALIC)));
                p1.setAlignment(Element.ALIGN_RIGHT);
                document1.add(p1);
                document1.close();
            }  
        }
        
        if(!effectue.isEmpty()){
            for(Commande c : effectue ){
                new File("/home/huang/Commerce/Commerce/Commerce-war/web/pdf/attente"+c.getId().toString()+".pdf").delete();
                new File("/home/huang/Commerce/Commerce/Commerce-war/web/pdf/cours"+c.getId().toString()+".pdf").delete();
                
                
                        Document document1 = new Document();
                PdfWriter.getInstance(document1, new FileOutputStream("/home/aymeric/Commerce/Commerce/Commerce-war/web/pdf/effectue"+c.getId().toString()+".pdf"));

                document1.open();
                Paragraph text = new Paragraph();
                text.add(new Paragraph("Grenoble Dvd", new Font(Font.FontFamily.TIMES_ROMAN,24,Font.BOLD))); 
                text.add(new Paragraph(" "));
                text.add(new Paragraph(" "));
                text.add(new Paragraph(" "));
                text.add(new Paragraph(" "));
                text.add(new Paragraph("Facture", new Font(Font.FontFamily.TIMES_ROMAN,24,Font.BOLD))); 
                text.add(new Paragraph(" "));
                text.add(new Paragraph(" "));
                text.add(new Paragraph(" "));
                text.add(new Paragraph(" "));
                text.add(new Paragraph("Effectuée : Commande n°"+c.getId().toString(), new Font(Font.FontFamily.TIMES_ROMAN,18,Font.BOLD))); 
                text.add(new Paragraph(" "));
                text.add(new Paragraph(" "));
                
                document1.add(text);
                
                Paragraph p = new Paragraph(c.getDate(), new Font(Font.FontFamily.TIMES_ROMAN,14,Font.ITALIC));
                p.setAlignment(Element.ALIGN_RIGHT);
                p.add(new Paragraph(" "));
                p.add(new Paragraph(" "));
                document1.add(p);
               
                Paragraph text1= new Paragraph();
                text1.add(new Paragraph("Cher"+c.getClient().getNom() + " " +c.getClient().getPrenom(), new Font(Font.FontFamily.TIMES_ROMAN,14,Font.ITALIC)));
                text1.add(new Paragraph("Email: "+c.getClient().getEmail(), new Font(Font.FontFamily.TIMES_ROMAN,14,Font.ITALIC)));
                
                text1.add(new Paragraph(" "));
                text1.add(new Paragraph(" "));
                text1.add(new Paragraph(" "));
                text1.add(new Paragraph(" "));
                text1.add(new Paragraph(" "));
                
                document1.add(text1);
                
                PdfPTable table = new PdfPTable(3);
                //On créer l'objet cellule.
                PdfPCell cell;
                        
                cell = new PdfPCell(new Phrase("Facture"));
                cell.setColspan(3);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase("Nom du Dvd"));
                cell.setRowspan(1);
                table.addCell(cell);
                //contenu du tableau.
                table.addCell("Quantité");
                table.addCell("Prix");
                
                for (Entry<Dvd, Integer> entry : c.getDvds().entrySet()){
                    
                    Dvd dvd = (Dvd) entry.getKey();
                    Object value = entry.getValue();
                    //Image dvdimage = Image.getInstance(dvd.getImage());
                    //On créer un objet table dans lequel on intialise ça taille.
                    
                    cell = new PdfPCell(new Phrase(dvd.getTitre()));
                    cell.setRowspan(1);
                    table.addCell(cell);
                    
                    table.addCell(String.valueOf(value));
                    table.addCell(String.valueOf(dvd.getPrix()));
                    
                }
                
                cell = new PdfPCell(new Phrase("Total: " + String.valueOf(c.getMontant())));
                cell.setColspan(3);
                
                table.addCell(cell);
                    
                document1.add(table);
                
                Paragraph p1 = new Paragraph();
                p1.add(new Paragraph(" "));
                p1.add(new Paragraph(" "));
                p1.add(new Paragraph(" "));
                p1.add(new Paragraph(" "));
                p1.add(new Paragraph(" Grenoble Dvd: 12 avenue des ensimag 38000 Grenoble", new Font(Font.FontFamily.TIMES_ROMAN,14,Font.ITALIC)));
                p1.setAlignment(Element.ALIGN_RIGHT);
                document1.add(p1);
                document1.close();
            }
            
        }
        
        getServletContext().getRequestDispatcher("/WEB-INF/Commande.jsp").forward(request, response);
    }

    private void pageConnexion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("etat","vrai");
        getServletContext().getRequestDispatcher("/WEB-INF/Connexion.jsp").forward(request, response);
    }

    private void pageDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Dvd tempDvd = dvdf.find(Integer.toUnsignedLong(Integer.parseInt(request.getParameter("id"))));
        Set<Dvd> mySet = getThreeDvd(tempDvd);
        mySet.remove(tempDvd);
        request.setAttribute("set", mySet);
        request.setAttribute("dvd", tempDvd);
        getServletContext().getRequestDispatcher("/WEB-INF/Details.jsp").forward(request, response);
    }
    
    /**
     * Fonction qui est chargée de trouver des dvds similaires au dvd passé en paramètre
     * @param dvd, dvd en cours de lecture de la part du client et à partir duquel il faut choisir des dvds similaires
     * @return un set de dvd
     */
    private Set<Dvd> getThreeDvd(Dvd dvd){
        Set<Dvd> dvds = new HashSet<>();
        dvds.addAll(dvd.getRealisateur().getDvds());
        if (dvds.size() > 4){ //5 car il peu y avoir le dvd passé en paramètre et il en faut au moins 4
            return dvds;
        }
        dvds.addAll(dvd.getEditeur().getDvds());
        if (dvds.size() > 4){
            return dvds;
        }
        dvds.addAll(dvd.getAuteur().getDvds());
        if (dvds.size() > 4){
            return dvds;
        }
        List<Dvd> tempList = dvdf.findAll();
        dvds.addAll(tempList.subList(0, min(4,tempList.size())));
        return dvds;
    }

    private void pageForgetPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("email","vrai");
        getServletContext().getRequestDispatcher("/WEB-INF/PasswordOublie.jsp").forward(request, response);
    }

    private void pageInscription(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("etat","vrai");
        getServletContext().getRequestDispatcher("/WEB-INF/Inscription.jsp").forward(request, response);
    }

    private void pagePanier(HttpServletRequest request, HttpServletResponse response, Panier panierClient) throws ServletException, IOException {
        request.setAttribute("panier", panierClient.getDvd());
        request.setAttribute("toPay", panierClient.toPay());
        getServletContext().getRequestDispatcher("/WEB-INF/Panier.jsp").forward(request, response);
    }

    private void pageRechercherDvd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Dvd> list = (ArrayList<Dvd>) dvdf.findAll();
        request.setAttribute("listeDvds", list);
        if (request.getAttribute("setDvd") == null){
            request.setAttribute("setDvd", new ArrayList<>());
        }
        getServletContext().getRequestDispatcher("/WEB-INF/RechercheDvd.jsp").forward(request, response);
    }

    private void removeCart(HttpServletRequest request, HttpServletResponse response, Panier panier) throws ServletException, IOException {
        panier.removeDvd(dvdf.find(Integer.toUnsignedLong(Integer.parseInt(request.getParameter("id")))));
        this.pagePanier(request, response, panier);
    }

}
