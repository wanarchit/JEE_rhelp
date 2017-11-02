/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NoyauFonctionnel;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "employes")
@ManagedBean
@NamedQueries({
    @NamedQuery(name = "Employes.findAll", query = "SELECT e FROM Employes e")
    , @NamedQuery(name = "Employes.findByNumSS", query = "SELECT e FROM Employes e WHERE e.numSS = :numSS")
    , @NamedQuery(name = "Employes.findByNom", query = "SELECT e FROM Employes e WHERE e.nom = :nom")
    , @NamedQuery(name = "Employes.findByPrenom", query = "SELECT e FROM Employes e WHERE e.prenom = :prenom")
    , @NamedQuery(name = "Employes.findByAdresse", query = "SELECT e FROM Employes e WHERE e.adresse = :adresse")
    , @NamedQuery(name = "Employes.findByDateNaissance", query = "SELECT e FROM Employes e WHERE e.dateNaissance = :dateNaissance")
    , @NamedQuery(name = "Employes.findBySexe", query = "SELECT e FROM Employes e WHERE e.sexe = :sexe")
    , @NamedQuery(name = "Employes.findByMail", query = "SELECT e FROM Employes e WHERE e.mail = :mail")})

public class Employes implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "numSS")
    private List<Userinfo> userinfoList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "numSS")
    private List<Reservations> reservationsList;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "numSS")
    private String numSS;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nom")
    private String nom;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "prenom")
    private String prenom;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "adresse")
    private String adresse;
    @Basic(optional = false)
    @NotNull(message = "Vous devez renseigner la date de naissance")
    @Column(name = "dateNaissance")
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;
    @Basic(optional = false)
    @NotNull(message = "Vous devez renseigner le sexe")
    @Column(name = "sexe")
    private boolean sexe;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "mail")
    private String mail;

    public Employes() {
    }

    public Employes(String numSS) {
        this.numSS = numSS;
    }

    public Employes(String numSS, String nom, String prenom, String adresse) {
        this.numSS = numSS;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
    }

    public String getNumSS() {
        return numSS;
    }

    public void setNumSS(String numSS) {
        this.numSS = numSS;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    
    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }
    
    public String displayDate(Date dateNaissance){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateDisplaying = formatter.format(dateNaissance);
        return dateDisplaying;
    }
    
    public boolean getSexe() {
        return sexe;
    }

    public void setSexe(boolean sexe) {
        this.sexe = sexe;
    }
    
    public String displaySexe(Boolean sexe){
        if(sexe){
            return "H";
        }else{
            return "F";
        }
    }
    
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numSS != null ? numSS.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Employes)) {
            return false;
        }
        Employes other = (Employes) object;
        if ((this.numSS == null && other.numSS != null) || (this.numSS != null && !this.numSS.equals(other.numSS))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "NoyauFonctionnel.Employes[ numSS=" + numSS + " ]";
    }

    public List<Reservations> getReservationsList() {
        return reservationsList;
    }

    public void setReservationsList(List<Reservations> reservationsList) {
        this.reservationsList = reservationsList;
    }

    public boolean isReserve(Date dateDemandD, Date dateDemandF){
        boolean valid = true;
        for (Reservations resa : getReservationsList()) {
            if (resa.getNumSS().equals(this)) {
                if(resa.getDateD().before(dateDemandD) && resa.getDateF().after(dateDemandD)){
                    valid = false;
                }
                if(resa.getDateD().after(dateDemandD) && resa.getDateF().before(dateDemandF)){
                    valid = false;
                }
                if(resa.getDateD().before(dateDemandF) && resa.getDateF().after(dateDemandF)){
                    valid = false;
                }
            }
        }
        return valid;
    }

    public List<Userinfo> getUserinfoList() {
        return userinfoList;
    }

    public void setUserinfoList(List<Userinfo> userinfoList) {
        this.userinfoList = userinfoList;
    }
    
    
}
