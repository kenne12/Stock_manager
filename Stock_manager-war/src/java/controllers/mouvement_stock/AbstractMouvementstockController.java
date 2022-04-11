package controllers.mouvement_stock;

import entities.Annee;
import entities.AnneeMois;
import entities.Client;
import entities.Commande;
import entities.Facture;
import entities.LigneMvtStock;
import entities.StockProduit;
import java.util.ArrayList;
import java.util.Date;
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
import sessions.PrivilegeFacadeLocal;
import sessions.ProduitFacadeLocal;
import sessions.StockProduitFacadeLocal;
import utils.MouvementStock;
import utils.Routine;
import utils.SessionMBean;

public class AbstractMouvementstockController {

    @Resource
    protected UserTransaction ut;
    @EJB
    protected LigneMvtStockFacadeLocal ligneMvtStockFacadeLocal;
    /*  48 */    protected List<LigneMvtStock> ligneMvtStocks = new ArrayList<>();

    @EJB
    protected FactureFacadeLocal factureFacadeLocal;
    /*  52 */    protected Facture facture = new Facture();
    /*  53 */    protected List<Facture> factures = new ArrayList<>();

    @EJB
    protected CommandeFacadeLocal commandeFacadeLocal;
    /*  57 */    protected List<Commande> commandes = new ArrayList<>();

    @EJB
    protected StockProduitFacadeLocal stockProduitFacadeLocal;
    /*  61 */    protected List<StockProduit> stockProduits = new ArrayList<>();

    @EJB
    protected ProduitFacadeLocal produitFacadeLocal;

    @EJB
    protected FamilleFacadeLocal familleFacadeLocal;

    @EJB
    protected LotFacadeLocal lotFacadeLocal;

    /*  72 */    List<MouvementStock> mouvementStocks = new ArrayList<>();

    @EJB
    protected ClientFacadeLocal clientFacadeLocal;
    /*  76 */    protected Client client = new Client();
    /*  77 */    protected List<Client> clients = new ArrayList<>();

    @EJB
    protected AnneeFacadeLocal anneeFacadeLocal;
    /*  81 */    protected Annee annee = SessionMBean.getMois().getIdannee();
    /*  82 */    protected List<Annee> annees = new ArrayList<>();

    @EJB
    protected AnneeMoisFacadeLocal anneeMoisFacadeLocal;
    /*  86 */    protected AnneeMois anneeMois = SessionMBean.getMois();
    /*  87 */    protected List<AnneeMois> anneeMoises = new ArrayList<>();

    @EJB
    protected MouchardFacadeLocal mouchardFacadeLocal;

    @EJB
    protected PrivilegeFacadeLocal privilegeFacadeLocal;

    /*  95 */    protected Routine routine = new Routine();

    /*  97 */    protected Double cout_quantite = Double.valueOf(0.0D);
    /*  98 */    protected Double total = Double.valueOf(0.0D);

    protected boolean showDateInterval = true;

    protected boolean showClient = false;

    protected boolean showMois = false;
    protected boolean showDate = false;
    protected boolean showDateDebut = true;
    protected boolean showDateFin = true;
    /* 108 */    protected Boolean imprimer = Boolean.valueOf(true);

    /* 110 */    protected int date_interval = 1;
    /* 111 */    protected Date date = new Date(System.currentTimeMillis());
    /* 112 */    protected Date dateDebut = new Date(System.currentTimeMillis());
    /* 113 */    protected Date dateFin = new Date(System.currentTimeMillis());

    /* 115 */    protected String fileName = "";
    /* 116 */    protected String printDialogTitle = "";
    /* 117 */    protected int critere = 1;

    public Client getClient() {
        /* 120 */ return this.client;
    }

    public void setClient(Client client) {
        /* 124 */ this.client = client;
    }

    public List<Client> getClients() {
        /* 128 */ this.clients = this.clientFacadeLocal.findAllRange();
        /* 129 */ return this.clients;
    }

    public void setClients(List<Client> clients) {
        /* 133 */ this.clients = clients;
    }

    public FactureFacadeLocal getFactureFacadeLocal() {
        /* 137 */ return this.factureFacadeLocal;
    }

    public void setFactureFacadeLocal(FactureFacadeLocal factureFacadeLocal) {
        /* 141 */ this.factureFacadeLocal = factureFacadeLocal;
    }

    public Facture getFacture() {
        /* 145 */ return this.facture;
    }

    public void setFacture(Facture facture) {
        /* 149 */ this.facture = facture;
    }

    public List<Facture> getFactures() {
        /* 153 */ return this.factures;
    }

    public void setFactures(List<Facture> factures) {
        /* 157 */ this.factures = factures;
    }

