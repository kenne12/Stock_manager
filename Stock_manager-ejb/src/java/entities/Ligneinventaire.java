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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ligneinventaire.findAll", query = "SELECT l FROM Ligneinventaire l"),
    @NamedQuery(name = "Ligneinventaire.findByIdLigneinventaire", query = "SELECT l FROM Ligneinventaire l WHERE l.idLigneinventaire = :idLigneinventaire"),
    @NamedQuery(name = "Ligneinventaire.findByQuantiteLogique", query = "SELECT l FROM Ligneinventaire l WHERE l.quantiteLogique = :quantiteLogique"),
    @NamedQuery(name = "Ligneinventaire.findByQuantitePhysique", query = "SELECT l FROM Ligneinventaire l WHERE l.quantitePhysique = :quantitePhysique"),
    @NamedQuery(name = "Ligneinventaire.findByPrix", query = "SELECT l FROM Ligneinventaire l WHERE l.prix = :prix"),
    @NamedQuery(name = "Ligneinventaire.findByEcart", query = "SELECT l FROM Ligneinventaire l WHERE l.ecart = :ecart"),
    @NamedQuery(name = "Ligneinventaire.findByObservation", query = "SELECT l FROM Ligneinventaire l WHERE l.observation = :observation")})
public class Ligneinventaire implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_ligneinventaire")
    private Long idLigneinventaire;
    @Column(name = "quantite_logique")
    private Double quantiteLogique;
    @Column(name = "quantite_physique")
    private Double quantitePhysique;
    private Double prix;
    private Double ecart;
    @Size(max = 2147483647)
    private String observation;
    @JoinColumn(name = "id_inventaire", referencedColumnName = "idinventaire")
    @ManyToOne(fetch = FetchType.LAZY)
    private Inventaire idInventaire;
    @JoinColumn(name = "idlot", referencedColumnName = "idlot")
    @ManyToOne(fetch = FetchType.LAZY)
    private Lot idlot;

    public Ligneinventaire() {
    }

    public Ligneinventaire(Long idLigneinventaire) {
        /*  63 */ this.idLigneinventaire = idLigneinventaire;
    }

    public Long getIdLigneinventaire() {
        /*  67 */ return this.idLigneinventaire;
    }

    public void setIdLigneinventaire(Long idLigneinventaire) {
        /*  71 */ this.idLigneinventaire = idLigneinventaire;
    }

    public Double getQuantiteLogique() {
        /*  75 */ return this.quantiteLogique;
    }

    public void setQuantiteLogique(Double quantiteLogique) {
        /*  79 */ this.quantiteLogique = quantiteLogique;
    }

    public Double getQuantitePhysique() {
        /*  83 */ return this.quantitePhysique;
    }

    public void setQuantitePhysique(Double quantitePhysique) {
        /*  87 */ this.quantitePhysique = quantitePhysique;
    }

    public Double getPrix() {
        /*  91 */ return this.prix;
    }

    public void setPrix(Double prix) {
        /*  95 */ this.prix = prix;
    }

    public Double getEcart() {
        /*  99 */ return this.ecart;
    }

    public void setEcart(Double ecart) {
        /* 103 */ this.ecart = ecart;
    }

    public String getObservation() {
        /* 107 */ return this.observation;
    }

    public void setObservation(String observation) {
        /* 111 */ this.observation = observation;
    }

    public Inventaire getIdInventaire() {
        /* 115 */ return this.idInventaire;
    }

    public void setIdInventaire(Inventaire idInventaire) {
        /* 119 */ this.idInventaire = idInventaire;
    }

    public Lot getIdlot() {
        /* 123 */ return this.idlot;
    }

    public void setIdlot(Lot idlot) {
        /* 127 */ this.idlot = idlot;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.idLigneinventaire != null) ? this.idLigneinventaire.hashCode() : 0;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Ligneinventaire)) {
            return false;
        }
        Ligneinventaire other = (Ligneinventaire) object;
        if ((this.idLigneinventaire == null && other.idLigneinventaire != null) || (this.idLigneinventaire != null && !this.idLigneinventaire.equals(other.idLigneinventaire))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Ligneinventaire[ idLigneinventaire=" + this.idLigneinventaire + " ]";
    }
}
