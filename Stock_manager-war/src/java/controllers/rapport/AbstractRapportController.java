package controllers.rapport;

import entities.Annee;
import entities.AnneeMois;
import entities.Client;
import entities.Facture;
import entities.Journee;
import entities.Stock;
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
import sessions.JourneeFacadeLocal;
import sessions.MouchardFacadeLocal;
import sessions.PrivilegeFacadeLocal;
import sessions.StockFacadeLocal;
import utils.Routine;
import utils.SessionMBean;

public class AbstractRapportController {

    @Resource
    protected UserTransaction ut;
    @EJB
    protected FactureFacadeLocal factureFacadeLocal;
    protected Facture facture = new Facture();
    protected List<Facture> factures = new ArrayList<>();

    @EJB
    protected JourneeFacadeLocal journeeFacadeLocal;
    protected List<Journee> Journees = new ArrayList<>();

    @EJB
    protected StockFacadeLocal stockFacadeLocal;
    protected List<Stock> stocks = new ArrayList<>();

    @EJB
    protected CommandeFacadeLocal commandeFacadeLocal;

    @EJB
    protected ClientFacadeLocal clientFacadeLocal;
    protected Client client = new Client();
    protected List<Client> clients = new ArrayList<>();

    @EJB
    protected AnneeFacadeLocal anneeFacadeLocal;
    /*  52 */    protected Annee annee = SessionMBean.getMois().getIdannee();
    /*  53 */    protected List<Annee> annees = new ArrayList<>();

    @EJB
    protected AnneeMoisFacadeLocal anneeMoisFacadeLocal;
    /*  57 */    protected AnneeMois anneeMois = SessionMBean.getMois();
    /*  58 */    protected List<AnneeMois> anneeMoises = new ArrayList<>();

    @EJB
    protected MouchardFacadeLocal mouchardFacadeLocal;

    @EJB
    protected PrivilegeFacadeLocal privilegeFacadeLocal;

    /*  66 */    protected Routine routine = new Routine();

    /*  68 */    protected Double cout_quantite = (0.0D);
    /*  69 */    protected Double total = (0.0D);

    protected boolean showDateInterval = true;

    protected boolean showClient = false;

    protected boolean showMois = false;
    protected boolean showDate = false;
    protected boolean showDateDebut = true;
    protected boolean showDateFin = true;
    /*  79 */    protected Boolean imprimer = (true);

    /*  81 */    protected int date_interval = 1;
    /*  82 */    protected Date date = new Date(System.currentTimeMillis());
    /*  83 */    protected Date dateDebut = new Date(System.currentTimeMillis());
    /*  84 */    protected Date dateFin = new Date(System.currentTimeMillis());

    /*  86 */    protected String fileName = "";
    /*  87 */    protected String printDialogTitle = "";
    /*  88 */    protected int critere = 1;

    public Client getClient() {
        /*  91 */ return this.client;
    }

    public void setClient(Client client) {
        /*  95 */ this.client = client;
    }

    public List<Client> getClients() {
        /*  99 */ this.clients = this.clientFacadeLocal.findAllRange();
        /* 100 */ return this.clients;
    }

    public void setClients(List<Client> clients) {
        /* 104 */ this.clients = clients;
    }

    public FactureFacadeLocal getFactureFacadeLocal() {
        /* 108 */ return this.factureFacadeLocal;
    }

    public void setFactureFacadeLocal(FactureFacadeLocal factureFacadeLocal) {
        /* 112 */ this.factureFacadeLocal = factureFacadeLocal;
    }

    public Facture getFacture() {
        /* 116 */ return this.facture;
    }

    public void setFacture(Facture facture) {
        /* 120 */ this.facture = facture;
    }

    public List<Facture> getFactures() {
        /* 124 */ return this.factures;
    }

    public void setFactures(List<Facture> factures) {
        /* 128 */ this.factures = factures;
    }

    public MouchardFacadeLocal getMouchardFacadeLocal() {
        /* 132 */ return this.mouchardFacadeLocal;
    }

