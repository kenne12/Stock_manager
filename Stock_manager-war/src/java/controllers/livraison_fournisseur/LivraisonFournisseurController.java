package controllers.livraison_fournisseur;

import controllers.livraison_fournisseur.AbstractLivraisonFournisseurController;
import entities.CommandeFournisseur;
import entities.Fournisseur;
import entities.LigneCmdeFournisseur;
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
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.context.RequestContext;
import utils.JsfUtil;
import utils.SessionMBean;
import utils.Utilitaires;

@ManagedBean
@SessionScoped
public class LivraisonFournisseurController
        extends AbstractLivraisonFournisseurController
        implements Serializable {

    @PostConstruct
    private void init() {
    }

    public void updateMois() {
        try {
            /*  50 */ this.anneeMois = this.anneeMoisFacadeLocal.find(this.anneeMois.getIdAnneeMois());
            /*  51 */        } catch (Exception e) {
            /*  52 */ e.printStackTrace();
        }
    }

    public void updateCalculTva() {
        /*  57 */ updateTotal();
    }

    public List<Lot> findByProduit(Produit p) {
        try {
            /*  62 */ return this.lotFacadeLocal.findByArticle(p.getIdproduit(), p.getPerissable().booleanValue());
            /*  63 */        } catch (Exception e) {
            /*  64 */ return new ArrayList<>();
        }
    }

    public void prepareCreate() {
        try {
            /*  71 */ if (!Utilitaires.isAccess(Long.valueOf(52L))) {
                /*  72 */ notifyError("acces_refuse");
                return;
            }
            /*  75 */ RequestContext.getCurrentInstance().execute("PF('CommandeCreateDialog').show()");
            /*  76 */ this.mode = "Create";

            /*  78 */ this.stock = new Stock();

            /*  80 */ this.stock.setCalculTva(Boolean.valueOf(false));
            /*  81 */ this.stock.setCalculRemise(Boolean.valueOf(false));

            /*  83 */ this.stock.setMontantRemise(Double.valueOf(0.0D));
            /*  84 */ this.stock.setMontantTtc(Double.valueOf(0.0D));
            /*  85 */ this.stock.setMontantTva(Double.valueOf(0.0D));
            /*  86 */ this.stock.setTauxRemise(Double.valueOf(0.0D));
            /*  87 */ this.stock.setTauxTva(Double.valueOf(0.0D));

            /*  89 */ this.stockProduits.clear();

            /*  91 */ this.commandeFournisseur = new CommandeFournisseur();
            /*  92 */ this.stockProduits.clear();
            /*  93 */ this.showSelectorCommand = Boolean.valueOf(false);

            /*  95 */ this.total = Double.valueOf(0.0D);
            /*  96 */        } catch (Exception e) {
            /*  97 */ this.routine.catchException(e, this.routine.localizeMessage("echec_operation"));
            /*  98 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
        }
    }

    public void prepareCreateCommande() {
        try {
            /* 104 */ this.commandeFournisseur = new CommandeFournisseur();
            /* 105 */ this.fournisseur = new Fournisseur();
            /* 106 */ this.commandeFournisseurs = this.commandeFournisseurFacadeLocal.findByLivre(false);
            /* 107 */ RequestContext.getCurrentInstance().execute("PF('ArticleCreateDialog').show()");
            /* 108 */        } catch (Exception e) {
            /* 109 */ notifyFail(e);
        }
    }

    public void prepareEdit() {
        try {
            /* 116 */ if (this.stock == null) {
                /* 117 */ notifyError("not_row_selected");

                return;
            }
            /* 121 */ if (this.stock.getLivraisonDirecte().booleanValue()) {
                /* 122 */ notifyError("livraison_effectuee_par_commande");

                return;
            }
            /* 126 */ if (Utilitaires.isDayClosed()) {
                /* 127 */ notifyError("journee_cloturee");

                return;
            }
            /* 131 */ this.showSelectorCommand = Boolean.valueOf(true);
            /* 132 */ this.commandeFournisseur = this.commandeFournisseurFacadeLocal.find(Long.valueOf(this.stock.getIdCmdeFournisseur().longValue()));
            /* 133 */ if (this.commandeFournisseur.getLivre().booleanValue()) {
                /* 134 */ notifyError("commande_deja_livree");

                return;
            }
            /* 138 */ if (!Utilitaires.isAccess(Long.valueOf(49L))) {
                /* 139 */ notifyError("acces_refuse");
                /* 140 */ this.commandeFournisseur = null;

                return;
            }
            /* 144 */ this.mode = "Edit";

            /* 146 */ this.ligneCmdeFournisseurs = this.ligneCmdeFournisseurFacadeLocal.findByCommande(this.commandeFournisseur.getIdCmdeFournisseur());
            /* 147 */ this.stockProduits = this.stockProduitFacadeLocal.findByStock(this.stock);
            /* 148 */ this.fournisseur = this.stock.getIdfournisseur();
            /* 149 */ this.total = this.stock.getMontantTtc();
            /* 150 */ RequestContext.getCurrentInstance().execute("PF('CommandeCreateDialog').show()");
        } /* 152 */ catch (Exception e) {
            /* 153 */ notifyFail(e);
        }
    }

    public void prepareview() {
        try {
            /* 159 */ if (this.stock != null) {
                /* 160 */ this.stockProduits = this.stockProduitFacadeLocal.findByStock(this.stock);
                /* 161 */ if (!this.stockProduits.isEmpty()) {
                    /* 162 */ RequestContext.getCurrentInstance().execute("PF('FactureDetailDialog').show()");
                    return;
                }
                /* 165 */ notifyError("liste_article_vide");
            } else {
                /* 167 */ notifyError("not_row_selected");
            }
            /* 169 */        } catch (Exception e) {
            /* 170 */ notifyFail(e);
        }
    }

    public void selectCommande() {
        try {
            /* 176 */ if (this.commandeFournisseur != null) {

                /* 178 */ this.stockProduits.clear();

                /* 180 */ this.fournisseur = this.commandeFournisseur.getIdfournisseur();
                /* 181 */ this.ligneCmdeFournisseurs = this.ligneCmdeFournisseurFacadeLocal.findByCommande(this.commandeFournisseur.getIdCmdeFournisseur());
                /* 182 */ this.commandeFournisseurs.clear();
                /* 183 */ this.stock.setTauxRemise(this.commandeFournisseur.getTauxRemise());
                /* 184 */ this.stock.setTauxTva(this.commandeFournisseur.getTauxTva());

                /* 186 */ this.stock.setCalculRemise(this.commandeFournisseur.getCalculRemise());
                /* 187 */ this.stock.setCalculTva(this.commandeFournisseur.getCalculTva());
                /* 188 */ this.commandeFournisseur.setDateLivraisonEffective(SessionMBean.getDateOuverture());
                /* 189 */ int conteur = 0;
                /* 190 */ for (LigneCmdeFournisseur lcf : this.ligneCmdeFournisseurs) {
                    /* 191 */ StockProduit sp = new StockProduit();
                    /* 192 */ sp.setIdStockProduit(Long.valueOf(0L));
                    /* 193 */ sp.setIdlot(lcf.getIdlot());
                    /* 194 */ sp.setIdproduit(lcf.getIdproduit());
                    /* 195 */ sp.setIdstock(this.stock);
                    /* 196 */ sp.setQuantite(lcf.getQuantite());
                    /* 197 */ sp.setPrixAcquisition(lcf.getMontant());
                    /* 198 */ sp.setBenefice(Double.valueOf((lcf.getIdproduit().getPrixMaximal().doubleValue() - lcf.getMontant().doubleValue()) * lcf.getQuantite().doubleValue()));
                    /* 199 */ this.stockProduits.add(sp);
                    /* 200 */ ((LigneCmdeFournisseur) this.ligneCmdeFournisseurs.get(conteur)).setTauxSatisfaction(Double.valueOf(0.0D));
                    /* 201 */ conteur++;
                }
                /* 203 */ this.commandeFournisseur.setTauxSatisfaction(Double.valueOf(0.0D));
                /* 204 */ updateTotal();
                /* 205 */ RequestContext.getCurrentInstance().execute("PF('ArticleCreateDialog').hide()");
            }
            /* 207 */        } catch (Exception e) {
            /* 208 */ e.printStackTrace();
        }
    }

    public void filterMois() {
        try {
            /* 214 */ this.anneeMoises.clear();
            /* 215 */ if (this.annee.getIdannee() != null) {
                /* 216 */ this.anneeMoises = this.anneeMoisFacadeLocal.findByAnneeEtat(this.annee, true);
            }
            /* 218 */        } catch (Exception e) {
            /* 219 */ e.printStackTrace();
        }
    }

    public void create() {
        try {
            /* 225 */ if ("Create".equals(this.mode)) {

                /* 227 */ if (!this.ligneCmdeFournisseurs.isEmpty()) {

                    /* 229 */ String message = "";

                    /* 231 */ String code = "S-" + this.anneeMois.getIdannee().getNom() + "-" + this.anneeMois.getIdmois().getNom().toUpperCase().substring(0, 2);
                    /* 232 */ Long nextPayement = this.stockFacadeLocal.nextVal(this.anneeMois);
                    /* 233 */ code = Utilitaires.genererCodeStock(code, nextPayement);
                    /* 234 */ this.stock.setCode(code);

                    /* 236 */ this.stock.setIdstock(this.stockFacadeLocal.nextLongVal());
                    /* 237 */ this.mvtStock.setIdMvtStock(this.mvtStockFacadeLocal.nextLongVal());

                    /* 239 */ this.stock.setIdfournisseur(this.fournisseur);
                    /* 240 */ this.stock.setIdAnneeMois(this.anneeMois);
                    /* 241 */ this.stock.setMontant(this.total);
                    /* 242 */ this.stock.setLivraisonDirecte(Boolean.valueOf(false));
                    /* 243 */ this.stock.setDateAchat(this.commandeFournisseur.getDateLivraisonEffective());
                    /* 244 */ this.stock.setBord(Double.valueOf(0.0D));
                    /* 245 */ this.stock.setIdCmdeFournisseur(BigInteger.valueOf(this.commandeFournisseur.getIdCmdeFournisseur().longValue()));
                    /* 246 */ this.stock.setIdMvtStock(BigInteger.valueOf(this.mvtStock.getIdMvtStock().longValue()));

                    /* 248 */ updateTotal();
                    /* 249 */ this.ut.begin();

                    /* 251 */ this.mvtStock.setIdmois(this.stock.getIdAnneeMois());
                    /* 252 */ this.mvtStock.setCode(code);
                    /* 253 */ this.mvtStock.setDateMvt(this.commandeFournisseur.getDateLivraisonEffective());
                    /* 254 */ this.mvtStock.setIdstock(BigInteger.valueOf(this.stock.getIdstock().longValue()));

                    /* 256 */ this.stockFacadeLocal.create(this.stock);
                    /* 257 */ this.mvtStockFacadeLocal.create(this.mvtStock);

                    /* 259 */ for (StockProduit s : this.stockProduits) {
                        /* 260 */ s.setIdStockProduit(this.stockProduitFacadeLocal.nextVal());
                        /* 261 */ s.setIdstock(this.stock);

                        /* 263 */ s.setBenefice(Double.valueOf(0.0D));
                        /* 264 */ if (s.getPrixAcquisition().doubleValue() > s.getIdlot().getPrixVente().doubleValue()) {
                            /* 265 */ s.setBenefice(Double.valueOf((s.getIdlot().getPrixVente().doubleValue() - s.getPrixAcquisition().doubleValue()) * s.getQuantite().doubleValue()));
                            /* 266 */ this.stock.setBord(Double.valueOf(this.stock.getBord().doubleValue() + s.getBenefice().doubleValue()));
                        }
                        /* 268 */ this.stockProduitFacadeLocal.create(s);

                        /* 271 */ s.getIdproduit().setQuantite(Double.valueOf(s.getIdproduit().getQuantite().doubleValue() + s.getQuantite().doubleValue()));
                        /* 272 */ this.produitFacadeLocal.edit(s.getIdproduit());

                        /* 275 */ s.getIdlot().setQuantite(Double.valueOf(s.getIdlot().getQuantite().doubleValue() + s.getQuantite().doubleValue()));
                        /* 276 */ this.lotFacadeLocal.edit(s.getIdlot());

                        /* 278 */ LigneMvtStock lmvts = new LigneMvtStock();
                        /* 279 */ lmvts.setIdLigneMvtStock(this.ligneMvtStockFacadeLocal.nextLongVal());
                        /* 280 */ lmvts.setIdMvtStock(this.mvtStock);
                        /* 281 */ lmvts.setIdlot(s.getIdlot());
                        /* 282 */ lmvts.setClient(" ");
                        /* 283 */ lmvts.setFournisseur(this.fournisseur.getRaisonSociale());
                        /* 284 */ lmvts.setQuantiteEntree(s.getQuantite());
                        /* 285 */ lmvts.setQuantiteSortie(Double.valueOf(0.0D));
                        /* 286 */ lmvts.setReste(s.getIdlot().getQuantite());
                        /* 287 */ lmvts.setType("ENTREE");
                        /* 288 */ this.ligneMvtStockFacadeLocal.create(lmvts);
                    }

                    /* 291 */ this.commandeFournisseur.setIdstock(BigInteger.valueOf(this.stock.getIdstock().longValue()));
                    /* 292 */ this.commandeFournisseur.setCode(code);
                    /* 293 */ this.commandeFournisseur.setLivre(Boolean.valueOf(true));
                    /* 294 */ this.commandeFournisseurFacadeLocal.edit(this.commandeFournisseur);

                    /* 296 */ Utilitaires.saveOperation(this.mouchardFacadeLocal, "Enregistrement du bon de livraison fournisseur ; N° : " + this.stock.getCode() + "; Montant : " + this.stock.getMontantTtc(), SessionMBean.getUserAccount());

                    /* 298 */ this.ut.commit();
                    /* 299 */ this.commandeFournisseur = new CommandeFournisseur();
                    /* 300 */ this.ligneCmdeFournisseurs.clear();
                    /* 301 */ this.stockProduits.clear();
                    /* 302 */ this.detail = this.supprimer = this.modifier = this.imprimer = Boolean.valueOf(true);
                    /* 303 */ JsfUtil.addSuccessMessage(message);

                    /* 305 */ notifySuccess();
                    /* 306 */ RequestContext.getCurrentInstance().execute("PF('CommandeCreateDialog').hide()");
                } else {
                    /* 308 */ notifyError("liste_article_vide");
                }

                /* 311 */            } else if (this.stock != null) {

                /* 313 */ this.ut.begin();

                /* 315 */ updateTotal();
                /* 316 */ this.stockFacadeLocal.edit(this.stock);

                /* 318 */ if (!this.stockProduits.isEmpty()) {
                    /* 319 */ for (StockProduit s : this.stockProduits) {

                        /* 321 */ StockProduit sp = this.stockProduitFacadeLocal.find(s.getIdStockProduit());
                        /* 322 */ if (sp.getQuantite() != s.getQuantite()) {
                            /* 323 */ Produit pro = sp.getIdproduit();
                            /* 324 */ pro.setQuantite(Double.valueOf(pro.getQuantite().doubleValue() + sp.getQuantite().doubleValue() - sp.getQuantite().doubleValue()));
                            /* 325 */ this.produitFacadeLocal.edit(pro);

                            /* 327 */ Lot l = sp.getIdlot();
                            /* 328 */ l.setQuantite(Double.valueOf(l.getQuantite().doubleValue() + sp.getQuantite().doubleValue() - sp.getQuantite().doubleValue()));
                            /* 329 */ this.lotFacadeLocal.edit(l);

                            /* 331 */ LigneMvtStock lmvts = new LigneMvtStock();
                            /* 332 */ lmvts.setIdLigneMvtStock(this.ligneMvtStockFacadeLocal.nextLongVal());
                            /* 333 */ lmvts.setIdMvtStock(this.mvtStock);
                            /* 334 */ lmvts.setIdlot(s.getIdlot());
                            /* 335 */ lmvts.setClient(" ");
                            /* 336 */ lmvts.setFournisseur(this.fournisseur.getRaisonSociale());
                            /* 337 */ if (s.getQuantite().doubleValue() > sp.getQuantite().doubleValue()) {
                                /* 338 */ lmvts.setQuantiteEntree(Double.valueOf(s.getQuantite().doubleValue() - sp.getQuantite().doubleValue()));
                                /* 339 */ lmvts.setQuantiteSortie(Double.valueOf(0.0D));
                                /* 340 */ lmvts.setReste(s.getIdlot().getQuantite());
                                /* 341 */ lmvts.setType("ENTREE");
                            } else {
                                /* 343 */ lmvts.setQuantiteEntree(Double.valueOf(0.0D));
                                /* 344 */ lmvts.setQuantiteSortie(Double.valueOf(sp.getQuantite().doubleValue() - s.getQuantite().doubleValue()));
                                /* 345 */ lmvts.setReste(s.getIdlot().getQuantite());
                                /* 346 */ lmvts.setType("SORTIE");
                            }
                            /* 348 */ this.ligneMvtStockFacadeLocal.create(lmvts);
                        }
                        /* 350 */ this.stockProduitFacadeLocal.edit(s);
                    }
                }

                /* 354 */ this.ut.commit();
                /* 355 */ this.ligneCmdeFournisseurs.clear();
                /* 356 */ this.commandeFournisseurs.clear();
                /* 357 */ this.ligneMvtStocks.clear();
                /* 358 */ this.commandeFournisseur = new CommandeFournisseur();
                /* 359 */ this.stock = null;
                /* 360 */ this.supprimer = this.modifier = this.imprimer = this.detail = Boolean.valueOf(true);

                /* 362 */ notifySuccess();
                /* 363 */ RequestContext.getCurrentInstance().execute("PF('CommandeCreateDialog').hide()");
            } else {
                /* 365 */ notifyError("not_row_selected");
            }

            /* 368 */        } catch (Exception e) {
            /* 369 */ notifyFail(e);
        }
    }

    public void delete() {
        try {
            /* 375 */ if (this.stock != null) {

                /* 377 */ if (!this.stock.getLivraisonDirecte().booleanValue()) {

                    /* 379 */ if (!Utilitaires.isAccess(Long.valueOf(53L))) {
                        /* 380 */ notifyError("acces_refuse");
                        /* 381 */ this.detail = this.supprimer = this.modifier = this.imprimer = Boolean.valueOf(true);
                        /* 382 */ this.stock = null;

                        return;
                    }
                    /* 386 */ this.ut.begin();

                    /* 388 */ List<StockProduit> temp = this.stockProduitFacadeLocal.findByStock(this.stock);
                    /* 389 */ if (!temp.isEmpty()) {
                        /* 390 */ for (StockProduit sp : temp) {
                            /* 391 */ sp.setIdproduit(this.produitFacadeLocal.find(sp.getIdproduit().getIdproduit()));
                            /* 392 */ sp.getIdproduit().setQuantite(Double.valueOf(sp.getIdproduit().getQuantite().doubleValue() + sp.getQuantite().doubleValue()));
                            /* 393 */ this.produitFacadeLocal.edit(sp.getIdproduit());

                            /* 395 */ if (sp.getIdlot() != null) {
                                /* 396 */ sp.setIdlot(this.lotFacadeLocal.find(sp.getIdlot().getIdlot()));
                                /* 397 */ sp.getIdlot().setQuantite(Double.valueOf(sp.getIdlot().getQuantite().doubleValue() + sp.getQuantite().doubleValue()));
                                /* 398 */ this.lotFacadeLocal.edit(sp.getIdlot());
                            }
                            /* 400 */ this.stockProduitFacadeLocal.remove(sp);
                        }
                    }
                    /* 403 */ this.stockFacadeLocal.remove(this.stock);

                    /* 405 */ this.commandeFournisseur = this.commandeFournisseurFacadeLocal.find(Long.valueOf(this.stock.getIdCmdeFournisseur().longValue()));

                    /* 407 */ this.commandeFournisseur.setLivre(Boolean.valueOf(false));
                    /* 408 */ this.commandeFournisseur.setCode("-");
                    /* 409 */ this.commandeFournisseur.setTauxSatisfaction(Double.valueOf(0.0D));
                    /* 410 */ this.commandeFournisseurFacadeLocal.edit(this.commandeFournisseur);

                    /* 412 */ MvtStock mTemp = this.mvtStockFacadeLocal.find(Long.valueOf(this.stock.getIdMvtStock().longValue()));

                    /* 414 */ List<LigneMvtStock> lmvt = this.ligneMvtStockFacadeLocal.findByMvt(mTemp.getIdMvtStock());
                    /* 415 */ for (LigneMvtStock l : lmvt) {
                        /* 416 */ this.ligneMvtStockFacadeLocal.remove(l);
                    }

                    /* 419 */ this.mvtStockFacadeLocal.remove(mTemp);

                    /* 421 */ Utilitaires.saveOperation(this.mouchardFacadeLocal, "Annulation de livraison fournisseur : " + this.stock.getCode() + " ; Montant : " + this.stock.getMontantTtc() + " Fournisseur : " + this.stock.getIdfournisseur().getRaisonSociale(), SessionMBean.getUserAccount());
                    /* 422 */ this.ut.commit();

                    /* 424 */ this.stock = null;
                    /* 425 */ this.commandeFournisseur = null;
                    /* 426 */ this.supprimer = this.modifier = this.imprimer = this.detail = Boolean.valueOf(true);
                    /* 427 */ notifySuccess();
                } else {

                    /* 430 */ notifyError(this.mode);
                }
            } else {
                /* 433 */ notifyError("not_row_selected");
            }
            /* 435 */        } catch (Exception e) {
            /* 436 */ notifyFail(e);
        }
    }

    public void initPrinter(Stock s) {
        /* 441 */ this.stock = s;
        /* 442 */ print();
    }

    public void initEdit(Stock s) {
        /* 446 */ this.stock = s;
        /* 447 */ prepareEdit();
    }

    public void initView(Stock s) {
        /* 451 */ this.stock = s;
        /* 452 */ prepareview();
    }

    public void initDelete(Stock s) {
        /* 456 */ this.stock = s;
        /* 457 */ delete();
    }

    public void print() {
        try {
            /* 462 */ if (!Utilitaires.isAccess(Long.valueOf(54L))) {
                /* 463 */ notifyError("acces_refuse");
                /* 464 */ this.commandeFournisseur = null;

                return;
            }
            /* 468 */ if (this.commandeFournisseur != null) {

                /* 472 */ RequestContext.getCurrentInstance().execute("PF('FactureImprimerDialog').show()");
            } else {
                /* 474 */ notifyError("not_row_selected");
            }
            /* 476 */        } catch (Exception e) {
            /* 477 */ notifyFail(e);
        }
    }

    public void addProduit() {
        try {
            /* 483 */ LigneCmdeFournisseur l = this.ligneCmdeFournisseur;
            /* 484 */ l.setIdLigneCmdeFournisseur(Long.valueOf(0L));

            /* 486 */ l.setIdlot(this.lot);
            /* 487 */ l.setIdproduit(this.produit);

            /* 489 */ boolean drapeau = false;
            /* 490 */ int i = 0;
            /* 491 */ for (LigneCmdeFournisseur lcf : this.ligneCmdeFournisseurs) {
                /* 492 */ if (lcf.getIdproduit().getIdproduit().equals(l.getIdproduit().getIdproduit())) {
                    /* 493 */ drapeau = true;
                    break;
                }
                /* 496 */ i++;
            }

            /* 499 */ if (!drapeau) {
                /* 500 */ this.ligneCmdeFournisseurs.add(l);
                /* 501 */ RequestContext.getCurrentInstance().execute("PF('ArticleCreateDialog').hide()");
                /* 502 */ JsfUtil.addSuccessMessage(this.routine.localizeMessage("operation_reussie"));
                /* 503 */ this.ligneCmdeFournisseur = new LigneCmdeFournisseur();
                /* 504 */ this.produit = new Produit();
                return;
            }
            /* 507 */ JsfUtil.addErrorMessage("Article existant dans la commande");
            /* 508 */ updateTotal();
            /* 509 */        } catch (Exception e) {
            /* 510 */ e.printStackTrace();
            /* 511 */ this.routine.feedBack("information", "/resources/tool_images/error.png", this.routine.localizeMessage("formulaire_incomplet"));
            /* 512 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
        }
    }

    public void removeProduit(int index) {
        try {
            /* 518 */ boolean trouve = false;
            /* 519 */ this.ut.begin();

            /* 521 */ LigneCmdeFournisseur lcf = this.ligneCmdeFournisseurs.get(index);
            /* 522 */ if (lcf.getIdLigneCmdeFournisseur().longValue() != 0L) {
                /* 523 */ trouve = true;
                /* 524 */ this.ligneCmdeFournisseurFacadeLocal.remove(lcf);
                /* 525 */ Utilitaires.saveOperation(this.mouchardFacadeLocal, "Suppression de l'article : " + lcf.getIdproduit().getNom() + " quantité : " + lcf.getQuantite() + " dans la facture : " + this.stock.getCode(), SessionMBean.getUserAccount());
            }
            /* 527 */ this.ligneCmdeFournisseurs.remove(index);

            /* 529 */ updateTotal();
            /* 530 */ if (trouve) {
                /* 531 */ this.commandeFournisseurFacadeLocal.edit(this.commandeFournisseur);
            }
            /* 533 */ this.ut.commit();
            /* 534 */ JsfUtil.addSuccessMessage("Supprimé");
            /* 535 */        } catch (Exception e) {
            /* 536 */ e.printStackTrace();
            /* 537 */ JsfUtil.addErrorMessage(this.routine.localizeMessage("echec_operation"));
        }
    }

    public Double calculTotal(List<StockProduit> stockProduits) {
        /* 542 */ Double resultat = Double.valueOf(0.0D);
        /* 543 */ for (StockProduit sp : stockProduits) {
            /* 544 */ resultat = Double.valueOf(resultat.doubleValue() + sp.getPrixAcquisition().doubleValue() * sp.getQuantite().doubleValue());
        }
        /* 546 */ return resultat;
    }

    public void updateTotal() {
        try {
            /* 551 */ this.total = calculTotal(this.stockProduits);
            /* 552 */ this.stock.setMontant(this.total);

            /* 554 */ if (this.stock.getCalculRemise().booleanValue()) {
                /* 555 */ this.stock.setMontantRemise(Double.valueOf(this.total.doubleValue() * this.stock.getTauxRemise().doubleValue() / 100.0D));
            } else {
                /* 557 */ this.stock.setMontantRemise(Double.valueOf(0.0D));
            }

            /* 560 */ if (this.stock.getCalculTva().booleanValue()) {
                /* 561 */ this.stock.setMontantTva(Double.valueOf((this.total.doubleValue() - this.stock.getMontantRemise().doubleValue()) * this.stock.getTauxTva().doubleValue() / 100.0D));
            } else {
                /* 563 */ this.stock.setMontantTva(Double.valueOf(0.0D));
            }
            /* 565 */ this.stock.setMontantTtc(Double.valueOf(this.total.doubleValue() - this.stock.getMontantRemise().doubleValue() + this.stock.getMontantTva().doubleValue()));
            /* 566 */        } catch (Exception e) {
            /* 567 */ e.printStackTrace();
        }
    }

    public void updateTotaux() {
        try {
            /* 573 */ this.cout_quantite = Double.valueOf(0.0D);
            /* 574 */ if (this.ligneCmdeFournisseur.getQuantite() != null
                    && /* 575 */ this.ligneCmdeFournisseur.getMontant() != null) {
                /* 576 */ this.cout_quantite = Double.valueOf(this.ligneCmdeFournisseur.getMontant().doubleValue() * this.ligneCmdeFournisseur.getQuantite().doubleValue());
            }
        } /* 579 */ catch (Exception e) {
            /* 580 */ e.printStackTrace();
            /* 581 */ this.cout_quantite = Double.valueOf(0.0D);
        }
    }

    public void updatedataLot() {
        try {
            /* 587 */ if (this.lot != null) {
                /* 588 */ this.ligneCmdeFournisseur.setMontant(this.lot.getPrixAchat());
            }
            /* 590 */        } catch (Exception e) {
            /* 591 */ e.printStackTrace();
        }
    }

    public void notifyError(String message) {
        /* 596 */ this.routine.feedBack("avertissement", "/resources/tool_images/warning.jpeg", this.routine.localizeMessage(message));
        /* 597 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
    }

    public void notifySuccess() {
        /* 601 */ RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').hide()");
        /* 602 */ this.routine.feedBack("information", "/resources/tool_images/success.png", this.routine.localizeMessage("operation_reussie"));
        /* 603 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
    }

    public void notifyFail(Exception e) {
        /* 607 */ RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').hide()");
        /* 608 */ this.routine.catchException(e, this.routine.localizeMessage("echec_operation"));
        /* 609 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
    }
}
