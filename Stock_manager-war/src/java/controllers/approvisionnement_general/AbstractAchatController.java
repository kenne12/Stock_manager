package controllers.approvisionnement_general;

import entities.Annee;
import entities.AnneeMois;
import entities.Famille;
import entities.Fournisseur;
import entities.Produit;
import entities.Stock;
import entities.StockProduit;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.transaction.UserTransaction;
import sessions.AnneeFacadeLocal;
import sessions.AnneeMoisFacadeLocal;
import sessions.FamilleFacadeLocal;
import sessions.FournisseurFacadeLocal;
import sessions.MouchardFacadeLocal;
import sessions.PrivilegeFacadeLocal;
import sessions.ProduitFacadeLocal;
import sessions.StockFacadeLocal;
import sessions.StockProduitFacadeLocal;

public class AbstractAchatController {

    @Resource
    protected UserTransaction ut;
    @EJB
    protected FamilleFacadeLocal familleFacadeLocal;
    /*  41 */    protected Famille famille = new Famille();
    /*  42 */    protected List<Famille> familles = new ArrayList<>();

    @EJB
    protected ProduitFacadeLocal produitFacadeLocal;
    /*  46 */    protected Produit produit = new Produit();
    /*  47 */    protected List<Produit> produits = new ArrayList<>();

    @EJB
    protected StockFacadeLocal stockFacadeLocal;
    /*  51 */    protected Stock stock = new Stock();
    /*  52 */    protected List<Stock> stocks = new ArrayList<>();

    @EJB
    protected StockProduitFacadeLocal stockProduitFacadeLocal;
    /*  56 */    protected StockProduit stockProduit = new StockProduit();
    /*  57 */    protected List<StockProduit> stockProduits = new ArrayList<>();

    @EJB
    protected AnneeFacadeLocal anneeFacadeLocal;
    /*  61 */    protected Annee annee = new Annee();
    /*  62 */    protected List<Annee> annees = new ArrayList<>();

    @EJB
    protected AnneeMoisFacadeLocal anneeMoisFacadeLocal;
    /*  66 */    protected AnneeMois anneeMois = new AnneeMois();
    /*  67 */    protected List<AnneeMois> anneeMoises = new ArrayList<>();

    @EJB
    protected FournisseurFacadeLocal fournisseurFacadeLocal;
    /*  71 */    protected Fournisseur fournisseur = new Fournisseur();
    /*  72 */    protected List<Fournisseur> fournisseurs = new ArrayList<>();

    @EJB
    protected MouchardFacadeLocal mouchardFacadeLocal;

    @EJB
    protected PrivilegeFacadeLocal privilegeFacadeLocal;

    /*  80 */    protected Double cout_quantite = Double.valueOf(0.0D);
    /*  81 */    protected Double total = Double.valueOf(0.0D);

    /*  83 */    protected Boolean detail = Boolean.valueOf(true);
    /*  84 */    protected Boolean modifier = Boolean.valueOf(true);
    /*  85 */    protected Boolean consulter = Boolean.valueOf(true);
    /*  86 */    protected Boolean imprimer = Boolean.valueOf(true);
    /*  87 */    protected Boolean supprimer = Boolean.valueOf(true);

    /*  89 */    protected Boolean showEditSolde = Boolean.valueOf(true);

    /*  91 */    protected Boolean showUserCreateDialog = Boolean.valueOf(false);
    /*  92 */    protected Boolean showUserDetailDialog = Boolean.valueOf(false);
    /*  93 */    protected Boolean showUserDeleteDialog = Boolean.valueOf(false);
    /*  94 */    protected Boolean showUserEditDialog = Boolean.valueOf(false);
    /*  95 */    protected Boolean showUserPrintDialog = Boolean.valueOf(false);

    protected boolean buttonActif = false;

    protected boolean buttonInactif = true;

    protected boolean showClientSolde = false;

    protected boolean payement_espece_compte = true;

    protected boolean payement_credit = false;
    /* 106 */    protected String fileName = "";

    /* 108 */    protected String mode = "";

    public ProduitFacadeLocal getProduitFacadeLocal() {
        /* 111 */ return this.produitFacadeLocal;
    }

