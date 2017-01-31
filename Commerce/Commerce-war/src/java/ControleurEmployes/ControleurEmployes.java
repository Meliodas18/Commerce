/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControleurEmployes;

import Controleur.Controleur;
import entity.Auteur;
import entity.Categorie;
import entity.Commande;
import entity.Dvd;
import entity.Editeur;
import entity.Employe;
import entity.Realisateur;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import session.AuteurFacade;
import session.CommandeFacade;
import session.DvdFacade;
import session.EditeurFacade;
import session.EmployeFacade;
import session.Panier;
import session.RealisateurFacade;
import session.SousCommandeFacade;
/*import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;*/

import java.io.FileOutputStream;
import java.util.Set;
import session.CategorieFacade;

/**
 *
 * @author aymeric
 */
@WebServlet(name = "ControleurEmployes", urlPatterns = {"/ControleurEmployes"})
@MultipartConfig
public class ControleurEmployes extends HttpServlet {

    @EJB
    private DvdFacade dvdf;
    
    @EJB
    private AuteurFacade auteurf;
    
    @EJB
    private EmployeFacade employef;
    
    @EJB
    private RealisateurFacade realisateurf;
    
    @EJB
    private CommandeFacade commandef;
    
    @EJB
    private SousCommandeFacade sousCommandef;
    
    @EJB
    private CategorieFacade categorief;
    
    private String emailEmploye = "aymeric.delecourt@phelma.grenoble-inp.fr";
    
