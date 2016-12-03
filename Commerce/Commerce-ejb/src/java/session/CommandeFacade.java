/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Commande;
import entity.Dvd;
import java.util.Map;
import java.util.Set;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import static session.EmailSessionBean.sendEmail;

/**
 *
 * @author aymeric
 */
@Stateless
public class CommandeFacade extends AbstractFacade<Commande> {

    @PersistenceContext(unitName = "Commerce-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CommandeFacade() {
        super(Commande.class);
    }
    
    public void create(Commande commande, String emailFournisseur){
        super.create(commande);
        if (commande.getEtat().equals("En Attente")){
            sendEmail(emailFournisseur, "[Projet Jboss EJB] Commande de dvds", commande.toString());
        }
    }
    
    public void changeState(Set<Commande> commandes, int quantiteRajoutee){
        for (Commande com : commandes) {
            Map<Dvd,Integer> dvdQuantite = com.getDvds();
            for(Dvd dvd : dvdQuantite.keySet()){
                
                //Si la valeur vaut 0, tous les dvds sont déjà là donc rien à faire
                if (dvdQuantite.get(dvd) != 0 && com.getEtat().equals("En Attente")){
                    if (dvdQuantite.get(dvd) <= quantiteRajoutee){
                        com.setEtat("En Cours");
                        this.edit(com);
                        quantiteRajoutee -= dvdQuantite.get(dvd);
                    } else {
                        dvdQuantite.replace(dvd, dvdQuantite.get(dvd) - quantiteRajoutee);
                        this.edit(com);
                        quantiteRajoutee = 0;
                    }
                }
            }
        }      
    }
    
}
