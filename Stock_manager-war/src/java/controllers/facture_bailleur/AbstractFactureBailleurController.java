package controllers.facture_bailleur;

import entities.Annee;
import entities.AnneeMois;
import entities.Bailleur;
import entities.Commande;
import entities.Facture;
import entities.FactureBailleur;
import entities.Famille;
import entities.LigneFactBailleur;
import entities.Produit;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.transaction.UserTransaction;
import sessions.AnneeFacadeLocal;
import sessions.AnneeMoisFacadeLocal;
import sessions.BailleurFacadeLocal;
import sessions.CommandeFacadeLocal;
import sessions.FactureBailleurArticleFacadeLocal;
import sessions.FactureBailleurFacadeLocal;
import sessions.FactureFacadeLocal;
import sessions.FamilleFacadeLocal;
import sessions.LigneFactBailleurFacadeLocal;
import sessions.LotFacadeLocal;
import sessions.MouchardFacadeLocal;
import sessions.ProduitFacadeLocal;
import utils.Routine;
import utils.SessionMBean;

public class AbstractFactureBailleurController {

    @Resource
    protected UserTransaction ut;
    @EJB
    protected FactureBailleurFacadeLocal factureBailleurFacadeLocal;
    /*  48 */    protected FactureBailleur factureBailleur = new FactureBailleur();
    /*  49 */    protected List<FactureBailleur> factureBailleurs = new ArrayList<>();

    @EJB
    protected BailleurFacadeLocal bailleurFacadeLocal;
    /*  53 */    protected Bailleur bailleur = new Bailleur();
    /*  54 */    protected List<Bailleur> bailleurs = new ArrayList<>();

    @EJB
    protected LigneFactBailleurFacadeLocal ligneFactBailleurFacadeLocal;
    /*  58 */    protected List<LigneFactBailleur> ligneFactBailleurs = new ArrayList<>();

    @EJB
    protected FamilleFacadeLocal familleFacadeLocal;
    /*  62 */    protected Famille famille = new Famille();
    /*  63 */    protected List<Famille> familles = new ArrayList<>();

    @EJB
    protected ProduitFacadeLocal produitFacadeLocal;
    /*  67 */    protected Produit produit = new Produit();
    /*  68 */    protected List<Produit> produits = new ArrayList<>();

    @EJB
    protected FactureFacadeLocal factureFacadeLocal;
    /*  72 */    protected List<Facture> factures = new ArrayList<>();

    @EJB
    protected CommandeFacadeLocal commandeFacadeLocal;
    /*  76 */    protected List<Commande> commandes = new ArrayList<>();

    @EJB
    protected AnneeFacadeLocal anneeFacadeLocal;
    /*  80 */    protected Annee annee = SessionMBean.getMois().getIdannee();
    /*  81 */    protected List<Annee> annees = new ArrayList<>();

    @EJB
    protected AnneeMoisFacadeLocal anneeMoisFacadeLocal;
    /*  85 */    protected AnneeMois anneeMois = SessionMBean.getMois();
    /*  86 */    protected List<AnneeMois> anneeMoises = new ArrayList<>();

    @EJB
    protected FactureBailleurArticleFacadeLocal factureBailleurArticleFacadeLocal;

    @EJB
    protected LotFacadeLocal lotFacadeLocal;

    @EJB
    protected MouchardFacadeLocal mouchardFacadeLocal;

    /*  97 */    protected Routine routine = new Routine();

    /*  99 */    protected Double total = Double.valueOf(0.0D);

    /* 101 */    protected Boolean detail = Boolean.valueOf(true);
    /* 102 */    protected Boolean modifier = Boolean.valueOf(true);
    /* 103 */    protected Boolean consulter = Boolean.valueOf(true);
    /* 104 */    protected Boolean imprimer = Boolean.valueOf(true);
    /* 105 */    protected Boolean supprimer = Boolean.valueOf(true);

    protected boolean disabledSearch = false;

    /* 109 */    protected String fileName = "";

