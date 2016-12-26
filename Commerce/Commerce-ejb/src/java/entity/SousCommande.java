/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyJoinColumn;

/**
 *
 * @author aymeric
 */
@Entity
public class SousCommande implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String etat;
    
    @ManyToOne(fetch=FetchType.EAGER)
    private Commande commande;
    
    @ManyToOne
    private Editeur editeur;
    
    @ElementCollection(fetch=FetchType.EAGER)
    @MapKeyJoinColumn(name = "DVD_ID")
    @Column(name = "Quantite")
    @CollectionTable(name = "DVD_SOUSCOMMANDE")
    protected Map<Dvd,Integer> dvds; 

    public SousCommande(Commande commande, Editeur editeur, String etat) {
        this.commande = commande;
        this.editeur = editeur;
        this.dvds = new HashMap<>();
        this.etat = etat;
    }
    
    public SousCommande(){
    }

    public String getEtat() {
        return etat;
    }

    public Commande getCommande() {
        return commande;
    }

    public Editeur getEditeur() {
        return editeur;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public void setEditeur(Editeur editeur) {
        this.editeur = editeur;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Map<Dvd, Integer> getDvds() {
        return dvds;
    }

    public void addDvds(Dvd dvd, int quantite) {
        this.dvds.put(dvd, quantite);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SousCommande)) {
            return false;
        }
        SousCommande other = (SousCommande) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.SousCommande[ id=" + id + " ]";
    }
    
    public void removeCommande(){
        this.commande = null;
    }
    
    
}