    @EJB
    private EditeurFacade editeurf;
    
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
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
            case "pageAjouterDvd":
                pageAjouterDvd(request,response);
                break;
            case "ajouterDvd":
                ajouterDvd(request,response);
                break;
            case "pageConnexionEmploye":
                pageConnexionEmploye(request,response);
                break;
            case "connexionEmploye":
                connexionEmploye(request,response);
                break;
            case "pageCommandes":
                pageCommandes(request,response);
                break;
            case "pageLivraisons":
                pageLivraisons(request,response);
                break;
            case "livraisons":
                livraisons(request,response);
                break;
            case "pageEnvoiColis":
                pageEnvoiColis(request,response);
                break;
            case "envoiColis":
                envoiColis(request,response);
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
        } catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(ControleurEmployes.class.getName()).log(Level.SEVERE, null, ex);
        }/* catch (DocumentException ex) {
            Logger.getLogger(ControleurEmployes.class.getName()).log(Level.SEVERE, null, ex);
        }*/
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
        } catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(ControleurEmployes.class.getName()).log(Level.SEVERE, null, ex);
        }/* catch (DocumentException ex) {
            Logger.getLogger(ControleurEmployes.class.getName()).log(Level.SEVERE, null, ex);
        }*/
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

    /**
     * Permet de passer d'une chaîne de caractères avec des accents dans le mauvais format en utf-8
     * @param s String en provenanace de la request en ISo_8859_1
     * @return La même chaîne de caractère mais en utf-8
     */
    private String goodString(String s){
        byte[] bytes = s.getBytes(StandardCharsets.ISO_8859_1);
        return new String(bytes, StandardCharsets.UTF_8); 
    }
    
    
    private void pageAjouterDvd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Categorie> tempList = categorief.findAll();
        request.setAttribute("Cat",tempList);
        getServletContext().getRequestDispatcher("/WEB-INF/AjoutDvds.jsp").forward(request, response);
    }
    
    //Attention aux exceptions -- doivent pas etre lancées ici à priori
    //!!! Changer le path
    private void ajouterDvd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
         
        String path = System.getProperty("user.dir") + "../../../Commerce/Commerce/Commerce-war/web/images";
        Part filePart = request.getPart("file");
        String fileName = getFileName(filePart);

        OutputStream out = null;
        InputStream filecontent = null;
    
        try {
            out = new FileOutputStream(new File(path + File.separator
                    + fileName));
            filecontent = filePart.getInputStream();

            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = filecontent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
        } catch (FileNotFoundException fne) {

        } finally {
            if (out != null) {
                out.close();
            }
            if (filecontent != null) {
                filecontent.close();
            }

        }
          
        Dvd dvd = new Dvd(goodString(request.getParameter("titre")), goodString(request.getParameter("description")), Double.parseDouble(request.getParameter("prix")), request.getParameter("dateSortie"), Integer.parseInt(request.getParameter("quantite")), path + File.separator+ fileName);
        String[] paramDvd = {"titre","description","prix","dateSortie"};
        String[] param = {"prenom","nom"};
        String[] paramEditeur = {"nom"};
        ArrayList<Long> id;
        
        //Si le dvd n'existe pas, on crée le dvd
        if (dvdf.getId(dvd, paramDvd).isEmpty()){
            dvdf.create(dvd);
            
            Auteur auteur = new Auteur(goodString(request.getParameter("prenomAuteur")),goodString(request.getParameter("nomAuteur")));
            id = auteurf.getId(auteur, param);
            if (id.isEmpty()){
                auteur.addDvds(dvd);
                auteurf.create(auteur);
            } else {
                auteur = auteurf.find(id.get(0));
                auteur.addDvds(dvd);
                auteurf.edit(auteur);
            }
            
            Realisateur realisateur = new Realisateur(goodString(request.getParameter("prenomRealisateur")),goodString(request.getParameter("nomRealisateur")));
            id = realisateurf.getId(realisateur, param);
            if (id.isEmpty()){
                realisateurf.create(realisateur);
            } else {
                realisateur = realisateurf.find(id.get(0));
                realisateurf.edit(realisateur);
            }
            dvd.setRealisateur(realisateur);
            
            Editeur editeur = new Editeur(goodString(request.getParameter("nomEditeur")));
            id = editeurf.getId(editeur, paramEditeur);
            if (id.isEmpty()){
                editeurf.create(editeur);
            } else {
                editeur = editeurf.find(id.get(0));
                editeurf.edit(editeur);
            }
            dvd.setEditeur(editeur);
            
            Categorie tempCat = new Categorie();
            tempCat.setType(request.getParameter("categorie"));
            String[] myParam = {"type"};
            Categorie categorie = categorief.find(categorief.getId(tempCat, myParam).get(0));
            dvd.setCategories(categorie);
            dvdf.edit(dvd);
        }
        getServletContext().getRequestDispatcher("/WEB-INF/Accueil.jsp").forward(request, response);

    }
    
    private String getFileName( Part part) {
    String partHeader = part.getHeader("content-disposition");
    for (String content : part.getHeader("content-disposition").split(";")) {
        if (content.trim().startsWith("filename")) {
            return content.substring(
                    content.indexOf('=') + 1).trim().replace("\"", "");
        }
    }
    return null;
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
    
    //Affiche les commandes
    private void pageCommandes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setAttribute("attente",commandef.getAttente());
        request.setAttribute("cours",commandef.getCours());
        request.setAttribute("effectuee",commandef.getEffectuee());
        
        /*List<Commande> attente = commandef.getAttente();
        List<Commande> cours = commandef.getCours();
        List<Commande> effectue = commandef.getEffectuee();
        if(!attente.isEmpty()){
          for(Commande c : attente ){
            Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream("/home/huang/Commerce/Commerce/Commerce-war/web/pdf/attente"+c.getId().toString()+".pdf"));

                document.open();
                Paragraph text = new Paragraph();
                text.add(new Paragraph(" "));
                text.add(new Paragraph(" "));
                text.add(new Paragraph("En attente : Commande n°"+c.getId().toString(),new Font(Font.FontFamily.TIMES_ROMAN,18,Font.BOLD))); 
                text.add(new Paragraph(" "));
                text.add(new Paragraph(" "));
                Set listKeys=c.getDvds().keySet();
                Iterator it = listKeys.iterator();
                    while (it.hasNext()){
                       Object cle = it.next(); 
                       Dvd dvd = (Dvd) cle;
                       text.add(new Paragraph("Titre du Dvd: "+dvd.getTitre(),new Font(Font.FontFamily.TIMES_ROMAN,18,Font.BOLD)));
                       text.add(new Paragraph(" "));
                       Image dvdimage = Image.getInstance(dvd.getImage());
                       
                       text.add(dvdimage);
                       text.add(new Paragraph(" "));
                       text.add(new Paragraph("Date de Sortie: "+dvd.getDateSortie(), new Font(Font.FontFamily.TIMES_ROMAN,14,Font.BOLD)));
                       text.add(new Paragraph(" "));
                       text.add(new Paragraph("Quantité: "+dvd.getQuantite(), new Font(Font.FontFamily.TIMES_ROMAN,14,Font.BOLD)));
                       text.add(new Paragraph(" "));
                       text.add(new Paragraph("Description: "+dvd.getDescription(), new Font(Font.FontFamily.TIMES_ROMAN,12,Font.ITALIC)));
                       text.add(new Paragraph(" "));
                       text.add(new Paragraph(" "));
                       text.add(new Paragraph(" "));
                    }
                    
                text.add(new Paragraph(" "));
                text.add(new Paragraph(" "));
                
                text.add(new Paragraph(c.getDate(), new Font(Font.FontFamily.TIMES_ROMAN,14,Font.ITALIC)));
                
                text.add(new Paragraph(" "));
                text.add(new Paragraph("Prix : "+String.valueOf(c.getMontant()), new Font(Font.FontFamily.TIMES_ROMAN,18,Font.BOLD)));
                document.add(text);

                document.close();
        }  
        }
        
        if(!cours.isEmpty()){
          for(Commande c : cours ){
            Document document1 = new Document();
                PdfWriter.getInstance(document1, new FileOutputStream("/home/huang/Commerce/Commerce/Commerce-war/web/pdf/cours"+c.getId().toString()+".pdf"));

                document1.open();
                Paragraph text = new Paragraph();
                text.add(new Paragraph(" "));
                text.add(new Paragraph(" "));
                text.add(new Paragraph("En cours : Commande n°"+c.getId().toString(), new Font(Font.FontFamily.TIMES_ROMAN,18,Font.BOLD))); 
                text.add(new Paragraph(" "));
                text.add(new Paragraph(" "));
                
                text.add(new Paragraph(" "));
                
                Set listKeys=c.getDvds().keySet();
                Iterator it = listKeys.iterator();
                    while (it.hasNext()){
                       Object cle = it.next(); 
                       Dvd dvd = (Dvd) cle;
                       Image dvdimage = Image.getInstance(dvd.getImage());
                       
                       text.add(new Paragraph("Titre du Dvd: "+dvd.getTitre(),new Font(Font.FontFamily.TIMES_ROMAN,18,Font.BOLD)));
                       text.add(new Paragraph(" "));
                       text.add(dvdimage);
                       text.add(new Paragraph(" "));
                       text.add(new Paragraph("Date de Sortie: "+dvd.getDateSortie(), new Font(Font.FontFamily.TIMES_ROMAN,14,Font.BOLD)));
                       text.add(new Paragraph(" "));
                       text.add(new Paragraph("Quantité: "+dvd.getQuantite(), new Font(Font.FontFamily.TIMES_ROMAN,14,Font.BOLD)));
                       text.add(new Paragraph(" "));
                       text.add(new Paragraph("Description: "+dvd.getDescription(), new Font(Font.FontFamily.TIMES_ROMAN,12,Font.ITALIC)));
                       text.add(new Paragraph(" "));
                       text.add(new Paragraph(" "));
                       text.add(new Paragraph(" "));
                    }
                    
                text.add(new Paragraph(" "));
                text.add(new Paragraph(" "));
                
                text.add(new Paragraph(c.getDate(), new Font(Font.FontFamily.TIMES_ROMAN,14,Font.ITALIC)));
                
                text.add(new Paragraph(" "));
                text.add(new Paragraph("Prix : "+String.valueOf(c.getMontant()), new Font(Font.FontFamily.TIMES_ROMAN,18,Font.BOLD)));
                document1.add(text);

                document1.close();
        }  
        }
        
        if(!effectue.isEmpty()){
            for(Commande c : effectue ){
                new File("/home/huang/Commerce/Commerce/Commerce-war/web/pdf/attente"+c.getId().toString()+".pdf").delete();
                new File("/home/huang/Commerce/Commerce/Commerce-war/web/pdf/cours"+c.getId().toString()+".pdf").delete();
                
            }
        }
        */
        
        
        
        
        getServletContext().getRequestDispatcher("/WEB-INF/Commande.jsp").forward(request, response);
    }
    
    private void livraisons(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Dvd dvd = dvdf.find(Long.parseLong((request.getParameter("id"))));
        if (dvd == null){
            request.setAttribute("etat","faux");
        } else {
            request.setAttribute("etat","possible");
            dvdf.increaseQuantity(Integer.parseInt(request.getParameter("quantite")),dvd);
            List<Long> tempId = sousCommandef.changeState(dvd.getSousCommande(),Integer.parseInt(request.getParameter("quantite")), emailEmploye,dvd);
            if (!tempId.isEmpty()){
                sousCommandef.removeSousCommande(tempId);
            }
        }
        getServletContext().getRequestDispatcher("/WEB-INF/Livraisons.jsp").forward(request, response);
    }

    private void pageLivraisons(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("etat","possible");
        getServletContext().getRequestDispatcher("/WEB-INF/Livraisons.jsp").forward(request, response);
    }

    //Il faut vérifier que la commande n'est pas en attente !
    private void envoiColis(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Commande commande = commandef.find(Integer.toUnsignedLong(Integer.parseInt(request.getParameter("id"))));
        if (commande == null){
            request.setAttribute("etat","faux");
        } else {
            if (commande.getEtat().equals("En Attente")){
                request.setAttribute("etat", "impossible");
            } else {
                commande.setEtat("Effectuée");
                commandef.edit(commande);
                request.setAttribute("etat", "possible");
            }
        }
        getServletContext().getRequestDispatcher("/WEB-INF/EnvoiColis.jsp").forward(request, response);
    }

    //Etat possible car on ne veut rien afficher comme erreur dans la page - voir envoiColis
    private void pageEnvoiColis(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("etat", "possible");
        getServletContext().getRequestDispatcher("/WEB-INF/EnvoiColis.jsp").forward(request, response);
    }
    
}
