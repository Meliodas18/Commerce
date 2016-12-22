/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Commande;
import entity.Dvd;
import entity.SousCommande;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author aymeric
 */
@Stateless
public class DvdFacade extends AbstractFacade<Dvd> {

    @PersistenceContext(unitName = "Commerce-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DvdFacade() {
        super(Dvd.class);
    }
    
    public int decreaseQuantity(int quantite, Dvd dvd){
        Dvd dvdTemp = this.find(dvd.getId());
        if (dvdTemp != null){
            dvdTemp.setQuantite(dvdTemp.getQuantite() - quantite);
            this.edit(dvdTemp);
        } 
        return dvdTemp.getQuantite();
    }
    
    public void increaseQuantity(int quantite, Dvd dvd){
        Dvd dvdTemp = this.find(dvd.getId());
        if (dvdTemp != null){
            dvdTemp.setQuantite(dvdTemp.getQuantite() + quantite);
            this.edit(dvdTemp);
        }  
    }
    
    public List<Commande> getCommande(Dvd dvd){
        Dvd dvdTemp = this.find(dvd.getId());
        if (dvdTemp != null){
            return dvdTemp.getCommande();
        } else {
            return null;
        }
    }
    
    public List<SousCommande> getSousCommande(Dvd dvd){
        Dvd dvdTemp = this.find(dvd.getId());
        if (dvdTemp != null){
            return dvdTemp.getSousCommande();
        } else {
            return null;
        }
    }
    
}
