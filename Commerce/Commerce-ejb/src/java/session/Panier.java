/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Client;
import entity.Commande;
import entity.Dvd;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.Remove;
import javax.ejb.StatefulTimeout;

/**
 *
 * @author aymeric
 */
@Stateful
@StatefulTimeout(unit = TimeUnit.MINUTES, value = 20)
public class Panier{
    
    @EJB
    private DvdFacade dvdf;
    
    @EJB
    private CommandeFacade commandef;
    
    @EJB
    private EmailSessionBean emailf;
    
    private HashMap<Dvd,Integer> dvdh;
    private String emailFournisseur = "aymeric.delecourt@phelma.grenoble-inp.fr";
    
    @PostConstruct
    private void initializeBean(){
        dvdh = new HashMap<>();
    }
    
    public void addDvd(Dvd dvd,int quantity){
        dvdf.decreaseQuantity(quantity,dvd);   
        if (!dvdh.containsKey(dvd)){
            this.dvdh.put(dvd,quantity);
        } else {
            this.dvdh.replace(dvd,quantity + dvdh.get(dvd));
        }

    }
    
    //Pour toutes les fonctions, prendre la valeur de dvd dans la base pour etre à jour
    
    //Remove une quantite :quantity de dvd :dvd
    public void removeDvd(Dvd dvd, int quantity){
        dvdh.replace(dvd,dvdf.find(dvd.getId()).getQuantite() + quantity);
        dvdf.increaseQuantity(quantity,dvdf.find(dvd.getId()));
    }
    
    //Retire tous les dvds du panier mais les rajoute dans la base de données
    public void removeAll(){
        for (Dvd dvd : dvdh.keySet()){
            removeDvd(dvd,dvdh.get(dvd));
        }
        dvdh.clear();
    }
    
    public HashMap<Dvd,Integer> getDvd(){
        return this.dvdh;
    }
    
    @Remove
    //On crée une commande et on ajoute la hashMap des dvds dans la commande avant d'effacer la hashMap
    public void confirmOrder(Client client) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        Commande commande = new Commande("Effectuée",client);
        for (Dvd dvd : dvdh.keySet()){
            if (dvdf.find(dvd.getId()).getQuantite() < 0){
                commande.setEtat("En Attente");
            }
        }
        commande.setDvds(dvdh);
        commandef.create(commande);
        this.dvdh.clear();
        
        if (commande.getEtat().equals("En Attente")){
            emailf.sendemail(emailFournisseur, "[Projet Jboss EJB] Commande de dvds", commande.toString());
        }
    }

}
