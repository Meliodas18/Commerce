/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Dvd;
import java.util.HashMap;
import java.util.LinkedList;
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

    private HashMap<Dvd,Integer> dvds;
    
    @PostConstruct
    private void initializeBean(){
        dvds = new HashMap<>();
    }
    
    public void addDvd(Dvd dvd,int quantity){
        dvdf.decreaseQuantity(quantity,dvd);   
        if (!dvds.containsKey(dvd)){
            this.dvds.put(dvd,quantity);
        } else {
            this.dvds.replace(dvd,quantity + dvds.get(dvd));
        }

    }
    
    //Pour toutes les fonctions, prendre la valueur de dvd dans la base pour etre à jour
    
    //Remove une quantite :quantity de dvd :dvd
    public void removeDvd(Dvd dvd, int quantity){
        dvds.replace(dvd,dvdf.find(dvd.getId()).getQuantite() + quantity);
        dvdf.increaseQuantity(quantity,dvdf.find(dvd.getId()));
    }
    
    //Retire tous les dvds du panier mais les rajoute dans la base de données
    public void removeAll(){
        for (Dvd dvd : dvds.keySet()){
            removeDvd(dvd,dvds.get(dvd));
        }
        dvds.clear();
    }
    
    public LinkedList<Dvd> getDvd(){
        return new LinkedList<>(this.dvds.keySet());
    }
    
    @Remove
    public void confirmOrder(){
        this.dvds.clear();
    }

}
