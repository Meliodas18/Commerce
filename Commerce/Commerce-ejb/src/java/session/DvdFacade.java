/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Dvd;
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
    
    public void decreaseQuantity(int quantite, Dvd dvd){
        dvd.setQuantite(dvd.getQuantite() - quantite);
        this.edit(dvd); //très important à faire !!! 
    }
    
    public void increaseQuantity(int quantite, Dvd dvd){
        dvd.setQuantite(dvd.getQuantite() + quantite);
        this.edit(dvd);
    }
    
}
