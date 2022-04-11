package controllers.vente;

import entities.Annee;
import entities.AnneeMois;
import entities.Client;
import entities.Commande;
import entities.Facture;
import entities.Famille;
import entities.Lot;
import entities.MvtStock;
import entities.Produit;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.transaction.UserTransaction;
import sessions.AnneeFacadeLocal;
import sessions.AnneeMoisFacadeLocal;
import sessions.ClientFacadeLocal;
import sessions.CommandeFacadeLocal;
import sessions.FactureFacadeLocal;
import sessions.FamilleFacadeLocal;
import sessions.LigneMvtStockFacadeLocal;
import sessions.LotFacadeLocal;
import sessions.MouchardFacadeLocal;
import sessions.MvtStockFacadeLocal;
import sessions.ProduitFacadeLocal;
import utils.Routine;
import utils.SessionMBean;

public class AbstractVenteController {

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
    protected ClientFacadeLocal clientFacadeLocal;
    protected Client client = new Client();
    protected List<Client> clients = new ArrayList<>();

    @EJB
    protected FactureFacadeLocal factureFacadeLocal;
    protected Facture facture = new Facture();
    protected List<Facture> factures = new ArrayList<>();

    @EJB
    protected CommandeFacadeLocal commandeFacadeLocal;
    protected Commande commande = new Commande();
    protected List<Commande> commandes = new ArrayList<>();

    @EJB
    protected AnneeFacadeLocal anneeFacadeLocal;
    protected Annee annee = SessionMBean.getMois().getIdannee();
    protected List<Annee> annees = new ArrayList<>();

    @EJB
    protected AnneeMoisFacadeLocal anneeMoisFacadeLocal;
    protected AnneeMois anneeMois = SessionMBean.getMois();
    protected List<AnneeMois> anneeMoises = new ArrayList<>();

    @EJB
    protected LotFacadeLocal lotFacadeLocal;
    protected Lot lot = new Lot();
    protected List<Lot> lots = new ArrayList<>();

    @EJB
    protected MvtStockFacadeLocal mvtStockFacadeLocal;
    protected MvtStock mvtStock = new MvtStock();

    @EJB
    protected LigneMvtStockFacadeLocal ligneMvtStockFacadeLocal;

    @EJB
    protected MouchardFacadeLocal mouchardFacadeLocal;

    protected Routine routine = new Routine();

    protected Double cout_quantite = (0.0D);
    protected Double total = (0.0D);

    protected boolean nouveauClient = true;

    protected Boolean saisieClient = true;

    protected Boolean selectClient = true;

    protected Boolean showSelector = true;

    protected Boolean detail = true;
    protected Boolean modifier = true;
    protected Boolean consulter = true;
    protected Boolean imprimer = true;
    protected Boolean supprimer = true;

    protected Boolean showEditSolde = true;

    protected boolean buttonActif = false;

    protected boolean buttonInactif = true;

    protected boolean showClientSolde = false;

    protected boolean payement_espece_compte = true;

    protected boolean payement_credit = false;
    protected String fileName = "";

    protected String mode = "";

    protected int conteur = 0;
    protected int quantiteVendue = 0;
    protected double montantJour;

    public Produit getProduit() {
        return this.produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public List<Produit> getProduits() {
        return this.produits;
    }

    public Client getClient() {
        return this.client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Client> getClients() {
        this.clients = this.clientFacadeLocal.findAllRange();
        return this.clients;
    }

    public Facture getFacture() {
        return this.facture;
    }

    public void setFacture(Facture facture) {
        this.modifier = this.supprimer = this.detail = this.imprimer = (facture == null);
        this.facture = facture;
    }

    public List<Facture> getFactures() {
        return this.factures;
    }

    public Commande getCommande() {
        return this.commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public List<Commande> getCommandes() {
        return this.commandes;
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

    public Boolean getShowEditSolde() {
        return this.showEditSolde;
    }

    public boolean isButtonActif() {
        return this.buttonActif;
    }

    public boolean isButtonInactif() {
        return this.buttonInactif;
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

    public boolean isShowClientSolde() {
        return this.showClientSolde;
    }

    public boolean isPayement_espece_compte() {
        return this.payement_espece_compte;
    }

    public boolean isPayement_credit() {
        return this.payement_credit;
    }

    public void setPayement_credit(boolean payement_credit) {
        this.payement_credit = payement_credit;
    }

    public String getFileName() {
        return this.fileName;
    }

    public boolean isNouveauClient() {
        return this.nouveauClient;
    }

    public void setNouveauClient(boolean nouveauClient) {
        this.nouveauClient = nouveauClient;
    }

    public Boolean getSaisieClient() {
        return this.saisieClient;
    }

    public Boolean getSelectClient() {
        return this.selectClient;
    }

    public Boolean getShowSelector() {
        return this.showSelector;
    }

    public Routine getRoutine() {
        return this.routine;
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

    public int getQuantiteVendue() {
        return quantiteVendue;
    }

    public double getMontantJour() {
        return montantJour;
    }

}
