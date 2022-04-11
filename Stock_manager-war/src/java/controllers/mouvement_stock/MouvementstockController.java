package controllers.mouvement_stock;

import entities.Commande;
import entities.Facture;
import entities.Produit;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.context.RequestContext;
import utils.PrintUtils;
import utils.SessionMBean;
import utils.Utilitaires;

@ManagedBean
@SessionScoped
public class MouvementstockController
        extends AbstractMouvementstockController
        implements Serializable {

    @PostConstruct
    private void init() {
        /*  39 */ this.critere = 1;
        /*  40 */ this.showDateInterval = false;
        /*  41 */ this.showClient = false;
        /*  42 */ this.showMois = false;
        /*  43 */ this.showDate = false;
        /*  44 */ this.showDateDebut = false;
        /*  45 */ this.showDateFin = false;
    }

    public void initBoolean(boolean showDateInterval, boolean showClient, boolean showMois, boolean showDate, boolean showDateDebut, boolean showDateFin) {
        /*  50 */ this.showDateInterval = showDateInterval;
        /*  51 */ this.showClient = showClient;
        /*  52 */ this.showMois = showMois;
        /*  53 */ this.showDate = showDate;
        /*  54 */ this.showDateDebut = showDateDebut;
        /*  55 */ this.showDateFin = showDateFin;
    }

    public void updateCritere() {
        /*  60 */ this.factures.clear();
        this.imprimer = (true);
        if (this.critere == 1) {
            initBoolean(false, false, false, false, false, false);
        } else if (this.critere == 2) {
            initBoolean(false, false, true, false, false, false);
        } else if (this.critere == 3) {
            initBoolean(true, true, false, false, true, true);
            this.date_interval = 1;
        } else if (this.critere == 4) {
            initBoolean(false, false, false, true, false, false);
            this.date_interval = 1;
        } else {
            initBoolean(false, false, false, false, true, true);
        }
    }

    public void updateClient() {
        /*  78 */ this.ligneMvtStocks.clear();
        /*  79 */ this.imprimer = (true);
    }

    public void updateDate() {
        /*  83 */ updateClient();
    }

    public void updateDateInterl() {
        /*  87 */ this.factures.clear();
        /*  88 */ this.imprimer = (true);
        /*  89 */ if (this.date_interval == 1) {
            /*  90 */ this.showDate = false;
            /*  91 */ this.showDateDebut = true;
            /*  92 */ this.showDateFin = true;
        } else {
            /*  94 */ this.showDate = true;
            /*  95 */ this.showDateDebut = false;
            /*  96 */ this.showDateFin = false;
        }
    }

    public void rechercher() {
        try {
            this.imprimer = (true);
            if (this.critere == 1) {
                this.factures = this.factureFacadeLocal.findAllRange();
                ajaxHide();
                if (!this.factures.isEmpty()) {
                    this.imprimer = (false);
                    notifySuccess();
                } else {
                    notifyFail();
                }
                return;
            }
            if (this.critere == 2) {
                this.anneeMois = this.anneeMoisFacadeLocal.find(this.anneeMois.getIdAnneeMois());
                this.ligneMvtStocks = this.ligneMvtStockFacadeLocal.findByIntervale(this.anneeMois.getDateDebut(), this.anneeMois.getDateFin());

                ajaxHide();
                if (!this.ligneMvtStocks.isEmpty() && this.ligneMvtStocks != null) {
                    this.imprimer = (false);
                    notifySuccess();
                } else {
                    notifyFail();
                    this.imprimer = (true);
                }
                return;
            }
            if (this.critere == 3) {
                if (this.date_interval == 2) {
                    this.factures = this.factureFacadeLocal.findClientDate(this.client.getIdclient(), this.date);
                } else {
                    this.factures = this.factureFacadeLocal.findClientInterval(this.client.getIdclient(), this.dateDebut, this.dateFin);
                }
                ajaxHide();
                if (!this.factures.isEmpty()) {
                    this.imprimer = (false);
                    notifySuccess();
                } else {
                    notifyFail();
                }
                return;
            }
            if (this.critere == 4) {
                this.factures = this.factureFacadeLocal.findAllDate(this.date);
                ajaxHide();
                if (!this.factures.isEmpty()) {
                    this.imprimer = (false);
                    notifySuccess();
                } else {
                    notifyFail();
                }
                return;
            }
            this.factures = this.factureFacadeLocal.findByInterval(this.dateDebut, this.dateFin);
            ajaxHide();
            if (!this.factures.isEmpty()) {
                this.imprimer = (false);
                notifySuccess();
            } else {
                notifyFail();
            }

            return;
        } catch (Exception e) {
            ajaxHide();
            this.routine.catchException(e, this.routine.localizeMessage("echec_operation"));
            RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
            return;
        }
    }

    public void ajaxHide() {
        /* 169 */ RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').hide()");
    }

    public void notifySuccess() {
        /* 173 */ this.routine.feedBack("information", "/resources/tool_images/success.png", this.routine.localizeMessage("operation_reussie"));
        /* 174 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
    }

    public void notifyFail() {
        /* 178 */ this.routine.feedBack("avertissement", "/resources/tool_images/error.png", this.routine.localizeMessage("aucune_ligne_trouvee"));
        /* 179 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
    }

    public void prepareview() {
        try {
            if (this.facture != null) {
                List<Commande> c = this.commandeFacadeLocal.findByFacture(this.facture);
                if (!c.isEmpty()) {
                    RequestContext.getCurrentInstance().execute("PF('FactureDetailDialog').show()");
                    return;
                }
                this.facture.setCommandeList(c);
                this.routine.feedBack("avertissement", "/resources/tool_images/error.png", this.routine.localizeMessage("liste_article_vide"));
                RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
            } else {
                this.routine.feedBack("avertissement", "/resources/tool_images/error.png", this.routine.localizeMessage("not_row_selected"));
                RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
            }
        } catch (Exception e) {
            this.routine.catchException(e, this.routine.localizeMessage("echec_operation"));
            RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
        }
    }

    public void initPrinter(Facture f) {
        /* 205 */ this.facture = f;
        /* 206 */ print();
    }

    public void initView(Facture f) {
        /* 210 */ this.facture = f;
        /* 211 */ prepareview();
    }

    public void print() {
        try {
            if (!Utilitaires.isAccess((26L))) {
                this.routine.feedBack("information", "/resources/tool_images/error.png", this.routine.localizeMessage("acces_refuse"));
                /* 218 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
                /* 219 */ this.facture = null;

                return;
            }
            if (this.facture != null) {
                this.printDialogTitle = this.routine.localizeMessage("facture");
                List<Commande> c = this.commandeFacadeLocal.findByFacture(this.facture);
                this.facture.setCommandeList(c);
                this.fileName = PrintUtils.printFacture(this.facture, SessionMBean.getParametrage());
                RequestContext.getCurrentInstance().execute("PF('FactureImprimerDialog').show()");
            } else {
                this.routine.feedBack("information", "/resources/tool_images/error.png", this.routine.localizeMessage("not_row_selected"));
                RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
            }
        } catch (Exception e) {
            this.routine.catchException(e, this.routine.localizeMessage("echec_operation"));
            RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
        }
    }

    public void printHistoric() {
        try {
            this.imprimer = (true);
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            if (this.critere == 1) {
                this.factures = this.factureFacadeLocal.findAllRange();
                ajaxHide();
                if (!this.factures.isEmpty()) {
                    this.imprimer = (false);

                    this.printDialogTitle = "Historique des ventes";
                    String titre = "HISTORIQUE DE TOUTES LES VENTES";

                    this.fileName = "Historic_total_vente_" + sdf.format(new Date(System.currentTimeMillis())) + ".pdf";
                    PrintUtils.printHistoric(this.factures, SessionMBean.getParametrage(), titre, this.fileName);
                    RequestContext.getCurrentInstance().execute("PF('FactureImprimerDialog').show()");
                } else {
                    notifyFail();
                }
                return;
            }
            if (this.critere == 2) {

                ajaxHide();
                if (!this.ligneMvtStocks.isEmpty() || this.ligneMvtStocks != null) {
                    this.imprimer = (false);

                    this.printDialogTitle = "Mouvent du stock " + this.anneeMois.getIdmois().getNom() + "/" + this.anneeMois.getIdannee().getNom();
                    String titre = "FICHE DES MOUVEMENTS DU STOCK : " + this.anneeMois.getIdmois().getNom() + "/" + this.anneeMois.getIdannee().getNom();

                    this.fileName = "fiche_mvt_stock_" + this.anneeMois.getIdmois().getNom() + "_" + this.anneeMois.getIdannee().getNom() + ".pdf";

                    List<Produit> produitsTemp = this.produitFacadeLocal.findAllRange();

                    PrintUtils.printMouvementMensuel(this.ligneMvtStocks, produitsTemp, SessionMBean.getParametrage(), titre, this.fileName);
                    RequestContext.getCurrentInstance().execute("PF('FactureImprimerDialog').show()");
                } else {
                    notifyFail();
                }
                return;
            }
            if (this.critere == 3) {
                if (this.date_interval == 2) {
                    this.factures = this.factureFacadeLocal.findClientDate(this.client.getIdclient(), this.date);
                } else {
                    this.factures = this.factureFacadeLocal.findClientInterval(this.client.getIdclient(), this.dateDebut, this.dateFin);
                }
                ajaxHide();
                if (!this.factures.isEmpty()) {
                    this.imprimer = (false);

                    if (this.date_interval == 2) {
                        /* 289 */ this.printDialogTitle = "Historique des achats du client " + ((Facture) this.factures.get(0)).getIdclient().getNom() + " à la date du " + sdf.format(this.date);
                        /* 290 */ String str = "HISTORIQUE DES OPERATIONS DU : " + sdf.format(this.date);

                        /* 292 */ this.fileName = "Historic_des_achats_" + ((Facture) this.factures.get(0)).getIdclient().getNom() + "_" + sdf.format(this.date) + ".pdf";
                        /* 293 */ PrintUtils.printHistoric(this.factures, SessionMBean.getParametrage(), str, this.fileName, ((Facture) this.factures.get(0)).getIdclient(), "" + sdf.format(this.date));
                        /* 294 */ RequestContext.getCurrentInstance().execute("PF('FactureImprimerDialog').show()");

                        return;
                    }
                    /* 298 */ this.printDialogTitle = "Historique des achats du client " + ((Facture) this.factures.get(0)).getIdclient().getNom() + " du " + sdf.format(this.dateDebut) + " Au " + sdf.format(this.dateFin);
                    /* 299 */ String titre = "HISTORIQUE DES VENTES DU : " + sdf.format(this.dateDebut) + " AU " + sdf.format(this.dateFin);

                    /* 301 */ this.fileName = "Historic_des_achats_" + ((Facture) this.factures.get(0)).getIdclient().getNom() + "_" + sdf.format(this.dateDebut) + "_Au_" + sdf.format(this.dateFin) + ".pdf";
                    /* 302 */ PrintUtils.printHistoric(this.factures, SessionMBean.getParametrage(), titre, this.fileName, ((Facture) this.factures.get(0)).getIdclient(), "" + sdf.format(this.dateDebut) + " Au " + sdf.format(this.dateFin));
                    /* 303 */ RequestContext.getCurrentInstance().execute("PF('FactureImprimerDialog').show()");
                } else {
                    /* 305 */ notifyFail();
                }
                return;
            }
            if (this.critere == 4) {
                this.factures = this.factureFacadeLocal.findAllDate(this.date);
                ajaxHide();
                if (!this.factures.isEmpty()) {
                    this.imprimer = (false);

                    /* 314 */ this.printDialogTitle = "Historique des ventes du " + sdf.format(this.date);
                    /* 315 */ String titre = "HISTORIQUE DE TOUTES LES VENTES : " + sdf.format(this.date);

                    /* 317 */ this.fileName = "Historic_des_vente_" + sdf.format(this.date) + ".pdf";
                    /* 318 */ PrintUtils.printHistoric(this.factures, SessionMBean.getParametrage(), titre, this.fileName);
                    /* 319 */ RequestContext.getCurrentInstance().execute("PF('FactureImprimerDialog').show()");
                } else {
                    notifyFail();
                }
                return;
            }
            this.factures = this.factureFacadeLocal.findByInterval(this.dateDebut, this.dateFin);
            ajaxHide();
            if (!this.factures.isEmpty()) {
                this.imprimer = (false);

                this.printDialogTitle = "Historique des ventes du " + sdf.format(this.dateDebut) + " Au " + sdf.format(this.dateFin);
                String titre = "HISTORIQUE DES VENTES DU " + sdf.format(this.dateDebut) + " AU " + sdf.format(this.dateFin);

                this.fileName = "Historic_des_vente_" + sdf.format(this.dateDebut) + "_au_" + sdf.format(this.dateFin) + ".pdf";
                PrintUtils.printHistoric(this.factures, SessionMBean.getParametrage(), titre, this.fileName);
                RequestContext.getCurrentInstance().execute("PF('FactureImprimerDialog').show()");
            } else {
                notifyFail();
            }

            return;
        } catch (Exception e) {
            ajaxHide();
            this.routine.catchException(e, this.routine.localizeMessage("echec_operation"));
            RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
            return;
        }
    }
}
