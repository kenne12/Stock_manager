package controllers.synthesemensuelle;
 
 import controllers.synthesemensuelle.AbstractSyntheseMController;
import entities.Commande;
import entities.Privilege;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import utils.JsfUtil;
import utils.PrintUtils;
import utils.SessionMBean;

@ManagedBean
@ViewScoped
public class SyntheseMController
        extends AbstractSyntheseMController {

    @PostConstruct
    private void init() {
        /*  33 */ this.commandes.clear();
    }

    public void find() {
        /*  37 */ this.commandes.clear();

        try {
            /*  40 */ if (this.anneeMois.getIdAnneeMois() != null) {
                /*  41 */ this.anneeMois = this.anneeMoisFacadeLocal.find(this.anneeMois.getIdAnneeMois());
                /*  42 */ this.commandes = this.commandeFacadeLocal.findByMois(this.anneeMois);
                /*  43 */ this.stockes = this.stockFacadeLocal.findByMois(this.anneeMois);
                /*  44 */ this.salaires = this.salaireFacadeLocal.findByMois(this.anneeMois);
                /*  45 */ this.depenses = this.depenseFacadeLocal.findBymois(this.anneeMois);
                /*  46 */ if (this.commandes.isEmpty()) {
                    /*  47 */ this.showPrintButton = true;
                } else {
                    /*  49 */ this.showPrintButton = false;
                }

            }
            /*  53 */        } catch (Exception e) {
            /*  54 */ e.printStackTrace();
        }
    }

    public void printReport() {
        try {
            /*  60 */ Privilege p = this.privilegeFacadeLocal.findByUser(SessionMBean.getUserAccount().getIdutilisateur().intValue(), 1);
            /*  61 */ if (p != null) {
                /*  62 */ this.showReportPrintDialog = true;
            } else {
                /*  64 */ p = new Privilege();
                /*  65 */ p = this.privilegeFacadeLocal.findByUser(SessionMBean.getUserAccount().getIdutilisateur().intValue(), 17);
                /*  66 */ if (p != null) {
                    /*  67 */ this.showReportPrintDialog = true;
                } else {
                    /*  69 */ this.showReportPrintDialog = false;
                    /*  70 */ JsfUtil.addErrorMessage("Vous n'avez pas le privilege d'éditer le rapport mensuel d'activité");

                    return;
                }
            }
            /*  75 */ this.fileName = PrintUtils.printSynthesis(this.anneeMois, this.commandes, this.depenses, this.salaires, this.stockes, "SYNTHESE DES ACTIVITES : " + this.anneeMois.getIdmois().getNom() + "-" + this.anneeMois.getIdannee().getNom());
            /*  76 */        } catch (Exception e) {
            /*  77 */ e.printStackTrace();
        }
    }

    public String calculSommeMontant() {
        /*  82 */ if (this.commandes.isEmpty()) {
            /*  83 */ return "";
        }
        /*  85 */ Double resultat = Double.valueOf(0.0D);
        /*  86 */ for (Commande c : this.commandes) {
            /*  87 */ resultat = Double.valueOf(resultat.doubleValue() + c.getMontant().doubleValue() * c.getQuantite().doubleValue());
        }
        /*  89 */ return JsfUtil.formaterStringMoney(resultat);
    }

    public String calculSommeQuantite() {
        /*  93 */ if (this.commandes.isEmpty()) {
            /*  94 */ return "";
        }
        /*  96 */ Double resultat = Double.valueOf(0.0D);
        /*  97 */ for (Commande c : this.commandes) {
            /*  98 */ resultat = Double.valueOf(resultat.doubleValue() + c.getQuantite().doubleValue());
        }
        /* 100 */ return resultat.toString();
    }

    public void filterMois() {
        try {
            /* 105 */ this.anneeMoises.clear();
            /* 106 */ if (this.annee.getIdannee() != null) {
                /* 107 */ this.anneeMoises = this.anneeMoisFacadeLocal.findByAnneeEtat(this.annee, true);
            }
            /* 109 */        } catch (Exception e) {
            /* 110 */ e.printStackTrace();
        }
    }
}


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-war.war!\WEB-INF\classes\controllers\synthesemensuelle\SyntheseMController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
