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
    @NamedQuery(name = "Stock.findAll", query = "SELECT s FROM Stock s"),
    @NamedQuery(name = "Stock.findByIdstock", query = "SELECT s FROM Stock s WHERE s.idstock = :idstock"),
    @NamedQuery(name = "Stock.findByCode", query = "SELECT s FROM Stock s WHERE s.code = :code"),
    @NamedQuery(name = "Stock.findByMontant", query = "SELECT s FROM Stock s WHERE s.montant = :montant"),
    @NamedQuery(name = "Stock.findByDateAchat", query = "SELECT s FROM Stock s WHERE s.dateAchat = :dateAchat"),
    @NamedQuery(name = "Stock.findByIdMvtStock", query = "SELECT s FROM Stock s WHERE s.idMvtStock = :idMvtStock"),
    @NamedQuery(name = "Stock.findByCalculTva", query = "SELECT s FROM Stock s WHERE s.calculTva = :calculTva"),
    @NamedQuery(name = "Stock.findByCalculRemise", query = "SELECT s FROM Stock s WHERE s.calculRemise = :calculRemise"),
    @NamedQuery(name = "Stock.findByTauxTva", query = "SELECT s FROM Stock s WHERE s.tauxTva = :tauxTva"),
    @NamedQuery(name = "Stock.findByTauxRemise", query = "SELECT s FROM Stock s WHERE s.tauxRemise = :tauxRemise"),
    @NamedQuery(name = "Stock.findByLivraisonDirecte", query = "SELECT s FROM Stock s WHERE s.livraisonDirecte = :livraisonDirecte"),
    @NamedQuery(name = "Stock.findByMontantTva", query = "SELECT s FROM Stock s WHERE s.montantTva = :montantTva"),
    @NamedQuery(name = "Stock.findByMontantTtc", query = "SELECT s FROM Stock s WHERE s.montantTtc = :montantTtc"),
    @NamedQuery(name = "Stock.findByMontantRemise", query = "SELECT s FROM Stock s WHERE s.montantRemise = :montantRemise"),
    @NamedQuery(name = "Stock.findByIdCmdeFournisseur", query = "SELECT s FROM Stock s WHERE s.idCmdeFournisseur = :idCmdeFournisseur"),
    @NamedQuery(name = "Stock.findByBord", query = "SELECT s FROM Stock s WHERE s.bord = :bord")})
