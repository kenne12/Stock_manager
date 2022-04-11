package controllers.commande_fournisseur;

import entities.Annee;
import entities.AnneeMois;
import entities.CommandeFournisseur;
import entities.Famille;
import entities.Fournisseur;
import entities.LigneCmdeFournisseur;
import entities.Lot;
import entities.Produit;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.transaction.UserTransaction;
import sessions.AnneeFacadeLocal;
import sessions.AnneeMoisFacadeLocal;
import sessions.CommandeFournisseurFacadeLocal;
import sessions.FamilleFacadeLocal;
import sessions.FournisseurFacadeLocal;
import sessions.LigneCmdeFournisseurFacadeLocal;
import sessions.LotFacadeLocal;
import sessions.MouchardFacadeLocal;
import sessions.ProduitFacadeLocal;
import utils.Routine;
import utils.SessionMBean;

public class AbstractCommandeFournisseurController {

    @Resource
    protected UserTransaction ut;
    @EJB
    protected CommandeFournisseurFacadeLocal commandeFournisseurFacadeLocal;
    /*  44 */    protected CommandeFournisseur commandeFournisseur = new CommandeFournisseur();
    /*  45 */    protected List<CommandeFournisseur> commandeFournisseurs = new ArrayList<>();

    @EJB
    protected LigneCmdeFournisseurFacadeLocal ligneCmdeFournisseurFacadeLocal;
    /*  49 */    protected LigneCmdeFournisseur ligneCmdeFournisseur = new LigneCmdeFournisseur();
    /*  50 */    protected List<LigneCmdeFournisseur> ligneCmdeFournisseurs = new ArrayList<>();

    @EJB
    protected FamilleFacadeLocal familleFacadeLocal;
    /*  54 */    protected Famille famille = new Famille();
    /*  55 */    protected List<Famille> familles = new ArrayList<>();

    @EJB
    protected ProduitFacadeLocal produitFacadeLocal;
    /*  59 */    protected Produit produit = new Produit();
    /*  60 */    protected List<Produit> produits = new ArrayList<>();

    @EJB
    protected FournisseurFacadeLocal fournisseurFacadeLocal;
    /*  64 */    protected Fournisseur fournisseur = new Fournisseur();
    /*  65 */    protected List<Fournisseur> fournisseurs = new ArrayList<>();

    @EJB
    protected AnneeFacadeLocal anneeFacadeLocal;
    /*  69 */    protected Annee annee = SessionMBean.getMois().getIdannee();
    /*  70 */    protected List<Annee> annees = new ArrayList<>();

    @EJB
    protected AnneeMoisFacadeLocal anneeMoisFacadeLocal;
    /*  74 */    protected AnneeMois anneeMois = SessionMBean.getMois();
    /*  75 */    protected List<AnneeMois> anneeMoises = new ArrayList<>();

    @EJB
    protected LotFacadeLocal lotFacadeLocal;
    /*  79 */    protected Lot lot = new Lot();
    /*  80 */    protected List<Lot> lots = new ArrayList<>();

    @EJB
    protected MouchardFacadeLocal mouchardFacadeLocal;

    /*  85 */    protected Routine routine = new Routine();

    /*  87 */    protected Double cout_quantite = Double.valueOf(0.0D);
    /*  88 */    protected Double total = Double.valueOf(0.0D);

    /*  90 */    protected Boolean detail = Boolean.valueOf(true);
    /*  91 */    protected Boolean modifier = Boolean.valueOf(true);
    /*  92 */    protected Boolean consulter = Boolean.valueOf(true);
    /*  93 */    protected Boolean imprimer = Boolean.valueOf(true);
    /*  94 */    protected Boolean supprimer = Boolean.valueOf(true);

    /*  96 */    protected String fileName = "";

    /*  98 */    protected String mode = "";

    public Produit getProduit() {
        /* 101 */ return this.produit;
    }

    public void setProduit(Produit produit) {
        /* 105 */ this.produit = produit;
    }

    public List<Produit> getProduits() {
        /* 109 */ return this.produits;
    }

    public Boolean getDetail() {
        /* 113 */ return this.detail;
    }

    public Boolean getModifier() {
        /* 117 */ return this.modifier;
    }

