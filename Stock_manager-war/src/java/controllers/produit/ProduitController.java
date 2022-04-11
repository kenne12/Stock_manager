package controllers.produit;

import entities.Bailleur;
import entities.Famille;
import entities.Formeproduit;
import entities.Formestockage;
import entities.Fournisseur;
import entities.Lot;
import entities.Produit;
import entities.Unite;
import entities.UserP;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
import utils.JsfUtil;
import utils.PrintUtils;
import utils.SessionMBean;
import utils.Utilitaires;

@ManagedBean
@ViewScoped
public class ProduitController extends AbstractProduitController implements Serializable {

    @PostConstruct
    private void init() {
        this.fournisseur = new Fournisseur();
        this.famille = new Famille();
        this.password.add("momo1234");
        this.password.add("kenne1234");
    }

    public void prepareCreate() {
        if (!Utilitaires.isAccess(13L)) {
            notifyError("acces_refuse");
            return;
        }
        this.mode = "Create";
        this.fournisseur = new Fournisseur();
        this.unite = new Unite();
        this.famille = new Famille();
        this.produit = new Produit();
        this.produit.setQuantite(0.0D);
        this.produit.setStockCritique(0);
        this.produit.setPerissable(true);
        this.produit.setEtat(true);
        this.produit.setQteDosage(0.0D);
        this.produit.setPhoto("article.jpeg");
        this.produit.setPrixachat(0.0D);
        this.produit.setPrixMaximal(0.0D);
        this.produit.setTva(SessionMBean.getParametrage().getTauxTva());

        this.formeproduit = new Formeproduit();
        this.formestockage = new Formestockage();
        this.userP = new UserP();
        this.bailleur = new Bailleur();
        this.lot = new Lot();

        this.showLot = true;
        this.showUser = SessionMBean.getParametrage().getEtatuser();
        this.showBailleur = SessionMBean.getParametrage().getEtatbailleur();
        RequestContext.getCurrentInstance().execute("PF('ArticleCreerDialog').show()");
    }

    public void prepareEdit() {
        this.mode = "Edit";
        try {
            if (!Utilitaires.isAccess((14L))) {
                notifyError("acces_refuse");
                return;
            }
            if (this.produit != null) {
                this.showLot = false;
                this.famille = this.produit.getIdfamille();
                this.unite = this.produit.getIdUnite();
                this.formeproduit = this.produit.getIdforme();
                this.formestockage = this.produit.getIdformeStockage();
                RequestContext.getCurrentInstance().execute("PF('ArticleCreerDialog').show()");
                return;
            }
            notifyError("not_row_select");
        } catch (Exception e) {
            notifyFail(e);
        }
    }

    public void updateLot() {
        if ("Create".equals(this.mode)) {
            this.showLot = this.produit.getPerissable();
            if (!this.showLot) {
                this.showBailleur = this.showUser = false;
            } else {
                if (SessionMBean.getParametrage().getEtatbailleur()) {
                    this.showBailleur = true;
                }
                if (SessionMBean.getParametrage().getEtatuser()) {
                    this.showUser = true;
                }
            }
            this.lot = new Lot();
            return;
        }
        this.showLot = false;
    }

