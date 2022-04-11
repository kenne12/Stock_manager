package controllers.approvisionnement_general;

import controllers.approvisionnement_general.AbstractAchatController;
import entities.Privilege;
import entities.StockProduit;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import utils.JsfUtil;
import utils.PrintUtils;
import utils.SessionMBean;

@ManagedBean
@ViewScoped
public class AchatController
        extends AbstractAchatController {

    @PostConstruct
    private void init() {
        /* 33 */ this.stockProduits = this.stockProduitFacadeLocal.findAllRange();
    }

    public void print() {
        try {
            /* 38 */ Privilege p = this.privilegeFacadeLocal.findByUser(SessionMBean.getUserAccount().getIdutilisateur().intValue(), 1);
            /* 39 */ if (p != null) {
                /* 40 */ this.showUserPrintDialog = Boolean.valueOf(true);
            } else {

                /* 43 */ p = this.privilegeFacadeLocal.findByUser(SessionMBean.getUserAccount().getIdutilisateur().intValue(), 22);
                /* 44 */ if (p != null) {
                    /* 45 */ this.showUserPrintDialog = Boolean.valueOf(true);
                } else {
                    /* 47 */ this.showUserPrintDialog = Boolean.valueOf(false);
                    /* 48 */ JsfUtil.addErrorMessage("Vous n 'avez pas le privilege d'imprimer la fiche de stock");

                    return;
                }
            }
            /* 53 */ if (this.stock != null) {
                /* 54 */ this.stockProduits = this.stockProduitFacadeLocal.findAllRange();
                /* 55 */ this.fileName = PrintUtils.printStock(this.stockProduits);
            } else {
                /* 57 */ JsfUtil.addWarningMessage("Veuillez selectionner un stock");
            }

            /* 60 */        } catch (Exception e) {
            /* 61 */ e.printStackTrace();
        }
    }

    public Double calculTotal() {
        /* 66 */ Double resultat = Double.valueOf(0.0D);
        /* 67 */ if (!this.stockProduits.isEmpty()) {
            /* 68 */ for (StockProduit s : this.stockProduits) {
                /* 69 */ resultat = Double.valueOf(resultat.doubleValue() + s.getPrixAcquisition().doubleValue() * s.getQuantite().doubleValue());
            }
        }
        /* 72 */ return resultat;
    }

    public Double calculQuantite() {
        /* 76 */ Double resultat = Double.valueOf(0.0D);
        /* 77 */ if (!this.stockProduits.isEmpty()) {
            /* 78 */ for (StockProduit s : this.stockProduits) {
                /* 79 */ resultat = Double.valueOf(resultat.doubleValue() + s.getQuantite().doubleValue());
            }
        }
        /* 82 */ return resultat;
    }
}


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-war.war!\WEB-INF\classes\controllers\approvisionnement_general\AchatController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
