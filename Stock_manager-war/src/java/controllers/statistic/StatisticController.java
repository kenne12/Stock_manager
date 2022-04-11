package controllers.statistic;

import controllers.statistic.AbstractStatisticController;
import entities.AnneeMois;
import entities.Commande;
import entities.Facture;
import entities.Stock;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.BarChartSeries;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import utils.PrintUtils;
import utils.SessionMBean;
import utils.Utilitaires;

@ManagedBean
@ViewScoped
public class StatisticController
        extends AbstractStatisticController
        implements Serializable {

    @PostConstruct
    private void init() {
        try {
            this.critere = 1;

            this.annee = SessionMBean.getMois().getIdannee();
            this.anneeMoises = this.anneeMoisFacadeLocal.findByAnnee(this.annee);

            createLineModels();
            createBarModel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {
        try {
            if (!this.annee.getIdannee().equals(Integer.valueOf(0))) {
                this.annee = this.anneeFacadeLocal.find(this.annee.getIdannee());
                this.anneeMoises = this.anneeMoisFacadeLocal.findByAnnee(this.annee);
                createLineModels();
                createBarModel();
            } else {
                this.lineModel1 = new LineChartModel();
                this.lineModel2 = new LineChartModel();
                this.barModel = new BarChartModel();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createLineModels() {
        this.lineModel1 = initLinearModel();
        this.lineModel1.setTitle("Courbe évolutive des ventes et approvisionnement");
        this.lineModel1.setLegendPosition("e");
        this.lineModel1.getAxes().put(AxisType.X, new CategoryAxis("Mois"));
        this.lineModel1.getAxes().put(AxisType.Y, new CategoryAxis("Montant"));
        Axis yAxis = this.lineModel1.getAxis(AxisType.Y);
        yAxis.setMin(0);

        this.lineModel2 = initCategoryModel();
        this.lineModel2.setTitle("Courbe évolutive des ventes et approvisionnements aucours de l'année " + this.annee.getNom());
        this.lineModel2.setLegendPosition("e");
        this.lineModel2.setShowPointLabels(true);
        this.lineModel2.getAxes().put(AxisType.X, new CategoryAxis("Mois"));
        yAxis = this.lineModel2.getAxis(AxisType.Y);
        yAxis.setLabel("Montant");
        yAxis.setMin(0);
    }

    private LineChartModel initLinearModel() {
        try {
            LineChartModel model = new LineChartModel();

            LineChartSeries series1 = new LineChartSeries();
            series1.setLabel("Ventes");

            LineChartSeries series2 = new LineChartSeries();
            series2.setLabel("Approvisionnement");

            for (AnneeMois a : this.anneeMoises) {

                Integer somme = 0;
                List<Facture> factures = this.factureFacadeLocal.findByMois(a.getIdAnneeMois());
                for (Facture f : factures) {
                    somme += f.getMontantTtc().intValue();
                }

                series1.set(a.getIdmois().getNom(), somme);

                int somme1 = 0;
                List<Stock> stocks = this.stockFacadeLocal.findByMois(a);
                for (Stock s : stocks) {
                    somme1 += s.getMontant().intValue();
                }
                series2.set(a.getIdmois().getNom(), somme1);
            }

            model.addSeries((ChartSeries) series1);
            model.addSeries((ChartSeries) series2);

            return model;
        } catch (Exception e) {
            e.printStackTrace();
            return new LineChartModel();
        }
    }

    private LineChartModel initCategoryModel() {
        try {
            LineChartModel model = new LineChartModel();

            LineChartSeries series1 = new LineChartSeries();
            series1.setLabel("Ventes");

            LineChartSeries series2 = new LineChartSeries();
            series2.setLabel("Approvisionnement");

            for (AnneeMois a : this.anneeMoises) {

                Integer somme = 0;
                List<Facture> factures = this.factureFacadeLocal.findByMois(a.getIdAnneeMois());
                for (Facture f : factures) {
                    somme += f.getMontantTtc().intValue();
                }

                series1.set(a.getIdmois().getNom(), somme);

                int somme1 = 0;
                List<Stock> stocks = this.stockFacadeLocal.findByMois(a);
                for (Stock s : stocks) {
                    somme1 += s.getMontant().intValue();
                }
                series2.set(a.getIdmois().getNom(), somme1);
            }

            model.addSeries((ChartSeries) series1);
            model.addSeries((ChartSeries) series2);

            return model;
        } catch (Exception e) {
            e.printStackTrace();
            return new LineChartModel();
        }
    }

    private BarChartModel initBarModel() {
        try {
            BarChartModel model = new BarChartModel();

            BarChartSeries series1 = new BarChartSeries();
            series1.setLabel("Ventes");

            BarChartSeries series2 = new BarChartSeries();
            series2.setLabel("Approvisionnement");

            for (AnneeMois a : this.anneeMoises) {

                int somme = 0;
                List<Facture> factures = this.factureFacadeLocal.findByMois(a.getIdAnneeMois());
                for (Facture f : factures) {
                    somme += f.getMontantTtc().intValue();
                }

                series1.set(a.getIdmois().getNom(), somme);

                int somme1 = 0;
                List<Stock> stocks = this.stockFacadeLocal.findByMois(a);
                for (Stock s : stocks) {
                    somme1 += s.getMontant().intValue();
                }
                series2.set(a.getIdmois().getNom(), somme1);
            }

            model.addSeries((ChartSeries) series1);
            model.addSeries((ChartSeries) series2);

            return model;
        } catch (Exception e) {
            e.printStackTrace();
            return new BarChartModel();
        }
    }

    private void createBarModel() {
        this.barModel = initBarModel();

        this.barModel.setTitle("Histogramme des ventes et approvisonnements de l'année " + this.annee.getNom());
        this.barModel.setLegendPosition("ne");

        Axis xAxis = this.barModel.getAxis(AxisType.X);
        xAxis.setLabel("Mois");

        Axis yAxis = this.barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Montant");
        yAxis.setMin(0);
    }

    public void updateClient() {
        this.factures.clear();
        this.imprimer = true;
    }

    public void updateDate() {
        updateClient();
    }

    public void rechercher() {
        try {
            this.imprimer = true;
            if (this.critere == 1) {
                this.factures = this.factureFacadeLocal.findAllRange();
                ajaxHide();
                if (!this.factures.isEmpty()) {
                    this.imprimer = false;
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
                    this.imprimer = false;
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
                    this.imprimer = false;
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
                    this.imprimer = false;
                    notifySuccess();
                } else {
                    notifyFail();
                }
                return;
            }
            this.factures = this.factureFacadeLocal.findByInterval(this.dateDebut, this.dateFin);
            ajaxHide();
            if (!this.factures.isEmpty()) {
                this.imprimer = false;
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
        /* 305 */ RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').hide()");
    }

    public void notifySuccess() {
        /* 309 */ this.routine.feedBack("information", "/resources/tool_images/success.png", this.routine.localizeMessage("operation_reussie"));
        /* 310 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
    }

    public void notifyFail() {
        /* 314 */ this.routine.feedBack("avertissement", "/resources/tool_images/error.png", this.routine.localizeMessage("aucune_ligne_trouvee"));
        /* 315 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
    }

    public void prepareview() {
        try {
            /* 320 */ if (this.facture != null) {
                /* 321 */ List<Commande> c = this.commandeFacadeLocal.findByFacture(this.facture);
                /* 322 */ if (!c.isEmpty()) {
                    /* 323 */ RequestContext.getCurrentInstance().execute("PF('FactureDetailDialog').show()");
                    return;
                }
                /* 326 */ this.facture.setCommandeList(c);
                /* 327 */ this.routine.feedBack("avertissement", "/resources/tool_images/error.png", this.routine.localizeMessage("liste_article_vide"));
                /* 328 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
            } else {

                /* 331 */ this.routine.feedBack("avertissement", "/resources/tool_images/error.png", this.routine.localizeMessage("not_row_selected"));
                /* 332 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
            }
            /* 334 */        } catch (Exception e) {
            /* 335 */ this.routine.catchException(e, this.routine.localizeMessage("echec_operation"));
            /* 336 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
        }
    }

    public void initPrinter(Facture f) {
        /* 341 */ this.facture = f;
        /* 342 */ print();
    }

    public void initView(Facture f) {
        /* 346 */ this.facture = f;
        /* 347 */ prepareview();
    }

    public void print() {
        try {
            /* 352 */ if (!Utilitaires.isAccess(Long.valueOf(26L))) {
                /* 353 */ this.routine.feedBack("information", "/resources/tool_images/error.png", this.routine.localizeMessage("acces_refuse"));
                /* 354 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
                /* 355 */ this.facture = null;

                return;
            }
            /* 359 */ if (this.facture != null) {
                /* 360 */ this.printDialogTitle = this.routine.localizeMessage("facture");
                /* 361 */ List<Commande> c = this.commandeFacadeLocal.findByFacture(this.facture);
                /* 362 */ this.facture.setCommandeList(c);
                /* 363 */ this.fileName = PrintUtils.printFacture(this.facture, SessionMBean.getParametrage());
                /* 364 */ RequestContext.getCurrentInstance().execute("PF('FactureImprimerDialog').show()");
            } else {
                /* 366 */ this.routine.feedBack("information", "/resources/tool_images/error.png", this.routine.localizeMessage("not_row_selected"));
                /* 367 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
            }
            /* 369 */        } catch (Exception e) {
            /* 370 */ this.routine.catchException(e, this.routine.localizeMessage("echec_operation"));
            /* 371 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
        }
    }

    public void printHistoric() {
        try {
            /* 377 */ this.imprimer = Boolean.valueOf(true);
            /* 378 */ SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            /* 379 */ if (this.critere == 1) {
                /* 380 */ this.factures = this.factureFacadeLocal.findAllRange();
                /* 381 */ ajaxHide();
                /* 382 */ if (!this.factures.isEmpty()) {
                    /* 383 */ this.imprimer = Boolean.valueOf(false);

                    /* 385 */ this.printDialogTitle = "Historique des ventes";
                    /* 386 */ String titre = "HISTORIQUE DE TOUTES LES VENTES";

                    /* 388 */ this.fileName = "Historic_total_vente_" + sdf.format(new Date(System.currentTimeMillis())) + ".pdf";
                    /* 389 */ PrintUtils.printHistoric(this.factures, SessionMBean.getParametrage(), titre, this.fileName);
                    /* 390 */ RequestContext.getCurrentInstance().execute("PF('FactureImprimerDialog').show()");
                } else {
                    /* 392 */ notifyFail();
                }
                return;
            }
            /* 395 */ if (this.critere == 2) {
                /* 396 */ this.anneeMois = this.anneeMoisFacadeLocal.find(this.anneeMois.getIdAnneeMois());
                /* 397 */ this.factures = this.factureFacadeLocal.findByMois(this.anneeMois.getIdAnneeMois().intValue());
                /* 398 */ ajaxHide();
                /* 399 */ if (!this.factures.isEmpty()) {
                    /* 400 */ this.imprimer = Boolean.valueOf(false);

                    /* 402 */ this.printDialogTitle = "Historique des ventes de " + this.anneeMois.getIdmois().getNom() + "/" + this.anneeMois.getIdannee().getNom();
                    /* 403 */ String titre = "HISTORIQUE DES VENTES : " + this.anneeMois.getIdmois().getNom() + "/" + this.anneeMois.getIdannee().getNom();

                    /* 405 */ this.fileName = "Historic_mensuel_vente_" + this.anneeMois.getIdmois().getNom() + "_" + this.anneeMois.getIdannee().getNom() + ".pdf";
                    /* 406 */ PrintUtils.printHistoric(this.factures, SessionMBean.getParametrage(), titre, this.fileName);
                    /* 407 */ RequestContext.getCurrentInstance().execute("PF('FactureImprimerDialog').show()");
                } else {
                    /* 409 */ notifyFail();
                }
                return;
            }
            /* 412 */ if (this.critere == 3) {
                /* 413 */ if (this.date_interval == 2) {
                    /* 414 */ this.factures = this.factureFacadeLocal.findClientDate(this.client.getIdclient().intValue(), this.date);
                } else {
                    /* 416 */ this.factures = this.factureFacadeLocal.findClientInterval(this.client.getIdclient().intValue(), this.dateDebut, this.dateFin);
                }
                /* 418 */ ajaxHide();
                /* 419 */ if (!this.factures.isEmpty()) {
                    /* 420 */ this.imprimer = Boolean.valueOf(false);

                    /* 422 */ if (this.date_interval == 2) {
                        /* 423 */ this.printDialogTitle = "Historique des achats du client " + ((Facture) this.factures.get(0)).getIdclient().getNom() + " à la date du " + sdf.format(this.date);
                        /* 424 */ String str = "HISTORIQUE DES OPERATIONS DU : " + sdf.format(this.date);

                        /* 426 */ this.fileName = "Historic_des_achats_" + ((Facture) this.factures.get(0)).getIdclient().getNom() + "_" + sdf.format(this.date) + ".pdf";
                        /* 427 */ PrintUtils.printHistoric(this.factures, SessionMBean.getParametrage(), str, this.fileName, ((Facture) this.factures.get(0)).getIdclient(), "" + sdf.format(this.date));
                        /* 428 */ RequestContext.getCurrentInstance().execute("PF('FactureImprimerDialog').show()");

                        return;
                    }
                    /* 432 */ this.printDialogTitle = "Historique des achats du client " + ((Facture) this.factures.get(0)).getIdclient().getNom() + " du " + sdf.format(this.dateDebut) + " Au " + sdf.format(this.dateFin);
                    /* 433 */ String titre = "HISTORIQUE DES VENTES DU : " + sdf.format(this.dateDebut) + " AU " + sdf.format(this.dateFin);

                    /* 435 */ this.fileName = "Historic_des_achats_" + ((Facture) this.factures.get(0)).getIdclient().getNom() + "_" + sdf.format(this.dateDebut) + "_Au_" + sdf.format(this.dateFin) + ".pdf";
                    /* 436 */ PrintUtils.printHistoric(this.factures, SessionMBean.getParametrage(), titre, this.fileName, ((Facture) this.factures.get(0)).getIdclient(), "" + sdf.format(this.dateDebut) + " Au " + sdf.format(this.dateFin));
                    /* 437 */ RequestContext.getCurrentInstance().execute("PF('FactureImprimerDialog').show()");
                } else {
                    /* 439 */ notifyFail();
                }
                return;
            }
            /* 442 */ if (this.critere == 4) {
                /* 443 */ this.factures = this.factureFacadeLocal.findAllDate(this.date);
                /* 444 */ ajaxHide();
                /* 445 */ if (!this.factures.isEmpty()) {
                    /* 446 */ this.imprimer = Boolean.valueOf(false);

                    /* 448 */ this.printDialogTitle = "Historique des ventes du " + sdf.format(this.date);
                    /* 449 */ String titre = "HISTORIQUE DE TOUTES LES VENTES : " + sdf.format(this.date);

                    /* 451 */ this.fileName = "Historic_des_vente_" + sdf.format(this.date) + ".pdf";
                    /* 452 */ PrintUtils.printHistoric(this.factures, SessionMBean.getParametrage(), titre, this.fileName);
                    /* 453 */ RequestContext.getCurrentInstance().execute("PF('FactureImprimerDialog').show()");
                } else {
                    /* 455 */ notifyFail();
                }
                return;
            }
            /* 459 */ this.factures = this.factureFacadeLocal.findByInterval(this.dateDebut, this.dateFin);
            /* 460 */ ajaxHide();
            /* 461 */ if (!this.factures.isEmpty()) {
                /* 462 */ this.imprimer = Boolean.valueOf(false);

                /* 464 */ this.printDialogTitle = "Historique des ventes du " + sdf.format(this.dateDebut) + " Au " + sdf.format(this.dateFin);
                /* 465 */ String titre = "HISTORIQUE DES VENTES DU " + sdf.format(this.dateDebut) + " AU " + sdf.format(this.dateFin);

                /* 467 */ this.fileName = "Historic_des_vente_" + sdf.format(this.dateDebut) + "_au_" + sdf.format(this.dateFin) + ".pdf";
                /* 468 */ PrintUtils.printHistoric(this.factures, SessionMBean.getParametrage(), titre, this.fileName);
                /* 469 */ RequestContext.getCurrentInstance().execute("PF('FactureImprimerDialog').show()");
            } else {
                /* 471 */ notifyFail();
            }

            return;
            /* 475 */        } catch (Exception e) {
            /* 476 */ ajaxHide();
            /* 477 */ this.routine.catchException(e, this.routine.localizeMessage("echec_operation"));
            /* 478 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
            return;
        }
    }
}
