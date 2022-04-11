package controllers.livraison_fournisseur;

import entities.Annee;
import entities.AnneeMois;
import entities.CommandeFournisseur;
import entities.Fournisseur;
import entities.LigneCmdeFournisseur;
import entities.LigneMvtStock;
import entities.Lot;
import entities.MvtStock;
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
import sessions.CommandeFournisseurFacadeLocal;
import sessions.FournisseurFacadeLocal;
import sessions.LigneCmdeFournisseurFacadeLocal;
import sessions.LigneMvtStockFacadeLocal;
import sessions.LotFacadeLocal;
import sessions.MouchardFacadeLocal;
import sessions.MvtStockFacadeLocal;
import sessions.ProduitFacadeLocal;
import sessions.StockFacadeLocal;
import sessions.StockProduitFacadeLocal;
import utils.Routine;
import utils.SessionMBean;

public class AbstractLivraisonFournisseurController {

    @Resource
    protected UserTransaction ut;
    @EJB
    protected StockFacadeLocal stockFacadeLocal;
    /*  50 */    protected Stock stock = new Stock();
    /*  51 */    protected List<Stock> stocks = new ArrayList<>();

    @EJB
    protected StockProduitFacadeLocal stockProduitFacadeLocal;
    /*  55 */    protected StockProduit stockProduit = new StockProduit();
    /*  56 */    protected List<StockProduit> stockProduits = new ArrayList<>();

    @EJB
    protected CommandeFournisseurFacadeLocal commandeFournisseurFacadeLocal;
    /*  60 */    protected CommandeFournisseur commandeFournisseur = new CommandeFournisseur();
    /*  61 */    protected List<CommandeFournisseur> commandeFournisseurs = new ArrayList<>();

    @EJB
    protected LigneCmdeFournisseurFacadeLocal ligneCmdeFournisseurFacadeLocal;
    /*  65 */    protected LigneCmdeFournisseur ligneCmdeFournisseur = new LigneCmdeFournisseur();
    /*  66 */    protected List<LigneCmdeFournisseur> ligneCmdeFournisseurs = new ArrayList<>();

    @EJB
    protected ProduitFacadeLocal produitFacadeLocal;
    /*  70 */    protected Produit produit = new Produit();
    /*  71 */    protected List<Produit> produits = new ArrayList<>();

    @EJB
    protected FournisseurFacadeLocal fournisseurFacadeLocal;
    /*  75 */    protected Fournisseur fournisseur = new Fournisseur();

    @EJB
    protected AnneeFacadeLocal anneeFacadeLocal;
    /*  79 */    protected Annee annee = SessionMBean.getMois().getIdannee();
    /*  80 */    protected List<Annee> annees = new ArrayList<>();

    @EJB
    protected AnneeMoisFacadeLocal anneeMoisFacadeLocal;
    /*  84 */    protected AnneeMois anneeMois = SessionMBean.getMois();
    /*  85 */    protected List<AnneeMois> anneeMoises = new ArrayList<>();

    @EJB
    protected LotFacadeLocal lotFacadeLocal;
    /*  89 */    protected Lot lot = new Lot();
    /*  90 */    protected List<Lot> lots = new ArrayList<>();

    @EJB
    protected MvtStockFacadeLocal mvtStockFacadeLocal;
    /*  94 */    protected MvtStock mvtStock = new MvtStock();

    @EJB
    protected LigneMvtStockFacadeLocal ligneMvtStockFacadeLocal;
    /*  98 */    protected List<LigneMvtStock> ligneMvtStocks = new ArrayList<>();

    @EJB
    protected MouchardFacadeLocal mouchardFacadeLocal;

    /* 103 */    protected Routine routine = new Routine();

    /* 105 */    protected Double cout_quantite = Double.valueOf(0.0D);
    /* 106 */    protected Double total = Double.valueOf(0.0D);

    /* 108 */    protected Boolean showSelectorCommand = Boolean.valueOf(true);

    /* 110 */    protected Boolean detail = Boolean.valueOf(true);
    /* 111 */    protected Boolean modifier = Boolean.valueOf(true);
    /* 112 */    protected Boolean consulter = Boolean.valueOf(true);
    /* 113 */    protected Boolean imprimer = Boolean.valueOf(true);
    /* 114 */    protected Boolean supprimer = Boolean.valueOf(true);

    /* 116 */    protected String fileName = "";

    /* 118 */    protected String mode = "";

    public Produit getProduit() {
        /* 121 */ return this.produit;
    }

    public void setProduit(Produit produit) {
        /* 125 */ this.produit = produit;
    }

    public List<Produit> getProduits() {
        /* 129 */ return this.produits;
    }

    public Boolean getDetail() {
        /* 133 */ return this.detail;
    }

    public Boolean getModifier() {
        /* 137 */ return this.modifier;
    }

