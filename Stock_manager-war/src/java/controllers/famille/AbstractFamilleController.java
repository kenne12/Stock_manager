package controllers.famille;

import entities.Famille;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import sessions.FamilleFacadeLocal;
import sessions.MouchardFacadeLocal;
import sessions.PrivilegeFacadeLocal;

public class AbstractFamilleController {

    @EJB
    protected FamilleFacadeLocal familleFacadeLocal;
    protected Famille famille;
    /*  25 */    protected List<Famille> familles = new ArrayList<>();

    @EJB
    protected MouchardFacadeLocal mouchardFacadeLocal;

    @EJB
    protected PrivilegeFacadeLocal privilegeFacadeLocal;

    /*  33 */    protected Boolean detail = Boolean.valueOf(true);
    /*  34 */    protected Boolean modifier = Boolean.valueOf(true);
    /*  35 */    protected Boolean consulter = Boolean.valueOf(true);
    /*  36 */    protected Boolean imprimer = Boolean.valueOf(true);
    /*  37 */    protected Boolean supprimer = Boolean.valueOf(true);

    /*  39 */    protected Boolean showEditSolde = Boolean.valueOf(true);

    /*  41 */    protected Boolean showUserCreateDialog = Boolean.valueOf(false);
    /*  42 */    protected Boolean showUserDetailDialog = Boolean.valueOf(false);
    /*  43 */    protected Boolean showUserDeleteDialog = Boolean.valueOf(false);
    /*  44 */    protected Boolean showUserEditDialog = Boolean.valueOf(false);
    /*  45 */    protected Boolean showUserPrintDialog = Boolean.valueOf(false);

    protected boolean buttonActif = false;

    protected boolean buttonInactif = true;
    /*  50 */    protected String mode = "";

    public Boolean getDetail() {
        /*  53 */ return this.detail;
    }

    public void setDetail(Boolean detail) {
        /*  57 */ this.detail = detail;
    }

    public Boolean getModifier() {
        /*  61 */ return this.modifier;
    }

    public void setModifier(Boolean modifier) {
        /*  65 */ this.modifier = modifier;
    }

    public Boolean getConsulter() {
        /*  69 */ return this.consulter;
    }

    public void setConsulter(Boolean consulter) {
        /*  73 */ this.consulter = consulter;
    }

    public Boolean getImprimer() {
        /*  77 */ this.imprimer = Boolean.valueOf(this.familleFacadeLocal.findAll().isEmpty());
        /*  78 */ return this.imprimer;
    }

    public void setImprimer(Boolean imprimer) {
        /*  82 */ this.imprimer = imprimer;
    }

    public Boolean getSupprimer() {
        /*  86 */ return this.supprimer;
    }

    public void setSupprimer(Boolean supprimer) {
        /*  90 */ this.supprimer = supprimer;
    }

    public Boolean getShowEditSolde() {
        /*  94 */ return this.showEditSolde;
    }

    public void setShowEditSolde(Boolean showEditSolde) {
        /*  98 */ this.showEditSolde = showEditSolde;
    }

    public Famille getFamille() {
        /* 102 */ return this.famille;
    }

    public void setFamille(Famille famille) {
        /* 106 */ this.modifier = this.supprimer = this.detail = Boolean.valueOf((famille == null));
        /* 107 */ this.famille = famille;
    }

    public List<Famille> getFamilles() {
        /* 111 */ this.familles = this.familleFacadeLocal.findAllRange();
        /* 112 */ return this.familles;
    }

    public void setFamilles(List<Famille> familles) {
        /* 116 */ this.familles = familles;
    }

    public Boolean getShowUserCreateDialog() {
        /* 120 */ return this.showUserCreateDialog;
    }

    public void setShowUserCreateDialog(Boolean showUserCreateDialog) {
        /* 124 */ this.showUserCreateDialog = showUserCreateDialog;
    }

    public Boolean getShowUserDetailDialog() {
        /* 128 */ return this.showUserDetailDialog;
    }

    public void setShowUserDetailDialog(Boolean showUserDetailDialog) {
        /* 132 */ this.showUserDetailDialog = showUserDetailDialog;
    }

    public Boolean getShowUserDeleteDialog() {
        /* 136 */ return this.showUserDeleteDialog;
    }

    public void setShowUserDeleteDialog(Boolean showUserDeleteDialog) {
        /* 140 */ this.showUserDeleteDialog = showUserDeleteDialog;
    }

    public Boolean getShowUserEditDialog() {
        /* 144 */ return this.showUserEditDialog;
    }

    public void setShowUserEditDialog(Boolean showUserEditDialog) {
        /* 148 */ this.showUserEditDialog = showUserEditDialog;
    }

    public Boolean getShowUserPrintDialog() {
        /* 152 */ return this.showUserPrintDialog;
    }

    public void setShowUserPrintDialog(Boolean showUserPrintDialog) {
        /* 156 */ this.showUserPrintDialog = showUserPrintDialog;
    }

    public boolean isButtonActif() {
        /* 160 */ return this.buttonActif;
    }

    public void setButtonActif(boolean buttonActif) {
        /* 164 */ this.buttonActif = buttonActif;
    }

    public boolean isButtonInactif() {
        /* 168 */ return this.buttonInactif;
    }

    public void setButtonInactif(boolean buttonInactif) {
        /* 172 */ this.buttonInactif = buttonInactif;
    }
}


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-war.war!\WEB-INF\classes\controllers\famille\AbstractFamilleController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
