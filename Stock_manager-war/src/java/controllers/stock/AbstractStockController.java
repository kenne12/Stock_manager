package controllers.stock;

import entities.Annee;
import entities.AnneeMois;
import entities.Famille;
import entities.Fournisseur;
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
import sessions.FamilleFacadeLocal;
import sessions.FournisseurFacadeLocal;
import sessions.LigneMvtStockFacadeLocal;
import sessions.LotFacadeLocal;
import sessions.MouchardFacadeLocal;
import sessions.MvtStockFacadeLocal;
import sessions.ProduitFacadeLocal;
import sessions.StockFacadeLocal;
import sessions.StockProduitFacadeLocal;
import utils.Routine;
import utils.SessionMBean;

public class AbstractStockController {

    @Resource
    protected UserTransaction ut;
    @EJB
    protected FamilleFacadeLocal familleFacadeLocal;
    protected Famille famille = new Famille();
    protected List<Famille> familles = new ArrayList<>();

    @EJB
    protected ProduitFacadeLocal produitFacadeLocal;
    protected Produit produit = new Produit();
    protected List<Produit> produits = new ArrayList<>();

    @EJB
    protected StockFacadeLocal stockFacadeLocal;
    protected Stock stock = new Stock();
    protected List<Stock> stocks = new ArrayList<>();

    @EJB
    protected StockProduitFacadeLocal stockProduitFacadeLocal;
    protected StockProduit stockProduit = new StockProduit();
    protected List<StockProduit> stockProduits = new ArrayList<>();

    @EJB
    protected LotFacadeLocal lotFacadeLocal;
    protected Lot lot = new Lot();
    protected List<Lot> lots = new ArrayList<>();

    @EJB
    protected AnneeFacadeLocal anneeFacadeLocal;
    protected Annee annee = new Annee();
    protected List<Annee> annees = new ArrayList<>();

    @EJB
    protected AnneeMoisFacadeLocal anneeMoisFacadeLocal;
    protected AnneeMois anneeMois = SessionMBean.getMois();
    protected List<AnneeMois> anneeMoises = new ArrayList<>();

    @EJB
    protected FournisseurFacadeLocal fournisseurFacadeLocal;
    protected Fournisseur fournisseur = new Fournisseur();
    protected List<Fournisseur> fournisseurs = new ArrayList<>();

    @EJB
    protected MvtStockFacadeLocal mvtStockFacadeLocal;
    protected MvtStock mvtStock = new MvtStock();

    @EJB
    protected LigneMvtStockFacadeLocal ligneMvtStockFacadeLocal;

    @EJB
    protected MouchardFacadeLocal mouchardFacadeLocal;

    protected Routine routine = new Routine();

    protected Double cout_quantite = 0.0;
    protected Double total = 0.0;

    protected int quantiteEntrant;
    protected double montantJour;

    protected Boolean detail = true;
    protected Boolean modifier = true;
    protected Boolean consulter = true;
    protected Boolean imprimer = true;
    protected Boolean supprimer = true;

    protected String fileName = "";

    protected String mode = "";

    public Produit getProduit() {
        return this.produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public List<Produit> getProduits() {
        return this.produits;
    }

    public Fournisseur getFournisseur() {
        return this.fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }

    public List<Fournisseur> getFournisseurs() {
        this.fournisseurs = this.fournisseurFacadeLocal.findAllRange();
        return this.fournisseurs;
    }

    public Stock getStock() {
        return this.stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
        this.modifier = this.supprimer = this.detail = this.imprimer = (stock == null);
    }

    public List<Stock> getStocks() {
        return this.stocks;
    }

    public StockProduit getStockProduit() {
        return this.stockProduit;
    }

    public void setStockProduit(StockProduit stockProduit) {
        this.stockProduit = stockProduit;
    }

    public List<StockProduit> getStockProduits() {
        return this.stockProduits;
    }

    public Boolean getDetail() {
        return this.detail;
    }

    public Boolean getModifier() {
        return this.modifier;
    }

    public Boolean getConsulter() {
        return this.consulter;
    }

    public Boolean getImprimer() {
        return this.imprimer;
    }

    public Boolean getSupprimer() {
        return this.supprimer;
    }

    public Famille getFamille() {
        return this.famille;
    }

    public void setFamille(Famille famille) {
        this.famille = famille;
    }

    public List<Famille> getFamilles() {
        this.familles = this.familleFacadeLocal.findAllRange();
        return this.familles;
    }

    public Double getCout_quantite() {
        return this.cout_quantite;
    }

    public void setCout_quantite(Double cout_quantite) {
        this.cout_quantite = cout_quantite;
    }

    public Double getTotal() {
        return this.total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Annee getAnnee() {
        return this.annee;
    }

    public void setAnnee(Annee annee) {
        this.annee = annee;
    }

    public List<Annee> getAnnees() {
        try {
            this.annees = this.anneeFacadeLocal.findByEtat(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.annees;
    }

    public AnneeMois getAnneeMois() {
        return this.anneeMois;
    }

    public void setAnneeMois(AnneeMois anneeMois) {
        this.anneeMois = anneeMois;
    }

    public List<AnneeMois> getAnneeMoises() {
        try {
            this.anneeMoises = this.anneeMoisFacadeLocal.findByEtat(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.anneeMoises;
    }

    public String getFileName() {
        return this.fileName;
    }

    public Lot getLot() {
        return this.lot;
    }

    public void setLot(Lot lot) {
        this.lot = lot;
    }

    public List<Lot> getLots() {
        return this.lots;
    }

    public Routine getRoutine() {
        return this.routine;
    }

    public int getQuantiteEntrant() {
        return quantiteEntrant;
    }

    public double getMontantJour() {
        return montantJour;
    }

}
