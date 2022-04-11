package controllers.famille;
 
 import controllers.famille.AbstractFamilleController;
import entities.Famille;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import utils.JsfUtil;
import utils.SessionMBean;
import utils.Utilitaires;

@ManagedBean
@ViewScoped
public class FamilleController
        extends AbstractFamilleController
        implements Serializable {

    @PostConstruct
    private void init() {
        /*  33 */ this.famille = new Famille();
    }

    public void prepareCreate() {
        /*  37 */ this.famille = new Famille();
        /*  38 */ this.mode = "Create";
        try {
            /*  40 */ if (Utilitaires.isAccess(Long.valueOf(10L))) {
                /*  41 */ this.showUserCreateDialog = Boolean.valueOf(true);
            } else {
                /*  43 */ this.showUserCreateDialog = Boolean.valueOf(false);
                /*  44 */ JsfUtil.addErrorMessage("Vous n'avez pas le privilège d'enregistrer une famille d'article");
            }
            /*  46 */        } catch (Exception e) {
            /*  47 */ e.printStackTrace();
        }
    }

    public void prepareDetail() {
    }

    public void prepareEdit() {
        /*  56 */ this.mode = "Edit";
        try {
            /*  58 */ if (Utilitaires.isAccess(Long.valueOf(11L))) {
                /*  59 */ this.showUserCreateDialog = Boolean.valueOf(true);
            } else {
                /*  61 */ this.showUserCreateDialog = Boolean.valueOf(false);
                /*  62 */ JsfUtil.addErrorMessage("Vous n'avez pas le privilège de modifier une famille d'article");
            }
            /*  64 */        } catch (Exception e) {
            /*  65 */ e.printStackTrace();
            /*  66 */ JsfUtil.addErrorMessage("Echec");
        }
    }

    public void create() {
        try {
            /*  72 */ if (this.mode.equals("Create")) {

                /*  74 */ this.famille.setIdfamille(this.familleFacadeLocal.nextVal());
                /*  75 */ this.familleFacadeLocal.create(this.famille);
                /*  76 */ Utilitaires.saveOperation(this.mouchardFacadeLocal, "Enregistrement du la famille des produits : " + this.famille.getNom(), SessionMBean.getUserAccount());
                /*  77 */ JsfUtil.addSuccessMessage("Client enregistré avec succès");
                /*  78 */ this.famille = new Famille();
            } /*  80 */ else if (this.famille != null) {
                /*  81 */ this.familleFacadeLocal.edit(this.famille);
                /*  82 */ JsfUtil.addSuccessMessage("Opération réussie");
            } else {

                /*  85 */ JsfUtil.addErrorMessage("Aucun famille selectionné");
            }

            /*  88 */        } catch (Exception e) {
            /*  89 */ e.printStackTrace();
        }
    }

    public void edit() {
    }

    public void delete() {
        try {
            /*  99 */ if (this.famille != null) {

                /* 101 */ if (Utilitaires.isAccess(Long.valueOf(12L))) {
                    /* 102 */ this.showUserCreateDialog = Boolean.valueOf(true);
                } else {
                    /* 104 */ this.showUserCreateDialog = Boolean.valueOf(false);
                    /* 105 */ JsfUtil.addErrorMessage("Vous n'avez pas le privilège de supprimer une famille de produit");

                    return;
                }
                /* 109 */ this.familleFacadeLocal.remove(this.famille);

                /* 111 */ Utilitaires.saveOperation(this.mouchardFacadeLocal, "Suppresion de la famille de produit : " + this.famille.getNom(), SessionMBean.getUserAccount());
                /* 112 */ JsfUtil.addSuccessMessage("Operation réussie");
            } else {
                /* 114 */ JsfUtil.addErrorMessage("Aucune Famille selectionnée");
            }
            /* 116 */        } catch (Exception e) {
            /* 117 */ e.printStackTrace();
            /* 118 */ JsfUtil.addErrorMessage("Echec de l'opération : impossible de supprimer");
        }
    }

    public void print() {
    }
}


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-war.war!\WEB-INF\classes\controllers\famille\FamilleController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
