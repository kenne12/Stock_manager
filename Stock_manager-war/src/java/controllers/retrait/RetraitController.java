package controllers.retrait;

import controllers.retrait.AbstractRetraitController;
import entities.Caisse;
import entities.Client;
import entities.Privilege;
import entities.Retrait;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import utils.JsfUtil;
import utils.SessionMBean;
import utils.Utilitaires;

@ManagedBean
@ViewScoped
public class RetraitController
        extends AbstractRetraitController {

    @PostConstruct
    private void init() {
        /*  36 */ this.client = new Client();
        /*  37 */ this.retrait = new Retrait();
    }

    public void prepareCreate() {
        /*  41 */ this.client = new Client();
        /*  42 */ this.retrait = new Retrait();
        /*  43 */ this.retrait.setDate(new Date());
        /*  44 */ this.mode = "Create";
        /*  45 */ this.showClient = Boolean.valueOf(false);
        try {
            /*  47 */ Privilege p = this.privilegeFacadeLocal.findByUser(SessionMBean.getUserAccount().getIdutilisateur().intValue(), 1);
            /*  48 */ if (p != null) {
                /*  49 */ this.showRetraitCreateDialog = Boolean.valueOf(true);
            } else {
                /*  51 */ p = new Privilege();
                /*  52 */ p = this.privilegeFacadeLocal.findByUser(SessionMBean.getUserAccount().getIdutilisateur().intValue(), 13);
                /*  53 */ if (p != null) {
                    /*  54 */ this.showRetraitCreateDialog = Boolean.valueOf(true);
                } else {
                    /*  56 */ this.showRetraitCreateDialog = Boolean.valueOf(false);
                    /*  57 */ JsfUtil.addErrorMessage("Vous n 'avez pas le privilege d'enregistrer un retrait");
                }
            }
            /*  60 */        } catch (Exception e) {
            /*  61 */ e.printStackTrace();
        }
    }

    public void prepareEdit() {
        /*  66 */ this.mode = "Edit";
        /*  67 */ this.showClient = Boolean.valueOf(true);

        /*  69 */ if (this.retrait != null) {
            /*  70 */ this.retrait1 = this.retrait.getMontant();
            /*  71 */ this.commission = this.retrait.getCommission();
        }

        try {
            /*  75 */ Privilege p = this.privilegeFacadeLocal.findByUser(SessionMBean.getUserAccount().getIdutilisateur().intValue(), 1);
            /*  76 */ if (p != null) {
                /*  77 */ this.showRetraitCreateDialog = Boolean.valueOf(true);
            } else {
                /*  79 */ p = new Privilege();
                /*  80 */ p = this.privilegeFacadeLocal.findByUser(SessionMBean.getUserAccount().getIdutilisateur().intValue(), 14);
                /*  81 */ if (p != null) {
                    /*  82 */ this.showRetraitCreateDialog = Boolean.valueOf(true);
                } else {
                    /*  84 */ this.showRetraitCreateDialog = Boolean.valueOf(false);
                    /*  85 */ JsfUtil.addErrorMessage("Vous n 'avez pas le privilege de modifier un retrait");
                }
            }
            /*  88 */        } catch (Exception e) {
            /*  89 */ e.printStackTrace();
        }
    }

    public void updateSolde1() {
        /*  96 */ if (this.mode == "Create") {
            /*  97 */ if (this.retrait.getIdclient() != null) {
                /*  98 */ if (this.retrait1 != null) {
                    /*  99 */ Client c = this.clientFacadeLocal.find(this.retrait.getIdclient().getIdclient());
                    /* 100 */ if (this.retrait1.intValue() >= 500) {
                        /* 101 */ if (this.retrait1.intValue() >= 500 && this.retrait1.intValue() <= 20000) {
                            /* 102 */ this.commission = Integer.valueOf(500);
                            /* 103 */ int solde = c.getSolde().intValue() - this.retrait1.intValue() - this.commission.intValue();
                            /* 104 */ this.retrait.getIdclient().setSolde(Integer.valueOf(solde));
                            /* 105 */                        } else if (this.retrait1.intValue() >= 20001 && this.retrait1.intValue() <= 40000) {
                            /* 106 */ this.commission = Integer.valueOf(1000);
                            /* 107 */ int solde = c.getSolde().intValue() - this.retrait1.intValue() - this.commission.intValue();
                            /* 108 */ this.retrait.getIdclient().setSolde(Integer.valueOf(solde));
                            /* 109 */                        } else if (this.retrait1.intValue() >= 40001 && this.retrait1.intValue() <= 60000) {
                            /* 110 */ this.commission = Integer.valueOf(1500);
                            /* 111 */ int solde = c.getSolde().intValue() - this.retrait1.intValue() - this.commission.intValue();
                            /* 112 */ this.retrait.getIdclient().setSolde(Integer.valueOf(solde));
                            /* 113 */                        } else if (this.retrait1.intValue() >= 60001 && this.retrait1.intValue() <= 100000) {
                            /* 114 */ this.commission = Integer.valueOf(2000);
                            /* 115 */ int solde = c.getSolde().intValue() - this.retrait1.intValue() - this.commission.intValue();
                            /* 116 */ this.retrait.getIdclient().setSolde(Integer.valueOf(solde));
                            /* 117 */                        } else if (this.retrait1.intValue() >= 100001 && this.retrait1.intValue() <= 150000) {
                            /* 118 */ this.commission = Integer.valueOf(2500);
                            /* 119 */ int solde = c.getSolde().intValue() - this.retrait1.intValue() - this.commission.intValue();
                            /* 120 */ this.retrait.getIdclient().setSolde(Integer.valueOf(solde));
                            /* 121 */                        } else if (this.retrait1.intValue() >= 150001 && this.retrait1.intValue() <= 200000) {
                            /* 122 */ this.commission = Integer.valueOf(3000);
                            /* 123 */ int solde = c.getSolde().intValue() - this.retrait1.intValue() - this.commission.intValue();
                            /* 124 */ this.retrait.getIdclient().setSolde(Integer.valueOf(solde));
                            /* 125 */                        } else if (this.retrait1.intValue() >= 200001 && this.retrait1.intValue() <= 250000) {
                            /* 126 */ this.commission = Integer.valueOf(3500);
                            /* 127 */ int solde = c.getSolde().intValue() - this.retrait1.intValue() - this.commission.intValue();
                            /* 128 */ this.retrait.getIdclient().setSolde(Integer.valueOf(solde));
                            /* 129 */                        } else if (this.retrait1.intValue() >= 250001 && this.retrait1.intValue() <= 300000) {
                            /* 130 */ this.commission = Integer.valueOf(4000);
                            /* 131 */ int solde = c.getSolde().intValue() - this.retrait1.intValue() - this.commission.intValue();
                            /* 132 */ this.retrait.getIdclient().setSolde(Integer.valueOf(solde));
                            /* 133 */                        } else if (this.retrait1.intValue() >= 300001 && this.retrait1.intValue() <= 350000) {
                            /* 134 */ this.commission = Integer.valueOf(4500);
                            /* 135 */ int solde = c.getSolde().intValue() - this.retrait1.intValue() - this.commission.intValue();
                            /* 136 */ this.retrait.getIdclient().setSolde(Integer.valueOf(solde));
                            /* 137 */                        } else if (this.retrait1.intValue() >= 350001 && this.retrait1.intValue() <= 500000) {
                            /* 138 */ this.commission = Integer.valueOf(5000);
                            /* 139 */ int solde = c.getSolde().intValue() - this.retrait1.intValue() - this.commission.intValue();
                            /* 140 */ this.retrait.getIdclient().setSolde(Integer.valueOf(solde));
                            /* 141 */                        } else if (this.retrait1.intValue() >= 500001 && this.retrait1.intValue() <= 700000) {
                            /* 142 */ this.commission = Integer.valueOf(6000);
                            /* 143 */ int solde = c.getSolde().intValue() - this.retrait1.intValue() - this.commission.intValue();
                            /* 144 */ this.retrait.getIdclient().setSolde(Integer.valueOf(solde));
                            /* 145 */                        } else if (this.retrait1.intValue() >= 700001 && this.retrait1.intValue() <= 800000) {
                            /* 146 */ this.commission = Integer.valueOf(7000);
                            /* 147 */ int solde = c.getSolde().intValue() - this.retrait1.intValue() - this.commission.intValue();
                            /* 148 */ this.retrait.getIdclient().setSolde(Integer.valueOf(solde));
                            /* 149 */                        } else if (this.retrait1.intValue() >= 800001 && this.retrait1.intValue() <= 900000) {
                            /* 150 */ this.commission = Integer.valueOf(8000);
                            /* 151 */ int solde = c.getSolde().intValue() - this.retrait1.intValue() - this.commission.intValue();
                            /* 152 */ this.retrait.getIdclient().setSolde(Integer.valueOf(solde));
                            /* 153 */                        } else if (this.retrait1.intValue() >= 900001 && this.retrait1.intValue() <= 1000000) {
                            /* 154 */ this.commission = Integer.valueOf(9000);
                            /* 155 */ int solde = c.getSolde().intValue() - this.retrait1.intValue() - this.commission.intValue();
                            /* 156 */ this.retrait.getIdclient().setSolde(Integer.valueOf(solde));
                        } else {
                            /* 158 */ this.commission = Integer.valueOf(this.retrait1.intValue() / 100);
                            /* 159 */ int solde = c.getSolde().intValue() - this.retrait1.intValue() - this.commission.intValue();
                            /* 160 */ this.retrait.getIdclient().setSolde(Integer.valueOf(solde));
                        }
                    } else {
                        /* 163 */ Client c1 = this.clientFacadeLocal.find(this.retrait.getIdclient().getIdclient());
                        /* 164 */ this.retrait.getIdclient().setSolde(c1.getSolde());
                        /* 165 */ this.commission = Integer.valueOf(0);
                    }
                } else {
                    /* 168 */ Client c = this.clientFacadeLocal.find(this.retrait.getIdclient().getIdclient());
                    /* 169 */ this.retrait.getIdclient().setSolde(c.getSolde());
                }

            }
            /* 173 */        } else if (this.retrait.getIdclient() != null) {
            /* 174 */ if (this.retrait1 != null) {

                /* 176 */ Client c = this.clientFacadeLocal.find(this.retrait.getIdclient().getIdclient());
                /* 177 */ if (this.retrait1.intValue() >= 500) {
                    /* 178 */ if (this.retrait1.intValue() >= 500 && this.retrait1.intValue() <= 20000) {
                        /* 179 */ this.commission = Integer.valueOf(500);
                        /* 180 */ int solde = c.getSolde().intValue() + this.retrait.getMontant().intValue() + this.retrait.getCommission().intValue() - this.retrait1.intValue() - this.commission.intValue();
                        /* 181 */ this.retrait.getIdclient().setSolde(Integer.valueOf(solde));
                        /* 182 */                    } else if (this.retrait1.intValue() >= 20001 && this.retrait1.intValue() <= 40000) {
                        /* 183 */ this.commission = Integer.valueOf(1000);
                        /* 184 */ int solde = c.getSolde().intValue() + this.retrait.getMontant().intValue() + this.retrait.getCommission().intValue() - this.retrait1.intValue() - this.commission.intValue();
                        /* 185 */ this.retrait.getIdclient().setSolde(Integer.valueOf(solde));
                        /* 186 */                    } else if (this.retrait1.intValue() >= 40001 && this.retrait1.intValue() <= 60000) {
                        /* 187 */ this.commission = Integer.valueOf(1500);
                        /* 188 */ int solde = c.getSolde().intValue() + this.retrait.getMontant().intValue() + this.retrait.getCommission().intValue() - this.retrait1.intValue() - this.commission.intValue();
                        /* 189 */ this.retrait.getIdclient().setSolde(Integer.valueOf(solde));
                        /* 190 */                    } else if (this.retrait1.intValue() >= 60001 && this.retrait1.intValue() <= 100000) {
                        /* 191 */ this.commission = Integer.valueOf(2000);
                        /* 192 */ int solde = c.getSolde().intValue() + this.retrait.getMontant().intValue() + this.retrait.getCommission().intValue() - this.retrait1.intValue() - this.commission.intValue();
                        /* 193 */ this.retrait.getIdclient().setSolde(Integer.valueOf(solde));
                        /* 194 */                    } else if (this.retrait1.intValue() >= 100001 && this.retrait1.intValue() <= 150000) {
                        /* 195 */ this.commission = Integer.valueOf(2500);
                        /* 196 */ int solde = c.getSolde().intValue() + this.retrait.getMontant().intValue() + this.retrait.getCommission().intValue() - this.retrait1.intValue() - this.commission.intValue();
                        /* 197 */ this.retrait.getIdclient().setSolde(Integer.valueOf(solde));
                        /* 198 */                    } else if (this.retrait1.intValue() >= 150001 && this.retrait1.intValue() <= 200000) {
                        /* 199 */ this.commission = Integer.valueOf(3000);
                        /* 200 */ int solde = c.getSolde().intValue() + this.retrait.getMontant().intValue() + this.retrait.getCommission().intValue() - this.retrait1.intValue() - this.commission.intValue();
                        /* 201 */ this.retrait.getIdclient().setSolde(Integer.valueOf(solde));
                        /* 202 */                    } else if (this.retrait1.intValue() >= 200001 && this.retrait1.intValue() <= 250000) {
                        /* 203 */ this.commission = Integer.valueOf(3500);
                        /* 204 */ int solde = c.getSolde().intValue() + this.retrait.getMontant().intValue() + this.retrait.getCommission().intValue() - this.retrait1.intValue() - this.commission.intValue();
                        /* 205 */ this.retrait.getIdclient().setSolde(Integer.valueOf(solde));
                        /* 206 */                    } else if (this.retrait1.intValue() >= 250001 && this.retrait1.intValue() <= 300000) {
                        /* 207 */ this.commission = Integer.valueOf(4000);
                        /* 208 */ int solde = c.getSolde().intValue() + this.retrait.getMontant().intValue() + this.retrait.getCommission().intValue() - this.retrait1.intValue() - this.commission.intValue();
                        /* 209 */ this.retrait.getIdclient().setSolde(Integer.valueOf(solde));
                        /* 210 */                    } else if (this.retrait1.intValue() >= 300001 && this.retrait1.intValue() <= 350000) {
                        /* 211 */ this.commission = Integer.valueOf(4500);
                        /* 212 */ int solde = c.getSolde().intValue() + this.retrait.getMontant().intValue() + this.retrait.getCommission().intValue() - this.retrait1.intValue() - this.commission.intValue();
                        /* 213 */ this.retrait.getIdclient().setSolde(Integer.valueOf(solde));
                        /* 214 */                    } else if (this.retrait1.intValue() >= 350001 && this.retrait1.intValue() <= 500000) {
                        /* 215 */ this.commission = Integer.valueOf(5000);
                        /* 216 */ int solde = c.getSolde().intValue() + this.retrait.getMontant().intValue() + this.retrait.getCommission().intValue() - this.retrait1.intValue() - this.commission.intValue();
                        /* 217 */ this.retrait.getIdclient().setSolde(Integer.valueOf(solde));
                        /* 218 */                    } else if (this.retrait1.intValue() >= 500001 && this.retrait1.intValue() <= 700000) {
                        /* 219 */ this.commission = Integer.valueOf(6000);
                        /* 220 */ int solde = c.getSolde().intValue() + this.retrait.getMontant().intValue() + this.retrait.getCommission().intValue() - this.retrait1.intValue() - this.commission.intValue();
                        /* 221 */ this.retrait.getIdclient().setSolde(Integer.valueOf(solde));
                        /* 222 */                    } else if (this.retrait1.intValue() >= 700001 && this.retrait1.intValue() <= 800000) {
                        /* 223 */ this.commission = Integer.valueOf(7000);
                        /* 224 */ int solde = c.getSolde().intValue() - this.retrait1.intValue() - this.commission.intValue();
                        /* 225 */ this.retrait.getIdclient().setSolde(Integer.valueOf(solde));
                        /* 226 */                    } else if (this.retrait1.intValue() >= 800001 && this.retrait1.intValue() <= 900000) {
                        /* 227 */ this.commission = Integer.valueOf(8000);
                        /* 228 */ int solde = c.getSolde().intValue() - this.retrait1.intValue() - this.commission.intValue();
                        /* 229 */ this.retrait.getIdclient().setSolde(Integer.valueOf(solde));
                        /* 230 */                    } else if (this.retrait1.intValue() >= 900001 && this.retrait1.intValue() <= 1000000) {
                        /* 231 */ this.commission = Integer.valueOf(9000);
                        /* 232 */ int solde = c.getSolde().intValue() - this.retrait1.intValue() - this.commission.intValue();
                        /* 233 */ this.retrait.getIdclient().setSolde(Integer.valueOf(solde));
                    } else {
                        /* 235 */ this.commission = Integer.valueOf(this.retrait1.intValue() / 100);
                        /* 236 */ int solde = c.getSolde().intValue() - this.retrait1.intValue() - this.commission.intValue();
                        /* 237 */ this.retrait.getIdclient().setSolde(Integer.valueOf(solde));
                    }
                } else {
                    /* 240 */ Client c1 = this.clientFacadeLocal.find(this.retrait.getIdclient().getIdclient());
                    /* 241 */ this.retrait.getIdclient().setSolde(c1.getSolde());
                    /* 242 */ this.commission = this.retrait.getCommission();
                }
            } else {
                /* 245 */ Client c = this.clientFacadeLocal.find(this.retrait.getIdclient().getIdclient());
                /* 246 */ this.retrait.getIdclient().setSolde(c.getSolde());
            }
        }
    }

    public void updateSolde() {
        /* 253 */ this.retrait1 = Integer.valueOf(0);
    }

    public void create() {
        try {
            /* 258 */ if (this.mode.equals("Create")) {

                /* 260 */ if (this.retrait1.intValue() >= 500) {

                    /* 262 */ Client c = this.clientFacadeLocal.find(this.retrait.getIdclient().getIdclient());

                    /* 264 */ if (this.retrait1.intValue() >= 500 && this.retrait1.intValue() <= 20000) {
                        /* 265 */ this.commission = Integer.valueOf(500);
                        /* 266 */                    } else if (this.retrait1.intValue() >= 20001 && this.retrait1.intValue() <= 40000) {
                        /* 267 */ this.commission = Integer.valueOf(1000);
                        /* 268 */                    } else if (this.retrait1.intValue() >= 40001 && this.retrait1.intValue() <= 60000) {
                        /* 269 */ this.commission = Integer.valueOf(1500);
                        /* 270 */                    } else if (this.retrait1.intValue() >= 60001 && this.retrait1.intValue() <= 100000) {
                        /* 271 */ this.commission = Integer.valueOf(2000);
                        /* 272 */                    } else if (this.retrait1.intValue() >= 100001 && this.retrait1.intValue() <= 150000) {
                        /* 273 */ this.commission = Integer.valueOf(2500);
                        /* 274 */                    } else if (this.retrait1.intValue() >= 150001 && this.retrait1.intValue() <= 200000) {
                        /* 275 */ this.commission = Integer.valueOf(3000);
                        /* 276 */                    } else if (this.retrait1.intValue() >= 200001 && this.retrait1.intValue() <= 250000) {
                        /* 277 */ this.commission = Integer.valueOf(3500);
                        /* 278 */                    } else if (this.retrait1.intValue() >= 250001 && this.retrait1.intValue() <= 300000) {
                        /* 279 */ this.commission = Integer.valueOf(4000);
                        /* 280 */                    } else if (this.retrait1.intValue() >= 300001 && this.retrait1.intValue() <= 350000) {
                        /* 281 */ this.commission = Integer.valueOf(4500);
                        /* 282 */                    } else if (this.retrait1.intValue() >= 350001 && this.retrait1.intValue() <= 500000) {
                        /* 283 */ this.commission = Integer.valueOf(5000);
                        /* 284 */                    } else if (this.retrait1.intValue() >= 500001 && this.retrait1.intValue() <= 700000) {
                        /* 285 */ this.commission = Integer.valueOf(6000);
                        /* 286 */                    } else if (this.retrait1.intValue() >= 700001 && this.retrait1.intValue() <= 800000) {
                        /* 287 */ this.commission = Integer.valueOf(7000);
                        /* 288 */                    } else if (this.retrait1.intValue() >= 800001 && this.retrait1.intValue() <= 900000) {
                        /* 289 */ this.commission = Integer.valueOf(8000);
                        /* 290 */                    } else if (this.retrait1.intValue() >= 900001 && this.retrait1.intValue() <= 1000000) {
                        /* 291 */ this.commission = Integer.valueOf(9000);
                    } else {
                        /* 293 */ this.commission = Integer.valueOf(this.retrait1.intValue() / 100);
                    }

                    /* 296 */ this.retrait.setIdretrait(this.retraitFacadeLocal.nextLongVal());
                    /* 297 */ this.retrait.setMontant(this.retrait1);
                    /* 298 */ this.retrait.setCommission(this.commission);
                    /* 299 */ this.retrait.setHeure(new Date());
                    /* 300 */ this.retraitFacadeLocal.create(this.retrait);

                    /* 302 */ Client c1 = this.clientFacadeLocal.find(this.retrait.getIdclient().getIdclient());
                    /* 303 */ c1.setSolde(Integer.valueOf(c1.getSolde().intValue() - this.retrait1.intValue() - this.commission.intValue()));
                    /* 304 */ this.clientFacadeLocal.edit(c1);

                    /* 306 */ Caisse caisse = this.caisseFacadeLocal.findAll().get(0);
                    /* 307 */ caisse.setMontant(Integer.valueOf(caisse.getMontant().intValue() - this.retrait1.intValue()));
                    /* 308 */ this.caisseFacadeLocal.edit(caisse);

                    /* 310 */ Utilitaires.saveOperation(this.mouchardFacadeLocal, "Enregistrement du retrait -> client : " + this.retrait.getIdclient().getPrenom() + " " + this.retrait.getIdclient().getNom() + " ; Montant : " + this.retrait1, SessionMBean.getUserAccount());

                    /* 312 */ this.retrait = new Retrait();
                    /* 313 */ JsfUtil.addSuccessMessage("Transaction réussie");
                } else {
                    /* 315 */ JsfUtil.addErrorMessage("Impossible de faire un retrait inferieur a 500 f");
                }

            } /* 319 */ else if (this.retrait != null) {

                /* 321 */ if (this.retrait1.intValue() >= 500) {

                    /* 323 */ if (this.retrait1.intValue() >= 500 && this.retrait1.intValue() <= 20000) {
                        /* 324 */ this.commission = Integer.valueOf(500);
                        /* 325 */                    } else if (this.retrait1.intValue() >= 20001 && this.retrait1.intValue() <= 40000) {
                        /* 326 */ this.commission = Integer.valueOf(1000);
                        /* 327 */                    } else if (this.retrait1.intValue() >= 40001 && this.retrait1.intValue() <= 60000) {
                        /* 328 */ this.commission = Integer.valueOf(1500);
                        /* 329 */                    } else if (this.retrait1.intValue() >= 60001 && this.retrait1.intValue() <= 100000) {
                        /* 330 */ this.commission = Integer.valueOf(2000);
                        /* 331 */                    } else if (this.retrait1.intValue() >= 100001 && this.retrait1.intValue() <= 150000) {
                        /* 332 */ this.commission = Integer.valueOf(2500);
                        /* 333 */                    } else if (this.retrait1.intValue() >= 150001 && this.retrait1.intValue() <= 200000) {
                        /* 334 */ this.commission = Integer.valueOf(3000);
                        /* 335 */                    } else if (this.retrait1.intValue() >= 200001 && this.retrait1.intValue() <= 250000) {
                        /* 336 */ this.commission = Integer.valueOf(3500);
                        /* 337 */                    } else if (this.retrait1.intValue() >= 250001 && this.retrait1.intValue() <= 300000) {
                        /* 338 */ this.commission = Integer.valueOf(4000);
                        /* 339 */                    } else if (this.retrait1.intValue() >= 300001 && this.retrait1.intValue() <= 350000) {
                        /* 340 */ this.commission = Integer.valueOf(4500);
                        /* 341 */                    } else if (this.retrait1.intValue() >= 350001 && this.retrait1.intValue() <= 500000) {
                        /* 342 */ this.commission = Integer.valueOf(5000);
                        /* 343 */                    } else if (this.retrait1.intValue() >= 500001 && this.retrait1.intValue() <= 700000) {
                        /* 344 */ this.commission = Integer.valueOf(6000);
                        /* 345 */                    } else if (this.retrait1.intValue() >= 700001 && this.retrait1.intValue() <= 800000) {
                        /* 346 */ this.commission = Integer.valueOf(7000);
                        /* 347 */                    } else if (this.retrait1.intValue() >= 800001 && this.retrait1.intValue() <= 900000) {
                        /* 348 */ this.commission = Integer.valueOf(8000);
                        /* 349 */                    } else if (this.retrait1.intValue() >= 900001 && this.retrait1.intValue() <= 1000000) {
                        /* 350 */ this.commission = Integer.valueOf(9000);
                    } else {
                        /* 352 */ this.commission = Integer.valueOf(this.retrait1.intValue() / 100);
                    }

                    /* 355 */ Retrait r = this.retraitFacadeLocal.find(this.retrait.getIdretrait());

                    /* 357 */ Client c = this.clientFacadeLocal.find(this.retrait.getIdclient().getIdclient());
                    /* 358 */ c.setSolde(Integer.valueOf(c.getSolde().intValue() + r.getMontant().intValue() + r.getCommission().intValue()));
                    /* 359 */ if (c.getSolde().intValue() < 0) {
                        /* 360 */ c.setSolde(Integer.valueOf(0));
                    }
                    /* 362 */ this.clientFacadeLocal.edit(c);

                    /* 364 */ Caisse caisse = this.caisseFacadeLocal.findAll().get(0);
                    /* 365 */ caisse.setMontant(Integer.valueOf(caisse.getMontant().intValue() + r.getMontant().intValue()));

                    /* 367 */ if (caisse.getMontant().intValue() < 0) {
                        /* 368 */ caisse.setMontant(Integer.valueOf(0));
                    }
                    /* 370 */ this.caisseFacadeLocal.edit(caisse);

                    /* 372 */ this.retrait.setMontant(this.retrait1);
                    /* 373 */ this.retrait.setCommission(this.commission);
                    /* 374 */ this.retraitFacadeLocal.edit(this.retrait);

                    /* 376 */ Client c1 = this.clientFacadeLocal.find(this.retrait.getIdclient().getIdclient());
                    /* 377 */ c1.setSolde(Integer.valueOf(c1.getSolde().intValue() - this.retrait1.intValue() - this.commission.intValue()));
                    /* 378 */ this.clientFacadeLocal.edit(c1);

                    /* 380 */ Caisse caisse1 = this.caisseFacadeLocal.findAll().get(0);
                    /* 381 */ caisse1.setMontant(Integer.valueOf(caisse1.getMontant().intValue() - this.retrait1.intValue()));
                    /* 382 */ this.caisseFacadeLocal.edit(caisse1);

                    /* 384 */ Utilitaires.saveOperation(this.mouchardFacadeLocal, "Modification du retrait -> client : " + this.retrait.getIdclient().getPrenom() + " " + this.retrait.getIdclient().getNom() + " Ancien montant : " + r.getMontant() + " ; Nouveau Montant : " + this.retrait1, SessionMBean.getUserAccount());

                    /* 386 */ JsfUtil.addSuccessMessage("Opération réussie");
                } else {
                    /* 388 */ JsfUtil.addErrorMessage("Le montant ne peut etre inferieurà 500 f");
                }
            } else {

                /* 392 */ JsfUtil.addErrorMessage("Aucun retrait selectionné");
            }

            /* 395 */        } catch (Exception e) {
            /* 396 */ e.printStackTrace();
        }
    }

    public void edit() {
    }

    public void delete() {
        try {
            /* 406 */ if (this.retrait != null) {

                try {
                    /* 409 */ Privilege p = this.privilegeFacadeLocal.findByUser(SessionMBean.getUserAccount().getIdutilisateur().intValue(), 1);
                    /* 410 */ if (p != null) {
                        /* 411 */ this.showRetraitDeleteDialog = Boolean.valueOf(true);
                    } else {
                        /* 413 */ p = new Privilege();
                        /* 414 */ p = this.privilegeFacadeLocal.findByUser(SessionMBean.getUserAccount().getIdutilisateur().intValue(), 15);
                        /* 415 */ if (p != null) {
                            /* 416 */ this.showRetraitDeleteDialog = Boolean.valueOf(true);
                        } else {
                            /* 418 */ this.showRetraitDeleteDialog = Boolean.valueOf(false);
                            /* 419 */ JsfUtil.addErrorMessage("Vous n 'avez pas le privilege de supprimer ce retrait");
                            return;
                        }
                    }
                    /* 423 */                } catch (Exception e) {
                    /* 424 */ e.printStackTrace();
                }

                /* 427 */ this.retraitFacadeLocal.remove(this.retrait);

                /* 429 */ Client c = this.clientFacadeLocal.find(this.retrait.getIdclient().getIdclient());
                /* 430 */ c.setSolde(Integer.valueOf(c.getSolde().intValue() + this.retrait.getMontant().intValue()));
                /* 431 */ this.clientFacadeLocal.edit(c);

                /* 433 */ Caisse caisse = this.caisseFacadeLocal.findAll().get(0);
                /* 434 */ caisse.setMontant(Integer.valueOf(caisse.getMontant().intValue() + this.retrait.getMontant().intValue()));
                /* 435 */ this.caisseFacadeLocal.edit(caisse);

                /* 437 */ Utilitaires.saveOperation(this.mouchardFacadeLocal, "Suppression du retrait -> client : " + this.retrait.getIdclient().getPrenom() + " " + this.retrait.getIdclient().getNom() + " ; Montant : " + this.retrait.getMontant(), SessionMBean.getUserAccount());

                /* 439 */ JsfUtil.addSuccessMessage("Operation réussie");
            } else {
                /* 441 */ JsfUtil.addErrorMessage("Aucun client selectionnée");
            }
            /* 443 */        } catch (Exception e) {
            /* 444 */ e.printStackTrace();
        }
    }

    public void print() {
        /* 449 */ Privilege p = this.privilegeFacadeLocal.findByUser(SessionMBean.getUserAccount().getIdutilisateur().intValue(), 1);
        /* 450 */ if (p != null) {
            /* 451 */ this.showRetraitPrintDialog = Boolean.valueOf(true);
        } else {
            /* 453 */ p = new Privilege();
            /* 454 */ p = this.privilegeFacadeLocal.findByUser(SessionMBean.getUserAccount().getIdutilisateur().intValue(), 12);
            /* 455 */ if (p != null) {
                /* 456 */ this.showRetraitPrintDialog = Boolean.valueOf(true);
            } else {
                /* 458 */ this.showRetraitPrintDialog = Boolean.valueOf(false);
                /* 459 */ JsfUtil.addErrorMessage("Vous n 'avez pas le privilege d'imprimer le rapport des retrait");
                return;
            }
        }
    }
}
