package entities;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Produit.findAll", query = "SELECT p FROM Produit p"),
    @NamedQuery(name = "Produit.findByIdproduit", query = "SELECT p FROM Produit p WHERE p.idproduit = :idproduit"),
    @NamedQuery(name = "Produit.findByCode", query = "SELECT p FROM Produit p WHERE p.code = :code"),
    @NamedQuery(name = "Produit.findByNom", query = "SELECT p FROM Produit p WHERE p.nom = :nom"),
    @NamedQuery(name = "Produit.findByQuantite", query = "SELECT p FROM Produit p WHERE p.quantite = :quantite"),
    @NamedQuery(name = "Produit.findByStockCritique", query = "SELECT p FROM Produit p WHERE p.stockCritique = :stockCritique"),
    @NamedQuery(name = "Produit.findByPrixMaximal", query = "SELECT p FROM Produit p WHERE p.prixMaximal = :prixMaximal"),
    @NamedQuery(name = "Produit.findByTelephone", query = "SELECT p FROM Produit p WHERE p.telephone = :telephone"),
    @NamedQuery(name = "Produit.findByPhoto", query = "SELECT p FROM Produit p WHERE p.photo = :photo"),
    @NamedQuery(name = "Produit.findByPerissable", query = "SELECT p FROM Produit p WHERE p.perissable = :perissable"),
    @NamedQuery(name = "Produit.findByEtat", query = "SELECT p FROM Produit p WHERE p.etat = :etat"),
    @NamedQuery(name = "Produit.findByQteDosage", query = "SELECT p FROM Produit p WHERE p.qteDosage = :qteDosage"),
    @NamedQuery(name = "Produit.findByTva", query = "SELECT p FROM Produit p WHERE p.tva = :tva"),
    @NamedQuery(name = "Produit.findByPrixachat", query = "SELECT p FROM Produit p WHERE p.prixachat = :prixachat")})
