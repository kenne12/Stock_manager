package controllers.recherche;

import entities.Commande;
import entities.Facture;
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
public class RechercheController extends AbstractRechercheController implements Serializable {

    @PostConstruct
    private void init() {
        /*  38 */ this.critere = 1;
        /*  39 */ this.showDateInterval = false;
        /*  40 */ this.showClient = false;
        /*  41 */ this.showMois = false;
        /*  42 */ this.showDate = false;
        /*  43 */ this.showDateDebut = false;
        /*  44 */ this.showDateFin = false;
    }

    public void initBoolean(boolean showDateInterval, boolean showClient, boolean showMois, boolean showDate, boolean showDateDebut, boolean showDateFin) {
        /*  49 */ this.showDateInterval = showDateInterval;
        /*  50 */ this.showClient = showClient;
        /*  51 */ this.showMois = showMois;
        /*  52 */ this.showDate = showDate;
        /*  53 */ this.showDateDebut = showDateDebut;
        /*  54 */ this.showDateFin = showDateFin;
    }

    public void updateCritere() {
        /*  59 */ this.factures.clear();
        /*  60 */ this.imprimer = Boolean.valueOf(true);
        /*  61 */ if (this.critere == 1) {
            /*  62 */ initBoolean(false, false, false, false, false, false);
            /*  63 */        } else if (this.critere == 2) {
            /*  64 */ initBoolean(false, false, true, false, false, false);
            /*  65 */        } else if (this.critere == 3) {
            /*  66 */ initBoolean(true, true, false, false, true, true);
            /*  67 */ this.date_interval = 1;
            /*  68 */        } else if (this.critere == 4) {
            /*  69 */ initBoolean(false, false, false, true, false, false);
            /*  70 */ this.date_interval = 1;
        } else {
            /*  72 */ initBoolean(false, false, false, false, true, true);
        }
    }

    public void updateClient() {
        /*  77 */ this.factures.clear();
        /*  78 */ this.imprimer = (true);
    }

    public void updateDate() {
        /*  82 */ updateClient();
    }

