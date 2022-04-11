package controllers.stock;

import entities.Famille;
import entities.LigneMvtStock;
import entities.Lot;
import entities.MvtStock;
import entities.Produit;
import entities.Stock;
import entities.StockProduit;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.transaction.Transactional;
import org.primefaces.context.RequestContext;
import utils.JsfUtil;
import utils.PrintUtils;
import utils.SessionMBean;
import utils.Utilitaires;

@ManagedBean
@ViewScoped
public class StockController extends AbstractStockController implements Serializable {

    @PostConstruct
    private void init() {
        try {
            this.stocks = this.stockFacadeLocal.findAllDate(SessionMBean.getDateOuverture());
        } catch (Exception e) {
            stocks = new ArrayList<>();
        }
        this.sommeQuantite();
    }

    public void updateMois() {
        try {
            this.anneeMois = this.anneeMoisFacadeLocal.find(this.anneeMois.getIdAnneeMois());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sommeQuantite() {
        quantiteEntrant = 0;
        montantJour = 0;
        if (!stocks.isEmpty()) {
            for (Stock s : stocks) {
                montantJour += s.getMontant();
                if (s.getStockProduitList().isEmpty()) {
                    s.setStockProduitList(stockProduitFacadeLocal.findByStock(s));
                }

                for (StockProduit sp : s.getStockProduitList()) {
                    quantiteEntrant += sp.getQuantite();
                }

            }
        }
    }

    public void prepareCreate() {
        try {
            if (Utilitaires.isDayClosed()) {
                notifyError("journee_cloturee");

                return;
            }
            if (!Utilitaires.isAccess((19L))) {
                notifyError("acces_refuse");
                return;
            }
            this.mode = "Create";

            this.produit = null;
            this.stock = new Stock();
            this.stock.setDateAchat(SessionMBean.getDateOuverture());
            this.stockProduits.clear();
            RequestContext.getCurrentInstance().execute("PF('StockCreateDialog').show()");
        } catch (Exception e) {
            notifyFail(e);
        }
    }

    public void prepareCreateCommande() {
        this.famille = new Famille();
        this.produit = new Produit();
        this.stockProduit = new StockProduit();
        this.lot = new Lot();
        this.cout_quantite = (0.0D);
        this.produits = this.produitFacadeLocal.findAllRange();
    }

    public void prepareEdit() {
        try {
            if (!this.stock.getLivraisonDirecte()) {
                notifyError("livraison_directe");
                return;
            }
            if (Utilitaires.isDayClosed()) {
                notifyError("journee_cloturee");
                return;
            }
            if (!Utilitaires.isAccess((20L))) {
                notifyError("acces_refuse");
                return;
            }
            this.mode = "Edit";
            if (this.stock != null) {
                this.stockProduits = this.stockProduitFacadeLocal.findByStock(this.stock);

                this.fournisseur = this.stock.getIdfournisseur();
                this.anneeMois = this.stock.getIdAnneeMois();

                this.annee = this.anneeMois.getIdannee();
                this.anneeMoises = this.anneeMoisFacadeLocal.findByAnneeEtat(this.annee, true);
                this.total = this.stock.getMontant();
                this.mvtStock = this.mvtStockFacadeLocal.find((this.stock.getIdMvtStock().longValue()));
                RequestContext.getCurrentInstance().execute("PF('StockCreateDialog').show()");
                return;
            }
            notifyError("not_row_selected");
        } catch (Exception e) {
            notifyFail(e);
        }
    }

    public void prepareview() {
        try {
            if (this.stock != null) {
                this.stockProduits = this.stockProduitFacadeLocal.findByStock(this.stock);
                if (!this.stockProduits.isEmpty()) {
                    notifyError("liste_article_vide");
                    return;
                }
                RequestContext.getCurrentInstance().execute("PF('StockDetailDialog').show()");
            } else {
                notifyError("not_row_selected");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void filterProduit() {
        try {
            this.produits.clear();
            this.produit = new Produit();
            this.lot = new Lot();
            if (this.famille.getIdfamille() != null) {
                this.produits = this.produitFacadeLocal.findByFamille(this.famille);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void filterMois() {
        try {
            this.anneeMoises.clear();
            if (this.annee.getIdannee() != null) {
                this.anneeMoises = this.anneeMoisFacadeLocal.findByAnneeEtat(this.annee, true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private double sommeQ(List<StockProduit> list) {
        if (list.isEmpty()) {
            return 0;
        }
        double qte = 0;
        for (StockProduit s : list) {
            qte += s.getQuantite();
        }
        return qte;
    }

    public void create() {
        try {
            if (this.mode.equals("Create")) {

                if (!this.stockProduits.isEmpty()) {

                    this.mvtStock = new MvtStock();

                    this.fournisseur = this.fournisseurFacadeLocal.find(this.fournisseur.getIdfournisseur());

                    String code = "S-" + this.anneeMois.getIdannee().getNom() + "-" + this.anneeMois.getIdmois().getNom().toUpperCase().substring(0, 3);
                    Long nextPayement = this.stockFacadeLocal.nextVal(this.anneeMois);
                    code = Utilitaires.genererCodeStock(code, nextPayement);

                    this.ut.begin();

                    this.stock.setIdstock(this.stockFacadeLocal.nextLongVal());
                    this.mvtStock.setIdMvtStock(this.mvtStockFacadeLocal.nextLongVal());

                    this.stock.setIdfournisseur(this.fournisseur);
                    this.stock.setIdAnneeMois(this.anneeMois);
                    this.stock.setMontant(this.total);
                    this.stock.setLivraisonDirecte((true));
                    this.stock.setCode(code);
                    this.stock.setIdMvtStock(BigInteger.valueOf(this.mvtStock.getIdMvtStock()));

                    this.mvtStock.setCode(code);
                    this.mvtStock.setDateMvt(this.stock.getDateAchat());
                    this.mvtStock.setIdmois(this.stock.getIdAnneeMois());
                    this.mvtStock.setIdstock(BigInteger.valueOf(this.stock.getIdstock()));

                    this.stock.setQuantite(this.sommeQ(stockProduits));

                    this.stockFacadeLocal.create(this.stock);
                    this.mvtStockFacadeLocal.create(this.mvtStock);

                    for (StockProduit s : this.stockProduits) {
                        s.setIdStockProduit(this.stockProduitFacadeLocal.nextVal());
                        s.setIdstock(this.stock);
                        this.stockProduitFacadeLocal.create(s);

                        Produit p = this.produitFacadeLocal.find(s.getIdlot().getIdproduit().getIdproduit());
                        p.setPrixachat(s.getPrixAcquisition());
                        p.setPrixMaximal(s.getPrixAcquisition());
                        p.setQuantite((p.getQuantite() + s.getQuantite()));
                        this.produitFacadeLocal.edit(p);

                        Lot lotTemp = this.lotFacadeLocal.find(s.getIdlot().getIdlot());
                        double quantite_avant = lotTemp.getQuantite();
                        lotTemp.setQuantite(lotTemp.getQuantite() + s.getQuantite());
                        lotTemp.setPrixAchat(s.getPrixAcquisition());
                        lotTemp.setPrixVente(s.getPrixAcquisition());
                        this.lotFacadeLocal.edit(lotTemp);

                        LigneMvtStock lmvts = new LigneMvtStock();
                        lmvts.setIdLigneMvtStock(this.ligneMvtStockFacadeLocal.nextLongVal());
                        lmvts.setIdMvtStock(this.mvtStock);
                        lmvts.setIdlot(s.getIdlot());
                        lmvts.setClient(" ");
                        lmvts.setFournisseur(this.fournisseur.getRaisonSociale());
                        lmvts.setQuantiteEntree(s.getQuantite());
                        lmvts.setQuantiteSortie(0.0);
                        lmvts.setQuantiteAvant(quantite_avant);
                        lmvts.setReste(lotTemp.getQuantite());
                        lmvts.setType("ENTREE");
                        this.ligneMvtStockFacadeLocal.create(lmvts);
                    }

                    Utilitaires.saveOperation(this.mouchardFacadeLocal, "Enregistrement de l'entrée en stock N° : " + code + " ; Montant : " + this.stock.getMontant(), SessionMBean.getUserAccount());
                    this.ut.commit();

                    this.stock = new Stock();
                    this.detail = imprimer = modifier = supprimer = true;

                    notifySuccess();                    
                    RequestContext.getCurrentInstance().execute("PF('StockCreateDialog').hide()");
                    this.init();
                    return;
                }
                notifyError("liste_article_vide");
                return;
            }
            if (this.stock != null) {

                Stock s1 = this.stockFacadeLocal.find(this.stock.getIdstock());

                this.fournisseur = this.fournisseurFacadeLocal.find(this.fournisseur.getIdfournisseur());
                this.stock.setIdfournisseur(this.fournisseur);
                this.stock.setMontant(this.total);

                if (!this.stockProduits.isEmpty()) {
                    this.ut.begin();
                    this.stockFacadeLocal.edit(this.stock);
                    for (StockProduit s : this.stockProduits) {

                        if (s.getIdStockProduit() != 0L) {
                            StockProduit sp = this.stockProduitFacadeLocal.find(s.getIdStockProduit());
                            if (!Objects.equals(s.getQuantite(), sp.getQuantite())) {
                                LigneMvtStock ligneMvtStock = ligneMvtStockFacadeLocal.findByMvtIdLot(stock.getIdMvtStock().longValue(), s.getIdlot().getIdlot());

                                Produit pro = this.produitFacadeLocal.find(s.getIdproduit().getIdproduit());
                                pro.setQuantite((pro.getQuantite() - sp.getQuantite()) + s.getQuantite());
                                this.produitFacadeLocal.edit(pro);

                                Lot lotTemp = this.lotFacadeLocal.find(s.getIdlot().getIdlot());
                                lotTemp.setQuantite((lotTemp.getQuantite() - sp.getQuantite()) + s.getQuantite());
                                this.lotFacadeLocal.edit(lotTemp);

                                ligneMvtStock.setQuantiteEntree(s.getQuantite());
                                ligneMvtStock.setReste(ligneMvtStock.getQuantiteAvant() + s.getQuantite());
                                this.ligneMvtStockFacadeLocal.edit(ligneMvtStock);
                            }
                            this.stockProduitFacadeLocal.edit(s);
                            continue;
                        }

                        s.setIdStockProduit(this.stockProduitFacadeLocal.nextVal());
                        s.setIdstock(this.stock);
                        this.stockProduitFacadeLocal.create(s);

                        Produit p = this.produitFacadeLocal.find(s.getIdproduit().getIdproduit());
                        p.setQuantite((p.getQuantite() + s.getQuantite()));
                        this.produitFacadeLocal.edit(p);

                        Lot l = this.lotFacadeLocal.find(s.getIdlot().getIdlot());
                        double quantite_avant = l.getQuantite();
                        l.setQuantite((l.getQuantite() + s.getQuantite()));
                        this.lotFacadeLocal.edit(l);

                        LigneMvtStock lmvts = new LigneMvtStock();
                        lmvts.setIdLigneMvtStock(this.ligneMvtStockFacadeLocal.nextLongVal());
                        lmvts.setIdMvtStock(this.mvtStock);
                        lmvts.setIdlot(s.getIdlot());
                        lmvts.setClient(" ");
                        lmvts.setFournisseur(this.fournisseur.getRaisonSociale());
                        lmvts.setQuantiteEntree(s.getQuantite());
                        lmvts.setQuantiteSortie(0.0);
                        lmvts.setReste(l.getQuantite());
                        lmvts.setQuantiteAvant(quantite_avant);
                        lmvts.setType("ENTREE");
                        this.ligneMvtStockFacadeLocal.create(lmvts);
                    }

                    Utilitaires.saveOperation(this.mouchardFacadeLocal, "Enregistrement de l'entrée en stock N° : " + this.stock.getCode() + " ; Ancien Montant : " + s1.getMontant() + " Nouveau montant : " + this.stock.getMontant(), SessionMBean.getUserAccount());
                    this.ut.commit();
                    this.detail = imprimer = modifier = supprimer = true;
                    notifySuccess();
                    RequestContext.getCurrentInstance().execute("PF('StockCreateDialog').hide()");
                    this.init();
                    return;
                }
                notifyError("liste_article_vide");
                return;
            }
            notifyError("not_row_selected");
        } catch (Exception e) {
            notifyFail(e);
        }
    }

    @Transactional
    public void delete() {
        try {
            if (this.stock != null) {

                if (Utilitaires.isDayClosed()) {
                    notifyError("journee_cloturee");
                    return;
                }
                if (!Utilitaires.isAccess((21L))) {
                    notifyError("acces_refuse");
                    return;
                }
                List<StockProduit> temp = this.stockProduitFacadeLocal.findByStock(this.stock);
                if (temp.isEmpty()) {
                    for (StockProduit s : temp) {
                        Produit p = this.produitFacadeLocal.find(s.getIdproduit().getIdproduit());
                        p.setQuantite((p.getQuantite() - s.getQuantite()));
                        this.produitFacadeLocal.edit(p);

                        Lot lTemp = this.lotFacadeLocal.find(s.getIdlot().getIdlot());
                        lTemp.setQuantite((lTemp.getQuantite() - s.getQuantite()));
                        this.lotFacadeLocal.edit(lTemp);
                    }
                }
                
                this.stockProduitFacadeLocal.deleteByIdStock(stock.getIdstock());

                MvtStock mTemp = this.mvtStockFacadeLocal.find(Long.valueOf(this.stock.getIdMvtStock().longValue()));
                ligneMvtStockFacadeLocal.deleteByIdMvtStock(mvtStock.getIdMvtStock());
                this.mvtStockFacadeLocal.deleteMvtById(mTemp.getIdMvtStock());
                this.stockFacadeLocal.deleteStockById(this.stock.getIdstock());

                Utilitaires.saveOperation(this.mouchardFacadeLocal, "Annulation de l'entrée en stock : " + this.stock.getCode() + " Montant : " + this.stock.getMontant(), SessionMBean.getUserAccount());

                notifySuccess();               
                this.stock = new Stock();
                this.init();
                return;
            }
            notifyError("not_row_selected");
        } catch (Exception e) {
            notifyFail(e);
        }
    }

    public void print() {
        try {
            if (!Utilitaires.isAccess(22L)) {
                notifyError("acces_refuse");
                return;
            }
            if (this.stock != null) {
                this.stockProduits = this.stockProduitFacadeLocal.findByStock(this.stock);
                this.fileName = PrintUtils.printStock(this.stock, this.stockProduits);
                RequestContext.getCurrentInstance().execute("PF('StockImprimerDialog').show()");
                return;
            }
            notifyError("not_row_selected");
        } catch (Exception e) {
            notifyFail(e);
        }
    }

    public void initPrinter(Stock s) {
        this.stock = s;
        print();
    }

    public void initEdit(Stock s) {
        this.stock = s;
        prepareEdit();
    }

    public void initView(Stock s) {
        this.stock = s;
        prepareview();
    }

    public void initDelete(Stock s) {
        this.stock = s;
        delete();
    }

    public void addProduit() {
        try {
            StockProduit s = this.stockProduit;
            s.setIdStockProduit((0L));

            Lot ltemp = this.lot;
            s.setIdlot(ltemp);
            s.setIdproduit(ltemp.getIdproduit());

            boolean drapeau = false;
            for (StockProduit s1 : this.stockProduits) {
                if (s1.getIdlot().getIdlot().equals(s.getIdlot().getIdlot())) {
                    drapeau = true;
                    break;
                }
            }
            if (drapeau) {
                notifyError("article_existant_dans_le_tableau");
                return;
            }
            this.stockProduits.add(s);
            this.total = calculTotal(this.stockProduits);

            this.stockProduit = new StockProduit();
            this.produit = new Produit();
            RequestContext.getCurrentInstance().execute("PF('AddarticleCreateDialog').hide()");
            notifySuccess();
        } catch (Exception e) {
            notifyFail(e);
        }
    }

    public void removeProduit(StockProduit stockProduit) {
        try {
            int i = 0;
            for (StockProduit s : this.stockProduits) {

                if (s.getIdproduit().getIdproduit().equals(stockProduit.getIdproduit().getIdproduit())) {
                    if (s.getIdStockProduit() != 0L) {

                        ut.begin();
                        StockProduit sOld = stockProduitFacadeLocal.find(s.getIdStockProduit());
                        Stock stockOld = stockFacadeLocal.find(stock.getIdstock());

                        this.stockProduitFacadeLocal.remove(s);
                        stockOld.setMontant(stockOld.getMontant() - (sOld.getPrixAcquisition() * sOld.getQuantite()));
                        this.stockFacadeLocal.edit(stockOld);

                        Produit pro = this.produitFacadeLocal.find(s.getIdproduit().getIdproduit());
                        pro.setQuantite((pro.getQuantite() - sOld.getQuantite()));
                        this.produitFacadeLocal.edit(pro);

                        Lot lotTemp = this.lotFacadeLocal.find(s.getIdlot().getIdlot());
                        lotTemp.setQuantite((lotTemp.getQuantite() - sOld.getQuantite()));
                        this.lotFacadeLocal.edit(lotTemp);

                        LigneMvtStock lmvt = ligneMvtStockFacadeLocal.findByMvtIdLot(stock.getIdMvtStock().longValue(), lotTemp.getIdlot());
                        if (lmvt != null) {
                            ligneMvtStockFacadeLocal.remove(lmvt);
                        }

                        ut.commit();
                    }
                    this.stockProduits.remove(i);
                    break;
                }
                i++;
            }
            this.total = calculTotal(this.stockProduits);
            JsfUtil.addSuccessMessage("Supprimé");
        } catch (Exception e) {
            notifyFail(e);
        }
    }

    public Double calculTotal(List<StockProduit> stockProduits) {
        Double resultat = 0.0;
        if (!stockProduits.isEmpty()) {
            for (StockProduit s : stockProduits) {
                resultat = (resultat + s.getPrixAcquisition() * s.getQuantite());
            }
        }
        return resultat;
    }

    public void updateTotal() {
        try {
            this.total = calculTotal(this.stockProduits);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateTotaux() {
        try {
            this.cout_quantite = 0.0;
            if (this.stockProduit.getQuantite() != null
                    && this.stockProduit.getPrixAcquisition() != null) {
                this.cout_quantite = (this.stockProduit.getPrixAcquisition() * this.stockProduit.getQuantite());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updatedata() {
        try {
            if (this.produit.getIdproduit() != null) {
                this.famille = this.produit.getIdfamille();

                this.lots = this.lotFacadeLocal.findByArticle(this.produit.getIdproduit(), this.produit.getPerissable());
                if (this.lots.size() == 1) {
                    this.lot = this.lots.get(0);
                    this.stockProduit.setPrixAcquisition(this.lot.getPrixAchat());
                    return;
                }
                this.lot = new Lot();
                this.lot.setQuantite(0.0);
                this.stockProduit.setPrixAcquisition(0.0);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updatedataLot() {
        try {
            if (this.lot != null) {
                this.stockProduit.setPrixAcquisition(this.lot.getPrixAchat());
            }
        } catch (Exception e) {
            e.printStackTrace();
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
}
