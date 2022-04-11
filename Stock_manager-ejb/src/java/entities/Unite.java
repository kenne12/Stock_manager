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
    @NamedQuery(name = "Unite.findAll", query = "SELECT u FROM Unite u"),
    @NamedQuery(name = "Unite.findByIdUnite", query = "SELECT u FROM Unite u WHERE u.idUnite = :idUnite"),
    @NamedQuery(name = "Unite.findByCode", query = "SELECT u FROM Unite u WHERE u.code = :code"),
    @NamedQuery(name = "Unite.findByNom", query = "SELECT u FROM Unite u WHERE u.nom = :nom")})
public class Unite implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_unite")
    private Integer idUnite;
    @Size(max = 2147483647)
    private String code;
    @Size(max = 2147483647)
    private String nom;
    @OneToMany(mappedBy = "idUnite", fetch = FetchType.LAZY)
    private List<Produit> produitList;

    public Unite() {
    }

    public Unite(Integer idUnite) {
        /*  52 */ this.idUnite = idUnite;
    }

    public Integer getIdUnite() {
        /*  56 */ return this.idUnite;
    }

    public void setIdUnite(Integer idUnite) {
        /*  60 */ this.idUnite = idUnite;
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
        /*  91 */ hash += (this.idUnite != null) ? this.idUnite.hashCode() : 0;
        /*  92 */ return hash;
    }

    @Override
    public boolean equals(Object object) {
        /*  98 */ if (!(object instanceof Unite)) {
            /*  99 */ return false;
        }
        /* 101 */ Unite other = (Unite) object;
        /* 102 */ if ((this.idUnite == null && other.idUnite != null) || (this.idUnite != null && !this.idUnite.equals(other.idUnite))) {
            /* 103 */ return false;
        }
        /* 105 */ return true;
    }

    @Override
    public String toString() {
        /* 110 */ return "entities.Unite[ idUnite=" + this.idUnite + " ]";
    }
}