    /* 111 */    protected String mode = "";

    public Boolean getDetail() {
        /* 114 */ return this.detail;
    }

    public Boolean getModifier() {
        /* 118 */ return this.modifier;
    }

    public Boolean getConsulter() {
        /* 122 */ return this.consulter;
    }

    public Boolean getImprimer() {
        /* 126 */ return this.imprimer;
    }

    public Boolean getSupprimer() {
        /* 130 */ return this.supprimer;
    }

    public Famille getFamille() {
        /* 134 */ return this.famille;
    }

    public void setFamille(Famille famille) {
        /* 138 */ this.famille = famille;
    }

    public List<Famille> getFamilles() {
        /* 142 */ this.familles = this.familleFacadeLocal.findAllRange();
        /* 143 */ return this.familles;
    }

    public Double getTotal() {
        /* 147 */ return this.total;
    }

    public void setTotal(Double total) {
        /* 151 */ this.total = total;
    }

    public Annee getAnnee() {
        /* 155 */ return this.annee;
    }

    public void setAnnee(Annee annee) {
        /* 159 */ this.annee = annee;
    }

    public List<Annee> getAnnees() {
        try {
            /* 164 */ this.annees = this.anneeFacadeLocal.findByEtat(true);
            /* 165 */        } catch (Exception e) {
            /* 166 */ e.printStackTrace();
        }
        /* 168 */ return this.annees;
    }

    public AnneeMois getAnneeMois() {
        /* 172 */ return this.anneeMois;
    }

    public void setAnneeMois(AnneeMois anneeMois) {
        /* 176 */ this.anneeMois = anneeMois;
    }

    public List<AnneeMois> getAnneeMoises() {
        try {
            /* 181 */ this.anneeMoises = this.anneeMoisFacadeLocal.findByEtat(Boolean.valueOf(true));
            /* 182 */        } catch (Exception e) {
            /* 183 */ e.printStackTrace();
        }
        /* 185 */ return this.anneeMoises;
    }

    public String getFileName() {
        /* 189 */ return this.fileName;
    }

    public Routine getRoutine() {
        /* 193 */ return this.routine;
    }

    public FactureBailleur getFactureBailleur() {
        /* 197 */ return this.factureBailleur;
    }

    public void setFactureBailleur(FactureBailleur factureBailleur) {
        /* 201 */ this.factureBailleur = factureBailleur;
        /* 202 */ this.modifier = this.supprimer = this.detail = this.imprimer = Boolean.valueOf((factureBailleur == null));
    }

    public List<FactureBailleur> getFactureBailleurs() {
        /* 206 */ this.factureBailleurs = this.factureBailleurFacadeLocal.findAllRange();
        /* 207 */ return this.factureBailleurs;
    }

    public void setFactureBailleurs(List<FactureBailleur> factureBailleurs) {
        /* 211 */ this.factureBailleurs = factureBailleurs;
    }

    public List<LigneFactBailleur> getLigneFactBailleurs() {
        /* 215 */ return this.ligneFactBailleurs;
    }

    public void setLigneFactBailleurs(List<LigneFactBailleur> ligneFactBailleurs) {
        /* 219 */ this.ligneFactBailleurs = ligneFactBailleurs;
    }

    public boolean isDisabledSearch() {
        /* 223 */ return this.disabledSearch;
    }

    public Bailleur getBailleur() {
        /* 227 */ return this.bailleur;
    }

    public void setBailleur(Bailleur bailleur) {
        /* 231 */ this.bailleur = bailleur;
    }

    public List<Bailleur> getBailleurs() {
        /* 235 */ this.bailleurs = this.bailleurFacadeLocal.findAllRange();
        /* 236 */ return this.bailleurs;
    }

    public void setBailleurs(List<Bailleur> bailleurs) {
        /* 240 */ this.bailleurs = bailleurs;
    }
}


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-war.war!\WEB-INF\classes\controllers\facture_bailleur\AbstractFactureBailleurController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
