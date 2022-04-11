package controllers.depense;

import controllers.depense.AbstractDepenseController;
import entities.Depense;
import entities.Privilege;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import utils.JsfUtil;
import utils.SessionMBean;
import utils.Utilitaires;

@ManagedBean
@ViewScoped
public class DepenseController
        extends AbstractDepenseController {

    @PostConstruct
    private void init() {
        /*  34 */ this.depense = new Depense();
    }

    public void prepareCreate() {
        /*  38 */ this.depense = new Depense();
        /*  39 */ this.depense.setMontant(Double.valueOf(0.0D));
        /*  40 */ this.depense.setEtat(Boolean.valueOf(false));
        /*  41 */ this.depense.setDateDepense(new Date());

        /*  43 */ this.mode = "Create";
        /*  44 */ this.showEditSolde = Boolean.valueOf(true);
        /*  45 */ this.showMontantCarnet = true;
        /*  46 */ this.showMontantCarnetCompnent = true;
        try {
            /*  48 */ Privilege p = this.privilegeFacadeLocal.findByUser(SessionMBean.getUserAccount().getIdutilisateur().intValue(), 1);
            /*  49 */ if (p != null) {
                /*  50 */ this.showDepenseCreateDialog = true;
            } else {
                /*  52 */ p = new Privilege();
                /*  53 */ p = this.privilegeFacadeLocal.findByUser(SessionMBean.getUserAccount().getIdutilisateur().intValue(), 7);
                /*  54 */ if (p != null) {
                    /*  55 */ this.showDepenseCreateDialog = true;
                } else {
                    /*  57 */ JsfUtil.addErrorMessage("Vous n 'avez pas le privilege d'enregistrer un depense");
                }
            }
            /*  60 */        } catch (Exception e) {
            /*  61 */ e.printStackTrace();
        }
    }

    public void prepareEdit() {
        /*  67 */ this.mode = "Edit";
        /*  68 */ this.showMontantCarnet = false;
        /*  69 */ this.showMontantCarnetCompnent = false;

        try {
            /*  72 */ Privilege p = this.privilegeFacadeLocal.findByUser(SessionMBean.getUserAccount().getIdutilisateur().intValue(), 1);
            /*  73 */ if (this.depense.getIdmois() != null) {
                /*  74 */ this.mois = this.depense.getIdmois();
            }
            /*  76 */ if (p != null) {
                /*  77 */ this.showDepenseCreateDialog = true;
                return;
            }
            /*  80 */ p = new Privilege();
            /*  81 */ p = this.privilegeFacadeLocal.findByUser(SessionMBean.getUserAccount().getIdutilisateur().intValue(), 8);
            /*  82 */ if (p != null) {
                /*  83 */ this.showDepenseCreateDialog = true;
                return;
            }
            /*  86 */ JsfUtil.addErrorMessage("Vous n'avez pas le privilege de modifier ce depense");

        } /*  90 */ catch (Exception e) {
            /*  91 */ e.printStackTrace();
        }
    }

    public void create() {
        try {
            /*  98 */ if (this.mode.equals("Create")) {

                /* 100 */ if (this.mois.getIdAnneeMois() != null) {
                    /* 101 */ this.mois = this.moisFacadeLocal.find(this.mois.getIdAnneeMois());
                    /* 102 */ this.depense.setIdmois(this.mois);
                }

                /* 105 */ this.depense.setIddepense(this.depenseFacadeLocal.nextVal());
                /* 106 */ this.depenseFacadeLocal.create(this.depense);

                /* 108 */ Utilitaires.saveOperation(this.mouchardFacadeLocal, "Enregistrement de la depense : " + this.depense.getMotif() + " -> " + this.depense.getMontant() + " ->" + this.mois.getIdmois().getNom(), SessionMBean.getUserAccount());
                /* 109 */ JsfUtil.addSuccessMessage("Depense enregistré avec succès");

            } /* 112 */ else if (this.depense != null) {

                /* 114 */ this.depenseFacadeLocal.edit(this.depense);
                /* 115 */ JsfUtil.addSuccessMessage("Opération réussie");
            } else {
                /* 117 */ JsfUtil.addErrorMessage("Aucun depense selectionné");
            }

            /* 120 */        } catch (Exception e) {
            /* 121 */ e.printStackTrace();
        }
    }

    public void edit() {
    }

    public void delete() {
        try {
            /* 131 */ if (this.depense != null) {

                /* 133 */ Privilege p = this.privilegeFacadeLocal.findByUser(SessionMBean.getUserAccount().getIdutilisateur().intValue(), 1);
                /* 134 */ if (p != null) {
                    /* 135 */ this.showDepenseDeleteDialog = true;
                } else {
                    /* 137 */ p = new Privilege();
                    /* 138 */ p = this.privilegeFacadeLocal.findByUser(SessionMBean.getUserAccount().getIdutilisateur().intValue(), 9);
                    /* 139 */ if (p != null) {
                        /* 140 */ this.showDepenseDeleteDialog = true;
                    } else {
                        /* 142 */ this.showDepenseDeleteDialog = false;
                        /* 143 */ JsfUtil.addErrorMessage("Vous n'avez pas le privilège de supprimer ce  depense");

                        return;
                    }
                }
                /* 148 */ this.depenseFacadeLocal.remove(this.depense);

                /* 150 */ Utilitaires.saveOperation(this.mouchardFacadeLocal, "Suppression de la depense : " + this.depense.getMotif() + " -> " + this.depense.getMontant() + " -> " + this.depense.getIdmois().getIdmois().getNom(), SessionMBean.getUserAccount());
                /* 151 */ JsfUtil.addSuccessMessage("Operation réussie");
            } else {
                /* 153 */ JsfUtil.addErrorMessage("Aucun depense selectionnée");
            }
            /* 155 */        } catch (Exception e) {
            /* 156 */ e.printStackTrace();
        }
    }

    public void print() {
        try {
            /* 162 */ Privilege p = this.privilegeFacadeLocal.findByUser(SessionMBean.getUserAccount().getIdutilisateur().intValue(), 1);
            /* 163 */ if (p != null) {
                /* 164 */ this.showDepensePrintDialog = Boolean.valueOf(true);
            } else {
                /* 166 */ p = new Privilege();
                /* 167 */ p = this.privilegeFacadeLocal.findByUser(SessionMBean.getUserAccount().getIdutilisateur().intValue(), 20);
                /* 168 */ if (p != null) {
                    /* 169 */ this.showDepensePrintDialog = Boolean.valueOf(true);
                } else {
                    /* 171 */ this.showDepensePrintDialog = Boolean.valueOf(false);
                    /* 172 */ JsfUtil.addErrorMessage("Vous n 'avez pas le privilège d'éditer la liste des depenses");

                    return;
                }
            }
            /* 177 */ System.err.println("" + this.fileName);
            /* 178 */        } catch (Exception e) {
            /* 179 */ e.printStackTrace();
        }
    }
}


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-war.war!\WEB-INF\classes\controllers\depense\DepenseController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
