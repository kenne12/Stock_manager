package controllers.commande_client;

import entities.Annee;
import entities.AnneeMois;
import entities.Client;
import entities.Commande;
import entities.CommandeClient;
import entities.Facture;
import entities.Famille;
import entities.LigneCommandeClient;
import entities.Lot;
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
import sessions.FamilleFacadeLocal;
import sessions.LigneCommandeClientFacadeLocal;
import sessions.LotFacadeLocal;
import sessions.MouchardFacadeLocal;
import sessions.ProduitFacadeLocal;
import utils.Routine;
import utils.SessionMBean;

public class AbstractCommandeClientController {

    @Resource
    protected UserTransaction ut;
    @EJB
    protected CommandeClientFacadeLocal commandeClientFacadeLocal;
    /*  48 */    protected CommandeClient commandeClient = new CommandeClient();
    /*  49 */    protected List<CommandeClient> commandeClients = new ArrayList<>();

    @EJB
    protected LigneCommandeClientFacadeLocal ligneCommandeClientFacadeLocal;
    /*  53 */    protected LigneCommandeClient ligneCommandeClient = new LigneCommandeClient();
    /*  54 */    protected List<LigneCommandeClient> ligneCommandeClients = new ArrayList<>();

    @EJB
    protected FamilleFacadeLocal familleFacadeLocal;
    /*  58 */    protected Famille famille = new Famille();
    /*  59 */    protected List<Famille> familles = new ArrayList<>();

    @EJB
    protected ProduitFacadeLocal produitFacadeLocal;
    /*  63 */    protected Produit produit = new Produit();
    /*  64 */    protected List<Produit> produits = new ArrayList<>();

    @EJB
    protected ClientFacadeLocal clientFacadeLocal;
    /*  68 */    protected Client client = new Client();
    /*  69 */    protected List<Client> clients = new ArrayList<>();

    @EJB
    protected FactureFacadeLocal factureFacadeLocal;
    /*  73 */    protected Facture facture = new Facture();
    /*  74 */    protected List<Facture> factures = new ArrayList<>();

    @EJB
    protected CommandeFacadeLocal commandeFacadeLocal;
    /*  78 */    protected Commande commande = new Commande();
    /*  79 */    protected List<Commande> commandes = new ArrayList<>();

    @EJB
    protected AnneeFacadeLocal anneeFacadeLocal;
    /*  83 */    protected Annee annee = SessionMBean.getMois().getIdannee();
    /*  84 */    protected List<Annee> annees = new ArrayList<>();

    @EJB
    protected AnneeMoisFacadeLocal anneeMoisFacadeLocal;
    /*  88 */    protected AnneeMois anneeMois = SessionMBean.getMois();
    /*  89 */    protected List<AnneeMois> anneeMoises = new ArrayList<>();

    @EJB
    protected LotFacadeLocal lotFacadeLocal;
    /*  93 */    protected Lot lot = new Lot();
    /*  94 */    protected List<Lot> lots = new ArrayList<>();

    @EJB
    protected MouchardFacadeLocal mouchardFacadeLocal;

    /*  99 */    protected Routine routine = new Routine();

    /* 101 */    protected Double cout_quantite = Double.valueOf(0.0D);
    /* 102 */    protected Double total = Double.valueOf(0.0D);

    protected boolean nouveauClient = true;

    /* 107 */    protected Boolean saisieClient = Boolean.valueOf(true);

    /* 109 */    protected Boolean selectClient = Boolean.valueOf(true);

    /* 111 */    protected Boolean showSelector = Boolean.valueOf(true);

    /* 113 */    protected Boolean detail = Boolean.valueOf(true);
    /* 114 */    protected Boolean modifier = Boolean.valueOf(true);
    /* 115 */    protected Boolean consulter = Boolean.valueOf(true);
    /* 116 */    protected Boolean imprimer = Boolean.valueOf(true);
    /* 117 */    protected Boolean supprimer = Boolean.valueOf(true);

    /* 119 */    protected String fileName = "";

    /* 121 */    protected String mode = "";

    /* 123 */    int conteur = 0;

    public Produit getProduit() {
        /* 126 */ return this.produit;
    }

    public void setProduit(Produit produit) {
        /* 130 */ this.produit = produit;
    }

    public List<Produit> getProduits() {
        /* 134 */ return this.produits;
    }

    public Client getClient() {
        /* 138 */ return this.client;
    }

    public void setClient(Client client) {
        /* 142 */ this.client = client;
    }

    public List<Client> getClients() {
        /* 146 */ this.clients = this.clientFacadeLocal.findAllRange();
        /* 147 */ return this.clients;
    }

    public Facture getFacture() {
        /* 151 */ return this.facture;
    }

    public void setFacture(Facture facture) {
        /* 155 */ this.facture = facture;
    }

