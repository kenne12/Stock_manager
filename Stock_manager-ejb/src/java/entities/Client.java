package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
    @NamedQuery(name = "Client.findAll", query = "SELECT c FROM Client c"),
    @NamedQuery(name = "Client.findByIdclient", query = "SELECT c FROM Client c WHERE c.idclient = :idclient"),
    @NamedQuery(name = "Client.findByNom", query = "SELECT c FROM Client c WHERE c.nom = :nom"),
    @NamedQuery(name = "Client.findByPrenom", query = "SELECT c FROM Client c WHERE c.prenom = :prenom"),
    @NamedQuery(name = "Client.findByContact", query = "SELECT c FROM Client c WHERE c.contact = :contact"),
    @NamedQuery(name = "Client.findBySolde", query = "SELECT c FROM Client c WHERE c.solde = :solde"),
    @NamedQuery(name = "Client.findByNumeroCompte", query = "SELECT c FROM Client c WHERE c.numeroCompte = :numeroCompte"),
    @NamedQuery(name = "Client.findByContact2", query = "SELECT c FROM Client c WHERE c.contact2 = :contact2"),
    @NamedQuery(name = "Client.findByAdresse", query = "SELECT c FROM Client c WHERE c.adresse = :adresse"),
    @NamedQuery(name = "Client.findByEmail", query = "SELECT c FROM Client c WHERE c.email = :email"),
    @NamedQuery(name = "Client.findByCode", query = "SELECT c FROM Client c WHERE c.code = :code"),
    @NamedQuery(name = "Client.findByFax", query = "SELECT c FROM Client c WHERE c.fax = :fax")})
public class Client
        implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idclient;
    @Size(max = 254)
    private String nom;
    @Size(max = 254)
    private String prenom;
    @Size(max = 254)
    private String contact;
    private Integer solde;
    @Size(max = 2147483647)
    @Column(name = "numero_compte")
    private String numeroCompte;
    @Size(max = 2147483647)
    @Column(name = "contact_2")
    private String contact2;
    @Size(max = 2147483647)
    private String adresse;
    @Size(max = 2147483647)
    private String email;
    @Size(max = 2147483647)
    private String code;
    @Size(max = 2147483647)
    private String fax;
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "idclient", fetch = FetchType.LAZY)
    private List<Retrait> retraitList;
    @OneToMany(mappedBy = "idclient", fetch = FetchType.LAZY)
    private List<CommandeClient> commandeClientList;
    @OneToMany(mappedBy = "idclient", fetch = FetchType.LAZY)
    private List<Facture> factureList;
    @JoinColumn(name = "iddistrict", referencedColumnName = "iddistrict")
    @ManyToOne(fetch = FetchType.LAZY)
    private District iddistrict;

    public Client() {
    }

    public Client(Integer idclient) {
        /*  88 */ this.idclient = idclient;
    }

    public Integer getIdclient() {
        /*  92 */ return this.idclient;
    }

    public void setIdclient(Integer idclient) {
        /*  96 */ this.idclient = idclient;
    }

    public String getNom() {
        /* 100 */ return this.nom;
    }

    public void setNom(String nom) {
        /* 104 */ this.nom = nom;
    }

    public String getPrenom() {
        /* 108 */ return this.prenom;
    }

    public void setPrenom(String prenom) {
        /* 112 */ this.prenom = prenom;
    }

    public String getContact() {
        /* 116 */ return this.contact;
    }

    public void setContact(String contact) {
        /* 120 */ this.contact = contact;
    }

    public Integer getSolde() {
        /* 124 */ return this.solde;
    }

    public void setSolde(Integer solde) {
        /* 128 */ this.solde = solde;
    }

    public String getNumeroCompte() {
        /* 132 */ return this.numeroCompte;
    }

    public void setNumeroCompte(String numeroCompte) {
        /* 136 */ this.numeroCompte = numeroCompte;
    }

    public String getContact2() {
        /* 140 */ return this.contact2;
    }

    public void setContact2(String contact2) {
        /* 144 */ this.contact2 = contact2;
    }

    public String getAdresse() {
        /* 148 */ return this.adresse;
    }

    public void setAdresse(String adresse) {
        /* 152 */ this.adresse = adresse;
    }

    public String getEmail() {
        /* 156 */ return this.email;
    }

    public void setEmail(String email) {
        /* 160 */ this.email = email;
    }

    public String getCode() {
        /* 164 */ return this.code;
    }

    public void setCode(String code) {
        /* 168 */ this.code = code;
    }

    public String getFax() {
        /* 172 */ return this.fax;
    }

    public void setFax(String fax) {
        /* 176 */ this.fax = fax;
    }

    @XmlTransient
    public List<Retrait> getRetraitList() {
        /* 181 */ return this.retraitList;
    }

    public void setRetraitList(List<Retrait> retraitList) {
        /* 185 */ this.retraitList = retraitList;
    }

    @XmlTransient
    public List<CommandeClient> getCommandeClientList() {
        /* 190 */ return this.commandeClientList;
    }

    public void setCommandeClientList(List<CommandeClient> commandeClientList) {
        /* 194 */ this.commandeClientList = commandeClientList;
    }

    @XmlTransient
    public List<Facture> getFactureList() {
        /* 199 */ return this.factureList;
    }

    public void setFactureList(List<Facture> factureList) {
        /* 203 */ this.factureList = factureList;
    }

    public District getIddistrict() {
        /* 207 */ return this.iddistrict;
    }

    public void setIddistrict(District iddistrict) {
        /* 211 */ this.iddistrict = iddistrict;
    }

    @Override
    public int hashCode() {
        /* 216 */ int hash = 0;
        /* 217 */ hash += (this.idclient != null) ? this.idclient.hashCode() : 0;
        /* 218 */ return hash;
    }

    @Override
    public boolean equals(Object object) {
        /* 224 */ if (!(object instanceof Client)) {
            /* 225 */ return false;
        }
        /* 227 */ Client other = (Client) object;
        /* 228 */ if ((this.idclient == null && other.idclient != null) || (this.idclient != null && !this.idclient.equals(other.idclient))) {
            /* 229 */ return false;
        }
        /* 231 */ return true;
    }

    @Override
    public String toString() {
        /* 236 */ return "entities.Client[ idclient=" + this.idclient + " ]";
    }
}
