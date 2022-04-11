package controllers.fraiscarnet;

import entities.Client;
import entities.Profession;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import sessions.CaisseFacadeLocal;
import sessions.ClientFacadeLocal;
import sessions.MouchardFacadeLocal;
import sessions.PrivilegeFacadeLocal;
import sessions.ProfessionFacadeLocal;
import sessions.RetraitFacadeLocal;
import sessions.VersementFacadeLocal;

public class AbstractCarnetClientController {

    @EJB
    protected ClientFacadeLocal clientFacadeLocal;
    protected Client client;
    /*  30 */    protected List<Client> clients = new ArrayList<>();
    /*  31 */    protected List<Client> clients1 = new ArrayList<>();

    @EJB
    protected MouchardFacadeLocal mouchardFacadeLocal;

    @EJB
    protected ProfessionFacadeLocal professionFacadeLocal;
    /*  38 */    protected List<Profession> professions = new ArrayList<>();

    @EJB
    protected VersementFacadeLocal versementFacadeLocal;

    @EJB
    protected CaisseFacadeLocal caisseFacadeLocal;

    @EJB
    protected RetraitFacadeLocal retraitFacadeLocal;

    @EJB
    protected PrivilegeFacadeLocal privilegeFacadeLocal;

    /*  52 */    protected String fileName = "";

    /*  54 */    protected Boolean detail = Boolean.valueOf(true);
    /*  55 */    protected Boolean modifier = Boolean.valueOf(true);
    /*  56 */    protected Boolean consulter = Boolean.valueOf(true);
    /*  57 */    protected Boolean imprimer = Boolean.valueOf(true);
    /*  58 */    protected Boolean supprimer = Boolean.valueOf(true);

    /*  60 */    protected Boolean showEditSolde = Boolean.valueOf(true);

    protected boolean showClientCreateDialog = false;
    protected boolean showClientDeleteDialog = false;
    /*  64 */    protected Boolean showClientPrintDialog = Boolean.valueOf(false);

    protected boolean showMontantCarnet = true;

    protected boolean showMontantCarnetCompnent = true;
    /*  69 */    protected int carnet = 500;

    /*  71 */    protected String mode = "";

    public Client getClient() {
        /*  74 */ return this.client;
    }

    public void setClient(Client client) {
        /*  78 */ this.modifier = this.supprimer = this.detail = Boolean.valueOf((client == null));
        /*  79 */ this.client = client;
    }

    public List<Client> getClients() {
        /*  83 */ return this.clients;
    }

    public void setClients(List<Client> clients) {
        /*  87 */ this.clients = clients;
    }

    public Boolean getDetail() {
        /*  91 */ return this.detail;
    }

    public void setDetail(Boolean detail) {
        /*  95 */ this.detail = detail;
    }

    public Boolean getModifier() {
        /*  99 */ return this.modifier;
    }

    public void setModifier(Boolean modifier) {
        /* 103 */ this.modifier = modifier;
    }

    public Boolean getConsulter() {
        /* 107 */ return this.consulter;
    }

    public void setConsulter(Boolean consulter) {
        /* 111 */ this.consulter = consulter;
    }

    public Boolean getImprimer() {
        /* 115 */ this.imprimer = Boolean.valueOf(this.clientFacadeLocal.findAllRange().isEmpty());
        /* 116 */ return this.imprimer;
    }

    public void setImprimer(Boolean imprimer) {
        /* 120 */ this.imprimer = imprimer;
    }

    public Boolean getSupprimer() {
        /* 125 */ return this.supprimer;
    }

    public void setSupprimer(Boolean supprimer) {
        /* 129 */ this.supprimer = supprimer;
    }

    public Boolean getShowEditSolde() {
        /* 133 */ return this.showEditSolde;
    }

    public void setShowEditSolde(Boolean showEditSolde) {
        /* 137 */ this.showEditSolde = showEditSolde;
    }

    public boolean isShowClientCreateDialog() {
        /* 141 */ return this.showClientCreateDialog;
    }

    public void setShowClientCreateDialog(boolean showClientCreateDialog) {
        /* 145 */ this.showClientCreateDialog = showClientCreateDialog;
    }

    public boolean isShowClientDeleteDialog() {
        /* 149 */ return this.showClientDeleteDialog;
    }

    public void setShowClientDeleteDialog(boolean showClientDeleteDialog) {
        /* 153 */ this.showClientDeleteDialog = showClientDeleteDialog;
    }

    public Boolean getShowClientPrintDialog() {
        /* 157 */ return this.showClientPrintDialog;
    }

    public void setShowClientPrintDialog(Boolean showClientPrintDialog) {
        /* 161 */ this.showClientPrintDialog = showClientPrintDialog;
    }

    public String getFileName() {
        /* 165 */ return this.fileName;
    }

    public void setFileName(String fileName) {
        /* 169 */ this.fileName = fileName;
    }

    public int getCarnet() {
        /* 173 */ return this.carnet;
    }

    public void setCarnet(int carnet) {
        /* 177 */ this.carnet = carnet;
    }

    public boolean isShowMontantCarnet() {
        /* 181 */ return this.showMontantCarnet;
    }

    public void setShowMontantCarnet(boolean showMontantCarnet) {
        /* 185 */ this.showMontantCarnet = showMontantCarnet;
    }

    public boolean isShowMontantCarnetCompnent() {
        /* 189 */ return this.showMontantCarnetCompnent;
    }

    public void setShowMontantCarnetCompnent(boolean showMontantCarnetCompnent) {
        /* 193 */ this.showMontantCarnetCompnent = showMontantCarnetCompnent;
    }

    public List<Client> getClients1() {
        /* 197 */ return this.clients1;
    }

    public void setClients1(List<Client> clients1) {
        /* 201 */ this.clients1 = clients1;
    }
}


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-war.war!\WEB-INF\classes\controllers\fraiscarnet\AbstractCarnetClientController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