    public List<Facture> getFactures() {
        try {
            /* 160 */ this.factures = this.factureFacadeLocal.findAllDate(SessionMBean.getDateOuverture());
            /* 161 */        } catch (Exception e) {
            /* 162 */ e.printStackTrace();
        }
        /* 164 */ return this.factures;
    }

    public Commande getCommande() {
        /* 168 */ return this.commande;
    }

    public void setCommande(Commande commande) {
        /* 172 */ this.commande = commande;
    }

    public List<Commande> getCommandes() {
        /* 176 */ return this.commandes;
    }

    public Boolean getDetail() {
        /* 180 */ return this.detail;
    }

    public Boolean getModifier() {
        /* 184 */ return this.modifier;
    }

    public Boolean getConsulter() {
        /* 188 */ return this.consulter;
    }

    public Boolean getImprimer() {
        /* 192 */ return this.imprimer;
    }

    public Boolean getSupprimer() {
        /* 196 */ return this.supprimer;
    }

    public Famille getFamille() {
        /* 200 */ return this.famille;
    }

    public void setFamille(Famille famille) {
        /* 204 */ this.famille = famille;
    }

    public List<Famille> getFamilles() {
        /* 208 */ this.familles = this.familleFacadeLocal.findAllRange();
        /* 209 */ return this.familles;
    }

    public Double getCout_quantite() {
        /* 213 */ return this.cout_quantite;
    }

    public void setCout_quantite(Double cout_quantite) {
        /* 217 */ this.cout_quantite = cout_quantite;
    }

    public Double getTotal() {
        /* 221 */ return this.total;
    }

    public void setTotal(Double total) {
        /* 225 */ this.total = total;
    }

    public Annee getAnnee() {
        /* 229 */ return this.annee;
    }

    public void setAnnee(Annee annee) {
        /* 233 */ this.annee = annee;
    }

    public List<Annee> getAnnees() {
        try {
            /* 238 */ this.annees = this.anneeFacadeLocal.findByEtat(true);
            /* 239 */        } catch (Exception e) {
            /* 240 */ e.printStackTrace();
        }
        /* 242 */ return this.annees;
    }

    public AnneeMois getAnneeMois() {
        /* 246 */ return this.anneeMois;
    }

    public void setAnneeMois(AnneeMois anneeMois) {
        /* 250 */ this.anneeMois = anneeMois;
    }

    public List<AnneeMois> getAnneeMoises() {
        try {
            /* 255 */ this.anneeMoises = this.anneeMoisFacadeLocal.findByEtat(Boolean.valueOf(true));
            /* 256 */        } catch (Exception e) {
            /* 257 */ e.printStackTrace();
        }
        /* 259 */ return this.anneeMoises;
    }

    public String getFileName() {
        /* 263 */ return this.fileName;
    }

    public boolean isNouveauClient() {
        /* 267 */ return this.nouveauClient;
    }

    public void setNouveauClient(boolean nouveauClient) {
        /* 271 */ this.nouveauClient = nouveauClient;
    }

    public Boolean getSaisieClient() {
        /* 275 */ return this.saisieClient;
    }

    public Boolean getSelectClient() {
        /* 279 */ return this.selectClient;
    }

    public Boolean getShowSelector() {
        /* 283 */ return this.showSelector;
    }

    public Routine getRoutine() {
        /* 287 */ return this.routine;
    }

    public Lot getLot() {
        /* 291 */ return this.lot;
    }

    public void setLot(Lot lot) {
        /* 295 */ this.lot = lot;
    }

    public List<Lot> getLots() {
        /* 299 */ return this.lots;
    }

    public CommandeClient getCommandeClient() {
        /* 303 */ return this.commandeClient;
    }

    public void setCommandeClient(CommandeClient commandeClient) {
        /* 307 */ this.commandeClient = commandeClient;
        /* 308 */ this.modifier = this.supprimer = this.detail = this.imprimer = Boolean.valueOf((commandeClient == null));
    }

    public List<CommandeClient> getCommandeClients() {
        try {
            /* 313 */ this.commandeClients = this.commandeClientFacadeLocal.findByInterval(SessionMBean.getMois().getDateDebut(), SessionMBean.getMois().getDateFin());
            /* 314 */        } catch (Exception e) {
            /* 315 */ e.printStackTrace();
        }
        /* 317 */ return this.commandeClients;
    }

    public LigneCommandeClient getLigneCommandeClient() {
        /* 321 */ return this.ligneCommandeClient;
    }

    public void setLigneCommandeClient(LigneCommandeClient ligneCommandeClient) {
        /* 325 */ this.ligneCommandeClient = ligneCommandeClient;
    }

    public List<LigneCommandeClient> getLigneCommandeClients() {
        /* 329 */ return this.ligneCommandeClients;
    }
}


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-war.war!\WEB-INF\classes\controllers\commande_client\AbstractCommandeClientController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
