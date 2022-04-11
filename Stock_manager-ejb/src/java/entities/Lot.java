package entities;

import java.io.Serializable;
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
    @NamedQuery(name = "Lot.findAll", query = "SELECT l FROM Lot l"),
    @NamedQuery(name = "Lot.findByIdlot", query = "SELECT l FROM Lot l WHERE l.idlot = :idlot"),
    @NamedQuery(name = "Lot.findByQuantite", query = "SELECT l FROM Lot l WHERE l.quantite = :quantite"),
    @NamedQuery(name = "Lot.findByPrixAchat", query = "SELECT l FROM Lot l WHERE l.prixAchat = :prixAchat"),
    @NamedQuery(name = "Lot.findByPrixVente", query = "SELECT l FROM Lot l WHERE l.prixVente = :prixVente"),
    @NamedQuery(name = "Lot.findByNumeroLot", query = "SELECT l FROM Lot l WHERE l.numeroLot = :numeroLot"),
    @NamedQuery(name = "Lot.findByDateFabrication", query = "SELECT l FROM Lot l WHERE l.dateFabrication = :dateFabrication"),
    @NamedQuery(name = "Lot.findByDatePeremption", query = "SELECT l FROM Lot l WHERE l.datePeremption = :datePeremption"),
    @NamedQuery(name = "Lot.findByStockCritique", query = "SELECT l FROM Lot l WHERE l.stockCritique = :stockCritique"),
    @NamedQuery(name = "Lot.findByTva", query = "SELECT l FROM Lot l WHERE l.tva = :tva"),
    @NamedQuery(name = "Lot.findByEtat", query = "SELECT l FROM Lot l WHERE l.etat = :etat"),
    @NamedQuery(name = "Lot.findByDateEnregistrement", query = "SELECT l FROM Lot l WHERE l.dateEnregistrement = :dateEnregistrement")})