public class Produit implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Long idproduit;
    @Size(max = 2147483647)
    private String code;
    @Size(max = 2147483647)
    private String nom;
    @Size(max = 300)
    private String description;
    private Double quantite;
    @Column(name = "stock_critique")
    private Integer stockCritique;
    @Column(name = "prix_maximal")
    private Double prixMaximal;
    private Boolean telephone;
    @Size(max = 2147483647)
    private String photo;
    private Boolean perissable;
    private Boolean etat;
    @Column(name = "qte_dosage")
    private Double qteDosage;
    private Double tva;
    private Double prixachat;
    @OneToMany(mappedBy = "idproduit", fetch = FetchType.LAZY)
    private List<Lot> lotList;
    @OneToMany(mappedBy = "idproduit", fetch = FetchType.LAZY)
    private List<LigneFactBailleur> ligneFactBailleurList;
    @OneToMany(mappedBy = "idproduit", fetch = FetchType.LAZY)
    private List<LigneCmdeFournisseur> ligneCmdeFournisseurList;
    @OneToMany(mappedBy = "idproduit", fetch = FetchType.LAZY)
    private List<StockProduit> stockProduitList;
    @JoinColumn(name = "idfamille", referencedColumnName = "idfamille")
    @ManyToOne(fetch = FetchType.LAZY)
    private Famille idfamille;
    @JoinColumn(name = "idforme", referencedColumnName = "idforme")
    @ManyToOne(fetch = FetchType.LAZY)
    private Formeproduit idforme;
    @JoinColumn(name = "idforme_stockage", referencedColumnName = "idforme_stockage")
    @ManyToOne(fetch = FetchType.LAZY)
    private Formestockage idformeStockage;
    @JoinColumn(name = "id_unite", referencedColumnName = "id_unite")
    @ManyToOne(fetch = FetchType.LAZY)
    private Unite idUnite;
    @OneToMany(mappedBy = "idproduit", fetch = FetchType.LAZY)
    private List<Commande> commandeList;
    @OneToMany(mappedBy = "idproduit", fetch = FetchType.LAZY)
    private List<LigneCommandeClient> ligneCommandeClientList;

    public Produit() {
    }

    public Produit(Long idproduit) {
        /* 103 */ this.idproduit = idproduit;
    }

    public Long getIdproduit() {
        /* 107 */ return this.idproduit;
    }

    public void setIdproduit(Long idproduit) {
        /* 111 */ this.idproduit = idproduit;
    }

    public String getCode() {
        /* 115 */ return this.code;
    }

    public void setCode(String code) {
        /* 119 */ this.code = code;
    }

    public String getNom() {
        /* 123 */ return this.nom;
    }

    public void setNom(String nom) {
        /* 127 */ this.nom = nom;
    }

    public Double getQuantite() {
        /* 131 */ return this.quantite;
    }

    public void setQuantite(Double quantite) {
        /* 135 */ this.quantite = quantite;
    }

    public Integer getStockCritique() {
        /* 139 */ return this.stockCritique;
    }

    public void setStockCritique(Integer stockCritique) {
        /* 143 */ this.stockCritique = stockCritique;
    }

    public Double getPrixMaximal() {
        /* 147 */ return this.prixMaximal;
    }

    public void setPrixMaximal(Double prixMaximal) {
        /* 151 */ this.prixMaximal = prixMaximal;
    }

    public Boolean getTelephone() {
        /* 155 */ return this.telephone;
    }

    public void setTelephone(Boolean telephone) {
        /* 159 */ this.telephone = telephone;
    }

    public String getPhoto() {
        /* 163 */ return this.photo;
    }

    public void setPhoto(String photo) {
        /* 167 */ this.photo = photo;
    }

    public Boolean getPerissable() {
        /* 171 */ return this.perissable;
    }

    public void setPerissable(Boolean perissable) {
        /* 175 */ this.perissable = perissable;
    }

    public Boolean getEtat() {
        /* 179 */ return this.etat;
    }

    public void setEtat(Boolean etat) {
        /* 183 */ this.etat = etat;
    }

    public Double getQteDosage() {
        /* 187 */ return this.qteDosage;
    }

    public void setQteDosage(Double qteDosage) {
        /* 191 */ this.qteDosage = qteDosage;
    }

    public Double getTva() {
        /* 195 */ return this.tva;
    }

    public void setTva(Double tva) {
        /* 199 */ this.tva = tva;
    }

    public String getDescription() {
        /* 203 */ return this.description;
    }

    public void setDescription(String description) {
        /* 207 */ this.description = description;
    }

    public Double getPrixachat() {
        /* 211 */ return this.prixachat;
    }

    public void setPrixachat(Double prixachat) {
        /* 215 */ this.prixachat = prixachat;
    }

    @XmlTransient
    public List<Lot> getLotList() {
        /* 220 */ return this.lotList;
    }

    public void setLotList(List<Lot> lotList) {
        /* 224 */ this.lotList = lotList;
    }

    @XmlTransient
    public List<LigneFactBailleur> getLigneFactBailleurList() {
        /* 229 */ return this.ligneFactBailleurList;
    }

    public void setLigneFactBailleurList(List<LigneFactBailleur> ligneFactBailleurList) {
        /* 233 */ this.ligneFactBailleurList = ligneFactBailleurList;
    }

    @XmlTransient
    public List<LigneCmdeFournisseur> getLigneCmdeFournisseurList() {
        /* 238 */ return this.ligneCmdeFournisseurList;
    }

    public void setLigneCmdeFournisseurList(List<LigneCmdeFournisseur> ligneCmdeFournisseurList) {
        /* 242 */ this.ligneCmdeFournisseurList = ligneCmdeFournisseurList;
    }

    @XmlTransient
    public List<StockProduit> getStockProduitList() {
        /* 247 */ return this.stockProduitList;
    }

    public void setStockProduitList(List<StockProduit> stockProduitList) {
        /* 251 */ this.stockProduitList = stockProduitList;
    }

    public Famille getIdfamille() {
        /* 255 */ return this.idfamille;
    }

    public void setIdfamille(Famille idfamille) {
        /* 259 */ this.idfamille = idfamille;
    }

    public Formeproduit getIdforme() {
        /* 263 */ return this.idforme;
    }

    public void setIdforme(Formeproduit idforme) {
        /* 267 */ this.idforme = idforme;
    }

    public Formestockage getIdformeStockage() {
        /* 271 */ return this.idformeStockage;
    }

    public void setIdformeStockage(Formestockage idformeStockage) {
        /* 275 */ this.idformeStockage = idformeStockage;
    }

    public Unite getIdUnite() {
        /* 279 */ return this.idUnite;
    }

    public void setIdUnite(Unite idUnite) {
        /* 283 */ this.idUnite = idUnite;
    }

    @XmlTransient
    public List<Commande> getCommandeList() {
        return this.commandeList;
    }

    public void setCommandeList(List<Commande> commandeList) {
        this.commandeList = commandeList;
    }

    @XmlTransient
    public List<LigneCommandeClient> getLigneCommandeClientList() {
        return this.ligneCommandeClientList;
    }

    public void setLigneCommandeClientList(List<LigneCommandeClient> ligneCommandeClientList) {
        this.ligneCommandeClientList = ligneCommandeClientList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.idproduit != null) ? this.idproduit.hashCode() : 0;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Produit)) {
            return false;
        }
        Produit other = (Produit) object;
        if ((this.idproduit == null && other.idproduit != null) || (this.idproduit != null && !this.idproduit.equals(other.idproduit))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Produit[ idproduit=" + this.idproduit + " ]";
    }
}
