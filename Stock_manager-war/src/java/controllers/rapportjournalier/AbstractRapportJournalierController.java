package controllers.rapportjournalier;

import entities.Commande;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import sessions.CommandeFacadeLocal;
import sessions.PrivilegeFacadeLocal;
import utils.Routine;

public class AbstractRapportJournalierController {

    protected Routine routine = new Routine();

    @EJB
    protected CommandeFacadeLocal commandeFacadeLocal;
    protected List<Commande> commandes = new ArrayList<>();

    @EJB
    protected PrivilegeFacadeLocal privilegeFacadeLocal;

    Date date = new Date();

    protected String fileName = "";

    protected boolean showPrintButton = true;
    protected boolean showReportPrintDialog = false;

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isShowPrintButton() {
        return this.showPrintButton;
    }

    public void setShowPrintButton(boolean showPrintButton) {
        this.showPrintButton = showPrintButton;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public boolean isShowReportPrintDialog() {
        return this.showReportPrintDialog;
    }

    public void setShowReportPrintDialog(boolean showReportPrintDialog) {
        this.showReportPrintDialog = showReportPrintDialog;
    }

    public List<Commande> getCommandes() {
        return this.commandes;
    }

    public void setCommandes(List<Commande> commandes) {
        this.commandes = commandes;
    }

    public Routine getRoutine() {
        return this.routine;
    }
}
