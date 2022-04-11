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
    @NamedQuery(name = "Facture.findAll", query = "SELECT f FROM Facture f"),
    @NamedQuery(name = "Facture.findByIdfacture", query = "SELECT f FROM Facture f WHERE f.idfacture = :idfacture"),
    @NamedQuery(name = "Facture.findByNumeroFacture", query = "SELECT f FROM Facture f WHERE f.numeroFacture = :numeroFacture"),
    @NamedQuery(name = "Facture.findByMontant", query = "SELECT f FROM Facture f WHERE f.montant = :montant"),
    @NamedQuery(name = "Facture.findByDateAchat", query = "SELECT f FROM Facture f WHERE f.dateAchat = :dateAchat"),
    @NamedQuery(name = "Facture.findByPaye", query = "SELECT f FROM Facture f WHERE f.paye = :paye"),
    @NamedQuery(name = "Facture.findByCredit", query = "SELECT f FROM Facture f WHERE f.credit = :credit"),
    @NamedQuery(name = "Facture.findByMontantPaye", query = "SELECT f FROM Facture f WHERE f.montantPaye = :montantPaye"),
    @NamedQuery(name = "Facture.findByReste", query = "SELECT f FROM Facture f WHERE f.reste = :reste"),
    @NamedQuery(name = "Facture.findByTauxTva", query = "SELECT f FROM Facture f WHERE f.tauxTva = :tauxTva"),
    @NamedQuery(name = "Facture.findByMontantTva", query = "SELECT f FROM Facture f WHERE f.montantTva = :montantTva"),
    @NamedQuery(name = "Facture.findByTauxRemise", query = "SELECT f FROM Facture f WHERE f.tauxRemise = :tauxRemise"),
    @NamedQuery(name = "Facture.findByMontantRemise", query = "SELECT f FROM Facture f WHERE f.montantRemise = :montantRemise"),
    @NamedQuery(name = "Facture.findByMontantTtc", query = "SELECT f FROM Facture f WHERE f.montantTtc = :montantTtc"),
    @NamedQuery(name = "Facture.findByBord", query = "SELECT f FROM Facture f WHERE f.bord = :bord"),
    @NamedQuery(name = "Facture.findByCalcultva", query = "SELECT f FROM Facture f WHERE f.calcultva = :calcultva"),
    @NamedQuery(name = "Facture.findByCalculRemise", query = "SELECT f FROM Facture f WHERE f.calculRemise = :calculRemise"),
    @NamedQuery(name = "Facture.findByIdMvtStock", query = "SELECT f FROM Facture f WHERE f.idMvtStock = :idMvtStock"),
    @NamedQuery(name = "Facture.findByVenteDirecte", query = "SELECT f FROM Facture f WHERE f.venteDirecte = :venteDirecte"),
    @NamedQuery(name = "Facture.findByIdCmdeClient", query = "SELECT f FROM Facture f WHERE f.idCmdeClient = :idCmdeClient"),
    @NamedQuery(name = "Facture.findByComptabilise", query = "SELECT f FROM Facture f WHERE f.comptabilise = :comptabilise")})
