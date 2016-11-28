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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 *
 * @author aymeric
 */
@Entity
public class Dvd implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String titre;
    private String description;
    private Double prix;
    private String dateSortie;
    private int quantite;

    public Dvd(String titre, String description, Double prix, String dateSortie, int quantite) {
        this.titre = titre;
        this.description = description;
        this.prix = prix;
        this.dateSortie = dateSortie;
        this.quantite = quantite;
        this.auteurs = new HashSet<>();
    }
        
    public Dvd(){
    }
    
    public String getTitre() {
        return titre;
    }   

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrix() {
        return prix;
    }

    public String getDateSortie() {
        return dateSortie;
    }

    public int getQuantite() {
        return quantite;
    }
    
    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setId(Long id) {
        this.id = id;
    }
        
    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public void setDateSortie(String dateSortie) {
        this.dateSortie = dateSortie;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
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
        if (!(object instanceof Dvd)) {
            return false;
        }
        Dvd other = (Dvd) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Dvd{" + "id=" + id + ", titre=" + titre + ", description=" + description + ", prix=" + prix + ", dateSortie=" + dateSortie + ", quantite=" + quantite + '}';
    }
    
    @ManyToMany(mappedBy="dvds")
    protected Set<Auteur> auteurs;
    

    public Set<Auteur> getAuteurs() {
        return auteurs;
    }

    public void setAuteurs(Set<Auteur> auteurs) {
        this.auteurs = auteurs;
    }
    
    public void addAuteurs(Auteur auteur) {
        this.auteurs.add(auteur);
    }
    
    
    
}
