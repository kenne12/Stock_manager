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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "ligne_mvt_stock")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LigneMvtStock.findAll", query = "SELECT l FROM LigneMvtStock l"),
    @NamedQuery(name = "LigneMvtStock.findByIdLigneMvtStock", query = "SELECT l FROM LigneMvtStock l WHERE l.idLigneMvtStock = :idLigneMvtStock"),
    @NamedQuery(name = "LigneMvtStock.findByType", query = "SELECT l FROM LigneMvtStock l WHERE l.type = :type"),
    @NamedQuery(name = "LigneMvtStock.findByQuantiteEntree", query = "SELECT l FROM LigneMvtStock l WHERE l.quantiteEntree = :quantiteEntree"),
    @NamedQuery(name = "LigneMvtStock.findByQuantiteSortie", query = "SELECT l FROM LigneMvtStock l WHERE l.quantiteSortie = :quantiteSortie"),
    @NamedQuery(name = "LigneMvtStock.findByClient", query = "SELECT l FROM LigneMvtStock l WHERE l.client = :client"),
    @NamedQuery(name = "LigneMvtStock.findByFournisseur", query = "SELECT l FROM LigneMvtStock l WHERE l.fournisseur = :fournisseur"),
    @NamedQuery(name = "LigneMvtStock.findByReste", query = "SELECT l FROM LigneMvtStock l WHERE l.reste = :reste")})
public class LigneMvtStock implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_ligne_mvt_stock")
    private Long idLigneMvtStock;
    @Size(max = 20)
    private String type;
    @Column(name = "quantite_entree")
    private Double quantiteEntree;
    @Column(name = "quantite_sortie")
    private Double quantiteSortie;
    @Column(name = "quantite_avant")
    private Double quantiteAvant;
    @Size(max = 100)
    private String client;
    @Size(max = 100)
    private String fournisseur;
    private Double reste;
    @JoinColumn(name = "idlot", referencedColumnName = "idlot")
    @ManyToOne(fetch = FetchType.LAZY)
    private Lot idlot;
    @JoinColumn(name = "id_mvt_stock", referencedColumnName = "id_mvt_stock")
    @ManyToOne(fetch = FetchType.LAZY)
    private MvtStock idMvtStock;

    public LigneMvtStock() {
    }

    public LigneMvtStock(Long idLigneMvtStock) {
        this.idLigneMvtStock = idLigneMvtStock;
    }

    public Long getIdLigneMvtStock() {
        return this.idLigneMvtStock;
    }

    public void setIdLigneMvtStock(Long idLigneMvtStock) {
        this.idLigneMvtStock = idLigneMvtStock;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getQuantiteEntree() {
        return this.quantiteEntree;
    }

    public void setQuantiteEntree(Double quantiteEntree) {
        this.quantiteEntree = quantiteEntree;
    }

    public Double getQuantiteSortie() {
        /*  97 */ return this.quantiteSortie;
    }

    public void setQuantiteSortie(Double quantiteSortie) {
        /* 101 */ this.quantiteSortie = quantiteSortie;
    }

    public String getClient() {
        /* 105 */ return this.client;
    }

    public void setClient(String client) {
        /* 109 */ this.client = client;
    }

    public String getFournisseur() {
        /* 113 */ return this.fournisseur;
    }

    public void setFournisseur(String fournisseur) {
        this.fournisseur = fournisseur;
    }

    public Double getReste() {
        return this.reste;
    }

    public void setReste(Double reste) {
        this.reste = reste;
    }

    public Lot getIdlot() {
        return this.idlot;
    }

    public void setIdlot(Lot idlot) {
        this.idlot = idlot;
    }

    public MvtStock getIdMvtStock() {
        return this.idMvtStock;
    }

    public void setIdMvtStock(MvtStock idMvtStock) {
        this.idMvtStock = idMvtStock;
    }

    public Double getQuantiteAvant() {
        return quantiteAvant;
    }

    public void setQuantiteAvant(Double quantiteAvant) {
        this.quantiteAvant = quantiteAvant;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.idLigneMvtStock != null) ? this.idLigneMvtStock.hashCode() : 0;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof LigneMvtStock)) {
            return false;
        }
        LigneMvtStock other = (LigneMvtStock) object;
        if ((this.idLigneMvtStock == null && other.idLigneMvtStock != null) || (this.idLigneMvtStock != null && !this.idLigneMvtStock.equals(other.idLigneMvtStock))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.LigneMvtStock[ idLigneMvtStock=" + this.idLigneMvtStock + " ]";
    }
}
