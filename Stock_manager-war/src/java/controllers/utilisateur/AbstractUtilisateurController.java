package controllers.utilisateur;

import entities.Personnel;
import entities.Utilisateur;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import sessions.MouchardFacadeLocal;
import sessions.PersonnelFacadeLocal;
import sessions.PrivilegeFacadeLocal;
import sessions.UtilisateurFacadeLocal;
import utils.Routine;

public class AbstractUtilisateurController {

    @EJB
    protected UtilisateurFacadeLocal utilisateurFacadeLocal;
    protected Utilisateur utilisateur;
    /*  28 */    protected List<Utilisateur> utilisateurs = new ArrayList<>();

    /*  30 */    protected List<Utilisateur> utilisateurActifs = new ArrayList<>();
    /*  31 */    protected List<Utilisateur> utilisateurInactifs = new ArrayList<>();

    @EJB
    protected PersonnelFacadeLocal personnelFacadeLocal;
    /*  35 */    protected Personnel personnel = new Personnel();
    /*  36 */    protected List<Personnel> personnels = new ArrayList<>();

    @EJB
    protected MouchardFacadeLocal mouchardFacadeLocal;

    @EJB
    protected PrivilegeFacadeLocal privilegeFacadeLocal;

    /*  44 */    protected List<String> templates = new ArrayList<>();

    /*  46 */    protected Boolean detail = Boolean.valueOf(true);
    /*  47 */    protected Boolean modifier = Boolean.valueOf(true);
    /*  48 */    protected Boolean consulter = Boolean.valueOf(true);
    /*  49 */    protected Boolean imprimer = Boolean.valueOf(true);
    /*  50 */    protected Boolean supprimer = Boolean.valueOf(true);

    /*  52 */    protected Boolean showEditSolde = Boolean.valueOf(true);

    /*  54 */    protected Boolean showUserCreateDialog = Boolean.valueOf(false);
    /*  55 */    protected Boolean showUserDetailDialog = Boolean.valueOf(false);
    /*  56 */    protected Boolean showUserDeleteDialog = Boolean.valueOf(false);
    /*  57 */    protected Boolean showUserEditDialog = Boolean.valueOf(false);
    /*  58 */    protected Boolean showUserPrintDialog = Boolean.valueOf(false);

    protected boolean buttonActif = false;

    protected boolean buttonInactif = true;
    /*  63 */    protected Routine routine = new Routine();

    /*  65 */    protected String mode = "";

    public Boolean getDetail() {
        /*  68 */ return this.detail;
    }

    public void setDetail(Boolean detail) {
        /*  72 */ this.detail = detail;
    }

    public Boolean getModifier() {
        /*  76 */ return this.modifier;
    }

    public void setModifier(Boolean modifier) {
        /*  80 */ this.modifier = modifier;
    }

    public Boolean getConsulter() {
        /*  84 */ return this.consulter;
    }

    public void setConsulter(Boolean consulter) {
        /*  88 */ this.consulter = consulter;
    }

    public Boolean getImprimer() {
        /*  92 */ this.imprimer = Boolean.valueOf(this.utilisateurFacadeLocal.findAll().isEmpty());
        /*  93 */ return this.imprimer;
    }

    public void setImprimer(Boolean imprimer) {
        /*  97 */ this.imprimer = imprimer;
    }

    public Boolean getSupprimer() {
        /* 101 */ return this.supprimer;
    }

    public void setSupprimer(Boolean supprimer) {
        /* 105 */ this.supprimer = supprimer;
    }

    public Boolean getShowEditSolde() {
        /* 109 */ return this.showEditSolde;
    }

    public void setShowEditSolde(Boolean showEditSolde) {
        /* 113 */ this.showEditSolde = showEditSolde;
    }

    public Utilisateur getUtilisateur() {
        /* 117 */ return this.utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        /* 121 */ this.modifier = this.supprimer = this.detail = Boolean.valueOf((utilisateur == null));
        /* 122 */ this.utilisateur = utilisateur;
    }

    public List<Utilisateur> getUtilisateurs() {
        /* 126 */ this.utilisateurs = this.utilisateurFacadeLocal.findAll();
        /* 127 */ return this.utilisateurs;
    }

    public void setUtilisateurs(List<Utilisateur> utilisateurs) {
        /* 131 */ this.utilisateurs = utilisateurs;
    }

    public Boolean getShowUserCreateDialog() {
        /* 135 */ return this.showUserCreateDialog;
    }

    public void setShowUserCreateDialog(Boolean showUserCreateDialog) {
        /* 139 */ this.showUserCreateDialog = showUserCreateDialog;
    }

    public Boolean getShowUserDetailDialog() {
        /* 143 */ return this.showUserDetailDialog;
    }

    public void setShowUserDetailDialog(Boolean showUserDetailDialog) {
        /* 147 */ this.showUserDetailDialog = showUserDetailDialog;
    }

    public Boolean getShowUserDeleteDialog() {
        /* 151 */ return this.showUserDeleteDialog;
    }

    public void setShowUserDeleteDialog(Boolean showUserDeleteDialog) {
        /* 155 */ this.showUserDeleteDialog = showUserDeleteDialog;
    }

    public Boolean getShowUserEditDialog() {
        /* 159 */ return this.showUserEditDialog;
    }

    public void setShowUserEditDialog(Boolean showUserEditDialog) {
        /* 163 */ this.showUserEditDialog = showUserEditDialog;
    }

    public Boolean getShowUserPrintDialog() {
        /* 167 */ return this.showUserPrintDialog;
    }

    public void setShowUserPrintDialog(Boolean showUserPrintDialog) {
        /* 171 */ this.showUserPrintDialog = showUserPrintDialog;
    }

    public List<Utilisateur> getUtilisateurActifs() {
        /* 175 */ this.utilisateurActifs = this.utilisateurFacadeLocal.findByActif(Boolean.valueOf(true));
        /* 176 */ return this.utilisateurActifs;
    }

    public void setUtilisateurActifs(List<Utilisateur> utilisateurActifs) {
        /* 180 */ this.utilisateurActifs = utilisateurActifs;
    }

    public List<Utilisateur> getUtilisateurInactifs() {
        /* 184 */ this.utilisateurInactifs = this.utilisateurFacadeLocal.findByActif(Boolean.valueOf(false));
        /* 185 */ return this.utilisateurInactifs;
    }

    public void setUtilisateurInactifs(List<Utilisateur> utilisateurInactifs) {
        /* 189 */ this.utilisateurInactifs = utilisateurInactifs;
    }

    public boolean isButtonActif() {
        /* 193 */ return this.buttonActif;
    }

    public void setButtonActif(boolean buttonActif) {
        /* 197 */ this.buttonActif = buttonActif;
    }

    public boolean isButtonInactif() {
        /* 201 */ return this.buttonInactif;
    }

    public void setButtonInactif(boolean buttonInactif) {
        /* 205 */ this.buttonInactif = buttonInactif;
    }

    public Personnel getPersonnel() {
        /* 209 */ return this.personnel;
    }

    public void setPersonnel(Personnel personnel) {
        /* 213 */ this.personnel = personnel;
    }

    public List<Personnel> getPersonnels() {
        /* 217 */ return this.personnels;
    }

    public void setPersonnels(List<Personnel> personnels) {
        /* 221 */ this.personnels = personnels;
    }

    public List<String> getTemplates() {
        /* 225 */ return this.templates;
    }

    public Routine getRoutine() {
        /* 229 */ return this.routine;
    }
}
