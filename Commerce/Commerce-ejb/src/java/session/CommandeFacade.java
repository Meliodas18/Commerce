/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Client;
import entity.Commande;
import java.util.Iterator;
import java.util.List;
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
    
    public List<Commande> getAttente(){
        List<Commande> temp = findAll();
        Iterator<Commande> i = temp.iterator();
        while (i.hasNext()){
            if (!i.next().getEtat().equals("En Attente")){
                i.remove();
            }
        }
        return temp;
    }
    
    public List<Commande> getCours(){
        List<Commande> temp = findAll();
        Iterator<Commande> i = temp.iterator();
        while (i.hasNext()){
            if (!i.next().getEtat().equals("En Cours")){
                i.remove();
            }
        }
        return temp;
    }
    
    public List<Commande> getEffectuee(){
        List<Commande> temp = findAll();
        Iterator<Commande> i = temp.iterator();
        while (i.hasNext()){
            if (!i.next().getEtat().equals("Effectuée")){
                i.remove();
            }
        }
        return temp;
    }
    
    public List<Commande> getAttente(Client c){
        List<Commande> temp = findAll();
        Iterator<Commande> i = temp.iterator();
        while (i.hasNext()){
            Commande tempCom = i.next();
            if (!tempCom.getEtat().equals("En Attente") || !tempCom.getClient().equals(c)){
                i.remove();
            }
        }
        return temp;
    }
    
    public List<Commande> getCours(Client c){
        List<Commande> temp = findAll();
        Iterator<Commande> i = temp.iterator();
        while (i.hasNext()){
            Commande tempCom = i.next();
            if (!tempCom.getEtat().equals("En Cours") || !tempCom.getClient().equals(c)){
                i.remove();
            }
        }
        return temp;
    }
    
    public List<Commande> getEffectuee(Client c){
        List<Commande> temp = findAll();
        Iterator<Commande> i = temp.iterator();
        while (i.hasNext()){
            Commande tempCom = i.next();
            if (!tempCom.getEtat().equals("Effectuée") || !tempCom.getClient().equals(c)){
                i.remove();
            }
        }
        return temp;
    }
    
}
