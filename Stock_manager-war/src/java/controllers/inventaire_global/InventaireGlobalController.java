package controllers.inventaire_global;

import entities.Inventaire;
import entities.LigneMvtStock;
import entities.Ligneinventaire;
import entities.Lot;
import entities.MvtStock;
import entities.Produit;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.context.RequestContext;
import utils.JsfUtil;
import utils.PrintUtils;
import utils.SessionMBean;
import utils.Utilitaires;

@ManagedBean
@SessionScoped
public class InventaireGlobalController extends AbstractInventaireGlobalController implements Serializable {

    @PostConstruct
    private void init() {
    }

    public void updateMois() {
        try {
            this.anneeMois = this.anneeMoisFacadeLocal.find(this.anneeMois.getIdAnneeMois());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void prepareCreate() {
        try {
            if (!Utilitaires.isAccess(48L)) {
                notifyError("acces_refuse");

                return;
            }
            this.mode = "Create";
            this.valideBtn = this.routine.localizeMessage("valider");
            this.editQuantite = false;

            this.inventaire = new Inventaire();
            this.inventaire.setDateInventaire(SessionMBean.getDateOuverture());

            this.ligneinventaires.clear();
            this.produits.clear();

            String code = "INV-" + this.anneeMois.getIdannee().getNom() + "-" + this.anneeMois.getIdmois().getNom().toUpperCase().substring(0, 2);
            Long nextPayement = this.inventaireFacadeLocal.nextVal(this.anneeMois);
            code = Utilitaires.genererCodeInventaire(code, nextPayement);
            this.inventaire.setCode(code);

            this.lots = Utilitaires.filterNotPeremptLot(this.lotFacadeLocal.findAllRange());

            for (Lot l : this.lots) {
                Ligneinventaire li = new Ligneinventaire();
                li.setIdLigneinventaire(0L);
                li.setPrix(l.getPrixVente());
                li.setIdlot(l);
                li.setQuantiteLogique(l.getQuantite());
                li.setQuantitePhysique(l.getQuantite());
                li.setEcart(0.0D);
                li.setObservation("-");
                this.ligneinventaires.add(li);

                if (!this.produits.contains(l.getIdproduit())) {
                    this.produits.add(l.getIdproduit());
                }
            }
            RequestContext.getCurrentInstance().execute("PF('CommandeCreateDialog').show()");
        } catch (Exception e) {
            this.routine.catchException(e, this.routine.localizeMessage("echec_operation"));
            RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
        }
    }

    public void prepareCreatePartial() {
        try {
            if (!Utilitaires.isAccess(48L)) {
                notifyError("acces_refuse");

                return;
            }
            this.mode = "Create";
            this.valideBtn = "" + this.routine.localizeMessage("valider");
            this.showSelectArticle = false;

            this.inventaire = new Inventaire();
            this.inventaire.setDateInventaire(SessionMBean.getDateOuverture());

            this.ligneinventaires.clear();
            this.produits.clear();

            String code = "INV-" + this.anneeMois.getIdannee().getNom() + "-" + this.anneeMois.getIdmois().getNom().toUpperCase().substring(0, 2);
            Long nextPayement = this.inventaireFacadeLocal.nextVal(this.anneeMois);
            code = Utilitaires.genererCodeInventaire(code, nextPayement);
            this.inventaire.setCode(code);

            RequestContext.getCurrentInstance().execute("PF('CommandeCreateDialog').show()");
        } catch (Exception e) {
            this.routine.catchException(e, this.routine.localizeMessage("echec_operation"));
            RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
        }
    }

    public void prepareAddArticle() {
        try {
            this.lots = Utilitaires.filterNotPeremptLot(this.lotFacadeLocal.findAllRange());
            if ("Create".equals(this.mode)) {
                this.selectedLots.clear();
                RequestContext.getCurrentInstance().execute("PF('ArticleCreateDialog').show()");
                return;
            }
            this.selectedLots.clear();
            this.produits.clear();
            for (Ligneinventaire l : this.ligneinventaires) {
                this.selectedLots.add(l.getIdlot());
                if (!this.produits.contains(l.getIdlot().getIdproduit())) {
                    this.produits.add(l.getIdlot().getIdproduit());
                }
            }
            RequestContext.getCurrentInstance().execute("PF('ArticleCreateDialog').show()");
        } catch (Exception e) {
            notifyFail(e);
        }
    }

    public void prepareEdit() {
        try {
            if (this.inventaire == null) {
                notifyError("not_row_selected");
                return;
            }
            if (!Utilitaires.isAccess(49L)) {
                notifyError("acces_refuse");
                this.inventaire = null;
                return;
            }
            if (this.inventaire.getValidee()) {
                notifyError("inventaire_validee");
                return;
            }
            this.mode = "Edit";
            this.editQuantite = false;
            this.showSelectArticle = true;
            this.valideBtn = "" + this.routine.localizeMessage("valider");
            this.ligneinventaires = this.ligneinventaireFacadeLocal.findByInventaire(this.inventaire.getIdinventaire());
            this.produits.clear();
            if (!this.inventaire.getValidee()) {
                this.showSelectArticle = false;
                for (Ligneinventaire l : this.ligneinventaires) {
                    if (!this.produits.contains(l.getIdlot().getIdproduit())) {
                        this.produits.add(l.getIdlot().getIdproduit());
                    }
                }
            }
            RequestContext.getCurrentInstance().execute("PF('CommandeCreateDialog').show()");
        } catch (Exception e) {
            notifyFail(e);
        }
    }

    public void prepareValidate() {
        try {
            if (this.inventaire == null) {
                notifyError("not_row_selected");
                return;
            }
            if (!Utilitaires.isAccess((49L))) {
                notifyError("acces_refuse");
                this.inventaire = null;
                return;
            }
            if (this.inventaire.getValidee()) {
                notifyError("inventaire_validee");
                return;
            }
            this.mode = "Validate";
            this.editQuantite = true;
            this.showSelectArticle = true;
            this.valideBtn = this.routine.localizeMessage("valider_inventaire");
            this.ligneinventaires = this.ligneinventaireFacadeLocal.findByInventaire(this.inventaire.getIdinventaire());
            this.produits.clear();
            if (!this.inventaire.getValidee()) {
                this.showSelectArticle = false;
                for (Ligneinventaire l : this.ligneinventaires) {
                    if (!this.produits.contains(l.getIdlot().getIdproduit())) {
                        this.produits.add(l.getIdlot().getIdproduit());
                    }
                }
            }
            RequestContext.getCurrentInstance().execute("PF('CommandeCreateDialog').show()");
        } catch (Exception e) {
            notifyFail(e);
        }
    }

    public void prepareview() {
        try {
            if (this.inventaire != null) {
                this.ligneinventaires = this.ligneinventaireFacadeLocal.findByInventaire(this.inventaire.getIdinventaire());
                if (!this.ligneinventaires.isEmpty()) {
                    RequestContext.getCurrentInstance().execute("PF('FactureDetailDialog').show()");
                    return;
                }
                notifyError("liste_article_vide");
            } else {
                notifyError("not_row_selected");
            }
        } catch (Exception e) {
            notifyFail(e);
        }
    }

    public void create() {
        try {
            if ("Create".equals(this.mode)) {

                if (!this.ligneinventaires.isEmpty()) {

                    this.ut.begin();

                    this.inventaire.setIdmois(this.anneeMois);
                    this.inventaire.setIdinventaire(this.inventaireFacadeLocal.nextVal());
                    this.inventaire.setValidee((false));
                    this.inventaireFacadeLocal.create(this.inventaire);

                    for (Ligneinventaire li : this.ligneinventaires) {
                        li.setIdLigneinventaire(this.ligneinventaireFacadeLocal.nextVal());
                        li.setIdInventaire(this.inventaire);
                        this.ligneinventaireFacadeLocal.create(li);
                    }

                    Utilitaires.saveOperation(this.mouchardFacadeLocal, "Enregistrement de l'inventaire : " + this.inventaire.getCode(), SessionMBean.getUserAccount());

                    this.ut.commit();
                    this.inventaire = null;
                    this.ligneinventaires.clear();
                    this.detail = this.imprimer = (true);

                    notifySuccess();
                    RequestContext.getCurrentInstance().execute("PF('CommandeCreateDialog').hide()");
                } else {
                    notifyError("liste_article_vide");
                }
            } else if ("Edit".equals(this.mode)) {
                if (this.inventaire != null) {

                    this.ut.begin();
                    this.inventaireFacadeLocal.edit(this.inventaire);

                    if (!this.ligneinventaires.isEmpty()) {
                        for (Ligneinventaire li : this.ligneinventaires) {
                            this.ligneinventaireFacadeLocal.edit(li);
                        }
                    }

                    this.ut.commit();
                    this.inventaire = null;
                    this.ligneinventaires.clear();
                    this.detail = this.imprimer = true;

                    notifySuccess();
                    RequestContext.getCurrentInstance().execute("PF('CommandeCreateDialog').hide()");
                } else {
                    notifyError("not_row_selected");
                }
            } else if (this.inventaire != null) {

                this.ut.begin();

                this.mvtStock.setIdMvtStock(this.mvtStockFacadeLocal.nextLongVal());
                this.mvtStock.setDateMvt(this.inventaire.getDateInventaire());
                this.mvtStock.setIdmois(this.inventaire.getIdmois());
                this.mvtStock.setCode(this.inventaire.getCode());
                this.mvtStock.setIdinventaire(BigInteger.valueOf(this.inventaire.getIdinventaire()));
                this.mvtStockFacadeLocal.create(this.mvtStock);

                this.inventaire.setIdMvtStock(BigInteger.valueOf(this.mvtStock.getIdMvtStock()));
                this.inventaire.setValidee((true));
                this.inventaireFacadeLocal.edit(this.inventaire);

                if (!this.ligneinventaires.isEmpty()) {
                    for (Ligneinventaire li : this.ligneinventaires) {

                        if (li.getEcart() == 0.0D) {
                            li.setObservation("Normal");
                        } else if (li.getEcart() > 0.0D) {
                            double qteAvant = li.getIdlot().getQuantite();

                            li.getIdlot().setQuantite(li.getQuantitePhysique());
                            li.setObservation("Ajout de " + li.getEcart() + " Article(s)");
                            this.lotFacadeLocal.edit(li.getIdlot());

                            Produit pTemp = this.produitFacadeLocal.find(li.getIdlot().getIdproduit().getIdproduit());
                            pTemp.setQuantite((pTemp.getQuantite() + li.getEcart()));
                            this.produitFacadeLocal.edit(pTemp);

                            LigneMvtStock lmvts = new LigneMvtStock();
                            lmvts.setIdLigneMvtStock(this.ligneMvtStockFacadeLocal.nextLongVal());
                            lmvts.setIdMvtStock(this.mvtStock);
                            lmvts.setIdlot(li.getIdlot());
                            lmvts.setClient("INVENTAIRE");
                            lmvts.setFournisseur("/");
                            lmvts.setQuantiteEntree(li.getEcart());
                            lmvts.setQuantiteSortie(0.0);
                            lmvts.setReste(li.getIdlot().getQuantite());
                            lmvts.setQuantiteAvant(qteAvant);
                            lmvts.setType("ENTREE");
                            this.ligneMvtStockFacadeLocal.create(lmvts);
                        } else {
                            double qteAvant = li.getIdlot().getQuantite();

                            li.getIdlot().setQuantite(li.getQuantitePhysique());
                            this.lotFacadeLocal.edit(li.getIdlot());
                            li.setObservation("Retrait de " + Math.abs(li.getEcart()) + " Articles");

                            Produit pTemp = this.produitFacadeLocal.find(li.getIdlot().getIdproduit().getIdproduit());
                            pTemp.setQuantite((pTemp.getQuantite() - Math.abs(li.getEcart())));
                            this.produitFacadeLocal.edit(pTemp);

                            LigneMvtStock lmvts = new LigneMvtStock();
                            lmvts.setIdLigneMvtStock(this.ligneMvtStockFacadeLocal.nextLongVal());
                            lmvts.setIdMvtStock(this.mvtStock);
                            lmvts.setIdlot(li.getIdlot());
                            lmvts.setClient("/");
                            lmvts.setFournisseur("INVENTAIRE");
                            lmvts.setQuantiteEntree(0.0);
                            lmvts.setQuantiteSortie((Math.abs(li.getEcart())));
                            lmvts.setReste(li.getIdlot().getQuantite());
                            lmvts.setQuantiteAvant(qteAvant);
                            lmvts.setType("SORTIE");
                            this.ligneMvtStockFacadeLocal.create(lmvts);
                        }
                        this.ligneinventaireFacadeLocal.edit(li);
                    }
                }

                this.ut.commit();
                this.inventaire = null;
                this.ligneinventaires.clear();
                this.detail = this.imprimer = (true);

                notifySuccess();
                RequestContext.getCurrentInstance().execute("PF('CommandeCreateDialog').hide()");
            } else {
                notifyError("not_row_selected");
            }
        } catch (Exception e) {
            notifyFail(e);
        }
    }

    public void delete() {
        try {
            if (this.inventaire == null) {
                notifyError("not_row_selected");
                return;
            }
            if (!Utilitaires.isAccess((50L))) {
                notifyError("acces_refuse");
                this.detail = this.imprimer = true;
                this.inventaire = null;
                return;
            }
            if (this.inventaire.getValidee()) {
                notifyError("inventaire_validee");
                this.detail = this.imprimer = true;
                this.inventaire = null;
                return;
            }
            this.ut.begin();

            this.ligneinventaireFacadeLocal.deleteByIdInventaire(this.inventaire.getIdinventaire());
            this.inventaireFacadeLocal.remove(this.inventaire);

            this.ut.commit();
            Utilitaires.saveOperation(this.mouchardFacadeLocal, "Annulation de l'inventaire : " + this.inventaire.getCode(), SessionMBean.getUserAccount());
            this.inventaire = null;
            this.detail = this.imprimer = true;
            notifySuccess();
        } catch (Exception e) {
            notifyFail(e);
        }
    }

    public void annuler() {
        try {
            if (this.inventaire == null) {
                this.detail = this.imprimer = (true);
                notifyError("not_row_selected");
                return;
            }
            if (!Utilitaires.isAccess(50L)) {
                this.detail = this.imprimer = (true);
                notifyError("acces_refuse");
                this.inventaire = null;
                return;
            }
            if (!this.inventaire.getValidee()) {
                notifyError("inventaire_non_validee");
                return;
            }
            this.ut.begin();

            List<Ligneinventaire> temp = this.ligneinventaireFacadeLocal.findByInventaire(this.inventaire.getIdinventaire());
            if (!temp.isEmpty()) {
                for (Ligneinventaire li : temp) {
                    this.ligneinventaireFacadeLocal.remove(li);
                    Produit pTemp = this.produitFacadeLocal.find(li.getIdlot().getIdproduit().getIdproduit());
                    if (li.getEcart() > 0.0D) {
                        pTemp.setQuantite(pTemp.getQuantite() - li.getEcart());
                        li.getIdlot().setQuantite(li.getIdlot().getQuantite() - li.getEcart());
                    } else {
                        pTemp.setQuantite(pTemp.getQuantite() + Math.abs(li.getEcart()));
                        li.getIdlot().setQuantite(li.getIdlot().getQuantite() + Math.abs(li.getEcart()));
                    }
                    this.lotFacadeLocal.edit(li.getIdlot());
                    this.produitFacadeLocal.edit(pTemp);
                }
            }
            this.inventaireFacadeLocal.remove(this.inventaire);

            MvtStock mTemp = this.mvtStockFacadeLocal.find(this.inventaire.getIdMvtStock().longValue());
            ligneMvtStockFacadeLocal.deleteByIdMvtStock(this.inventaire.getIdMvtStock().longValue());
            this.mvtStockFacadeLocal.remove(mTemp);
            this.ut.commit();
            Utilitaires.saveOperation(this.mouchardFacadeLocal, "Annulation de l'inventaire : " + this.inventaire.getCode(), SessionMBean.getUserAccount());
            this.inventaire = null;
            this.detail = this.supprimer = this.imprimer = (true);
            notifySuccess();
        } catch (Exception e) {
            notifyFail(e);
        }
    }

    public void initPrinter(Inventaire i, String mode) {
        this.inventaire = i;
        print(mode);
    }

    public void initEdit(Inventaire i) {
        this.inventaire = i;
        prepareEdit();
    }

    public void initView(Inventaire i) {
        this.inventaire = i;
        prepareview();
    }

    public void initDelete(Inventaire i) {
        this.inventaire = i;
        delete();
    }

    public void print(String mode) {
        try {
            if (!Utilitaires.isAccess((51L))) {
                notifyError("acces_refuse");
                this.inventaire = null;
                return;
            }
            if (this.inventaire != null) {
                List<Ligneinventaire> l = this.ligneinventaireFacadeLocal.findByInventaire(this.inventaire.getIdinventaire());
                this.inventaire.setLigneinventaireList(l);
                if (mode.equals("1")) {
                    this.fileName = PrintUtils.printInventaireDetaille(this.inventaire, SessionMBean.getParametrage());
                } else {
                    this.fileName = PrintUtils.printInventaire(this.inventaire, SessionMBean.getParametrage());
                }
                RequestContext.getCurrentInstance().execute("PF('FactureImprimerDialog').show()");
            } else {
                notifyError("not_row_selected");
            }
        } catch (Exception e) {
            notifyFail(e);
        }
    }

    public void addProduit() {
        try {
            for (Lot l : this.selectedLots) {

                if (!ifExistLot(this.ligneinventaires, l)) {
                    Ligneinventaire li = new Ligneinventaire();
                    li.setIdLigneinventaire((0L));
                    li.setPrix(l.getPrixVente());
                    li.setIdlot(l);
                    li.setQuantiteLogique(l.getQuantite());
                    li.setQuantitePhysique(l.getQuantite());
                    li.setEcart(0.0);
                    li.setObservation("-");
                    this.ligneinventaires.add(li);
                }

                if (!this.produits.contains(l.getIdproduit())) {
                    this.produits.add(l.getIdproduit());
                }
            }
            RequestContext.getCurrentInstance().execute("PF('ArticleCreateDialog').hide()");
        } catch (Exception e) {
            notifyFail(e);
        }
    }

    public boolean ifExistLot(List<Ligneinventaire> ligneinventaires, Lot lot) {
        boolean result = false;
        for (Ligneinventaire l : ligneinventaires) {
            if (l.getIdlot().equals(lot)) {
                result = true;
                break;
            }
        }
        return result;
    }

    public void removeProduit(int index) {
        try {
            boolean trouve = false;
            this.ut.begin();

            Ligneinventaire li = this.ligneinventaires.get(index);
            if (li.getIdLigneinventaire() != 0L) {
                trouve = true;
                this.ligneinventaireFacadeLocal.remove(li);
                Utilitaires.saveOperation(this.mouchardFacadeLocal, "Suppression du lot : " + li.getIdlot().getNumeroLot() + " dans l'inventaire : " + this.inventaire.getCode(), SessionMBean.getUserAccount());
            }
            this.ligneinventaires.remove(index);
            this.ut.commit();
            JsfUtil.addSuccessMessage("Supprimé");
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addErrorMessage(this.routine.localizeMessage("echec_operation"));
        }
    }

    public void updateEcart(int index) {
        try {
            Ligneinventaire li = this.ligneinventaires.get(index);
            try {
                this.ligneinventaires.get(index).setEcart((li.getQuantitePhysique() - li.getIdlot().getQuantite()));
            } catch (Exception e) {
                this.ligneinventaires.get(index).setEcart(0.0);
                this.ligneinventaires.get(index).setQuantitePhysique(li.getIdlot().getQuantite());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void notifyError(String message) {
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
}
