package controllers.versement;

import entities.Facture;
import entities.Versement;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.transaction.UserTransaction;
import sessions.FactureFacadeLocal;
import sessions.MouchardFacadeLocal;
import sessions.VersementFacadeLocal;
import utils.Routine;
import utils.SessionMBean;

public class AbstractVersementController {

    @Resource
    protected UserTransaction ut;
    @EJB
    protected VersementFacadeLocal versementFacadeLocal;
    protected Versement versement;
    protected List<Versement> versements = new ArrayList<>();

    @EJB
    protected FactureFacadeLocal factureFacadeLocal;
    protected Facture facture = new Facture();
    protected List<Facture> factures = new ArrayList<>();

    @EJB
    protected MouchardFacadeLocal mouchardFacadeLocal;

    protected Routine routine = new Routine();

    protected Boolean modifier = true;
    protected Boolean supprimer = true;

    protected Boolean showClient = true;

    protected String mode = "";
    protected String fileName = "";

    public Boolean getModifier() {
        return this.modifier;
    }

    public void setModifier(Boolean modifier) {
        this.modifier = modifier;
    }

    public Boolean getSupprimer() {
        return this.supprimer;
    }

    public void setSupprimer(Boolean supprimer) {
        this.supprimer = supprimer;
    }

    public Versement getVersement() {
        return this.versement;
    }

    public void setVersement(Versement versement) {
        this.versement = versement;
        this.supprimer = this.modifier = (versement == null);
    }

    public List<Versement> getVersements() {
        this.versements = this.versementFacadeLocal.findAllRange(SessionMBean.getMois().getIdannee().getIdannee());
        return this.versements;
    }

    public void setVersements(List<Versement> versements) {
        this.versements = versements;
    }

    public Boolean getShowClient() {
        return this.showClient;
    }

    public void setShowClient(Boolean showClient) {
        this.showClient = showClient;
    }

    public Facture getFacture() {
        return this.facture;
    }

    public void setFacture(Facture facture) {
        this.facture = facture;
    }

    public List<Facture> getFactures() {
        return this.factures;
    }

    public void setFactures(List<Facture> factures) {
        this.factures = factures;
    }

    public Routine getRoutine() {
        return this.routine;
    }

    public String getFileName() {
        return this.fileName;
    }
}
