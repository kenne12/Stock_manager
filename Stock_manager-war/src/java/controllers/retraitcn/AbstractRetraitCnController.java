package controllers.retraitcn;

import entities.Client;
import entities.Retrait;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import sessions.CaisseFacadeLocal;
import sessions.ClientFacadeLocal;
import sessions.MouchardFacadeLocal;
import sessions.PrivilegeFacadeLocal;
import sessions.RetraitFacadeLocal;

public class AbstractRetraitCnController {

    @EJB
    protected RetraitFacadeLocal retraitFacadeLocal;
    protected Retrait retrait;
    /*  28 */    protected List<Retrait> retraits = new ArrayList<>();

    @EJB
    protected ClientFacadeLocal clientFacadeLocal;
    protected Client client;
    /*  33 */    protected List<Client> clients = new ArrayList<>();

    @EJB
    protected MouchardFacadeLocal mouchardFacadeLocal;

    @EJB
    protected CaisseFacadeLocal caisseFacadeLocal;

    @EJB
    protected PrivilegeFacadeLocal privilegeFacadeLocal;

    /*  44 */    protected Integer retrait1 = Integer.valueOf(0);
    /*  45 */    protected Integer commission = Integer.valueOf(0);

    /*  47 */    protected Boolean detail = Boolean.valueOf(true);
    /*  48 */    protected Boolean modifier = Boolean.valueOf(true);
    /*  49 */    protected Boolean consulter = Boolean.valueOf(true);
    /*  50 */    protected Boolean imprimer = Boolean.valueOf(true);
    /*  51 */    protected Boolean supprimer = Boolean.valueOf(true);

    /*  53 */    protected Boolean showRetraitCreateDialog = Boolean.valueOf(false);
    /*  54 */    protected Boolean showRetraitDetailDialog = Boolean.valueOf(false);
    /*  55 */    protected Boolean showRetraitDeleteDialog = Boolean.valueOf(false);
    /*  56 */    protected Boolean showRetraitEditDialog = Boolean.valueOf(false);
    /*  57 */    protected Boolean showRetraitPrintDialog = Boolean.valueOf(false);

    /*  59 */    protected Boolean showClient = Boolean.valueOf(true);

    /*  61 */    protected String mode = "";

    public Client getClient() {
        /*  64 */ return this.client;
    }

    public void setClient(Client client) {
        /*  68 */ this.client = client;
    }

    public List<Client> getClients() {
        /*  72 */ this.clients = this.clientFacadeLocal.findAllRange();
        /*  73 */ return this.clients;
    }

    public void setClients(List<Client> clients) {
        /*  77 */ this.clients = clients;
    }

    public Boolean getDetail() {
        /*  81 */ return this.detail;
    }

    public void setDetail(Boolean detail) {
        /*  85 */ this.detail = detail;
    }

    public Boolean getModifier() {
        /*  89 */ return this.modifier;
    }

    public void setModifier(Boolean modifier) {
        /*  93 */ this.modifier = modifier;
    }

    public Boolean getImprimer() {
        /*  97 */ this.imprimer = Boolean.valueOf(this.retraitFacadeLocal.findAll().isEmpty());
        /*  98 */ return this.imprimer;
    }

    public void setImprimer(Boolean imprimer) {
        /* 102 */ this.imprimer = imprimer;
    }

    public Boolean getSupprimer() {
        /* 106 */ return this.supprimer;
    }

    public void setSupprimer(Boolean supprimer) {
        /* 110 */ this.supprimer = supprimer;
    }

    public Integer getRetrait1() {
        /* 114 */ return this.retrait1;
    }

    public void setRetrait1(Integer retrait1) {
        /* 118 */ this.retrait1 = retrait1;
    }

    public Boolean getShowClient() {
        /* 122 */ return this.showClient;
    }

    public void setShowClient(Boolean showClient) {
        /* 127 */ this.showClient = showClient;
    }

    public Retrait getRetrait() {
        /* 131 */ return this.retrait;
    }

    public void setRetrait(Retrait retrait) {
        /* 135 */ this.modifier = this.supprimer = this.detail = Boolean.valueOf((retrait == null));
        /* 136 */ this.retrait = retrait;
    }

    public List<Retrait> getRetraits() {
        /* 140 */ this.retraits = this.retraitFacadeLocal.findAll();
        /* 141 */ return this.retraits;
    }

    public void setRetraits(List<Retrait> retraits) {
        /* 145 */ this.retraits = retraits;
    }

    public Boolean getShowRetraitCreateDialog() {
        /* 149 */ return this.showRetraitCreateDialog;
    }

    public void setShowRetraitCreateDialog(Boolean showRetraitCreateDialog) {
        /* 153 */ this.showRetraitCreateDialog = showRetraitCreateDialog;
    }

    public Boolean getShowRetraitDetailDialog() {
        /* 157 */ return this.showRetraitDetailDialog;
    }

    public void setShowRetraitDetailDialog(Boolean showRetraitDetailDialog) {
        /* 161 */ this.showRetraitDetailDialog = showRetraitDetailDialog;
    }

    public Boolean getShowRetraitDeleteDialog() {
        /* 165 */ return this.showRetraitDeleteDialog;
    }

    public void setShowRetraitDeleteDialog(Boolean showRetraitDeleteDialog) {
        /* 169 */ this.showRetraitDeleteDialog = showRetraitDeleteDialog;
    }

    public Boolean getShowRetraitEditDialog() {
        /* 173 */ return this.showRetraitEditDialog;
    }

    public void setShowRetraitEditDialog(Boolean showRetraitEditDialog) {
        /* 177 */ this.showRetraitEditDialog = showRetraitEditDialog;
    }

    public Boolean getShowRetraitPrintDialog() {
        /* 181 */ return this.showRetraitPrintDialog;
    }

    public void setShowRetraitPrintDialog(Boolean showRetraitPrintDialog) {
        /* 185 */ this.showRetraitPrintDialog = showRetraitPrintDialog;
    }

    public Integer getCommission() {
        /* 189 */ return this.commission;
    }

    public void setCommission(Integer commission) {
        /* 193 */ this.commission = commission;
    }
}


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-war.war!\WEB-INF\classes\controllers\retraitcn\AbstractRetraitCnController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
