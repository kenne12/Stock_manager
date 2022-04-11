package controllers.statisticclient;

import controllers.statisticclient.AbstractStatisticclientController;
import entities.AnneeMois;
import entities.Client;
import entities.Commande;
import entities.Facture;
import entities.Stock;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import utils.SessionMBean;

@ManagedBean
@ViewScoped
public class StatisticclientController
        extends AbstractStatisticclientController
        implements Serializable {

    @PostConstruct
    private void init() {
        try {
            /*  43 */ this.clients = this.clientFacadeLocal.findAllRange();
            /*  44 */ this.annee = SessionMBean.getMois().getIdannee();
            /*  45 */ this.anneeMoises = this.anneeMoisFacadeLocal.findByAnnee(this.annee);

            /*  47 */ this.selectedClients.add(this.clients.get(0));
            /*  48 */ update();
            /*  49 */        } catch (Exception e) {
            /*  50 */ e.printStackTrace();
        }
    }

    public void update() {
        try {
            /*  56 */ if (!this.annee.getIdannee().equals(Integer.valueOf(0))) {
                /*  57 */ this.annee = this.anneeFacadeLocal.find(this.annee.getIdannee());
                /*  58 */ this.anneeMoises = this.anneeMoisFacadeLocal.findByAnnee(this.annee);
                /*  59 */ this.factures = this.factureFacadeLocal.findByAnnee(this.annee.getIdannee().intValue());
                /*  60 */ if (!this.selectedClients.isEmpty()) {
                    /*  61 */ createLineModels();
                    return;
                }
                /*  64 */ this.lineModel2 = new LineChartModel();
            }
            /*  66 */ this.lineModel2 = new LineChartModel();
            /*  67 */        } catch (Exception e) {
            /*  68 */ e.printStackTrace();
        }
    }

    private void createLineModels() {
        /*  73 */ this.lineModel2 = initCategoryModel();
        /*  74 */ this.lineModel2.setTitle("Courbe évolutive des achats des clients aucours de l'année : " + this.annee.getNom());
        /*  75 */ this.lineModel2.setLegendPosition("e");
        /*  76 */ this.lineModel2.setShowPointLabels(true);
        /*  77 */ this.lineModel2.getAxes().put(AxisType.X, new CategoryAxis("Mois"));
        /*  78 */ Axis yAxis = this.lineModel2.getAxis(AxisType.Y);
        /*  79 */ yAxis.setLabel("Montant");
        /*  80 */ yAxis.setMin(Integer.valueOf(0));
    }

    private LineChartModel initLinearModel() {
        try {
            /*  87 */ LineChartModel model = new LineChartModel();

            /*  89 */ LineChartSeries series1 = new LineChartSeries();
            /*  90 */ series1.setLabel("Ventes");

            /*  92 */ LineChartSeries series2 = new LineChartSeries();
            /*  93 */ series2.setLabel("Approvisionnement");

            /*  95 */ for (AnneeMois a : this.anneeMoises) {

                /*  97 */ Integer somme = Integer.valueOf(0);
                /*  98 */ List<Facture> factures = this.factureFacadeLocal.findByMois(a.getIdAnneeMois().intValue());
                /*  99 */ for (Facture f : factures) {
                    /* 100 */ somme = Integer.valueOf(somme.intValue() + f.getMontantTtc().intValue());
                }

                /* 103 */ series1.set(a.getIdmois().getNom(), somme);

                /* 105 */ int somme1 = 0;
                /* 106 */ List<Stock> stocks = this.stockFacadeLocal.findByMois(a);
                /* 107 */ for (Stock s : stocks) {
                    /* 108 */ somme1 += s.getMontant().intValue();
                }
                /* 110 */ series2.set(a.getIdmois().getNom(), Integer.valueOf(somme1));
            }

            /* 113 */ model.addSeries((ChartSeries) series1);
            /* 114 */ model.addSeries((ChartSeries) series2);

            /* 116 */ return model;
        } /* 118 */ catch (Exception e) {
            /* 119 */ e.printStackTrace();
            /* 120 */ return new LineChartModel();
        }
    }

    private LineChartModel initCategoryModel() {
        try {
            /* 127 */ LineChartModel model = new LineChartModel();

            /* 129 */ for (Client c : this.selectedClients) {
                /* 130 */ LineChartSeries series = new LineChartSeries();
                /* 131 */ series.setLabel("" + c.getNom());

                /* 133 */ for (AnneeMois a : this.anneeMoises) {
                    /* 134 */ Double somme = Double.valueOf(0.0D);
                    /* 135 */ for (Facture f : this.factures) {
                        /* 136 */ if (f.getIdclient().equals(c) && f.getIdAnneeMois().equals(a)) {
                            /* 137 */ somme = Double.valueOf(somme.doubleValue() + f.getMontantTtc().doubleValue());
                        }
                    }
                    /* 140 */ series.set(a.getIdmois().getNom(), somme);
                }

                /* 143 */ model.addSeries((ChartSeries) series);
            }

            /* 146 */ return model;
            /* 147 */        } catch (Exception e) {
            /* 148 */ e.printStackTrace();
            /* 149 */ return new LineChartModel();
        }
    }

    public void ajaxHide() {
        /* 154 */ RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').hide()");
    }

    public void notifySuccess() {
        /* 158 */ this.routine.feedBack("information", "/resources/tool_images/success.png", this.routine.localizeMessage("operation_reussie"));
        /* 159 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
    }

    public void notifyFail() {
        /* 163 */ this.routine.feedBack("avertissement", "/resources/tool_images/error.png", this.routine.localizeMessage("aucune_ligne_trouvee"));
        /* 164 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
    }

    public void prepareview() {
        try {
            /* 169 */ if (this.facture != null) {
                /* 170 */ List<Commande> c = this.commandeFacadeLocal.findByFacture(this.facture);
                /* 171 */ if (!c.isEmpty()) {
                    /* 172 */ RequestContext.getCurrentInstance().execute("PF('FactureDetailDialog').show()");
                    return;
                }
                /* 175 */ this.facture.setCommandeList(c);
                /* 176 */ this.routine.feedBack("avertissement", "/resources/tool_images/error.png", this.routine.localizeMessage("liste_article_vide"));
                /* 177 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
            } else {

                /* 180 */ this.routine.feedBack("avertissement", "/resources/tool_images/error.png", this.routine.localizeMessage("not_row_selected"));
                /* 181 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
            }
            /* 183 */        } catch (Exception e) {
            /* 184 */ this.routine.catchException(e, this.routine.localizeMessage("echec_operation"));
            /* 185 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
        }
    }
}
