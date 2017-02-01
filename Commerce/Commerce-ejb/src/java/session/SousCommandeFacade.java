/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Commande;
import entity.Dvd;
import entity.SousCommande;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author aymeric
 */
@Stateless
public class SousCommandeFacade extends AbstractFacade<SousCommande> {
    
    @EJB
    private DvdFacade dvdf;
    
    @EJB
    private CommandeFacade commandef;

    @PersistenceContext(unitName = "Commerce-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SousCommandeFacade() {
        super(SousCommande.class);
    }
    
    @Override
    public void create(SousCommande sc){
        super.create(sc);
    }
    
    public void flush(){
        em.flush();
    }
    
    //Les sous-commandes n'ont rien à voir entre elles potentiellement mais elles ont le même dvd à chaque fois
    //Return -1 ne fait rien, return autre chose, alors il faut supprimer toutes les sous commandes associées à la commande
    public List<Long> changeState(List<SousCommande> sousCommandes, int quantiteRajoutee, String emailEmploye, Dvd myDvd) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        
        List<Long> result = new ArrayList<>();
        
        //Pour chaque SousCommande on essaye de remplir pour dire qu'on a reçu des dvds
        for (SousCommande sc : sousCommandes) {

            //On recupère la hashmap liant le dvd à la quantité de dvds demandées pour la sous commande sc
            Map<Dvd,Integer> dvdQuantite = sc.getDvds();

            //Pour le dvd concerné, on augmente  -- dans la hashmap pour une sous-commande on n'a qu'une seule instance d'un dvd
            for (Iterator<Dvd> it = dvdQuantite.keySet().iterator(); it.hasNext();) {
                Dvd dvd = it.next();
                //Si on a 0, il n'y a plus rien à faire pour ce dvd, on l'a bien reçu
                //Il faut prendre que les souscommandes en attente
                //Faire si c'est le dvd recherché
                if (dvd.equals(myDvd) && dvdQuantite.get(dvd) != 0 && sc.getEtat().equals("En Attente")){

                    //Si la quantité est suffisante, on change l'état, sinon on modifie juste la quantité dont on aura besoin
                    if (dvdQuantite.get(dvd) <= quantiteRajoutee){
                        quantiteRajoutee -= dvdQuantite.get(dvd);
                        it.remove(); //On retire le dvd des commandes en cours car on vient de le recevoir
                        this.edit(sc);
                    } else {
                        dvdQuantite.replace(dvd, dvdQuantite.get(dvd) - quantiteRajoutee);
                        this.edit(sc);
                        quantiteRajoutee = 0;
                    }
                }
            }
            if (dvdQuantite.isEmpty()){
                sc.setEtat("Recue");
                edit(sc);
                //Pour la commande, on regarde si on recue toutes les sous-commandes
                //Si oui, alors on peut changer etat de la commande, sinon on retourne sans rien faire
                Set<SousCommande> mySet = commandef.find(sc.getCommande().getId()).getSousCommande();
                boolean canGo = true; //permet de savoir si on va pouvoir dire que la commande est ok
                for (SousCommande mySc : mySet){
                    if ("En Attente".equals(mySc.getEtat())){
                       canGo = false; 
                    }
                }
                if (canGo){
                    commandef.changeState(sc.getCommande(), emailEmploye);
                    result.add(sc.getCommande().getId());
                }
            }
        }
        return result;
    } 
    
    //utilisé en pratique
    public void changeState(SousCommande sc, String emailEmploye){
        sc.setEtat("Reçue");
        edit(sc);
        Map<Dvd,Integer> myMap = sc.getDvds();
        for (Dvd dvd : myMap.keySet()){
            dvdf.increaseQuantity(myMap.get(dvd), dvd);
        }
        Set<SousCommande> mySet = sc.getCommande().getSousCommande();
        for (SousCommande souscom : mySet){
            if (souscom.getEtat().equals("En Attente")){
                return;
            }
        }
        commandef.changeState(sc.getCommande(), emailEmploye);
    }
    
    public void removeSousCommande(List<Long> myList){
        for(Long idCommande : myList){
            Commande temp = commandef.find(idCommande);
            for (SousCommande sc : temp.getSousCommande()){
                temp.remove(sc);
                commandef.edit(temp);
                remove(find(sc.getId()));
            }
        }
    } 
}
