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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "mvt_stock")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MvtStock.findAll", query = "SELECT m FROM MvtStock m"),
    @NamedQuery(name = "MvtStock.findByIdMvtStock", query = "SELECT m FROM MvtStock m WHERE m.idMvtStock = :idMvtStock"),
    @NamedQuery(name = "MvtStock.findByCode", query = "SELECT m FROM MvtStock m WHERE m.code = :code"),
    @NamedQuery(name = "MvtStock.findByDateMvt", query = "SELECT m FROM MvtStock m WHERE m.dateMvt = :dateMvt"),
    @NamedQuery(name = "MvtStock.findByIdfacture", query = "SELECT m FROM MvtStock m WHERE m.idfacture = :idfacture"),
    @NamedQuery(name = "MvtStock.findByIdstock", query = "SELECT m FROM MvtStock m WHERE m.idstock = :idstock"),
    @NamedQuery(name = "MvtStock.findByIdinventaire", query = "SELECT m FROM MvtStock m WHERE m.idinventaire = :idinventaire")})
public class MvtStock implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_mvt_stock")
    private Long idMvtStock;
    @Size(max = 50)
    private String code;
    @Column(name = "date_mvt")
    @Temporal(TemporalType.DATE)
    private Date dateMvt;
    private BigInteger idfacture;
    private BigInteger idstock;
    private BigInteger idinventaire;
    @JoinColumn(name = "idmois", referencedColumnName = "id_annee_mois")
    @ManyToOne(fetch = FetchType.LAZY)
    private AnneeMois idmois;
    @OneToMany(mappedBy = "idMvtStock", fetch = FetchType.LAZY)
    private List<LigneMvtStock> ligneMvtStockList;

    public MvtStock() {
    }

    public MvtStock(Long idMvtStock) {
        /*  70 */ this.idMvtStock = idMvtStock;
    }

    public Long getIdMvtStock() {
        /*  74 */ return this.idMvtStock;
    }

    public void setIdMvtStock(Long idMvtStock) {
        /*  78 */ this.idMvtStock = idMvtStock;
    }

    public String getCode() {
        /*  82 */ return this.code;
    }

    public void setCode(String code) {
        /*  86 */ this.code = code;
    }

    public Date getDateMvt() {
        /*  90 */ return this.dateMvt;
    }

    public void setDateMvt(Date dateMvt) {
        /*  94 */ this.dateMvt = dateMvt;
    }

    public BigInteger getIdfacture() {
        /*  98 */ return this.idfacture;
    }

    public void setIdfacture(BigInteger idfacture) {
        /* 102 */ this.idfacture = idfacture;
    }

    public BigInteger getIdstock() {
        /* 106 */ return this.idstock;
    }

    public void setIdstock(BigInteger idstock) {
        /* 110 */ this.idstock = idstock;
    }

    public BigInteger getIdinventaire() {
        /* 114 */ return this.idinventaire;
    }

    public void setIdinventaire(BigInteger idinventaire) {
        /* 118 */ this.idinventaire = idinventaire;
    }

    public AnneeMois getIdmois() {
        /* 122 */ return this.idmois;
    }

    public void setIdmois(AnneeMois idmois) {
        /* 126 */ this.idmois = idmois;
    }

    @XmlTransient
    public List<LigneMvtStock> getLigneMvtStockList() {
        /* 131 */ return this.ligneMvtStockList;
    }

    public void setLigneMvtStockList(List<LigneMvtStock> ligneMvtStockList) {
        /* 135 */ this.ligneMvtStockList = ligneMvtStockList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.idMvtStock != null) ? this.idMvtStock.hashCode() : 0;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof MvtStock)) {
            return false;
        }
        MvtStock other = (MvtStock) object;
        if ((this.idMvtStock == null && other.idMvtStock != null) || (this.idMvtStock != null && !this.idMvtStock.equals(other.idMvtStock))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.MvtStock[ idMvtStock=" + this.idMvtStock + " ]";
    }
}