public class Facture implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Long idfacture;
    @Size(max = 2147483647)
    @Column(name = "numero_facture")
    private String numeroFacture;
    private Double montant;
    @Column(name = "date_achat")
    @Temporal(TemporalType.DATE)
    private Date dateAchat;
    private Boolean paye;
    private Boolean credit;
    @Column(name = "montant_paye")
    private Double montantPaye;
    private Double reste;
    @Column(name = "taux_tva")
    private Double tauxTva;
    @Column(name = "montant_tva")
    private Double montantTva;
    @Column(name = "taux_remise")
    private Double tauxRemise;
    @Column(name = "montant_remise")
    private Double montantRemise;
    @Column(name = "montant_ttc")
    private Double montantTtc;
    private Double bord;
    private Boolean calcultva;
    @Column(name = "calcul_remise")
    private Boolean calculRemise;
    @Column(name = "id_mvt_stock")
    private BigInteger idMvtStock;
    @Column(name = "vente_directe")
    private Boolean venteDirecte;
    @Column(name = "id_cmde_client")
    private BigInteger idCmdeClient;
    private Boolean comptabilise;
    @JoinColumn(name = "id_annee_mois", referencedColumnName = "id_annee_mois")
    @ManyToOne(fetch = FetchType.LAZY)
    private AnneeMois idAnneeMois;
    @JoinColumn(name = "idclient", referencedColumnName = "idclient")
    @ManyToOne(fetch = FetchType.LAZY)
    private Client idclient;
    @JoinColumn(name = "idutilisateur", referencedColumnName = "idutilisateur")
    @ManyToOne(fetch = FetchType.LAZY)
    private Utilisateur idutilisateur;
    @OneToMany(mappedBy = "idfacture", fetch = FetchType.LAZY)
    private List<Commande> commandeList;
    @OneToMany(mappedBy = "idfacture", fetch = FetchType.LAZY)
    private List<Versement> versementList;

    private double quantite;

    public Facture() {
    }

    public Facture(Long idfacture) {
        /* 115 */ this.idfacture = idfacture;
    }

    public Long getIdfacture() {
        /* 119 */ return this.idfacture;
    }

    public void setIdfacture(Long idfacture) {
        /* 123 */ this.idfacture = idfacture;
    }

    public String getNumeroFacture() {
        /* 127 */ return this.numeroFacture;
    }

    public void setNumeroFacture(String numeroFacture) {
        /* 131 */ this.numeroFacture = numeroFacture;
    }

    public Double getMontant() {
        /* 135 */ return this.montant;
    }

    public void setMontant(Double montant) {
        /* 139 */ this.montant = montant;
    }

    public Date getDateAchat() {
        /* 143 */ return this.dateAchat;
    }

    public void setDateAchat(Date dateAchat) {
        /* 147 */ this.dateAchat = dateAchat;
    }

    public Boolean getPaye() {
        /* 151 */ return this.paye;
    }

    public void setPaye(Boolean paye) {
        /* 155 */ this.paye = paye;
    }

    public Boolean getCredit() {
        /* 159 */ return this.credit;
    }

    public void setCredit(Boolean credit) {
        /* 163 */ this.credit = credit;
    }

    public Double getMontantPaye() {
        /* 167 */ return this.montantPaye;
    }

    public void setMontantPaye(Double montantPaye) {
        /* 171 */ this.montantPaye = montantPaye;
    }

    public Double getReste() {
        /* 175 */ return this.reste;
    }

    public void setReste(Double reste) {
        /* 179 */ this.reste = reste;
    }

    public Double getTauxTva() {
        /* 183 */ return this.tauxTva;
    }

    public void setTauxTva(Double tauxTva) {
        /* 187 */ this.tauxTva = tauxTva;
    }

    public Double getMontantTva() {
        /* 191 */ return this.montantTva;
    }

    public void setMontantTva(Double montantTva) {
        /* 195 */ this.montantTva = montantTva;
    }

    public Double getTauxRemise() {
        /* 199 */ return this.tauxRemise;
    }

    public void setTauxRemise(Double tauxRemise) {
        /* 203 */ this.tauxRemise = tauxRemise;
    }

    public Double getMontantRemise() {
        /* 207 */ return this.montantRemise;
    }

    public void setMontantRemise(Double montantRemise) {
        /* 211 */ this.montantRemise = montantRemise;
    }

    public Double getMontantTtc() {
        /* 215 */ return this.montantTtc;
    }

    public void setMontantTtc(Double montantTtc) {
        /* 219 */ this.montantTtc = montantTtc;
    }

    public Double getBord() {
        /* 223 */ return this.bord;
    }

    public void setBord(Double bord) {
        /* 227 */ this.bord = bord;
    }

    public Boolean getCalcultva() {
        /* 231 */ return this.calcultva;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public void setCalcultva(Boolean calcultva) {
        /* 235 */ this.calcultva = calcultva;
    }

    public Boolean getCalculRemise() {
        /* 239 */ return this.calculRemise;
    }

    public void setCalculRemise(Boolean calculRemise) {
        /* 243 */ this.calculRemise = calculRemise;
    }

    public BigInteger getIdMvtStock() {
        /* 247 */ return this.idMvtStock;
    }

    public void setIdMvtStock(BigInteger idMvtStock) {
        /* 251 */ this.idMvtStock = idMvtStock;
    }

    public Boolean getVenteDirecte() {
        /* 255 */ return this.venteDirecte;
    }

    public void setVenteDirecte(Boolean venteDirecte) {
        /* 259 */ this.venteDirecte = venteDirecte;
    }

    public BigInteger getIdCmdeClient() {
        /* 263 */ return this.idCmdeClient;
    }

    public void setIdCmdeClient(BigInteger idCmdeClient) {
        /* 267 */ this.idCmdeClient = idCmdeClient;
    }

    public Boolean getComptabilise() {
        /* 271 */ return this.comptabilise;
    }

    public void setComptabilise(Boolean comptabilise) {
        /* 275 */ this.comptabilise = comptabilise;
    }

    public AnneeMois getIdAnneeMois() {
        /* 279 */ return this.idAnneeMois;
    }

    public void setIdAnneeMois(AnneeMois idAnneeMois) {
        /* 283 */ this.idAnneeMois = idAnneeMois;
    }

    public Client getIdclient() {
        /* 287 */ return this.idclient;
    }

    public void setIdclient(Client idclient) {
        /* 291 */ this.idclient = idclient;
    }

    public Utilisateur getIdutilisateur() {
        /* 295 */ return this.idutilisateur;
    }

    public void setIdutilisateur(Utilisateur idutilisateur) {
        /* 299 */ this.idutilisateur = idutilisateur;
    }

    @XmlTransient
    public List<Commande> getCommandeList() {
        return this.commandeList;
    }

    public void setCommandeList(List<Commande> commandeList) {
        this.commandeList = commandeList;
    }

    @XmlTransient
    public List<Versement> getVersementList() {
        return this.versementList;
    }

    public void setVersementList(List<Versement> versementList) {
        this.versementList = versementList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.idfacture != null) ? this.idfacture.hashCode() : 0;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Facture)) {
            return false;
        }
        Facture other = (Facture) object;
        if ((this.idfacture == null && other.idfacture != null) || (this.idfacture != null && !this.idfacture.equals(other.idfacture))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Facture[ idfacture=" + this.idfacture + " ]";
    }
}
