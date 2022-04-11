package controllers.reglement;

import entities.Annee;
import entities.AnneeMois;
import entities.Client;
import entities.Commande;
import entities.Facture;
import entities.Famille;
import entities.Produit;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.transaction.UserTransaction;
import sessions.AnneeFacadeLocal;
import sessions.AnneeMoisFacadeLocal;
import sessions.ClientFacadeLocal;
import sessions.CommandeFacadeLocal;
import sessions.FactureFacadeLocal;
import sessions.FamilleFacadeLocal;
import sessions.MouchardFacadeLocal;
import sessions.PrivilegeFacadeLocal;
import sessions.ProduitFacadeLocal;

public class AbstractReglementController {

    @Resource
    protected UserTransaction ut;
    @EJB
    protected FamilleFacadeLocal familleFacadeLocal;
    /*  41 */    protected Famille famille = new Famille();
    /*  42 */    protected List<Famille> familles = new ArrayList<>();

    @EJB
    protected ProduitFacadeLocal produitFacadeLocal;
    /*  46 */    protected Produit produit = new Produit();
    /*  47 */    protected List<Produit> produits = new ArrayList<>();

    @EJB
    protected ClientFacadeLocal clientFacadeLocal;
    /*  51 */    protected Client client = new Client();
    /*  52 */    protected List<Client> clients = new ArrayList<>();

    @EJB
    protected FactureFacadeLocal factureFacadeLocal;
    /*  56 */    protected Facture facture = new Facture();
    /*  57 */    protected List<Facture> factures = new ArrayList<>();

    @EJB
    protected CommandeFacadeLocal commandeFacadeLocal;
    /*  61 */    protected Commande commande = new Commande();
    /*  62 */    protected List<Commande> commandes = new ArrayList<>();

    @EJB
    protected AnneeFacadeLocal anneeFacadeLocal;
    /*  66 */    protected Annee annee = new Annee();
    /*  67 */    protected List<Annee> annees = new ArrayList<>();

    @EJB
    protected AnneeMoisFacadeLocal anneeMoisFacadeLocal;
    /*  71 */    protected AnneeMois anneeMois = new AnneeMois();
    /*  72 */    protected List<AnneeMois> anneeMoises = new ArrayList<>();

    @EJB
    protected MouchardFacadeLocal mouchardFacadeLocal;

    @EJB
    protected PrivilegeFacadeLocal privilegeFacadeLocal;

    /*  80 */    protected Double cout_quantite = Double.valueOf(0.0D);
    /*  81 */    protected Double total = Double.valueOf(0.0D);

    /*  83 */    protected Double montant_a_regler = Double.valueOf(0.0D);

    /*  86 */    protected Boolean nouveauClient = Boolean.valueOf(true);

    /*  88 */    protected Boolean saisieClient = Boolean.valueOf(true);

    /*  90 */    protected Boolean selectClient = Boolean.valueOf(true);

    /*  92 */    protected Boolean showSelector = Boolean.valueOf(true);

    /*  94 */    protected Boolean detail = Boolean.valueOf(true);
    /*  95 */    protected Boolean modifier = Boolean.valueOf(true);
    /*  96 */    protected Boolean consulter = Boolean.valueOf(true);
    /*  97 */    protected Boolean imprimer = Boolean.valueOf(true);
    /*  98 */    protected Boolean supprimer = Boolean.valueOf(true);

    /* 100 */    protected Boolean showEditSolde = Boolean.valueOf(true);

    /* 102 */    protected Boolean showUserCreateDialog = Boolean.valueOf(false);
    /* 103 */    protected Boolean showUserDetailDialog = Boolean.valueOf(false);
    /* 104 */    protected Boolean showUserDeleteDialog = Boolean.valueOf(false);
    /* 105 */    protected Boolean showUserEditDialog = Boolean.valueOf(false);
    /* 106 */    protected Boolean showUserPrintDialog = Boolean.valueOf(false);

    protected boolean buttonActif = false;

    protected boolean buttonInactif = true;

    protected boolean showClientSolde = false;

    protected boolean payement_espece_compte = true;

    protected boolean payement_credit = false;
    /* 117 */    protected String fileName = "";

    /* 119 */    protected String mode = "";

    public ProduitFacadeLocal getProduitFacadeLocal() {
        /* 122 */ return this.produitFacadeLocal;
    }

    public void setProduitFacadeLocal(ProduitFacadeLocal produitFacadeLocal) {
        /* 126 */ this.produitFacadeLocal = produitFacadeLocal;
    }

    public Produit getProduit() {
        /* 130 */ return this.produit;
    }

    public void setProduit(Produit produit) {
        /* 134 */ this.produit = produit;
    }

    public List<Produit> getProduits() {
        /* 138 */ return this.produits;
    }

    public void setProduits(List<Produit> produits) {
        /* 142 */ this.produits = produits;
    }

