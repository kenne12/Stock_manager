package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Formeproduit.findAll", query = "SELECT f FROM Formeproduit f"),
    @NamedQuery(name = "Formeproduit.findByIdforme", query = "SELECT f FROM Formeproduit f WHERE f.idforme = :idforme"),
    @NamedQuery(name = "Formeproduit.findByCode", query = "SELECT f FROM Formeproduit f WHERE f.code = :code"),
    @NamedQuery(name = "Formeproduit.findByNom", query = "SELECT f FROM Formeproduit f WHERE f.nom = :nom")})
public class Formeproduit implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idforme;
    @Size(max = 2147483647)
    private String code;
    @Size(max = 2147483647)
    private String nom;
    @OneToMany(mappedBy = "idforme", fetch = FetchType.LAZY)
    private List<Produit> produitList;

    public Formeproduit() {
    }

    public Formeproduit(Integer idforme) {
        /*  50 */ this.idforme = idforme;
    }

    public Integer getIdforme() {
        /*  54 */ return this.idforme;
    }

    public void setIdforme(Integer idforme) {
        /*  58 */ this.idforme = idforme;
    }

    public String getCode() {
        /*  62 */ return this.code;
    }

    public void setCode(String code) {
        /*  66 */ this.code = code;
    }

    public String getNom() {
        /*  70 */ return this.nom;
    }

    public void setNom(String nom) {
        /*  74 */ this.nom = nom;
    }

    @XmlTransient
    public List<Produit> getProduitList() {
        /*  79 */ return this.produitList;
    }

    public void setProduitList(List<Produit> produitList) {
        /*  83 */ this.produitList = produitList;
    }

    @Override
    public int hashCode() {
        /*  88 */ int hash = 0;
        /*  89 */ hash += (this.idforme != null) ? this.idforme.hashCode() : 0;
        /*  90 */ return hash;
    }

    @Override
    public boolean equals(Object object) {
        /*  96 */ if (!(object instanceof Formeproduit)) {
            /*  97 */ return false;
        }
        /*  99 */ Formeproduit other = (Formeproduit) object;
        /* 100 */ if ((this.idforme == null && other.idforme != null) || (this.idforme != null && !this.idforme.equals(other.idforme))) {
            /* 101 */ return false;
        }
        /* 103 */ return true;
    }

    @Override
    public String toString() {
        /* 108 */ return "entities.Formeproduit[ idforme=" + this.idforme + " ]";
    }
}