    public void create() {
        try {
            if (this.mode.equals("Create")) {

                if (!this.produitFacadeLocal.findByCode(this.produit.getCode()).isEmpty()) {
                    notifyError("code_article_existant");

                    return;
                }
                if (!Utilitaires.checkBenefice(this.produit.getPrixachat(), this.produit.getPrixMaximal())) {
                    notifyError("prix_achat_vente_incorrect");
                    return;
                }
                this.produit.setIdproduit(this.produitFacadeLocal.nextVal());

                if (this.famille.getIdfamille() != null) {
                    this.produit.setIdfamille(this.famille);
                }

                this.produit.setIdUnite(this.unite);

                if (this.showFormeProduit) {
                    this.produit.setIdforme(this.formeproduit);
                } else {
                    this.produit.setIdforme(this.formeproduitFacadeLocal.find(1));
                }

                if (this.showFormeStockage) {
                    this.produit.setIdformeStockage(this.formestockage);
                } else {
                    this.produit.setIdformeStockage(this.formestockageFacadeLocal.find(1));
                }

                this.ut.begin();

                this.produitFacadeLocal.create(this.produit);

                this.lot.setIdlot(this.lotFacadeLocal.nextLongVal());
                this.lot.setIdproduit(this.produit);
                this.lot.setPrixVente(this.produit.getPrixMaximal());
                this.lot.setPrixAchat(this.produit.getPrixachat());
                this.lot.setQuantite(this.produit.getQuantite());
                this.lot.setStockCritique(Double.valueOf(this.produit.getStockCritique()));
                this.lot.setDateEnregistrement(new Date());
                this.lot.setEtat(true);

                if (this.showBailleur) {
                    this.lot.setIdbailleur(this.bailleur);
                } else {
                    this.lot.setIdbailleur(this.bailleurFacadeLocal.find(1));
                }

                if (this.showUser) {
                    this.lot.setIduserP(this.userP);
                } else {
                    this.lot.setIduserP(this.userPFacadeLocal.find((1)));
                }

                if (this.showLot) {
                    if (this.lot.getDatePeremption().before(this.lot.getDateFabrication())) {
                        Date d1 = this.lot.getDatePeremption();
                        this.lot.setDatePeremption(this.lot.getDateFabrication());
                        this.lot.setDateFabrication(d1);
                    }
                    this.lotFacadeLocal.create(this.lot);
                } else {
                    this.lot.setDateFabrication(null);
                    this.lot.setDatePeremption(null);
                    this.lot.setNumeroLot(this.produit.getCode());
                    this.lotFacadeLocal.create(this.lot);
                }

                Utilitaires.saveOperation(this.mouchardFacadeLocal, "Enregistrement du produit : " + this.produit.getNom(), SessionMBean.getUserAccount());

                this.ut.commit();
                this.fournisseur = null;
                this.famille = new Famille();
                this.produit = new Produit();

                this.modifier = this.supprimer = this.detail = true;

                RequestContext.getCurrentInstance().execute("PF('ArticleCreerDialog').hide()");
                notifySuccess();
            } else if (this.produit != null) {

                Produit p = this.produitFacadeLocal.find(this.produit.getIdproduit());

                if (!p.getCode().equals(this.produit.getCode()) && !this.produitFacadeLocal.findByCode(this.produit.getCode()).isEmpty()) {
                    notifyError("code_article_existant");
                    return;
                }

                if (!Objects.equals(this.famille.getIdfamille(), p.getIdfamille().getIdfamille())) {
                    this.produit.setIdfamille(this.familleFacadeLocal.find(this.famille.getIdfamille()));
                }
                this.produit.setIdUnite(this.uniteFacadeLocal.find(this.unite.getIdUnite()));

                if (this.showFormeProduit) {
                    this.produit.setIdforme(this.formeproduitFacadeLocal.find(this.formeproduit.getIdforme()));
                }

                if (this.showFormeStockage) {
                    this.produit.setIdformeStockage(this.formestockageFacadeLocal.find(this.formestockage.getIdformeStockage()));
                }

                this.ut.begin();
                this.produitFacadeLocal.edit(this.produit);
                Utilitaires.saveOperation(this.mouchardFacadeLocal, "Modification du produit : " + this.produit.getNom() + " Ancienne quantité : " + p.getQuantite() + " ; Nouvelle quantité : " + this.produit.getQuantite(), SessionMBean.getUserAccount());
                this.ut.commit();

                this.modifier = this.supprimer = this.detail = true;
                this.produit = new Produit();
                RequestContext.getCurrentInstance().execute("PF('ArticleCreerDialog').hide()");
                notifySuccess();
            } else {
                notifyError("not_row_select");
            }
        } catch (Exception e) {
            notifyFail(e);
        }
    }

    public void notifyError(String message) {
        RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').hide()");
        this.routine.feedBack("avertissement", "/resources/tool_images/warning.jpeg", this.routine.localizeMessage(message));
        RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
    }

    public void notifySuccess() {
        RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').hide()");
        this.routine.feedBack("information", "/resources/tool_images/success.png", this.routine.localizeMessage("operation_reussie"));
        RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
    }

    public void notifyFail(Exception e) {
        RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').hide()");
        this.routine.catchException(e, this.routine.localizeMessage("echec_operation"));
        RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
    }

    public void checkSession() {
        try {
            if (!"".equals(this.sessionPassword)) {
                if (this.password.contains(this.sessionPassword)) {
                    HttpSession session1 = SessionMBean.getSession();
                    session1.setAttribute("session", true);
                    this.session = false;
                } else {
                    JsfUtil.addErrorMessage("Mot de passe incorrect");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete() {
        try {
            if (this.produit != null) {

                if (!Utilitaires.isAccess(15L)) {
                    notifyError("acces_refuse");
                    return;
                }
                this.ut.begin();
                this.produitFacadeLocal.remove(this.produit);
                Utilitaires.saveOperation(this.mouchardFacadeLocal, "Suppresion du produit : " + this.produit.getNom(), SessionMBean.getUserAccount());
                this.ut.commit();

                this.produit = null;

                notifySuccess();
            } else {
                notifyError("not_row_selected");
            }
        } catch (Exception e) {
            notifyFail(e);
        }
    }

    public void print() {
        try {
            if (!Utilitaires.isAccess(29L)) {
                notifyError("acces_refuse");
                return;
            }
            this.fileName = PrintUtils.printProductReport(this.produits);
            RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').hide()");
            RequestContext.getCurrentInstance().execute("PF('ArticleImprimerDialog').show()");
        } catch (Exception e) {
            notifyFail(e);
        }
    }

    public void printStockGeneral() {
        try {
            if (!Utilitaires.isAccess(32l)) {
                notifyError("acces_refuse");
                return;
            }
            this.fileName1 = PrintUtils.printGeneralStockReport(this.produits);
            RequestContext.getCurrentInstance().execute("PF('StockImprimerDialog').show()");
        } catch (Exception e) {
            notifyFail(e);
        }
    }

    public void printInventory() {
        try {
            if (!Utilitaires.isAccess(31L)) {
                notifyError("acces_refuse");
                return;
            }
            this.fileName1 = PrintUtils.printInventoryReport(this.produits);
            RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').hide()");
            RequestContext.getCurrentInstance().execute("PF('InventaireImprimerDialog').show()");
        } catch (Exception e) {
            notifyFail(e);
        }
    }

    public void printSousStock() {
        try {
            if (!Utilitaires.isAccess(30L)) {
                notifyError("acces_refuse");
                return;
            }
            this.fileName2 = PrintUtils.printCritickStockReport(this.produits1);
            RequestContext.getCurrentInstance().execute("PF('StockImprimerDialog').show()");
        } catch (Exception e) {
            notifyFail(e);
        }
    }
}
