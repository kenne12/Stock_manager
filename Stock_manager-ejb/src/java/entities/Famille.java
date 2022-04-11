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
    @NamedQuery(name = "Famille.findAll", query = "SELECT f FROM Famille f"),
    @NamedQuery(name = "Famille.findByIdfamille", query = "SELECT f FROM Famille f WHERE f.idfamille = :idfamille"),
    @NamedQuery(name = "Famille.findByNom", query = "SELECT f FROM Famille f WHERE f.nom = :nom")})
public class Famille implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idfamille;
    @Size(max = 2147483647)
    private String nom;
    @OneToMany(mappedBy = "idfamille", fetch = FetchType.LAZY)
    private List<FactureBailleur> factureBailleurList;
    @OneToMany(mappedBy = "idfamille", fetch = FetchType.LAZY)
    private List<Produit> produitList;

    public Famille() {
    }

    public Famille(Integer idfamille) {
        /*  49 */ this.idfamille = idfamille;
    }

    public Integer getIdfamille() {
        /*  53 */ return this.idfamille;
    }

    public void setIdfamille(Integer idfamille) {
        /*  57 */ this.idfamille = idfamille;
    }

    public String getNom() {
        /*  61 */ return this.nom;
    }

    public void setNom(String nom) {
        /*  65 */ this.nom = nom;
    }

    @XmlTransient
    public List<FactureBailleur> getFactureBailleurList() {
        /*  70 */ return this.factureBailleurList;
    }

    public void setFactureBailleurList(List<FactureBailleur> factureBailleurList) {
        /*  74 */ this.factureBailleurList = factureBailleurList;
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
        /*  89 */ hash += (this.idfamille != null) ? this.idfamille.hashCode() : 0;
        /*  90 */ return hash;
    }

    @Override
    public boolean equals(Object object) {
        /*  96 */ if (!(object instanceof Famille)) {
            /*  97 */ return false;
        }
        /*  99 */ Famille other = (Famille) object;
        /* 100 */ if ((this.idfamille == null && other.idfamille != null) || (this.idfamille != null && !this.idfamille.equals(other.idfamille))) {
            /* 101 */ return false;
        }
        /* 103 */ return true;
    }

    @Override
    public String toString() {
        /* 108 */ return "entities.Famille[ idfamille=" + this.idfamille + " ]";
    }
}
