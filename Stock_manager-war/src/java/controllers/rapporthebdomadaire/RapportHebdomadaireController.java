package controllers.rapporthebdomadaire;

import controllers.rapporthebdomadaire.AbstractRapportHebdomaireController;
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
public class RapportHebdomadaireController
        extends AbstractRapportHebdomaireController
        implements Serializable {

    @PostConstruct
    private void init() {
        /*  34 */ this.commandes.clear();
    }

    public void find() {
        /*  38 */ this.commandes.clear();
        /*  39 */ this.showPrintButton = true;
        try {
            /*  41 */ if (this.anneeMois.getIdAnneeMois() != null) {
                /*  42 */ this.anneeMois = this.anneeMoisFacadeLocal.find(this.anneeMois.getIdAnneeMois());
                /*  43 */ this.commandes = this.commandeFacadeLocal.findByMois(this.anneeMois);
                /*  44 */ if (!this.commandes.isEmpty()) {
                    /*  45 */ this.showPrintButton = false;
                    return;
                }
                /*  48 */ this.routine.feedBack("information", "/resources/tool_images/error.png", this.routine.localizeMessage("aucune_donnee_trouvee"));
                /*  49 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
            }

            /*  52 */        } catch (Exception e) {
            /*  53 */ this.routine.catchException(e, this.routine.localizeMessage("echec_operation"));
            /*  54 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
        }
    }

    public void printReport() {
        try {
            /*  60 */ if (!Utilitaires.isAccess(Long.valueOf(39L))) {
                /*  61 */ RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').hide()");
                /*  62 */ this.routine.feedBack("information", "/resources/tool_images/error.png", this.routine.localizeMessage("acces_refuse"));
                /*  63 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
                return;
            }
            /*  66 */ RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').hide()");
            /*  67 */ this.fileName = PrintUtils.printWeeklySaleReport(this.anneeMois, this.commandes);
            /*  68 */ RequestContext.getCurrentInstance().execute("PF('ReportMImprimerDialog').show()");
            /*  69 */        } catch (Exception e) {
            /*  70 */ RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').hide()");
            /*  71 */ this.routine.catchException(e, this.routine.localizeMessage("echec_operation"));
            /*  72 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
        }
    }

    public String calculSommeMontant() {
        /*  77 */ if (this.commandes.isEmpty()) {
            /*  78 */ return "";
        }
        /*  80 */ Double resultat = Double.valueOf(0.0D);
        /*  81 */ for (Commande c : this.commandes) {
            /*  82 */ resultat = Double.valueOf(resultat.doubleValue() + c.getMontant().doubleValue() * c.getQuantite().doubleValue());
        }
        /*  84 */ return JsfUtil.formaterStringMoney(resultat);
    }

    public String calculSommeQuantite() {
        /*  88 */ if (this.commandes.isEmpty()) {
            /*  89 */ return "";
        }
        /*  91 */ Double resultat = Double.valueOf(0.0D);
        /*  92 */ for (Commande c : this.commandes) {
            /*  93 */ resultat = Double.valueOf(resultat.doubleValue() + c.getQuantite().doubleValue());
        }
        /*  95 */ return resultat.toString();
    }

    public void filterMois() {
        try {
            /* 100 */ this.anneeMoises.clear();
            /* 101 */ if (this.annee.getIdannee() != null) {
                /* 102 */ this.anneeMoises = this.anneeMoisFacadeLocal.findByAnneeEtat(this.annee, true);
            }
            /* 104 */        } catch (Exception e) {
            /* 105 */ e.printStackTrace();
        }
    }
}


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-war.war!\WEB-INF\classes\controllers\rapporthebdomadaire\RapportHebdomadaireController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
