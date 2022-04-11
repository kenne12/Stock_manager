package controllers.syntheannuelle;

import controllers.syntheannuelle.AbstractSyntheseAnController;
import entities.AnneeMois;
import entities.Commande;
import entities.Privilege;
import java.util.HashMap;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import utils.JsfUtil;
import utils.PrintUtils;
import utils.SessionMBean;

@ManagedBean
@ViewScoped
public class SyntheseAnController
        extends AbstractSyntheseAnController {

    @PostConstruct
    private void init() {
        /*  35 */ this.commandes.clear();
    }

    public void find() {
        /*  39 */ this.commandes.clear();

        try {
            /*  42 */ if (this.annee.getIdannee() != null) {

                /*  44 */ this.annee = this.anneeFacadeLocal.find(this.annee.getIdannee());

                /*  46 */ this.anneeMoises = this.anneeMoisFacadeLocal.findByAnnee(this.annee);

                /*  48 */ this.mapAchat = new HashMap<>();
                /*  49 */ this.mapDepense = new HashMap<>();
                /*  50 */ this.mapSalaire = new HashMap<>();
                /*  51 */ this.mapVente = new HashMap<>();

                /*  53 */ for (AnneeMois a : this.anneeMoises) {

                    /*  55 */ this.mapAchat.put(a, this.stockFacadeLocal.findByMois(a));
                    /*  56 */ this.mapVente.put(a, this.commandeFacadeLocal.findByMois(a));
                    /*  57 */ this.mapSalaire.put(a, this.salaireFacadeLocal.findByMois(a));
                    /*  58 */ this.mapDepense.put(a, this.depenseFacadeLocal.findBymois(a));
                }

                /*  61 */ if (this.mapVente.isEmpty()) {
                    /*  62 */ this.showPrintButton = true;
                } else {
                    /*  64 */ this.showPrintButton = false;
                }

            }
            /*  68 */        } catch (Exception e) {
            /*  69 */ e.printStackTrace();
        }
    }

    public void printReport() {
        try {
            /*  75 */ Privilege p = this.privilegeFacadeLocal.findByUser(SessionMBean.getUserAccount().getIdutilisateur().intValue(), 1);
            /*  76 */ if (p != null) {
                /*  77 */ this.showReportPrintDialog = true;
            } else {
                /*  79 */ p = new Privilege();
                /*  80 */ p = this.privilegeFacadeLocal.findByUser(SessionMBean.getUserAccount().getIdutilisateur().intValue(), 17);
                /*  81 */ if (p != null) {
                    /*  82 */ this.showReportPrintDialog = true;
                } else {
                    /*  84 */ this.showReportPrintDialog = false;
                    /*  85 */ JsfUtil.addErrorMessage("Vous n'avez pas le privilege d'éditer la synthèse annuelle");

                    return;
                }
            }
            /*  90 */ this.fileName = PrintUtils.printSynthesisAnnuel(this.annee, this.anneeMoises, this.mapVente, this.mapAchat, this.mapSalaire, this.mapDepense, "SYNTHESE DES ACTIVITES ANNEE : " + this.annee.getNom());
            /*  91 */        } catch (Exception e) {
            /*  92 */ e.printStackTrace();
        }
    }

    public String calculSommeMontant() {
        /*  97 */ if (this.commandes.isEmpty()) {
            /*  98 */ return "";
        }
        /* 100 */ Double resultat = Double.valueOf(0.0D);
        /* 101 */ for (Commande c : this.commandes) {
            /* 102 */ resultat = Double.valueOf(resultat.doubleValue() + c.getMontant().doubleValue() * c.getQuantite().doubleValue());
        }
        /* 104 */ return JsfUtil.formaterStringMoney(resultat);
    }

    public String calculSommeQuantite() {
        /* 108 */ if (this.commandes.isEmpty()) {
            /* 109 */ return "";
        }
        /* 111 */ Double resultat = Double.valueOf(0.0D);
        /* 112 */ for (Commande c : this.commandes) {
            /* 113 */ resultat = Double.valueOf(resultat.doubleValue() + c.getQuantite().doubleValue());
        }
        /* 115 */ return resultat.toString();
    }

    public void filterMois() {
        try {
            /* 120 */ this.anneeMoises.clear();
            /* 121 */ if (this.annee.getIdannee() != null) {
                /* 122 */ this.anneeMoises = this.anneeMoisFacadeLocal.findByAnneeEtat(this.annee, true);
            }
            /* 124 */        } catch (Exception e) {
            /* 125 */ e.printStackTrace();
        }
    }
}
