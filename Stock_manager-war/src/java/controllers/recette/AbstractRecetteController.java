package controllers.recette;

import entities.Annee;
import entities.AnneeMois;
import entities.Bailleur;
import entities.Formestockage;
import entities.Journee;
import entities.Lot;
import entities.UserP;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.transaction.UserTransaction;
import sessions.AnneeFacadeLocal;
import sessions.AnneeMoisFacadeLocal;
import sessions.BailleurFacadeLocal;
import sessions.CommandeFacadeLocal;
import sessions.FactureFacadeLocal;
import sessions.FormestockageFacadeLocal;
import sessions.JourneeFacadeLocal;
import sessions.LotFacadeLocal;
import sessions.MouchardFacadeLocal;
import sessions.StockFacadeLocal;
import sessions.UserPFacadeLocal;
import utils.Routine;
import utils.SessionMBean;

public class AbstractRecetteController {

    @Resource
    protected UserTransaction ut;
    @EJB
    protected FormestockageFacadeLocal formestockageFacadeLocal;
    protected Formestockage formestockage = new Formestockage();
    protected List<Formestockage> formestockages = new ArrayList<>();

    @EJB
    protected LotFacadeLocal lotFacadeLocal;
    protected Lot lot = new Lot();
    protected List<Lot> lots = new ArrayList<>();

    @EJB
    protected BailleurFacadeLocal bailleurFacadeLocal;
    protected Bailleur bailleur = new Bailleur();
    protected List<Bailleur> bailleurs = new ArrayList<>();

    @EJB
    protected UserPFacadeLocal userPFacadeLocal;
    protected UserP userP = new UserP();
    protected List<UserP> userPs = new ArrayList<>();

    @EJB
    protected AnneeFacadeLocal anneeFacadeLocal;
    protected Annee annee = new Annee();
    protected List<Annee> annees = new ArrayList<>();

    @EJB
    protected AnneeMoisFacadeLocal anneeMoisFacadeLocal;
    protected AnneeMois anneeMois = new AnneeMois();
    protected List<AnneeMois> listMois = new ArrayList<>();

    @EJB
    protected CommandeFacadeLocal commandeFacadeLocal;

    @EJB
    protected JourneeFacadeLocal journeeFacadeLocal;
    protected List<Journee> journees = new ArrayList<>();

    @EJB
    protected FactureFacadeLocal factureFacadeLocal;

    @EJB
    protected StockFacadeLocal stockFacadeLocal;

    @EJB
    protected MouchardFacadeLocal mouchardFacadeLocal;

    protected Routine routine = new Routine();

    protected List<String> password = new ArrayList<>();

    protected String sessionPassword = "";

    protected Boolean session = true;

    protected Boolean detail = true;
    protected Boolean modifier = true;
    protected Boolean consulter = true;
    protected Boolean imprimer = true;
    protected Boolean supprimer = true;

    protected boolean showQuantiteDosage = SessionMBean.getParametrage().getEtatQuantiteDosage();
    protected boolean showFormeProduit = SessionMBean.getParametrage().getEtatFormeProduit();
    protected boolean showFormeStockage = SessionMBean.getParametrage().getEtatFormeStockage();
    protected boolean showUser = SessionMBean.getParametrage().getEtatuser();
    protected boolean showBailleur = SessionMBean.getParametrage().getEtatbailleur();

    protected boolean showLot = true;

    protected String mode = "";

    protected String fileName = "";
    protected String fileName1 = "";
    protected String fileName2 = "";

    public Boolean getDetail() {
        return this.detail;
    }

    public void setDetail(Boolean detail) {
        this.detail = detail;
    }

    public Boolean getModifier() {
        return this.modifier;
    }

    public void setModifier(Boolean modifier) {
        this.modifier = modifier;
    }

    public Boolean getConsulter() {
        return this.consulter;
    }

    public void setConsulter(Boolean consulter) {
        this.consulter = consulter;
    }

    public Boolean getImprimer() {
        return this.imprimer;
    }

    public void setImprimer(Boolean imprimer) {
        this.imprimer = imprimer;
    }

    public Boolean getSupprimer() {
        return this.supprimer;
    }

    public void setSupprimer(Boolean supprimer) {
        this.supprimer = supprimer;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName1() {
        return this.fileName1;
    }

    public void setFileName1(String fileName1) {
        this.fileName1 = fileName1;
    }

    public String getFileName2() {
        return this.fileName2;
    }

    public void setFileName2(String fileName2) {
        this.fileName2 = fileName2;
    }

    public Boolean getSession() {
        try {
            if (SessionMBean.getSession1()) {
                this.session = false;
            } else {
                this.session = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.session;
    }

    public void setSession(Boolean session) {
        this.session = session;
    }

    public String getSessionPassword() {
        return this.sessionPassword;
    }

    public void setSessionPassword(String sessionPassword) {
        this.sessionPassword = sessionPassword;
    }

    public Routine getRoutine() {
        return this.routine;
    }

    public Formestockage getFormestockage() {
        return this.formestockage;
    }

    public void setFormestockage(Formestockage formestockage) {
        this.formestockage = formestockage;
    }

    public List<Formestockage> getFormestockages() {
        this.formestockages = this.formestockageFacadeLocal.findAll();
        return this.formestockages;
    }

    public boolean isShowQuantiteDosage() {
        return this.showQuantiteDosage;
    }

    public boolean isShowFormeProduit() {
        return this.showFormeProduit;
    }

    public boolean isShowFormeStockage() {
        return this.showFormeStockage;
    }

    public Lot getLot() {
        return this.lot;
    }

    public void setLot(Lot lot) {
        this.lot = lot;
    }

    public boolean isShowLot() {
        return this.showLot;
    }

    public Bailleur getBailleur() {
        return this.bailleur;
    }

    public void setBailleur(Bailleur bailleur) {
        this.bailleur = bailleur;
    }

    public List<Bailleur> getBailleurs() {
        this.bailleurs = this.bailleurFacadeLocal.findAll();
        return this.bailleurs;
    }

    public UserP getUserP() {
        return this.userP;
    }

    public void setUserP(UserP userP) {
        this.userP = userP;
    }

    public List<UserP> getUserPs() {
        this.userPs = this.userPFacadeLocal.findAll();
        return this.userPs;
    }

    public void setUserPs(List<UserP> userPs) {
        this.userPs = userPs;
    }

    public boolean isShowUser() {
        return this.showUser;
    }

    public boolean isShowBailleur() {
        return this.showBailleur;
    }

    public String getMode() {
        return this.mode;
    }

    public List<Annee> getAnnees() {
        this.annees = this.anneeFacadeLocal.findByRange();
        return this.annees;
    }

    public void setAnnees(List<Annee> annees) {
        this.annees = annees;
    }

    public AnneeMois getAnneeMois() {
        return this.anneeMois;
    }

    public void setAnneeMois(AnneeMois anneeMois) {
        this.anneeMois = anneeMois;
    }

    public List<AnneeMois> getListMois() {
        return this.listMois;
    }

    public List<Journee> getJournees() {
        return this.journees;
    }

    public Annee getAnnee() {
        return this.annee;
    }

    public void setAnnee(Annee annee) {
        this.annee = annee;
    }
}
