package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
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
    @NamedQuery(name = "Formestockage.findAll", query = "SELECT f FROM Formestockage f"),
    @NamedQuery(name = "Formestockage.findByIdformeStockage", query = "SELECT f FROM Formestockage f WHERE f.idformeStockage = :idformeStockage"),
    @NamedQuery(name = "Formestockage.findByCode", query = "SELECT f FROM Formestockage f WHERE f.code = :code"),
    @NamedQuery(name = "Formestockage.findByNom", query = "SELECT f FROM Formestockage f WHERE f.nom = :nom")})
public class Formestockage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idforme_stockage")
    private Integer idformeStockage;
    @Size(max = 2147483647)
    private String code;
    @Size(max = 2147483647)
    private String nom;
    @OneToMany(mappedBy = "idformeStockage", fetch = FetchType.LAZY)
    private List<Produit> produitList;

    public Formestockage() {
    }

    public Formestockage(Integer idformeStockage) {
        /*  52 */ this.idformeStockage = idformeStockage;
    }

    public Integer getIdformeStockage() {
        /*  56 */ return this.idformeStockage;
    }

    public void setIdformeStockage(Integer idformeStockage) {
        /*  60 */ this.idformeStockage = idformeStockage;
    }

    public String getCode() {
        /*  64 */ return this.code;
    }

    public void setCode(String code) {
        /*  68 */ this.code = code;
    }

    public String getNom() {
        /*  72 */ return this.nom;
    }

    public void setNom(String nom) {
        /*  76 */ this.nom = nom;
    }

    @XmlTransient
    public List<Produit> getProduitList() {
        /*  81 */ return this.produitList;
    }

    public void setProduitList(List<Produit> produitList) {
        /*  85 */ this.produitList = produitList;
    }

    @Override
    public int hashCode() {
        /*  90 */ int hash = 0;
        /*  91 */ hash += (this.idformeStockage != null) ? this.idformeStockage.hashCode() : 0;
        /*  92 */ return hash;
    }

    @Override
    public boolean equals(Object object) {
        /*  98 */ if (!(object instanceof Formestockage)) {
            /*  99 */ return false;
        }
        /* 101 */ Formestockage other = (Formestockage) object;
        /* 102 */ if ((this.idformeStockage == null && other.idformeStockage != null) || (this.idformeStockage != null && !this.idformeStockage.equals(other.idformeStockage))) {
            /* 103 */ return false;
        }
        /* 105 */ return true;
    }

    @Override
    public String toString() {
        /* 110 */ return "entities.Formestockage[ idformeStockage=" + this.idformeStockage + " ]";
    }
}
