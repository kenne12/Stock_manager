package controllers.syntheannuelle;

import entities.Annee;
import entities.AnneeMois;
import entities.Commande;
import entities.Depense;
import entities.Salaire;
import entities.Stock;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import sessions.AnneeFacadeLocal;
import sessions.AnneeMoisFacadeLocal;
import sessions.CommandeFacadeLocal;
import sessions.DepenseFacadeLocal;
import sessions.PrivilegeFacadeLocal;
import sessions.SalaireFacadeLocal;
import sessions.StockFacadeLocal;

public class AbstractSyntheseAnController {

    @EJB
    protected CommandeFacadeLocal commandeFacadeLocal;
    /*  36 */    protected List<Commande> commandes = new ArrayList<>();

    @EJB
    protected StockFacadeLocal stockFacadeLocal;
    /*  40 */    protected List<Stock> stockes = new ArrayList<>();

    @EJB
    protected SalaireFacadeLocal salaireFacadeLocal;
    /*  44 */    protected List<Salaire> salaires = new ArrayList<>();

    @EJB
    protected DepenseFacadeLocal depenseFacadeLocal;
    /*  48 */    protected List<Depense> depenses = new ArrayList<>();

    @EJB
    protected AnneeFacadeLocal anneeFacadeLocal;
    /*  52 */    protected Annee annee = new Annee();
    /*  53 */    protected List<Annee> annees = new ArrayList<>();

    @EJB
    protected AnneeMoisFacadeLocal anneeMoisFacadeLocal;
    /*  57 */    protected AnneeMois anneeMois = new AnneeMois();
    /*  58 */    protected List<AnneeMois> anneeMoises = new ArrayList<>();

    /*  60 */    protected Map mapVente = new HashMap<>();
    /*  61 */    protected Map mapAchat = new HashMap<>();
    /*  62 */    protected Map mapSalaire = new HashMap<>();
    /*  63 */    protected Map mapDepense = new HashMap<>();

    @EJB
    protected PrivilegeFacadeLocal privilegeFacadeLocal;

    /*  68 */    Date dateDebut = new Date();
    /*  69 */    Date dateFin = new Date();

    /*  71 */    protected String fileName = "";

    protected boolean showPrintButton = true;
    protected boolean showReportPrintDialog = false;

    public Date getDateDebut() {
        /*  77 */ return this.dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        /*  81 */ this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        /*  85 */ return this.dateFin;
    }

    public void setDateFin(Date dateFin) {
        /*  89 */ this.dateFin = dateFin;
    }

    public boolean isShowPrintButton() {
        /*  93 */ return this.showPrintButton;
    }

    public void setShowPrintButton(boolean showPrintButton) {
        /*  97 */ this.showPrintButton = showPrintButton;
    }

    public String getFileName() {
        /* 101 */ return this.fileName;
    }

    public void setFileName(String fileName) {
        /* 105 */ this.fileName = fileName;
    }

    public boolean isShowReportPrintDialog() {
        /* 109 */ return this.showReportPrintDialog;
    }

    public void setShowReportPrintDialog(boolean showReportPrintDialog) {
        /* 113 */ this.showReportPrintDialog = showReportPrintDialog;
    }

    public Annee getAnnee() {
        /* 117 */ return this.annee;
    }

    public void setAnnee(Annee annee) {
        /* 121 */ this.annee = annee;
    }

    public List<Annee> getAnnees() {
        /* 125 */ this.annees = this.anneeFacadeLocal.findAll();
        /* 126 */ return this.annees;
    }

    public void setAnnees(List<Annee> annees) {
        /* 130 */ this.annees = annees;
    }

    public AnneeMois getAnneeMois() {
        /* 134 */ return this.anneeMois;
    }

    public void setAnneeMois(AnneeMois anneeMois) {
        /* 138 */ this.anneeMois = anneeMois;
    }

    public List<AnneeMois> getAnneeMoises() {
        /* 142 */ return this.anneeMoises;
    }

    public void setAnneeMoises(List<AnneeMois> anneeMoises) {
        /* 146 */ this.anneeMoises = anneeMoises;
    }

    public List<Commande> getCommandes() {
        /* 150 */ return this.commandes;
    }

    public void setCommandes(List<Commande> commandes) {
        /* 154 */ this.commandes = commandes;
    }
}
