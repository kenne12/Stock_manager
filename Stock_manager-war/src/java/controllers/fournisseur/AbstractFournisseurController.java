package controllers.fournisseur;

import entities.Fournisseur;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import sessions.FournisseurFacadeLocal;
import sessions.MouchardFacadeLocal;
import sessions.PrivilegeFacadeLocal;

public class AbstractFournisseurController {

    @EJB
    protected FournisseurFacadeLocal fournisseurFacadeLocal;
    protected Fournisseur fournisseur;
    /*  25 */    protected List<Fournisseur> fournisseurs = new ArrayList<>();

    /*  27 */    protected List<Fournisseur> fournisseurActifs = new ArrayList<>();
    /*  28 */    protected List<Fournisseur> fournisseurInactifs = new ArrayList<>();

    @EJB
    protected MouchardFacadeLocal mouchardFacadeLocal;

    @EJB
    protected PrivilegeFacadeLocal privilegeFacadeLocal;

    /*  36 */    protected Boolean detail = Boolean.valueOf(true);
    /*  37 */    protected Boolean modifier = Boolean.valueOf(true);
    /*  38 */    protected Boolean consulter = Boolean.valueOf(true);
    /*  39 */    protected Boolean imprimer = Boolean.valueOf(true);
    /*  40 */    protected Boolean supprimer = Boolean.valueOf(true);

    /*  42 */    protected Boolean showEditSolde = Boolean.valueOf(true);

    /*  44 */    protected Boolean showUserCreateDialog = Boolean.valueOf(false);
    /*  45 */    protected Boolean showUserDetailDialog = Boolean.valueOf(false);
    /*  46 */    protected Boolean showUserDeleteDialog = Boolean.valueOf(false);
    /*  47 */    protected Boolean showUserEditDialog = Boolean.valueOf(false);
    /*  48 */    protected Boolean showUserPrintDialog = Boolean.valueOf(false);

    protected boolean buttonActif = false;

    protected boolean buttonInactif = true;
    /*  53 */    protected String mode = "";

    public Boolean getDetail() {
        /*  56 */ return this.detail;
    }

    public void setDetail(Boolean detail) {
        /*  60 */ this.detail = detail;
    }

    public Boolean getModifier() {
        /*  64 */ return this.modifier;
    }

    public void setModifier(Boolean modifier) {
        /*  68 */ this.modifier = modifier;
    }

    public Boolean getConsulter() {
        /*  72 */ return this.consulter;
    }

    public void setConsulter(Boolean consulter) {
        /*  76 */ this.consulter = consulter;
    }

    public Boolean getImprimer() {
        /*  80 */ this.imprimer = Boolean.valueOf(this.fournisseurFacadeLocal.findAll().isEmpty());
        /*  81 */ return this.imprimer;
    }

    public void setImprimer(Boolean imprimer) {
        /*  85 */ this.imprimer = imprimer;
    }

    public Boolean getSupprimer() {
        /*  89 */ return this.supprimer;
    }

    public void setSupprimer(Boolean supprimer) {
        /*  93 */ this.supprimer = supprimer;
    }

    public Boolean getShowEditSolde() {
        /*  97 */ return this.showEditSolde;
    }

    public void setShowEditSolde(Boolean showEditSolde) {
        /* 101 */ this.showEditSolde = showEditSolde;
    }

    public Fournisseur getFournisseur() {
        /* 105 */ return this.fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        /* 109 */ this.modifier = this.supprimer = this.detail = Boolean.valueOf((fournisseur == null));
        /* 110 */ this.fournisseur = fournisseur;
    }

    public List<Fournisseur> getFournisseurs() {
        /* 114 */ this.fournisseurs = this.fournisseurFacadeLocal.findAllRange();
        /* 115 */ return this.fournisseurs;
    }

    public void setFournisseurs(List<Fournisseur> fournisseurs) {
        /* 119 */ this.fournisseurs = fournisseurs;
    }

    public Boolean getShowUserCreateDialog() {
        /* 123 */ return this.showUserCreateDialog;
    }

    public void setShowUserCreateDialog(Boolean showUserCreateDialog) {
        /* 127 */ this.showUserCreateDialog = showUserCreateDialog;
    }

    public Boolean getShowUserDetailDialog() {
        /* 131 */ return this.showUserDetailDialog;
    }

    public void setShowUserDetailDialog(Boolean showUserDetailDialog) {
        /* 135 */ this.showUserDetailDialog = showUserDetailDialog;
    }

    public Boolean getShowUserDeleteDialog() {
        /* 139 */ return this.showUserDeleteDialog;
    }

    public void setShowUserDeleteDialog(Boolean showUserDeleteDialog) {
        /* 143 */ this.showUserDeleteDialog = showUserDeleteDialog;
    }

    public Boolean getShowUserEditDialog() {
        /* 147 */ return this.showUserEditDialog;
    }

    public void setShowUserEditDialog(Boolean showUserEditDialog) {
        /* 151 */ this.showUserEditDialog = showUserEditDialog;
    }

    public Boolean getShowUserPrintDialog() {
        /* 155 */ return this.showUserPrintDialog;
    }

    public void setShowUserPrintDialog(Boolean showUserPrintDialog) {
        /* 159 */ this.showUserPrintDialog = showUserPrintDialog;
    }

    public List<Fournisseur> getFournisseurActifs() {
        /* 163 */ this.fournisseurActifs = this.fournisseurFacadeLocal.findAll();
        /* 164 */ return this.fournisseurActifs;
    }

    public void setFournisseurActifs(List<Fournisseur> fournisseurActifs) {
        /* 168 */ this.fournisseurActifs = fournisseurActifs;
    }

    public boolean isButtonActif() {
        /* 172 */ return this.buttonActif;
    }

    public void setButtonActif(boolean buttonActif) {
        /* 176 */ this.buttonActif = buttonActif;
    }

    public boolean isButtonInactif() {
        /* 180 */ return this.buttonInactif;
    }

    public void setButtonInactif(boolean buttonInactif) {
        /* 184 */ this.buttonInactif = buttonInactif;
    }
}


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-war.war!\WEB-INF\classes\controllers\fournisseur\AbstractFournisseurController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