    public ClientFacadeLocal getClientFacadeLocal() {
        /* 146 */ return this.clientFacadeLocal;
    }

    public void setClientFacadeLocal(ClientFacadeLocal clientFacadeLocal) {
        /* 150 */ this.clientFacadeLocal = clientFacadeLocal;
    }

    public Client getClient() {
        /* 154 */ return this.client;
    }

    public void setClient(Client client) {
        /* 158 */ this.client = client;
    }

    public List<Client> getClients() {
        /* 162 */ this.clients = this.clientFacadeLocal.findAllRange();
        /* 163 */ return this.clients;
    }

    public void setClients(List<Client> clients) {
        /* 167 */ this.clients = clients;
    }

    public FactureFacadeLocal getFactureFacadeLocal() {
        /* 171 */ return this.factureFacadeLocal;
    }

    public void setFactureFacadeLocal(FactureFacadeLocal factureFacadeLocal) {
        /* 175 */ this.factureFacadeLocal = factureFacadeLocal;
    }

    public Facture getFacture() {
        /* 179 */ return this.facture;
    }

    public void setFacture(Facture facture) {
        /* 183 */ this.modifier = this.supprimer = this.detail = this.imprimer = Boolean.valueOf((facture == null));
        /* 184 */ this.facture = facture;
    }

    public List<Facture> getFactures() {
        /* 188 */ return this.factures;
    }

    public void setFactures(List<Facture> factures) {
        /* 192 */ this.factures = factures;
    }

    public CommandeFacadeLocal getCommandeFacadeLocal() {
        /* 196 */ return this.commandeFacadeLocal;
    }

    public void setCommandeFacadeLocal(CommandeFacadeLocal commandeFacadeLocal) {
        /* 200 */ this.commandeFacadeLocal = commandeFacadeLocal;
    }

    public Commande getCommande() {
        /* 204 */ return this.commande;
    }

    public void setCommande(Commande commande) {
        /* 208 */ this.commande = commande;
    }

    public List<Commande> getCommandes() {
        /* 212 */ return this.commandes;
    }

    public void setCommandes(List<Commande> commandes) {
        /* 216 */ this.commandes = commandes;
    }

    public MouchardFacadeLocal getMouchardFacadeLocal() {
        /* 220 */ return this.mouchardFacadeLocal;
    }

    public void setMouchardFacadeLocal(MouchardFacadeLocal mouchardFacadeLocal) {
        /* 224 */ this.mouchardFacadeLocal = mouchardFacadeLocal;
    }

    public PrivilegeFacadeLocal getPrivilegeFacadeLocal() {
        /* 228 */ return this.privilegeFacadeLocal;
    }

    public void setPrivilegeFacadeLocal(PrivilegeFacadeLocal privilegeFacadeLocal) {
        /* 232 */ this.privilegeFacadeLocal = privilegeFacadeLocal;
    }

    public Boolean getDetail() {
        /* 236 */ return this.detail;
    }

    public void setDetail(Boolean detail) {
        /* 240 */ this.detail = detail;
    }

    public Boolean getModifier() {
        /* 244 */ return this.modifier;
    }

    public void setModifier(Boolean modifier) {
        /* 248 */ this.modifier = modifier;
    }

    public Boolean getConsulter() {
        /* 252 */ return this.consulter;
    }

    public void setConsulter(Boolean consulter) {
        /* 256 */ this.consulter = consulter;
    }

    public Boolean getImprimer() {
        /* 260 */ return this.imprimer;
    }

    public void setImprimer(Boolean imprimer) {
        /* 264 */ this.imprimer = imprimer;
    }

    public Boolean getSupprimer() {
        /* 268 */ return this.supprimer;
    }

    public void setSupprimer(Boolean supprimer) {
        /* 272 */ this.supprimer = supprimer;
    }

    public Boolean getShowEditSolde() {
        /* 276 */ return this.showEditSolde;
    }

    public void setShowEditSolde(Boolean showEditSolde) {
        /* 280 */ this.showEditSolde = showEditSolde;
    }

    public Boolean getShowUserCreateDialog() {
        /* 284 */ return this.showUserCreateDialog;
    }

    public void setShowUserCreateDialog(Boolean showUserCreateDialog) {
        /* 288 */ this.showUserCreateDialog = showUserCreateDialog;
    }

    public Boolean getShowUserDetailDialog() {
        /* 292 */ return this.showUserDetailDialog;
    }

    public void setShowUserDetailDialog(Boolean showUserDetailDialog) {
        /* 296 */ this.showUserDetailDialog = showUserDetailDialog;
    }

    public Boolean getShowUserDeleteDialog() {
        /* 300 */ return this.showUserDeleteDialog;
    }

    public void setShowUserDeleteDialog(Boolean showUserDeleteDialog) {
        /* 304 */ this.showUserDeleteDialog = showUserDeleteDialog;
    }

    public Boolean getShowUserEditDialog() {
        /* 308 */ return this.showUserEditDialog;
    }