public class Lot implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Long idlot;
    private Double quantite;
    @Column(name = "prix_achat")
    private Double prixAchat;
    @Column(name = "prix_vente")
    private Double prixVente;
    @Size(max = 2147483647)
    @Column(name = "numero_lot")
    private String numeroLot;
    @Column(name = "date_fabrication")
    @Temporal(TemporalType.DATE)
    private Date dateFabrication;
    @Column(name = "date_peremption")
    @Temporal(TemporalType.DATE)
    private Date datePeremption;
    @Column(name = "stock_critique")
    private Double stockCritique;
    private Double tva;
    private Boolean etat;
    @Column(name = "date_enregistrement")
    @Temporal(TemporalType.DATE)
    private Date dateEnregistrement;
    @OneToMany(mappedBy = "idlot", fetch = FetchType.LAZY)
    private List<Ligneinventaire> ligneinventaireList;
    @JoinColumn(name = "idbailleur", referencedColumnName = "idbailleur")
    @ManyToOne(fetch = FetchType.LAZY)
    private Bailleur idbailleur;
    @JoinColumn(name = "idproduit", referencedColumnName = "idproduit")
    @ManyToOne(fetch = FetchType.LAZY)
    private Produit idproduit;
    @JoinColumn(name = "iduser_p", referencedColumnName = "iduser_p")
    @ManyToOne(fetch = FetchType.LAZY)
    private UserP iduserP;
    @OneToMany(mappedBy = "idlot", fetch = FetchType.LAZY)
    private List<LigneCmdeFournisseur> ligneCmdeFournisseurList;
    @OneToMany(mappedBy = "idlot", fetch = FetchType.LAZY)
    private List<StockProduit> stockProduitList;
    @OneToMany(mappedBy = "idlot", fetch = FetchType.LAZY)
    private List<Commande> commandeList;
    @OneToMany(mappedBy = "idlot", fetch = FetchType.LAZY)
    private List<LigneCommandeClient> ligneCommandeClientList;
    @OneToMany(mappedBy = "idlot", fetch = FetchType.LAZY)
    private List<LigneMvtStock> ligneMvtStockList;

    public Lot() {
    }

    public Lot(Long idlot) {
        /* 101 */ this.idlot = idlot;
    }

    public Long getIdlot() {
        /* 105 */ return this.idlot;
    }

    public void setIdlot(Long idlot) {
        /* 109 */ this.idlot = idlot;
    }

    public Double getQuantite() {
        /* 113 */ return this.quantite;
    }

    public void setQuantite(Double quantite) {
        /* 117 */ this.quantite = quantite;
    }

    public Double getPrixAchat() {
        /* 121 */ return this.prixAchat;
    }

    public void setPrixAchat(Double prixAchat) {
        /* 125 */ this.prixAchat = prixAchat;
    }

    public Double getPrixVente() {
        /* 129 */ return this.prixVente;
    }

    public void setPrixVente(Double prixVente) {
        /* 133 */ this.prixVente = prixVente;
    }

    public String getNumeroLot() {
        /* 137 */ return this.numeroLot;
    }

    public void setNumeroLot(String numeroLot) {
        /* 141 */ this.numeroLot = numeroLot;
    }

    public Date getDateFabrication() {
        /* 145 */ return this.dateFabrication;
    }

    public void setDateFabrication(Date dateFabrication) {
        /* 149 */ this.dateFabrication = dateFabrication;
    }

    public Date getDatePeremption() {
        /* 153 */ return this.datePeremption;
    }

    public void setDatePeremption(Date datePeremption) {
        /* 157 */ this.datePeremption = datePeremption;
    }

    public Double getStockCritique() {
        /* 161 */ return this.stockCritique;
    }

    public void setStockCritique(Double stockCritique) {
        /* 165 */ this.stockCritique = stockCritique;
    }

    public Double getTva() {
        /* 169 */ return this.tva;
    }

    public void setTva(Double tva) {
        /* 173 */ this.tva = tva;
    }

    public Boolean getEtat() {
        /* 177 */ return this.etat;
    }

    public void setEtat(Boolean etat) {
        /* 181 */ this.etat = etat;
    }

    public Date getDateEnregistrement() {
        /* 185 */ return this.dateEnregistrement;
    }

    public void setDateEnregistrement(Date dateEnregistrement) {
        /* 189 */ this.dateEnregistrement = dateEnregistrement;
    }

    @XmlTransient
    public List<Ligneinventaire> getLigneinventaireList() {
        /* 194 */ return this.ligneinventaireList;
    }

    public void setLigneinventaireList(List<Ligneinventaire> ligneinventaireList) {
        /* 198 */ this.ligneinventaireList = ligneinventaireList;
    }

    public Bailleur getIdbailleur() {
        /* 202 */ return this.idbailleur;
    }

    public void setIdbailleur(Bailleur idbailleur) {
        /* 206 */ this.idbailleur = idbailleur;
    }

    public Produit getIdproduit() {
        /* 210 */ return this.idproduit;
    }

    public void setIdproduit(Produit idproduit) {
        /* 214 */ this.idproduit = idproduit;
    }

    public UserP getIduserP() {
        /* 218 */ return this.iduserP;
    }

    public void setIduserP(UserP iduserP) {
        /* 222 */ this.iduserP = iduserP;
    }

    @XmlTransient
    public List<LigneCmdeFournisseur> getLigneCmdeFournisseurList() {
        /* 227 */ return this.ligneCmdeFournisseurList;
    }

    public void setLigneCmdeFournisseurList(List<LigneCmdeFournisseur> ligneCmdeFournisseurList) {
        /* 231 */ this.ligneCmdeFournisseurList = ligneCmdeFournisseurList;
    }

    @XmlTransient
    public List<StockProduit> getStockProduitList() {
        /* 236 */ return this.stockProduitList;
    }

    public void setStockProduitList(List<StockProduit> stockProduitList) {
        /* 240 */ this.stockProduitList = stockProduitList;
    }

    @XmlTransient
    public List<Commande> getCommandeList() {
        /* 245 */ return this.commandeList;
    }

    public void setCommandeList(List<Commande> commandeList) {
        /* 249 */ this.commandeList = commandeList;
    }

    @XmlTransient
    public List<LigneCommandeClient> getLigneCommandeClientList() {
        /* 254 */ return this.ligneCommandeClientList;
    }

    public void setLigneCommandeClientList(List<LigneCommandeClient> ligneCommandeClientList) {
        /* 258 */ this.ligneCommandeClientList = ligneCommandeClientList;
    }

    @XmlTransient
    public List<LigneMvtStock> getLigneMvtStockList() {
        /* 263 */ return this.ligneMvtStockList;
    }

    public void setLigneMvtStockList(List<LigneMvtStock> ligneMvtStockList) {
        /* 267 */ this.ligneMvtStockList = ligneMvtStockList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.idlot != null) ? this.idlot.hashCode() : 0;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Lot)) {
            return false;
        }
        Lot other = (Lot) object;
        if ((this.idlot == null && other.idlot != null) || (this.idlot != null && !this.idlot.equals(other.idlot))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Lot[ idlot=" + this.idlot + " ]";
    }
}
