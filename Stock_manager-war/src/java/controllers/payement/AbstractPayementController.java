package controllers.payement;

import entities.AnneeMois;
import entities.Personnel;
import entities.Salaire;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import sessions.AnneeMoisFacadeLocal;
import sessions.CaisseFacadeLocal;
import sessions.MouchardFacadeLocal;
import sessions.PersonnelFacadeLocal;
import sessions.PrivilegeFacadeLocal;
import sessions.RetraitFacadeLocal;
import sessions.SalaireFacadeLocal;
import sessions.VersementFacadeLocal;

public class AbstractPayementController {

    @EJB
    protected SalaireFacadeLocal salaireFacadeLocal;
    /*  31 */    protected Salaire salaire = new Salaire();
    /*  32 */    protected List<Salaire> salaires = new ArrayList<>();

    @EJB
    protected PersonnelFacadeLocal personnelFacadeLocal;
    /*  36 */    protected Personnel personnel = new Personnel();
    /*  37 */    protected List<Personnel> personnels = new ArrayList<>();

    @EJB
    protected MouchardFacadeLocal mouchardFacadeLocal;

    @EJB
    protected AnneeMoisFacadeLocal moisFacadeLocal;
    /*  44 */    protected AnneeMois mois = new AnneeMois();
    /*  45 */    protected List<AnneeMois> moiss = new ArrayList<>();

    @EJB
    protected VersementFacadeLocal versementFacadeLocal;

    @EJB
    protected CaisseFacadeLocal caisseFacadeLocal;

    @EJB
    protected RetraitFacadeLocal retraitFacadeLocal;

    @EJB
    protected PrivilegeFacadeLocal privilegeFacadeLocal;

    /*  59 */    protected String fileName = "";

    /*  61 */    protected Boolean payed = Boolean.valueOf(false);

    /*  63 */    protected String message = "";

    /*  65 */    protected Boolean detail = Boolean.valueOf(true);
    /*  66 */    protected Boolean modifier = Boolean.valueOf(true);
    /*  67 */    protected Boolean consulter = Boolean.valueOf(true);
    /*  68 */    protected Boolean imprimer = Boolean.valueOf(true);
    /*  69 */    protected Boolean supprimer = Boolean.valueOf(true);

    /*  71 */    protected Boolean showEditSolde = Boolean.valueOf(true);

    protected boolean showSalaireCreateDialog = false;
    protected boolean showSalaireDeleteDialog = false;
    /*  75 */    protected Boolean showSalairePrintDialog = Boolean.valueOf(false);

    protected boolean showMontantCarnet = true;

    protected boolean showMontantCarnetCompnent = true;
    /*  80 */    protected int carnet = 500;

    /*  82 */    protected String mode = "";

    public Salaire getSalaire() {
        /*  85 */ return this.salaire;
    }

    public void setSalaire(Salaire salaire) {
        /*  89 */ this.modifier = this.supprimer = this.detail = Boolean.valueOf((salaire == null));
        /*  90 */ this.salaire = salaire;
    }

    public List<Salaire> getSalaires() {
        /*  94 */ this.salaires = this.salaireFacadeLocal.findAllRange();
        /*  95 */ return this.salaires;
    }

    public void setSalaires(List<Salaire> salaires) {
        /*  99 */ this.salaires = salaires;
    }

    public Boolean getDetail() {
        /* 103 */ return this.detail;
    }

    public void setDetail(Boolean detail) {
        /* 107 */ this.detail = detail;
    }

    public Boolean getModifier() {
        /* 111 */ return this.modifier;
    }

    public void setModifier(Boolean modifier) {
        /* 115 */ this.modifier = modifier;
    }

    public Boolean getConsulter() {
        /* 119 */ return this.consulter;
    }

    public void setConsulter(Boolean consulter) {
        /* 123 */ this.consulter = consulter;
    }

    public Boolean getImprimer() {
        /* 127 */ this.imprimer = Boolean.valueOf(this.salaireFacadeLocal.findAll().isEmpty());
        /* 128 */ return this.imprimer;
    }

