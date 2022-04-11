package controllers.retraitcn;

import controllers.retraitcn.AbstractRetraitCnController;
import entities.Caisse;
import entities.Client;
import entities.Privilege;
import entities.Retrait;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import utils.JsfUtil;
import utils.SessionMBean;
import utils.Utilitaires;

@ManagedBean
@ViewScoped
public class RetraitCnController
        extends AbstractRetraitCnController {

    @PostConstruct
    private void init() {
        /*  36 */ this.client = new Client();
        /*  37 */ this.retrait = new Retrait();
    }

    public void prepareCreate() {
        /*  41 */ this.client = new Client();
        /*  42 */ this.retrait = new Retrait();
        /*  43 */ this.retrait.setDate(new Date());
        /*  44 */ this.mode = "Create";
        /*  45 */ this.showClient = Boolean.valueOf(false);
        try {
            /*  47 */ Privilege p = this.privilegeFacadeLocal.findByUser(SessionMBean.getUserAccount().getIdutilisateur().intValue(), 1);
            /*  48 */ if (p != null) {
                /*  49 */ this.showRetraitCreateDialog = Boolean.valueOf(true);
            } else {
                /*  51 */ p = new Privilege();
                /*  52 */ p = this.privilegeFacadeLocal.findByUser(SessionMBean.getUserAccount().getIdutilisateur().intValue(), 13);
                /*  53 */ if (p != null) {
                    /*  54 */ this.showRetraitCreateDialog = Boolean.valueOf(true);
                } else {
                    /*  56 */ this.showRetraitCreateDialog = Boolean.valueOf(false);
                    /*  57 */ JsfUtil.addErrorMessage("Vous n 'avez pas le privilege d'enregistrer un rétrait");
                }
            }
            /*  60 */        } catch (Exception e) {
            /*  61 */ e.printStackTrace();
        }
    }

    public void prepareEdit() {
        /*  66 */ this.mode = "Edit";
        /*  67 */ this.showClient = Boolean.valueOf(true);

        /*  69 */ if (this.retrait != null) {
            /*  70 */ this.retrait1 = this.retrait.getMontant();
            /*  71 */ this.commission = this.retrait.getCommission();
        }

        try {
            /*  75 */ Privilege p = this.privilegeFacadeLocal.findByUser(SessionMBean.getUserAccount().getIdutilisateur().intValue(), 1);
            /*  76 */ if (p != null) {
                /*  77 */ this.showRetraitCreateDialog = Boolean.valueOf(true);
            } else {
                /*  79 */ p = new Privilege();
                /*  80 */ p = this.privilegeFacadeLocal.findByUser(SessionMBean.getUserAccount().getIdutilisateur().intValue(), 14);
                /*  81 */ if (p != null) {
                    /*  82 */ this.showRetraitCreateDialog = Boolean.valueOf(true);
                } else {
                    /*  84 */ this.showRetraitCreateDialog = Boolean.valueOf(false);
                    /*  85 */ JsfUtil.addErrorMessage("Vous n 'avez pas le privilege de modifier un rétrait");
                }
            }
            /*  88 */        } catch (Exception e) {
            /*  89 */ e.printStackTrace();
        }
    }

    public void updateSolde1() {
        /*  95 */ if (this.mode == "Create") {
            /*  96 */ if (this.retrait.getIdclient() != null) {
                /*  97 */ if (this.retrait1 != null) {
                    /*  98 */ Client c = this.clientFacadeLocal.find(this.retrait.getIdclient().getIdclient());
                    /*  99 */ if (this.commission != null) {
                        /* 100 */ c.setSolde(Integer.valueOf(c.getSolde().intValue() - this.retrait1.intValue() - this.commission.intValue()));
                        /* 101 */ this.retrait.getIdclient().setSolde(c.getSolde());
                    }
                } else {
                    /* 104 */ Client c = this.clientFacadeLocal.find(this.retrait.getIdclient().getIdclient());
                    /* 105 */ this.retrait.getIdclient().setSolde(c.getSolde());
                }

            }
            /* 109 */        } else if (this.retrait.getIdclient() != null) {
            /* 110 */ if (this.retrait1 != null) {
                /* 111 */ if (this.commission != null) {
                    /* 112 */ Client c = this.clientFacadeLocal.find(this.retrait.getIdclient().getIdclient());
                    /* 113 */ int solde = c.getSolde().intValue() + this.retrait.getMontant().intValue() + this.retrait.getCommission().intValue() - this.retrait1.intValue() - this.commission.intValue();
                    /* 114 */ this.retrait.getIdclient().setSolde(Integer.valueOf(solde));
                }
            } else {
                /* 117 */ Client c = this.clientFacadeLocal.find(this.retrait.getIdclient().getIdclient());
                /* 118 */ this.retrait.getIdclient().setSolde(c.getSolde());
            }
        }
    }

    public void updateSolde() {
        /* 125 */ this.retrait1 = Integer.valueOf(0);
    }

    public void create() {
        try {
            /* 130 */ if (this.mode.equals("Create")) {

                /* 132 */ if (this.retrait1.intValue() != 500 && this.retrait1.intValue() != 0) {

                    /* 134 */ if (this.commission == null) {
                        /* 135 */ this.commission = Integer.valueOf(0);
                    }

                    /* 138 */ this.retrait.setIdretrait(this.retraitFacadeLocal.nextLongVal());
                    /* 139 */ this.retrait.setMontant(this.retrait1);
                    /* 140 */ this.retrait.setCommission(this.commission);
                    /* 141 */ this.retrait.setHeure(new Date());
                    /* 142 */ this.retraitFacadeLocal.create(this.retrait);

                    /* 144 */ Client c = this.clientFacadeLocal.find(this.retrait.getIdclient().getIdclient());
                    /* 145 */ c.setSolde(Integer.valueOf(c.getSolde().intValue() - this.retrait1.intValue() - this.commission.intValue()));
                    /* 146 */ this.clientFacadeLocal.edit(c);

                    /* 148 */ Caisse caisse = this.caisseFacadeLocal.findAll().get(0);
                    /* 149 */ caisse.setMontant(Integer.valueOf(caisse.getMontant().intValue() - this.retrait1.intValue()));
                    /* 150 */ this.caisseFacadeLocal.edit(caisse);

                    /* 152 */ Utilitaires.saveOperation(this.mouchardFacadeLocal, "Enregistrement du retrait -> client : " + this.retrait.getIdclient().getPrenom() + " " + this.retrait.getIdclient().getNom() + " ; Montant : " + this.retrait1, SessionMBean.getUserAccount());

                    /* 154 */ this.retrait = new Retrait();
                    /* 155 */ JsfUtil.addSuccessMessage("Transaction réussie");
                } else {
                    /* 157 */ JsfUtil.addErrorMessage("Impossible de faire un retrait inferieur a 500 f");
                }

            } /* 161 */ else if (this.retrait != null && this.retrait1.intValue() != 0) {

                /* 163 */ Retrait r = this.retraitFacadeLocal.find(this.retrait.getIdretrait());

                /* 165 */ Client c = this.clientFacadeLocal.find(this.retrait.getIdclient().getIdclient());
                /* 166 */ c.setSolde(Integer.valueOf(c.getSolde().intValue() + r.getMontant().intValue() + r.getCommission().intValue()));
                /* 167 */ if (c.getSolde().intValue() < 0) {
                    /* 168 */ c.setSolde(Integer.valueOf(0));
                }
                /* 170 */ this.clientFacadeLocal.edit(c);

                /* 172 */ Caisse caisse = this.caisseFacadeLocal.findAll().get(0);
                /* 173 */ caisse.setMontant(Integer.valueOf(caisse.getMontant().intValue() + r.getMontant().intValue() + r.getCommission().intValue()));

                /* 175 */ if (caisse.getMontant().intValue() < 0) {
                    /* 176 */ caisse.setMontant(Integer.valueOf(0));
                }
                /* 178 */ this.caisseFacadeLocal.edit(caisse);

                /* 180 */ if (this.commission == null) {
                    /* 181 */ this.commission = Integer.valueOf(0);
                }

                /* 184 */ this.retrait.setMontant(this.retrait1);
                /* 185 */ this.retrait.setCommission(this.commission);
                /* 186 */ this.retraitFacadeLocal.edit(this.retrait);

                /* 188 */ Client c1 = this.clientFacadeLocal.find(this.retrait.getIdclient().getIdclient());
                /* 189 */ c1.setSolde(Integer.valueOf(c1.getSolde().intValue() - this.retrait1.intValue() - this.commission.intValue()));
                /* 190 */ this.clientFacadeLocal.edit(c1);

                /* 192 */ Caisse caisse1 = this.caisseFacadeLocal.findAll().get(0);
                /* 193 */ caisse1.setMontant(Integer.valueOf(caisse1.getMontant().intValue() - this.retrait1.intValue()));
                /* 194 */ this.caisseFacadeLocal.edit(caisse1);

                /* 196 */ Utilitaires.saveOperation(this.mouchardFacadeLocal, "Modification du retrait -> client : " + this.retrait.getIdclient().getPrenom() + " " + this.retrait.getIdclient().getNom() + " Ancien montant : " + r.getMontant() + " ; Nouveau Montant : " + this.retrait1, SessionMBean.getUserAccount());

                /* 198 */ JsfUtil.addSuccessMessage("Opération réussie");
            } else {

                /* 201 */ JsfUtil.addErrorMessage("Aucun retrait selectionné");
            }

            /* 204 */        } catch (Exception e) {
            /* 205 */ e.printStackTrace();
        }
    }

    public void delete() {
        try {
            /* 211 */ if (this.retrait != null) {

                try {
                    /* 214 */ Privilege p = this.privilegeFacadeLocal.findByUser(SessionMBean.getUserAccount().getIdutilisateur().intValue(), 1);
                    /* 215 */ if (p != null) {
                        /* 216 */ this.showRetraitDeleteDialog = Boolean.valueOf(true);
                    } else {
                        /* 218 */ p = new Privilege();
                        /* 219 */ p = this.privilegeFacadeLocal.findByUser(SessionMBean.getUserAccount().getIdutilisateur().intValue(), 15);
                        /* 220 */ if (p != null) {
                            /* 221 */ this.showRetraitDeleteDialog = Boolean.valueOf(true);
                        } else {
                            /* 223 */ this.showRetraitDeleteDialog = Boolean.valueOf(false);
                            /* 224 */ JsfUtil.addErrorMessage("Vous n 'avez pas le privilege de supprimer ce retrait");
                            return;
                        }
                    }
                    /* 228 */                } catch (Exception e) {
                    /* 229 */ e.printStackTrace();
                }

                /* 232 */ this.retraitFacadeLocal.remove(this.retrait);

                /* 234 */ Client c = this.clientFacadeLocal.find(this.retrait.getIdclient().getIdclient());
                /* 235 */ c.setSolde(Integer.valueOf(c.getSolde().intValue() + this.retrait.getMontant().intValue() + this.retrait.getCommission().intValue()));
                /* 236 */ this.clientFacadeLocal.edit(c);

                /* 238 */ Caisse caisse = this.caisseFacadeLocal.findAll().get(0);
                /* 239 */ caisse.setMontant(Integer.valueOf(caisse.getMontant().intValue() + this.retrait.getMontant().intValue()));
                /* 240 */ this.caisseFacadeLocal.edit(caisse);

                /* 242 */ Utilitaires.saveOperation(this.mouchardFacadeLocal, "Suppression du retrait -> client : " + this.retrait.getIdclient().getPrenom() + " " + this.retrait.getIdclient().getNom() + " ; Montant : " + this.retrait.getMontant(), SessionMBean.getUserAccount());

                /* 244 */ JsfUtil.addSuccessMessage("Operation réussie");
            } else {
                /* 246 */ JsfUtil.addErrorMessage("Aucun client selectionnée");
            }
            /* 248 */        } catch (Exception e) {
            /* 249 */ e.printStackTrace();
        }
    }

    public void print() {
        /* 254 */ Privilege p = this.privilegeFacadeLocal.findByUser(SessionMBean.getUserAccount().getIdutilisateur().intValue(), 1);
        /* 255 */ if (p != null) {
            /* 256 */ this.showRetraitPrintDialog = Boolean.valueOf(true);
        } else {
            /* 258 */ p = new Privilege();
            /* 259 */ p = this.privilegeFacadeLocal.findByUser(SessionMBean.getUserAccount().getIdutilisateur().intValue(), 12);
            /* 260 */ if (p != null) {
                /* 261 */ this.showRetraitPrintDialog = Boolean.valueOf(true);
            } else {
                /* 263 */ this.showRetraitPrintDialog = Boolean.valueOf(false);
                /* 264 */ JsfUtil.addErrorMessage("Vous n 'avez pas le privilege d'imprimer le rapport des retrait");
                return;
            }
        }
    }
}
