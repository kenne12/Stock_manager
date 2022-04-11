package controllers.fraiscarnet;

import controllers.fraiscarnet.AbstractCarnetClientController;
import entities.Caisse;
import entities.Client;
import entities.Privilege;
import entities.Retrait;
import entities.Versement;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import utils.JsfUtil;
import utils.PrintUtils;
import utils.SessionMBean;
import utils.Utilitaires;

@ManagedBean
@ViewScoped
public class CarnetClientController
        extends AbstractCarnetClientController {

    @PostConstruct
    private void init() {
        /*  38 */ this.client = new Client();
        /*  39 */ load();
    }

    public void prepareCreate() {
        /*  43 */ this.client = new Client();
        /*  44 */ this.mode = "Create";
        /*  45 */ this.showMontantCarnetCompnent = true;
        try {
            /*  47 */ Privilege p = this.privilegeFacadeLocal.findByUser(SessionMBean.getUserAccount().getIdutilisateur().intValue(), 1);
            /*  48 */ if (p != null) {
                /*  49 */ this.showClientCreateDialog = true;
            } else {
                /*  51 */ p = new Privilege();
                /*  52 */ p = this.privilegeFacadeLocal.findByUser(SessionMBean.getUserAccount().getIdutilisateur().intValue(), 21);
                /*  53 */ if (p != null) {
                    /*  54 */ this.showClientCreateDialog = true;
                } else {
                    /*  56 */ JsfUtil.addErrorMessage("Vous n 'avez pas le privilege d'enregistrer un client");
                }
            }
            /*  59 */        } catch (Exception e) {
            /*  60 */ e.printStackTrace();
        }

        try {
            /*  64 */ List<Client> clientsTemp = this.clientFacadeLocal.findAll();
            /*  65 */ for (Client client : clientsTemp);

        } /*  68 */ catch (Exception e) {
            /*  69 */ e.printStackTrace();
        }
    }

    public void prepareEdit() {
        /*  75 */ this.mode = "Edit";
        /*  76 */ this.showMontantCarnetCompnent = false;
        /*  77 */ if (this.client != null) {
            /*  78 */ List<Versement> versementsTemp = this.versementFacadeLocal.find(this.client);
            /*  79 */ if (versementsTemp.isEmpty()) {
                /*  80 */ List<Retrait> retraitsTemp = this.retraitFacadeLocal.find(this.client);
                /*  81 */ if (retraitsTemp.isEmpty()) {
                    /*  82 */ this.showEditSolde = Boolean.valueOf(true);
                } else {
                    /*  84 */ this.showEditSolde = Boolean.valueOf(false);
                }
            } else {
                /*  87 */ this.showEditSolde = Boolean.valueOf(false);
            }
        }

        try {
            /*  92 */ Privilege p = this.privilegeFacadeLocal.findByUser(SessionMBean.getUserAccount().getIdutilisateur().intValue(), 1);
            /*  93 */ if (p != null) {
                /*  94 */ this.showClientCreateDialog = true;
                return;
            }
            /*  97 */ p = new Privilege();
            /*  98 */ p = this.privilegeFacadeLocal.findByUser(SessionMBean.getUserAccount().getIdutilisateur().intValue(), 8);
            /*  99 */ if (p != null) {
                /* 100 */ this.showClientCreateDialog = true;
                return;
            }
            /* 103 */ JsfUtil.addErrorMessage("Vous n'avez pas le privilege de modifier ce client");

        } /* 106 */ catch (Exception e) {
            /* 107 */ e.printStackTrace();
        }
    }

    public void create() {
        try {
            /* 114 */ if (this.mode.equals("Create")) {
                /* 116 */ if (this.client.getIdclient() != null) {
                    /* 117 */ this.client = this.clientFacadeLocal.find(this.client.getIdclient());

                    /* 119 */ Caisse c = this.caisseFacadeLocal.findAll().get(0);
                    /* 120 */ c.setMontant(Integer.valueOf(c.getMontant().intValue() + this.carnet));
                    /* 121 */ this.caisseFacadeLocal.edit(c);

                    /* 124 */ this.clientFacadeLocal.edit(this.client);

                    /* 126 */ Utilitaires.saveOperation(this.mouchardFacadeLocal, "Enregistrement des frais de carnet du client : " + this.client.getNom() + " " + this.client.getPrenom(), SessionMBean.getUserAccount());
                    /* 127 */ this.client = new Client();
                    /* 128 */ load();
                    /* 129 */ JsfUtil.addSuccessMessage("Opération réussie");
                } else {
                    /* 131 */ JsfUtil.addErrorMessage("Aucun client sélectionné");
                }
            }
            /* 134 */        } catch (Exception e) {
            /* 135 */ e.printStackTrace();
        }
    }

    public void edit() {
    }

    public void delete() {
        try {
            /* 145 */ if (this.client != null) {

                /* 147 */ Privilege p = this.privilegeFacadeLocal.findByUser(SessionMBean.getUserAccount().getIdutilisateur().intValue(), 1);
                /* 148 */ if (p != null) {
                    /* 149 */ this.showClientDeleteDialog = true;
                } else {
                    /* 151 */ p = new Privilege();
                    /* 152 */ p = this.privilegeFacadeLocal.findByUser(SessionMBean.getUserAccount().getIdutilisateur().intValue(), 22);
                    /* 153 */ if (p != null) {
                        /* 154 */ this.showClientDeleteDialog = true;
                    } else {
                        /* 156 */ this.showClientDeleteDialog = false;
                        /* 157 */ JsfUtil.addErrorMessage("Vous n'avez pas le privilège de supprimer les frais de carnet");

                        return;
                    }
                }

                /* 163 */ this.clientFacadeLocal.edit(this.client);

                /* 165 */ load();
                /* 166 */ Utilitaires.saveOperation(this.mouchardFacadeLocal, "Suppression des frais de carnet du client : " + this.client.getNom() + " " + this.client.getPrenom(), SessionMBean.getUserAccount());
                /* 167 */ JsfUtil.addSuccessMessage("Operation réussie");
            } else {
                /* 169 */ JsfUtil.addErrorMessage("Aucun client selectionnée");
            }
            /* 171 */        } catch (Exception e) {
            /* 172 */ e.printStackTrace();
        }
    }

    public void print() {
        try {
            /* 178 */ Privilege p = this.privilegeFacadeLocal.findByUser(SessionMBean.getUserAccount().getIdutilisateur().intValue(), 1);
            /* 179 */ if (p != null) {
                /* 180 */ this.showClientPrintDialog = Boolean.valueOf(true);
            } else {
                /* 182 */ p = new Privilege();
                /* 183 */ p = this.privilegeFacadeLocal.findByUser(SessionMBean.getUserAccount().getIdutilisateur().intValue(), 20);
                /* 184 */ if (p != null) {
                    /* 185 */ this.showClientPrintDialog = Boolean.valueOf(true);
                } else {
                    /* 187 */ this.showClientPrintDialog = Boolean.valueOf(false);
                    /* 188 */ JsfUtil.addErrorMessage("Vous n 'avez pas le privilège d'éditer la liste des clients");
                    return;
                }
            }
            /* 192 */ this.fileName = PrintUtils.printCustomerList(this.clients);
            /* 193 */ System.err.println("" + this.fileName);
            /* 194 */        } catch (Exception e) {
            /* 195 */ e.printStackTrace();
        }
    }

    public void load() {
        /* 200 */ this.clients.clear();
        try {
            /* 202 */ List<Client> clientsTemp = this.clientFacadeLocal.findAll();
            /* 203 */ for (Client client : clientsTemp);

        } /* 206 */ catch (Exception e) {
            /* 207 */ e.printStackTrace();
        }
    }
}


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-war.war!\WEB-INF\classes\controllers\fraiscarnet\CarnetClientController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
