package controllers.inventaire_global;

import entities.Annee;
import entities.AnneeMois;
import entities.Inventaire;
import entities.Ligneinventaire;
import entities.Lot;
import entities.MvtStock;
import entities.Produit;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.transaction.UserTransaction;
import sessions.AnneeFacadeLocal;
import sessions.AnneeMoisFacadeLocal;
import sessions.InventaireFacadeLocal;
import sessions.LigneMvtStockFacadeLocal;
import sessions.LigneinventaireFacadeLocal;
import sessions.LotFacadeLocal;
import sessions.MouchardFacadeLocal;
import sessions.MvtStockFacadeLocal;
import sessions.ProduitFacadeLocal;
import utils.Routine;
import utils.SessionMBean;

public class AbstractInventaireGlobalController {

    @Resource
    protected UserTransaction ut;
    @EJB
    protected InventaireFacadeLocal inventaireFacadeLocal;
    protected Inventaire inventaire = null;
    protected List<Inventaire> inventaires = new ArrayList<>();

    @EJB
    protected LigneinventaireFacadeLocal ligneinventaireFacadeLocal;
    protected List<Ligneinventaire> ligneinventaires = new ArrayList<>();

    @EJB
    protected ProduitFacadeLocal produitFacadeLocal;
    protected List<Produit> produits = new ArrayList<>();

    @EJB
    protected AnneeFacadeLocal anneeFacadeLocal;
    protected Annee annee = SessionMBean.getMois().getIdannee();
    protected List<Annee> annees = new ArrayList<>();

    @EJB
    protected AnneeMoisFacadeLocal anneeMoisFacadeLocal;
    protected AnneeMois anneeMois = SessionMBean.getMois();
    protected List<AnneeMois> anneeMoises = new ArrayList<>();

    @EJB
    protected LotFacadeLocal lotFacadeLocal;
    protected List<Lot> lots = new ArrayList<>();
    protected List<Lot> selectedLots = new ArrayList<>();

    @EJB
    protected MvtStockFacadeLocal mvtStockFacadeLocal;
    protected MvtStock mvtStock = new MvtStock();

    @EJB
    protected LigneMvtStockFacadeLocal ligneMvtStockFacadeLocal;

    protected boolean editQuantite = false;

    @EJB
    protected MouchardFacadeLocal mouchardFacadeLocal;

    protected Routine routine = new Routine();

    protected Boolean detail = (true);
    protected Boolean modifier = (true);
    protected Boolean consulter = (true);
    protected Boolean imprimer = (true);
    protected Boolean supprimer = (true);

    protected boolean valider = true;
    protected boolean cancel = true;
    protected String fileName = "";

    protected boolean showSelectArticle = false;
    protected String mode = "";
    protected String valideBtn = "";

    public Boolean getDetail() {
        return this.detail;
    }

    public Boolean getModifier() {
        try {
            if (this.inventaire != null) {
                if (this.inventaire.getValidee()) {
                    this.modifier = (true);
                } else {
                    this.modifier = (false);
                }
            } else {
                this.modifier = (true);
            }
        } catch (Exception e) {
            this.modifier = (true);
        }
        return this.modifier;
    }

    public boolean isValider() {
        try {
            if (this.inventaire != null) {
                if (this.inventaire.getValidee()) {
                    this.valider = true;
                } else {
                    this.valider = false;
                }
            } else {
                this.valider = true;
            }
        } catch (Exception e) {
            this.valider = true;
        }
        return this.valider;
    }

    public Boolean getConsulter() {
        return this.consulter;
    }

    public Boolean getImprimer() {
        return this.imprimer;
    }

    public Boolean getSupprimer() {
        try {
            if (this.inventaire != null) {
                if (this.inventaire.getValidee()) {
                    this.supprimer = true;
                } else {
                    this.supprimer = false;
                }
            } else {
                this.supprimer = true;
            }
        } catch (Exception e) {
            this.supprimer = true;
        }
        return this.supprimer;
    }

    public Annee getAnnee() {
        return this.annee;
    }

    public void setAnnee(Annee annee) {
        this.annee = annee;
    }

    public List<Annee> getAnnees() {
        try {
            this.annees = this.anneeFacadeLocal.findByEtat(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.annees;
    }

    public AnneeMois getAnneeMois() {
        return this.anneeMois;
    }

    public void setAnneeMois(AnneeMois anneeMois) {
        this.anneeMois = anneeMois;
    }

    public List<AnneeMois> getAnneeMoises() {
        try {
            this.anneeMoises = this.anneeMoisFacadeLocal.findByEtat(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.anneeMoises;
    }

    public String getFileName() {
        return this.fileName;
    }

    public Routine getRoutine() {
        return this.routine;
    }

    public List<Lot> getLots() {
        return this.lots;
    }

    public Inventaire getInventaire() {
        return this.inventaire;
    }

    public void setInventaire(Inventaire inventaire) {
        this.inventaire = inventaire;
        this.supprimer = this.detail = this.imprimer = (inventaire == null);
    }

    public List<Inventaire> getInventaires() {
        try {
            this.inventaires = this.inventaireFacadeLocal.findAllRange();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.inventaires;
    }

    public void setInventaires(List<Inventaire> inventaires) {
        this.inventaires = inventaires;
    }

    public List<Ligneinventaire> getLigneinventaires() {
        return this.ligneinventaires;
    }

    public void setLigneinventaires(List<Ligneinventaire> ligneinventaires) {
        this.ligneinventaires = ligneinventaires;
    }

    public String getValideBtn() {
        return this.valideBtn;
    }

    public void setValideBtn(String valideBtn) {
        this.valideBtn = valideBtn;
    }

    public List<Lot> getSelectedLots() {
        return this.selectedLots;
    }

    public void setSelectedLots(List<Lot> selectedLots) {
        this.selectedLots = selectedLots;
    }

    public boolean isShowSelectArticle() {
        return this.showSelectArticle;
    }

    public boolean isEditQuantite() {
        return this.editQuantite;
    }

    public boolean isCancel() {
        try {
            if (this.inventaire != null) {
                if (this.inventaire.getValidee()) {
                    this.cancel = false;
                } else {
                    this.cancel = true;
                }
            } else {
                this.cancel = true;
            }
        } catch (Exception e) {
            this.cancel = true;
        }
        return this.cancel;
    }
}
