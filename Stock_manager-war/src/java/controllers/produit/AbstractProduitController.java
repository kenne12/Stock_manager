package controllers.produit;

import entities.Bailleur;
import entities.Famille;
import entities.Formeproduit;
import entities.Formestockage;
import entities.Fournisseur;
import entities.Lot;
import entities.Produit;
import entities.Unite;
import entities.UserP;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.transaction.UserTransaction;
import sessions.BailleurFacadeLocal;
import sessions.FamilleFacadeLocal;
import sessions.FormeproduitFacadeLocal;
import sessions.FormestockageFacadeLocal;
import sessions.FournisseurFacadeLocal;
import sessions.LotFacadeLocal;
import sessions.MouchardFacadeLocal;
import sessions.ProduitFacadeLocal;
import sessions.UniteFacadeLocal;
import sessions.UserPFacadeLocal;
import utils.Routine;
import utils.SessionMBean;

public class AbstractProduitController {

    @Resource
    protected UserTransaction ut;
    @EJB
    protected ProduitFacadeLocal produitFacadeLocal;
    protected Produit produit;
    /*  47 */    protected List<Produit> produits = new ArrayList<>();
    /*  48 */    protected List<Produit> produits1 = new ArrayList<>();

    @EJB
    protected FournisseurFacadeLocal fournisseurFacadeLocal;
    protected Fournisseur fournisseur;
    /*  53 */    protected List<Fournisseur> fournisseurs = new ArrayList<>();

    @EJB
    protected FamilleFacadeLocal familleFacadeLocal;
    /*  57 */    protected Famille famille = new Famille();
    /*  58 */    protected List<Famille> familles = new ArrayList<>();

    @EJB
    protected UniteFacadeLocal uniteFacadeLocal;
    /*  62 */    protected Unite unite = new Unite();
    /*  63 */    protected List<Unite> unites = new ArrayList<>();

    @EJB
    protected FormeproduitFacadeLocal formeproduitFacadeLocal;
    /*  67 */    protected Formeproduit formeproduit = new Formeproduit();
    /*  68 */    protected List<Formeproduit> formeproduits = new ArrayList<>();

    @EJB
    protected FormestockageFacadeLocal formestockageFacadeLocal;
    /*  72 */    protected Formestockage formestockage = new Formestockage();
    /*  73 */    protected List<Formestockage> formestockages = new ArrayList<>();

    @EJB
    protected LotFacadeLocal lotFacadeLocal;
    /*  77 */    protected Lot lot = new Lot();
    /*  78 */    protected List<Lot> lots = new ArrayList<>();

    @EJB
    protected BailleurFacadeLocal bailleurFacadeLocal;
    /*  82 */    protected Bailleur bailleur = new Bailleur();
    /*  83 */    protected List<Bailleur> bailleurs = new ArrayList<>();

    @EJB
    protected UserPFacadeLocal userPFacadeLocal;
    /*  87 */    protected UserP userP = new UserP();
    /*  88 */    protected List<UserP> userPs = new ArrayList<>();

    @EJB
    protected MouchardFacadeLocal mouchardFacadeLocal;

    /*  93 */    protected Routine routine = new Routine();

    /*  95 */    protected List<String> password = new ArrayList<>();

    /*  97 */    protected String sessionPassword = "";

    /*  99 */    protected Boolean session = Boolean.valueOf(true);

    /* 101 */    protected Boolean detail = Boolean.valueOf(true);
    /* 102 */    protected Boolean modifier = Boolean.valueOf(true);
    /* 103 */    protected Boolean consulter = Boolean.valueOf(true);
    /* 104 */    protected Boolean imprimer = Boolean.valueOf(true);
    /* 105 */    protected Boolean supprimer = Boolean.valueOf(true);

    /* 107 */    protected boolean showQuantiteDosage = SessionMBean.getParametrage().getEtatQuantiteDosage().booleanValue();
    /* 108 */    protected boolean showFormeProduit = SessionMBean.getParametrage().getEtatFormeProduit().booleanValue();
    /* 109 */    protected boolean showFormeStockage = SessionMBean.getParametrage().getEtatFormeStockage().booleanValue();
    /* 110 */    protected boolean showUser = SessionMBean.getParametrage().getEtatuser().booleanValue();
    /* 111 */    protected boolean showBailleur = SessionMBean.getParametrage().getEtatbailleur().booleanValue();

    protected boolean showLot = true;

    /* 115 */    protected String mode = "";

    /* 117 */    protected String fileName = "";
    /* 118 */    protected String fileName1 = "";
    /* 119 */    protected String fileName2 = "";

    public Boolean getDetail() {
        /* 126 */ return this.detail;
    }

    public void setDetail(Boolean detail) {
        /* 130 */ this.detail = detail;
    }

    public Boolean getModifier() {
        /* 134 */ return this.modifier;
    }

    public void setModifier(Boolean modifier) {
        /* 138 */ this.modifier = modifier;
    }

    public Boolean getConsulter() {
        /* 142 */ return this.consulter;
    }

    public void setConsulter(Boolean consulter) {
        /* 146 */ this.consulter = consulter;
    }

    public Boolean getImprimer() {
        /* 150 */ this.imprimer = Boolean.valueOf(this.produitFacadeLocal.findAll().isEmpty());
        /* 151 */ return this.imprimer;
    }

    public void setImprimer(Boolean imprimer) {
        /* 155 */ this.imprimer = imprimer;
    }

    public Boolean getSupprimer() {
        /* 159 */ return this.supprimer;
    }

    public void setSupprimer(Boolean supprimer) {
        /* 163 */ this.supprimer = supprimer;
    }

