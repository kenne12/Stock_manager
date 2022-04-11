package controllers.rapporthebdomadaire;

import entities.Annee;
import entities.AnneeMois;
import entities.Commande;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import sessions.AnneeFacadeLocal;
import sessions.AnneeMoisFacadeLocal;
import sessions.CommandeFacadeLocal;
import sessions.PrivilegeFacadeLocal;
import utils.Routine;

public class AbstractRapportHebdomaireController {
    /*  27 */ protected Routine routine = new Routine();

    @EJB
    protected CommandeFacadeLocal commandeFacadeLocal;
    /*  31 */    protected List<Commande> commandes = new ArrayList<>();

    @EJB
    protected AnneeFacadeLocal anneeFacadeLocal;
    /*  35 */    protected Annee annee = new Annee();
    /*  36 */    protected List<Annee> annees = new ArrayList<>();

    @EJB
    protected AnneeMoisFacadeLocal anneeMoisFacadeLocal;
    /*  40 */    protected AnneeMois anneeMois = new AnneeMois();
    /*  41 */    protected List<AnneeMois> anneeMoises = new ArrayList<>();

    @EJB
    protected PrivilegeFacadeLocal privilegeFacadeLocal;

    /*  46 */    Date dateDebut = new Date();
    /*  47 */    Date dateFin = new Date();

    /*  49 */    protected String fileName = "";

    protected boolean showPrintButton = true;
    protected boolean showReportPrintDialog = false;

    public Date getDateDebut() {
        /*  55 */ return this.dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        /*  59 */ this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        /*  63 */ return this.dateFin;
    }

    public void setDateFin(Date dateFin) {
        /*  67 */ this.dateFin = dateFin;
    }

    public boolean isShowPrintButton() {
        /*  71 */ return this.showPrintButton;
    }

    public void setShowPrintButton(boolean showPrintButton) {
        /*  75 */ this.showPrintButton = showPrintButton;
    }

    public String getFileName() {
        /*  79 */ return this.fileName;
    }

    public void setFileName(String fileName) {
        /*  83 */ this.fileName = fileName;
    }

    public boolean isShowReportPrintDialog() {
        /*  87 */ return this.showReportPrintDialog;
    }

    public void setShowReportPrintDialog(boolean showReportPrintDialog) {
        /*  91 */ this.showReportPrintDialog = showReportPrintDialog;
    }

    public Annee getAnnee() {
        /*  95 */ return this.annee;
    }

    public void setAnnee(Annee annee) {
        /*  99 */ this.annee = annee;
    }

    public List<Annee> getAnnees() {
        /* 103 */ this.annees = this.anneeFacadeLocal.findByRange();
        /* 104 */ return this.annees;
    }

    public void setAnnees(List<Annee> annees) {
        /* 108 */ this.annees = annees;
    }

    public AnneeMois getAnneeMois() {
        /* 112 */ return this.anneeMois;
    }

    public void setAnneeMois(AnneeMois anneeMois) {
        /* 116 */ this.anneeMois = anneeMois;
    }

    public List<AnneeMois> getAnneeMoises() {
        /* 120 */ return this.anneeMoises;
    }

    public void setAnneeMoises(List<AnneeMois> anneeMoises) {
        /* 124 */ this.anneeMoises = anneeMoises;
    }

    public List<Commande> getCommandes() {
        /* 128 */ return this.commandes;
    }

    public void setCommandes(List<Commande> commandes) {
        /* 132 */ this.commandes = commandes;
    }

    public Routine getRoutine() {
        /* 136 */ return this.routine;
    }
}


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-war.war!\WEB-INF\classes\controllers\rapporthebdomadaire\AbstractRapportHebdomaireController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
