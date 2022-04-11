package controllers.commande_client;

import controllers.commande_client.AbstractCommandeClientController;
import entities.Client;
import entities.CommandeClient;
import entities.Famille;
import entities.LigneCommandeClient;
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
import utils.PrintUtils;
import utils.SessionMBean;
import utils.Utilitaires;

@ManagedBean
@SessionScoped
public class CommandeClientController
        extends AbstractCommandeClientController
        implements Serializable {

    @PostConstruct
    private void init() {
        /*  42 */ this.conteur = 0;
    }

    public void updateMois() {
        try {
            /*  47 */ this.anneeMois = this.anneeMoisFacadeLocal.find(this.anneeMois.getIdAnneeMois());
            /*  48 */        } catch (Exception e) {
            /*  49 */ e.printStackTrace();
        }
    }

    public void updateCalculTva() {
        /*  54 */ updateTotal();
    }

    public void prepareCreate() {
        try {
            /*  60 */ if (!Utilitaires.isAccess(Long.valueOf(48L))) {
                /*  61 */ notifyError("acces_refuse");
                return;
            }
            /*  64 */ RequestContext.getCurrentInstance().execute("PF('CommandeCreateDialog').show()");
            /*  65 */ this.mode = "Create";
            /*  66 */ this.produit = new Produit();
            /*  67 */ this.client = new Client();

            /*  69 */ this.nouveauClient = false;
            /*  70 */ updateClient();

            /*  72 */ this.commandeClient = new CommandeClient();
            /*  73 */ this.ligneCommandeClients.clear();

            /*  75 */ this.commandeClient.setLivre(Boolean.valueOf(false));
            /*  76 */ this.commandeClient.setCalculTva(SessionMBean.getParametrage().getCalcultva());
            /*  77 */ this.commandeClient.setCalculRemise(SessionMBean.getParametrage().getCalculRemise());
            /*  78 */ this.commandeClient.setTauxTva(SessionMBean.getParametrage().getTauxTva());
            /*  79 */ this.commandeClient.setTauxRemise(SessionMBean.getParametrage().getTauxRemise());
            /*  80 */ this.commandeClient.setDateCommande(SessionMBean.getDateOuverture());
            /*  81 */ this.commandeClient.setDateLivraisonPrevisionnelle(SessionMBean.getDateOuverture());
            /*  82 */ this.commandeClient.setMontantHt(Double.valueOf(0.0D));
            /*  83 */ this.commandeClient.setMontantTtc(Double.valueOf(0.0D));
            /*  84 */ this.commandeClient.setMontantRemise(Double.valueOf(0.0D));
            /*  85 */ this.commandeClient.setMontantTva(Double.valueOf(0.0D));

            /*  87 */ this.total = Double.valueOf(0.0D);
            /*  88 */ this.conteur = 0;
            /*  89 */        } catch (Exception e) {
            /*  90 */ this.routine.catchException(e, this.routine.localizeMessage("echec_operation"));
            /*  91 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
        }
    }

    public void prepareCreateCommande() {
        /*  96 */ this.famille = new Famille();
        /*  97 */ this.produit = new Produit();
        /*  98 */ this.ligneCommandeClient = new LigneCommandeClient();
        /*  99 */ this.lot = new Lot();
        /* 100 */ if (this.conteur == 11) {
            /* 101 */ this.conteur = 0;
        }
        /* 103 */ this.cout_quantite = Double.valueOf(0.0D);
        /* 104 */ RequestContext.getCurrentInstance().execute("PF('ArticleCreateDialog').show()");
        /* 105 */ if (this.conteur == 0) {
            /* 106 */ this.produits = this.produitFacadeLocal.findAllRange(true);
            return;
        }
        /* 108 */ if (this.conteur > 0 && this.conteur <= 10) {
            return;
        }
        /* 111 */ this.produits = this.produitFacadeLocal.findAllRange(true);
    }

    public void prepareEdit() {
        try {
            /* 118 */ if (this.commandeClient == null) {
                /* 119 */ notifyError("not_row_selected");

                return;
            }
            /* 123 */ if (this.commandeClient.getLivre().booleanValue()) {
                /* 124 */ notifyError("commande_deja_livree");

                return;
            }
            /* 128 */ if (!Utilitaires.isAccess(Long.valueOf(49L))) {
                /* 129 */ notifyError("acces_refuse");
                /* 130 */ this.commandeClient = null;

                return;
            }
            /* 134 */ this.mode = "Edit";
            /* 135 */ this.showSelector = Boolean.valueOf(false);
            /* 136 */ this.selectClient = Boolean.valueOf(true);
            /* 137 */ this.saisieClient = Boolean.valueOf(false);

            /* 139 */ this.ligneCommandeClients = this.ligneCommandeClientFacadeLocal.findByCommande(this.commandeClient.getIdCommandeClient());
            /* 140 */ this.client = this.commandeClient.getIdclient();
            /* 141 */ this.total = this.commandeClient.getMontantTtc();

            /* 143 */ RequestContext.getCurrentInstance().execute("PF('CommandeCreateDialog').show()");
            /* 144 */        } catch (Exception e) {
            /* 145 */ notifyFail(e);
        }
    }

    public void prepareview() {
        try {
            /* 151 */ if (this.commandeClient != null) {
                /* 152 */ this.ligneCommandeClients = this.ligneCommandeClientFacadeLocal.findByCommande(this.commandeClient.getIdCommandeClient());
                /* 153 */ if (!this.ligneCommandeClients.isEmpty()) {
                    /* 154 */ RequestContext.getCurrentInstance().execute("PF('FactureDetailDialog').show()");
                    return;
                }
                /* 157 */ notifyError("liste_article_vide");
            } else {

                /* 160 */ notifyError("not_row_selected");
            }
            /* 162 */        } catch (Exception e) {
            /* 163 */ notifyFail(e);
        }
    }

    public void filterProduit() {
        try {
            /* 169 */ this.produits.clear();
            /* 170 */ this.produit = new Produit();
            /* 171 */ this.lot = new Lot();
            /* 172 */ if (this.famille.getIdfamille() != null) {
                /* 173 */ this.produits = this.produitFacadeLocal.findByFamille(this.famille);
            }
            /* 175 */        } catch (Exception e) {
            /* 176 */ e.printStackTrace();
        }
    }

    public void filterMois() {
        try {
            /* 182 */ this.anneeMoises.clear();
            /* 183 */ if (this.annee.getIdannee() != null) {
                /* 184 */ this.anneeMoises = this.anneeMoisFacadeLocal.findByAnneeEtat(this.annee, true);
            }
            /* 186 */        } catch (Exception e) {
            /* 187 */ e.printStackTrace();
        }
    }

    public void updateClient() {
        try {
            /* 193 */ if (this.nouveauClient) {
                /* 194 */ this.saisieClient = Boolean.valueOf(true);
                /* 195 */ this.selectClient = Boolean.valueOf(false);
            } else {
                /* 197 */ this.saisieClient = Boolean.valueOf(false);
                /* 198 */ this.selectClient = Boolean.valueOf(true);
            }
            /* 200 */        } catch (Exception e) {
            /* 201 */ e.printStackTrace();
        }
    }

    public void create() {
        try {
            /* 207 */ if ("Create".equals(this.mode)) {

                /* 209 */ if (!this.ligneCommandeClients.isEmpty()) {

                    /* 211 */ String message = "";

                    /* 213 */ if (this.nouveauClient) {
                        /* 214 */ this.client.setIdclient(this.clientFacadeLocal.nextVal());
                        /* 215 */ this.client.setSolde(Integer.valueOf(0));
                        /* 216 */ this.clientFacadeLocal.create(this.client);
                        /* 217 */ Utilitaires.saveOperation(this.mouchardFacadeLocal, "Enregistrement du client : " + this.client.getNom(), SessionMBean.getUserAccount());
                    } else {
                        /* 219 */ this.client = this.clientFacadeLocal.find(this.client.getIdclient());
                    }

                    /* 222 */ this.commandeClient.setIdCommandeClient(this.commandeClientFacadeLocal.nextLongVal());
                    /* 223 */ this.commandeClient.setIdclient(this.client);
                    /* 224 */ this.commandeClient.setIdmois(this.anneeMois);

                    /* 226 */ this.ut.begin();

                    /* 228 */ this.commandeClient.setCode("-");
                    /* 229 */ updateTotal();
                    /* 230 */ this.commandeClientFacadeLocal.create(this.commandeClient);

                    /* 232 */ for (LigneCommandeClient lcc : this.ligneCommandeClients) {
                        /* 233 */ lcc.setIdLigneCmdeClient(this.ligneCommandeClientFacadeLocal.nextVal());
                        /* 234 */ lcc.setIdCmdeClient(this.commandeClient);
                        /* 235 */ this.ligneCommandeClientFacadeLocal.create(lcc);
                    }

                    /* 238 */ Utilitaires.saveOperation(this.mouchardFacadeLocal, "Enregistrement de la commande ; Montant " + this.commandeClient.getMontantTtc(), SessionMBean.getUserAccount());

                    /* 240 */ this.ut.commit();
                    /* 241 */ this.commandeClient = null;
                    /* 242 */ this.ligneCommandeClients.clear();
                    /* 243 */ this.detail = this.supprimer = this.modifier = this.imprimer = Boolean.valueOf(true);
                    /* 244 */ JsfUtil.addSuccessMessage(message);

                    /* 246 */ notifySuccess();
                    /* 247 */ RequestContext.getCurrentInstance().execute("PF('CommandeCreateDialog').hide()");
                } else {
                    /* 249 */ notifyError("liste_article_vide");
                }

                /* 252 */            } else if (this.commandeClient != null) {

                /* 254 */ this.ut.begin();

                /* 256 */ this.client = this.clientFacadeLocal.find(this.client.getIdclient());
                /* 257 */ this.commandeClient.setIdclient(this.client);

                /* 259 */ updateTotal();
                /* 260 */ this.commandeClientFacadeLocal.edit(this.commandeClient);

                /* 262 */ if (!this.ligneCommandeClients.isEmpty()) {
                    /* 263 */ for (LigneCommandeClient lcc : this.ligneCommandeClients) {
                        /* 264 */ if (lcc.getIdLigneCmdeClient().longValue() != 0L) {
                            /* 265 */ this.ligneCommandeClientFacadeLocal.edit(lcc);
                            continue;
                        }
                        /* 267 */ lcc.setIdLigneCmdeClient(this.ligneCommandeClientFacadeLocal.nextVal());
                        /* 268 */ lcc.setIdCmdeClient(this.commandeClient);
                        /* 269 */ this.ligneCommandeClientFacadeLocal.create(lcc);
                    }
                }

                /* 274 */ this.ut.commit();
                /* 275 */ this.commandeClient = null;
                /* 276 */ this.ligneCommandeClients.clear();
                /* 277 */ this.detail = this.supprimer = this.modifier = this.imprimer = Boolean.valueOf(true);

                /* 279 */ notifySuccess();
                /* 280 */ RequestContext.getCurrentInstance().execute("PF('CommandeCreateDialog').hide()");
            } else {

                /* 283 */ notifyError("not_row_selected");
            }

            /* 286 */        } catch (Exception e) {
            /* 287 */ notifyFail(e);
        }
    }

    public void updateAvance() {
    }

    public void delete() {
        try {
            /* 297 */ if (this.commandeClient != null) {

                /* 299 */ if (this.commandeClient.getLivre().booleanValue()) {
                    /* 300 */ notifyError("commande_deja_livree");

                    return;
                }
                /* 304 */ if (!Utilitaires.isAccess(Long.valueOf(50L))) {
                    /* 305 */ notifyError("acces_refuse");
                    /* 306 */ this.detail = this.supprimer = this.modifier = this.imprimer = Boolean.valueOf(true);
                    /* 307 */ this.commandeClient = null;

                    return;
                }
                /* 311 */ this.ut.begin();

                /* 313 */ List<LigneCommandeClient> temp = this.ligneCommandeClientFacadeLocal.findByCommande(this.commandeClient.getIdCommandeClient());
                /* 314 */ if (!temp.isEmpty()) {
                    /* 315 */ for (LigneCommandeClient lcc : temp) {
                        /* 316 */ this.ligneCommandeClientFacadeLocal.remove(lcc);
                    }
                }
                /* 319 */ this.commandeClientFacadeLocal.remove(this.commandeClient);
                /* 320 */ this.ut.commit();
                /* 321 */ Utilitaires.saveOperation(this.mouchardFacadeLocal, "Suppresion de la commande client : " + this.commandeClient.getCode() + " Montant : " + this.commandeClient.getMontantTtc() + " Client : " + this.commandeClient.getIdclient().getNom(), SessionMBean.getUserAccount());
                /* 322 */ this.commandeClient = null;
                /* 323 */ this.supprimer = this.modifier = this.imprimer = Boolean.valueOf(true);
                /* 324 */ notifySuccess();
            } else {
                /* 326 */ notifyError("not_row_selected");
            }
            /* 328 */        } catch (Exception e) {
            /* 329 */ notifyFail(e);
        }
    }

    public void initPrinter(CommandeClient c) {
        /* 334 */ this.commandeClient = c;
        /* 335 */ print();
    }

    public void initEdit(CommandeClient c) {
        /* 339 */ this.commandeClient = c;
        /* 340 */ prepareEdit();
    }

    public void initView(CommandeClient c) {
        /* 344 */ this.commandeClient = c;
        /* 345 */ prepareview();
    }

    public void initDelete(CommandeClient c) {
        /* 349 */ this.commandeClient = c;
        /* 350 */ delete();
    }

    public void print() {
        try {
            if (!Utilitaires.isAccess(51L)) {
                notifyError("acces_refuse");
                this.commandeClient = null;
                return;
            }
            if (this.commandeClient != null) {
                this.commandeClient.setLigneCommandeClientList(this.ligneCommandeClientFacadeLocal.findByCommande(this.commandeClient.getIdCommandeClient()));
                this.fileName = PrintUtils.printBonCommandeClient(this.commandeClient, SessionMBean.getParametrage());
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
            /* 375 */ LigneCommandeClient l = this.ligneCommandeClient;
            /* 376 */ l.setIdLigneCmdeClient(Long.valueOf(0L));

            /* 378 */ l.setIdlot(this.lot);
            /* 379 */ l.setIdproduit(this.produit);

            /* 381 */ boolean drapeau = false;
            /* 382 */ int i = 0;
            /* 383 */ for (LigneCommandeClient lcc : this.ligneCommandeClients) {
                /* 384 */ if (lcc.getIdproduit().getIdproduit().equals(l.getIdproduit().getIdproduit())) {
                    /* 385 */ drapeau = true;
                    break;
                }
                /* 388 */ i++;
            }

            /* 391 */ if (!drapeau) {
                /* 392 */ this.ligneCommandeClients.add(l);
                /* 393 */ RequestContext.getCurrentInstance().execute("PF('ArticleCreateDialog').hide()");
                /* 394 */ JsfUtil.addSuccessMessage(this.routine.localizeMessage("operation_reussie"));
                /* 395 */ this.ligneCommandeClient = new LigneCommandeClient();
                /* 396 */ this.produit = new Produit();
                return;
            }
            /* 399 */ JsfUtil.addErrorMessage("Article existant dans la commande");
            /* 400 */ updateTotal();
            /* 401 */ this.conteur++;
            /* 402 */        } catch (Exception e) {
            /* 403 */ e.printStackTrace();
            /* 404 */ this.routine.feedBack("information", "/resources/tool_images/error.png", this.routine.localizeMessage("formulaire_incomplet"));
            /* 405 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
        }
    }

    public void removeProduit(int index) {
        try {
            /* 411 */ boolean trouve = false;
            /* 412 */ this.ut.begin();

            /* 414 */ LigneCommandeClient lcc = this.ligneCommandeClients.get(index);
            /* 415 */ if (lcc.getIdLigneCmdeClient().longValue() != 0L) {
                /* 416 */ trouve = true;
                /* 417 */ this.ligneCommandeClientFacadeLocal.remove(lcc);
                /* 418 */ Utilitaires.saveOperation(this.mouchardFacadeLocal, "Suppression de l'article : " + lcc.getIdproduit().getNom() + " quantité : " + lcc.getQuantite() + " dans la facture : " + this.facture.getNumeroFacture(), SessionMBean.getUserAccount());
            }
            /* 420 */ this.ligneCommandeClients.remove(index);

            /* 422 */ updateTotal();
            /* 423 */ if (trouve) {
                /* 424 */ this.commandeClientFacadeLocal.edit(this.commandeClient);
            }
            /* 426 */ this.ut.commit();
            /* 427 */ JsfUtil.addSuccessMessage("Supprimé");
            /* 428 */        } catch (Exception e) {
            /* 429 */ e.printStackTrace();
            /* 430 */ JsfUtil.addErrorMessage(this.routine.localizeMessage("echec_operation"));
        }
    }

    public Double calculTotal(List<LigneCommandeClient> ligneCommandeClients) {
        /* 435 */ Double resultat = Double.valueOf(0.0D);
        /* 436 */ for (LigneCommandeClient lcc : ligneCommandeClients) {
            /* 437 */ resultat = Double.valueOf(resultat.doubleValue() + lcc.getMontant().doubleValue() * lcc.getQuantite().doubleValue());
        }
        /* 439 */ return resultat;
    }

    public void updateTotal() {
        try {
            /* 444 */ this.total = calculTotal(this.ligneCommandeClients);
            /* 445 */ this.commandeClient.setMontantHt(this.total);

            /* 447 */ if (this.commandeClient.getCalculRemise().booleanValue()) {
                /* 448 */ this.commandeClient.setMontantRemise(Double.valueOf(this.total.doubleValue() * this.commandeClient.getTauxRemise().doubleValue() / 100.0D));
            } else {
                /* 450 */ this.commandeClient.setMontantRemise(Double.valueOf(0.0D));
            }

            /* 453 */ if (this.commandeClient.getCalculTva().booleanValue()) {
                /* 454 */ this.commandeClient.setMontantTva(Double.valueOf((this.total.doubleValue() - this.commandeClient.getMontantRemise().doubleValue()) * this.commandeClient.getTauxTva().doubleValue() / 100.0D));
            } else {
                /* 456 */ this.commandeClient.setMontantTva(Double.valueOf(0.0D));
            }
            /* 458 */ this.commandeClient.setMontantTtc(Double.valueOf(this.total.doubleValue() - this.commandeClient.getMontantRemise().doubleValue() + this.commandeClient.getMontantTva().doubleValue()));
            /* 459 */        } catch (Exception e) {
            /* 460 */ e.printStackTrace();
        }
    }

    public void updateTotaux() {
        try {
            /* 466 */ this.cout_quantite = Double.valueOf(0.0D);
            /* 467 */ if (this.ligneCommandeClient.getQuantite() != null
                    && /* 468 */ this.ligneCommandeClient.getMontant() != null) {
                /* 469 */ this.cout_quantite = Double.valueOf(this.ligneCommandeClient.getMontant().doubleValue() * this.ligneCommandeClient.getQuantite().doubleValue());
            }
        } /* 472 */ catch (Exception e) {
            /* 473 */ e.printStackTrace();
            /* 474 */ this.cout_quantite = Double.valueOf(0.0D);
        }
    }

    public void updatedata() {
        try {
            /* 480 */ if (this.produit != null) {
                /* 481 */ this.lot = new Lot();
                /* 482 */ this.famille = this.produit.getIdfamille();

                /* 484 */ this.lots = this.lotFacadeLocal.findByArticle1(this.produit.getIdproduit(), this.produit.getPerissable().booleanValue(), new Date());

                /* 486 */ if (this.lots.size() == 0) {
                    /* 487 */ this.lot = null;
                    return;
                }
                /* 490 */ if (this.lots.size() == 1) {
                    /* 491 */ this.lot = this.lots.get(0);
                    /* 492 */ this.ligneCommandeClient.setMontant(this.lot.getPrixVente());
                    return;
                }
                /* 495 */ this.lot = this.lots.get(0);
                /* 496 */ this.ligneCommandeClient.setMontant(this.lot.getPrixVente());
            }
            /* 498 */        } catch (Exception e) {
            /* 499 */ e.printStackTrace();
        }
    }

    public void updatedataLot() {
        try {
            /* 505 */ if (this.lot != null) {
                /* 506 */ this.ligneCommandeClient.setMontant(this.lot.getPrixVente());
            }
            /* 508 */        } catch (Exception e) {
            /* 509 */ e.printStackTrace();
        }
    }

    public void notifyError(String message) {
        /* 514 */ this.routine.feedBack("avertissement", "/resources/tool_images/warning.jpeg", this.routine.localizeMessage(message));
        /* 515 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
    }

    public void notifySuccess() {
        /* 519 */ RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').hide()");
        /* 520 */ this.routine.feedBack("information", "/resources/tool_images/success.png", this.routine.localizeMessage("operation_reussie"));
        /* 521 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
    }

    public void notifyFail(Exception e) {
        /* 525 */ RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').hide()");
        /* 526 */ this.routine.catchException(e, this.routine.localizeMessage("echec_operation"));
        /* 527 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
    }
}


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-war.war!\WEB-INF\classes\controllers\commande_client\CommandeClientController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