    public Fournisseur getFournisseur() {
        /* 167 */ return this.fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        /* 171 */ this.modifier = this.supprimer = this.detail = Boolean.valueOf((fournisseur == null));
        /* 172 */ this.fournisseur = fournisseur;
    }

    public List<Fournisseur> getFournisseurs() {
        /* 176 */ this.fournisseurs = this.fournisseurFacadeLocal.findAll();
        /* 177 */ return this.fournisseurs;
    }

    public void setFournisseurs(List<Fournisseur> fournisseurs) {
        /* 181 */ this.fournisseurs = fournisseurs;
    }

    public Famille getFamille() {
        /* 185 */ return this.famille;
    }

    public void setFamille(Famille famille) {
        /* 189 */ this.famille = famille;
    }

    public List<Famille> getFamilles() {
        /* 193 */ this.familles = this.familleFacadeLocal.findAllRange();
        /* 194 */ return this.familles;
    }

    public Produit getProduit() {
        /* 198 */ return this.produit;
    }

    public void setProduit(Produit produit) {
        /* 202 */ this.modifier = this.supprimer = this.detail = Boolean.valueOf((produit == null));
        /* 203 */ this.produit = produit;
    }

    public List<Produit> getProduits() {
        /* 207 */ this.produits = this.produitFacadeLocal.findAllRange();
        /* 208 */ return this.produits;
    }

    public String getFileName() {
        /* 212 */ return this.fileName;
    }

    public void setFileName(String fileName) {
        /* 216 */ this.fileName = fileName;
    }

    public String getFileName1() {
        /* 220 */ return this.fileName1;
    }

    public void setFileName1(String fileName1) {
        /* 224 */ this.fileName1 = fileName1;
    }

    public List<Produit> getProduits1() {
        /* 228 */ this.produits1 = this.produitFacadeLocal.findSousStock();
        /* 229 */ return this.produits1;
    }

    public void setProduits1(List<Produit> produits1) {
        /* 233 */ this.produits1 = produits1;
    }

    public String getFileName2() {
        /* 237 */ return this.fileName2;
    }

    public void setFileName2(String fileName2) {
        /* 241 */ this.fileName2 = fileName2;
    }

    public Boolean getSession() {
        try {
            /* 246 */ if (SessionMBean.getSession1().booleanValue()) {
                /* 247 */ this.session = Boolean.valueOf(false);
            } else {
                /* 249 */ this.session = Boolean.valueOf(true);
            }
            /* 251 */        } catch (Exception e) {
            /* 252 */ e.printStackTrace();
        }
        /* 254 */ return this.session;
    }

    public void setSession(Boolean session) {
        /* 258 */ this.session = session;
    }

    public String getSessionPassword() {
        /* 262 */ return this.sessionPassword;
    }

    public void setSessionPassword(String sessionPassword) {
        /* 266 */ this.sessionPassword = sessionPassword;
    }

    public Routine getRoutine() {
        /* 270 */ return this.routine;
    }

    public Unite getUnite() {
        /* 274 */ return this.unite;
    }

    public void setUnite(Unite unite) {
        /* 278 */ this.unite = unite;
    }

    public List<Unite> getUnites() {
        /* 282 */ this.unites = this.uniteFacadeLocal.findAll();
        /* 283 */ return this.unites;
    }

    public Formeproduit getFormeproduit() {
        /* 287 */ return this.formeproduit;
    }

    public void setFormeproduit(Formeproduit formeproduit) {
        /* 291 */ this.formeproduit = formeproduit;
    }

    public Formestockage getFormestockage() {
        /* 295 */ return this.formestockage;
    }

    public void setFormestockage(Formestockage formestockage) {
        /* 299 */ this.formestockage = formestockage;
    }

    public List<Formeproduit> getFormeproduits() {
        /* 303 */ this.formeproduits = this.formeproduitFacadeLocal.findAll();
        /* 304 */ return this.formeproduits;
    }

    public List<Formestockage> getFormestockages() {
        /* 308 */ this.formestockages = this.formestockageFacadeLocal.findAll();
        /* 309 */ return this.formestockages;
    }

    public boolean isShowQuantiteDosage() {
        /* 313 */ return this.showQuantiteDosage;
    }

    public boolean isShowFormeProduit() {
        /* 317 */ return this.showFormeProduit;
    }

    public boolean isShowFormeStockage() {
        /* 321 */ return this.showFormeStockage;
    }

    public Lot getLot() {
        /* 325 */ return this.lot;
    }

    public void setLot(Lot lot) {
        /* 329 */ this.lot = lot;
    }

    public boolean isShowLot() {
        /* 333 */ return this.showLot;
    }

    public Bailleur getBailleur() {
        /* 337 */ return this.bailleur;
    }

    public void setBailleur(Bailleur bailleur) {
        /* 341 */ this.bailleur = bailleur;
    }

    public List<Bailleur> getBailleurs() {
        /* 345 */ this.bailleurs = this.bailleurFacadeLocal.findAll();
        /* 346 */ return this.bailleurs;
    }

    public void setBailleurs(List<Bailleur> bailleurs) {
        /* 350 */ this.bailleurs = bailleurs;
    }

    public UserP getUserP() {
        /* 354 */ return this.userP;
    }

    public void setUserP(UserP userP) {
        /* 358 */ this.userP = userP;
    }

    public List<UserP> getUserPs() {
        /* 362 */ this.userPs = this.userPFacadeLocal.findAll();
        /* 363 */ return this.userPs;
    }

    public void setUserPs(List<UserP> userPs) {
        /* 367 */ this.userPs = userPs;
    }

    public boolean isShowUser() {
        /* 371 */ return this.showUser;
    }

    public boolean isShowBailleur() {
        /* 375 */ return this.showBailleur;
    }

    public String getMode() {
        /* 379 */ return this.mode;
    }
}