public class Stock implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Long idstock;
    @Size(max = 2147483647)
    private String code;
    private Double montant;
    @Column(name = "date_achat")
    @Temporal(TemporalType.DATE)
    private Date dateAchat;
    @Column(name = "id_mvt_stock")
    private BigInteger idMvtStock;
    @Column(name = "calcul_tva")
    private Boolean calculTva;
    @Column(name = "calcul_remise")
    private Boolean calculRemise;
    @Column(name = "taux_tva")
    private Double tauxTva;
    @Column(name = "taux_remise")
    private Double tauxRemise;
    @Column(name = "livraison_directe")
    private Boolean livraisonDirecte;
    @Column(name = "montant_tva")
    private Double montantTva;
    @Column(name = "montant_ttc")
    private Double montantTtc;
    @Column(name = "montant_remise")
    private Double montantRemise;
    @Column(name = "id_cmde_fournisseur")
    private BigInteger idCmdeFournisseur;
    private Double bord;
    @JoinColumn(name = "id_annee_mois", referencedColumnName = "id_annee_mois")
    @ManyToOne(fetch = FetchType.LAZY)
    private AnneeMois idAnneeMois;
    @JoinColumn(name = "idfournisseur", referencedColumnName = "idfournisseur")
    @ManyToOne(fetch = FetchType.LAZY)
    private Fournisseur idfournisseur;
    @OneToMany(mappedBy = "idstock", fetch = FetchType.LAZY)
    private List<StockProduit> stockProduitList;

    private double quantite;

    public Stock() {
    }

    public Stock(Long idstock) {
        /*  99 */ this.idstock = idstock;
    }

    public Long getIdstock() {
        /* 103 */ return this.idstock;
    }

    public void setIdstock(Long idstock) {
        /* 107 */ this.idstock = idstock;
    }

    public String getCode() {
        /* 111 */ return this.code;
    }

    public void setCode(String code) {
        /* 115 */ this.code = code;
    }

    public Double getMontant() {
        /* 119 */ return this.montant;
    }

    public void setMontant(Double montant) {
        /* 123 */ this.montant = montant;
    }

    public Date getDateAchat() {
        /* 127 */ return this.dateAchat;
    }

    public void setDateAchat(Date dateAchat) {
        /* 131 */ this.dateAchat = dateAchat;
    }

    public BigInteger getIdMvtStock() {
        /* 135 */ return this.idMvtStock;
    }

    public void setIdMvtStock(BigInteger idMvtStock) {
        /* 139 */ this.idMvtStock = idMvtStock;
    }

    public Boolean getCalculTva() {
        /* 143 */ return this.calculTva;
    }

    public void setCalculTva(Boolean calculTva) {
        /* 147 */ this.calculTva = calculTva;
    }

    public Boolean getCalculRemise() {
        /* 151 */ return this.calculRemise;
    }

    public void setCalculRemise(Boolean calculRemise) {
        /* 155 */ this.calculRemise = calculRemise;
    }

    public Double getTauxTva() {
        /* 159 */ return this.tauxTva;
    }

    public void setTauxTva(Double tauxTva) {
        /* 163 */ this.tauxTva = tauxTva;
    }

    public Double getTauxRemise() {
        /* 167 */ return this.tauxRemise;
    }

    public void setTauxRemise(Double tauxRemise) {
        /* 171 */ this.tauxRemise = tauxRemise;
    }

    public Boolean getLivraisonDirecte() {
        /* 175 */ return this.livraisonDirecte;
    }

    public void setLivraisonDirecte(Boolean livraisonDirecte) {
        /* 179 */ this.livraisonDirecte = livraisonDirecte;
    }

    public Double getMontantTva() {
        /* 183 */ return this.montantTva;
    }

    public void setMontantTva(Double montantTva) {
        /* 187 */ this.montantTva = montantTva;
    }

    public Double getMontantTtc() {
        /* 191 */ return this.montantTtc;
    }

    public void setMontantTtc(Double montantTtc) {
        /* 195 */ this.montantTtc = montantTtc;
    }

    public Double getMontantRemise() {
        /* 199 */ return this.montantRemise;
    }

    public void setMontantRemise(Double montantRemise) {
        /* 203 */ this.montantRemise = montantRemise;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public BigInteger getIdCmdeFournisseur() {
        return this.idCmdeFournisseur;
    }

    public void setIdCmdeFournisseur(BigInteger idCmdeFournisseur) {
        this.idCmdeFournisseur = idCmdeFournisseur;
    }

    public Double getBord() {
        return this.bord;
    }

    public void setBord(Double bord) {
        this.bord = bord;
    }

    public AnneeMois getIdAnneeMois() {
        return this.idAnneeMois;
    }

    public void setIdAnneeMois(AnneeMois idAnneeMois) {
        this.idAnneeMois = idAnneeMois;
    }

    public Fournisseur getIdfournisseur() {
        return this.idfournisseur;
    }

    public void setIdfournisseur(Fournisseur idfournisseur) {
        this.idfournisseur = idfournisseur;
    }

    @XmlTransient
    public List<StockProduit> getStockProduitList() {
        return this.stockProduitList;
    }

    public void setStockProduitList(List<StockProduit> stockProduitList) {
        this.stockProduitList = stockProduitList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.idstock != null) ? this.idstock.hashCode() : 0;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Stock)) {
            return false;
        }
        Stock other = (Stock) object;
        if ((this.idstock == null && other.idstock != null) || (this.idstock != null && !this.idstock.equals(other.idstock))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Stock[ idstock=" + this.idstock + " ]";
    }
}