    public void updateDateInterl() {
        /*  86 */ this.factures.clear();
        /*  87 */ this.imprimer = (true);
        /*  88 */ if (this.date_interval == 1) {
            /*  89 */ this.showDate = false;
            /*  90 */ this.showDateDebut = true;
            /*  91 */ this.showDateFin = true;
        } else {
            /*  93 */ this.showDate = true;
            /*  94 */ this.showDateDebut = false;
            /*  95 */ this.showDateFin = false;
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
                this.factures = this.factureFacadeLocal.findByMois(this.anneeMois.getIdAnneeMois());
                ajaxHide();
                if (!this.factures.isEmpty()) {
                    this.imprimer = (false);
                    notifySuccess();
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
                /* 150 */ this.imprimer = (false);
                /* 151 */ notifySuccess();
            } else {
                /* 153 */ notifyFail();
            }

            return;
        } catch (Exception e) {
            ajaxHide();
            /* 159 */ this.routine.catchException(e, this.routine.localizeMessage("echec_operation"));
            /* 160 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
            return;
        }
    }

    public void ajaxHide() {
        /* 165 */ RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').hide()");
    }

    public void notifySuccess() {
        /* 169 */ this.routine.feedBack("information", "/resources/tool_images/success.png", this.routine.localizeMessage("operation_reussie"));
        /* 170 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
    }

    public void notifyFail() {
        /* 174 */ this.routine.feedBack("avertissement", "/resources/tool_images/error.png", this.routine.localizeMessage("aucune_ligne_trouvee"));
        /* 175 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
    }

    public void prepareview() {
        try {
            /* 180 */ if (this.facture != null) {
                /* 181 */ List<Commande> c = this.commandeFacadeLocal.findByFacture(this.facture);
                /* 182 */ if (!c.isEmpty()) {
                    /* 183 */ RequestContext.getCurrentInstance().execute("PF('FactureDetailDialog').show()");
                    return;
                }
                /* 186 */ this.facture.setCommandeList(c);
                /* 187 */ this.routine.feedBack("avertissement", "/resources/tool_images/error.png", this.routine.localizeMessage("liste_article_vide"));
                /* 188 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
            } else {

                /* 191 */ this.routine.feedBack("avertissement", "/resources/tool_images/error.png", this.routine.localizeMessage("not_row_selected"));
                /* 192 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
            }
            /* 194 */        } catch (Exception e) {
            /* 195 */ this.routine.catchException(e, this.routine.localizeMessage("echec_operation"));
            /* 196 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
        }
    }

    public void initPrinter(Facture f) {
        /* 201 */ this.facture = f;
        /* 202 */ print();
    }

    public void initView(Facture f) {
        /* 206 */ this.facture = f;
        /* 207 */ prepareview();
    }

    public void print() {
        try {
            /* 212 */ if (!Utilitaires.isAccess(Long.valueOf(26L))) {
                /* 213 */ this.routine.feedBack("information", "/resources/tool_images/error.png", this.routine.localizeMessage("acces_refuse"));
                /* 214 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
                /* 215 */ this.facture = null;

                return;
            }
            /* 219 */ if (this.facture != null) {
                /* 220 */ this.printDialogTitle = this.routine.localizeMessage("facture");
                /* 221 */ List<Commande> c = this.commandeFacadeLocal.findByFacture(this.facture);
                /* 222 */ this.facture.setCommandeList(c);
                /* 223 */ this.fileName = PrintUtils.printFacture(this.facture, SessionMBean.getParametrage());
                /* 224 */ RequestContext.getCurrentInstance().execute("PF('FactureImprimerDialog').show()");
            } else {
                /* 226 */ this.routine.feedBack("information", "/resources/tool_images/error.png", this.routine.localizeMessage("not_row_selected"));
                /* 227 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
            }
            /* 229 */        } catch (Exception e) {
            /* 230 */ this.routine.catchException(e, this.routine.localizeMessage("echec_operation"));
            /* 231 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
        }
    }

    public void printHistoric() {
        try {
            /* 237 */ this.imprimer = Boolean.valueOf(true);
            /* 238 */ SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            /* 239 */ if (this.critere == 1) {
                /* 240 */ this.factures = this.factureFacadeLocal.findAllRange();
                /* 241 */ ajaxHide();
                /* 242 */ if (!this.factures.isEmpty()) {
                    /* 243 */ this.imprimer = Boolean.valueOf(false);

                    /* 245 */ this.printDialogTitle = "Historique des ventes";
                    /* 246 */ String titre = "HISTORIQUE DE TOUTES LES VENTES";

                    /* 248 */ this.fileName = "Historic_total_vente_" + sdf.format(new Date(System.currentTimeMillis())) + ".pdf";
                    /* 249 */ PrintUtils.printHistoric(this.factures, SessionMBean.getParametrage(), titre, this.fileName);
                    /* 250 */ RequestContext.getCurrentInstance().execute("PF('FactureImprimerDialog').show()");
                } else {
                    /* 252 */ notifyFail();
                }
                return;
            }
            /* 255 */ if (this.critere == 2) {
                /* 256 */ this.anneeMois = this.anneeMoisFacadeLocal.find(this.anneeMois.getIdAnneeMois());
                /* 257 */ this.factures = this.factureFacadeLocal.findByMois(this.anneeMois.getIdAnneeMois().intValue());
                /* 258 */ ajaxHide();
                /* 259 */ if (!this.factures.isEmpty()) {
                    /* 260 */ this.imprimer = Boolean.valueOf(false);

                    /* 262 */ this.printDialogTitle = "Historique des ventes de " + this.anneeMois.getIdmois().getNom() + "/" + this.anneeMois.getIdannee().getNom();
                    /* 263 */ String titre = "HISTORIQUE DES VENTES : " + this.anneeMois.getIdmois().getNom() + "/" + this.anneeMois.getIdannee().getNom();

                    /* 265 */ this.fileName = "Historic_mensuel_vente_" + this.anneeMois.getIdmois().getNom() + "_" + this.anneeMois.getIdannee().getNom() + ".pdf";
                    /* 266 */ PrintUtils.printHistoric(this.factures, SessionMBean.getParametrage(), titre, this.fileName);
                    /* 267 */ RequestContext.getCurrentInstance().execute("PF('FactureImprimerDialog').show()");
                } else {
                    /* 269 */ notifyFail();
                }
                return;
            }
            /* 272 */ if (this.critere == 3) {
                /* 273 */ if (this.date_interval == 2) {
                    /* 274 */ this.factures = this.factureFacadeLocal.findClientDate(this.client.getIdclient().intValue(), this.date);
                } else {
                    /* 276 */ this.factures = this.factureFacadeLocal.findClientInterval(this.client.getIdclient().intValue(), this.dateDebut, this.dateFin);
                }
                /* 278 */ ajaxHide();
                /* 279 */ if (!this.factures.isEmpty()) {
                    /* 280 */ this.imprimer = Boolean.valueOf(false);

                    /* 282 */ if (this.date_interval == 2) {
                        /* 283 */ this.printDialogTitle = "Historique des achats du client " + ((Facture) this.factures.get(0)).getIdclient().getNom() + " à la date du " + sdf.format(this.date);
                        /* 284 */ String str = "HISTORIQUE DES OPERATIONS DU : " + sdf.format(this.date);

                        /* 286 */ this.fileName = "Historic_des_achats_" + ((Facture) this.factures.get(0)).getIdclient().getNom() + "_" + sdf.format(this.date) + ".pdf";
                        /* 287 */ PrintUtils.printHistoric(this.factures, SessionMBean.getParametrage(), str, this.fileName, ((Facture) this.factures.get(0)).getIdclient(), "" + sdf.format(this.date));
                        /* 288 */ RequestContext.getCurrentInstance().execute("PF('FactureImprimerDialog').show()");

                        return;
                    }
                    /* 292 */ this.printDialogTitle = "Historique des achats du client " + ((Facture) this.factures.get(0)).getIdclient().getNom() + " du " + sdf.format(this.dateDebut) + " Au " + sdf.format(this.dateFin);
                    /* 293 */ String titre = "HISTORIQUE DES VENTES DU : " + sdf.format(this.dateDebut) + " AU " + sdf.format(this.dateFin);

                    /* 295 */ this.fileName = "Historic_des_achats_" + ((Facture) this.factures.get(0)).getIdclient().getNom() + "_" + sdf.format(this.dateDebut) + "_Au_" + sdf.format(this.dateFin) + ".pdf";
                    /* 296 */ PrintUtils.printHistoric(this.factures, SessionMBean.getParametrage(), titre, this.fileName, ((Facture) this.factures.get(0)).getIdclient(), "" + sdf.format(this.dateDebut) + " Au " + sdf.format(this.dateFin));
                    /* 297 */ RequestContext.getCurrentInstance().execute("PF('FactureImprimerDialog').show()");
                } else {
                    /* 299 */ notifyFail();
                }
                return;
            }
            /* 302 */ if (this.critere == 4) {
                /* 303 */ this.factures = this.factureFacadeLocal.findAllDate(this.date);
                /* 304 */ ajaxHide();
                /* 305 */ if (!this.factures.isEmpty()) {
                    /* 306 */ this.imprimer = Boolean.valueOf(false);

                    /* 308 */ this.printDialogTitle = "Historique des ventes du " + sdf.format(this.date);
                    /* 309 */ String titre = "HISTORIQUE DE TOUTES LES VENTES : " + sdf.format(this.date);

                    /* 311 */ this.fileName = "Historic_des_vente_" + sdf.format(this.date) + ".pdf";
                    /* 312 */ PrintUtils.printHistoric(this.factures, SessionMBean.getParametrage(), titre, this.fileName);
                    /* 313 */ RequestContext.getCurrentInstance().execute("PF('FactureImprimerDialog').show()");
                } else {
                    /* 315 */ notifyFail();
                }
                return;
            }
            /* 319 */ this.factures = this.factureFacadeLocal.findByInterval(this.dateDebut, this.dateFin);
            /* 320 */ ajaxHide();
            /* 321 */ if (!this.factures.isEmpty()) {
                /* 322 */ this.imprimer = Boolean.valueOf(false);

                /* 324 */ this.printDialogTitle = "Historique des ventes du " + sdf.format(this.dateDebut) + " Au " + sdf.format(this.dateFin);
                /* 325 */ String titre = "HISTORIQUE DES VENTES DU " + sdf.format(this.dateDebut) + " AU " + sdf.format(this.dateFin);

                /* 327 */ this.fileName = "Historic_des_vente_" + sdf.format(this.dateDebut) + "_au_" + sdf.format(this.dateFin) + ".pdf";
                /* 328 */ PrintUtils.printHistoric(this.factures, SessionMBean.getParametrage(), titre, this.fileName);
                /* 329 */ RequestContext.getCurrentInstance().execute("PF('FactureImprimerDialog').show()");
            } else {
                /* 331 */ notifyFail();
            }

            return;
            /* 335 */        } catch (Exception e) {
            /* 336 */ ajaxHide();
            /* 337 */ this.routine.catchException(e, this.routine.localizeMessage("echec_operation"));
            /* 338 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
            return;
        }
    }
}
