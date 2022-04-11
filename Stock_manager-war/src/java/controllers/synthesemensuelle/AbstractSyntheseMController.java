package controllers.synthesemensuelle;

import entities.Annee;
import entities.AnneeMois;
import entities.Commande;
import entities.Depense;
import entities.Salaire;
import entities.Stock;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import sessions.AnneeFacadeLocal;
import sessions.AnneeMoisFacadeLocal;
import sessions.CommandeFacadeLocal;
import sessions.DepenseFacadeLocal;
import sessions.PrivilegeFacadeLocal;
import sessions.SalaireFacadeLocal;
import sessions.StockFacadeLocal;

public class AbstractSyntheseMController {

    @EJB
    protected CommandeFacadeLocal commandeFacadeLocal;
    /*  34 */    protected List<Commande> commandes = new ArrayList<>();

    @EJB
    protected StockFacadeLocal stockFacadeLocal;
    /*  38 */    protected List<Stock> stockes = new ArrayList<>();

    @EJB
    protected SalaireFacadeLocal salaireFacadeLocal;
    /*  42 */    protected List<Salaire> salaires = new ArrayList<>();

    @EJB
    protected DepenseFacadeLocal depenseFacadeLocal;
    /*  46 */    protected List<Depense> depenses = new ArrayList<>();

    @EJB
    protected AnneeFacadeLocal anneeFacadeLocal;
    /*  50 */    protected Annee annee = new Annee();
    /*  51 */    protected List<Annee> annees = new ArrayList<>();

    @EJB
    protected AnneeMoisFacadeLocal anneeMoisFacadeLocal;
    /*  55 */    protected AnneeMois anneeMois = new AnneeMois();
    /*  56 */    protected List<AnneeMois> anneeMoises = new ArrayList<>();

    @EJB
    protected PrivilegeFacadeLocal privilegeFacadeLocal;

    /*  61 */    Date dateDebut = new Date();
    /*  62 */    Date dateFin = new Date();

    /*  64 */    protected String fileName = "";

    protected boolean showPrintButton = true;
    protected boolean showReportPrintDialog = false;

    public Date getDateDebut() {
        /*  70 */ return this.dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        /*  74 */ this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        /*  78 */ return this.dateFin;
    }

    public void setDateFin(Date dateFin) {
        /*  82 */ this.dateFin = dateFin;
    }

    public boolean isShowPrintButton() {
        /*  86 */ return this.showPrintButton;
    }

    public void setShowPrintButton(boolean showPrintButton) {
        /*  90 */ this.showPrintButton = showPrintButton;
    }

    public String getFileName() {
        /*  94 */ return this.fileName;
    }

    public void setFileName(String fileName) {
        /*  98 */ this.fileName = fileName;
    }

    public boolean isShowReportPrintDialog() {
        /* 102 */ return this.showReportPrintDialog;
    }

    public void setShowReportPrintDialog(boolean showReportPrintDialog) {
        /* 106 */ this.showReportPrintDialog = showReportPrintDialog;
    }

    public Annee getAnnee() {
        /* 110 */ return this.annee;
    }

    public void setAnnee(Annee annee) {
        /* 114 */ this.annee = annee;
    }

    public List<Annee> getAnnees() {
        /* 118 */ this.annees = this.anneeFacadeLocal.findAll();
        /* 119 */ return this.annees;
    }

    public void setAnnees(List<Annee> annees) {
        /* 123 */ this.annees = annees;
    }

    public AnneeMois getAnneeMois() {
        /* 127 */ return this.anneeMois;
    }

    public void setAnneeMois(AnneeMois anneeMois) {
        /* 131 */ this.anneeMois = anneeMois;
    }

    public List<AnneeMois> getAnneeMoises() {
        /* 135 */ return this.anneeMoises;
    }

    public void setAnneeMoises(List<AnneeMois> anneeMoises) {
        /* 139 */ this.anneeMoises = anneeMoises;
    }

    public List<Commande> getCommandes() {
        /* 143 */ return this.commandes;
    }

    public void setCommandes(List<Commande> commandes) {
        /* 147 */ this.commandes = commandes;
    }
}
