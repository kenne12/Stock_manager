package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "stock_produit")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StockProduit.findAll", query = "SELECT s FROM StockProduit s"),
    @NamedQuery(name = "StockProduit.findByIdStockProduit", query = "SELECT s FROM StockProduit s WHERE s.idStockProduit = :idStockProduit"),
    @NamedQuery(name = "StockProduit.findByQuantite", query = "SELECT s FROM StockProduit s WHERE s.quantite = :quantite"),
    @NamedQuery(name = "StockProduit.findByPrixAcquisition", query = "SELECT s FROM StockProduit s WHERE s.prixAcquisition = :prixAcquisition"),
    @NamedQuery(name = "StockProduit.findByBenefice", query = "SELECT s FROM StockProduit s WHERE s.benefice = :benefice")})
public class StockProduit implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_stock_produit")
    private Long idStockProduit;
    private Double quantite;
    @Column(name = "prix_acquisition")
    private Double prixAcquisition;
    private Double benefice;
    @JoinColumn(name = "idlot", referencedColumnName = "idlot")
    @ManyToOne(fetch = FetchType.LAZY)
    private Lot idlot;
    @JoinColumn(name = "idproduit", referencedColumnName = "idproduit")
    @ManyToOne(fetch = FetchType.LAZY)
    private Produit idproduit;
    @JoinColumn(name = "idstock", referencedColumnName = "idstock")
    @ManyToOne(fetch = FetchType.LAZY)
    private Stock idstock;

    public StockProduit() {
    }

    public StockProduit(Long idStockProduit) {
        /*  61 */ this.idStockProduit = idStockProduit;
    }

    public Long getIdStockProduit() {
        /*  65 */ return this.idStockProduit;
    }

    public void setIdStockProduit(Long idStockProduit) {
        /*  69 */ this.idStockProduit = idStockProduit;
    }

    public Double getQuantite() {
        /*  73 */ return this.quantite;
    }

    public void setQuantite(Double quantite) {
        /*  77 */ this.quantite = quantite;
    }

    public Double getPrixAcquisition() {
        /*  81 */ return this.prixAcquisition;
    }

    public void setPrixAcquisition(Double prixAcquisition) {
        /*  85 */ this.prixAcquisition = prixAcquisition;
    }

    public Double getBenefice() {
        /*  89 */ return this.benefice;
    }

    public void setBenefice(Double benefice) {
        /*  93 */ this.benefice = benefice;
    }

    public Lot getIdlot() {
        /*  97 */ return this.idlot;
    }

    public void setIdlot(Lot idlot) {
        /* 101 */ this.idlot = idlot;
    }

    public Produit getIdproduit() {
        /* 105 */ return this.idproduit;
    }

    public void setIdproduit(Produit idproduit) {
        /* 109 */ this.idproduit = idproduit;
    }

    public Stock getIdstock() {
        /* 113 */ return this.idstock;
    }

    public void setIdstock(Stock idstock) {
        /* 117 */ this.idstock = idstock;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.idStockProduit != null) ? this.idStockProduit.hashCode() : 0;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof StockProduit)) {
            return false;
        }
        StockProduit other = (StockProduit) object;
        if ((this.idStockProduit == null && other.idStockProduit != null) || (this.idStockProduit != null && !this.idStockProduit.equals(other.idStockProduit))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.StockProduit[ idStockProduit=" + this.idStockProduit + " ]";
    }
}
