package utils;

import entities.Annee;
import entities.AnneeMois;
import entities.Facture;
import entities.Journee;
import entities.Mouchard;
import entities.Parametrage;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import sessions.AnneeFacadeLocal;
import sessions.AnneeMoisFacadeLocal;
import sessions.FactureFacadeLocal;
import sessions.JourneeFacadeLocal;
import sessions.MenuFacadeLocal;
import sessions.MouchardFacadeLocal;
import sessions.ParametrageFacadeLocal;
import sessions.PrivilegeFacadeLocal;
import utils.Routine;

public class AbstractLoginBean {

    @EJB
    protected ParametrageFacadeLocal parametrageFacadeLocal;
    /*  35 */    protected Parametrage param = new Parametrage();

    @EJB
    protected AnneeFacadeLocal anneeFacadeLocal;
    /*  39 */    protected Annee annee = new Annee();
    /*  40 */    protected List<Annee> annees = new ArrayList<>();

    @EJB
    protected AnneeMoisFacadeLocal anneeMoisFacadeLocal;
    /*  44 */    protected AnneeMois anneeMois = new AnneeMois();
    /*  45 */    protected List<AnneeMois> anneeMoises = new ArrayList<>();

    @EJB
    protected JourneeFacadeLocal journeeFacadeLocal;
    /*  49 */    protected Journee journee = new Journee();

    @EJB
    protected FactureFacadeLocal factureFacadeLocal;
    /*  53 */    protected List<Facture> factures = new ArrayList<>();

    @EJB
    protected MenuFacadeLocal menuFacadeLocal;

    @EJB
    protected PrivilegeFacadeLocal privilegeFacadeLocal;

    @EJB
    protected MouchardFacadeLocal mouchardFacadeLocal;

    protected Mouchard traceur;
    /*  65 */    protected Routine routine = new Routine();

    /*  67 */    protected Date date = new Date();

    /*  69 */    protected String confirmPassword = "";

    protected boolean gestionPersonnel;

    protected boolean gestionNote;

    protected boolean gestionPrivilege;
    protected boolean gestionDiscipline;
    protected boolean gestionInscription;
    protected boolean gestionEtat;
    protected boolean parametrage;
    protected boolean bibliotheque;
    /*  81 */    protected String gestionPersonnelVisible = "hidden";
    /*  82 */    protected String gestionNoteVisible = "hidden";
    /*  83 */    protected String gestionPrivilegeVisible = "hidden";
    /*  84 */    protected String gestionDisciplineVisible = "hidden";
    /*  85 */    protected String gestionInscriptionVisible = "hidden";
    /*  86 */    protected String gestionEtatVisible = "hidden";
    /*  87 */    protected String parametrageVisible = "hidden";
    /*  88 */    protected String bibliothequeVisible = "hidden";

    protected boolean showHibernatePanel = false;
    /*  91 */    protected String hibernatePassword = "";

    protected boolean showSessionPanel = true;

    protected boolean connected = false;

    /*  98 */    protected String connectionVisible = "visible";

    public boolean isGestionPersonnel() {
        /* 101 */ return this.gestionPersonnel;
    }

    public void setGestionPersonnel(boolean gestionPersonnel) {
        /* 105 */ this.gestionPersonnel = gestionPersonnel;
    }

    public boolean isGestionNote() {
        /* 109 */ return this.gestionNote;
    }

    public void setGestionNote(boolean gestionNote) {
        /* 113 */ this.gestionNote = gestionNote;
    }

    public boolean isGestionPrivilege() {
        /* 117 */ return this.gestionPrivilege;
    }

    public void setGestionPrivilege(boolean gestionPrivilege) {
        /* 121 */ this.gestionPrivilege = gestionPrivilege;
    }

    public boolean isGestionDiscipline() {
        /* 125 */ return this.gestionDiscipline;
    }

    public void setGestionDiscipline(boolean gestionDiscipline) {
        /* 129 */ this.gestionDiscipline = gestionDiscipline;
    }

    public boolean isGestionInscription() {
        /* 133 */ return this.gestionInscription;
    }

    public void setGestionInscription(boolean gestionInscription) {
        /* 137 */ this.gestionInscription = gestionInscription;
    }

    public boolean isGestionEtat() {
        /* 141 */ return this.gestionEtat;
    }

    public void setGestionEtat(boolean gestionEtat) {
        /* 145 */ this.gestionEtat = gestionEtat;
    }

    public boolean isParametrage() {
        /* 149 */ return this.parametrage;
    }

    public void setParametrage(boolean parametrage) {
        /* 153 */ this.parametrage = parametrage;
    }

    public boolean isBibliotheque() {
        /* 157 */ return this.bibliotheque;
    }

