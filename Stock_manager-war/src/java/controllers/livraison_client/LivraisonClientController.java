package controllers.livraison_client;

import entities.Client;
import entities.Commande;
import entities.CommandeClient;
import entities.Facture;
import entities.LigneCommandeClient;
import entities.LigneMvtStock;
import entities.Lot;
import entities.MvtStock;
import entities.Produit;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.context.RequestContext;
import utils.JsfUtil;
import utils.PrintUtils;
import utils.SessionMBean;
import utils.Utilitaires;

@ManagedBean
@SessionScoped
public class LivraisonClientController
        extends AbstractLivraisonClientController
        implements Serializable {

    @PostConstruct
    private void init() {
    }

    public void updateMois() {
        try {
            /*  51 */ this.anneeMois = this.anneeMoisFacadeLocal.find(this.anneeMois.getIdAnneeMois());
            /*  52 */        } catch (Exception e) {
            /*  53 */ e.printStackTrace();
        }
    }

    public void updateCalculTva() {
        /*  58 */ updateTotal();
    }

    public void prepareCreate() {
        try {
            /*  64 */ if (!Utilitaires.isAccess(Long.valueOf(52L))) {
                /*  65 */ notifyError("acces_refuse");
                return;
            }
            /*  68 */ RequestContext.getCurrentInstance().execute("PF('CommandeCreateDialog').show()");
            /*  69 */ this.mode = "Create";

            /*  71 */ this.facture = new Facture();
            /*  72 */ this.client = new Client();
            /*  73 */ this.facture.setCalcultva(Boolean.valueOf(false));
            /*  74 */ this.facture.setCalculRemise(Boolean.valueOf(false));
            /*  75 */ this.facture.setCredit(Boolean.valueOf(false));
            /*  76 */ this.facture.setComptabilise(Boolean.valueOf(false));

            /*  78 */ this.facture.setMontantPaye(Double.valueOf(0.0D));
            /*  79 */ this.facture.setReste(Double.valueOf(0.0D));
            /*  80 */ this.facture.setMontantRemise(Double.valueOf(0.0D));
            /*  81 */ this.facture.setMontantTtc(Double.valueOf(0.0D));
            /*  82 */ this.facture.setMontantTva(Double.valueOf(0.0D));
            /*  83 */ this.facture.setTauxRemise(Double.valueOf(0.0D));
            /*  84 */ this.facture.setTauxTva(Double.valueOf(0.0D));
            /*  85 */ this.facture.setBord(Double.valueOf(0.0D));
            /*  86 */ this.commandes.clear();
            /*  87 */ this.payement_credit = false;

            /*  89 */ this.commandeClient = new CommandeClient();
            /*  90 */ this.ligneCommandeClients.clear();
            /*  91 */ this.showSelectorCommand = Boolean.valueOf(false);

            /*  93 */ this.total = Double.valueOf(0.0D);
            /*  94 */        } catch (Exception e) {
            /*  95 */ this.routine.catchException(e, this.routine.localizeMessage("echec_operation"));
            /*  96 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
        }
    }

    public void prepareCreateCommande() {
        try {
            /* 102 */ this.commandeClient = new CommandeClient();
            /* 103 */ this.commandeClients = this.commandeClientFacadeLocal.findByLivre(false);
            /* 104 */ RequestContext.getCurrentInstance().execute("PF('ArticleCreateDialog').show()");
            /* 105 */        } catch (Exception e) {
            /* 106 */ notifyFail(e);
        }
    }

    public void prepareEdit() {
        try {
            /* 113 */ if (this.facture == null) {
                /* 114 */ notifyError("not_row_selected");

                return;
            }
            /* 118 */ if (!this.facture.getVenteDirecte().booleanValue()) {

                /* 120 */ if (Utilitaires.isDayClosed()) {
                    /* 121 */ notifyError("journee_cloturee");

                    return;
                }
                /* 125 */ this.showSelectorCommand = Boolean.valueOf(true);
                /* 126 */ this.commandeClient = this.commandeClientFacadeLocal.find(Long.valueOf(this.facture.getIdCmdeClient().longValue()));

                /* 128 */ if (this.commandeClient.getLivre().booleanValue()) {
                    /* 129 */ notifyError("commande_deja_livree");

                    return;
                }
                /* 133 */ if (!Utilitaires.isAccess(Long.valueOf(49L))) {
                    /* 134 */ notifyError("acces_refuse");
                    /* 135 */ this.commandeClient = null;

                    return;
                }
                /* 139 */ this.mode = "Edit";

                /* 141 */ this.ligneCommandeClients = this.ligneCommandeClientFacadeLocal.findByCommande(this.commandeClient.getIdCommandeClient());
                /* 142 */ this.commandes = this.commandeFacadeLocal.findByFacture(this.facture);
                /* 143 */ this.client = this.facture.getIdclient();
                /* 144 */ this.total = this.facture.getMontantTtc();
                /* 145 */ RequestContext.getCurrentInstance().execute("PF('CommandeCreateDialog').show()");
            } else {
                /* 147 */ notifyError("vente_directe");
            }
            /* 149 */        } catch (Exception e) {
            /* 150 */ notifyFail(e);
        }
    }

    public void prepareview() {
        try {
            /* 156 */ if (this.facture != null) {
                /* 157 */ this.commandes = this.commandeFacadeLocal.findByFacture(this.facture);
                /* 158 */ if (!this.commandes.isEmpty()) {
                    /* 159 */ RequestContext.getCurrentInstance().execute("PF('FactureDetailDialog').show()");
                    return;
                }
                /* 162 */ notifyError("liste_article_vide");
            } else {
                /* 164 */ notifyError("not_row_selected");
            }
            /* 166 */        } catch (Exception e) {
            /* 167 */ notifyFail(e);
        }
    }

    public void selectCommande() {
        try {
            /* 173 */ if (this.commandeClient != null) {

                /* 175 */ this.commandes.clear();

                /* 177 */ this.client = this.commandeClient.getIdclient();
                /* 178 */ this.ligneCommandeClients = this.ligneCommandeClientFacadeLocal.findByCommande(this.commandeClient.getIdCommandeClient());

                /* 180 */ this.facture.setTauxRemise(this.commandeClient.getTauxRemise());
                /* 181 */ this.facture.setTauxTva(this.commandeClient.getTauxTva());

                /* 183 */ this.facture.setCalculRemise(this.commandeClient.getCalculRemise());
                /* 184 */ this.facture.setCalcultva(this.commandeClient.getCalculTva());
                /* 185 */ this.commandeClient.setDateLivraisonEffective(SessionMBean.getDateOuverture());
                /* 186 */ int conteur = 0;
                /* 187 */ Double pourcentage = Double.valueOf(0.0D);
                /* 188 */ for (LigneCommandeClient lcc : this.ligneCommandeClients) {

                    /* 190 */ boolean suffisant = false;
                    /* 191 */ Double qte = lcc.getQuantite();
                    /* 192 */ Double qteReste = lcc.getQuantite();
                    /* 193 */ List<Lot> lotTemp = this.lotFacadeLocal.findByArticle(lcc.getIdproduit().getIdproduit(), lcc.getIdproduit().getPerissable().booleanValue());

                    /* 195 */ Lot lSearch = null;
                    /* 196 */ for (Lot l : lotTemp) {
                        /* 197 */ if (l.getIdlot().equals(lcc.getIdlot().getIdlot())
                                && /* 198 */ l.getQuantite().doubleValue() > 0.0D) {
                            /* 199 */ lSearch = l;

                            break;
                        }
                    }

                    /* 205 */ Double sommeQte = Double.valueOf(0.0D);

                    /* 207 */ if (lSearch != null) {
                        /* 209 */ if (lSearch.getQuantite().doubleValue() > 0.0D) {

                            /* 211 */ Commande c = new Commande();
                            /* 212 */ c.setIdcommande(Long.valueOf(0L));
                            /* 213 */ c.setIdlot(lcc.getIdlot());
                            /* 214 */ c.setIdproduit(lcc.getIdproduit());
                            /* 215 */ c.setMontant(lcc.getMontant());
                            /* 216 */ c.setTva(lcc.getIdproduit().getTva());

                            /* 218 */ if (lSearch.getQuantite().doubleValue() >= lcc.getQuantite().doubleValue()) {
                                /* 219 */ c.setQuantite(lcc.getQuantite());
                                /* 220 */ this.commandes.add(c);
                                /* 221 */ qteReste = Double.valueOf(0.0D);
                                /* 222 */ sommeQte = Double.valueOf(sommeQte.doubleValue() + lcc.getQuantite().doubleValue());
                                /* 223 */ suffisant = true;
                            } else {
                                /* 225 */ c.setQuantite(lSearch.getQuantite());
                                /* 226 */ qteReste = Double.valueOf(qte.doubleValue() - lSearch.getQuantite().doubleValue());
                                /* 227 */ this.commandes.add(c);
                                /* 228 */ sommeQte = Double.valueOf(sommeQte.doubleValue() + lSearch.getQuantite().doubleValue());
                                /* 229 */ suffisant = false;
                            }
                        } else {
                            /* 232 */ suffisant = false;
                        }
                    }

                    /* 236 */ if (!suffisant) {
                        /* 237 */ for (Lot l : lotTemp) {

                            /* 239 */ if (!l.getIdlot().equals(lcc.getIdlot().getIdlot())) {

                                /* 241 */ if (l.getQuantite().doubleValue() > 0.0D) {

                                    /* 243 */ Commande c = new Commande();
                                    /* 244 */ c.setIdcommande(Long.valueOf(0L));
                                    /* 245 */ c.setIdlot(l);
                                    /* 246 */ c.setIdproduit(lcc.getIdproduit());
                                    /* 247 */ c.setMontant(lcc.getMontant());
                                    /* 248 */ c.setTva(lcc.getIdproduit().getTva());

                                    /* 250 */ if (l.getQuantite().doubleValue() >= qteReste.doubleValue()) {
                                        /* 251 */ c.setQuantite(qteReste);
                                        /* 252 */ this.commandes.add(c);
                                        /* 253 */ sommeQte = Double.valueOf(sommeQte.doubleValue() + qteReste.doubleValue());
                                        /* 254 */ suffisant = true;
                                        break;
                                    }
                                    /* 257 */ qteReste = Double.valueOf(qteReste.doubleValue() - l.getQuantite().doubleValue());
                                    /* 258 */ c.setQuantite(l.getQuantite());
                                    /* 259 */ this.commandes.add(c);
                                    /* 260 */ sommeQte = Double.valueOf(sommeQte.doubleValue() + l.getQuantite().doubleValue());
                                    /* 261 */ suffisant = false;
                                    continue;
                                }
                                /* 264 */ suffisant = false;
                            }
                        }
                    }

                    /* 270 */ Utilitaires.arrondiNDecimales(sommeQte.doubleValue() / qte.doubleValue() * 100.0D, 2);
                    /* 271 */ ((LigneCommandeClient) this.ligneCommandeClients.get(conteur)).setTauxSatisfaction(Utilitaires.arrondiNDecimales(sommeQte.doubleValue() / qte.doubleValue() * 100.0D, 2));
                    /* 272 */ pourcentage = Double.valueOf(pourcentage.doubleValue() + Utilitaires.arrondiNDecimales(sommeQte.doubleValue() / qte.doubleValue() * 100.0D, 2).doubleValue());
                    /* 273 */ conteur++;
                }
                /* 275 */ this.commandeClient.setTauxSatisfaction(pourcentage);
                /* 276 */ updateTotal();
                /* 277 */ RequestContext.getCurrentInstance().execute("PF('ArticleCreateDialog').hide()");
            }
            /* 279 */        } catch (Exception e) {
            /* 280 */ e.printStackTrace();
        }
    }

    public void filterMois() {
        try {
            /* 286 */ this.anneeMoises.clear();
            /* 287 */ if (this.annee.getIdannee() != null) {
                /* 288 */ this.anneeMoises = this.anneeMoisFacadeLocal.findByAnneeEtat(this.annee, true);
            }
            /* 290 */        } catch (Exception e) {
            /* 291 */ e.printStackTrace();
        }
    }

    public void create() {
        try {
            /* 297 */ if ("Create".equals(this.mode)) {

                /* 299 */ if (!this.ligneCommandeClients.isEmpty()) {

                    /* 301 */ String message = "";

                    /* 303 */ String code = "F-" + this.anneeMois.getIdannee().getNom() + "-" + this.anneeMois.getIdmois().getNom().toUpperCase().substring(0, 2);
                    /* 304 */ Long nextPayement = this.factureFacadeLocal.nextVal(this.anneeMois);
                    /* 305 */ code = Utilitaires.genererCodeFacture(code, nextPayement);

                    /* 307 */ this.facture.setNumeroFacture(code);

                    /* 309 */ this.facture.setIdfacture(this.factureFacadeLocal.nextLongVal());
                    /* 310 */ this.mvtStock.setIdMvtStock(this.mvtStockFacadeLocal.nextLongVal());

                    /* 312 */ this.facture.setIdclient(this.client);
                    /* 313 */ this.facture.setIdAnneeMois(this.anneeMois);
                    /* 314 */ this.facture.setIdutilisateur(SessionMBean.getUserAccount());
                    /* 315 */ this.facture.setMontant(this.total);
                    /* 316 */ this.facture.setVenteDirecte(Boolean.valueOf(false));
                    /* 317 */ this.facture.setDateAchat(this.commandeClient.getDateLivraisonEffective());
                    /* 318 */ this.facture.setBord(Double.valueOf(0.0D));
                    /* 319 */ this.facture.setIdCmdeClient(BigInteger.valueOf(this.commandeClient.getIdCommandeClient().longValue()));
                    /* 320 */ this.facture.setIdMvtStock(BigInteger.valueOf(this.mvtStock.getIdMvtStock().longValue()));

                    /* 322 */ if (this.facture.getCredit().booleanValue()) {
                        /* 323 */ this.facture.setReste(Double.valueOf(this.facture.getMontantTtc().doubleValue() - this.facture.getMontantPaye().doubleValue()));
                        /* 324 */ this.facture.setPaye(Boolean.valueOf(false));
                        /* 325 */ message = "Commande enregistrée avec succès , mais pour un payement ultérieur -> Reste : " + this.facture.getReste() + " Numero de commande ->" + code;
                    } else {
                        /* 327 */ this.facture.setPaye(Boolean.valueOf(true));
                        /* 328 */ this.facture.setMontantPaye(this.facture.getMontantTtc());
                        /* 329 */ this.facture.setReste(Double.valueOf(0.0D));
                        /* 330 */ message = "Commande enregistrée avec succès et payée au comptent";
                    }

                    /* 333 */ updateTotal();
                    /* 334 */ this.ut.begin();

                    /* 336 */ this.mvtStock.setIdmois(this.facture.getIdAnneeMois());
                    /* 337 */ this.mvtStock.setCode(code);
                    /* 338 */ this.mvtStock.setDateMvt(this.commandeClient.getDateLivraisonEffective());
                    /* 339 */ this.mvtStock.setIdfacture(BigInteger.valueOf(this.facture.getIdfacture().longValue()));

                    /* 341 */ this.factureFacadeLocal.create(this.facture);
                    /* 342 */ this.mvtStockFacadeLocal.create(this.mvtStock);

                    /* 344 */ for (Commande c : this.commandes) {
                        /* 345 */ c.setIdcommande(this.commandeFacadeLocal.nextVal());
                        /* 346 */ c.setIdfacture(this.facture);
                        /* 347 */ c.setTva(Double.valueOf(0.0D));
                        /* 348 */ c.setComptabilise(Boolean.valueOf(false));
                        /* 349 */ if (this.facture.getCalcultva().booleanValue()) {
                            /* 350 */ c.setTva(this.facture.getTauxTva());
                            /* 351 */ c.setMontanttva(Double.valueOf(c.getMontant().doubleValue() * this.facture.getTauxTva().doubleValue() / 100.0D));
                        }

                        /* 354 */ c.setBenefice(Double.valueOf(0.0D));
                        /* 355 */ if (c.getMontant().doubleValue() > c.getIdlot().getPrixVente().doubleValue()) {
                            /* 356 */ c.setBenefice(Double.valueOf((c.getMontant().doubleValue() - c.getIdlot().getPrixVente().doubleValue()) * c.getQuantite().doubleValue()));
                            /* 357 */ this.facture.setBord(Double.valueOf(this.facture.getBord().doubleValue() + c.getBenefice().doubleValue()));
                        }
                        /* 359 */ this.commandeFacadeLocal.create(c);

                        /* 362 */ c.getIdproduit().setQuantite(Double.valueOf(c.getIdproduit().getQuantite().doubleValue() - c.getQuantite().doubleValue()));
                        /* 363 */ this.produitFacadeLocal.edit(c.getIdproduit());

                        /* 366 */ c.getIdlot().setQuantite(Double.valueOf(c.getIdlot().getQuantite().doubleValue() - c.getQuantite().doubleValue()));
                        /* 367 */ this.lotFacadeLocal.edit(c.getIdlot());

                        /* 369 */ LigneMvtStock lmvts = new LigneMvtStock();
                        /* 370 */ lmvts.setIdLigneMvtStock(this.ligneMvtStockFacadeLocal.nextLongVal());
                        /* 371 */ lmvts.setIdMvtStock(this.mvtStock);
                        /* 372 */ lmvts.setIdlot(c.getIdlot());
                        /* 373 */ lmvts.setClient(this.client.getNom());
                        /* 374 */ lmvts.setFournisseur("");
                        /* 375 */ lmvts.setQuantiteEntree(Double.valueOf(0.0D));
                        /* 376 */ lmvts.setQuantiteSortie(c.getQuantite());
                        /* 377 */ lmvts.setReste(c.getIdlot().getQuantite());
                        /* 378 */ lmvts.setType("SORTIE");
                        /* 379 */ this.ligneMvtStockFacadeLocal.create(lmvts);
                    }

                    /* 382 */ this.commandeClient.setIdfacture(BigInteger.valueOf(this.facture.getIdfacture().longValue()));
                    /* 383 */ this.commandeClient.setCode(code);
                    /* 384 */ this.commandeClient.setLivre(Boolean.valueOf(true));
                    /* 385 */ this.commandeClientFacadeLocal.edit(this.commandeClient);
                    /* 386 */ if (this.facture.getBord().doubleValue() > 0.0D) {
                        /* 387 */ this.factureFacadeLocal.edit(this.facture);
                    }
                    /* 389 */ Utilitaires.saveOperation(this.mouchardFacadeLocal, "Enregistrement du bon de livraison ; N° : " + this.facture.getNumeroFacture() + "; Montant : " + this.facture.getMontantTtc(), SessionMBean.getUserAccount());

                    /* 391 */ this.ut.commit();
                    /* 392 */ this.commandeClient = new CommandeClient();
                    /* 393 */ this.ligneCommandeClients.clear();
                    /* 394 */ this.commandes.clear();
                    /* 395 */ this.detail = this.supprimer = this.modifier = this.imprimer = Boolean.valueOf(true);
                    /* 396 */ JsfUtil.addSuccessMessage(message);

                    /* 398 */ notifySuccess();
                    /* 399 */ RequestContext.getCurrentInstance().execute("PF('CommandeCreateDialog').hide()");
                } else {
                    /* 401 */ notifyError("liste_article_vide");
                }

                /* 404 */            } else if (this.facture != null) {

                /* 406 */ if (this.facture.getCredit().booleanValue()) {
                    /* 407 */ this.facture.setReste(Double.valueOf(calculTotal(this.commandes).doubleValue() - this.facture.getMontantPaye().doubleValue()));
                    /* 408 */ this.facture.setPaye(Boolean.valueOf(false));
                } else {
                    /* 410 */ this.facture.setPaye(Boolean.valueOf(true));
                    /* 411 */ this.facture.setMontantPaye(calculTotal(this.commandes));
                    /* 412 */ this.facture.setReste(Double.valueOf(0.0D));
                }

                /* 415 */ this.ut.begin();

                /* 417 */ updateTotal();
                /* 418 */ this.factureFacadeLocal.edit(this.facture);

                /* 420 */ if (!this.commandes.isEmpty()) {
                    /* 421 */ for (Commande c : this.commandes) {

                        /* 423 */ if (c.getIdcommande().longValue() != 0L) {

                            /* 425 */ Commande cc = this.commandeFacadeLocal.find(c.getIdcommande());
                            /* 426 */ if (c.getQuantite() != cc.getQuantite()) {
                                /* 427 */ Produit pro = cc.getIdproduit();
                                /* 428 */ pro.setQuantite(Double.valueOf(pro.getQuantite().doubleValue() + cc.getQuantite().doubleValue() - c.getQuantite().doubleValue()));
                                /* 429 */ this.produitFacadeLocal.edit(pro);

                                /* 431 */ Lot l = cc.getIdlot();
                                /* 432 */ l.setQuantite(Double.valueOf(l.getQuantite().doubleValue() + cc.getQuantite().doubleValue() - c.getQuantite().doubleValue()));
                                /* 433 */ this.lotFacadeLocal.edit(l);

                                /* 435 */ LigneMvtStock ligneMvtStock = new LigneMvtStock();
                                /* 436 */ ligneMvtStock.setIdLigneMvtStock(this.ligneMvtStockFacadeLocal.nextLongVal());
                                /* 437 */ ligneMvtStock.setIdMvtStock(this.mvtStock);
                                /* 438 */ ligneMvtStock.setIdlot(c.getIdlot());
                                /* 439 */ ligneMvtStock.setClient(this.client.getNom());
                                /* 440 */ ligneMvtStock.setFournisseur(" ");
                                /* 441 */ if (c.getQuantite().doubleValue() > cc.getQuantite().doubleValue()) {
                                    /* 442 */ ligneMvtStock.setQuantiteEntree(Double.valueOf(0.0D));
                                    /* 443 */ ligneMvtStock.setQuantiteSortie(Double.valueOf(c.getQuantite().doubleValue() - cc.getQuantite().doubleValue()));
                                    /* 444 */ ligneMvtStock.setReste(c.getIdlot().getQuantite());
                                    /* 445 */ ligneMvtStock.setType("SORTIE");
                                } else {
                                    /* 447 */ ligneMvtStock.setQuantiteEntree(Double.valueOf(cc.getQuantite().doubleValue() - c.getQuantite().doubleValue()));
                                    /* 448 */ ligneMvtStock.setQuantiteSortie(Double.valueOf(0.0D));
                                    /* 449 */ ligneMvtStock.setReste(c.getIdlot().getQuantite());
                                    /* 450 */ ligneMvtStock.setType("ENTREE");
                                }
                                /* 452 */ this.ligneMvtStockFacadeLocal.create(ligneMvtStock);
                            }
                            /* 454 */ this.commandeFacadeLocal.edit(c);
                            continue;
                        }
                        /* 456 */ LigneMvtStock lmvts = new LigneMvtStock();
                        /* 457 */ lmvts.setIdLigneMvtStock(this.ligneMvtStockFacadeLocal.nextLongVal());
                        /* 458 */ lmvts.setIdMvtStock(this.mvtStock);
                        /* 459 */ lmvts.setIdlot(c.getIdlot());
                        /* 460 */ lmvts.setClient(this.client.getNom());
                        /* 461 */ lmvts.setFournisseur("");
                        /* 462 */ lmvts.setQuantiteEntree(Double.valueOf(0.0D));
                        /* 463 */ lmvts.setQuantiteSortie(c.getQuantite());
                        /* 464 */ lmvts.setReste(c.getIdlot().getQuantite());
                        /* 465 */ lmvts.setType("SORTIE");
                        /* 466 */ this.ligneMvtStockFacadeLocal.create(lmvts);
                    }
                }

                /* 471 */ MvtStock mTemp = this.mvtStockFacadeLocal.find(Long.valueOf(this.facture.getIdMvtStock().longValue()));

                /* 473 */ List<LigneMvtStock> lmvt = this.ligneMvtStockFacadeLocal.findByMvt(mTemp.getIdMvtStock());
                /* 474 */ for (LigneMvtStock l : lmvt) {
                    /* 475 */ this.ligneMvtStockFacadeLocal.remove(l);
                }

                /* 478 */ this.mvtStockFacadeLocal.remove(mTemp);

                /* 480 */ this.ut.commit();
                /* 481 */ this.ligneCommandeClients.clear();
                /* 482 */ this.commandeClients.clear();
                /* 483 */ this.commandeClient = new CommandeClient();
                /* 484 */ this.facture = null;
                /* 485 */ this.supprimer = this.modifier = this.imprimer = Boolean.valueOf(true);

                /* 487 */ notifySuccess();

                /* 489 */ RequestContext.getCurrentInstance().execute("PF('CommandeCreateDialog').hide()");
            } else {
                /* 491 */ notifyError("not_row_selected");
            }

            /* 494 */        } catch (Exception e) {
            /* 495 */ notifyFail(e);
        }
    }

    public void updateAvance() {
        try {
            /* 501 */ if (this.facture.getCredit().booleanValue()) {
                /* 502 */ this.payement_credit = true;
            } else {
                /* 504 */ this.payement_credit = false;
            }
            /* 506 */        } catch (Exception e) {
            /* 507 */ e.printStackTrace();
        }
    }

    public void delete() {
        try {
            /* 513 */ if (this.facture != null) {

                /* 515 */ if (!this.facture.getVenteDirecte().booleanValue()) {

                    /* 517 */ if (!Utilitaires.isAccess(Long.valueOf(53L))) {
                        /* 518 */ notifyError("acces_refuse");
                        /* 519 */ this.detail = this.supprimer = this.modifier = this.imprimer = Boolean.valueOf(true);
                        /* 520 */ this.facture = null;

                        return;
                    }
                    /* 524 */ this.ut.begin();

                    /* 526 */ List<Commande> temp = this.commandeFacadeLocal.findByFacture(this.facture);
                    /* 527 */ if (!temp.isEmpty()) {
                        /* 528 */ for (Commande c : temp) {
                            /* 529 */ c.setIdproduit(this.produitFacadeLocal.find(c.getIdproduit().getIdproduit()));
                            /* 530 */ c.getIdproduit().setQuantite(Double.valueOf(c.getIdproduit().getQuantite().doubleValue() + c.getQuantite().doubleValue()));
                            /* 531 */ this.produitFacadeLocal.edit(c.getIdproduit());

                            /* 533 */ if (c.getIdlot() != null) {
                                /* 534 */ c.setIdlot(this.lotFacadeLocal.find(c.getIdlot().getIdlot()));
                                /* 535 */ c.getIdlot().setQuantite(Double.valueOf(c.getIdlot().getQuantite().doubleValue() + c.getQuantite().doubleValue()));
                                /* 536 */ this.lotFacadeLocal.edit(c.getIdlot());
                            }
                            /* 538 */ this.commandeFacadeLocal.remove(c);
                        }
                    }
                    /* 541 */ this.factureFacadeLocal.remove(this.facture);

                    /* 543 */ this.commandeClient = this.commandeClientFacadeLocal.find(Long.valueOf(this.facture.getIdCmdeClient().longValue()));

                    /* 545 */ this.commandeClient.setLivre(Boolean.valueOf(false));
                    /* 546 */ this.commandeClient.setCode("-");
                    /* 547 */ this.commandeClient.setTauxSatisfaction(Double.valueOf(0.0D));
                    /* 548 */ this.commandeClientFacadeLocal.edit(this.commandeClient);

                    /* 550 */ MvtStock mTemp = this.mvtStockFacadeLocal.find(Long.valueOf(this.facture.getIdMvtStock().longValue()));

                    /* 552 */ List<LigneMvtStock> lmvt = this.ligneMvtStockFacadeLocal.findByMvt(mTemp.getIdMvtStock());
                    /* 553 */ for (LigneMvtStock l : lmvt) {
                        /* 554 */ this.ligneMvtStockFacadeLocal.remove(l);
                    }

                    /* 557 */ this.mvtStockFacadeLocal.remove(mTemp);

                    /* 559 */ Utilitaires.saveOperation(this.mouchardFacadeLocal, "Annulation de livraison client : " + this.facture.getNumeroFacture() + " ; Montant : " + this.facture.getMontant() + " Client : " + this.facture.getIdclient().getNom(), SessionMBean.getUserAccount());
                    /* 560 */ this.ut.commit();

                    /* 562 */ this.facture = null;
                    /* 563 */ this.commandeClient = null;
                    /* 564 */ this.supprimer = this.modifier = this.imprimer = this.detail = Boolean.valueOf(true);
                    /* 565 */ notifySuccess();
                } else {

                    /* 568 */ notifyError("vente_directe");
                }
            } else {
                /* 571 */ notifyError("not_row_selected");
            }
            /* 573 */        } catch (Exception e) {
            /* 574 */ notifyFail(e);
        }
    }

    public void initPrinter(Facture f) {
        /* 579 */ this.facture = f;
        /* 580 */ print();
    }

    public void initPrinterBon(Facture f) {
        /* 584 */ this.facture = f;
        /* 585 */ printBonLivraison();
    }

    public void initEdit(Facture f) {
        /* 589 */ this.facture = f;
        /* 590 */ prepareEdit();
    }

    public void initView(Facture f) {
        /* 594 */ this.facture = f;
        /* 595 */ prepareview();
    }

    public void initDelete(Facture f) {
        /* 599 */ this.facture = f;
        /* 600 */ delete();
    }

    public void print() {
        try {
            /* 605 */ if (!Utilitaires.isAccess(Long.valueOf(54L))) {
                /* 606 */ notifyError("acces_refuse");
                /* 607 */ this.facture = null;

                return;
            }
            /* 611 */ if (this.facture != null) {
                /* 612 */ this.printDialogTitle = this.routine.localizeMessage("facture");
                /* 613 */ List<Commande> c = this.commandeFacadeLocal.findByFacture(this.facture);
                /* 614 */ this.facture.setCommandeList(c);
                /* 615 */ this.fileName = PrintUtils.printFacture(this.facture, SessionMBean.getParametrage());
                /* 616 */ RequestContext.getCurrentInstance().execute("PF('FactureImprimerDialog').show()");
            } else {
                /* 618 */ notifyError("not_row_selected");
            }
            /* 620 */        } catch (Exception e) {
            /* 621 */ notifyFail(e);
        }
    }

    public void printBonLivraison() {
        try {
            /* 627 */ if (!Utilitaires.isAccess(Long.valueOf(54L))) {
                /* 628 */ notifyError("acces_refuse");
                /* 629 */ this.facture = null;

                return;
            }
            /* 633 */ if (this.facture != null) {
                /* 634 */ this.printDialogTitle = this.routine.localizeMessage("bon_de_livraison");
                /* 635 */ List<Commande> c = this.commandeFacadeLocal.findByFacture(this.facture);
                /* 636 */ this.facture.setCommandeList(c);
                /* 637 */ this.fileName = PrintUtils.printBonLivraison(this.facture, SessionMBean.getParametrage());
                /* 638 */ RequestContext.getCurrentInstance().execute("PF('FactureImprimerDialog').show()");
            } else {
                /* 640 */ notifyError("not_row_selected");
            }
            /* 642 */        } catch (Exception e) {
            /* 643 */ notifyFail(e);
        }
    }

    public void addProduit() {
        try {
            /* 649 */ LigneCommandeClient l = this.ligneCommandeClient;
            /* 650 */ l.setIdLigneCmdeClient(Long.valueOf(0L));

            /* 652 */ l.setIdlot(this.lot);
            /* 653 */ l.setIdproduit(this.produit);

            /* 655 */ boolean drapeau = false;
            /* 656 */ int i = 0;
            /* 657 */ for (LigneCommandeClient lcc : this.ligneCommandeClients) {
                /* 658 */ if (lcc.getIdproduit().getIdproduit().equals(l.getIdproduit().getIdproduit())) {
                    /* 659 */ drapeau = true;
                    break;
                }
                /* 662 */ i++;
            }

            /* 665 */ if (!drapeau) {
                /* 666 */ this.ligneCommandeClients.add(l);
                /* 667 */ RequestContext.getCurrentInstance().execute("PF('ArticleCreateDialog').hide()");
                /* 668 */ JsfUtil.addSuccessMessage(this.routine.localizeMessage("operation_reussie"));
                /* 669 */ this.ligneCommandeClient = new LigneCommandeClient();
                /* 670 */ this.produit = new Produit();
                return;
            }
            /* 673 */ JsfUtil.addErrorMessage("Article existant dans la commande");
            /* 674 */ updateTotal();
            /* 675 */        } catch (Exception e) {
            /* 676 */ e.printStackTrace();
            /* 677 */ this.routine.feedBack("information", "/resources/tool_images/error.png", this.routine.localizeMessage("formulaire_incomplet"));
            /* 678 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
        }
    }

    public void removeProduit(int index) {
        try {
            /* 684 */ boolean trouve = false;
            /* 685 */ this.ut.begin();

            /* 687 */ LigneCommandeClient lcc = this.ligneCommandeClients.get(index);
            /* 688 */ if (lcc.getIdLigneCmdeClient().longValue() != 0L) {
                /* 689 */ trouve = true;
                /* 690 */ this.ligneCommandeClientFacadeLocal.remove(lcc);
                /* 691 */ Utilitaires.saveOperation(this.mouchardFacadeLocal, "Suppression de l'article : " + lcc.getIdproduit().getNom() + " quantité : " + lcc.getQuantite() + " dans la facture : " + this.facture.getNumeroFacture(), SessionMBean.getUserAccount());
            }
            /* 693 */ this.ligneCommandeClients.remove(index);

            /* 695 */ updateTotal();
            /* 696 */ if (trouve) {
                /* 697 */ this.commandeClientFacadeLocal.edit(this.commandeClient);
            }
            /* 699 */ this.ut.commit();
            /* 700 */ JsfUtil.addSuccessMessage("Supprimé");
            /* 701 */        } catch (Exception e) {
            /* 702 */ e.printStackTrace();
            /* 703 */ JsfUtil.addErrorMessage(this.routine.localizeMessage("echec_operation"));
        }
    }

    public Double calculTotal(List<Commande> commandes) {
        /* 708 */ Double resultat = Double.valueOf(0.0D);
        /* 709 */ for (Commande c : commandes) {
            /* 710 */ resultat = Double.valueOf(resultat.doubleValue() + c.getMontant().doubleValue() * c.getQuantite().doubleValue());
        }
        /* 712 */ return resultat;
    }

    public void updateTotal() {
        try {
            /* 717 */ this.total = calculTotal(this.commandes);
            /* 718 */ this.facture.setMontant(this.total);

            /* 720 */ if (this.facture.getCalculRemise().booleanValue()) {
                /* 721 */ this.facture.setMontantRemise(Double.valueOf(this.total.doubleValue() * this.facture.getTauxRemise().doubleValue() / 100.0D));
            } else {
                /* 723 */ this.facture.setMontantRemise(Double.valueOf(0.0D));
            }

            /* 726 */ if (this.facture.getCalcultva().booleanValue()) {
                /* 727 */ this.facture.setMontantTva(Double.valueOf((this.total.doubleValue() - this.facture.getMontantRemise().doubleValue()) * this.facture.getTauxTva().doubleValue() / 100.0D));
            } else {
                /* 729 */ this.facture.setMontantTva(Double.valueOf(0.0D));
            }
            /* 731 */ this.facture.setMontantTtc(Double.valueOf(this.total.doubleValue() - this.facture.getMontantRemise().doubleValue() + this.facture.getMontantTva().doubleValue()));
            /* 732 */        } catch (Exception e) {
            /* 733 */ e.printStackTrace();
        }
    }

    public void updateTotaux() {
        try {
            /* 739 */ this.cout_quantite = Double.valueOf(0.0D);
            /* 740 */ if (this.ligneCommandeClient.getQuantite() != null
                    && /* 741 */ this.ligneCommandeClient.getMontant() != null) {
                /* 742 */ this.cout_quantite = Double.valueOf(this.ligneCommandeClient.getMontant().doubleValue() * this.ligneCommandeClient.getQuantite().doubleValue());
            }
        } /* 745 */ catch (Exception e) {
            /* 746 */ e.printStackTrace();
            /* 747 */ this.cout_quantite = Double.valueOf(0.0D);
        }
    }

    public void updatedataLot() {
        try {
            /* 753 */ if (this.lot != null) {
                /* 754 */ this.ligneCommandeClient.setMontant(this.lot.getPrixVente());
            }
            /* 756 */        } catch (Exception e) {
            /* 757 */ e.printStackTrace();
        }
    }

    public void notifyError(String message) {
        /* 762 */ this.routine.feedBack("avertissement", "/resources/tool_images/warning.jpeg", this.routine.localizeMessage(message));
        /* 763 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
    }

    public void notifySuccess() {
        /* 767 */ RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').hide()");
        /* 768 */ this.routine.feedBack("information", "/resources/tool_images/success.png", this.routine.localizeMessage("operation_reussie"));
        /* 769 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
    }

    public void notifyFail(Exception e) {
        /* 773 */ RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').hide()");
        /* 774 */ this.routine.catchException(e, this.routine.localizeMessage("echec_operation"));
        /* 775 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
    }
}


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-war.war!\WEB-INF\classes\controllers\livraison_client\LivraisonClientController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
