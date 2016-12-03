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
    
    private HashMap<Dvd,Integer> dvdh;
    private HashMap<Dvd,Integer> dvdToCommand;
    private String emailFournisseur = "aymeric.delecourt@phelma.grenoble-inp.fr";
    
    @PostConstruct
    private void initializeBean(){
        dvdh = new HashMap<>();
        dvdToCommand = new HashMap<>();
    }
    
    public void addDvd(Dvd dvd,int quantity){
        int quantiteActuelle = dvd.getQuantite();
        int quantiteRestante = dvdf.decreaseQuantity(quantity,dvd);   
        if (!dvdh.containsKey(dvd)){
            this.dvdh.put(dvd,quantity);
        } else {
            this.dvdh.replace(dvd,quantity + dvdh.get(dvd));
        }
        if (quantiteRestante < 0 ){
            if (!dvdToCommand.containsKey(dvd)){
                this.dvdToCommand.put(dvd, quantiteActuelle - quantiteRestante);
            } else {
                this.dvdToCommand.replace(dvd,quantity + dvdToCommand.get(dvd));
            }
        } else if (!dvdToCommand.containsKey(dvd)){ // dans ce cas j'insère quand meme le dvd pour la comande même si on va en commander 0 au fournisseur
            this.dvdToCommand.put(dvd,0);
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
    //Manque la génération d'une facture pour le client
    public void confirmOrder(Client client) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        Commande commande = new Commande("En Cours",client);
        for (Dvd dvd : dvdh.keySet()){
            if (dvdf.find(dvd.getId()).getQuantite() < 0){
                commande.setEtat("En Attente");
            }
        }
        commande.setDvds(dvdToCommand);
        commandef.create(commande,emailFournisseur);
        this.dvdh.clear();
    }

}
