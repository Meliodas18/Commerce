/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Dvd;
import entity.SousCommande;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import static session.EmailSessionBean.sendEmail;

/**
 *
 * @author aymeric
 */
@Stateless
public class SousCommandeFacade extends AbstractFacade<SousCommande> {

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
    
    //La souscommande passée en paramètre doit etre la bonne !!!
    public void changeState(List<SousCommande> sousCommandes, int quantiteRajoutee, String emailEmploye){
        
        //Pour chaque SousCommande on essaye de remplir pour dire qu'on a reçu des dvds
        for (SousCommande sc : sousCommandes) {

            //On recupère la hashmap liant le dvd à la quantité de dvds demandées pour la sous commande sc
            Map<Dvd,Integer> dvdQuantite = sc.getDvds();

            //Pour le dvd concerné, on augmente  -- dans la hashmap pour une sous-commande on n'a qu'une seule instance d'un dvd
            for(Dvd dvd : dvdQuantite.keySet()){

                //Si on a 0, il n'y a plus rien à faire pour ce dvd, on l'a bien reçu
                //Il faut prendre que les souscommandes en attente
                //Faire si c'est le dvd recherché
                if (dvdQuantite.get(dvd) != 0 && sc.getEtat().equals("En Attente")){

                    //Si la quantité est suffisante, on change l'état, sinon on modifie juste la quantité dont on aura besoin
                    if (dvdQuantite.get(dvd) <= quantiteRajoutee){
                        sc.setEtat("En Cours");
                        this.edit(sc);
                        quantiteRajoutee -= dvdQuantite.get(dvd);
                        sendEmail(emailEmploye, "[Projet Jboss EJB] Livraison de dvds effectuée", sc.toString());
                    } else {
                        dvdQuantite.replace(dvd, dvdQuantite.get(dvd) - quantiteRajoutee);
                        this.edit(sc);
                        quantiteRajoutee = 0;
                    }
                    break;
                }
            }
        }
    }
    
}
