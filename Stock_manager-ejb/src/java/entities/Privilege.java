package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Privilege.findAll", query = "SELECT p FROM Privilege p"),
    @NamedQuery(name = "Privilege.findByIdprivilege", query = "SELECT p FROM Privilege p WHERE p.idprivilege = :idprivilege")})
public class Privilege implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Long idprivilege;
    @JoinColumn(name = "idmenu", referencedColumnName = "idmenu")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Menu idmenu;
    @JoinColumn(name = "idutilisateur", referencedColumnName = "idutilisateur")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Utilisateur idutilisateur;

    public Privilege() {
    }

    public Privilege(Long idprivilege) {
        /* 46 */ this.idprivilege = idprivilege;
    }

    public Long getIdprivilege() {
        /* 50 */ return this.idprivilege;
    }

    public void setIdprivilege(Long idprivilege) {
        /* 54 */ this.idprivilege = idprivilege;
    }

    public Menu getIdmenu() {
        /* 58 */ return this.idmenu;
    }

    public void setIdmenu(Menu idmenu) {
        /* 62 */ this.idmenu = idmenu;
    }

    public Utilisateur getIdutilisateur() {
        /* 66 */ return this.idutilisateur;
    }

    public void setIdutilisateur(Utilisateur idutilisateur) {
        /* 70 */ this.idutilisateur = idutilisateur;
    }

    @Override
    public int hashCode() {
        /* 75 */ int hash = 0;
        /* 76 */ hash += (this.idprivilege != null) ? this.idprivilege.hashCode() : 0;
        /* 77 */ return hash;
    }

    @Override
    public boolean equals(Object object) {
        /* 83 */ if (!(object instanceof Privilege)) {
            /* 84 */ return false;
        }
        /* 86 */ Privilege other = (Privilege) object;
        /* 87 */ if ((this.idprivilege == null && other.idprivilege != null) || (this.idprivilege != null && !this.idprivilege.equals(other.idprivilege))) {
            /* 88 */ return false;
        }
        /* 90 */ return true;
    }

    @Override
    public String toString() {
        /* 95 */ return "entities.Privilege[ idprivilege=" + this.idprivilege + " ]";
    }
}
