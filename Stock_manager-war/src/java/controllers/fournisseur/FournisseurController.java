package controllers.fournisseur;

import entities.Fournisseur;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import utils.JsfUtil;
import utils.SessionMBean;
import utils.Utilitaires;

@ManagedBean
@ViewScoped
public class FournisseurController
        extends AbstractFournisseurController
        implements Serializable {

    @PostConstruct
    private void init() {
        /*  33 */ this.fournisseur = new Fournisseur();
    }

    public void prepareCreate() {
        try {
            /*  38 */ if (Utilitaires.isAccess(Long.valueOf(16L))) {
                /*  39 */ this.showUserCreateDialog = Boolean.valueOf(true);
            } else {
                /*  41 */ this.showUserCreateDialog = Boolean.valueOf(false);
                /*  42 */ JsfUtil.addErrorMessage("Vous n'avez pas le privilège d'enregistrer un fournisseur");
                return;
            }
            /*  45 */ this.mode = "Create";
            /*  46 */ this.fournisseur = new Fournisseur();
            /*  47 */ this.fournisseur.setContact1("-");
            /*  48 */ this.fournisseur.setContact2("-");
            /*  49 */        } catch (Exception e) {
            /*  50 */ e.printStackTrace();
            /*  51 */ JsfUtil.addErrorMessage("Echec");
        }
    }

    public void prepareEdit() {
        /*  56 */ this.mode = "Edit";
        try {
            /*  58 */ if (Utilitaires.isAccess(Long.valueOf(17L))) {
                /*  59 */ this.showUserCreateDialog = Boolean.valueOf(true);
            } else {
                /*  61 */ this.showUserCreateDialog = Boolean.valueOf(false);
                /*  62 */ JsfUtil.addErrorMessage("Vous n'avez pas le privilège de modifier un fournisseur");
                return;
            }
            /*  65 */        } catch (Exception e) {
            /*  66 */ e.printStackTrace();
            /*  67 */ JsfUtil.addErrorMessage("Echec");
        }
    }

    public void create() {
        try {
            /*  73 */ if (this.mode.equals("Create")) {
                /*  74 */ this.fournisseur.setIdfournisseur(this.fournisseurFacadeLocal.nextVal());
                /*  75 */ this.fournisseurFacadeLocal.create(this.fournisseur);
                /*  76 */ Utilitaires.saveOperation(this.mouchardFacadeLocal, "Enregistrement du fournisseur : " + this.fournisseur.getRaisonSociale(), SessionMBean.getUserAccount());
                /*  77 */ JsfUtil.addSuccessMessage("Fournisseur enregistré avec succès");
                /*  78 */ this.fournisseur = new Fournisseur();
            } /*  80 */ else if (this.fournisseur != null) {
                /*  81 */ this.fournisseurFacadeLocal.edit(this.fournisseur);
                /*  82 */ JsfUtil.addSuccessMessage("Opération réussie");
            } else {
                /*  84 */ JsfUtil.addErrorMessage("Aucun fournisseur selectionné");
            }

            /*  87 */        } catch (Exception e) {
            /*  88 */ e.printStackTrace();
            /*  89 */ JsfUtil.addErrorMessage("Echec");
        }
    }

    public void delete() {
        try {
            /*  95 */ if (this.fournisseur != null) {

                /*  97 */ if (Utilitaires.isAccess(Long.valueOf(18L))) {
                    /*  98 */ this.showUserCreateDialog = Boolean.valueOf(true);
                } else {
                    /* 100 */ this.showUserCreateDialog = Boolean.valueOf(false);
                    /* 101 */ JsfUtil.addErrorMessage("Vous n'avez pas le privilège de supprimer un fournisseur");

                    return;
                }
                /* 105 */ this.fournisseurFacadeLocal.remove(this.fournisseur);

                /* 107 */ Utilitaires.saveOperation(this.mouchardFacadeLocal, "Suppresion du fournisseur : " + this.fournisseur.getRaisonSociale(), SessionMBean.getUserAccount());

                /* 109 */ JsfUtil.addSuccessMessage("Operation réussie");
            } else {
                /* 111 */ JsfUtil.addErrorMessage("Aucun Fournisseur selectionnée");
            }
            /* 113 */        } catch (Exception e) {
            /* 114 */ e.printStackTrace();
        }
    }

    public void changeStatus(Fournisseur fournisseur, String mode) {
    }

    public void print() {
    }
}


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-war.war!\WEB-INF\classes\controllers\fournisseur\FournisseurController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
