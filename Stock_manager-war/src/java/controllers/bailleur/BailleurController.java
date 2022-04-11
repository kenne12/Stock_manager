package controllers.bailleur;

import controllers.bailleur.AbstractBailleurController;
import entities.Bailleur;
import entities.District;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import utils.SessionMBean;
import utils.Utilitaires;

@ManagedBean
@ViewScoped
public class BailleurController
        extends AbstractBailleurController
        implements Serializable {

    @PostConstruct
    private void init() {
        /*  34 */ this.bailleur = new Bailleur();
    }

    public void prepareCreate() {
        /*  38 */ if (!Utilitaires.isAccess(Long.valueOf(7L))) {
            /*  39 */ notifyError("acces_refuse");

            return;
        }
        /*  43 */ this.mode = "Create";
        /*  44 */ this.bailleur = new Bailleur();

        /*  46 */ this.district = new District();
        /*  47 */ RequestContext.getCurrentInstance().execute("PF('BailleurCreerDialog').show()");
    }

    public void prepareEdit() {
        /*  51 */ if (!Utilitaires.isAccess(Long.valueOf(8L))) {
            /*  52 */ notifyError("acces_refuse");

            return;
        }
        /*  56 */ if (this.bailleur != null) {
            /*  57 */ this.mode = "Edit";
            /*  58 */ RequestContext.getCurrentInstance().execute("PF('BailleurCreerDialog').show()");
            return;
        }
        /*  61 */ notifyError("not_row_selected");
    }

    public void create() {
        try {
            /*  66 */ if (this.mode.equals("Create")) {
                /*  67 */ this.bailleur.setIdbailleur(this.bailleurFacadeLocal.nextVal());
                /*  68 */ this.bailleurFacadeLocal.create(this.bailleur);

                /*  70 */ Utilitaires.saveOperation(this.mouchardFacadeLocal, "Enregistrement du bailleur : " + this.bailleur.getNom(), SessionMBean.getUserAccount());
                /*  71 */ this.bailleur = null;
                /*  72 */ RequestContext.getCurrentInstance().execute("PF('BailleurCreerDialog').hide()");
                /*  73 */ notifySuccess();
            } /*  75 */ else if (this.bailleur != null) {
                /*  76 */ this.bailleurFacadeLocal.edit(this.bailleur);
                /*  77 */ RequestContext.getCurrentInstance().execute("PF('BailleurCreerDialog').hide()");
                /*  78 */ notifySuccess();
            } else {
                /*  80 */ notifyError("not_row_selected");
            }

            /*  83 */        } catch (Exception e) {
            /*  84 */ notifyFail(e);
        }
    }

    public void delete() {
        try {
            /*  90 */ if (this.bailleur != null) {
                /*  91 */ if (!Utilitaires.isAccess(Long.valueOf(9L))) {
                    /*  92 */ notifyError("acces_refuse");

                    return;
                }
                /*  96 */ this.bailleurFacadeLocal.remove(this.bailleur);
                /*  97 */ Utilitaires.saveOperation(this.mouchardFacadeLocal, "Suppression du bailleur : " + this.bailleur.getNom(), SessionMBean.getUserAccount());
                /*  98 */ this.bailleur = null;
                /*  99 */ notifySuccess();
            } else {
                /* 101 */ notifyError("not_row_selected");
            }
            /* 103 */        } catch (Exception e) {
            /* 104 */ notifyFail(e);
        }
    }

    public void print() {
        try {
            /* 110 */ if (!Utilitaires.isAccess(Long.valueOf(9L))) {
                /* 111 */ notifyError("acces_refuse");

                return;
            }
            /* 115 */ RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').hide()");
            /* 116 */ RequestContext.getCurrentInstance().execute("PF('BailleurImprimerDialog').show()");
            /* 117 */        } catch (Exception e) {
            /* 118 */ notifyFail(e);
        }
    }

    public void notifyError(String message) {
        /* 123 */ RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').hide()");
        /* 124 */ this.routine.feedBack("avertissement", "/resources/tool_images/success.png", this.routine.localizeMessage(message));
        /* 125 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
    }

    public void notifySuccess() {
        /* 129 */ RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').hide()");
        /* 130 */ this.routine.feedBack("information", "/resources/tool_images/success.png", this.routine.localizeMessage("operation_reussie"));
        /* 131 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
    }

    public void notifyFail(Exception e) {
        /* 135 */ RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').show()");
        /* 136 */ this.routine.catchException(e, this.routine.localizeMessage("echec_operation"));
        /* 137 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
    }
}


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-war.war!\WEB-INF\classes\controllers\bailleur\BailleurController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