    public Boolean getConsulter() {
        /* 141 */ return this.consulter;
    }

    public Boolean getImprimer() {
        /* 145 */ return this.imprimer;
    }

    public Boolean getSupprimer() {
        /* 149 */ return this.supprimer;
    }

    public Double getCout_quantite() {
        /* 153 */ return this.cout_quantite;
    }

    public void setCout_quantite(Double cout_quantite) {
        /* 157 */ this.cout_quantite = cout_quantite;
    }

    public Double getTotal() {
        /* 161 */ return this.total;
    }

    public void setTotal(Double total) {
        /* 165 */ this.total = total;
    }

    public Annee getAnnee() {
        /* 169 */ return this.annee;
    }

    public void setAnnee(Annee annee) {
        /* 173 */ this.annee = annee;
    }

    public List<Annee> getAnnees() {
        try {
            /* 178 */ this.annees = this.anneeFacadeLocal.findByEtat(true);
            /* 179 */        } catch (Exception e) {
            /* 180 */ e.printStackTrace();
        }
        /* 182 */ return this.annees;
    }

    public AnneeMois getAnneeMois() {
        /* 186 */ return this.anneeMois;
    }

    public void setAnneeMois(AnneeMois anneeMois) {
        /* 190 */ this.anneeMois = anneeMois;
    }

    public List<AnneeMois> getAnneeMoises() {
        try {
            /* 195 */ this.anneeMoises = this.anneeMoisFacadeLocal.findByEtat(Boolean.valueOf(true));
            /* 196 */        } catch (Exception e) {
            /* 197 */ e.printStackTrace();
        }
        /* 199 */ return this.anneeMoises;
    }

    public String getFileName() {
        /* 203 */ return this.fileName;
    }

    public Boolean getShowSelectorCommand() {
        /* 207 */ return this.showSelectorCommand;
    }

    public Routine getRoutine() {
        /* 211 */ return this.routine;
    }

    public Lot getLot() {
        /* 215 */ return this.lot;
    }

    public void setLot(Lot lot) {
        /* 219 */ this.lot = lot;
    }

    public List<Lot> getLots() {
        /* 223 */ return this.lots;
    }

    public MvtStock getMvtStock() {
        /* 227 */ return this.mvtStock;
    }

    public void setMvtStock(MvtStock mvtStock) {
        /* 231 */ this.mvtStock = mvtStock;
    }

    public List<LigneMvtStock> getLigneMvtStocks() {
        /* 235 */ return this.ligneMvtStocks;
    }

    public CommandeFournisseur getCommandeFournisseur() {
        /* 239 */ return this.commandeFournisseur;
    }

    public void setCommandeFournisseur(CommandeFournisseur commandeFournisseur) {
        /* 243 */ this.commandeFournisseur = commandeFournisseur;
    }

    public List<CommandeFournisseur> getCommandeFournisseurs() {
        /* 247 */ return this.commandeFournisseurs;
    }

    public void setCommandeFournisseurs(List<CommandeFournisseur> commandeFournisseurs) {
        /* 251 */ this.commandeFournisseurs = commandeFournisseurs;
    }

    public LigneCmdeFournisseur getLigneCmdeFournisseur() {
        /* 255 */ return this.ligneCmdeFournisseur;
    }

    public void setLigneCmdeFournisseur(LigneCmdeFournisseur ligneCmdeFournisseur) {
        /* 259 */ this.ligneCmdeFournisseur = ligneCmdeFournisseur;
    }

    public List<LigneCmdeFournisseur> getLigneCmdeFournisseurs() {
        /* 263 */ return this.ligneCmdeFournisseurs;
    }

    public void setLigneCmdeFournisseurs(List<LigneCmdeFournisseur> ligneCmdeFournisseurs) {
        /* 267 */ this.ligneCmdeFournisseurs = ligneCmdeFournisseurs;
    }

    public Stock getStock() {
        /* 271 */ return this.stock;
    }

    public void setStock(Stock stock) {
        /* 275 */ this.stock = stock;
        /* 276 */ this.modifier = this.supprimer = this.detail = this.imprimer = Boolean.valueOf((stock == null));
    }

    public List<Stock> getStocks() {
        try {
            /* 281 */ this.stocks = this.stockFacadeLocal.findAllRange();
            /* 282 */        } catch (Exception e) {
            /* 283 */ e.printStackTrace();
        }
        /* 285 */ return this.stocks;
    }

    public StockProduit getStockProduit() {
        /* 289 */ return this.stockProduit;
    }

    public List<StockProduit> getStockProduits() {
        /* 293 */ return this.stockProduits;
    }

    public Fournisseur getFournisseur() {
        /* 297 */ return this.fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        /* 301 */ this.fournisseur = fournisseur;
    }
}


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-war.war!\WEB-INF\classes\controllers\livraison_fournisseur\AbstractLivraisonFournisseurController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
