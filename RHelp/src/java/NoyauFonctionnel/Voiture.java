/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NoyauFonctionnel;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Paul
 */
@Entity
@Table(name = "voiture")
@ManagedBean
@NamedQueries({
    @NamedQuery(name = "Voiture.findAll", query = "SELECT v FROM Voiture v")
    , @NamedQuery(name = "Voiture.findByPlaque", query = "SELECT v FROM Voiture v WHERE v.plaque = :plaque")
    , @NamedQuery(name = "Voiture.findByMarque", query = "SELECT v FROM Voiture v WHERE v.marque = :marque")
    , @NamedQuery(name = "Voiture.findByModele", query = "SELECT v FROM Voiture v WHERE v.modele = :modele")
    , @NamedQuery(name = "Voiture.findByKilometrage", query = "SELECT v FROM Voiture v WHERE v.kilometrage = :kilometrage")
    , @NamedQuery(name = "Voiture.findByDateRevision", query = "SELECT v FROM Voiture v WHERE v.dateRevision = :dateRevision")
    , @NamedQuery(name = "Voiture.findByDisponibilite", query = "SELECT v FROM Voiture v WHERE v.disponibilite = :disponibilite")})
public class Voiture implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "plaque")
    private String plaque;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "marque")
    private String marque;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "modele")
    private String modele;
    @Basic(optional = false)
    @NotNull
    @Column(name = "kilometrage")
    private int kilometrage;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateRevision")
    @Temporal(TemporalType.DATE)
    private Date dateRevision;
    @Basic(optional = false)
    @NotNull
    @Column(name = "disponibilite")
    private boolean disponibilite;

    public Voiture() {
    }

    public Voiture(String plaque) {
        this.plaque = plaque;
    }

    public Voiture(String plaque, String marque, String modele, int kilometrage, Date dateRevision, boolean disponibilite) {
        this.plaque = plaque;
        this.marque = marque;
        this.modele = modele;
        this.kilometrage = kilometrage;
        this.dateRevision = dateRevision;
        this.disponibilite = disponibilite;
    }

    public String getPlaque() {
        return plaque;
    }

    public void setPlaque(String plaque) {
        this.plaque = plaque;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public int getKilometrage() {
        return kilometrage;
    }

    public void setKilometrage(int kilometrage) {
        this.kilometrage = kilometrage;
    }

    public Date getDateRevision() {
        return dateRevision;
    }

    public void setDateRevision(Date dateRevision) {
        this.dateRevision = dateRevision;
    }
    
    public String displayDate(Date dateRevision){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateDisplaying = formatter.format(dateRevision);
        return dateDisplaying;
    }

    public boolean getDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(boolean disponibilite) {
        this.disponibilite = disponibilite;
    }
    
    public String displayDispo(Boolean dispo){
        if(dispo){
            return "Disponible";
        }else{
            return "Non disponible";
        }
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (plaque != null ? plaque.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Voiture)) {
            return false;
        }
        Voiture other = (Voiture) object;
        if ((this.plaque == null && other.plaque != null) || (this.plaque != null && !this.plaque.equals(other.plaque))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "NoyauFonctionnel.Voiture[ plaque=" + plaque + " ]";
    }
    
}
