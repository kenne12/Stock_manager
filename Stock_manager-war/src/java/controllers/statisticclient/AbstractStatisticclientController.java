package controllers.statisticclient;

import entities.Annee;
import entities.AnneeMois;
import entities.Client;
import entities.Facture;
import java.util.ArrayList;
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

public class AbstractStatisticclientController {

    protected LineChartModel lineModel1;
    protected LineChartModel lineModel2;
    protected BarChartModel barModel;
    @Resource
    protected UserTransaction ut;
    @EJB
    protected FactureFacadeLocal factureFacadeLocal;
    /*  45 */    protected Facture facture = new Facture();
    /*  46 */    protected List<Facture> factures = new ArrayList<>();

    @EJB
    protected StockFacadeLocal stockFacadeLocal;

    @EJB
    protected CommandeFacadeLocal commandeFacadeLocal;

    @EJB
    protected ClientFacadeLocal clientFacadeLocal;
    /*  56 */    protected Client client = new Client();
    /*  57 */    protected List<Client> clients = new ArrayList<>();
    /*  58 */    protected List<Client> selectedClients = new ArrayList<>();

    @EJB
    protected AnneeFacadeLocal anneeFacadeLocal;
    /*  62 */    protected Annee annee = SessionMBean.getMois().getIdannee();
    /*  63 */    protected List<Annee> annees = new ArrayList<>();

    @EJB
    protected AnneeMoisFacadeLocal anneeMoisFacadeLocal;
    /*  67 */    protected AnneeMois anneeMois = SessionMBean.getMois();
    /*  68 */    protected List<AnneeMois> anneeMoises = new ArrayList<>();

    @EJB
    protected MouchardFacadeLocal mouchardFacadeLocal;

    @EJB
    protected PrivilegeFacadeLocal privilegeFacadeLocal;

    /*  76 */    protected Routine routine = new Routine();

    /*  78 */    protected Double cout_quantite = Double.valueOf(0.0D);
    /*  79 */    protected Double total = Double.valueOf(0.0D);

    public Client getClient() {
        /*  82 */ return this.client;
    }

    public void setClient(Client client) {
        /*  86 */ this.client = client;
    }

    public List<Client> getClients() {
        /*  90 */ return this.clients;
    }

    public void setClients(List<Client> clients) {
        /*  94 */ this.clients = clients;
    }

    public FactureFacadeLocal getFactureFacadeLocal() {
        /*  98 */ return this.factureFacadeLocal;
    }

    public void setFactureFacadeLocal(FactureFacadeLocal factureFacadeLocal) {
        /* 102 */ this.factureFacadeLocal = factureFacadeLocal;
    }

    public Facture getFacture() {
        /* 106 */ return this.facture;
    }

    public void setFacture(Facture facture) {
        /* 110 */ this.facture = facture;
    }

    public List<Facture> getFactures() {
        /* 114 */ return this.factures;
    }

    public void setFactures(List<Facture> factures) {
        /* 118 */ this.factures = factures;
    }

    public MouchardFacadeLocal getMouchardFacadeLocal() {
        /* 122 */ return this.mouchardFacadeLocal;
    }

    public void setMouchardFacadeLocal(MouchardFacadeLocal mouchardFacadeLocal) {
        /* 126 */ this.mouchardFacadeLocal = mouchardFacadeLocal;
    }

    public PrivilegeFacadeLocal getPrivilegeFacadeLocal() {
        /* 130 */ return this.privilegeFacadeLocal;
    }

    public void setPrivilegeFacadeLocal(PrivilegeFacadeLocal privilegeFacadeLocal) {
        /* 134 */ this.privilegeFacadeLocal = privilegeFacadeLocal;
    }

    public Double getCout_quantite() {
        /* 138 */ return this.cout_quantite;
    }

    public void setCout_quantite(Double cout_quantite) {
        /* 142 */ this.cout_quantite = cout_quantite;
    }

    public Double getTotal() {
        /* 146 */ return this.total;
    }

    public void setTotal(Double total) {
        /* 150 */ this.total = total;
    }

    public Annee getAnnee() {
        /* 154 */ return this.annee;
    }

    public void setAnnee(Annee annee) {
        /* 158 */ this.annee = annee;
    }

    public List<Annee> getAnnees() {
        try {
            /* 163 */ this.annees = this.anneeFacadeLocal.findByRange();
            /* 164 */        } catch (Exception e) {
            /* 165 */ e.printStackTrace();
        }
        /* 167 */ return this.annees;
    }

    public void setAnnees(List<Annee> annees) {
        /* 171 */ this.annees = annees;
    }

    public AnneeMois getAnneeMois() {
        /* 175 */ return this.anneeMois;
    }

    public void setAnneeMois(AnneeMois anneeMois) {
        /* 179 */ this.anneeMois = anneeMois;
    }

    public List<AnneeMois> getAnneeMoises() {
        /* 183 */ return this.anneeMoises;
    }

    public void setAnneeMoises(List<AnneeMois> anneeMoises) {
        /* 187 */ this.anneeMoises = anneeMoises;
    }

    public Routine getRoutine() {
        /* 191 */ return this.routine;
    }

    public LineChartModel getLineModel1() {
        /* 195 */ return this.lineModel1;
    }

    public void setLineModel1(LineChartModel lineModel1) {
        /* 199 */ this.lineModel1 = lineModel1;
    }

    public LineChartModel getLineModel2() {
        /* 203 */ return this.lineModel2;
    }

    public void setLineModel2(LineChartModel lineModel2) {
        /* 207 */ this.lineModel2 = lineModel2;
    }

    public BarChartModel getBarModel() {
        /* 211 */ return this.barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        /* 215 */ this.barModel = barModel;
    }

    public List<Client> getSelectedClients() {
        /* 219 */ return this.selectedClients;
    }

    public void setSelectedClients(List<Client> selectedClients) {
        /* 223 */ this.selectedClients = selectedClients;
    }
}