    public MouchardFacadeLocal getMouchardFacadeLocal() {
        /* 161 */ return this.mouchardFacadeLocal;
    }

    public void setMouchardFacadeLocal(MouchardFacadeLocal mouchardFacadeLocal) {
        /* 165 */ this.mouchardFacadeLocal = mouchardFacadeLocal;
    }

    public PrivilegeFacadeLocal getPrivilegeFacadeLocal() {
        /* 169 */ return this.privilegeFacadeLocal;
    }

    public void setPrivilegeFacadeLocal(PrivilegeFacadeLocal privilegeFacadeLocal) {
        /* 173 */ this.privilegeFacadeLocal = privilegeFacadeLocal;
    }

    public Boolean getImprimer() {
        /* 177 */ return this.imprimer;
    }

    public void setImprimer(Boolean imprimer) {
        /* 181 */ this.imprimer = imprimer;
    }

    public Double getCout_quantite() {
        /* 185 */ return this.cout_quantite;
    }

    public void setCout_quantite(Double cout_quantite) {
        /* 189 */ this.cout_quantite = cout_quantite;
    }

    public Double getTotal() {
        /* 193 */ return this.total;
    }

    public void setTotal(Double total) {
        /* 197 */ this.total = total;
    }

    public Annee getAnnee() {
        /* 201 */ return this.annee;
    }

    public void setAnnee(Annee annee) {
        /* 205 */ this.annee = annee;
    }

    public List<Annee> getAnnees() {
        try {
            /* 210 */ this.annees = this.anneeFacadeLocal.findByEtat(true);
            /* 211 */        } catch (Exception e) {
            /* 212 */ e.printStackTrace();
        }
        /* 214 */ return this.annees;
    }

    public void setAnnees(List<Annee> annees) {
        /* 218 */ this.annees = annees;
    }

    public AnneeMois getAnneeMois() {
        /* 222 */ return this.anneeMois;
    }

    public void setAnneeMois(AnneeMois anneeMois) {
        /* 226 */ this.anneeMois = anneeMois;
    }

    public List<AnneeMois> getAnneeMoises() {
        try {
            /* 231 */ this.anneeMoises = this.anneeMoisFacadeLocal.findByAnnee(SessionMBean.getMois().getIdannee());
            /* 232 */        } catch (Exception e) {
            /* 233 */ e.printStackTrace();
        }
        /* 235 */ return this.anneeMoises;
    }

    public void setAnneeMoises(List<AnneeMois> anneeMoises) {
        /* 239 */ this.anneeMoises = anneeMoises;
    }

    public String getFileName() {
        /* 243 */ return this.fileName;
    }

    public void setFileName(String fileName) {
        /* 247 */ this.fileName = fileName;
    }

    public Routine getRoutine() {
        /* 251 */ return this.routine;
    }

    public int getCritere() {
        /* 255 */ return this.critere;
    }

    public void setCritere(int critere) {
        /* 259 */ this.critere = critere;
    }

    public Date getDate() {
        /* 263 */ return this.date;
    }

    public void setDate(Date date) {
        /* 267 */ this.date = date;
    }

    public Date getDateDebut() {
        /* 271 */ return this.dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        /* 275 */ this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        /* 279 */ return this.dateFin;
    }

    public void setDateFin(Date dateFin) {
        /* 283 */ this.dateFin = dateFin;
    }

    public int getDate_interval() {
        /* 287 */ return this.date_interval;
    }

    public void setDate_interval(int date_interval) {
        /* 291 */ this.date_interval = date_interval;
    }

    public boolean isShowDateInterval() {
        /* 295 */ return this.showDateInterval;
    }

    public boolean isShowClient() {
        /* 299 */ return this.showClient;
    }

    public boolean isShowMois() {
        /* 303 */ return this.showMois;
    }

    public boolean isShowDate() {
        /* 307 */ return this.showDate;
    }

    public boolean isShowDateDebut() {
        /* 311 */ return this.showDateDebut;
    }

    public boolean isShowDateFin() {
        /* 315 */ return this.showDateFin;
    }

    public String getPrintDialogTitle() {
        /* 319 */ return this.printDialogTitle;
    }

    public List<StockProduit> getStockProduits() {
        /* 323 */ return this.stockProduits;
    }

    public void setStockProduits(List<StockProduit> stockProduits) {
        /* 327 */ this.stockProduits = stockProduits;
    }

    public List<LigneMvtStock> getLigneMvtStocks() {
        /* 331 */ return this.ligneMvtStocks;
    }
}


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-war.war!\WEB-INF\classes\controllers\mouvement_stock\AbstractMouvementstockController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
