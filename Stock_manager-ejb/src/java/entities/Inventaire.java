package entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Inventaire.findAll", query = "SELECT i FROM Inventaire i"),
    @NamedQuery(name = "Inventaire.findByIdinventaire", query = "SELECT i FROM Inventaire i WHERE i.idinventaire = :idinventaire"),
    @NamedQuery(name = "Inventaire.findByDateInventaire", query = "SELECT i FROM Inventaire i WHERE i.dateInventaire = :dateInventaire"),
    @NamedQuery(name = "Inventaire.findByCode", query = "SELECT i FROM Inventaire i WHERE i.code = :code"),
    @NamedQuery(name = "Inventaire.findByValidee", query = "SELECT i FROM Inventaire i WHERE i.validee = :validee"),
    @NamedQuery(name = "Inventaire.findByIdMvtStock", query = "SELECT i FROM Inventaire i WHERE i.idMvtStock = :idMvtStock")})
public class Inventaire implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Long idinventaire;
    @Column(name = "date_inventaire")
    @Temporal(TemporalType.DATE)
    private Date dateInventaire;
    @Size(max = 2147483647)
    private String code;
    private Boolean validee;
    @Column(name = "id_mvt_stock")
    private BigInteger idMvtStock;
    @OneToMany(mappedBy = "idInventaire", fetch = FetchType.LAZY)
    private List<Ligneinventaire> ligneinventaireList;
    @JoinColumn(name = "idmois", referencedColumnName = "id_annee_mois")
    @ManyToOne(fetch = FetchType.LAZY)
    private AnneeMois idmois;

    public Inventaire() {
    }

    public Inventaire(Long idinventaire) {
        /*  66 */ this.idinventaire = idinventaire;
    }

    public Long getIdinventaire() {
        /*  70 */ return this.idinventaire;
    }

    public void setIdinventaire(Long idinventaire) {
        /*  74 */ this.idinventaire = idinventaire;
    }

    public Date getDateInventaire() {
        /*  78 */ return this.dateInventaire;
    }

    public void setDateInventaire(Date dateInventaire) {
        /*  82 */ this.dateInventaire = dateInventaire;
    }

    public String getCode() {
        /*  86 */ return this.code;
    }

    public void setCode(String code) {
        /*  90 */ this.code = code;
    }

    public Boolean getValidee() {
        /*  94 */ return this.validee;
    }

    public void setValidee(Boolean validee) {
        /*  98 */ this.validee = validee;
    }

    public BigInteger getIdMvtStock() {
        /* 102 */ return this.idMvtStock;
    }

    public void setIdMvtStock(BigInteger idMvtStock) {
        /* 106 */ this.idMvtStock = idMvtStock;
    }

    @XmlTransient
    public List<Ligneinventaire> getLigneinventaireList() {
        /* 111 */ return this.ligneinventaireList;
    }

    public void setLigneinventaireList(List<Ligneinventaire> ligneinventaireList) {
        /* 115 */ this.ligneinventaireList = ligneinventaireList;
    }

    public AnneeMois getIdmois() {
        /* 119 */ return this.idmois;
    }

    public void setIdmois(AnneeMois idmois) {
        /* 123 */ this.idmois = idmois;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.idinventaire != null) ? this.idinventaire.hashCode() : 0;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Inventaire)) {
            return false;
        }
        Inventaire other = (Inventaire) object;
        if ((this.idinventaire == null && other.idinventaire != null) || (this.idinventaire != null && !this.idinventaire.equals(other.idinventaire))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Inventaire[ idinventaire=" + this.idinventaire + " ]";
    }
}