    public void setProduitFacadeLocal(ProduitFacadeLocal produitFacadeLocal) {
        /* 115 */ this.produitFacadeLocal = produitFacadeLocal;
    }

    public Produit getProduit() {
        /* 119 */ return this.produit;
    }

    public void setProduit(Produit produit) {
        /* 123 */ this.produit = produit;
    }

    public List<Produit> getProduits() {
        /* 127 */ return this.produits;
    }

    public void setProduits(List<Produit> produits) {
        /* 131 */ this.produits = produits;
    }

    public Fournisseur getFournisseur() {
        /* 135 */ return this.fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        /* 139 */ this.fournisseur = fournisseur;
    }

    public List<Fournisseur> getFournisseurs() {
        /* 143 */ this.fournisseurs = this.fournisseurFacadeLocal.findAll();
        /* 144 */ return this.fournisseurs;
    }

    public void setFournisseurs(List<Fournisseur> fournisseurs) {
        /* 148 */ this.fournisseurs = fournisseurs;
    }

    public Stock getStock() {
        /* 152 */ return this.stock;
    }

    public void setStock(Stock stock) {
        /* 156 */ this.stock = stock;
        /* 157 */ this.modifier = this.supprimer = this.detail = this.imprimer = Boolean.valueOf((stock == null));
    }

    public List<Stock> getStocks() {
        /* 161 */ this.stocks = this.stockFacadeLocal.findAllRange();
        /* 162 */ return this.stocks;
    }

    public void setStocks(List<Stock> stocks) {
        /* 166 */ this.stocks = stocks;
    }

    public StockProduit getStockProduit() {
        /* 170 */ return this.stockProduit;
    }

    public void setStockProduit(StockProduit stockProduit) {
        /* 174 */ this.stockProduit = stockProduit;
    }

    public List<StockProduit> getStockProduits() {
        /* 178 */ return this.stockProduits;
    }

    public void setStockProduits(List<StockProduit> stockProduits) {
        /* 182 */ this.stockProduits = stockProduits;
    }

    public MouchardFacadeLocal getMouchardFacadeLocal() {
        /* 186 */ return this.mouchardFacadeLocal;
    }

    public void setMouchardFacadeLocal(MouchardFacadeLocal mouchardFacadeLocal) {
        /* 190 */ this.mouchardFacadeLocal = mouchardFacadeLocal;
    }

    public PrivilegeFacadeLocal getPrivilegeFacadeLocal() {
        /* 194 */ return this.privilegeFacadeLocal;
    }

    public void setPrivilegeFacadeLocal(PrivilegeFacadeLocal privilegeFacadeLocal) {
        /* 198 */ this.privilegeFacadeLocal = privilegeFacadeLocal;
    }

    public Boolean getDetail() {
        /* 202 */ return this.detail;
    }

    public void setDetail(Boolean detail) {
        /* 206 */ this.detail = detail;
    }

    public Boolean getModifier() {
        /* 210 */ return this.modifier;
    }

    public void setModifier(Boolean modifier) {
        /* 214 */ this.modifier = modifier;
    }

    public Boolean getConsulter() {
        /* 218 */ return this.consulter;
    }

    public void setConsulter(Boolean consulter) {
        /* 222 */ this.consulter = consulter;
    }

    public Boolean getImprimer() {
        /* 226 */ return this.imprimer;
    }

    public void setImprimer(Boolean imprimer) {
        /* 230 */ this.imprimer = imprimer;
    }

    public Boolean getSupprimer() {
        /* 234 */ return this.supprimer;
    }

    public void setSupprimer(Boolean supprimer) {
        /* 238 */ this.supprimer = supprimer;
    }

    public Boolean getShowEditSolde() {
        /* 242 */ return this.showEditSolde;
    }

    public void setShowEditSolde(Boolean showEditSolde) {
        /* 246 */ this.showEditSolde = showEditSolde;
    }

    public Boolean getShowUserCreateDialog() {
        /* 250 */ return this.showUserCreateDialog;
    }

    public void setShowUserCreateDialog(Boolean showUserCreateDialog) {
        /* 254 */ this.showUserCreateDialog = showUserCreateDialog;
    }

    public Boolean getShowUserDetailDialog() {
        /* 258 */ return this.showUserDetailDialog;
    }

