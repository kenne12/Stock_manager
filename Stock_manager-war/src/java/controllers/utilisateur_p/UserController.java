package controllers.utilisateur_p;

import controllers.utilisateur_p.AbstractUserController;
import entities.District;
import entities.UserP;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import utils.SessionMBean;
import utils.Utilitaires;

@ManagedBean
@ViewScoped
public class UserController
        extends AbstractUserController
        implements Serializable {

    @PostConstruct
    private void init() {
        /*  34 */ this.userP = new UserP();
    }

    public void prepareCreate() {
        /*  38 */ if (!Utilitaires.isAccess(Long.valueOf(7L))) {
            /*  39 */ notifyError("acces_refuse");

            return;
        }
        /*  43 */ this.mode = "Create";
        /*  44 */ this.userP = new UserP();

        /*  46 */ this.district = new District();
        /*  47 */ RequestContext.getCurrentInstance().execute("PF('UserPCreerDialog').show()");
    }

    public void prepareEdit() {
        /*  51 */ if (!Utilitaires.isAccess(Long.valueOf(8L))) {
            /*  52 */ notifyError("acces_refuse");

            return;
        }
        /*  56 */ if (this.userP != null) {
            /*  57 */ this.mode = "Edit";
            /*  58 */ RequestContext.getCurrentInstance().execute("PF('UserPCreerDialog').show()");
            return;
        }
        /*  61 */ notifyError("not_row_selected");
    }

    public void create() {
        try {
            /*  66 */ if (this.mode.equals("Create")) {
                /*  67 */ this.userP.setIduserP(this.userPFacadeLocal.nextVal());
                /*  68 */ this.userPFacadeLocal.create(this.userP);

                /*  70 */ Utilitaires.saveOperation(this.mouchardFacadeLocal, "Enregistrement de l'utilisateur : " + this.userP.getNom(), SessionMBean.getUserAccount());
                /*  71 */ this.userP = null;
                /*  72 */ RequestContext.getCurrentInstance().execute("PF('UserPCreerDialog').hide()");
                /*  73 */ notifySuccess();
            } /*  75 */ else if (this.userP != null) {
                /*  76 */ this.userPFacadeLocal.edit(this.userP);
                /*  77 */ RequestContext.getCurrentInstance().execute("PF('UserPCreerDialog').hide()");
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
            /*  90 */ if (this.userP != null) {
                /*  91 */ if (!Utilitaires.isAccess(Long.valueOf(9L))) {
                    /*  92 */ notifyError("acces_refuse");

                    return;
                }
                /*  96 */ this.userPFacadeLocal.remove(this.userP);
                /*  97 */ Utilitaires.saveOperation(this.mouchardFacadeLocal, "Suppression de l'utilisateur : " + this.userP.getNom(), SessionMBean.getUserAccount());
                /*  98 */ this.userP = null;
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
            /* 114 */ RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').hide()");
            /* 115 */ RequestContext.getCurrentInstance().execute("PF('UserPImprimerDialog').show()");
            /* 116 */        } catch (Exception e) {
            /* 117 */ notifyFail(e);
        }
    }

    public void notifyError(String message) {
        /* 122 */ RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').hide()");
        /* 123 */ this.routine.feedBack("avertissement", "/resources/tool_images/success.png", this.routine.localizeMessage(message));
        /* 124 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
    }

    public void notifySuccess() {
        /* 128 */ RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').hide()");
        /* 129 */ this.routine.feedBack("information", "/resources/tool_images/success.png", this.routine.localizeMessage("operation_reussie"));
        /* 130 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
    }

    public void notifyFail(Exception e) {
        /* 134 */ RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').show()");
        /* 135 */ this.routine.catchException(e, this.routine.localizeMessage("echec_operation"));
        /* 136 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
    }
}


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-war.war!\WEB-INF\classes\controller\\utilisateur_p\UserController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
