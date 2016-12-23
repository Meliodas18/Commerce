/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Commande;
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
        
    //Pour les commandes en attente qui peuvent passer en mode en cours
    public void changeState(Commande commande, String emailEmploye){
        if (commande.getEtat().equals("En Attente")){
            commande.setEtat("En Cours");
            edit(commande);
            sendEmail(emailEmploye, "[Projet Jboss EJB] Commandes n°" + commande.getId(), commande.toString());
        }
    }
    
}