    public void setShowUserDetailDialog(Boolean showUserDetailDialog) {
        /* 262 */ this.showUserDetailDialog = showUserDetailDialog;
    }

    public Boolean getShowUserDeleteDialog() {
        /* 266 */ return this.showUserDeleteDialog;
    }

    public void setShowUserDeleteDialog(Boolean showUserDeleteDialog) {
        /* 270 */ this.showUserDeleteDialog = showUserDeleteDialog;
    }

    public Boolean getShowUserEditDialog() {
        /* 274 */ return this.showUserEditDialog;
    }

    public void setShowUserEditDialog(Boolean showUserEditDialog) {
        /* 278 */ this.showUserEditDialog = showUserEditDialog;
    }

    public Boolean getShowUserPrintDialog() {
        /* 282 */ return this.showUserPrintDialog;
    }

    public void setShowUserPrintDialog(Boolean showUserPrintDialog) {
        /* 286 */ this.showUserPrintDialog = showUserPrintDialog;
    }

    public boolean isButtonActif() {
        /* 290 */ return this.buttonActif;
    }

    public void setButtonActif(boolean buttonActif) {
        /* 294 */ this.buttonActif = buttonActif;
    }

    public boolean isButtonInactif() {
        /* 298 */ return this.buttonInactif;
    }

    public void setButtonInactif(boolean buttonInactif) {
        /* 302 */ this.buttonInactif = buttonInactif;
    }

    public Famille getFamille() {
        /* 306 */ return this.famille;
    }

    public void setFamille(Famille famille) {
        /* 310 */ this.famille = famille;
    }

    public List<Famille> getFamilles() {
        /* 314 */ this.familles = this.familleFacadeLocal.findAllRange();
        /* 315 */ return this.familles;
    }

    public void setFamilles(List<Famille> familles) {
        /* 319 */ this.familles = familles;
    }

    public Double getCout_quantite() {
        /* 323 */ return this.cout_quantite;
    }

    public void setCout_quantite(Double cout_quantite) {
        /* 327 */ this.cout_quantite = cout_quantite;
    }

    public Double getTotal() {
        /* 331 */ return this.total;
    }

    public void setTotal(Double total) {
        /* 335 */ this.total = total;
    }

    public Annee getAnnee() {
        /* 339 */ return this.annee;
    }

    public void setAnnee(Annee annee) {
        /* 343 */ this.annee = annee;
    }

    public List<Annee> getAnnees() {
        try {
            /* 348 */ this.annees = this.anneeFacadeLocal.findByEtat(true);
            /* 349 */        } catch (Exception e) {
            /* 350 */ e.printStackTrace();
        }
        /* 352 */ return this.annees;
    }

    public void setAnnees(List<Annee> annees) {
        /* 356 */ this.annees = annees;
    }

    public AnneeMois getAnneeMois() {
        /* 360 */ return this.anneeMois;
    }

    public void setAnneeMois(AnneeMois anneeMois) {
        /* 364 */ this.anneeMois = anneeMois;
    }

    public List<AnneeMois> getAnneeMoises() {
        /* 368 */ return this.anneeMoises;
    }

    public void setAnneeMoises(List<AnneeMois> anneeMoises) {
        /* 372 */ this.anneeMoises = anneeMoises;
    }

    public boolean isShowClientSolde() {
        /* 376 */ return this.showClientSolde;
    }

    public void setShowClientSolde(boolean showClientSolde) {
        /* 380 */ this.showClientSolde = showClientSolde;
    }

    public boolean isPayement_espece_compte() {
        /* 384 */ return this.payement_espece_compte;
    }

    public void setPayement_espece_compte(boolean payement_espece_compte) {
        /* 388 */ this.payement_espece_compte = payement_espece_compte;
    }

    public boolean isPayement_credit() {
        /* 392 */ return this.payement_credit;
    }

    public void setPayement_credit(boolean payement_credit) {
        /* 396 */ this.payement_credit = payement_credit;
    }

    public String getFileName() {
        /* 400 */ return this.fileName;
    }

    public void setFileName(String fileName) {
        /* 404 */ this.fileName = fileName;
    }
}


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-war.war!\WEB-INF\classes\controllers\approvisionnement_general\AbstractAchatController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
