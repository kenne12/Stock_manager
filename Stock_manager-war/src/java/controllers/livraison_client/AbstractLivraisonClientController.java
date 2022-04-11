package controllers.livraison_client;

import entities.Annee;
import entities.AnneeMois;
import entities.Client;
import entities.Commande;
import entities.CommandeClient;
import entities.Facture;
import entities.LigneCommandeClient;
import entities.LigneMvtStock;
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
import sessions.CommandeClientFacadeLocal;
import sessions.CommandeFacadeLocal;
import sessions.FactureFacadeLocal;
import sessions.LigneCommandeClientFacadeLocal;
import sessions.LigneMvtStockFacadeLocal;
import sessions.LotFacadeLocal;
import sessions.MouchardFacadeLocal;
import sessions.MvtStockFacadeLocal;
import sessions.ProduitFacadeLocal;
import utils.Routine;
import utils.SessionMBean;

public class AbstractLivraisonClientController {

    @Resource
    protected UserTransaction ut;
    @EJB
    protected CommandeClientFacadeLocal commandeClientFacadeLocal;
    /*  50 */    protected CommandeClient commandeClient = new CommandeClient();
    /*  51 */    protected List<CommandeClient> commandeClients = new ArrayList<>();

    @EJB
    protected LigneCommandeClientFacadeLocal ligneCommandeClientFacadeLocal;
    /*  55 */    protected LigneCommandeClient ligneCommandeClient = new LigneCommandeClient();
    /*  56 */    protected List<LigneCommandeClient> ligneCommandeClients = new ArrayList<>();

    @EJB
    protected ProduitFacadeLocal produitFacadeLocal;
    /*  60 */    protected Produit produit = new Produit();
    /*  61 */    protected List<Produit> produits = new ArrayList<>();

    @EJB
    protected ClientFacadeLocal clientFacadeLocal;
    /*  65 */    protected Client client = new Client();
    /*  66 */    protected List<Client> clients = new ArrayList<>();

    @EJB
    protected FactureFacadeLocal factureFacadeLocal;
    /*  70 */    protected Facture facture = new Facture();
    /*  71 */    protected List<Facture> factures = new ArrayList<>();

    @EJB
    protected CommandeFacadeLocal commandeFacadeLocal;
    /*  75 */    protected Commande commande = new Commande();
    /*  76 */    protected List<Commande> commandes = new ArrayList<>();

    @EJB
    protected AnneeFacadeLocal anneeFacadeLocal;
    /*  80 */    protected Annee annee = SessionMBean.getMois().getIdannee();
    /*  81 */    protected List<Annee> annees = new ArrayList<>();

    @EJB
    protected AnneeMoisFacadeLocal anneeMoisFacadeLocal;
    /*  85 */    protected AnneeMois anneeMois = SessionMBean.getMois();
    /*  86 */    protected List<AnneeMois> anneeMoises = new ArrayList<>();

    @EJB
    protected LotFacadeLocal lotFacadeLocal;
    /*  90 */    protected Lot lot = new Lot();
    /*  91 */    protected List<Lot> lots = new ArrayList<>();

    @EJB
    protected MvtStockFacadeLocal mvtStockFacadeLocal;
    /*  95 */    protected MvtStock mvtStock = new MvtStock();

    @EJB
    protected LigneMvtStockFacadeLocal ligneMvtStockFacadeLocal;
    /*  99 */    protected List<LigneMvtStock> ligneMvtStocks = new ArrayList<>();

    @EJB
    protected MouchardFacadeLocal mouchardFacadeLocal;

    /* 104 */    protected Routine routine = new Routine();

    /* 106 */    protected Double cout_quantite = Double.valueOf(0.0D);
    /* 107 */    protected Double total = Double.valueOf(0.0D);

    /* 109 */    protected Boolean showSelectorCommand = Boolean.valueOf(true);

    protected boolean payement_credit = false;
    /* 112 */    protected Boolean detail = Boolean.valueOf(true);
    /* 113 */    protected Boolean modifier = Boolean.valueOf(true);
    /* 114 */    protected Boolean consulter = Boolean.valueOf(true);
    /* 115 */    protected Boolean imprimer = Boolean.valueOf(true);
    /* 116 */    protected Boolean supprimer = Boolean.valueOf(true);

    /* 118 */    protected String fileName = "";
    /* 119 */    protected String printDialogTitle = "";

    /* 121 */    protected String mode = "";

    public Produit getProduit() {
        /* 124 */ return this.produit;
    }

    public void setProduit(Produit produit) {
        /* 128 */ this.produit = produit;
    }

    public List<Produit> getProduits() {
        /* 132 */ return this.produits;
    }

    public Client getClient() {
        /* 136 */ return this.client;
    }

    public void setClient(Client client) {
        /* 140 */ this.client = client;
    }

