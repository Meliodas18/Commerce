/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

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
    @Size(max=2000)
    private String description;
    private Double prix;
    private String dateSortie;
    private int quantite;
    private String image;

    public Dvd(String titre, String description, Double prix, String dateSortie, int quantite, String image) {
        this.titre = titre;
        this.description = description;
        this.prix = prix;
        this.dateSortie = dateSortie;
        this.quantite = quantite;
        this.auteurs = new HashSet<>();
        this.commande = new ArrayList<>();
        this.image=image;
    }

    public String getImage() {
        return image;
    }
        
    
    public Dvd(){
    }
    
    public String getTitre() {
        return titre;
    }   

    public long getId() {
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

    @ManyToMany(fetch=FetchType.EAGER)
    protected List<Commande> commande;
    
    @ManyToMany(fetch=FetchType.EAGER)
    protected List<SousCommande> sousCommande;
    
    public List<SousCommande> getSousCommande(){
        return sousCommande;
    }
    
    public List<Commande> getCommande() {
        return commande;
    }

    public void setSousCommande(ArrayList<SousCommande> sousCommandes){
        this.sousCommande = sousCommandes;
    }
    
    public void setCommande(ArrayList<Commande> commande) {
        this.commande = commande;
    }

    @ManyToMany(mappedBy="dvds",fetch=FetchType.EAGER)
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
    
    @ManyToOne
    private Editeur editeur;

    public Editeur getEditeur() {
        return editeur;
    }

    public void setEditeur(Editeur editeur) {
        this.editeur = editeur;
    }    
    
    @ManyToOne(fetch=FetchType.EAGER)
    private Realisateur realisateur;

    public Realisateur getRealisateur() {
        return realisateur;
    }

    public void setRealisateur(Realisateur realisateur) {
        this.realisateur = realisateur;
    }
       
}
