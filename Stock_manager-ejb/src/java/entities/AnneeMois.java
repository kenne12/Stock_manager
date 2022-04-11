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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "annee_mois")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AnneeMois.findAll", query = "SELECT a FROM AnneeMois a"),
    @NamedQuery(name = "AnneeMois.findByIdAnneeMois", query = "SELECT a FROM AnneeMois a WHERE a.idAnneeMois = :idAnneeMois"),
    @NamedQuery(name = "AnneeMois.findByDateDebut", query = "SELECT a FROM AnneeMois a WHERE a.dateDebut = :dateDebut"),
    @NamedQuery(name = "AnneeMois.findByDateFin", query = "SELECT a FROM AnneeMois a WHERE a.dateFin = :dateFin"),
    @NamedQuery(name = "AnneeMois.findByEtat", query = "SELECT a FROM AnneeMois a WHERE a.etat = :etat")})
public class AnneeMois
        implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_annee_mois")
    private Integer idAnneeMois;
    @Column(name = "date_debut")
    @Temporal(TemporalType.DATE)
    private Date dateDebut;
    @Column(name = "date_fin")
    @Temporal(TemporalType.DATE)
    private Date dateFin;
    private Boolean etat;
    @OneToMany(mappedBy = "idmois", fetch = FetchType.LAZY)
    private List<CommandeFournisseur> commandeFournisseurList;
    @OneToMany(mappedBy = "idmois", fetch = FetchType.LAZY)
    private List<Journee> journeeList;
    @OneToMany(mappedBy = "idmois", fetch = FetchType.LAZY)
    private List<CommandeClient> commandeClientList;
    @JoinColumn(name = "idannee", referencedColumnName = "idannee")
    @ManyToOne(fetch = FetchType.LAZY)
    private Annee idannee;
    @JoinColumn(name = "idmois", referencedColumnName = "idmois")
    @ManyToOne(fetch = FetchType.LAZY)
    private Mois idmois;
    @OneToMany(mappedBy = "idmois", fetch = FetchType.LAZY)
    private List<FactureBailleur> factureBailleurList;
    @OneToMany(mappedBy = "idAnneeMois", fetch = FetchType.LAZY)
    private List<Facture> factureList;
    @OneToMany(mappedBy = "idmois", fetch = FetchType.LAZY)
    private List<Inventaire> inventaireList;
    @OneToMany(mappedBy = "idAnneeMois", fetch = FetchType.LAZY)
    private List<Stock> stockList;
    @OneToMany(mappedBy = "idmois", fetch = FetchType.LAZY)
    private List<Depense> depenseList;
    @OneToMany(mappedBy = "idmois", fetch = FetchType.LAZY)
    private List<MvtStock> mvtStockList;
    @OneToMany(mappedBy = "idmois", fetch = FetchType.LAZY)
    private List<Salaire> salaireList;
    @OneToMany(mappedBy = "idmois", fetch = FetchType.LAZY)
    private List<Recette> recetteList;

    public AnneeMois() {
    }

    public AnneeMois(Integer idAnneeMois) {
        this.idAnneeMois = idAnneeMois;
    }

    public Integer getIdAnneeMois() {
        return this.idAnneeMois;
    }

    public void setIdAnneeMois(Integer idAnneeMois) {
        this.idAnneeMois = idAnneeMois;
    }

    public Date getDateDebut() {
        return this.dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return this.dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public Boolean getEtat() {
        return this.etat;
    }

    public void setEtat(Boolean etat) {
        this.etat = etat;
    }

    @XmlTransient
    public List<CommandeFournisseur> getCommandeFournisseurList() {
        return this.commandeFournisseurList;
    }

    public void setCommandeFournisseurList(List<CommandeFournisseur> commandeFournisseurList) {
        this.commandeFournisseurList = commandeFournisseurList;
    }

    @XmlTransient
    public List<Journee> getJourneeList() {
        return this.journeeList;
    }

    public void setJourneeList(List<Journee> journeeList) {
        this.journeeList = journeeList;
    }

    @XmlTransient
    public List<CommandeClient> getCommandeClientList() {
        return this.commandeClientList;
    }

    public void setCommandeClientList(List<CommandeClient> commandeClientList) {
        this.commandeClientList = commandeClientList;
    }

    public Annee getIdannee() {
        return this.idannee;
    }

    public void setIdannee(Annee idannee) {
        this.idannee = idannee;
    }

    public Mois getIdmois() {
        return this.idmois;
    }

    public void setIdmois(Mois idmois) {
        this.idmois = idmois;
    }

    @XmlTransient
    public List<FactureBailleur> getFactureBailleurList() {
        return this.factureBailleurList;
    }

    public void setFactureBailleurList(List<FactureBailleur> factureBailleurList) {
        this.factureBailleurList = factureBailleurList;
    }

    @XmlTransient
    public List<Facture> getFactureList() {
        return this.factureList;
    }

    public void setFactureList(List<Facture> factureList) {
        this.factureList = factureList;
    }

    @XmlTransient
    public List<Inventaire> getInventaireList() {
        return this.inventaireList;
    }

    public void setInventaireList(List<Inventaire> inventaireList) {
        this.inventaireList = inventaireList;
    }

    @XmlTransient
    public List<Stock> getStockList() {
        return this.stockList;
    }

    public void setStockList(List<Stock> stockList) {
        this.stockList = stockList;
    }

    @XmlTransient
    public List<Depense> getDepenseList() {
        return this.depenseList;
    }

    public void setDepenseList(List<Depense> depenseList) {
        this.depenseList = depenseList;
    }

    @XmlTransient
    public List<MvtStock> getMvtStockList() {
        return this.mvtStockList;
    }

    public void setMvtStockList(List<MvtStock> mvtStockList) {
        this.mvtStockList = mvtStockList;
    }

    @XmlTransient
    public List<Salaire> getSalaireList() {
        return this.salaireList;
    }

    public void setSalaireList(List<Salaire> salaireList) {
        this.salaireList = salaireList;
    }

    @XmlTransient
    public List<Recette> getRecetteList() {
        return this.recetteList;
    }

    public void setRecetteList(List<Recette> recetteList) {
        this.recetteList = recetteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.idAnneeMois != null) ? this.idAnneeMois.hashCode() : 0;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof AnneeMois)) {
            return false;
        }
        AnneeMois other = (AnneeMois) object;
        if ((this.idAnneeMois == null && other.idAnneeMois != null) || (this.idAnneeMois != null && !this.idAnneeMois.equals(other.idAnneeMois))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.AnneeMois[ idAnneeMois=" + this.idAnneeMois + " ]";
    }
}
