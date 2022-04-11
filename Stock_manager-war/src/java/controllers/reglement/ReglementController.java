package controllers.reglement;

import controllers.reglement.AbstractReglementController;
import entities.Client;
import entities.Commande;
import entities.Facture;
import entities.Famille;
import entities.Privilege;
import entities.Produit;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import utils.JsfUtil;
import utils.PrintUtils;
import utils.SessionMBean;
import utils.Utilitaires;

@ManagedBean
@SessionScoped
public class ReglementController
        extends AbstractReglementController {

    @PostConstruct
    private void init() {
    }

    public void prepareCreate() {
        /*  49 */ this.mode = "Create";
        /*  50 */ this.produit = new Produit();
        /*  51 */ this.facture = new Facture();
        /*  52 */ this.facture.setCredit(Boolean.valueOf(false));
        /*  53 */ this.facture.setMontantPaye(Double.valueOf(0.0D));
        /*  54 */ this.facture.setReste(Double.valueOf(0.0D));
        /*  55 */ this.commandes.clear();
        /*  56 */ this.payement_credit = false;
        /*  57 */ this.commande = new Commande();

        /*  59 */ this.facture.setDateAchat(new Date());

        /*  61 */ this.showSelector = Boolean.valueOf(true);
        /*  62 */ this.selectClient = Boolean.valueOf(false);
        /*  63 */ this.saisieClient = Boolean.valueOf(true);

        /*  65 */ this.client = new Client();
        /*  66 */ this.total = Double.valueOf(0.0D);

        try {
            /*  69 */ Privilege p = this.privilegeFacadeLocal.findByUser(SessionMBean.getUserAccount().getIdutilisateur().intValue(), 1);
            /*  70 */ if (p != null) {
                /*  71 */ this.showUserCreateDialog = Boolean.valueOf(true);
            } else {
                /*  73 */ p = this.privilegeFacadeLocal.findByUser(SessionMBean.getUserAccount().getIdutilisateur().intValue(), 23);
                /*  74 */ if (p != null) {
                    /*  75 */ this.showUserCreateDialog = Boolean.valueOf(true);
                } else {
                    /*  77 */ this.showUserCreateDialog = Boolean.valueOf(false);
                    /*  78 */ JsfUtil.addErrorMessage("Vous n'avez pas le privilège d'enregistrer une facture");
                }
            }
            /*  81 */        } catch (Exception e) {
            /*  82 */ e.printStackTrace();
        }
    }

    public void prepareCreateCommande() {
        /*  87 */ this.famille = new Famille();
        /*  88 */ this.produit = new Produit();
        /*  89 */ this.commande = new Commande();
        /*  90 */ this.cout_quantite = Double.valueOf(0.0D);
        /*  91 */ this.produits = this.produitFacadeLocal.findAllRange();
    }

    public void prepareDetail() {
    }

    public void prepareEdit() {
        /*  99 */ this.mode = "Edit";

        /* 101 */ this.showSelector = Boolean.valueOf(false);
        /* 102 */ this.selectClient = Boolean.valueOf(true);
        /* 103 */ this.saisieClient = Boolean.valueOf(false);
        /* 104 */ this.montant_a_regler = Double.valueOf(0.0D);

        try {
            /* 108 */ Privilege p = this.privilegeFacadeLocal.findByUser(SessionMBean.getUserAccount().getIdutilisateur().intValue(), 1);
            /* 109 */ if (p != null) {
                /* 110 */ this.showUserCreateDialog = Boolean.valueOf(true);
            } else {

                /* 113 */ p = this.privilegeFacadeLocal.findByUser(SessionMBean.getUserAccount().getIdutilisateur().intValue(), 24);
                /* 114 */ if (p != null) {
                    /* 115 */ this.showUserCreateDialog = Boolean.valueOf(true);
                } else {

                    /* 118 */ this.showUserCreateDialog = Boolean.valueOf(false);
                    /* 119 */ JsfUtil.addErrorMessage("Vous n 'avez pas le privilege de modifier cette facture");

                    return;
                }
            }
            /* 124 */ if (this.facture != null);

        } /* 128 */ catch (Exception e) {
            /* 129 */ e.printStackTrace();
        }
    }

    public void prepareview() {
        try {
            /* 135 */ if (this.facture != null) {

                /* 137 */ this.showSelector = Boolean.valueOf(false);
                /* 138 */ this.selectClient = Boolean.valueOf(true);
                /* 139 */ this.saisieClient = Boolean.valueOf(false);

                /* 141 */ this.commandes = this.commandeFacadeLocal.findByFacture(this.facture);
                /* 142 */ if (this.commandes.isEmpty()) {
                    /* 143 */ this.showUserDetailDialog = Boolean.valueOf(true);
                } else {
                    /* 145 */ this.showUserDetailDialog = Boolean.valueOf(false);
                    /* 146 */ JsfUtil.addErrorMessage("Cette facture ne contient aucun article");
                }
            } else {
                /* 149 */ JsfUtil.addErrorMessage("Aucune facture selectionnée");
            }
            /* 151 */        } catch (Exception e) {
            /* 152 */ e.printStackTrace();
        }
    }

    public void filterProduit() {
        try {
            /* 158 */ this.produits.clear();
            /* 159 */ if (this.famille.getIdfamille() != null) {
                /* 160 */ this.produits = this.produitFacadeLocal.findByFamille(this.famille);
            }
            /* 162 */        } catch (Exception e) {
            /* 163 */ e.printStackTrace();
        }
    }

    public void filterMois() {
        try {
            /* 169 */ this.anneeMoises.clear();
            /* 170 */ if (this.annee.getIdannee() != null) {
                /* 171 */ this.anneeMoises = this.anneeMoisFacadeLocal.findByAnneeEtat(this.annee, true);
            }
            /* 173 */        } catch (Exception e) {
            /* 174 */ e.printStackTrace();
        }
    }

    public void updateClient() {
        try {
            /* 180 */ if (this.nouveauClient.booleanValue()) {
                /* 181 */ this.saisieClient = Boolean.valueOf(true);
                /* 182 */ this.selectClient = Boolean.valueOf(false);
            } else {
                /* 184 */ this.saisieClient = Boolean.valueOf(false);
                /* 185 */ this.selectClient = Boolean.valueOf(true);
            }
            /* 187 */        } catch (Exception e) {
            /* 188 */ e.printStackTrace();
        }
    }

    public void create() {
        try {
            /* 194 */ if (!"Create".equals(this.mode)) {

                /* 197 */ if (this.facture != null) {

                    /* 199 */ this.ut.begin();

                    /* 201 */ if (this.facture.getMontant().doubleValue() >= this.facture.getMontantPaye().doubleValue() + this.montant_a_regler.doubleValue()) {
                        /* 202 */ this.facture.setMontantPaye(Double.valueOf(this.facture.getMontantPaye().doubleValue() + this.montant_a_regler.doubleValue()));
                        /* 203 */ this.facture.setPaye(Boolean.valueOf(true));
                        /* 204 */ this.facture.setReste(Double.valueOf(0.0D));
                    } else {
                        /* 206 */ this.facture.setMontantPaye(Double.valueOf(this.facture.getMontantPaye().doubleValue() + this.montant_a_regler.doubleValue()));
                        /* 207 */ this.facture.setPaye(Boolean.valueOf(false));
                        /* 208 */ this.facture.setReste(Double.valueOf(this.facture.getMontant().doubleValue() - this.facture.getMontantPaye().doubleValue() + this.montant_a_regler.doubleValue()));
                    }

                    /* 211 */ this.factureFacadeLocal.edit(this.facture);

                    /* 213 */ Utilitaires.saveOperation(this.mouchardFacadeLocal, "Règlement de la facture -> " + this.facture.getNumeroFacture() + " ; Montant -> " + this.montant_a_regler + " ; client -> " + this.facture.getIdclient().getNom() + " " + this.facture.getIdclient().getPrenom(), SessionMBean.getUserAccount());

                    /* 215 */ this.ut.commit();
                    /* 216 */ JsfUtil.addSuccessMessage("Opération réussie");

                    /* 218 */ filterMois();
                } else {

                    /* 221 */ JsfUtil.addErrorMessage("Aucun facture selectionnée");
                }
            }
            /* 224 */ this.showUserCreateDialog = Boolean.valueOf(false);
            /* 225 */        } catch (Exception e) {
            /* 226 */ e.printStackTrace();
        }
    }

    public void updateAvance() {
        try {
            /* 232 */ if (this.facture.getCredit().booleanValue()) {
                /* 233 */ this.payement_credit = true;
            } else {
                /* 235 */ this.payement_credit = false;
            }
            /* 237 */        } catch (Exception e) {
            /* 238 */ e.printStackTrace();
        }
    }

    public void edit() {
    }

    public void delete() {
        try {
            /* 248 */ if (this.facture != null) {

                /* 250 */ Privilege p = this.privilegeFacadeLocal.findByUser(SessionMBean.getUserAccount().getIdutilisateur().intValue(), 1);
                /* 251 */ if (p != null) {
                    /* 252 */ this.showUserDeleteDialog = Boolean.valueOf(true);
                } else {
                    /* 254 */ p = this.privilegeFacadeLocal.findByUser(SessionMBean.getUserAccount().getIdutilisateur().intValue(), 25);
                    /* 255 */ if (p != null) {
                        /* 256 */ this.showUserDeleteDialog = Boolean.valueOf(true);
                    } else {
                        /* 258 */ this.showUserDeleteDialog = Boolean.valueOf(false);
                        /* 259 */ JsfUtil.addErrorMessage("Vous n 'avez pas le privilege de supprimer cette commande");
                        return;
                    }
                }
                /* 263 */ this.ut.begin();

                /* 265 */ List<Commande> temp = this.commandeFacadeLocal.findByFacture(this.facture);
                /* 266 */ if (!temp.isEmpty()) {
                    /* 267 */ for (Commande c : temp) {
                        /* 268 */ c.getIdproduit().setQuantite(Double.valueOf(c.getIdproduit().getQuantite().doubleValue() + c.getQuantite().doubleValue()));
                        /* 269 */ this.produitFacadeLocal.edit(c.getIdproduit());
                        /* 270 */ this.commandeFacadeLocal.remove(c);
                    }
                }
                /* 273 */ this.factureFacadeLocal.remove(this.facture);
                /* 274 */ this.anneeMois = this.facture.getIdAnneeMois();
                /* 275 */ filterMois();

                /* 277 */ Utilitaires.saveOperation(this.mouchardFacadeLocal, "Suppression de la facture -> " + this.facture.getNumeroFacture() + " Montant : " + this.facture.getMontant() + " Client : " + this.facture.getIdclient().getNom() + " " + this.facture.getIdclient().getPrenom(), SessionMBean.getUserAccount());
                /* 278 */ this.ut.commit();

                /* 280 */ JsfUtil.addSuccessMessage("Operation réussie");
            } else {
                /* 282 */ JsfUtil.addErrorMessage("Aucune commande selectionnée");
            }
            /* 284 */        } catch (Exception e) {
            /* 285 */ e.printStackTrace();
        }
    }

    public void print() {
        try {
            /* 291 */ Privilege p = this.privilegeFacadeLocal.findByUser(SessionMBean.getUserAccount().getIdutilisateur().intValue(), 1);
            /* 292 */ if (p != null) {
                /* 293 */ this.showUserPrintDialog = Boolean.valueOf(true);
            } else {

                /* 296 */ p = this.privilegeFacadeLocal.findByUser(SessionMBean.getUserAccount().getIdutilisateur().intValue(), 16);
                /* 297 */ if (p != null) {
                    /* 298 */ this.showUserPrintDialog = Boolean.valueOf(true);
                } else {
                    /* 300 */ this.showUserPrintDialog = Boolean.valueOf(false);
                    /* 301 */ JsfUtil.addErrorMessage("Vous n 'avez pas le privilège d'imprimer la facture");

                    return;
                }
            }
            /* 306 */ if (this.facture != null) {
                /* 307 */ this.commandes = this.commandeFacadeLocal.findByFacture(this.facture);
                /* 308 */ this.facture.setCommandeList(this.commandes);
                /* 309 */ this.fileName = PrintUtils.printFacture(this.facture, SessionMBean.getParametrage());
            } else {
                /* 311 */ JsfUtil.addWarningMessage("Veuillez selectionner une commande");
            }

            /* 314 */        } catch (Exception e) {
            /* 315 */ e.printStackTrace();
        }
    }

    public void addProduit() {
        try {
            /* 322 */ Commande c = this.commande;
            /* 323 */ c.setIdproduit(this.produit);

            /* 325 */ boolean drapeau = false;
            /* 326 */ for (Commande c1 : this.commandes) {
                /* 327 */ if (c1.getIdproduit().getIdproduit().equals(c.getIdproduit().getIdproduit())) {
                    /* 328 */ drapeau = true;

                    break;
                }
            }
            /* 333 */ if (!drapeau) {
                /* 334 */ this.commandes.add(c);
                /* 335 */ JsfUtil.addSuccessMessage("Produit ajouté avec succès");
            } else {
                /* 337 */ JsfUtil.addErrorMessage("Ce produit est deja dans la liste");
            }

            /* 340 */ this.total = calculTotal(this.commandes);

            /* 342 */ this.commande = new Commande();
            /* 343 */ this.produit = new Produit();
        } /* 345 */ catch (Exception e) {
            /* 346 */ e.printStackTrace();
        }
    }

    public void removeProduit(Commande commande) {
        try {
            /* 352 */ int i = 0;
            /* 353 */ for (Commande c : this.commandes) {
                /* 354 */ if (c.getIdproduit().equals(commande.getIdproduit())) {

                    /* 356 */ if (c.getIdcommande() != null) {

                        /* 358 */ this.commandeFacadeLocal.remove(commande);
                        /* 359 */ this.facture.setMontant(Double.valueOf(this.facture.getMontant().doubleValue() - commande.getMontant().doubleValue() * commande.getQuantite().doubleValue()));
                        /* 360 */ this.factureFacadeLocal.edit(this.facture);

                        /* 362 */ Produit pro = commande.getIdproduit();
                        /* 363 */ pro.setQuantite(Double.valueOf(pro.getQuantite().doubleValue() + commande.getQuantite().doubleValue()));
                        /* 364 */ this.produitFacadeLocal.edit(pro);

                        /* 366 */ Utilitaires.saveOperation(this.mouchardFacadeLocal, "Suppression du produit -> " + pro.getNom() + " Dans la facture -> " + this.facture.getNumeroFacture(), SessionMBean.getUserAccount());
                    }

                    /* 369 */ this.commandes.remove(i);
                    break;
                }
                /* 372 */ i++;
            }
            /* 374 */ this.total = calculTotal(this.commandes);
            /* 375 */ JsfUtil.addSuccessMessage("Supprimé");
            /* 376 */        } catch (Exception e) {
            /* 377 */ e.printStackTrace();
        }
    }

    public void findByMois() {
        try {
            /* 383 */ if (this.anneeMois.getIdAnneeMois() != null);

        } /* 386 */ catch (Exception e) {
            /* 387 */ e.printStackTrace();
        }
    }

    public Double calculTotal(List<Commande> commandes) {
        /* 392 */ Double resultat = Double.valueOf(0.0D);
        /* 393 */ if (!commandes.isEmpty()) {
            /* 394 */ for (Commande c : commandes) {
                /* 395 */ resultat = Double.valueOf(resultat.doubleValue() + c.getMontant().doubleValue() * c.getQuantite().doubleValue());
            }
        }
        /* 398 */ return resultat;
    }

    public void updateTotal() {
        try {
            /* 403 */ this.total = calculTotal(this.commandes);
            /* 404 */        } catch (Exception e) {
            /* 405 */ e.printStackTrace();
        }
    }

    public void updateTotaux() {
        try {
            /* 411 */ this.cout_quantite = Double.valueOf(0.0D);
            /* 412 */ if (this.commande.getQuantite() != null
                    && /* 413 */ this.commande.getQuantite() != null) {
                /* 414 */ this.cout_quantite = Double.valueOf(this.commande.getMontant().doubleValue() * this.commande.getQuantite().doubleValue());

            }
        } /* 418 */ catch (Exception e) {
            /* 419 */ e.printStackTrace();
        }
    }

    public void updatedata() {
        try {
            /* 425 */ if (this.produit != null) {
                /* 426 */ this.commande.setMontant(this.produit.getPrixMaximal());
                /* 427 */ this.famille = this.produit.getIdfamille();
            }
            /* 429 */        } catch (Exception e) {
            /* 430 */ e.printStackTrace();
        }
    }

    public void closePanel() {
        /* 435 */ this.showUserCreateDialog = Boolean.valueOf(false);
    }
}