    public Boolean getConsulter() {
        /* 121 */ return this.consulter;
    }

    public Boolean getImprimer() {
        /* 125 */ return this.imprimer;
    }

    public Boolean getSupprimer() {
        /* 129 */ return this.supprimer;
    }

    public Famille getFamille() {
        /* 133 */ return this.famille;
    }

    public void setFamille(Famille famille) {
        /* 137 */ this.famille = famille;
    }

    public List<Famille> getFamilles() {
        /* 141 */ this.familles = this.familleFacadeLocal.findAllRange();
        /* 142 */ return this.familles;
    }

    public Double getCout_quantite() {
        /* 146 */ return this.cout_quantite;
    }

    public void setCout_quantite(Double cout_quantite) {
        /* 150 */ this.cout_quantite = cout_quantite;
    }

    public Double getTotal() {
        /* 154 */ return this.total;
    }

    public void setTotal(Double total) {
        /* 158 */ this.total = total;
    }

    public Annee getAnnee() {
        /* 162 */ return this.annee;
    }

    public void setAnnee(Annee annee) {
        /* 166 */ this.annee = annee;
    }

    public List<Annee> getAnnees() {
        try {
            /* 171 */ this.annees = this.anneeFacadeLocal.findByEtat(true);
            /* 172 */        } catch (Exception e) {
            /* 173 */ e.printStackTrace();
        }
        /* 175 */ return this.annees;
    }

    public AnneeMois getAnneeMois() {
        /* 179 */ return this.anneeMois;
    }

    public void setAnneeMois(AnneeMois anneeMois) {
        /* 183 */ this.anneeMois = anneeMois;
    }

    public List<AnneeMois> getAnneeMoises() {
        try {
            /* 188 */ this.anneeMoises = this.anneeMoisFacadeLocal.findByEtat(Boolean.valueOf(true));
            /* 189 */        } catch (Exception e) {
            /* 190 */ e.printStackTrace();
        }
        /* 192 */ return this.anneeMoises;
    }

    public String getFileName() {
        /* 196 */ return this.fileName;
    }

    public Routine getRoutine() {
        /* 200 */ return this.routine;
    }

    public Lot getLot() {
        /* 204 */ return this.lot;
    }

    public void setLot(Lot lot) {
        /* 208 */ this.lot = lot;
    }

    public List<Lot> getLots() {
        /* 212 */ return this.lots;
    }

    public CommandeFournisseur getCommandeFournisseur() {
        /* 216 */ return this.commandeFournisseur;
    }

    public void setCommandeFournisseur(CommandeFournisseur commandeFournisseur) {
        /* 220 */ this.commandeFournisseur = commandeFournisseur;
        /* 221 */ this.modifier = this.supprimer = this.detail = this.imprimer = Boolean.valueOf((commandeFournisseur == null));
    }

    public List<CommandeFournisseur> getCommandeFournisseurs() {
        try {
            /* 226 */ this.commandeFournisseurs = this.commandeFournisseurFacadeLocal.findByInterval(SessionMBean.getMois().getDateDebut(), SessionMBean.getMois().getDateFin());
            /* 227 */        } catch (Exception e) {
            /* 228 */ e.printStackTrace();
        }
        /* 230 */ return this.commandeFournisseurs;
    }

    public LigneCmdeFournisseur getLigneCmdeFournisseur() {
        /* 234 */ return this.ligneCmdeFournisseur;
    }

    public void setLigneCmdeFournisseur(LigneCmdeFournisseur ligneCmdeFournisseur) {
        /* 238 */ this.ligneCmdeFournisseur = ligneCmdeFournisseur;
    }

    public List<LigneCmdeFournisseur> getLigneCmdeFournisseurs() {
        /* 242 */ return this.ligneCmdeFournisseurs;
    }

    public Fournisseur getFournisseur() {
        /* 246 */ return this.fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        /* 250 */ this.fournisseur = fournisseur;
    }

    public List<Fournisseur> getFournisseurs() {
        /* 254 */ this.fournisseurs = this.fournisseurFacadeLocal.findAllRange();
        /* 255 */ return this.fournisseurs;
    }
}