    public List<Client> getClients() {
        /* 144 */ return this.clients;
    }

    public Facture getFacture() {
        /* 148 */ return this.facture;
    }

    public void setFacture(Facture facture) {
        /* 152 */ this.facture = facture;
        /* 153 */ this.modifier = this.supprimer = this.detail = this.imprimer = Boolean.valueOf((facture == null));
    }

    public List<Facture> getFactures() {
        try {
            /* 158 */ this.factures = this.factureFacadeLocal.findByInterval(SessionMBean.getMois().getDateDebut(), SessionMBean.getMois().getDateFin());
            /* 159 */        } catch (Exception e) {
            /* 160 */ e.printStackTrace();
        }
        /* 162 */ return this.factures;
    }

    public Commande getCommande() {
        /* 166 */ return this.commande;
    }

    public void setCommande(Commande commande) {
        /* 170 */ this.commande = commande;
    }

    public List<Commande> getCommandes() {
        /* 174 */ return this.commandes;
    }

    public Boolean getDetail() {
        /* 178 */ return this.detail;
    }

    public Boolean getModifier() {
        /* 182 */ return this.modifier;
    }

    public Boolean getConsulter() {
        /* 186 */ return this.consulter;
    }

    public Boolean getImprimer() {
        /* 190 */ return this.imprimer;
    }

    public Boolean getSupprimer() {
        /* 194 */ return this.supprimer;
    }

    public Double getCout_quantite() {
        /* 198 */ return this.cout_quantite;
    }

    public void setCout_quantite(Double cout_quantite) {
        /* 202 */ this.cout_quantite = cout_quantite;
    }

    public Double getTotal() {
        /* 206 */ return this.total;
    }

    public void setTotal(Double total) {
        /* 210 */ this.total = total;
    }

    public Annee getAnnee() {
        /* 214 */ return this.annee;
    }

    public void setAnnee(Annee annee) {
        /* 218 */ this.annee = annee;
    }

    public List<Annee> getAnnees() {
        try {
            /* 223 */ this.annees = this.anneeFacadeLocal.findByEtat(true);
            /* 224 */        } catch (Exception e) {
            /* 225 */ e.printStackTrace();
        }
        /* 227 */ return this.annees;
    }

    public AnneeMois getAnneeMois() {
        /* 231 */ return this.anneeMois;
    }

    public void setAnneeMois(AnneeMois anneeMois) {
        /* 235 */ this.anneeMois = anneeMois;
    }

    public List<AnneeMois> getAnneeMoises() {
        try {
            /* 240 */ this.anneeMoises = this.anneeMoisFacadeLocal.findByEtat(Boolean.valueOf(true));
            /* 241 */        } catch (Exception e) {
            /* 242 */ e.printStackTrace();
        }
        /* 244 */ return this.anneeMoises;
    }

    public String getFileName() {
        /* 248 */ return this.fileName;
    }

    public Boolean getShowSelectorCommand() {
        /* 252 */ return this.showSelectorCommand;
    }

    public Routine getRoutine() {
        /* 256 */ return this.routine;
    }

    public Lot getLot() {
        /* 260 */ return this.lot;
    }

    public void setLot(Lot lot) {
        /* 264 */ this.lot = lot;
    }

    public List<Lot> getLots() {
        /* 268 */ return this.lots;
    }

    public CommandeClient getCommandeClient() {
        /* 272 */ return this.commandeClient;
    }

    public void setCommandeClient(CommandeClient commandeClient) {
        /* 276 */ this.commandeClient = commandeClient;
    }

    public List<CommandeClient> getCommandeClients() {
        /* 280 */ return this.commandeClients;
    }

    public LigneCommandeClient getLigneCommandeClient() {
        /* 284 */ return this.ligneCommandeClient;
    }

    public void setLigneCommandeClient(LigneCommandeClient ligneCommandeClient) {
        /* 288 */ this.ligneCommandeClient = ligneCommandeClient;
    }

    public List<LigneCommandeClient> getLigneCommandeClients() {
        /* 292 */ return this.ligneCommandeClients;
    }

    public boolean isPayement_credit() {
        /* 296 */ return this.payement_credit;
    }

    public MvtStock getMvtStock() {
        /* 300 */ return this.mvtStock;
    }

    public void setMvtStock(MvtStock mvtStock) {
        /* 304 */ this.mvtStock = mvtStock;
    }

    public List<LigneMvtStock> getLigneMvtStocks() {
        /* 308 */ return this.ligneMvtStocks;
    }

    public String getPrintDialogTitle() {
        /* 312 */ return this.printDialogTitle;
    }

    public void setPrintDialogTitle(String printDialogTitle) {
        /* 316 */ this.printDialogTitle = printDialogTitle;
    }
}


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-war.war!\WEB-INF\classes\controllers\livraison_client\AbstractLivraisonClientController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
