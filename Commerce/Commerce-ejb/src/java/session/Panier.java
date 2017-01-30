/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Client;
import entity.Commande;
import entity.Dvd;
import entity.Editeur;
import entity.SousCommande;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
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
    private SousCommandeFacade sousCommandef;
    
    private HashMap<Dvd,Integer> dvdh;
    private HashMap<Dvd,Integer> dvdToCommand;
    private String emailFournisseur = "aymeric.delecourt@phelma.grenoble-inp.fr";
    private String emailEmploye = "aymeric.delecourt@phelma.grenoble-inp.fr";
    
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
    //Supprime le dvd du panier
    public void removeDvd(Dvd dvd){
        removeDvd(dvd,dvdh.get(dvd));
    }
    
    
    //Remove une quantite :quantity de dvd :dvd
    public void removeDvd(Dvd dvd, int quantity){
        if (dvdh.get(dvd) - quantity == 0){
            dvdh.remove(dvd);
        } else {
            dvdh.replace(dvd,dvdh.get(dvd) - quantity); 
        }
        dvdf.increaseQuantity(quantity,dvdf.find(dvd.getId()));
        //commandef.changeState(dvdf.find(dvd.getId()).getCommande(), quantity, emailEmploye);
    }
    
    //Retire tous les dvds du panier mais les rajoute dans la base de données
    public void removeAll(){
        Set<Dvd> tempSet = dvdh.keySet();
        Iterator<Dvd> it = tempSet.iterator();
        while(it.hasNext()){
            Dvd dvd = it.next();
            dvdf.increaseQuantity(dvdh.get(dvd),dvdf.find(dvd.getId()));//on replace dans la bd
        }
        dvdh.clear();
    }
    
    public HashMap<Dvd,Integer> getDvd(){
        return this.dvdh;
    }
    
    public double toPay(){
        double result = 0;
        for (Dvd dvd : dvdh.keySet()){
            result += dvd.getPrix() * dvdh.get(dvd);
        }
        return result;
    }
    
    //On crée une commande et on ajoute la hashMap des dvds dans la commande avant d'effacer la hashMap
    //Manque la génération d'une facture pour le client
    //S'il n'y a pas de dvds on ne crée pas de commande vide même si l'utilisateur tente de confirmer la commande à voir avec js
    @Remove
    public void confirmOrder(Client client) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        if (!dvdh.isEmpty()){
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
            Date date = new Date();
            Commande commande = new Commande("En Cours",client,toPay(),dateFormat.format(date));
            Set<Editeur> editeurs = new HashSet<>();
            Set<Dvd> dvds = new HashSet<>();
            SousCommande sc; //= new SousCommande(commande,editeur);
            for (Dvd dvd : dvdh.keySet()){
                if (dvdf.find(dvd.getId()).getQuantite() < 0){
                    commande.setEtat("En Attente");
                    editeurs.add(dvd.getEditeur());
                    dvds.add(dvd);
                }
            }
            commande.setDvds(dvdh);
            commandef.create(commande);
            for (Editeur editeur : editeurs){
                sc = new SousCommande(commande,editeur,"En Attente");
                for (Iterator<Dvd> it = dvds.iterator(); it.hasNext();) {
                    Dvd myDvd = it.next();
                    if (myDvd.getEditeur().equals(editeur)){
                        sc.addDvds(myDvd, dvdToCommand.get(myDvd));
                        it.remove();
                    }
                }
                sousCommandef.create(sc); 
            }
            this.dvdh.clear();
        }
    }

}
