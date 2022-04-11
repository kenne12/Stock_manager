package controllers.versement;

import controllers.versement.AbstractVersementController;
import entities.Facture;
import entities.Versement;
import java.io.Serializable;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import utils.PrintUtils;
import utils.SessionMBean;
import utils.Utilitaires;

@ManagedBean
@ViewScoped
public class VersementController
        extends AbstractVersementController
        implements Serializable {

    @PostConstruct
    private void init() {
        /*  36 */ this.facture = new Facture();
        /*  37 */ this.versement = new Versement();
        try {
            /*  39 */ this.factures = this.factureFacadeLocal.findNonregle();
            /*  40 */        } catch (Exception e) {
            /*  41 */ e.printStackTrace();
        }
    }

    public void prepareCreate() {
        try {
            /*  47 */ if (!Utilitaires.isAccess(Long.valueOf(48L))) {
                /*  48 */ notifyError("acces_refuse");

                return;
            }
            /*  52 */ this.mode = "Create";
            /*  53 */ this.facture = new Facture();
            /*  54 */ this.versement = new Versement();
            /*  55 */ this.versement.setDate(SessionMBean.getDateOuverture());
            /*  56 */ this.showClient = Boolean.valueOf(false);
            /*  57 */ RequestContext.getCurrentInstance().execute("PF('VersementCreerDialog').show()");
            /*  58 */        } catch (Exception e) {
            /*  59 */ notifyFail(e);
        }
    }

    public void prepareEdit() {
        try {
            /*  65 */ if (this.versement == null) {
                /*  66 */ notifyError("not_row_selected");

                return;
            }
            /*  70 */ if (!Utilitaires.isAccess(Long.valueOf(48L))) {
                /*  71 */ notifyError("acces_refuse");

                return;
            }
            /*  75 */ this.facture = this.versement.getIdfacture();
            /*  76 */ if (this.facture.getPaye().booleanValue() || this.facture.getReste().equals(Double.valueOf(0.0D))) {
                /*  77 */ this.factures.clear();
                /*  78 */ this.factures.add(this.facture);
            }
            /*  80 */ this.mode = "Edit";
            /*  81 */ this.showClient = Boolean.valueOf(true);
            /*  82 */ RequestContext.getCurrentInstance().execute("PF('VersementCreerDialog').show()");
            /*  83 */        } catch (Exception e) {
            /*  84 */ notifyFail(e);
        }
    }

    public void updateSolde() {
    }

    public void create() {
        try {
            /*  94 */ if (this.mode.equals("Create")) {

                /*  96 */ this.versement.setIdversement(this.versementFacadeLocal.nextVal());
                /*  97 */ this.versement.setHeure(new Date());
                /*  98 */ this.versement.setIdfacture(this.facture);

                /* 100 */ String code = "V-" + SessionMBean.getMois().getIdannee().getNom() + "-" + SessionMBean.getMois().getIdmois().getNom().toUpperCase().substring(0, 3);
                /* 101 */ Long nextPayement = this.versementFacadeLocal.nextVal(SessionMBean.getMois().getDateDebut(), SessionMBean.getMois().getDateFin());
                /* 102 */ code = Utilitaires.genererCodeStock(code, nextPayement);

                /* 104 */ this.ut.begin();

                /* 106 */ this.facture.setMontantPaye(Double.valueOf(this.facture.getMontantPaye().doubleValue() + this.versement.getMontant().doubleValue()));
                /* 107 */ this.facture.setReste(Double.valueOf(this.facture.getReste().doubleValue() - this.versement.getMontant().doubleValue()));
                /* 108 */ if (this.facture.getReste().equals(Double.valueOf(0.0D))) {
                    /* 109 */ this.facture.setPaye(Boolean.valueOf(true));
                }

                /* 112 */ this.versement.setReste(this.facture.getReste());
                /* 113 */ this.versement.setCode(code);
                /* 114 */ this.versementFacadeLocal.create(this.versement);

                /* 116 */ this.factureFacadeLocal.edit(this.facture);

                /* 118 */ Utilitaires.saveOperation(this.mouchardFacadeLocal, "Enregistrement du versement -> client : " + this.versement.getIdfacture().getIdclient().getNom() + " ; Montant : " + this.versement.getMontant() + " ; Code : " + this.versement.getCode(), SessionMBean.getUserAccount());
                /* 119 */ this.ut.commit();

                /* 121 */ this.versement = null;
                /* 122 */ this.facture = new Facture();
                /* 123 */ RequestContext.getCurrentInstance().execute("PF('VersementCreerDialog').hide()");
                /* 124 */ notifySuccess();
                /* 125 */ this.factures = this.factureFacadeLocal.findNonregle();
            } /* 127 */ else if (this.versement != null) {

                /* 129 */ this.ut.begin();
                /* 130 */ Versement v = this.versementFacadeLocal.find(this.versement.getIdversement());
                /* 131 */ if (!v.getMontant().equals(this.versement.getMontant())) {
                    /* 132 */ this.facture = v.getIdfacture();
                    /* 133 */ this.facture.setMontantPaye(Double.valueOf(this.facture.getMontantPaye().doubleValue() - v.getMontant().doubleValue() + this.versement.getMontant().doubleValue()));
                    /* 134 */ this.facture.setReste(Double.valueOf(this.facture.getReste().doubleValue() + v.getMontant().doubleValue() - this.versement.getMontant().doubleValue()));
                    /* 135 */ if (this.facture.getReste().equals(Double.valueOf(0.0D))) {
                        /* 136 */ this.facture.setPaye(Boolean.valueOf(true));
                    }
                    /* 138 */ this.factureFacadeLocal.edit(this.facture);
                    /* 139 */ Utilitaires.saveOperation(this.mouchardFacadeLocal, "Modification du versement -> du client : " + this.versement.getIdfacture().getIdclient().getNom() + " Ancien montant : " + this.versement.getMontant() + " ; Nouveau Montant : " + v.getMontant() + " ; Code : " + this.versement.getCode(), SessionMBean.getUserAccount());
                }
                /* 141 */ this.versement.setReste(this.facture.getReste());
                /* 142 */ this.versementFacadeLocal.edit(this.versement);
                /* 143 */ this.ut.commit();
                /* 144 */ this.versement = null;
                /* 145 */ this.facture = new Facture();
                /* 146 */ RequestContext.getCurrentInstance().execute("PF('VersementCreerDialog').hide()");
                /* 147 */ notifySuccess();
                /* 148 */ this.factures = this.factureFacadeLocal.findNonregle();
            } else {

                /* 151 */ notifyError("not_row_selected");
            }

            /* 154 */        } catch (Exception e) {
            /* 155 */ notifyFail(e);
        }
    }

    public void delete() {
        try {
            /* 161 */ if (this.versement != null) {
                /* 162 */ this.ut.begin();

                /* 164 */ this.versementFacadeLocal.remove(this.versement);

                /* 166 */ this.facture = this.versement.getIdfacture();
                /* 167 */ this.facture.setMontantPaye(Double.valueOf(this.facture.getMontantPaye().doubleValue() - this.versement.getMontant().doubleValue()));
                /* 168 */ this.facture.setReste(Double.valueOf(this.facture.getReste().doubleValue() + this.versement.getMontant().doubleValue()));
                /* 169 */ this.facture.setPaye(Boolean.valueOf(false));
                /* 170 */ if (this.facture.getReste().equals(Double.valueOf(0.0D))) {
                    /* 171 */ this.facture.setPaye(Boolean.valueOf(true));
                }
                /* 173 */ this.factureFacadeLocal.edit(this.facture);

                /* 175 */ Utilitaires.saveOperation(this.mouchardFacadeLocal, "Annulation du versement -> client : " + this.versement.getIdfacture().getIdclient().getNom() + " N° facture : " + this.versement.getIdfacture().getNumeroFacture() + " ; Montant : " + this.versement.getMontant() + " ; Code : " + this.versement.getCode(), SessionMBean.getUserAccount());
                /* 176 */ this.ut.commit();
                /* 177 */ notifySuccess();
                /* 178 */ this.versement = null;
                /* 179 */ this.facture = new Facture();
                /* 180 */ this.factures = this.factureFacadeLocal.findNonregle();
            } else {
                /* 182 */ notifyError("not_row_selected");
            }
            /* 184 */        } catch (Exception e) {
            /* 185 */ notifyFail(e);
        }
    }

    public void initPrinter(Versement versement) {
        /* 190 */ this.versement = versement;
        /* 191 */ print();
    }

    public void print() {
        try {
            /* 196 */ if (!Utilitaires.isAccess(Long.valueOf(26L))) {
                /* 197 */ notifyError("acces_refuse");
                /* 198 */ this.facture = null;

                return;
            }
            /* 202 */ if (this.versement != null) {
                /* 203 */ this.fileName = PrintUtils.printRecuVersement(this.versement, SessionMBean.getParametrage());
                /* 204 */ RequestContext.getCurrentInstance().execute("PF('VersementImprimerDialog').show()");
                return;
            }
            /* 207 */ notifyError("not_row_selected");
        } /* 209 */ catch (Exception e) {
            /* 210 */ notifyFail(e);
        }
    }

    public void notifyError(String message) {
        /* 215 */ this.routine.feedBack("avertissement", "/resources/tool_images/warning.jpeg", this.routine.localizeMessage(message));
        /* 216 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
    }

    public void notifySuccess() {
        /* 220 */ RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').hide()");
        /* 221 */ this.routine.feedBack("information", "/resources/tool_images/success.png", this.routine.localizeMessage("operation_reussie"));
        /* 222 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
    }

    public void notifyFail(Exception e) {
        /* 226 */ RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').hide()");
        /* 227 */ this.routine.catchException(e, this.routine.localizeMessage("echec_operation"));
        /* 228 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
    }
}


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-war.war!\WEB-INF\classes\controllers\versement\VersementController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
