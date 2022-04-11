package controllers.depense;

import entities.AnneeMois;
import entities.Depense;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import sessions.AnneeMoisFacadeLocal;
import sessions.CaisseFacadeLocal;
import sessions.DepenseFacadeLocal;
import sessions.MouchardFacadeLocal;
import sessions.PrivilegeFacadeLocal;
import sessions.RetraitFacadeLocal;
import sessions.VersementFacadeLocal;

public class AbstractDepenseController {

    @EJB
    protected DepenseFacadeLocal depenseFacadeLocal;
    /*  29 */    protected Depense depense = new Depense();
    /*  30 */    protected List<Depense> depenses = new ArrayList<>();

    @EJB
    protected MouchardFacadeLocal mouchardFacadeLocal;

    @EJB
    protected AnneeMoisFacadeLocal moisFacadeLocal;
    /*  37 */    protected AnneeMois mois = new AnneeMois();
    /*  38 */    protected List<AnneeMois> moiss = new ArrayList<>();

    @EJB
    protected VersementFacadeLocal versementFacadeLocal;

    @EJB
    protected CaisseFacadeLocal caisseFacadeLocal;

    @EJB
    protected RetraitFacadeLocal retraitFacadeLocal;

    @EJB
    protected PrivilegeFacadeLocal privilegeFacadeLocal;

    /*  52 */    protected String fileName = "";

    /*  54 */    protected Boolean detail = Boolean.valueOf(true);
    /*  55 */    protected Boolean modifier = Boolean.valueOf(true);
    /*  56 */    protected Boolean consulter = Boolean.valueOf(true);
    /*  57 */    protected Boolean imprimer = Boolean.valueOf(true);
    /*  58 */    protected Boolean supprimer = Boolean.valueOf(true);

    /*  60 */    protected Boolean showEditSolde = Boolean.valueOf(true);

    protected boolean showDepenseCreateDialog = false;
    protected boolean showDepenseDeleteDialog = false;
    /*  64 */    protected Boolean showDepensePrintDialog = Boolean.valueOf(false);

    protected boolean showMontantCarnet = true;

    protected boolean showMontantCarnetCompnent = true;
    /*  69 */    protected int carnet = 500;

    /*  71 */    protected String mode = "";

    public Depense getDepense() {
        /*  74 */ return this.depense;
    }

    public void setDepense(Depense depense) {
        /*  78 */ this.modifier = this.supprimer = this.detail = Boolean.valueOf((depense == null));
        /*  79 */ this.depense = depense;
    }

    public List<Depense> getDepenses() {
        /*  83 */ this.depenses = this.depenseFacadeLocal.findAllRange();
        /*  84 */ return this.depenses;
    }

    public void setDepenses(List<Depense> depenses) {
        /*  88 */ this.depenses = depenses;
    }

    public Boolean getDetail() {
        /*  92 */ return this.detail;
    }

    public void setDetail(Boolean detail) {
        /*  96 */ this.detail = detail;
    }

    public Boolean getModifier() {
        /* 100 */ return this.modifier;
    }

    public void setModifier(Boolean modifier) {
        /* 104 */ this.modifier = modifier;
    }

    public Boolean getConsulter() {
        /* 108 */ return this.consulter;
    }

    public void setConsulter(Boolean consulter) {
        /* 112 */ this.consulter = consulter;
    }

    public Boolean getImprimer() {
        /* 116 */ this.imprimer = Boolean.valueOf(this.depenseFacadeLocal.findAll().isEmpty());
        /* 117 */ return this.imprimer;
    }

    public void setImprimer(Boolean imprimer) {
        /* 121 */ this.imprimer = imprimer;
    }

    public List<AnneeMois> getAnneeMoiss() {
        try {
            /* 126 */ this.moiss = this.moisFacadeLocal.findByEtat(Boolean.valueOf(true));
            /* 127 */        } catch (Exception e) {
            /* 128 */ e.printStackTrace();
        }
        /* 130 */ return this.moiss;
    }

    public void setAnneeMoiss(List<AnneeMois> moiss) {
        /* 134 */ this.moiss = moiss;
    }

    public Boolean getSupprimer() {
        /* 138 */ return this.supprimer;
    }

    public void setSupprimer(Boolean supprimer) {
        /* 142 */ this.supprimer = supprimer;
    }

    public Boolean getShowEditSolde() {
        /* 146 */ return this.showEditSolde;
    }

    public void setShowEditSolde(Boolean showEditSolde) {
        /* 150 */ this.showEditSolde = showEditSolde;
    }

    public boolean isShowDepenseCreateDialog() {
        /* 154 */ return this.showDepenseCreateDialog;
    }

    public void setShowDepenseCreateDialog(boolean showDepenseCreateDialog) {
        /* 158 */ this.showDepenseCreateDialog = showDepenseCreateDialog;
    }

    public boolean isShowDepenseDeleteDialog() {
        /* 162 */ return this.showDepenseDeleteDialog;
    }

    public void setShowDepenseDeleteDialog(boolean showDepenseDeleteDialog) {
        /* 166 */ this.showDepenseDeleteDialog = showDepenseDeleteDialog;
    }

    public Boolean getShowDepensePrintDialog() {
        /* 170 */ return this.showDepensePrintDialog;
    }

    public void setShowDepensePrintDialog(Boolean showDepensePrintDialog) {
        /* 174 */ this.showDepensePrintDialog = showDepensePrintDialog;
    }

    public String getFileName() {
        /* 178 */ return this.fileName;
    }

    public void setFileName(String fileName) {
        /* 182 */ this.fileName = fileName;
    }

    public int getCarnet() {
        /* 186 */ return this.carnet;
    }

    public void setCarnet(int carnet) {
        /* 190 */ this.carnet = carnet;
    }

    public boolean isShowMontantCarnet() {
        /* 194 */ return this.showMontantCarnet;
    }

    public void setShowMontantCarnet(boolean showMontantCarnet) {
        /* 198 */ this.showMontantCarnet = showMontantCarnet;
    }

    public AnneeMois getAnneeMois() {
        /* 202 */ return this.mois;
    }

    public void setAnneeMois(AnneeMois mois) {
        /* 206 */ this.mois = mois;
    }

    public boolean isShowMontantCarnetCompnent() {
        /* 210 */ return this.showMontantCarnetCompnent;
    }

    public void setShowMontantCarnetCompnent(boolean showMontantCarnetCompnent) {
        /* 214 */ this.showMontantCarnetCompnent = showMontantCarnetCompnent;
    }
}


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-war.war!\WEB-INF\classes\controllers\depense\AbstractDepenseController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