    public void setBibliotheque(boolean bibliotheque) {
        /* 161 */ this.bibliotheque = bibliotheque;
    }

    public String getGestionPersonnelVisible() {
        /* 166 */ return this.gestionPersonnelVisible;
    }

    public void setGestionPersonnelVisible(String gestionPersonnelVisible) {
        /* 170 */ this.gestionPersonnelVisible = gestionPersonnelVisible;
    }

    public String getGestionNoteVisible() {
        /* 174 */ return this.gestionNoteVisible;
    }

    public void setGestionNoteVisible(String gestionNoteVisible) {
        /* 178 */ this.gestionNoteVisible = gestionNoteVisible;
    }

    public String getGestionPrivilegeVisible() {
        /* 182 */ return this.gestionPrivilegeVisible;
    }

    public void setGestionPrivilegeVisible(String gestionPrivilegeVisible) {
        /* 186 */ this.gestionPrivilegeVisible = gestionPrivilegeVisible;
    }

    public String getGestionDisciplineVisible() {
        /* 190 */ return this.gestionDisciplineVisible;
    }

    public void setGestionDisciplineVisible(String gestionDisciplineVisible) {
        /* 194 */ this.gestionDisciplineVisible = gestionDisciplineVisible;
    }

    public String getGestionInscriptionVisible() {
        /* 198 */ return this.gestionInscriptionVisible;
    }

    public void setGestionInscriptionVisible(String gestionInscriptionVisible) {
        /* 202 */ this.gestionInscriptionVisible = gestionInscriptionVisible;
    }

    public String getGestionEtatVisible() {
        /* 206 */ return this.gestionEtatVisible;
    }

    public void setGestionEtatVisible(String gestionEtatVisible) {
        /* 210 */ this.gestionEtatVisible = gestionEtatVisible;
    }

    public String getParametrageVisible() {
        /* 214 */ return this.parametrageVisible;
    }

    public void setParametrageVisible(String parametrageVisible) {
        /* 218 */ this.parametrageVisible = parametrageVisible;
    }

    public String getBibliothequeVisible() {
        /* 222 */ return this.bibliothequeVisible;
    }

    public void setBibliothequeVisible(String bibliothequeVisible) {
        /* 226 */ this.bibliothequeVisible = bibliothequeVisible;
    }

    public boolean isShowHibernatePanel() {
        /* 230 */ return this.showHibernatePanel;
    }

    public void setShowHibernatePanel(boolean showHibernatePanel) {
        /* 234 */ this.showHibernatePanel = showHibernatePanel;
    }

    public String getHibernatePassword() {
        /* 238 */ return this.hibernatePassword;
    }

    public void setHibernatePassword(String hibernatePassword) {
        /* 242 */ this.hibernatePassword = hibernatePassword;
    }

    public Mouchard getTraceur() {
        /* 246 */ return this.traceur;
    }

    public void setTraceur(Mouchard traceur) {
        /* 250 */ this.traceur = traceur;
    }

    public Parametrage getParam() {
        /* 254 */ return this.param;
    }

    public boolean isShowSessionPanel() {
        /* 258 */ return this.showSessionPanel;
    }

    public Annee getAnnee() {
        /* 262 */ return this.annee;
    }

    public void setAnnee(Annee annee) {
        /* 266 */ this.annee = annee;
    }

    public List<Annee> getAnnees() {
        /* 270 */ return this.annees;
    }

    public void setAnnees(List<Annee> annees) {
        /* 274 */ this.annees = annees;
    }

    public AnneeMois getAnneeMois() {
        /* 278 */ return this.anneeMois;
    }

    public void setAnneeMois(AnneeMois anneeMois) {
        /* 282 */ this.anneeMois = anneeMois;
    }

    public List<AnneeMois> getAnneeMoises() {
        /* 286 */ return this.anneeMoises;
    }

    public void setAnneeMoises(List<AnneeMois> anneeMoises) {
        /* 290 */ this.anneeMoises = anneeMoises;
    }

    public Date getDate() {
        /* 294 */ return this.date;
    }

    public void setDate(Date date) {
        /* 298 */ this.date = date;
    }

    public Journee getJournee() {
        /* 302 */ return this.journee;
    }

    public void setJournee(Journee journee) {
        /* 306 */ this.journee = journee;
    }

    public List<Facture> getFactures() {
        /* 310 */ return this.factures;
    }

    public void setFactures(List<Facture> factures) {
        /* 314 */ this.factures = factures;
    }

    public Routine getRoutine() {
        /* 318 */ return this.routine;
    }

    public String getConfirmPassword() {
        /* 322 */ return this.confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        /* 326 */ this.confirmPassword = confirmPassword;
    }
}
