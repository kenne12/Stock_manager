package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Journee.findAll", query = "SELECT j FROM Journee j"),
    @NamedQuery(name = "Journee.findByIdjournee", query = "SELECT j FROM Journee j WHERE j.idjournee = :idjournee"),
    @NamedQuery(name = "Journee.findByDateJour", query = "SELECT j FROM Journee j WHERE j.dateJour = :dateJour"),
    @NamedQuery(name = "Journee.findByIdutilisateurFermetture", query = "SELECT j FROM Journee j WHERE j.idutilisateurFermetture = :idutilisateurFermetture"),
    @NamedQuery(name = "Journee.findByHeureOuverture", query = "SELECT j FROM Journee j WHERE j.heureOuverture = :heureOuverture"),
    @NamedQuery(name = "Journee.findByHeureFermetture", query = "SELECT j FROM Journee j WHERE j.heureFermetture = :heureFermetture"),
    @NamedQuery(name = "Journee.findByOuverte", query = "SELECT j FROM Journee j WHERE j.ouverte = :ouverte"),
    @NamedQuery(name = "Journee.findByFermetture", query = "SELECT j FROM Journee j WHERE j.fermetture = :fermetture"),
    @NamedQuery(name = "Journee.findByMontant", query = "SELECT j FROM Journee j WHERE j.montant = :montant")})
public class Journee implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Long idjournee;
    @Column(name = "date_jour")
    @Temporal(TemporalType.DATE)
    private Date dateJour;
    @Column(name = "idutilisateur_fermetture")
    private Integer idutilisateurFermetture;
    @Column(name = "heure_ouverture")
    @Temporal(TemporalType.TIMESTAMP)
    private Date heureOuverture;
    @Column(name = "heure_fermetture")
    @Temporal(TemporalType.TIMESTAMP)
    private Date heureFermetture;
    private Boolean ouverte;
    private Boolean fermetture;
    private Double montant;
    private Double bord;
    @JoinColumn(name = "idmois", referencedColumnName = "id_annee_mois")
    @ManyToOne(fetch = FetchType.LAZY)
    private AnneeMois idmois;
    @JoinColumn(name = "idutilisateur_ouverture", referencedColumnName = "idutilisateur")
    @ManyToOne(fetch = FetchType.LAZY)
    private Utilisateur idutilisateurOuverture;

    @Column(name = "quantite_entree")
    private double quantiteEntree;
    @Column(name = "quantite_sortie")
    private double quantiteSortie;
    @Column(name = "montant_entree")
    private double montantEntree;

    public Journee() {
    }

    public Journee(Long idjournee) {
        /*  76 */ this.idjournee = idjournee;
    }

    public Long getIdjournee() {
        /*  80 */ return this.idjournee;
    }

    public void setIdjournee(Long idjournee) {
        /*  84 */ this.idjournee = idjournee;
    }

    public Date getDateJour() {
        /*  88 */ return this.dateJour;
    }

    public void setDateJour(Date dateJour) {
        /*  92 */ this.dateJour = dateJour;
    }

    public Integer getIdutilisateurFermetture() {
        /*  96 */ return this.idutilisateurFermetture;
    }

    public void setIdutilisateurFermetture(Integer idutilisateurFermetture) {
        /* 100 */ this.idutilisateurFermetture = idutilisateurFermetture;
    }

    public Date getHeureOuverture() {
        /* 104 */ return this.heureOuverture;
    }

    public void setHeureOuverture(Date heureOuverture) {
        /* 108 */ this.heureOuverture = heureOuverture;
    }

    public Date getHeureFermetture() {
        /* 112 */ return this.heureFermetture;
    }

    public void setHeureFermetture(Date heureFermetture) {
        /* 116 */ this.heureFermetture = heureFermetture;
    }

    public Boolean getOuverte() {
        /* 120 */ return this.ouverte;
    }

    public void setOuverte(Boolean ouverte) {
        /* 124 */ this.ouverte = ouverte;
    }

    public Double getBord() {
        /* 128 */ return this.bord;
    }

    public void setBord(Double bord) {
        /* 132 */ this.bord = bord;
    }

    public Boolean getFermetture() {
        /* 136 */ return this.fermetture;
    }

    public void setFermetture(Boolean fermetture) {
        /* 140 */ this.fermetture = fermetture;
    }

    public Double getMontant() {
        /* 144 */ return this.montant;
    }

    public void setMontant(Double montant) {
        /* 148 */ this.montant = montant;
    }

    public double getQuantiteEntree() {
        return quantiteEntree;
    }

    public void setQuantiteEntree(double quantiteEntree) {
        this.quantiteEntree = quantiteEntree;
    }

    public double getQuantiteSortie() {
        return quantiteSortie;
    }

    public void setQuantiteSortie(double quantiteSortie) {
        this.quantiteSortie = quantiteSortie;
    }

    public double getMontantEntree() {
        return montantEntree;
    }

    public void setMontantEntree(double montantEntree) {
        this.montantEntree = montantEntree;
    }

    public AnneeMois getIdmois() {
        /* 152 */ return this.idmois;
    }

    public void setIdmois(AnneeMois idmois) {
        /* 156 */ this.idmois = idmois;
    }

    public Utilisateur getIdutilisateurOuverture() {
        /* 160 */ return this.idutilisateurOuverture;
    }

    public void setIdutilisateurOuverture(Utilisateur idutilisateurOuverture) {
        /* 164 */ this.idutilisateurOuverture = idutilisateurOuverture;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.idjournee != null) ? this.idjournee.hashCode() : 0;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Journee)) {
            return false;
        }
        Journee other = (Journee) object;
        if ((this.idjournee == null && other.idjournee != null) || (this.idjournee != null && !this.idjournee.equals(other.idjournee))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Journee[ idjournee=" + this.idjournee + " ]";
    }
}
