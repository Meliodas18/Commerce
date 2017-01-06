/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author aymeric
 */
@Entity
public class Editeur implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    
    @OneToMany(fetch = FetchType.EAGER,mappedBy="editeur")
    protected Set<Dvd> dvds;

    public Editeur(String nom) {
        this.nom = nom;
        this.dvds = new HashSet<>();
    }
    
    public Editeur(){
    }
    
    public String getNom() {
        return nom;
    }

    public Set<Dvd> getDvds() {
        return dvds;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDvds(Set<Dvd> dvds) {
        this.dvds = dvds;
    }
    
    public void addDvds(Dvd dvd){
        this.dvds.add(dvd);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof Editeur)) {
            return false;
        }
        Editeur other = (Editeur) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Editeur[ id=" + id + " ]";
    }
    
}
