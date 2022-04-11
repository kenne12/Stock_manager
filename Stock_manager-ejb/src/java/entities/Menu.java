package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
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
    @NamedQuery(name = "Menu.findAll", query = "SELECT m FROM Menu m"),
    @NamedQuery(name = "Menu.findByIdmenu", query = "SELECT m FROM Menu m WHERE m.idmenu = :idmenu"),
    @NamedQuery(name = "Menu.findByNom", query = "SELECT m FROM Menu m WHERE m.nom = :nom"),
    @NamedQuery(name = "Menu.findByRessource", query = "SELECT m FROM Menu m WHERE m.ressource = :ressource")})
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idmenu;
    @Size(max = 254)
    private String nom;
    @Size(max = 254)
    private String ressource;
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "idmenu", fetch = FetchType.LAZY)
    private List<Privilege> privilegeList;

    public Menu() {
    }

    public Menu(Integer idmenu) {
        /*  51 */ this.idmenu = idmenu;
    }

    public Integer getIdmenu() {
        /*  55 */ return this.idmenu;
    }

    public void setIdmenu(Integer idmenu) {
        /*  59 */ this.idmenu = idmenu;
    }

    public String getNom() {
        /*  63 */ return this.nom;
    }

    public void setNom(String nom) {
        /*  67 */ this.nom = nom;
    }

    public String getRessource() {
        /*  71 */ return this.ressource;
    }

    public void setRessource(String ressource) {
        /*  75 */ this.ressource = ressource;
    }

    @XmlTransient
    public List<Privilege> getPrivilegeList() {
        /*  80 */ return this.privilegeList;
    }

    public void setPrivilegeList(List<Privilege> privilegeList) {
        /*  84 */ this.privilegeList = privilegeList;
    }

    @Override
    public int hashCode() {
        /*  89 */ int hash = 0;
        /*  90 */ hash += (this.idmenu != null) ? this.idmenu.hashCode() : 0;
        /*  91 */ return hash;
    }

    @Override
    public boolean equals(Object object) {
        /*  97 */ if (!(object instanceof Menu)) {
            /*  98 */ return false;
        }
        /* 100 */ Menu other = (Menu) object;
        /* 101 */ if ((this.idmenu == null && other.idmenu != null) || (this.idmenu != null && !this.idmenu.equals(other.idmenu))) {
            /* 102 */ return false;
        }
        /* 104 */ return true;
    }

    @Override
    public String toString() {
        /* 109 */ return "entities.Menu[ idmenu=" + this.idmenu + " ]";
    }
}
