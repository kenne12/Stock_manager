package controllers.privilege;

import controllers.privilege.AbstractPrivilegeController;
import controllers.privilege.PrivilegeInterfaceController;
import entities.Menu;
import entities.Privilege;
import entities.Utilisateur;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import utils.JsfUtil;
import utils.SessionMBean;
import utils.Utilitaires;

@ManagedBean
@ViewScoped
public class PrivilegeController
        extends AbstractPrivilegeController
        implements PrivilegeInterfaceController, Serializable {

    @PostConstruct
    private void initAcces() {
        /*  31 */ this.utilisateur = new Utilisateur();
    }

    public void prepareCreate() {
        /*  35 */ this.dualMenu.getSource().clear();
        /*  36 */ this.dualMenu.getTarget().clear();

        try {
            /*  39 */ if (Utilitaires.isAccess(Long.valueOf(5L))) {
                /*  40 */ this.showPrivilegeCreateDialog = Boolean.valueOf(true);
                return;
            }
            /*  43 */ this.showPrivilegeCreateDialog = Boolean.valueOf(false);
            /*  44 */ JsfUtil.addErrorMessage("Vous n'avez pas le droit d'attribuer les privilèges aux utilisateurs");
        } /*  46 */ catch (Exception e) {
            /*  47 */ e.printStackTrace();
        }
    }

    public void viewAccess(Utilisateur utilisateur) {
        try {
            /*  53 */ this.utilisateur = utilisateur;
            /*  54 */ this.privileges = this.privilegeFacadeLocal.findByUser(utilisateur.getIdutilisateur().intValue());
            /*  55 */        } catch (Exception e) {
            /*  56 */ e.printStackTrace();
            /*  57 */ JsfUtil.addErrorMessage("Echec");
        }
    }

    public void handleUserChange() {
        /*  62 */ this.dualMenu.getSource().clear();
        /*  63 */ this.dualMenu.getTarget().clear();
        try {
            /*  65 */ if (this.utilisateur.getIdutilisateur() != null) {

                /*  67 */ this.utilisateur = this.utilisateurFacadeLocal.find(this.utilisateur.getIdutilisateur());

                /*  69 */ List<Privilege> privilegeTemp = this.privilegeFacadeLocal.findByUser(this.utilisateur.getIdutilisateur().intValue());
                /*  70 */ if (privilegeTemp.isEmpty()) {
                    /*  71 */ this.dualMenu.setSource(this.menuFacadeLocal.findAll());
                } else {

                    /*  74 */ List<Menu> menusTarget = new ArrayList<>();

                    /*  76 */ for (Privilege p : privilegeTemp) {
                        /*  77 */ menusTarget.add(p.getIdmenu());
                    }

                    /*  80 */ this.dualMenu.getTarget().addAll(menusTarget);
                    /*  81 */ this.dualMenu.getSource().addAll(this.menuFacadeLocal.findAll());
                    /*  82 */ this.dualMenu.getSource().removeAll(menusTarget);
                }
            }
            /*  85 */        } catch (Exception e) {
            /*  86 */ e.printStackTrace();
        }
    }

    public void save() {
        try {
            /*  93 */ if (this.utilisateur.getIdutilisateur() != null) {
                /*  94 */ this.utilisateur = this.utilisateurFacadeLocal.find(this.utilisateur.getIdutilisateur());

                /*  96 */ for (Menu m : this.dualMenu.getSource()) {
                    /*  97 */ Privilege p = this.privilegeFacadeLocal.findByUser(this.utilisateur.getIdutilisateur().intValue(), m.getIdmenu().intValue());
                    /*  98 */ if (p != null) {
                        /*  99 */ this.privilegeFacadeLocal.remove(p);
                        /* 100 */ Utilitaires.saveOperation(this.mouchardFacadeLocal, "RETRAIT DU PRIVILEGE -> " + m.getNom() + " à l'utilisateur -> " + this.utilisateur.getNom() + " " + this.utilisateur.getPrenom(), SessionMBean.getUserAccount());
                    }
                }

                /* 104 */ Boolean flag = Boolean.valueOf(false);
                /* 105 */ for (Menu m : this.dualMenu.getTarget()) {

                    /* 107 */ if (!flag.booleanValue()) {

                        /* 109 */ if (m.getIdmenu().intValue() == 1) {
                            /* 110 */ flag = Boolean.valueOf(true);
                            /* 111 */ Privilege privilege = this.privilegeFacadeLocal.findByUser(this.utilisateur.getIdutilisateur().intValue(), 1);
                            /* 112 */ if (privilege == null) {
                                /* 113 */ privilege = new Privilege();
                                /* 114 */ privilege.setIdprivilege(this.privilegeFacadeLocal.nextVal());
                                /* 115 */ privilege.setIdmenu(m);
                                /* 116 */ privilege.setIdutilisateur(this.utilisateur);
                                /* 117 */ this.privilegeFacadeLocal.create(privilege);
                                /* 118 */ Utilitaires.saveOperation(this.mouchardFacadeLocal, "ATTRIBUTION DU PRIVILEGE : SUPER ADMINISTRATEUR à l'utilisateur -> " + this.utilisateur.getNom() + " " + this.utilisateur.getPrenom(), SessionMBean.getUserAccount());
                                break;
                            }
                            /* 121 */ JsfUtil.addSuccessMessage("Vous disposez dejà du privilège SUPER ADMINISTRATEUR");

                            break;
                        }
                        /* 125 */ Privilege p = this.privilegeFacadeLocal.findByUser(this.utilisateur.getIdutilisateur().intValue(), m.getIdmenu().intValue());
                        /* 126 */ if (p == null) {
                            /* 127 */ p = new Privilege();
                            /* 128 */ p.setIdprivilege(this.privilegeFacadeLocal.nextVal());
                            /* 129 */ p.setIdmenu(m);
                            /* 130 */ p.setIdutilisateur(this.utilisateur);
                            /* 131 */ this.privilegeFacadeLocal.create(p);
                            /* 132 */ Utilitaires.saveOperation(this.mouchardFacadeLocal, "ATTRIBUTION DU PRIVILEGE -> " + m.getNom() + " à l'utilisateur -> " + this.utilisateur.getNom() + " " + this.utilisateur.getPrenom(), SessionMBean.getUserAccount());
                        }
                    }
                }

                /* 138 */ JsfUtil.addSuccessMessage("Opération réussie");
            } else {

                /* 141 */ JsfUtil.addErrorMessage("Aucun utilisateur selectionné");
            }
            /* 143 */        } catch (Exception e) {
            /* 144 */ e.printStackTrace();
            /* 145 */ JsfUtil.addErrorMessage("Echec");
        }
    }

    public void delete() {
    }

    public void print() {
    }
}