    public void setShowUserEditDialog(Boolean showUserEditDialog) {
        /* 312 */ this.showUserEditDialog = showUserEditDialog;
    }

    public Boolean getShowUserPrintDialog() {
        /* 316 */ return this.showUserPrintDialog;
    }

    public void setShowUserPrintDialog(Boolean showUserPrintDialog) {
        /* 320 */ this.showUserPrintDialog = showUserPrintDialog;
    }

    public boolean isButtonActif() {
        /* 324 */ return this.buttonActif;
    }

    public void setButtonActif(boolean buttonActif) {
        /* 328 */ this.buttonActif = buttonActif;
    }

    public boolean isButtonInactif() {
        /* 332 */ return this.buttonInactif;
    }

    public void setButtonInactif(boolean buttonInactif) {
        /* 336 */ this.buttonInactif = buttonInactif;
    }

    public Famille getFamille() {
        /* 340 */ return this.famille;
    }

    public void setFamille(Famille famille) {
        /* 344 */ this.famille = famille;
    }

    public List<Famille> getFamilles() {
        /* 348 */ this.familles = this.familleFacadeLocal.findAllRange();
        /* 349 */ return this.familles;
    }

    public void setFamilles(List<Famille> familles) {
        /* 353 */ this.familles = familles;
    }

    public Double getCout_quantite() {
        /* 357 */ return this.cout_quantite;
    }

    public void setCout_quantite(Double cout_quantite) {
        /* 361 */ this.cout_quantite = cout_quantite;
    }

    public Double getTotal() {
        /* 365 */ return this.total;
    }

    public void setTotal(Double total) {
        /* 369 */ this.total = total;
    }

    public Annee getAnnee() {
        /* 373 */ return this.annee;
    }

    public void setAnnee(Annee annee) {
        /* 377 */ this.annee = annee;
    }

    public List<Annee> getAnnees() {
        try {
            /* 382 */ this.annees = this.anneeFacadeLocal.findByEtat(true);
            /* 383 */        } catch (Exception e) {
            /* 384 */ e.printStackTrace();
        }
        /* 386 */ return this.annees;
    }

    public void setAnnees(List<Annee> annees) {
        /* 390 */ this.annees = annees;
    }

    public AnneeMois getAnneeMois() {
        /* 394 */ return this.anneeMois;
    }

    public void setAnneeMois(AnneeMois anneeMois) {
        /* 398 */ this.anneeMois = anneeMois;
    }

    public List<AnneeMois> getAnneeMoises() {
        try {
            /* 403 */ this.anneeMoises = this.anneeMoisFacadeLocal.findByEtat(Boolean.valueOf(true));
            /* 404 */        } catch (Exception e) {
            /* 405 */ e.printStackTrace();
        }
        /* 407 */ return this.anneeMoises;
    }

    public void setAnneeMoises(List<AnneeMois> anneeMoises) {
        /* 411 */ this.anneeMoises = anneeMoises;
    }

    public boolean isShowClientSolde() {
        /* 415 */ return this.showClientSolde;
    }

    public void setShowClientSolde(boolean showClientSolde) {
        /* 419 */ this.showClientSolde = showClientSolde;
    }

    public boolean isPayement_espece_compte() {
        /* 423 */ return this.payement_espece_compte;
    }

    public void setPayement_espece_compte(boolean payement_espece_compte) {
        /* 427 */ this.payement_espece_compte = payement_espece_compte;
    }

    public boolean isPayement_credit() {
        /* 431 */ return this.payement_credit;
    }

    public void setPayement_credit(boolean payement_credit) {
        /* 435 */ this.payement_credit = payement_credit;
    }

    public String getFileName() {
        /* 439 */ return this.fileName;
    }

    public void setFileName(String fileName) {
        /* 443 */ this.fileName = fileName;
    }

    public Boolean getNouveauClient() {
        /* 447 */ return this.nouveauClient;
    }

    public void setNouveauClient(Boolean nouveauClient) {
        /* 451 */ this.nouveauClient = nouveauClient;
    }

    public Boolean getSaisieClient() {
        /* 455 */ return this.saisieClient;
    }

    public void setSaisieClient(Boolean saisieClient) {
        /* 459 */ this.saisieClient = saisieClient;
    }

    public Boolean getSelectClient() {
        /* 463 */ return this.selectClient;
    }

    public void setSelectClient(Boolean selectClient) {
        /* 467 */ this.selectClient = selectClient;
    }

    public Boolean getShowSelector() {
        /* 471 */ return this.showSelector;
    }

    public void setShowSelector(Boolean showSelector) {
        /* 475 */ this.showSelector = showSelector;
    }

    public Double getMontant_a_regler() {
        /* 479 */ return this.montant_a_regler;
    }

    public void setMontant_a_regler(Double montant_a_regler) {
        /* 483 */ this.montant_a_regler = montant_a_regler;
    }
}