    public void setMouchardFacadeLocal(MouchardFacadeLocal mouchardFacadeLocal) {
        /* 136 */ this.mouchardFacadeLocal = mouchardFacadeLocal;
    }

    public PrivilegeFacadeLocal getPrivilegeFacadeLocal() {
        /* 140 */ return this.privilegeFacadeLocal;
    }

    public void setPrivilegeFacadeLocal(PrivilegeFacadeLocal privilegeFacadeLocal) {
        /* 144 */ this.privilegeFacadeLocal = privilegeFacadeLocal;
    }

    public Boolean getImprimer() {
        /* 148 */ return this.imprimer;
    }

    public void setImprimer(Boolean imprimer) {
        /* 152 */ this.imprimer = imprimer;
    }

    public Double getCout_quantite() {
        /* 156 */ return this.cout_quantite;
    }

    public void setCout_quantite(Double cout_quantite) {
        /* 160 */ this.cout_quantite = cout_quantite;
    }

    public Double getTotal() {
        /* 164 */ return this.total;
    }

    public void setTotal(Double total) {
        /* 168 */ this.total = total;
    }

    public Annee getAnnee() {
        /* 172 */ return this.annee;
    }

    public void setAnnee(Annee annee) {
        /* 176 */ this.annee = annee;
    }

    public List<Annee> getAnnees() {
        try {
            /* 181 */ this.annees = this.anneeFacadeLocal.findByEtat(true);
            /* 182 */        } catch (Exception e) {
            /* 183 */ e.printStackTrace();
        }
        /* 185 */ return this.annees;
    }

    public void setAnnees(List<Annee> annees) {
        /* 189 */ this.annees = annees;
    }

    public AnneeMois getAnneeMois() {
        /* 193 */ return this.anneeMois;
    }

    public void setAnneeMois(AnneeMois anneeMois) {
        /* 197 */ this.anneeMois = anneeMois;
    }

    public List<AnneeMois> getAnneeMoises() {
        try {
            /* 202 */ this.anneeMoises = this.anneeMoisFacadeLocal.findByAnnee(SessionMBean.getMois().getIdannee());
            /* 203 */        } catch (Exception e) {
            /* 204 */ e.printStackTrace();
        }
        /* 206 */ return this.anneeMoises;
    }

    public void setAnneeMoises(List<AnneeMois> anneeMoises) {
        /* 210 */ this.anneeMoises = anneeMoises;
    }

    public String getFileName() {
        /* 214 */ return this.fileName;
    }

    public void setFileName(String fileName) {
        /* 218 */ this.fileName = fileName;
    }

    public Routine getRoutine() {
        /* 222 */ return this.routine;
    }

    public int getCritere() {
        /* 226 */ return this.critere;
    }

    public void setCritere(int critere) {
        /* 230 */ this.critere = critere;
    }

    public Date getDate() {
        /* 234 */ return this.date;
    }

    public void setDate(Date date) {
        /* 238 */ this.date = date;
    }

    public Date getDateDebut() {
        /* 242 */ return this.dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        /* 246 */ this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        /* 250 */ return this.dateFin;
    }

    public void setDateFin(Date dateFin) {
        /* 254 */ this.dateFin = dateFin;
    }

    public int getDate_interval() {
        /* 258 */ return this.date_interval;
    }

    public void setDate_interval(int date_interval) {
        /* 262 */ this.date_interval = date_interval;
    }

    public boolean isShowDateInterval() {
        /* 266 */ return this.showDateInterval;
    }

    public boolean isShowClient() {
        /* 270 */ return this.showClient;
    }

    public boolean isShowMois() {
        /* 274 */ return this.showMois;
    }

    public boolean isShowDate() {
        /* 278 */ return this.showDate;
    }

    public boolean isShowDateDebut() {
        /* 282 */ return this.showDateDebut;
    }

    public boolean isShowDateFin() {
        /* 286 */ return this.showDateFin;
    }

    public String getPrintDialogTitle() {
        /* 290 */ return this.printDialogTitle;
    }
}


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-war.war!\WEB-INF\classes\controllers\recherche\AbstractRechercheController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
