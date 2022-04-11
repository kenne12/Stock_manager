package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
    @NamedQuery(name = "Commande.findAll", query = "SELECT c FROM Commande c"),
    @NamedQuery(name = "Commande.findByIdcommande", query = "SELECT c FROM Commande c WHERE c.idcommande = :idcommande"),
    @NamedQuery(name = "Commande.findByQuantite", query = "SELECT c FROM Commande c WHERE c.quantite = :quantite"),
    @NamedQuery(name = "Commande.findByMontant", query = "SELECT c FROM Commande c WHERE c.montant = :montant"),
    @NamedQuery(name = "Commande.findBySerie", query = "SELECT c FROM Commande c WHERE c.serie = :serie"),
    @NamedQuery(name = "Commande.findByDescription", query = "SELECT c FROM Commande c WHERE c.description = :description"),
    @NamedQuery(name = "Commande.findByBenefice", query = "SELECT c FROM Commande c WHERE c.benefice = :benefice"),
    @NamedQuery(name = "Commande.findByTva", query = "SELECT c FROM Commande c WHERE c.tva = :tva"),
    @NamedQuery(name = "Commande.findByMontanttva", query = "SELECT c FROM Commande c WHERE c.montanttva = :montanttva"),
    @NamedQuery(name = "Commande.findByComptabilise", query = "SELECT c FROM Commande c WHERE c.comptabilise = :comptabilise")})
public class Commande implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Long idcommande;
    private Double quantite;
    private Double montant;
    @Size(max = 2147483647)
    private String serie;
    @Size(max = 2147483647)
    private String description;
    private Double benefice;
    private Double tva;
    private Double montanttva;
    private Boolean comptabilise;
    @OneToMany(mappedBy = "idcommande", fetch = FetchType.LAZY)
    private List<FactureBailleurArticle> factureBailleurArticleList;
    @JoinColumn(name = "idfacture", referencedColumnName = "idfacture")
    @ManyToOne(fetch = FetchType.LAZY)
    private Facture idfacture;
    @JoinColumn(name = "idlot", referencedColumnName = "idlot")
    @ManyToOne(fetch = FetchType.LAZY)
    private Lot idlot;
    @JoinColumn(name = "idproduit", referencedColumnName = "idproduit")
    @ManyToOne(fetch = FetchType.LAZY)
    private Produit idproduit;

    public Commande() {
    }

    public Commande(Long idcommande) {
        /*  74 */ this.idcommande = idcommande;
    }

    public Long getIdcommande() {
        /*  78 */ return this.idcommande;
    }

    public void setIdcommande(Long idcommande) {
        /*  82 */ this.idcommande = idcommande;
    }

    public Double getQuantite() {
        /*  86 */ return this.quantite;
    }

    public void setQuantite(Double quantite) {
        /*  90 */ this.quantite = quantite;
    }

    public Double getMontant() {
        /*  94 */ return this.montant;
    }

    public void setMontant(Double montant) {
        /*  98 */ this.montant = montant;
    }

    public String getSerie() {
        /* 102 */ return this.serie;
    }

    public void setSerie(String serie) {
        /* 106 */ this.serie = serie;
    }

    public String getDescription() {
        /* 110 */ return this.description;
    }

    public void setDescription(String description) {
        /* 114 */ this.description = description;
    }

    public Double getBenefice() {
        /* 118 */ return this.benefice;
    }

    public void setBenefice(Double benefice) {
        /* 122 */ this.benefice = benefice;
    }

    public Double getTva() {
        /* 126 */ return this.tva;
    }

    public void setTva(Double tva) {
        /* 130 */ this.tva = tva;
    }

    public Double getMontanttva() {
        /* 134 */ return this.montanttva;
    }

    public void setMontanttva(Double montanttva) {
        /* 138 */ this.montanttva = montanttva;
    }

    public Boolean getComptabilise() {
        /* 142 */ return this.comptabilise;
    }

    public void setComptabilise(Boolean comptabilise) {
        /* 146 */ this.comptabilise = comptabilise;
    }

    @XmlTransient
    public List<FactureBailleurArticle> getFactureBailleurArticleList() {
        /* 151 */ return this.factureBailleurArticleList;
    }

    public void setFactureBailleurArticleList(List<FactureBailleurArticle> factureBailleurArticleList) {
        /* 155 */ this.factureBailleurArticleList = factureBailleurArticleList;
    }

    public Facture getIdfacture() {
        /* 159 */ return this.idfacture;
    }

    public void setIdfacture(Facture idfacture) {
        /* 163 */ this.idfacture = idfacture;
    }

    public Lot getIdlot() {
        /* 167 */ return this.idlot;
    }

    public void setIdlot(Lot idlot) {
        this.idlot = idlot;
    }

    public Produit getIdproduit() {
        return this.idproduit;
    }

    public void setIdproduit(Produit idproduit) {
        /* 179 */ this.idproduit = idproduit;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.idcommande != null) ? this.idcommande.hashCode() : 0;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Commande)) {
            return false;
        }
        Commande other = (Commande) object;
        if ((this.idcommande == null && other.idcommande != null) || (this.idcommande != null && !this.idcommande.equals(other.idcommande))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Commande[ idcommande=" + this.idcommande + " ]";
    }
}
