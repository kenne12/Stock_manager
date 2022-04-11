package controllers.rapportjournalier;

import controllers.rapportjournalier.AbstractRapportJournalierController;
import entities.Commande;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import utils.JsfUtil;
import utils.PrintUtils;
import utils.Utilitaires;

@ManagedBean
@ViewScoped
public class RapportJournalierController
        extends AbstractRapportJournalierController
        implements Serializable {

    @PostConstruct
    private void init() {
        /* 34 */ this.commandes.clear();
    }

    public void find() {
        /* 38 */ this.commandes.clear();
        /* 39 */ this.showPrintButton = true;
        try {
            /* 41 */ if (!this.date.equals(null)) {
                /* 42 */ this.commandes = this.commandeFacadeLocal.findByDate(this.date);
                /* 43 */ if (!this.commandes.isEmpty()) {
                    /* 44 */ this.showPrintButton = false;
                    return;
                }
                /* 47 */ this.routine.feedBack("information", "/resources/tool_images/error.png", this.routine.localizeMessage("aucune_donnee_trouvee"));
                /* 48 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
            }
            /* 50 */        } catch (Exception e) {
            /* 51 */ this.routine.catchException(e, this.routine.localizeMessage("echec_operation"));
            /* 52 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
        }
    }

    public void printReport() {
        try {
            /* 58 */ if (!Utilitaires.isAccess(Long.valueOf(38L))) {
                /* 59 */ RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').hide()");
                /* 60 */ this.routine.feedBack("information", "/resources/tool_images/error.png", this.routine.localizeMessage("acces_refuse"));
                /* 61 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
                return;
            }
            /* 64 */ RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').hide()");
            /* 65 */ this.fileName = PrintUtils.printDailySaleReport(this.date, this.commandes);
            /* 66 */ RequestContext.getCurrentInstance().execute("PF('ReportJImprimerDialog').show()");
            /* 67 */        } catch (Exception e) {
            /* 68 */ RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').hide()");
            /* 69 */ this.routine.catchException(e, this.routine.localizeMessage("echec_operation"));
            /* 70 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
        }
    }

    public String calculSommeMontant() {
        /* 75 */ if (this.commandes.isEmpty()) {
            /* 76 */ return "";
        }
        /* 78 */ Double resultat = Double.valueOf(0.0D);
        /* 79 */ for (Commande c : this.commandes) {
            /* 80 */ resultat = Double.valueOf(resultat.doubleValue() + c.getMontant().doubleValue() * c.getQuantite().doubleValue());
        }
        /* 82 */ return JsfUtil.formaterStringMoney(resultat);
    }

    public String calculSommeQuantite() {
        /* 86 */ if (this.commandes.isEmpty()) {
            /* 87 */ return "";
        }
        /* 89 */ Double resultat = Double.valueOf(0.0D);
        /* 90 */ for (Commande c : this.commandes) {
            /* 91 */ resultat = Double.valueOf(resultat.doubleValue() + c.getQuantite().doubleValue());
        }
        /* 93 */ return resultat.toString();
    }
}


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-war.war!\WEB-INF\classes\controllers\rapportjournalier\RapportJournalierController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
