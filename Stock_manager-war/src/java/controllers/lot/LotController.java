package controllers.lot;

import entities.Commande;
import entities.Lot;
import entities.Produit;
import entities.StockProduit;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import utils.SessionMBean;
import utils.Utilitaires;

@ManagedBean
@ViewScoped
public class LotController
        extends AbstractLotController
        implements Serializable {

    @PostConstruct
    private void init() {
        /*  38 */ this.produit = new Produit();
        try {
            /*  40 */ this.produits = this.produitFacadeLocal.findByPerissable(true);
            /*  41 */        } catch (Exception e) {
            /*  42 */ e.printStackTrace();
        }
    }

    public void prepareCreate() {
        if (!Utilitaires.isAccess(Long.valueOf(44L))) {
            notifyError("acces_refuse");
            return;
        }
        /*  52 */ this.mode = "Create";
        /*  53 */ this.produit = new Produit();
        /*  54 */ this.lot = new Lot();
        /*  55 */ this.lot.setQuantite(Double.valueOf(0.0D));
        /*  56 */ this.lot.setStockCritique(Double.valueOf(0.0D));
        /*  57 */ this.lot.setTva(SessionMBean.getParametrage().getTauxTva());
        /*  58 */ this.lot.setEtat(Boolean.valueOf(true));
        /*  59 */ this.disableProduct = false;
        /*  60 */ this.dateRequired = true;
        /*  61 */ RequestContext.getCurrentInstance().execute("PF('LotCreerDialog').show()");
    }

    public void prepareEdit() {
        if (!Utilitaires.isAccess(Long.valueOf(45L))) {
            notifyError("acces_refuse");
            return;
        }
        if (this.lot.equals(null)) {
            notifyError("not_row_selected");
            return;
        }
        this.mode = "Edit";
        this.disableProduct = true;
        /*  77 */ this.produit = this.lot.getIdproduit();
        /*  78 */ this.dateRequired = true;
        if (!this.lot.getIdproduit().getPerissable().booleanValue()) {
            this.dateRequired = false;
            if (!this.produits.contains(this.produit)) {
                this.produits.add(this.produit);
            }
        }

        if (!this.lot.getIdproduit().getEtat().booleanValue()
                && !this.produits.contains(this.lot.getIdproduit())) {
            this.produits.add(this.lot.getIdproduit());
        }

        /*  92 */ RequestContext.getCurrentInstance().execute("PF('LotCreerDialog').show()");
    }

    public void updateData() {
        try {
            /*  97 */ if (this.produit.getIdproduit() != null) {
                /*  98 */ this.produit = this.produitFacadeLocal.find(this.produit.getIdproduit());

                /* 100 */ this.lot.setPrixAchat(this.produit.getPrixachat());
                /* 101 */ this.lot.setPrixVente(this.produit.getPrixMaximal());
                /* 102 */ this.lot.setTva(this.produit.getTva());
            }
            /* 104 */        } catch (Exception e) {
            /* 105 */ e.printStackTrace();
        }
    }

    public void create() {
        try {
            if (this.mode.equals("Create")) {

                if (this.lot.getDatePeremption().after(this.lot.getDateFabrication()) || this.lot.getDatePeremption().equals(this.lot.getDateFabrication())) {
                    this.lot.setIdlot(this.lotFacadeLocal.nextLongVal());
                    /* 115 */ this.lot.setIdproduit(this.produit);
                    /* 116 */ this.lot.setDateEnregistrement(new Date());
                    /* 117 */ this.lot.setEtat(Boolean.valueOf(true));

                    if (this.showBailleur) {
                        this.lot.setIdbailleur(this.bailleur);
                    } else {
                        this.lot.setIdbailleur(this.bailleurFacadeLocal.find(Integer.valueOf(1)));
                    }

                    if (this.showUser) {
                        this.lot.setIduserP(this.userP);
                    } else {
                        this.lot.setIduserP(this.userPFacadeLocal.find(Integer.valueOf(1)));
                    }

                    /* 131 */ this.lotFacadeLocal.create(this.lot);

                    /* 133 */ Utilitaires.saveOperation(this.mouchardFacadeLocal, "Enregistrement du lot : " + this.lot.getNumeroLot() + " de l'article -> " + this.produit.getNom(), SessionMBean.getUserAccount());
                    /* 134 */ this.lot = null;
                    /* 135 */ this.produit = new Produit();
                    /* 136 */ RequestContext.getCurrentInstance().execute("PF('LotCreerDialog').hide()");
                    /* 137 */ notifySuccess();
                    return;
                }
                /* 140 */ notifyError("erreur_ecart_date");
            } /* 142 */ else if (this.lot != null) {
                /* 143 */ this.lotFacadeLocal.edit(this.lot);
                /* 144 */ RequestContext.getCurrentInstance().execute("PF('LotCreerDialog').hide()");
                /* 145 */ notifySuccess();
                /* 146 */ this.produits = this.produitFacadeLocal.findByPerissable(true);
            } else {
                /* 148 */ notifyError("not_row_selected");
            }

            /* 151 */        } catch (Exception e) {
            /* 152 */ notifyFail(e);
        }
    }

    public void delete() {
        try {
            /* 158 */ if (this.lot != null) {
                /* 159 */ if (!Utilitaires.isAccess(Long.valueOf(46L))) {
                    /* 160 */ notifyError("acces_refuse");

                    return;
                }
                /* 164 */ if (!this.lot.getIdproduit().getPerissable().booleanValue()) {
                    /* 165 */ notifyError("impossible_supression_lot_initial");

                    return;
                }
                /* 169 */ List<Commande> cTemp = this.commandeFacadeLocal.findByLot(this.lot.getIdlot());
                /* 170 */ if (!cTemp.isEmpty()) {
                    /* 171 */ notifyError("suppression_impossible");

                    return;
                }
                /* 175 */ List<StockProduit> sTemp = this.stockProduitFacadeLocal.findByLot(this.lot.getIdlot());
                /* 176 */ if (!sTemp.isEmpty()) {
                    /* 177 */ notifyError("suppression_impossible");

                    return;
                }
                /* 181 */ this.lotFacadeLocal.remove(this.lot);
                /* 182 */ Utilitaires.saveOperation(this.mouchardFacadeLocal, "Suppression du lot -> " + this.lot.getNumeroLot() + " de l'article -> " + this.lot.getIdproduit().getNom(), SessionMBean.getUserAccount());
                /* 183 */ this.lot = null;
                /* 184 */ notifySuccess();
            } else {
                /* 186 */ notifyError("not_row_selected");
            }
            /* 188 */        } catch (Exception e) {
            /* 189 */ notifyFail(e);
        }
    }

    public void print() {
        try {
            /* 195 */ if (!Utilitaires.isAccess(Long.valueOf(9L))) {
                /* 196 */ notifyError("acces_refuse");
                return;
            }
            /* 199 */ RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').hide()");
            /* 200 */ RequestContext.getCurrentInstance().execute("PF('LotImprimerDialog').show()");
            /* 201 */        } catch (Exception e) {
            /* 202 */ notifyFail(e);
        }
    }

    public void notifyError(String message) {
        /* 207 */ RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').hide()");
        /* 208 */ this.routine.feedBack("avertissement", "/resources/tool_images/warning.jpeg", this.routine.localizeMessage(message));
        /* 209 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
    }

    public void notifySuccess() {
        /* 213 */ RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').hide()");
        /* 214 */ this.routine.feedBack("information", "/resources/tool_images/success.png", this.routine.localizeMessage("operation_reussie"));
        /* 215 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
    }

    public void notifyFail(Exception e) {
        /* 219 */ RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').show()");
        /* 220 */ this.routine.catchException(e, this.routine.localizeMessage("echec_operation"));
        /* 221 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
    }
}
