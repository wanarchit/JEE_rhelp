/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NoyauFonctionnel;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Paul
 */
@Entity
@Table(name = "userinfo")
@ManagedBean
@NamedQueries({
    @NamedQuery(name = "Userinfo.findAll", query = "SELECT u FROM Userinfo u")
    , @NamedQuery(name = "Userinfo.findById", query = "SELECT u FROM Userinfo u WHERE u.id = :id")
    , @NamedQuery(name = "Userinfo.findByUser", query = "SELECT u FROM Userinfo u WHERE u.user = :user")
    , @NamedQuery(name = "Userinfo.findByMdp", query = "SELECT u FROM Userinfo u WHERE u.mdp = :mdp")})
public class Userinfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "user")
    private String user;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "mdp")
    private String mdp;
    @JoinColumn(name = "numSS", referencedColumnName = "numSS")
    @ManyToOne(optional = false)
    private Employes numSS;

    public Userinfo() {
    }

    public Userinfo(Integer id) {
        this.id = id;
    }

    public Userinfo(Integer id, String user, String mdp) {
        this.id = id;
        this.user = user;
        this.mdp = mdp;
    }
    
    public Userinfo(Integer id, String user, String mdp, Employes numSS) {
        this.id = id;
        this.user = user;
        this.mdp = mdp;
        this.numSS = numSS;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public Employes getNumSS() {
        return numSS;
    }

    public void setNumSS(Employes numSS) {
        this.numSS = numSS;
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
        if (!(object instanceof Userinfo)) {
            return false;
        }
        Userinfo other = (Userinfo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "NoyauFonctionnel.Userinfo[ id=" + id + " ]";
    }
    
}
