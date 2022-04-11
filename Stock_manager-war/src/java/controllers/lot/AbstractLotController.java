package controllers.lot;

import entities.Bailleur;
import entities.Lot;
import entities.Produit;
import entities.UserP;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import sessions.BailleurFacadeLocal;
import sessions.CommandeFacadeLocal;
import sessions.LotFacadeLocal;
import sessions.MouchardFacadeLocal;
import sessions.ProduitFacadeLocal;
import sessions.StockProduitFacadeLocal;
import sessions.UserPFacadeLocal;
import utils.Routine;
import utils.SessionMBean;

public class AbstractLotController {

    @EJB
    protected LotFacadeLocal lotFacadeLocal;
    /*  33 */    protected Lot lot = new Lot();
    /*  34 */    protected List<Lot> lots = new ArrayList<>();

    @EJB
    protected ProduitFacadeLocal produitFacadeLocal;
    /*  38 */    protected Produit produit = null;
    /*  39 */    protected List<Produit> produits = new ArrayList<>();

    @EJB
    protected BailleurFacadeLocal bailleurFacadeLocal;
    /*  43 */    protected Bailleur bailleur = new Bailleur();
    /*  44 */    protected List<Bailleur> bailleurs = new ArrayList<>();

    @EJB
    protected UserPFacadeLocal userPFacadeLocal;
    /*  48 */    protected UserP userP = new UserP();
    /*  49 */    protected List<UserP> userPs = new ArrayList<>();

    @EJB
    protected MouchardFacadeLocal mouchardFacadeLocal;
    /*  53 */    protected Routine routine = new Routine();

    @EJB
    protected CommandeFacadeLocal commandeFacadeLocal;

    @EJB
    protected StockProduitFacadeLocal stockProduitFacadeLocal;

    /*  61 */    protected String fileName = "";
    /*  62 */    protected String mode = "";

    protected boolean dateRequired = true;
    /*  65 */    protected Boolean detail = Boolean.valueOf(true);
    /*  66 */    protected Boolean modifier = Boolean.valueOf(true);
    /*  67 */    protected Boolean consulter = Boolean.valueOf(true);
    /*  68 */    protected Boolean imprimer = Boolean.valueOf(true);
    /*  69 */    protected Boolean supprimer = Boolean.valueOf(true);

    protected boolean disableProduct = false;
    /*  72 */    protected boolean showUser = SessionMBean.getParametrage().getEtatuser().booleanValue();
    /*  73 */    protected boolean showBailleur = SessionMBean.getParametrage().getEtatbailleur().booleanValue();

    public Boolean getDetail() {
        /*  76 */ return this.detail;
    }

    public Boolean getModifier() {
        /*  80 */ return this.modifier;
    }

    public Boolean getConsulter() {
        /*  84 */ return this.consulter;
    }

    public Boolean getImprimer() {
        /*  88 */ return this.imprimer;
    }

    public Boolean getSupprimer() {
        /*  92 */ return this.supprimer;
    }

    public String getFileName() {
        /*  96 */ return this.fileName;
    }

    public void setFileName(String fileName) {
        /* 100 */ this.fileName = fileName;
    }

    public Routine getRoutine() {
        /* 104 */ return this.routine;
    }

    public boolean isDisableProduct() {
        /* 108 */ return this.disableProduct;
    }

    public Lot getLot() {
        /* 112 */ return this.lot;
    }

    public void setLot(Lot lot) {
        /* 116 */ this.lot = lot;
        /* 117 */ this.modifier = this.supprimer = this.detail = Boolean.valueOf((lot == null));
    }

    public List<Lot> getLots() {
        /* 121 */ this.lots = this.lotFacadeLocal.findAllRange();
        /* 122 */ return this.lots;
    }

    public Produit getProduit() {
        /* 126 */ return this.produit;
    }

    public void setProduit(Produit produit) {
        /* 130 */ this.produit = produit;
    }

    public List<Produit> getProduits() {
        /* 134 */ return this.produits;
    }

    public boolean isDateRequired() {
        /* 138 */ return this.dateRequired;
    }

    public Bailleur getBailleur() {
        /* 142 */ return this.bailleur;
    }

    public void setBailleur(Bailleur bailleur) {
        /* 146 */ this.bailleur = bailleur;
    }

    public List<Bailleur> getBailleurs() {
        /* 150 */ this.bailleurs = this.bailleurFacadeLocal.findAll();
        /* 151 */ return this.bailleurs;
    }

    public void setBailleurs(List<Bailleur> bailleurs) {
        /* 155 */ this.bailleurs = bailleurs;
    }

    public UserP getUserP() {
        /* 159 */ return this.userP;
    }

    public void setUserP(UserP userP) {
        /* 163 */ this.userP = userP;
    }

    public List<UserP> getUserPs() {
        /* 167 */ this.userPs = this.userPFacadeLocal.findAll();
        /* 168 */ return this.userPs;
    }

    public void setUserPs(List<UserP> userPs) {
        /* 172 */ this.userPs = userPs;
    }

    public boolean isShowUser() {
        /* 176 */ return this.showUser;
    }

    public boolean isShowBailleur() {
        /* 180 */ return this.showBailleur;
    }

    public String getMode() {
        /* 184 */ return this.mode;
    }
}


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-war.war!\WEB-INF\classes\controllers\lot\AbstractLotController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
