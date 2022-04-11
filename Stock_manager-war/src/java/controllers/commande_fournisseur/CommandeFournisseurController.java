package controllers.commande_fournisseur;
 
 import controllers.commande_fournisseur.AbstractCommandeFournisseurController;
import entities.CommandeFournisseur;
import entities.Famille;
import entities.Fournisseur;
import entities.LigneCmdeFournisseur;
import entities.Lot;
import entities.Produit;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.context.RequestContext;
import utils.JsfUtil;
import utils.SessionMBean;
import utils.Utilitaires;

@ManagedBean
@SessionScoped
public class CommandeFournisseurController
        extends AbstractCommandeFournisseurController
        implements Serializable {

    @PostConstruct
    private void init() {
    }

    public void updateMois() {
        try {
            /*  46 */ this.anneeMois = this.anneeMoisFacadeLocal.find(this.anneeMois.getIdAnneeMois());
            /*  47 */        } catch (Exception e) {
            /*  48 */ e.printStackTrace();
        }
    }

    public void updateCalculTva() {
        /*  53 */ updateTotal();
    }

    public void prepareCreate() {
        try {
            /*  59 */ if (!Utilitaires.isAccess(Long.valueOf(55L))) {
                /*  60 */ notifyError("acces_refuse");
                return;
            }
            /*  63 */ RequestContext.getCurrentInstance().execute("PF('CommandeCreateDialog').show()");
            /*  64 */ this.mode = "Create";
            /*  65 */ this.produit = new Produit();
            /*  66 */ this.fournisseur = new Fournisseur();

            /*  68 */ this.commandeFournisseur = new CommandeFournisseur();
            /*  69 */ this.ligneCmdeFournisseurs.clear();

            /*  71 */ this.commandeFournisseur.setCode("-");
            /*  72 */ this.commandeFournisseur.setDateCmde(SessionMBean.getDateOuverture());
            /*  73 */ this.commandeFournisseur.setTauxTva(SessionMBean.getParametrage().getTauxTva());
            /*  74 */ this.commandeFournisseur.setTauxRemise(SessionMBean.getParametrage().getTauxRemise());
            /*  75 */ this.commandeFournisseur.setDateCmde(SessionMBean.getDateOuverture());
            /*  76 */ this.commandeFournisseur.setDateLivraisonPrev(SessionMBean.getDateOuverture());
            /*  77 */ this.commandeFournisseur.setMontantHt(Double.valueOf(0.0D));
            /*  78 */ this.commandeFournisseur.setMontantTtc(Double.valueOf(0.0D));
            /*  79 */ this.commandeFournisseur.setMontantRemise(Double.valueOf(0.0D));
            /*  80 */ this.commandeFournisseur.setMontantTva(Double.valueOf(0.0D));
            /*  81 */ this.commandeFournisseur.setLivre(Boolean.valueOf(false));
            /*  82 */ this.commandeFournisseur.setPaye(Boolean.valueOf(false));
            /*  83 */ this.commandeFournisseur.setCalculRemise(SessionMBean.getParametrage().getCalculRemise());
            /*  84 */ this.commandeFournisseur.setCalculTva(SessionMBean.getParametrage().getCalcultva());
            /*  85 */ this.produits = this.produitFacadeLocal.findAllRange(true);
            /*  86 */ this.total = Double.valueOf(0.0D);
            /*  87 */        } catch (Exception e) {
            /*  88 */ this.routine.catchException(e, this.routine.localizeMessage("echec_operation"));
            /*  89 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
        }
    }

    public void prepareCreateCommande() {
        /*  94 */ this.famille = new Famille();
        /*  95 */ this.produit = new Produit();
        /*  96 */ this.ligneCmdeFournisseur = new LigneCmdeFournisseur();
        /*  97 */ this.lot = new Lot();

        /*  99 */ this.cout_quantite = Double.valueOf(0.0D);
        /* 100 */ RequestContext.getCurrentInstance().execute("PF('ArticleCreateDialog').show()");
    }

    public void prepareEdit() {
        try {
            /* 107 */ if (this.commandeFournisseur == null) {
                /* 108 */ notifyError("not_row_selected");

                return;
            }
            /* 112 */ if (this.commandeFournisseur.getLivre().booleanValue()) {
                /* 113 */ notifyError("commande_deja_livree");

                return;
            }
            /* 117 */ if (!Utilitaires.isAccess(Long.valueOf(56L))) {
                /* 118 */ notifyError("acces_refuse");
                /* 119 */ this.commandeFournisseur = null;

                return;
            }
            /* 123 */ this.mode = "Edit";
            /* 124 */ this.ligneCmdeFournisseurs = this.ligneCmdeFournisseurFacadeLocal.findByCommande(this.commandeFournisseur.getIdCmdeFournisseur());
            /* 125 */ this.total = this.commandeFournisseur.getMontantTtc();

            /* 127 */ RequestContext.getCurrentInstance().execute("PF('CommandeCreateDialog').show()");
            /* 128 */        } catch (Exception e) {
            /* 129 */ notifyFail(e);
        }
    }

    public void prepareview() {
        try {
            /* 135 */ if (this.commandeFournisseur != null) {
                /* 136 */ this.ligneCmdeFournisseurs = this.ligneCmdeFournisseurFacadeLocal.findByCommande(this.commandeFournisseur.getIdCmdeFournisseur());
                /* 137 */ if (!this.ligneCmdeFournisseurs.isEmpty()) {
                    /* 138 */ RequestContext.getCurrentInstance().execute("PF('FactureDetailDialog').show()");
                    return;
                }
                /* 141 */ notifyError("liste_article_vide");
            } else {

                /* 144 */ notifyError("not_row_selected");
            }
            /* 146 */        } catch (Exception e) {
            /* 147 */ notifyFail(e);
        }
    }

    public void filterProduit() {
        try {
            /* 153 */ this.produits.clear();
            /* 154 */ this.produit = new Produit();
            /* 155 */ this.lot = new Lot();
            /* 156 */ if (this.famille.getIdfamille() != null) {
                /* 157 */ this.produits = this.produitFacadeLocal.findByFamille(this.famille, true);
            }
            /* 159 */        } catch (Exception e) {
            /* 160 */ e.printStackTrace();
        }
    }

    public void filterMois() {
        try {
            /* 166 */ this.anneeMoises.clear();
            /* 167 */ if (this.annee.getIdannee() != null) {
                /* 168 */ this.anneeMoises = this.anneeMoisFacadeLocal.findByAnneeEtat(this.annee, true);
            }
            /* 170 */        } catch (Exception e) {
            /* 171 */ e.printStackTrace();
        }
    }

    public void create() {
        try {
            /* 177 */ if ("Create".equals(this.mode)) {

                /* 179 */ if (!this.ligneCmdeFournisseurs.isEmpty()) {

                    /* 181 */ String message = "";

                    /* 183 */ this.commandeFournisseur.setIdCmdeFournisseur(this.commandeFournisseurFacadeLocal.nextLongVal());
                    /* 184 */ this.commandeFournisseur.setIdmois(this.anneeMois);
                    /* 185 */ this.commandeFournisseur.setIdfournisseur(this.fournisseur);
                    /* 186 */ this.commandeFournisseur.setPaye(Boolean.valueOf(false));

                    /* 188 */ this.ut.begin();

                    /* 190 */ this.commandeFournisseur.setCode("-");
                    /* 191 */ updateTotal();
                    /* 192 */ this.commandeFournisseurFacadeLocal.create(this.commandeFournisseur);

                    /* 194 */ for (LigneCmdeFournisseur lcf : this.ligneCmdeFournisseurs) {
                        /* 195 */ lcf.setIdLigneCmdeFournisseur(this.ligneCmdeFournisseurFacadeLocal.nextVal());
                        /* 196 */ lcf.setIdCmdeFournisseur(this.commandeFournisseur);
                        /* 197 */ this.ligneCmdeFournisseurFacadeLocal.create(lcf);
                    }

                    /* 200 */ Utilitaires.saveOperation(this.mouchardFacadeLocal, "Enregistrement de la commande fournisseur ; Montant : " + this.commandeFournisseur.getMontantTtc(), SessionMBean.getUserAccount());

                    /* 202 */ this.ut.commit();
                    /* 203 */ this.commandeFournisseur = null;
                    /* 204 */ this.ligneCmdeFournisseurs.clear();
                    /* 205 */ this.detail = this.supprimer = this.modifier = this.imprimer = Boolean.valueOf(true);
                    /* 206 */ JsfUtil.addSuccessMessage(message);

                    /* 208 */ notifySuccess();
                    /* 209 */ RequestContext.getCurrentInstance().execute("PF('CommandeCreateDialog').hide()");
                } else {
                    /* 211 */ notifyError("liste_article_vide");
                }

                /* 214 */            } else if (this.commandeFournisseur != null) {

                /* 216 */ this.ut.begin();

                /* 218 */ updateTotal();
                /* 219 */ this.commandeFournisseurFacadeLocal.edit(this.commandeFournisseur);

                /* 221 */ if (!this.ligneCmdeFournisseurs.isEmpty()) {
                    /* 222 */ for (LigneCmdeFournisseur lcf : this.ligneCmdeFournisseurs) {
                        /* 223 */ if (lcf.getIdLigneCmdeFournisseur().longValue() != 0L) {
                            /* 224 */ this.ligneCmdeFournisseurFacadeLocal.edit(lcf);
                            continue;
                        }
                        /* 226 */ lcf.setIdLigneCmdeFournisseur(this.ligneCmdeFournisseurFacadeLocal.nextVal());
                        /* 227 */ lcf.setIdCmdeFournisseur(this.commandeFournisseur);
                        /* 228 */ this.ligneCmdeFournisseurFacadeLocal.create(lcf);
                    }
                }

                /* 233 */ this.ut.commit();
                /* 234 */ this.commandeFournisseur = null;
                /* 235 */ this.ligneCmdeFournisseurs.clear();
                /* 236 */ this.detail = this.supprimer = this.modifier = this.imprimer = Boolean.valueOf(true);

                /* 238 */ notifySuccess();
                /* 239 */ RequestContext.getCurrentInstance().execute("PF('CommandeCreateDialog').hide()");
            } else {

                /* 242 */ notifyError("not_row_selected");
            }

            /* 245 */        } catch (Exception e) {
            /* 246 */ notifyFail(e);
        }
    }

    public void updateAvance() {
    }

    public void delete() {
        try {
            /* 256 */ if (this.commandeFournisseur != null) {

                /* 258 */ if (this.commandeFournisseur.getLivre().booleanValue()) {
                    /* 259 */ notifyError("commande_deja_livree");

                    return;
                }
                /* 263 */ if (!Utilitaires.isAccess(Long.valueOf(57L))) {
                    /* 264 */ notifyError("acces_refuse");
                    /* 265 */ this.detail = this.supprimer = this.modifier = this.imprimer = Boolean.valueOf(true);
                    /* 266 */ this.commandeFournisseur = null;

                    return;
                }
                /* 270 */ this.ut.begin();

                /* 272 */ List<LigneCmdeFournisseur> temp = this.ligneCmdeFournisseurFacadeLocal.findByCommande(this.commandeFournisseur.getIdCmdeFournisseur());
                /* 273 */ if (!temp.isEmpty()) {
                    /* 274 */ for (LigneCmdeFournisseur lcf : temp) {
                        /* 275 */ this.ligneCmdeFournisseurFacadeLocal.remove(lcf);
                    }
                }
                /* 278 */ this.commandeFournisseurFacadeLocal.remove(this.commandeFournisseur);
                /* 279 */ this.ut.commit();
                /* 280 */ Utilitaires.saveOperation(this.mouchardFacadeLocal, "Annulation de la commande fournisseur : " + this.commandeFournisseur.getCode() + " Montant : " + this.commandeFournisseur.getMontantTtc() + " Fournisseur : " + this.commandeFournisseur.getIdfournisseur().getRaisonSociale(), SessionMBean.getUserAccount());
                /* 281 */ this.commandeFournisseur = null;
                /* 282 */ this.supprimer = this.modifier = this.imprimer = Boolean.valueOf(true);
                /* 283 */ notifySuccess();
            } else {
                /* 285 */ notifyError("not_row_selected");
            }
            /* 287 */        } catch (Exception e) {
            /* 288 */ notifyFail(e);
        }
    }

    public void initPrinter(CommandeFournisseur c) {
        /* 293 */ this.commandeFournisseur = c;
        /* 294 */ print();
    }

    public void initEdit(CommandeFournisseur c) {
        /* 298 */ this.commandeFournisseur = c;
        /* 299 */ prepareEdit();
    }

    public void initView(CommandeFournisseur c) {
        /* 303 */ this.commandeFournisseur = c;
        /* 304 */ prepareview();
    }

    public void initDelete(CommandeFournisseur c) {
        /* 308 */ this.commandeFournisseur = c;
        /* 309 */ delete();
    }

    public void print() {
        try {
            /* 314 */ if (!Utilitaires.isAccess(Long.valueOf(58L))) {
                /* 315 */ notifyError("acces_refuse");
                /* 316 */ this.commandeFournisseur = null;

                return;
            }
            /* 320 */ if (this.commandeFournisseur != null) {

                /* 324 */ RequestContext.getCurrentInstance().execute("PF('FactureImprimerDialog').show()");
            } else {
                /* 326 */ notifyError("not_row_selected");
            }
            /* 328 */        } catch (Exception e) {
            /* 329 */ notifyFail(e);
        }
    }

    public void addProduit() {
        try {
            /* 335 */ if (this.lot == null) {
                /* 336 */ JsfUtil.addErrorMessage(this.routine.localizeMessage("article_invalide"));

                return;
            }
            /* 340 */ LigneCmdeFournisseur l = this.ligneCmdeFournisseur;
            /* 341 */ l.setIdLigneCmdeFournisseur(Long.valueOf(0L));

            /* 343 */ l.setIdlot(this.lot);
            /* 344 */ l.setIdproduit(this.produit);

            /* 346 */ boolean drapeau = false;
            /* 347 */ int i = 0;
            /* 348 */ for (LigneCmdeFournisseur lcf : this.ligneCmdeFournisseurs) {
                /* 349 */ if (lcf.getIdproduit().getIdproduit().equals(l.getIdproduit().getIdproduit())) {
                    /* 350 */ drapeau = true;
                    break;
                }
                /* 353 */ i++;
            }

            /* 356 */ if (!drapeau) {
                /* 357 */ this.ligneCmdeFournisseurs.add(l);
                /* 358 */ updateTotal();
                /* 359 */ RequestContext.getCurrentInstance().execute("PF('ArticleCreateDialog').hide()");
                /* 360 */ JsfUtil.addSuccessMessage(this.routine.localizeMessage("operation_reussie"));
                /* 361 */ this.ligneCmdeFournisseur = new LigneCmdeFournisseur();
                /* 362 */ this.produit = new Produit();
                return;
            }
            /* 365 */ JsfUtil.addErrorMessage("Article existant dans la commande");
        } /* 367 */ catch (Exception e) {
            /* 368 */ e.printStackTrace();
            /* 369 */ this.routine.feedBack("information", "/resources/tool_images/error.png", this.routine.localizeMessage("formulaire_incomplet"));
            /* 370 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
        }
    }

    public void removeProduit(int index) {
        try {
            /* 376 */ boolean trouve = false;
            /* 377 */ this.ut.begin();

            /* 379 */ LigneCmdeFournisseur lcf = this.ligneCmdeFournisseurs.get(index);
            /* 380 */ if (lcf.getIdLigneCmdeFournisseur().longValue() != 0L) {
                /* 381 */ trouve = true;
                /* 382 */ this.ligneCmdeFournisseurFacadeLocal.remove(lcf);
                /* 383 */ Utilitaires.saveOperation(this.mouchardFacadeLocal, "Suppression de l'article : " + lcf.getIdproduit().getNom() + " quantité : " + lcf.getQuantite() + " dans la commande : " + this.commandeFournisseur.getCode(), SessionMBean.getUserAccount());
            }
            /* 385 */ this.ligneCmdeFournisseurs.remove(index);

            /* 387 */ updateTotal();
            /* 388 */ if (trouve) {
                /* 389 */ this.commandeFournisseurFacadeLocal.edit(this.commandeFournisseur);
            }
            /* 391 */ this.ut.commit();
            /* 392 */ JsfUtil.addSuccessMessage("Supprimé");
            /* 393 */        } catch (Exception e) {
            /* 394 */ e.printStackTrace();
            /* 395 */ JsfUtil.addErrorMessage(this.routine.localizeMessage("echec_operation"));
        }
    }

    public Double calculTotal(List<LigneCmdeFournisseur> ligneCmdeFournisseurs) {
        /* 400 */ Double resultat = Double.valueOf(0.0D);
        /* 401 */ for (LigneCmdeFournisseur lcf : ligneCmdeFournisseurs) {
            /* 402 */ resultat = Double.valueOf(resultat.doubleValue() + lcf.getMontant().doubleValue() * lcf.getQuantite().doubleValue());
        }
        /* 404 */ return resultat;
    }

    public void updateTotal() {
        try {
            /* 409 */ this.total = calculTotal(this.ligneCmdeFournisseurs);
            /* 410 */ this.commandeFournisseur.setMontantHt(this.total);

            /* 412 */ if (this.commandeFournisseur.getCalculRemise().booleanValue()) {
                /* 413 */ this.commandeFournisseur.setMontantRemise(Double.valueOf(this.total.doubleValue() * this.commandeFournisseur.getTauxRemise().doubleValue() / 100.0D));
            } else {
                /* 415 */ this.commandeFournisseur.setMontantRemise(Double.valueOf(0.0D));
            }

            /* 418 */ if (this.commandeFournisseur.getCalculTva().booleanValue()) {
                /* 419 */ this.commandeFournisseur.setMontantTva(Double.valueOf((this.total.doubleValue() - this.commandeFournisseur.getMontantRemise().doubleValue()) * this.commandeFournisseur.getTauxTva().doubleValue() / 100.0D));
            } else {
                /* 421 */ this.commandeFournisseur.setMontantTva(Double.valueOf(0.0D));
            }
            /* 423 */ this.commandeFournisseur.setMontantTtc(Double.valueOf(this.total.doubleValue() - this.commandeFournisseur.getMontantRemise().doubleValue() + this.commandeFournisseur.getMontantTva().doubleValue()));
            /* 424 */        } catch (Exception e) {
            /* 425 */ e.printStackTrace();
        }
    }

    public void updateTotaux() {
        try {
            /* 431 */ this.cout_quantite = Double.valueOf(0.0D);
            /* 432 */ if (this.ligneCmdeFournisseur.getQuantite() != null
                    && /* 433 */ this.ligneCmdeFournisseur.getMontant() != null) {
                /* 434 */ this.cout_quantite = Double.valueOf(this.ligneCmdeFournisseur.getMontant().doubleValue() * this.ligneCmdeFournisseur.getQuantite().doubleValue());
            }
        } /* 437 */ catch (Exception e) {
            /* 438 */ e.printStackTrace();
            /* 439 */ this.cout_quantite = Double.valueOf(0.0D);
        }
    }

    public void updatedata() {
        try {
            /* 445 */ if (this.produit != null) {
                /* 446 */ this.lot = new Lot();
                /* 447 */ this.famille = this.produit.getIdfamille();

                /* 449 */ this.lots = this.lotFacadeLocal.findByArticle(this.produit.getIdproduit(), this.produit.getPerissable().booleanValue(), new Date(), true);

                /* 451 */ if (this.lots.size() == 0) {
                    /* 452 */ this.lot = null;

                    return;
                }
                /* 456 */ if (this.lots.size() == 1) {
                    /* 457 */ this.lot = this.lots.get(0);
                    /* 458 */ this.ligneCmdeFournisseur.setMontant(this.produit.getPrixachat());
                    return;
                }
                /* 461 */ this.lot = this.lots.get(0);
                /* 462 */ this.ligneCmdeFournisseur.setMontant(this.produit.getPrixachat());
            }
            /* 464 */        } catch (Exception e) {
            /* 465 */ e.printStackTrace();
        }
    }

    public void updatedataLot() {
        try {
            /* 471 */ if (this.lot != null) {
                /* 472 */ this.ligneCmdeFournisseur.setMontant(this.lot.getPrixAchat());
            }
            /* 474 */        } catch (Exception e) {
            /* 475 */ e.printStackTrace();
        }
    }

    public void notifyError(String message) {
        /* 480 */ this.routine.feedBack("avertissement", "/resources/tool_images/warning.jpeg", this.routine.localizeMessage(message));
        /* 481 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
    }

    public void notifySuccess() {
        /* 485 */ RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').hide()");
        /* 486 */ this.routine.feedBack("information", "/resources/tool_images/success.png", this.routine.localizeMessage("operation_reussie"));
        /* 487 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
    }

    public void notifyFail(Exception e) {
        /* 491 */ RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').hide()");
        /* 492 */ this.routine.catchException(e, this.routine.localizeMessage("echec_operation"));
        /* 493 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
    }
}


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-war.war!\WEB-INF\classes\controllers\commande_fournisseur\CommandeFournisseurController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
