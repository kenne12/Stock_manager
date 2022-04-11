package controllers.payement;

import controllers.payement.AbstractPayementController;
import entities.AnneeMois;
import entities.Personnel;
import entities.Privilege;
import entities.Salaire;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import utils.JsfUtil;
import utils.SessionMBean;
import utils.Utilitaires;

@ManagedBean
@ViewScoped
public class PayementController
        extends AbstractPayementController {

    @PostConstruct
    private void init() {
        /*  37 */ this.salaire = new Salaire();
        /*  38 */ this.personnel = new Personnel();
        /*  39 */ this.mois = new AnneeMois();
    }

    public void prepareCreate() {
        /*  43 */ this.salaire = new Salaire();
        /*  44 */ this.salaire.setMontant(Double.valueOf(0.0D));

        /*  46 */ this.salaire.setDateSalaire(new Date());
        /*  47 */ this.salaire.setObservation("-");
        /*  48 */ this.personnel = new Personnel();
        /*  49 */ this.mois = new AnneeMois();
        /*  50 */ this.message = "";
        /*  51 */ this.payed = Boolean.valueOf(false);

        /*  53 */ this.mode = "Create";
        /*  54 */ this.showEditSolde = Boolean.valueOf(true);
        /*  55 */ this.showMontantCarnet = true;
        /*  56 */ this.showMontantCarnetCompnent = true;
        try {
            /*  58 */ Privilege p = this.privilegeFacadeLocal.findByUser(SessionMBean.getUserAccount().getIdutilisateur().intValue(), 1);
            /*  59 */ if (p != null) {
                /*  60 */ this.showSalaireCreateDialog = true;
            } else {
                /*  62 */ p = new Privilege();
                /*  63 */ p = this.privilegeFacadeLocal.findByUser(SessionMBean.getUserAccount().getIdutilisateur().intValue(), 7);
                /*  64 */ if (p != null) {
                    /*  65 */ this.showSalaireCreateDialog = true;
                } else {
                    /*  67 */ JsfUtil.addErrorMessage("Vous n 'avez pas le privilege d'enregistrer un salaire");
                }
            }
            /*  70 */        } catch (Exception e) {
            /*  71 */ e.printStackTrace();
        }
    }

    public void prepareEdit() {
        /*  77 */ this.mode = "Edit";
        /*  78 */ this.showMontantCarnet = false;
        /*  79 */ this.showMontantCarnetCompnent = false;

        /*  81 */ if (this.salaire != null) {
            /*  82 */ this.personnel = this.salaire.getIdpersonnel();
            /*  83 */ this.mois = this.salaire.getIdmois();
        }

        try {
            /*  87 */ Privilege p = this.privilegeFacadeLocal.findByUser(SessionMBean.getUserAccount().getIdutilisateur().intValue(), 1);
            /*  88 */ if (this.salaire.getIdmois() != null) {
                /*  89 */ this.mois = this.salaire.getIdmois();
            }
            /*  91 */ if (p != null) {
                /*  92 */ this.showSalaireCreateDialog = true;
                return;
            }
            /*  95 */ p = new Privilege();
            /*  96 */ p = this.privilegeFacadeLocal.findByUser(SessionMBean.getUserAccount().getIdutilisateur().intValue(), 8);
            /*  97 */ if (p != null) {
                /*  98 */ this.showSalaireCreateDialog = true;
                return;
            }
            /* 101 */ JsfUtil.addErrorMessage("Vous n'avez pas le privilege de modifier ce salaire");

        } /* 104 */ catch (Exception e) {
            /* 105 */ e.printStackTrace();
        }
    }

    public void updatePaye() {
        try {
            /* 112 */ if (this.personnel.getIdpersonnel() != null) {
                /* 114 */ if (this.mois.getIdAnneeMois() != null) {
                    /* 115 */ this.personnel = this.personnelFacadeLocal.find(this.personnel.getIdpersonnel());

                    /* 117 */ this.mois = this.moisFacadeLocal.find(this.mois.getIdAnneeMois());

                    /* 119 */ this.payed = Boolean.valueOf(true);
                    /* 120 */ List<Salaire> s = this.salaireFacadeLocal.findByPersonnel(this.personnel, this.mois);
                    /* 121 */ if (!s.isEmpty()) {
                        /* 122 */ Double somme = Double.valueOf(0.0D);
                        /* 123 */ for (Salaire s1 : s) {
                            /* 124 */ somme = Double.valueOf(somme.doubleValue() + s1.getMontant().doubleValue());
                        }
                        /* 126 */ this.message = "Payement effectué : " + somme + " Fcfa";
                    } else {
                        /* 128 */ this.message = "Pas encore payé";
                    }
                }
            }
            /* 132 */        } catch (Exception e) {
            /* 133 */ e.printStackTrace();
        }
    }

    public void create() {
        try {
            /* 139 */ if (this.mode.equals("Create")) {

                /* 141 */ if (this.mois.getIdAnneeMois() != null) {
                    /* 142 */ this.salaire.setIdmois(this.mois);
                }

                /* 145 */ if (this.personnel.getIdpersonnel() != null) {
                    /* 146 */ this.salaire.setIdpersonnel(this.personnel);
                }

                /* 149 */ this.salaire.setIdsalaire(this.salaireFacadeLocal.nextVal());
                /* 150 */ this.salaireFacadeLocal.create(this.salaire);

                /* 152 */ Utilitaires.saveOperation(this.mouchardFacadeLocal, "Enregistrement du salaire de  : " + this.personnel.getNom() + " " + this.personnel.getPrenom() + " -> " + this.salaire.getMontant() + " ->" + this.mois.getIdmois().getNom() + " " + this.mois.getIdannee().getNom(), SessionMBean.getUserAccount());
                /* 153 */ JsfUtil.addSuccessMessage("Salaire enregistré avec succès");

            } /* 156 */ else if (this.salaire != null) {
                /* 157 */ this.salaireFacadeLocal.edit(this.salaire);
                /* 158 */ JsfUtil.addSuccessMessage("Opération réussie");
            } else {
                /* 160 */ JsfUtil.addErrorMessage("Aucun salaire selectionné");
            }

            /* 163 */        } catch (Exception e) {
            /* 164 */ e.printStackTrace();
        }
    }

    public void edit() {
    }

    public void delete() {
        try {
            /* 174 */ if (this.salaire != null) {

                /* 176 */ Privilege p = this.privilegeFacadeLocal.findByUser(SessionMBean.getUserAccount().getIdutilisateur().intValue(), 1);
                /* 177 */ if (p != null) {
                    /* 178 */ this.showSalaireDeleteDialog = true;
                } else {
                    /* 180 */ p = new Privilege();
                    /* 181 */ p = this.privilegeFacadeLocal.findByUser(SessionMBean.getUserAccount().getIdutilisateur().intValue(), 9);
                    /* 182 */ if (p != null) {
                        /* 183 */ this.showSalaireDeleteDialog = true;
                    } else {
                        /* 185 */ this.showSalaireDeleteDialog = false;
                        /* 186 */ JsfUtil.addErrorMessage("Vous n'avez pas le privilège de supprimer ce  salaire");

                        return;
                    }
                }
                /* 191 */ this.salaireFacadeLocal.remove(this.salaire);

                /* 193 */ Utilitaires.saveOperation(this.mouchardFacadeLocal, "Suppression du salaire : " + this.salaire.getIdpersonnel().getNom() + " " + this.salaire.getIdpersonnel().getPrenom() + " -> " + this.salaire.getMontant() + " -> " + this.salaire.getIdmois().getIdmois().getNom() + " " + this.salaire.getIdmois().getIdannee().getNom(), SessionMBean.getUserAccount());
                /* 194 */ JsfUtil.addSuccessMessage("Operation réussie");
            } else {
                /* 196 */ JsfUtil.addErrorMessage("Aucun salaire selectionnée");
            }
            /* 198 */        } catch (Exception e) {
            /* 199 */ e.printStackTrace();
        }
    }

    public void print() {
        try {
            /* 205 */ Privilege p = this.privilegeFacadeLocal.findByUser(SessionMBean.getUserAccount().getIdutilisateur().intValue(), 1);
            /* 206 */ if (p != null) {
                /* 207 */ this.showSalairePrintDialog = Boolean.valueOf(true);
            } else {
                /* 209 */ p = new Privilege();
                /* 210 */ p = this.privilegeFacadeLocal.findByUser(SessionMBean.getUserAccount().getIdutilisateur().intValue(), 20);
                /* 211 */ if (p != null) {
                    /* 212 */ this.showSalairePrintDialog = Boolean.valueOf(true);
                } else {
                    /* 214 */ this.showSalairePrintDialog = Boolean.valueOf(false);
                    /* 215 */ JsfUtil.addErrorMessage("Vous n 'avez pas le privilège d'éditer la liste des salaires");

                    return;
                }
            }
            /* 220 */ System.err.println("" + this.fileName);
            /* 221 */        } catch (Exception e) {
            /* 222 */ e.printStackTrace();
        }
    }
}


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-war.war!\WEB-INF\classes\controllers\payement\PayementController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
