/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.EntityTest;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author aymeric
 */
@Stateless
@LocalBean
public class SessionTest {
    
    @PersistenceContext
    private EntityManager em;

    public String toto(){
        EntityTest test = new EntityTest("Coucou !");
        em.persist(test);
        return "CouCou !";
    }
}
