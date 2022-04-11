package controllers.client;

import controllers.client.AbstractClientController;
import entities.Client;
import entities.District;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import utils.PrintUtils;
import utils.SessionMBean;
import utils.Utilitaires;

@ManagedBean
@ViewScoped
public class ClientController
        extends AbstractClientController
        implements Serializable {

    @PostConstruct
    private void init() {
        /*  35 */ this.client = new Client();
    }

    public void prepareCreate() {
        /*  39 */ if (!Utilitaires.isAccess(Long.valueOf(7L))) {
            /*  40 */ notifyError("acces_refuse");

            return;
        }
        /*  44 */ this.mode = "Create";
        /*  45 */ this.client = new Client();
        /*  46 */ this.client.setCode("CL_" + this.clientFacadeLocal.nextVal());
        /*  47 */ this.client.setSolde(Integer.valueOf(0));
        /*  48 */ this.client.setPrenom("-");
        /*  49 */ this.client.setContact("-");
        /*  50 */ this.client.setContact2("-");
        /*  51 */ this.client.setEmail("-");
        /*  52 */ this.client.setAdresse("-");

        /*  54 */ this.district = new District(Integer.valueOf(1));
        /*  55 */ RequestContext.getCurrentInstance().execute("PF('ClientCreerDialog').show()");
    }

    public void prepareEdit() {
        /*  59 */ if (!Utilitaires.isAccess(Long.valueOf(8L))) {
            /*  60 */ notifyError("acces_refuse");

            return;
        }
        /*  64 */ if (this.client != null) {
            /*  65 */ this.mode = "Edit";
            /*  66 */ this.district = this.client.getIddistrict();
            /*  67 */ RequestContext.getCurrentInstance().execute("PF('ClientCreerDialog').show()");
        }
        /*  69 */ notifyError("not_row_selected");
    }

    public void create() {
        try {
            /*  74 */ if (this.mode.equals("Create")) {
                /*  75 */ this.client.setIdclient(this.clientFacadeLocal.nextVal());
                /*  76 */ this.client.setIddistrict(new District(Integer.valueOf(1)));
                /*  77 */ this.clientFacadeLocal.create(this.client);

                /*  79 */ Utilitaires.saveOperation(this.mouchardFacadeLocal, "Enregistrement du client : " + this.client.getNom() + " " + this.client.getPrenom(), SessionMBean.getUserAccount());
                /*  80 */ this.client = null;
                /*  81 */ RequestContext.getCurrentInstance().execute("PF('ClientCreerDialog').hide()");
                /*  82 */ notifySuccess();
            } /*  84 */ else if (this.client != null) {

                /*  86 */ this.clientFacadeLocal.edit(this.client);
                /*  87 */ RequestContext.getCurrentInstance().execute("PF('ClientCreerDialog').hide()");
                /*  88 */ notifySuccess();
            } else {
                /*  90 */ notifyError("not_row_selected");
            }

            /*  93 */        } catch (Exception e) {
            /*  94 */ notifyFail(e);
        }
    }

    public void delete() {
        try {
            /* 100 */ if (this.client != null) {
                /* 101 */ if (!Utilitaires.isAccess(Long.valueOf(9L))) {
                    /* 102 */ notifyError("acces_refuse");

                    return;
                }
                /* 106 */ this.clientFacadeLocal.remove(this.client);
                /* 107 */ Utilitaires.saveOperation(this.mouchardFacadeLocal, "Suppression du client : " + this.client.getNom() + " ; Code : " + this.client.getCode(), SessionMBean.getUserAccount());
                /* 108 */ this.client = null;
                /* 109 */ notifySuccess();
            } else {
                /* 111 */ notifyError("not_row_selected");
            }
            /* 113 */        } catch (Exception e) {
            /* 114 */ notifyFail(e);
        }
    }

    public void print() {
        try {
            /* 120 */ if (!Utilitaires.isAccess(Long.valueOf(9L))) {
                /* 121 */ notifyError("acces_refuse");
                return;
            }
            /* 124 */ this.fileName = PrintUtils.printCustomerList(this.clients);
            /* 125 */ RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').hide()");
            /* 126 */ RequestContext.getCurrentInstance().execute("PF('ClientImprimerDialog').show()");
            /* 127 */        } catch (Exception e) {
            /* 128 */ notifyFail(e);
        }
    }

    public void notifyError(String message) {
        /* 133 */ RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').hide()");
        /* 134 */ this.routine.feedBack("avertissement", "/resources/tool_images/success.png", this.routine.localizeMessage(message));
        /* 135 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
    }

    public void notifySuccess() {
        /* 139 */ RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').hide()");
        /* 140 */ this.routine.feedBack("information", "/resources/tool_images/success.png", this.routine.localizeMessage("operation_reussie"));
        /* 141 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
    }

    public void notifyFail(Exception e) {
        /* 145 */ RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').show()");
        /* 146 */ this.routine.catchException(e, this.routine.localizeMessage("echec_operation"));
        /* 147 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
    }
}


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-war.war!\WEB-INF\classes\controllers\client\ClientController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