    public void setImprimer(Boolean imprimer) {
        /* 132 */ this.imprimer = imprimer;
    }

    public List<AnneeMois> getAnneeMoiss() {
        try {
            /* 137 */ this.moiss = this.moisFacadeLocal.findByEtat(Boolean.valueOf(true));
            /* 138 */        } catch (Exception e) {
            /* 139 */ e.printStackTrace();
        }
        /* 141 */ return this.moiss;
    }

    public void setAnneeMoiss(List<AnneeMois> moiss) {
        /* 145 */ this.moiss = moiss;
    }

    public Boolean getSupprimer() {
        /* 149 */ return this.supprimer;
    }

    public void setSupprimer(Boolean supprimer) {
        /* 153 */ this.supprimer = supprimer;
    }

    public Boolean getShowEditSolde() {
        /* 157 */ return this.showEditSolde;
    }

    public void setShowEditSolde(Boolean showEditSolde) {
        /* 161 */ this.showEditSolde = showEditSolde;
    }

    public boolean isShowSalaireCreateDialog() {
        /* 165 */ return this.showSalaireCreateDialog;
    }

    public void setShowSalaireCreateDialog(boolean showSalaireCreateDialog) {
        /* 169 */ this.showSalaireCreateDialog = showSalaireCreateDialog;
    }

    public boolean isShowSalaireDeleteDialog() {
        /* 173 */ return this.showSalaireDeleteDialog;
    }

    public void setShowSalaireDeleteDialog(boolean showSalaireDeleteDialog) {
        /* 177 */ this.showSalaireDeleteDialog = showSalaireDeleteDialog;
    }

    public Boolean getShowSalairePrintDialog() {
        /* 181 */ return this.showSalairePrintDialog;
    }

    public void setShowSalairePrintDialog(Boolean showSalairePrintDialog) {
        /* 185 */ this.showSalairePrintDialog = showSalairePrintDialog;
    }

    public String getFileName() {
        /* 189 */ return this.fileName;
    }

    public void setFileName(String fileName) {
        /* 193 */ this.fileName = fileName;
    }

    public int getCarnet() {
        /* 197 */ return this.carnet;
    }

    public void setCarnet(int carnet) {
        /* 201 */ this.carnet = carnet;
    }

    public boolean isShowMontantCarnet() {
        /* 205 */ return this.showMontantCarnet;
    }

    public void setShowMontantCarnet(boolean showMontantCarnet) {
        /* 209 */ this.showMontantCarnet = showMontantCarnet;
    }

    public AnneeMois getAnneeMois() {
        /* 213 */ return this.mois;
    }

    public void setAnneeMois(AnneeMois mois) {
        /* 217 */ this.mois = mois;
    }

    public boolean isShowMontantCarnetCompnent() {
        /* 221 */ return this.showMontantCarnetCompnent;
    }

    public void setShowMontantCarnetCompnent(boolean showMontantCarnetCompnent) {
        /* 225 */ this.showMontantCarnetCompnent = showMontantCarnetCompnent;
    }

    public Personnel getPersonnel() {
        /* 229 */ return this.personnel;
    }

    public void setPersonnel(Personnel personnel) {
        /* 233 */ this.personnel = personnel;
    }

    public List<Personnel> getPersonnels() {
        /* 237 */ this.personnels = this.personnelFacadeLocal.findAll();
        /* 238 */ return this.personnels;
    }

    public void setPersonnels(List<Personnel> personnels) {
        /* 242 */ this.personnels = personnels;
    }

    public List<AnneeMois> getMoiss() {
        /* 246 */ return this.moiss;
    }

    public void setMoiss(List<AnneeMois> moiss) {
        /* 250 */ this.moiss = moiss;
    }

    public Boolean getPayed() {
        /* 254 */ return this.payed;
    }

    public void setPayed(Boolean payed) {
        /* 258 */ this.payed = payed;
    }

    public String getMessage() {
        /* 262 */ return this.message;
    }

    public void setMessage(String message) {
        /* 266 */ this.message = message;
    }
}


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-war.war!\WEB-INF\classes\controllers\payement\AbstractPayementController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
