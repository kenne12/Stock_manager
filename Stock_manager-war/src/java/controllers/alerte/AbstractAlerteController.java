package controllers.alerte;

import entities.CommandeClient;
import entities.CommandeFournisseur;
import entities.LigneCmdeFournisseur;
import entities.LigneCommandeClient;
import entities.Lot;
import entities.Produit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.transaction.UserTransaction;
import sessions.CommandeClientFacadeLocal;
import sessions.CommandeFournisseurFacadeLocal;
import sessions.LigneCmdeFournisseurFacadeLocal;
import sessions.LigneCommandeClientFacadeLocal;
import sessions.LotFacadeLocal;
import sessions.MouchardFacadeLocal;
import sessions.ProduitFacadeLocal;
import utils.Routine;
import utils.SessionMBean;
import utils.Utilitaires;

public class AbstractAlerteController {

    @Resource
    protected UserTransaction ut;
    @EJB
    protected CommandeClientFacadeLocal commandeClientFacadeLocal;
    /*  42 */    protected CommandeClient commandeClient = new CommandeClient();
    /*  43 */    protected List<CommandeClient> commandeClients = new ArrayList<>();

    @EJB
    protected CommandeFournisseurFacadeLocal commandeFournisseurFacadeLocal;
    /*  47 */    protected CommandeFournisseur commandeFournisseur = new CommandeFournisseur();
    /*  48 */    protected List<CommandeFournisseur> commandeFournisseurs = new ArrayList<>();

    @EJB
    protected LigneCommandeClientFacadeLocal ligneCommandeClientFacadeLocal;
    /*  52 */    protected LigneCommandeClient ligneCommandeClient = new LigneCommandeClient();
    /*  53 */    protected List<LigneCommandeClient> ligneCommandeClients = new ArrayList<>();

    @EJB
    protected LigneCmdeFournisseurFacadeLocal ligneCmdeFournisseurFacadeLocal;
    /*  57 */    protected LigneCmdeFournisseur ligneCmdeFournisseur = new LigneCmdeFournisseur();
    /*  58 */    protected List<LigneCmdeFournisseur> ligneCmdeFournisseurs = new ArrayList<>();

    @EJB
    protected ProduitFacadeLocal produitFacadeLocal;
    /*  62 */    protected List<Produit> produits = new ArrayList<>();

    @EJB
    protected LotFacadeLocal lotFacadeLocal;
    /*  66 */    protected List<Lot> lots = new ArrayList<>();

    @EJB
    protected MouchardFacadeLocal mouchardFacadeLocal;

    /*  71 */    protected Routine routine = new Routine();

    /*  73 */    protected Boolean showSelectorCommand = Boolean.valueOf(true);

    /*  75 */    protected Boolean detail = Boolean.valueOf(true);
    /*  76 */    protected Boolean modifier = Boolean.valueOf(true);
    /*  77 */    protected Boolean consulter = Boolean.valueOf(true);
    /*  78 */    protected Boolean imprimer = Boolean.valueOf(true);
    /*  79 */    protected Boolean supprimer = Boolean.valueOf(true);

    /*  81 */    protected String fileName = "";

    /*  83 */    protected String mode = "";

    public Boolean getDetail() {
        /*  86 */ return this.detail;
    }

    public Boolean getModifier() {
        /*  90 */ return this.modifier;
    }

    public Boolean getConsulter() {
        /*  94 */ return this.consulter;
    }

    public Boolean getImprimer() {
        /*  98 */ return this.imprimer;
    }

    public Boolean getSupprimer() {
        /* 102 */ return this.supprimer;
    }

    public String getFileName() {
        /* 106 */ return this.fileName;
    }

    public Boolean getShowSelectorCommand() {
        /* 110 */ return this.showSelectorCommand;
    }

    public Routine getRoutine() {
        /* 114 */ return this.routine;
    }

    public CommandeClient getCommandeClient() {
        /* 118 */ return this.commandeClient;
    }

    public void setCommandeClient(CommandeClient commandeClient) {
        /* 122 */ this.commandeClient = commandeClient;
    }

    public List<CommandeClient> getCommandeClients() {
        try {
            /* 127 */ this.commandeClients = this.commandeClientFacadeLocal.findByLivre(false);
            /* 128 */        } catch (Exception e) {
            /* 129 */ e.printStackTrace();
        }
        /* 131 */ return this.commandeClients;
    }

    public LigneCommandeClient getLigneCommandeClient() {
        /* 135 */ return this.ligneCommandeClient;
    }

    public void setLigneCommandeClient(LigneCommandeClient ligneCommandeClient) {
        /* 139 */ this.ligneCommandeClient = ligneCommandeClient;
    }

    public List<LigneCommandeClient> getLigneCommandeClients() {
        /* 143 */ return this.ligneCommandeClients;
    }

    public List<CommandeFournisseur> getCommandeFournisseurs() {
        try {
            /* 148 */ this.commandeFournisseurs = this.commandeFournisseurFacadeLocal.findByLivre(false);
            /* 149 */        } catch (Exception e) {
            /* 150 */ e.printStackTrace();
        }
        /* 152 */ return this.commandeFournisseurs;
    }

    public void setCommandeFournisseurs(List<CommandeFournisseur> commandeFournisseurs) {
        /* 156 */ this.commandeFournisseurs = commandeFournisseurs;
    }

    public List<Produit> getProduits() {
        try {
            /* 162 */ this.produits = this.produitFacadeLocal.findSousStock();
            /* 163 */        } catch (Exception e) {
            /* 164 */ e.printStackTrace();
        }
        /* 166 */ return this.produits;
    }

    public void setProduits(List<Produit> produits) {
        /* 170 */ this.produits = produits;
    }

    public List<Lot> getLots() {
        try {
            /* 175 */ this.lots.clear();
            /* 176 */ List<Lot> lotTemp = this.lotFacadeLocal.findAllRange();
            /* 177 */ for (Lot l : lotTemp) {
                /* 178 */ Integer ecart = Integer.valueOf(0);
                /* 179 */ boolean echec = false;
                try {
                    /* 181 */ if ((new Date()).after(l.getDatePeremption())) {
                        /* 182 */ ecart = Integer.valueOf(0);
                        /* 183 */ echec = true;
                    } else {
                        /* 185 */ ecart = Utilitaires.duration(new Date(), l.getDatePeremption());
                    }
                    /* 187 */                } catch (Exception e) {
                    /* 188 */ echec = true;
                    /* 189 */ ecart = Integer.valueOf(0);
                }

                /* 192 */ if (!echec
                        && /* 193 */ ecart.intValue() > 0
                        && /* 194 */ ecart.intValue() <= SessionMBean.getParametrage().getNbreJrAlertePeremption().intValue()) {
                    /* 195 */ this.lots.add(l);
                }
            }

        } /* 200 */ catch (Exception e) {
            /* 201 */ e.printStackTrace();
        }
        /* 203 */ return this.lots;
    }

    public void setLots(List<Lot> lots) {
        /* 207 */ this.lots = lots;
    }
}


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-war.war!\WEB-INF\classes\controllers\alerte\AbstractAlerteController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
