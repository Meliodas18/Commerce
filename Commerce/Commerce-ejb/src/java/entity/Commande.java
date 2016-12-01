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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 *
 * @author aymeric
 */
@Entity
public class Commande implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String etat;
    
    @ManyToOne
    protected Client client;
    
    @ManyToMany(fetch = FetchType.EAGER,mappedBy="commande")
    protected Set<Dvd> dvds;

    public Commande(String etat, Client client) {
        this.etat = etat;
        this.client = client;
        this.dvds = new HashSet<>();
    }
    
    public Commande(){
    }

    public void setDvds(Set<Dvd> dvds) {
        this.dvds = dvds;
    }

    public Set<Dvd> getDvds() {
        return dvds;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getEtat() {
        return etat;
    }

    public Client getClient() {
        return client;
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
        if (!(object instanceof Commande)) {
            return false;
        }
        Commande other = (Commande) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", etat=" + etat + ", client=" + client + ", dvds=" + dvds + '}';
    }
    
    
}
