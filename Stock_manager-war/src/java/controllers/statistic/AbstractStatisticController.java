package controllers.statistic;

import entities.Annee;
import entities.AnneeMois;
import entities.Client;
import entities.Facture;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.transaction.UserTransaction;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.LineChartModel;
import sessions.AnneeFacadeLocal;
import sessions.AnneeMoisFacadeLocal;
import sessions.ClientFacadeLocal;
import sessions.CommandeFacadeLocal;
import sessions.FactureFacadeLocal;
import sessions.MouchardFacadeLocal;
import sessions.PrivilegeFacadeLocal;
import sessions.StockFacadeLocal;
import utils.Routine;
import utils.SessionMBean;

public class AbstractStatisticController {

    protected LineChartModel lineModel1;
    protected LineChartModel lineModel2;
    protected BarChartModel barModel;
    @Resource
    protected UserTransaction ut;
    @EJB
    protected FactureFacadeLocal factureFacadeLocal;
    protected Facture facture = new Facture();
    protected List<Facture> factures = new ArrayList<>();

    @EJB
    protected StockFacadeLocal stockFacadeLocal;

    @EJB
    protected CommandeFacadeLocal commandeFacadeLocal;

    @EJB
    protected ClientFacadeLocal clientFacadeLocal;
    protected Client client = new Client();
    protected List<Client> clients = new ArrayList<>();

    @EJB
    protected AnneeFacadeLocal anneeFacadeLocal;
    protected Annee annee = SessionMBean.getMois().getIdannee();
    protected List<Annee> annees = new ArrayList<>();

    @EJB
    protected AnneeMoisFacadeLocal anneeMoisFacadeLocal;
    protected AnneeMois anneeMois = SessionMBean.getMois();
    protected List<AnneeMois> anneeMoises = new ArrayList<>();

    @EJB
    protected MouchardFacadeLocal mouchardFacadeLocal;

    @EJB
    protected PrivilegeFacadeLocal privilegeFacadeLocal;

    protected Routine routine = new Routine();

    protected Double cout_quantite = (0.0D);
    protected Double total = (0.0D);

    protected Boolean imprimer = (true);

    protected int date_interval = 1;
    protected Date date = new Date(System.currentTimeMillis());
    protected Date dateDebut = new Date(System.currentTimeMillis());
    protected Date dateFin = new Date(System.currentTimeMillis());

    protected String fileName = "";
    protected String printDialogTitle = "";
    protected int critere = 1;

    public Client getClient() {
        /*  93 */ return this.client;
    }

    public void setClient(Client client) {
        /*  97 */ this.client = client;
    }

    public List<Client> getClients() {
        /* 101 */ this.clients = this.clientFacadeLocal.findAllRange();
        /* 102 */ return this.clients;
    }

    public void setClients(List<Client> clients) {
        /* 106 */ this.clients = clients;
    }

    public FactureFacadeLocal getFactureFacadeLocal() {
        /* 110 */ return this.factureFacadeLocal;
    }

    public void setFactureFacadeLocal(FactureFacadeLocal factureFacadeLocal) {
        /* 114 */ this.factureFacadeLocal = factureFacadeLocal;
    }

    public Facture getFacture() {
        /* 118 */ return this.facture;
    }

    public void setFacture(Facture facture) {
        /* 122 */ this.facture = facture;
    }

    public List<Facture> getFactures() {
        /* 126 */ return this.factures;
    }

    public void setFactures(List<Facture> factures) {
        /* 130 */ this.factures = factures;
    }

    public MouchardFacadeLocal getMouchardFacadeLocal() {
        /* 134 */ return this.mouchardFacadeLocal;
    }

    public void setMouchardFacadeLocal(MouchardFacadeLocal mouchardFacadeLocal) {
        /* 138 */ this.mouchardFacadeLocal = mouchardFacadeLocal;
    }

    public PrivilegeFacadeLocal getPrivilegeFacadeLocal() {
        /* 142 */ return this.privilegeFacadeLocal;
    }

    public void setPrivilegeFacadeLocal(PrivilegeFacadeLocal privilegeFacadeLocal) {
        /* 146 */ this.privilegeFacadeLocal = privilegeFacadeLocal;
    }

    public Boolean getImprimer() {
        /* 150 */ return this.imprimer;
    }

    public void setImprimer(Boolean imprimer) {
        /* 154 */ this.imprimer = imprimer;
    }

    public Double getCout_quantite() {
        /* 158 */ return this.cout_quantite;
    }

    public void setCout_quantite(Double cout_quantite) {
        /* 162 */ this.cout_quantite = cout_quantite;
    }

    public Double getTotal() {
        /* 166 */ return this.total;
    }

    public void setTotal(Double total) {
        /* 170 */ this.total = total;
    }

    public Annee getAnnee() {
        /* 174 */ return this.annee;
    }

    public void setAnnee(Annee annee) {
        /* 178 */ this.annee = annee;
    }

    public List<Annee> getAnnees() {
        try {
            /* 183 */ this.annees = this.anneeFacadeLocal.findByRange();
            /* 184 */        } catch (Exception e) {
            /* 185 */ e.printStackTrace();
        }
        /* 187 */ return this.annees;
    }

    public void setAnnees(List<Annee> annees) {
        /* 191 */ this.annees = annees;
    }

    public AnneeMois getAnneeMois() {
        /* 195 */ return this.anneeMois;
    }

    public void setAnneeMois(AnneeMois anneeMois) {
        /* 199 */ this.anneeMois = anneeMois;
    }

    public List<AnneeMois> getAnneeMoises() {
        /* 203 */ return this.anneeMoises;
    }

    public void setAnneeMoises(List<AnneeMois> anneeMoises) {
        /* 207 */ this.anneeMoises = anneeMoises;
    }

    public String getFileName() {
        /* 211 */ return this.fileName;
    }

    public void setFileName(String fileName) {
        /* 215 */ this.fileName = fileName;
    }

    public Routine getRoutine() {
        /* 219 */ return this.routine;
    }

    public int getCritere() {
        /* 223 */ return this.critere;
    }

    public void setCritere(int critere) {
        /* 227 */ this.critere = critere;
    }

    public Date getDate() {
        /* 231 */ return this.date;
    }

    public void setDate(Date date) {
        /* 235 */ this.date = date;
    }

    public Date getDateDebut() {
        /* 239 */ return this.dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        /* 243 */ this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        /* 247 */ return this.dateFin;
    }

    public void setDateFin(Date dateFin) {
        /* 251 */ this.dateFin = dateFin;
    }

    public int getDate_interval() {
        /* 255 */ return this.date_interval;
    }

    public void setDate_interval(int date_interval) {
        /* 259 */ this.date_interval = date_interval;
    }

    public String getPrintDialogTitle() {
        /* 263 */ return this.printDialogTitle;
    }

    public LineChartModel getLineModel1() {
        /* 267 */ return this.lineModel1;
    }

    public void setLineModel1(LineChartModel lineModel1) {
        /* 271 */ this.lineModel1 = lineModel1;
    }

    public LineChartModel getLineModel2() {
        /* 275 */ return this.lineModel2;
    }

    public void setLineModel2(LineChartModel lineModel2) {
        /* 279 */ this.lineModel2 = lineModel2;
    }

    public BarChartModel getBarModel() {
        /* 283 */ return this.barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        /* 287 */ this.barModel = barModel;
    }
}
