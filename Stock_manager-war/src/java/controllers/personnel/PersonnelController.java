package controllers.personnel;

import entities.Personnel;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import controllers.personnel.AbstractPersonnelController;
import utils.SessionMBean;
import utils.Utilitaires;

@ManagedBean
@ViewScoped
public class PersonnelController
        extends AbstractPersonnelController {

    @PostConstruct
    private void init() {
        /*  32 */ this.personnel = new Personnel();
    }

    public void prepareCreate() {
        try {
            /*  37 */ if (!Utilitaires.isAccess(Long.valueOf(7L))) {
                /*  38 */ notifyError("acces_refuse");

                return;
            }
            /*  42 */ this.mode = "Create";
            /*  43 */ this.personnel = new Personnel();
            /*  44 */ this.personnel.setEtat(Boolean.valueOf(true));
            /*  45 */ this.personnel.setPrenom("-");
            /*  46 */ this.personnel.setAdresse("-");
            /*  47 */ this.personnel.setNumeroCni("-");
            /*  48 */ this.personnel.setContact("-");

            /*  50 */ RequestContext.getCurrentInstance().execute("PF('PersonnelCreerDialog').show()");
            /*  51 */        } catch (Exception e) {
            /*  52 */ notifyFail(e);
        }
    }

    public void prepareEdit() {
        try {
            /*  58 */ if (!Utilitaires.isAccess(Long.valueOf(8L))) {
                /*  59 */ notifyError("acces_refuse");

                return;
            }
            /*  63 */ if (this.personnel == null) {
                /*  64 */ notifyError("not_row_selected");

                return;
            }
            /*  68 */ this.mode = "Edit";

            /*  70 */ if (this.personnel.getIdprofession() != null) {
                /*  71 */ this.profession = this.personnel.getIdprofession();
            }
            /*  73 */ RequestContext.getCurrentInstance().execute("PF('PersonnelCreerDialog').show()");
            /*  74 */        } catch (Exception e) {
            /*  75 */ notifyFail(e);
        }
    }

    public void create() {
        try {
            /*  81 */ if (this.mode.equals("Create")) {

                /*  83 */ if (this.profession.getId() != null) {
                    /*  84 */ this.personnel.setIdprofession(this.profession);
                }

                /*  87 */ this.personnel.setIdpersonnel(this.personnelFacadeLocal.nextVal());
                /*  88 */ this.personnelFacadeLocal.create(this.personnel);

                /*  90 */ Utilitaires.saveOperation(this.mouchardFacadeLocal, "Enregistrement du personnel : " + this.personnel.getNom() + " " + this.personnel.getPrenom(), SessionMBean.getUserAccount());
                /*  91 */ this.personnel = null;
                /*  92 */ notifySuccess();
            } /*  94 */ else if (this.personnel != null) {
                /*  95 */ this.personnelFacadeLocal.edit(this.personnel);
                /*  96 */ notifySuccess();
            } else {
                /*  98 */ notifyError("not_row_selected");
            }

            /* 101 */        } catch (Exception e) {
            /* 102 */ notifyFail(e);
        }
    }

    public void delete() {
        try {
            /* 109 */ if (this.personnel != null) {

                /* 111 */ if (!Utilitaires.isAccess(Long.valueOf(9L))) {
                    /* 112 */ notifyError("acces_refuse");

                    return;
                }
                /* 116 */ this.personnelFacadeLocal.remove(this.personnel);
                /* 117 */ this.personnel = null;
                /* 118 */ Utilitaires.saveOperation(this.mouchardFacadeLocal, "Suppression du personnel : " + this.personnel.getNom() + " " + this.personnel.getPrenom(), SessionMBean.getUserAccount());
                /* 119 */ notifySuccess();
            } else {
                /* 121 */ notifyError("not_row_selected");
            }
            /* 123 */        } catch (Exception e) {
            /* 124 */ notifyFail(e);
        }
    }

    public void notifyError(String message) {
        /* 129 */ RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').hide()");
        /* 130 */ this.routine.feedBack("avertissement", "/resources/tool_images/warning.jpeg", this.routine.localizeMessage(message));
        /* 131 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
    }

    public void notifySuccess() {
        /* 135 */ RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').hide()");
        /* 136 */ this.routine.feedBack("information", "/resources/tool_images/success.png", this.routine.localizeMessage("operation_reussie"));
        /* 137 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
    }

    public void notifyFail(Exception e) {
        /* 141 */ RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').hide()");
        /* 142 */ this.routine.catchException(e, this.routine.localizeMessage("echec_operation"));
        /* 143 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
    }
}


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-war.war!\WEB-INF\classes\personnel\PersonnelController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
