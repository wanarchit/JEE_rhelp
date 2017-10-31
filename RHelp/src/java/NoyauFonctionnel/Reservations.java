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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "reservations")
@ManagedBean
@NamedQueries({
    @NamedQuery(name = "Reservations.findAll", query = "SELECT r FROM Reservations r")
    , @NamedQuery(name = "Reservations.findByIdResa", query = "SELECT r FROM Reservations r WHERE r.idResa = :idResa")
    , @NamedQuery(name = "Reservations.findByDateD", query = "SELECT r FROM Reservations r WHERE r.dateD = :dateD")
    , @NamedQuery(name = "Reservations.findByDateF", query = "SELECT r FROM Reservations r WHERE r.dateF = :dateF")
    , @NamedQuery(name = "Reservations.findByEtat", query = "SELECT r FROM Reservations r WHERE r.etat = :etat")})
public class Reservations implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idResa")
    private Integer idResa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateD;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateF")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateF;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "etat")
    private String etat;
    @JoinColumn(name = "numSS", referencedColumnName = "numSS")
    @ManyToOne(optional = false)
    private Employes numSS;
    @JoinColumn(name = "plaque", referencedColumnName = "plaque")
    @ManyToOne(optional = false)
    private Voiture plaque;

    public Reservations() {
    }

    public Reservations(Integer idResa) {
        this.idResa = idResa;
    }

    public Reservations(Integer idResa, Date dateD, Date dateF, String etat) {
        this.idResa = idResa;
        this.dateD = dateD;
        this.dateF = dateF;
        this.etat = etat;
    }

    public Integer getIdResa() {
        return idResa;
    }

    public void setIdResa(Integer idResa) {
        this.idResa = idResa;
    }

    public Date getDateD() {
        return dateD;
    }

    public void setDateD(Date dateD) {
        this.dateD = dateD;
    }
    
    public String displayDateD(Date dateD){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy 'à' HH'h'mm'<br />'");
        String dateDisplaying = formatter.format(dateD);
        return dateDisplaying;
    }

    public Date getDateF() {
        return dateF;
    }

    public void setDateF(Date dateF) {
        this.dateF = dateF;
    }
    
    public String displayDateF(Date dateF){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy 'à' HH'h'mm");
        String dateDisplaying = formatter.format(dateF);
        return dateDisplaying;
    }
    
    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Employes getNumSS() {
        return numSS;
    }

    public void setNumSS(Employes numSS) {
        this.numSS = numSS;
    }

    public Voiture getPlaque() {
        return plaque;
    }

    public void setPlaque(Voiture plaque) {
        this.plaque = plaque;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idResa != null ? idResa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reservations)) {
            return false;
        }
        Reservations other = (Reservations) object;
        if ((this.idResa == null && other.idResa != null) || (this.idResa != null && !this.idResa.equals(other.idResa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "NoyauFonctionnel.Reservations[ idResa=" + idResa + " ]";
    }
    
}
