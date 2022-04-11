package controllers.alerte;

import controllers.alerte.AbstractAlerteController;
import entities.Commande;
import entities.CommandeClient;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.context.RequestContext;
import utils.Utilitaires;

@ManagedBean
@SessionScoped
public class AlerteController
        extends AbstractAlerteController
        implements Serializable {

    @PostConstruct
    private void init() {
    }

    public void updateMois() {
    }

    public void prepareCreate() {
        try {
            /*  44 */ if (!Utilitaires.isAccess(Long.valueOf(52L))) {
                /*  45 */ notifyError("acces_refuse");
                return;
            }
            /*  48 */ RequestContext.getCurrentInstance().execute("PF('CommandeCreateDialog').show()");
            /*  49 */ this.mode = "Create";

            /*  51 */ this.commandeClient = new CommandeClient();
            /*  52 */ this.ligneCommandeClients.clear();
            /*  53 */ this.showSelectorCommand = Boolean.valueOf(false);
        } /*  55 */ catch (Exception e) {
            /*  56 */ this.routine.catchException(e, this.routine.localizeMessage("echec_operation"));
            /*  57 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
        }
    }

    public void prepareCreateCommande() {
        try {
            /*  63 */ this.commandeClient = new CommandeClient();
            /*  64 */ this.commandeClients = this.commandeClientFacadeLocal.findByLivre(false);
            /*  65 */ RequestContext.getCurrentInstance().execute("PF('ArticleCreateDialog').show()");
            /*  66 */        } catch (Exception e) {
            /*  67 */ notifyFail(e);
        }
    }

    public void prepareEdit() {
        try {
            /*  74 */ if (Utilitaires.isDayClosed()) {
                /*  75 */ notifyError("journee_cloturee");

                return;
            }
            /*  79 */ this.showSelectorCommand = Boolean.valueOf(true);

            /*  81 */ if (this.commandeClient.getLivre().booleanValue()) {
                /*  82 */ notifyError("commande_deja_livree");

                return;
            }
            /*  86 */ if (!Utilitaires.isAccess(Long.valueOf(49L))) {
                /*  87 */ notifyError("acces_refuse");
                /*  88 */ this.commandeClient = null;

                return;
            }
            /*  92 */ this.mode = "Edit";

            /*  94 */ this.ligneCommandeClients = this.ligneCommandeClientFacadeLocal.findByCommande(this.commandeClient.getIdCommandeClient());

            /*  96 */ RequestContext.getCurrentInstance().execute("PF('CommandeCreateDialog').show()");
        } /*  98 */ catch (Exception e) {
            /*  99 */ notifyFail(e);
        }
    }

    public void prepareview() {
        try {
            /* 105 */ if (this.commandeClient != null) {

                /* 111 */ notifyError("liste_article_vide");
            } else {
                /* 113 */ notifyError("not_row_selected");
            }
            /* 115 */        } catch (Exception e) {
            /* 116 */ notifyFail(e);
        }
    }

    public void selectCommande() {
    }

    public void filterMois() {
    }

    public void create() {
    }

    public Double calculTotal(List<Commande> commandes) {
        /* 141 */ Double resultat = Double.valueOf(0.0D);
        /* 142 */ for (Commande c : commandes) {
            /* 143 */ resultat = Double.valueOf(resultat.doubleValue() + c.getMontant().doubleValue() * c.getQuantite().doubleValue());
        }
        /* 145 */ return resultat;
    }

    public void updateTotaux() {
    }

    public void notifyError(String message) {
        /* 153 */ this.routine.feedBack("avertissement", "/resources/tool_images/warning.jpeg", this.routine.localizeMessage(message));
        /* 154 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
    }

    public void notifySuccess() {
        /* 158 */ RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').hide()");
        /* 159 */ this.routine.feedBack("information", "/resources/tool_images/success.png", this.routine.localizeMessage("operation_reussie"));
        /* 160 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
    }

    public void notifyFail(Exception e) {
        /* 164 */ RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').hide()");
        /* 165 */ this.routine.catchException(e, this.routine.localizeMessage("echec_operation"));
        /* 166 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
    }
}


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-war.war!\WEB-INF\classes\controllers\alerte\AlerteController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
