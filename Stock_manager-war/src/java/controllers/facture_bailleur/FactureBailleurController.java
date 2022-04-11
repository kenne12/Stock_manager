package controllers.facture_bailleur;

import controllers.facture_bailleur.AbstractFactureBailleurController;
import entities.Bailleur;
import entities.Commande;
import entities.FactureBailleur;
import entities.FactureBailleurArticle;
import entities.Famille;
import entities.LigneCommandeClient;
import entities.LigneFactBailleur;
import entities.Lot;
import entities.Produit;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.context.RequestContext;
import utils.Printer;
import utils.SessionMBean;
import utils.Utilitaires;

@ManagedBean
@SessionScoped
public class FactureBailleurController
        extends AbstractFactureBailleurController
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

    public void prepareCreate() {
        try {
            /*  60 */ if (!Utilitaires.isAccess(Long.valueOf(48L))) {
                /*  61 */ notifyError("acces_refuse");
                return;
            }
            /*  64 */ RequestContext.getCurrentInstance().execute("PF('CommandeCreateDialog').show()");
            /*  65 */ this.mode = "Create";
            /*  66 */ this.famille = new Famille();
            /*  67 */ this.factureBailleur = new FactureBailleur();
            /*  68 */ this.bailleur = new Bailleur();
            /*  69 */ this.factureBailleur.setCode("-");
            /*  70 */ this.factureBailleur.setPourcentageDist(Double.valueOf(0.0D));
            /*  71 */ this.factureBailleur.setPourcentageStockage(Double.valueOf(0.0D));
            /*  72 */ this.factureBailleur.setMontantStock(Double.valueOf(0.0D));
            /*  73 */ this.factureBailleur.setIdmois(this.anneeMois);
            /*  74 */ this.factureBailleur.setDateFacturation(SessionMBean.getDateOuverture());

            /*  76 */ this.ligneFactBailleurs.clear();
            /*  77 */ this.disabledSearch = false;

            /*  79 */ this.total = Double.valueOf(0.0D);
            /*  80 */        } catch (Exception e) {
            /*  81 */ this.routine.catchException(e, this.routine.localizeMessage("echec_operation"));
            /*  82 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
        }
    }

    public void prepareEdit() {
        try {
            /*  89 */ if (this.factureBailleur == null) {
                /*  90 */ notifyError("not_row_selected");

                return;
            }
            /*  94 */ if (!Utilitaires.isAccess(Long.valueOf(49L))) {
                /*  95 */ notifyError("acces_refuse");
                /*  96 */ this.factureBailleur = null;

                return;
            }
            /* 100 */ this.mode = "Edit";
            /* 101 */ this.bailleur = this.factureBailleur.getIdbailleur();
            /* 102 */ this.famille = this.factureBailleur.getIdfamille();
            /* 103 */ this.ligneFactBailleurs = this.ligneFactBailleurFacadeLocal.findByFacture(this.factureBailleur.getIdFactureBailleur());
            /* 104 */ this.total = this.factureBailleur.getMontantTotal();
            /* 105 */ this.disabledSearch = true;

            /* 107 */ RequestContext.getCurrentInstance().execute("PF('CommandeCreateDialog').show()");
            /* 108 */        } catch (Exception e) {
            /* 109 */ notifyFail(e);
        }
    }

    public void prepareview() {
        try {
            /* 115 */ if (this.factureBailleur != null) {
                /* 116 */ this.famille = this.factureBailleur.getIdfamille();
                /* 117 */ this.ligneFactBailleurs = this.ligneFactBailleurFacadeLocal.findByFacture(this.factureBailleur.getIdFactureBailleur());
                /* 118 */ if (!this.ligneFactBailleurs.isEmpty()) {
                    /* 119 */ RequestContext.getCurrentInstance().execute("PF('FactureDetailDialog').show()");
                    return;
                }
                /* 122 */ notifyError("liste_article_vide");
            } else {

                /* 125 */ notifyError("not_row_selected");
            }
            /* 127 */        } catch (Exception e) {
            /* 128 */ notifyFail(e);
        }
    }

    public void updateBailData() {
        try {
            /* 134 */ if (this.bailleur.getIdbailleur() != null) {
                /* 135 */ this.bailleur = this.bailleurFacadeLocal.find(this.bailleur.getIdbailleur());
                /* 136 */ this.factureBailleur.setPourcentageDist(this.bailleur.getPrctageDistribution());
                /* 137 */ this.factureBailleur.setPourcentageStockage(this.bailleur.getPrctageStockage());
            }
            /* 139 */        } catch (Exception e) {
            /* 140 */ e.printStackTrace();
        }
    }

    public void search() {
        try {
            /* 147 */ this.ligneFactBailleurs.clear();
            /* 148 */ this.commandes = this.commandeFacadeLocal.findByFamilleIntervalNotCount(this.famille.getIdfamille().intValue(), this.bailleur.getIdbailleur().intValue(), this.factureBailleur.getDateDebut(), this.factureBailleur.getDateFin());
            /* 149 */ List<Produit> pTemps = new ArrayList<>();

            /* 151 */ for (Commande c : this.commandes) {
                /* 152 */ if (!pTemps.contains(c.getIdproduit())) {
                    /* 153 */ pTemps.add(c.getIdproduit());
                }
            }

            /* 157 */ this.total = Double.valueOf(0.0D);
            /* 158 */ for (Produit p : pTemps) {
                /* 159 */ LigneFactBailleur l = new LigneFactBailleur();
                /* 160 */ l.setIdLigneFactBailleur(Long.valueOf(0L));
                /* 161 */ l.setIdproduit(p);
                /* 162 */ l.setPrixUnitaire(p.getPrixMaximal());

                /* 164 */ Double qte = Double.valueOf(0.0D);
                /* 165 */ for (Commande c : this.commandes) {
                    /* 166 */ if (c.getIdproduit().equals(p)) {
                        /* 167 */ qte = Double.valueOf(qte.doubleValue() + c.getQuantite().doubleValue());
                        /* 168 */ this.total = Double.valueOf(this.total.doubleValue() + p.getPrixMaximal().doubleValue() * c.getQuantite().doubleValue());
                    }
                }
                /* 171 */ l.setQuantite(qte);
                /* 172 */ this.ligneFactBailleurs.add(l);
            }

            /* 175 */ Double montant_stockage = Double.valueOf(0.0D);
            /* 176 */ List<Lot> l_bailleurs = this.lotFacadeLocal.findByIdbailleurIdfamille(this.bailleur.getIdbailleur().intValue(), this.famille.getIdfamille().intValue(), true);
            /* 177 */ for (Lot l : l_bailleurs) {
                /* 178 */ if (l.getIdproduit().getPerissable().booleanValue()) {
                    /* 179 */ if ((new Date()).before(l.getDatePeremption())) /* 180 */ {
                        montant_stockage = Double.valueOf(montant_stockage.doubleValue() + l.getPrixVente().doubleValue() * l.getQuantite().doubleValue());
                    }
                    continue;
                }
                /* 183 */ montant_stockage = Double.valueOf(montant_stockage.doubleValue() + l.getPrixVente().doubleValue() * l.getQuantite().doubleValue());
            }

            /* 187 */ this.factureBailleur.setMontantTotal(this.total);
            /* 188 */ this.factureBailleur.setMontantStock(montant_stockage);
            /* 189 */ this.factureBailleur.setMontantTaxee(Double.valueOf(this.total.doubleValue() * this.factureBailleur.getPourcentageDist().doubleValue() / 100.0D + montant_stockage.doubleValue() * this.factureBailleur.getPourcentageStockage().doubleValue() / 100.0D));

            /* 191 */ RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').hide()");
        } /* 193 */ catch (Exception e) {
            /* 194 */ notifyFail(e);
        }
    }

    public void create() {
        try {
            /* 200 */ if ("Create".equals(this.mode)) {

                /* 202 */ if (!this.ligneFactBailleurs.isEmpty()) {

                    /* 204 */ this.factureBailleur.setIdFactureBailleur(this.factureBailleurFacadeLocal.nextVal());
                    /* 205 */ this.factureBailleur.setIdmois(this.anneeMois);
                    /* 206 */ this.factureBailleur.setIdfamille(this.famille);
                    /* 207 */ this.factureBailleur.setIdbailleur(this.bailleur);

                    /* 209 */ this.ut.begin();

                    /* 211 */ this.factureBailleurFacadeLocal.create(this.factureBailleur);

                    /* 213 */ for (LigneFactBailleur lfb : this.ligneFactBailleurs) {
                        /* 214 */ lfb.setIdLigneFactBailleur(this.ligneFactBailleurFacadeLocal.nextVal());
                        /* 215 */ lfb.setIdfactureBailleur(this.factureBailleur);
                        /* 216 */ this.ligneFactBailleurFacadeLocal.create(lfb);
                    }

                    /* 219 */ this.factures.clear();

                    /* 221 */ for (Commande c : this.commandes) {
                        /* 222 */ FactureBailleurArticle f = new FactureBailleurArticle();
                        /* 223 */ f.setIdFactureBailleurArticle(this.factureBailleurArticleFacadeLocal.nextVal());
                        /* 224 */ f.setIdcommande(c);
                        /* 225 */ f.setIdfactureBailleur(this.factureBailleur);
                        /* 226 */ this.factureBailleurArticleFacadeLocal.create(f);

                        /* 228 */ c.setComptabilise(Boolean.valueOf(true));
                        /* 229 */ this.commandeFacadeLocal.edit(c);
                    }

                    /* 232 */ Utilitaires.saveOperation(this.mouchardFacadeLocal, "Enregistrement de la facture bailleur : " + this.factureBailleur.getCode() + " ; Montant -> " + this.factureBailleur.getMontantTotal(), SessionMBean.getUserAccount());

                    /* 234 */ this.ut.commit();

                    /* 236 */ this.factureBailleur = null;
                    /* 237 */ this.ligneFactBailleurs.clear();
                    /* 238 */ this.detail = this.supprimer = this.modifier = this.imprimer = Boolean.valueOf(true);

                    /* 240 */ notifySuccess();
                    /* 241 */ RequestContext.getCurrentInstance().execute("PF('CommandeCreateDialog').hide()");
                } else {
                    /* 243 */ notifyError("liste_article_vide");
                }

                /* 246 */            } else if (this.factureBailleur != null) {

                /* 248 */ this.ut.begin();

                /* 250 */ this.factureBailleurFacadeLocal.edit(this.factureBailleur);

                /* 252 */ this.ut.commit();
                /* 253 */ this.factureBailleur = null;

                /* 255 */ this.detail = this.supprimer = this.modifier = this.imprimer = Boolean.valueOf(true);

                /* 257 */ notifySuccess();
                /* 258 */ RequestContext.getCurrentInstance().execute("PF('CommandeCreateDialog').hide()");
            } else {

                /* 261 */ notifyError("not_row_selected");
            }

            /* 264 */        } catch (Exception e) {
            /* 265 */ notifyFail(e);
        }
    }

    public void delete() {
        try {
            /* 271 */ if (this.factureBailleur != null) {

                /* 273 */ if (!Utilitaires.isAccess(Long.valueOf(50L))) {
                    /* 274 */ notifyError("acces_refuse");
                    /* 275 */ this.detail = this.supprimer = this.modifier = this.imprimer = Boolean.valueOf(true);
                    /* 276 */ this.factureBailleur = null;

                    return;
                }
                /* 280 */ this.ut.begin();

                /* 282 */ List<LigneFactBailleur> temp = this.ligneFactBailleurFacadeLocal.findByFacture(this.factureBailleur.getIdFactureBailleur());

                /* 284 */ for (LigneFactBailleur lfb : temp) {
                    /* 285 */ this.ligneFactBailleurFacadeLocal.remove(lfb);
                }

                /* 288 */ this.factures.clear();
                /* 289 */ List<FactureBailleurArticle> temp1 = this.factureBailleurArticleFacadeLocal.findByFacture(this.factureBailleur.getIdFactureBailleur());
                /* 290 */ for (FactureBailleurArticle fba : temp1) {
                    /* 291 */ this.factureBailleurArticleFacadeLocal.remove(fba);

                    /* 293 */ fba.getIdcommande().setComptabilise(Boolean.valueOf(false));
                    /* 294 */ this.commandeFacadeLocal.edit(fba.getIdcommande());
                }
                /* 296 */ this.factureBailleurFacadeLocal.remove(this.factureBailleur);

                /* 298 */ this.ut.commit();

                /* 300 */ Utilitaires.saveOperation(this.mouchardFacadeLocal, "Suppresion de la facture bailleur : " + this.factureBailleur.getCode() + " Montant : " + this.factureBailleur.getMontantTotal(), SessionMBean.getUserAccount());
                /* 301 */ this.factureBailleur = null;
                /* 302 */ this.detail = this.supprimer = this.modifier = this.imprimer = Boolean.valueOf(true);
                /* 303 */ notifySuccess();
            } else {
                /* 305 */ notifyError("not_row_selected");
            }
            /* 307 */        } catch (Exception e) {
            /* 308 */ notifyFail(e);
        }
    }

    public void initPrinter(FactureBailleur f) {
        /* 313 */ this.factureBailleur = f;
        /* 314 */ print();
    }

    public void initEdit(FactureBailleur f) {
        /* 318 */ this.factureBailleur = f;
        /* 319 */ prepareEdit();
    }

    public void initView(FactureBailleur f) {
        /* 323 */ this.factureBailleur = f;
        /* 324 */ prepareview();
    }

    public void initDelete(FactureBailleur f) {
        /* 328 */ this.factureBailleur = f;
        /* 329 */ delete();
    }

    public String print() {
        try {
            if (!Utilitaires.isAccess(Long.valueOf(51L))) {
                /* 335 */ notifyError("acces_refuse");
                /* 336 */ this.factureBailleur = null;
                /* 337 */ return "";
            }

            if (this.factureBailleur != null) {
                HashMap<Object, Object> map = new HashMap<>();
                map.put("idfacture", this.factureBailleur.getIdFactureBailleur());
                //Printer.print("/reports/report_stock/facture.jasper", map);
            } else {
                notifyError("not_row_selected");
            }
        } catch (Exception e) {
            notifyFail(e);
        }
        /* 350 */ return "/operation/facture-bailleur/facture-bailleur.xhtml?faces-redirect=true";
    }

    public Double calculTotal(List<LigneCommandeClient> ligneCommandeClients) {
        /* 354 */ Double resultat = Double.valueOf(0.0D);
        /* 355 */ for (LigneCommandeClient lcc : ligneCommandeClients) {
            /* 356 */ resultat = Double.valueOf(resultat.doubleValue() + lcc.getMontant().doubleValue() * lcc.getQuantite().doubleValue());
        }
        /* 358 */ return resultat;
    }

    public void notifyError(String message) {
        /* 362 */ this.routine.feedBack("avertissement", "/resources/tool_images/warning.jpeg", this.routine.localizeMessage(message));
        /* 363 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
    }

    public void notifySuccess() {
        /* 367 */ RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').hide()");
        /* 368 */ this.routine.feedBack("information", "/resources/tool_images/success.png", this.routine.localizeMessage("operation_reussie"));
        /* 369 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
    }

    public void notifyFail(Exception e) {
        /* 373 */ RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').hide()");
        /* 374 */ this.routine.catchException(e, this.routine.localizeMessage("echec_operation"));
        /* 375 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
    }
}
