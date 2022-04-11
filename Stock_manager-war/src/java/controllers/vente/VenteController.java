package controllers.vente;

import entities.Client;
import entities.Commande;
import entities.Facture;
import entities.Famille;
import entities.LigneMvtStock;
import entities.Lot;
import entities.MvtStock;
import entities.Produit;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.transaction.Transactional;
import org.primefaces.context.RequestContext;
import utils.JsfUtil;
import utils.PrintUtils;
import utils.SessionMBean;
import utils.Utilitaires;

@ManagedBean
@SessionScoped
public class VenteController extends AbstractVenteController implements Serializable {

    @PostConstruct
    private void init() {
        this.conteur = 0;
        try {
            this.factures = this.factureFacadeLocal.findAllDate(SessionMBean.getDateOuverture());
            this.sommeQuantite();
        } catch (Exception e) {
            factures = new ArrayList<>();
        }

    }

    public void updateMois() {
        try {
            this.anneeMois = this.anneeMoisFacadeLocal.find(this.anneeMois.getIdAnneeMois());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateCalculTva() {
        updateTotal();
    }

    private void sommeQuantite() {
        quantiteVendue = 0;
        montantJour = 0;
        if (!factures.isEmpty()) {
            for (Facture f : factures) {
                montantJour += f.getMontant();
                if (f.getCommandeList().isEmpty()) {
                    f.setCommandeList(commandeFacadeLocal.findByFacture(f));
                }

                for (Commande c : f.getCommandeList()) {
                    quantiteVendue += c.getQuantite();
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
            if (!Utilitaires.isAccess((23L))) {
                notifyError("acces_refuse");
                return;
            }
            RequestContext.getCurrentInstance().execute("PF('CommandeCreateDialog').show()");
            this.mode = "Create";
            this.produit = new Produit();
            this.facture = new Facture();
            this.client = new Client();
            this.facture.setCalcultva(SessionMBean.getParametrage().getCalcultva());
            this.facture.setCalculRemise(SessionMBean.getParametrage().getCalculRemise());
            this.facture.setCredit((false));
            this.facture.setComptabilise((false));
            this.facture.setDateAchat(SessionMBean.getDateOuverture());
            this.facture.setMontantPaye(0d);
            this.facture.setReste(0d);
            this.facture.setMontantRemise(0d);
            this.facture.setMontantTtc(0d);
            this.facture.setMontantTva(0d);
            this.facture.setTauxRemise(SessionMBean.getParametrage().getTauxRemise());
            this.facture.setTauxTva(SessionMBean.getParametrage().getTauxTva());
            this.commandes.clear();
            this.payement_credit = false;

            this.nouveauClient = false;
            updateClient();

            this.total = (0d);
            this.conteur = 0;
        } catch (Exception e) {
            this.routine.catchException(e, this.routine.localizeMessage("echec_operation"));
            RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
        }
    }

    public void prepareCreateCommande() {
        this.famille = new Famille();
        this.produit = new Produit();
        this.commande = new Commande();
        this.lot = new Lot();
        this.commande.setSerie("-");
        this.cout_quantite = (0.0D);
        RequestContext.getCurrentInstance().execute("PF('ArticleCreateDialog').show()");
        this.produits = this.produitFacadeLocal.findAllRange1();
    }

    public void prepareEdit() {
        try {
            if (this.facture.getVenteDirecte()) {

                if (Utilitaires.isDayClosed()) {
                    notifyError("journee_cloturee");
                    return;
                }
                if (!Utilitaires.isAccess((24L))) {
                    notifyError("acces_refuse");
                    this.facture = null;
                    return;
                }
                this.mode = "Edit";
                this.showSelector = (false);
                this.selectClient = (true);
                this.saisieClient = (false);

                if (this.facture != null) {
                    this.commandes = this.commandeFacadeLocal.findByFacture(this.facture);
                    this.client = this.facture.getIdclient();
                    this.total = this.facture.getMontantTtc();
                    this.mvtStock = this.mvtStockFacadeLocal.find((this.facture.getIdMvtStock().longValue()));
                }
                RequestContext.getCurrentInstance().execute("PF('CommandeCreateDialog').show()");
            } else {
                notifyError("vente_effectuee_par_commande");
            }
        } catch (Exception e) {
            notifyError("echec_operation");
        }
    }

    public void prepareview() {
        try {
            if (this.facture != null) {
                this.commandes = this.commandeFacadeLocal.findByFacture(this.facture);
                if (!this.commandes.isEmpty()) {
                    RequestContext.getCurrentInstance().execute("PF('FactureDetailDialog').show()");
                    return;
                }
                notifyError("liste_article_vide");
            } else {
                notifyError("not_row_selected");
            }
        } catch (Exception e) {
            notifyError("echec_operation");
        }
    }

    public void filterProduit() {
        try {
            this.produits.clear();
            this.produit = new Produit();
            this.lot = new Lot();
            if (this.famille.getIdfamille() != null) {
                this.produits = this.produitFacadeLocal.findByFamille(this.famille, true);
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

    public void updateClient() {
        try {
            if (this.nouveauClient) {
                this.saisieClient = (true);
                this.selectClient = (false);
            } else {
                this.saisieClient = (false);
                this.selectClient = (true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private double sommeQ(List<Commande> list) {
        if (list.isEmpty()) {
            return 0;
        }
        double qte = 0;
        for (Commande c : list) {
            qte += c.getQuantite();
        }
        return qte;
    }

    public void create() {
        try {
            if ("Create".equals(this.mode)) {

                if (!this.commandes.isEmpty()) {

                    String message = "";

                    String code = "F-" + this.anneeMois.getIdannee().getNom() + "-" + this.anneeMois.getIdmois().getNom().toUpperCase().substring(0, 3);
                    Long nextFacture = this.factureFacadeLocal.nextVal(this.anneeMois);
                    code = Utilitaires.genererCodeFacture(code, nextFacture);

                    this.mvtStock = new MvtStock();

                    this.facture.setNumeroFacture(code);
                    this.mvtStock.setCode(code);

                    if (this.nouveauClient) {
                        this.client.setIdclient(this.clientFacadeLocal.nextVal());
                        this.client.setSolde((0));
                        this.clientFacadeLocal.create(this.client);
                        Utilitaires.saveOperation(this.mouchardFacadeLocal, "Enregistrement du client : " + this.client.getNom() + " " + this.client.getPrenom(), SessionMBean.getUserAccount());
                    } else {
                        this.client = this.clientFacadeLocal.find(this.client.getIdclient());
                    }

                    this.mvtStock.setIdMvtStock(this.mvtStockFacadeLocal.nextLongVal());
                    this.facture.setIdfacture(this.factureFacadeLocal.nextLongVal());
                    this.facture.setIdclient(this.client);
                    this.facture.setIdAnneeMois(this.anneeMois);
                    this.facture.setIdutilisateur(SessionMBean.getUserAccount());
                    this.facture.setMontant(this.total);
                    this.facture.setVenteDirecte((true));

                    this.mvtStock.setDateMvt(this.facture.getDateAchat());
                    this.mvtStock.setIdmois(this.facture.getIdAnneeMois());

                    if (this.facture.getCredit()) {
                        this.facture.setReste((this.facture.getMontantTtc() - this.facture.getMontantPaye()));
                        this.facture.setPaye((false));
                        message = "Commande enregistrée avec succès , mais pour un payement ultérieur -> Reste : " + this.facture.getReste() + " Numero de commande ->" + code;
                    } else {
                        this.facture.setPaye((true));
                        this.facture.setMontantPaye(this.facture.getMontantTtc());
                        this.facture.setReste((0.0D));
                        message = "Commande enregistrée avec succès et payée au comptent";
                    }

                    this.ut.begin();

                    updateTotal();
                    double benef = 0.0D;
                    for (Commande c : this.commandes) {
                        c.setBenefice(((c.getMontant() - c.getIdlot().getPrixAchat()) * c.getQuantite()));
                        if (c.getBenefice() < 0.0D) {
                            c.setBenefice(0.0D);
                        }
                        benef += c.getBenefice();
                    }

                    this.mvtStock.setIdfacture(BigInteger.valueOf(this.facture.getIdfacture()));
                    this.facture.setIdMvtStock(BigInteger.valueOf(this.mvtStock.getIdMvtStock()));

                    this.facture.setQuantite(this.sommeQ(commandes));

                    this.facture.setBord(benef);
                    this.factureFacadeLocal.create(this.facture);
                    this.mvtStockFacadeLocal.create(this.mvtStock);

                    for (Commande c : this.commandes) {
                        c.setIdcommande(this.commandeFacadeLocal.nextVal());
                        c.setIdfacture(this.facture);
                        c.setComptabilise((false));
                        c.setBenefice(((c.getMontant() - c.getIdlot().getPrixAchat()) * c.getQuantite()));
                        if (c.getBenefice() < 0.0) {
                            c.setBenefice(0.0);
                        }
                        this.commandeFacadeLocal.create(c);

                        Produit p = this.produitFacadeLocal.find(c.getIdlot().getIdproduit().getIdproduit());
                        p.setQuantite((p.getQuantite() - c.getQuantite()));
                        this.produitFacadeLocal.edit(p);

                        Lot lotTemp = this.lotFacadeLocal.find(c.getIdlot().getIdlot());
                        double quantiteAvant = lotTemp.getQuantite();
                        lotTemp.setQuantite((lotTemp.getQuantite() - c.getQuantite()));
                        this.lotFacadeLocal.edit(lotTemp);

                        LigneMvtStock lmvts = new LigneMvtStock();
                        lmvts.setIdLigneMvtStock(this.ligneMvtStockFacadeLocal.nextLongVal());
                        lmvts.setIdMvtStock(this.mvtStock);
                        lmvts.setIdlot(c.getIdlot());
                        lmvts.setClient(this.client.getNom());
                        lmvts.setFournisseur(" ");
                        lmvts.setQuantiteEntree(0.0);
                        lmvts.setQuantiteSortie(c.getQuantite());
                        lmvts.setQuantiteAvant(quantiteAvant);
                        lmvts.setReste(lotTemp.getQuantite());
                        lmvts.setType("SORTIE");
                        this.ligneMvtStockFacadeLocal.create(lmvts);
                    }

                    Utilitaires.saveOperation(this.mouchardFacadeLocal, "Enregistrement de la facture  : " + code + " ; Montant " + this.facture.getMontant(), SessionMBean.getUserAccount());
                    this.ut.commit();
                    this.factures = this.factureFacadeLocal.findAllDate(SessionMBean.getDateOuverture());

                    this.sommeQuantite();

                    this.facture = new Facture();
                    this.supprimer = this.modifier = this.imprimer = (true);
                    JsfUtil.addSuccessMessage(message);

                    notifySuccess();
                    RequestContext.getCurrentInstance().execute("PF('CommandeCreateDialog').hide()");
                } else {
                    notifyError("liste_article_vide");
                }
            } else if (this.facture != null) {

                if (this.facture.getCredit()) {
                    this.facture.setReste((calculTotal(this.commandes) - this.facture.getMontantPaye()));
                    this.facture.setPaye(false);
                } else {
                    this.facture.setPaye(true);
                    this.facture.setMontantPaye(calculTotal(this.commandes));
                    this.facture.setReste(0.0);
                }

                this.ut.begin();

                this.client = this.clientFacadeLocal.find(this.client.getIdclient());
                this.facture.setIdclient(this.client);

                this.updateTotal();
                this.facture.setQuantite(this.sommeQ(commandes));
                this.factureFacadeLocal.edit(this.facture);

                if (!this.commandes.isEmpty()) {
                    for (Commande c : this.commandes) {
                        if (c.getIdcommande() != 0L) {
                            Commande cc = this.commandeFacadeLocal.find(c.getIdcommande());
                            if (!Objects.equals(c.getQuantite(), cc.getQuantite())) {

                                LigneMvtStock ligneMvtStock = ligneMvtStockFacadeLocal.findByMvtIdLot(facture.getIdMvtStock().longValue(), c.getIdlot().getIdlot());

                                Produit pro = this.produitFacadeLocal.find(c.getIdproduit().getIdproduit());
                                pro.setQuantite(pro.getQuantite() + (cc.getQuantite() - c.getQuantite()));
                                this.produitFacadeLocal.edit(pro);

                                Lot lot = this.lotFacadeLocal.find(c.getIdlot().getIdlot());
                                lot.setQuantite(lot.getQuantite() + (cc.getQuantite() - c.getQuantite()));
                                this.lotFacadeLocal.edit(lot);

                                ligneMvtStock.setQuantiteSortie(c.getQuantite());
                                ligneMvtStock.setReste(ligneMvtStock.getQuantiteAvant() - c.getQuantite());

                                this.ligneMvtStockFacadeLocal.edit(ligneMvtStock);
                            }
                            this.commandeFacadeLocal.edit(c);
                            continue;
                        }
                        
                        c.setIdcommande(this.commandeFacadeLocal.nextVal());
                        c.setIdfacture(this.facture);
                        this.commandeFacadeLocal.create(c);

                        c.setIdproduit(this.produitFacadeLocal.find(c.getIdproduit().getIdproduit()));
                        c.getIdproduit().setQuantite((c.getIdproduit().getQuantite() - c.getQuantite()));
                        this.produitFacadeLocal.edit(c.getIdproduit());

                        Lot l = this.lotFacadeLocal.find(c.getIdlot().getIdlot());
                        double qteAvant = l.getQuantite();
                        l.setQuantite((l.getQuantite() - c.getQuantite()));
                        this.lotFacadeLocal.edit(l);

                        LigneMvtStock lmvts = new LigneMvtStock();
                        lmvts.setIdLigneMvtStock(this.ligneMvtStockFacadeLocal.nextLongVal());
                        lmvts.setIdMvtStock(this.mvtStock);
                        lmvts.setIdlot(c.getIdlot());
                        lmvts.setClient(this.client.getNom());
                        lmvts.setFournisseur(" ");
                        lmvts.setQuantiteAvant(qteAvant);
                        lmvts.setQuantiteEntree(0.0D);
                        lmvts.setQuantiteSortie(c.getQuantite());
                        lmvts.setReste(l.getQuantite());
                        lmvts.setType("SORTIE");
                        this.ligneMvtStockFacadeLocal.create(lmvts);
                    }
                }

                this.ut.commit();
                this.sommeQuantite();
                this.facture = null;
                this.supprimer = this.modifier = this.imprimer = true;

                notifySuccess();
                RequestContext.getCurrentInstance().execute("PF('CommandeCreateDialog').hide()");
            } else {
                notifyError("not_row_selected");
            }
        } catch (Exception e) {
            notifyFail(e);
        }
    }

    public void updateAvance() {
        try {
            if (this.facture.getCredit()) {
                this.payement_credit = true;
            } else {
                this.payement_credit = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //@Transactional
    public void delete() {
        try {
            if (this.facture != null) {

                if (this.facture.getVenteDirecte()) {

                    if (!Utilitaires.isAccess(25L)) {
                        notifyError("acces_refuse");
                        this.supprimer = this.modifier = this.imprimer = true;
                        this.facture = null;
                        return;
                    }

                    //ut.begin();
                    List<Commande> temp = this.commandeFacadeLocal.findByFacture(this.facture);
                    if (!temp.isEmpty()) {
                        for (Commande c : temp) {
                            Produit p = this.produitFacadeLocal.find(c.getIdproduit().getIdproduit());
                            p.setQuantite((p.getQuantite() + c.getQuantite()));
                            this.produitFacadeLocal.edit(p);

                            Lot lotTemp = this.lotFacadeLocal.find(c.getIdlot().getIdlot());
                            lotTemp.setQuantite((lotTemp.getQuantite() + c.getQuantite()));
                            this.lotFacadeLocal.edit(lotTemp);
                        }
                    }
                    this.commandeFacadeLocal.deleteByIdfacture(facture.getIdfacture());   
                    MvtStock mTemp = this.mvtStockFacadeLocal.find((this.facture.getIdMvtStock().longValue()));
                    ligneMvtStockFacadeLocal.deleteByIdMvtStock(mTemp.getIdMvtStock());
                    //this.mvtStockFacadeLocal.remove(mTemp);
                    //this.factureFacadeLocal.remove(this.facture);
                    this.mvtStockFacadeLocal.deleteMvtById(mTemp.getIdMvtStock());
                    this.factureFacadeLocal.deleteFactureById(facture.getIdfacture());
                    //this.ut.commit();

                    Utilitaires.saveOperation(this.mouchardFacadeLocal, "Annulation de la vente : " + this.facture.getNumeroFacture() + " Montant : " + this.facture.getMontant() + " Client : " + this.facture.getIdclient().getNom(), SessionMBean.getUserAccount());
                    this.facture = new Facture();
                    this.supprimer = this.modifier = this.imprimer = true;
                    notifySuccess();

                    this.factures = this.factureFacadeLocal.findAllDate(SessionMBean.getDateOuverture());
                    this.sommeQuantite();
                } else {
                    notifyError("vente_effectuee_par_commande");
                }
            } else {
                notifyError("not_row_selected");
            }
        } catch (Exception e) {
            e.printStackTrace();
            notifyFail(e);
        }
    }

    public void initPrinter(Facture f) {
        this.facture = f;
        print();
    }

    public void initEdit(Facture f) {
        this.facture = f;
        prepareEdit();
    }

    public void initView(Facture f) {
        this.facture = f;
        prepareview();
    }

    public void initDelete(Facture f) {
        this.facture = f;
        delete();
    }

    public void print() {
        try {
            if (!Utilitaires.isAccess((26L))) {
                notifyError("acces_refuse");
                this.facture = null;
                return;
            }
            if (this.facture != null) {
                List<Commande> c = this.commandeFacadeLocal.findByFacture(this.facture);
                this.facture.setCommandeList(c);
                this.fileName = PrintUtils.printFacture(this.facture, SessionMBean.getParametrage());
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
            if (this.lot == null) {
                JsfUtil.addErrorMessage(this.routine.localizeMessage("article_invalide"));
                return;
            }
            Commande c = this.commande;
            c.setIdcommande((0L));

            this.produit = this.produitFacadeLocal.find(this.produit.getIdproduit());
            this.lot = this.lotFacadeLocal.find(this.lot.getIdlot());

            double q = 0.0D;
            for (Commande c1 : this.commandes) {
                if (c1.getIdlot().equals(this.lot)) {
                    q += c1.getQuantite();
                }
            }

            if (c.getQuantite() + q > this.lot.getQuantite()) {
                JsfUtil.addErrorMessage(this.routine.localizeMessage("quantite_debordee"));
                return;
            }
            Lot lTemp = this.lot;
            Produit produitTemp = this.produit;

            c.setIdlot(lTemp);
            c.setIdproduit(produitTemp);
            this.commandes.add(c);

            RequestContext.getCurrentInstance().execute("PF('ArticleCreateDialog').hide()");
            JsfUtil.addSuccessMessage(this.routine.localizeMessage("operation_reussie"));

            updateTotal();

            this.commande = null;
            this.produit = null;
        } catch (Exception e) {
            e.printStackTrace();
            this.routine.feedBack("information", "/resources/tool_images/error.png", this.routine.localizeMessage("formulaire_incomplet"));
            RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
        }
    }

    public void removeProduit(int index) {
        try {
            Commande c1 = this.commandes.get(index);
            if (c1.getIdcommande() != 0L) {

                this.commandeFacadeLocal.remove(c1);
                Utilitaires.saveOperation(this.mouchardFacadeLocal, "Suppression de l'article : " + c1.getIdproduit().getNom() + " quantité : " + c1.getQuantite() + " dans la facture : " + this.facture.getNumeroFacture(), SessionMBean.getUserAccount());
                ut.begin();

                Commande cOld = commandeFacadeLocal.find(c1.getIdcommande());
                Produit pro = this.produitFacadeLocal.find(c1.getIdproduit().getIdproduit());
                pro.setQuantite((pro.getQuantite() + cOld.getQuantite()));
                this.produitFacadeLocal.edit(pro);

                Lot l = this.lotFacadeLocal.find(c1.getIdlot().getIdlot());
                l.setQuantite((l.getQuantite() + cOld.getQuantite()));
                this.lotFacadeLocal.edit(l);

                LigneMvtStock lmvt = ligneMvtStockFacadeLocal.findByMvtIdLot(facture.getIdMvtStock().longValue(), l.getIdlot());
                if (lmvt != null) {
                    ligneMvtStockFacadeLocal.remove(lmvt);
                }

                Facture fOld = factureFacadeLocal.find(facture.getIdfacture());
                fOld.setMontant(fOld.getMontant() - (cOld.getQuantite() * cOld.getMontant()));
                this.factureFacadeLocal.edit(fOld);
                ut.commit();
            }
            this.commandes.remove(index);

            updateTotal();

            JsfUtil.addSuccessMessage("Supprimé");
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addErrorMessage(this.routine.localizeMessage("echec_operation"));
        }
    }

    public Double calculTotal(List<Commande> commandes) {
        Double resultat = 0.0D;
        int i = 0;
        for (Commande c : commandes) {
            resultat += (c.getMontant() * c.getQuantite());
            commandes.get(i).setBenefice(((commandes.get(i).getMontant() - commandes.get(i).getIdlot().getPrixAchat()) * commandes.get(i).getQuantite()));
            if (commandes.get(i).getBenefice() < 0d) {
                commandes.get(i).setBenefice(0d);
            }
            i++;
        }
        return resultat;
    }

    public void updateTotal() {
        try {
            this.total = calculTotal(this.commandes);
            this.facture.setMontant(this.total);

            if (this.facture.getCalculRemise()) {
                this.facture.setMontantRemise((this.total * this.facture.getTauxRemise() / 100d));
            } else {
                this.facture.setMontantRemise((0.0D));
            }

            if (this.facture.getCalcultva()) {
                this.facture.setMontantTva(((this.total - this.facture.getMontantRemise()) * this.facture.getTauxTva() / 100d));
            } else {
                this.facture.setMontantTva((0.0D));
            }
            this.facture.setMontantTtc((this.total - this.facture.getMontantRemise() + this.facture.getMontantTva()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateTotaux() {
        try {
            this.cout_quantite = 0.0D;
            if (this.commande.getQuantite() != null && this.commande.getMontant() != null) {
                this.cout_quantite = (this.commande.getMontant() * this.commande.getQuantite());
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.cout_quantite = 0.0D;
        }
    }

    public void updatedata() {
        try {
            if (this.produit != null) {
                this.lot = new Lot();
                this.famille = this.produit.getIdfamille();

                this.lots = this.lotFacadeLocal.findByArticle(this.produit.getIdproduit(), this.produit.getPerissable(), new Date());

                if (Objects.equals(this.lots.size(), 0)) {
                    this.lot = null;
                    return;
                }
                if (this.lots.size() == 1) {
                    this.lot = this.lots.get(0);
                    this.commande.setMontant(this.lot.getPrixVente());
                    return;
                }
                this.lot = this.lots.get(0);
                this.commande.setMontant(this.lot.getPrixVente());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updatedataLot() {
        try {
            if (this.lot != null) {
                this.commande.setMontant(this.lot.getPrixVente());
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
