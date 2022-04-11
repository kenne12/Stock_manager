package utils;

import com.google.common.io.Files;
import entities.Annee;
import entities.AnneeMois;
import entities.Facture;
import entities.Journee;
import entities.Menu;
import entities.Mouchard;
import entities.Privilege;
import entities.Utilisateur;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.joda.time.DateTime;
import org.primefaces.context.RequestContext;
import sessions.UtilisateurFacadeLocal;

@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean extends AbstractLoginBean implements Serializable {

    @EJB
    private UtilisateurFacadeLocal utilisateurFacadeLocal;
    private Utilisateur utilisateur = new Utilisateur();

    private Utilisateur utilisateurConnected;
    String sc = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();

    @PostConstruct
    public void init() {
        this.utilisateur = new Utilisateur();
        this.utilisateur.setTheme("bootstrap");
    }

    public void login() {
        try {
            DateTime dateTime = new DateTime("2019-12-10");
            int licence = Utilitaires.duration(new Date(), dateTime.toDate());

            this.utilisateur = this.utilisateurFacadeLocal.login(this.utilisateur.getLogin(), (new ShaHash()).hash(this.utilisateur.getPassword()));
            if (this.utilisateur != null) {

                if (this.utilisateur.getActif()) {

                    HttpSession session = SessionMBean.getSession();
                    session.setAttribute("compte", this.utilisateur);
                    session.setAttribute("session", (false));

                    this.param = this.parametrageFacadeLocal.findAll().get(0);

                    session.setAttribute("parametre", this.param);

                    List<Privilege> privilegesTemp = this.privilegeFacadeLocal.findByUser(this.utilisateur.getIdutilisateur());
                    List<Long> accesses = new ArrayList<>();
                    List<String> access = new ArrayList<>();

                    for (Privilege p : privilegesTemp) {
                        accesses.add(Long.valueOf(p.getIdmenu().getIdmenu()));
                        String[] menus = p.getIdmenu().getRessource().split(";");
                        for (String temp : menus) {
                            if (!access.contains(temp)) {
                                access.add(temp);
                            }
                        }
                    }

                    session.setAttribute("accesses", accesses);
                    session.setAttribute("access", access);

                    this.annees = this.anneeFacadeLocal.findByEtat(true);

                    this.journee = new Journee();
                    this.journee.setIdutilisateurFermetture((0));

                    if (this.annees.size() > 1) {
                        int count = 0;
                        for (Annee a : this.annees) {
                            if (a.getEtat()) {
                                count++;
                            }
                        }

                        if (count > 1) {
                            this.showSessionPanel = true;
                            FacesContext.getCurrentInstance().getExternalContext().redirect(this.sc + "/index.html");
                        } else {
                            for (Annee a : this.annees) {
                                if (a.getEtat()) {
                                    this.annee = a;
                                    this.anneeMoises = this.anneeMoisFacadeLocal.findByAnnee(this.annee);
                                    break;
                                }
                            }
                            filterDate(new Date());
                            this.showSessionPanel = true;
                            this.connected = false;
                            this.connectionVisible = "visible";
                            FacesContext.getCurrentInstance().getExternalContext().redirect(this.sc + "/index.html");
                        }
                    } else {
                        this.showSessionPanel = true;
                        this.annee = this.annees.get(0);
                        this.connected = false;
                        this.connectionVisible = "hidden";
                        this.anneeMoises = this.anneeMoisFacadeLocal.findByAnnee(this.annee);
                        FacesContext.getCurrentInstance().getExternalContext().redirect(this.sc + "/index.html");
                        filterDate(new Date());
                    }
                } else {
                    JsfUtil.addWarningMessage("Compte bloqué ! contactez l'administrateur");
                }
            } else {
                this.utilisateur = new Utilisateur();
                JsfUtil.addErrorMessage("Login ou mot de passe incorrect");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            this.utilisateur = new Utilisateur();
            JsfUtil.addErrorMessage("Echec système");
        }
    }

    private void filterDate(Date date) {
        for (AnneeMois a : this.anneeMoises) {
            try {
                if ((a.getDateDebut().equals(date) || a.getDateDebut().before(date)) && (a.getDateFin().equals(date) || a.getDateFin().after(date))) {
                    this.anneeMois = a;
                    break;
                }
            } catch (Exception exception) {
            }
        }
    }

    public void initSession() {
        try {
            if (this.annee.getIdannee() != 0) {

                if (this.anneeMois.getIdAnneeMois() != 0) {

                    if (this.date.equals(this.anneeMois.getDateDebut()) || (this.date.after(this.anneeMois.getDateDebut()) && this.date.equals(this.anneeMois.getDateFin())) || this.date.before(this.anneeMois.getDateFin())) {

                        HttpSession session = SessionMBean.getSession();

                        this.annee = this.anneeFacadeLocal.find(this.annee.getIdannee());
                        session.setAttribute("annee", this.annee);
                        session.setAttribute("mois", this.anneeMois);
                        session.setAttribute("date", this.date);

                        List<String> allAccess = new ArrayList<>();

                        for (Menu m : this.menuFacadeLocal.findAll()) {
                            String[] menus = m.getRessource().split(";");
                            for (String temp : menus) {
                                if (!allAccess.contains(temp)) {
                                    allAccess.add(temp);
                                }
                            }
                        }

                        session.setAttribute("allAccess", allAccess);

                        List<Journee> journees = this.journeeFacadeLocal.find(this.date);
                        if (!journees.isEmpty()) {
                            this.journee = journees.get(0);
                        } else {
                            journee.setIdjournee(this.journeeFacadeLocal.nextVal());
                            this.journee.setDateJour(this.date);
                            this.journee.setHeureOuverture(new Date());
                            this.journee.setIdutilisateurOuverture(this.utilisateur);
                            this.journee.setIdutilisateurFermetture((0));
                            this.journee.setFermetture((false));
                            this.journee.setMontant((0.0D));
                            this.journee.setOuverte((true));
                            this.journee.setIdmois(this.anneeMois);
                            journee.setQuantiteEntree(0);
                            journee.setQuantiteSortie(0);
                            journee.setMontantEntree(0);
                            this.journeeFacadeLocal.create(this.journee);
                        }

                        session.setAttribute("journee", this.journee);

                        Utilitaires.saveOperation(this.mouchardFacadeLocal, "Connexion", this.utilisateur);

                        File file1 = new File(Utilitaires.path + "/photos/" + this.param.getLogo());
                        if (!file1.exists()) {
                            File file2 = new File(this.param.getRepertoireLogo() + "" + this.param.getLogo());
                            if (file2.exists()) {
                                Files.copy(file2, file1);
                            }
                        }
                        this.showSessionPanel = false;
                    } else {
                        JsfUtil.addErrorMessage("Choisir une date valable dans le mois choisi");
                    }
                } else {
                    JsfUtil.addErrorMessage("Veuillez selectionner une année");
                }
            } else {
                JsfUtil.addErrorMessage("Veuillez selectionner une année");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addErrorMessage("Echec");
        }
    }

    public void closeSession() {
        try {
            Double total = (0.0D);
            for (Facture f : this.factures) {
                total += f.getMontant();
            }
            this.journee.setHeureFermetture(new Date());
            this.journee.setIdutilisateurFermetture(SessionMBean.getUserAccount().getIdutilisateur());
            this.journee.setFermetture((true));
            this.journee.setMontant(total);
            this.journeeFacadeLocal.edit(this.journee);
            this.routine.feedBack("information", "/resources/tool_images/success.png", this.routine.localizeMessage("operation_reussie"));
            RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
        } catch (Exception e) {
            this.routine.catchException(e, this.routine.localizeMessage("echec_operation"));
            RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
        }
    }

    public void updateFermetture() {
        try {
            if (Utilitaires.isAccess((40L))) {
                RequestContext.getCurrentInstance().execute("PF('FermettureCreerDialog').show()");
            } else {
                this.routine.feedBack("information", "/resources/tool_images/error.png", this.routine.localizeMessage("acces_refuse"));
                RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
                return;
            }
            this.factures = this.factureFacadeLocal.findAllDate(this.date);
        } catch (Exception e) {
            this.routine.catchException(e, this.routine.localizeMessage("echec_operation"));
            RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
        }
    }

    public void deconnexion() {
        this.traceur = new Mouchard();
        Utilisateur user = SessionMBean.getUserAccount();
        Utilitaires.saveOperation(this.mouchardFacadeLocal, "Déconnexion", user);
        try {
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            String sc = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();

            UtilitaireSession.getInstance().setuser(null);
            FacesContext.getCurrentInstance().getExternalContext().redirect(sc + "/login.html");
        } catch (IOException ex) {
            Logger.getLogger(utils.LoginBean.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
    }

    public void change_session() {
        try {
            this.showSessionPanel = true;
            String sc = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
            FacesContext.getCurrentInstance().getExternalContext().redirect(sc + "/index.html?faces-redirect=true");
        } catch (IOException ex) {
            Logger.getLogger(utils.LoginBean.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
    }

    public void update_theme() {
        try {
            this.utilisateurFacadeLocal.edit(this.utilisateur);
        } catch (Exception e) {
            e.printStackTrace();
            this.utilisateur.setTheme("bootstrap");
            this.utilisateurFacadeLocal.edit(this.utilisateur);
        }
    }

    public void hibbernate() {
        try {
            this.showHibernatePanel = true;
            this.hibernatePassword = "";
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void unHibbernate() {
        try {
            if ((new ShaHash()).hash(this.hibernatePassword).equals(SessionMBean.getUserAccount().getPassword())) {
                this.showHibernatePanel = false;
            } else {
                this.showHibernatePanel = true;
                JsfUtil.addErrorMessage(this.routine.localizeMessage("mot_passe_incorrect"));
            }
        } catch (Exception e) {
            e.getMessage();
            JsfUtil.addErrorMessage(this.routine.localizeMessage("erreur"));
        }
    }

    public Utilisateur getUserconnected() {
        this.utilisateurConnected = UtilitaireSession.getInstance().getuser();
        String sc = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
        if (this.utilisateurConnected == null) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect(sc + "/login.html");
            } catch (IOException ex) {
                Logger.getLogger(utils.LoginBean.class.getName()).log(Level.SEVERE, (String) null, ex);
            }
            System.out.println("Uitlisateur déconnecté +++++++++++++++++++ ");
        }
        return this.utilisateurConnected;
    }

    public void setPriv() {
        watcher();
    }

    public static void watcher() {
        try {
            if (!FacesContext.getCurrentInstance().getExternalContext().getSessionMap().containsKey("compte")) {
                String sc = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
                FacesContext.getCurrentInstance().getExternalContext().redirect(sc + "/login.html");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateMois() {
        try {
            if (this.annee.getIdannee() == 0) {
                JsfUtil.addErrorMessage("Selectionner une année");
            } else {
                this.anneeMoises = this.anneeMoisFacadeLocal.findByAnnee(this.annee);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addErrorMessage("Echec");
        }
    }

    public void updateCalendar() {
        try {
            if (this.anneeMois.getIdAnneeMois() == 0) {
                JsfUtil.addErrorMessage("Echec : sélectionner une année");
            } else {
                this.anneeMois = this.anneeMoisFacadeLocal.find(this.anneeMois.getIdAnneeMois());
            }
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addErrorMessage("Echec");
        }
    }

    public String calculTotal() {
        if (this.factures.isEmpty()) {
            return "";
        }
        Double resultat = 0d;
        for (Facture f : this.factures) {
            resultat += f.getMontant();
        }
        return JsfUtil.formaterStringMoney(resultat);
    }

    public String calculMontantRegle() {
        if (this.factures.isEmpty()) {
            return "";
        }
        Double resultat = 0d;
        for (Facture f : this.factures) {
            resultat = (resultat + f.getMontantPaye());
        }
        return JsfUtil.formaterStringMoney(resultat);
    }

    public String calculMontantReste() {
        if (this.factures.isEmpty()) {
            return "";
        }
        Double resultat = (0.0D);
        for (Facture f : this.factures) {
            resultat = (resultat + f.getReste());
        }
        return JsfUtil.formaterStringMoney(resultat);
    }

    public String findPersonnelFermetture() {
        String result = "";
        try {
            if (this.journee.getIdutilisateurFermetture() != 0) {
                Utilisateur u = this.utilisateurFacadeLocal.find(this.journee.getIdutilisateurFermetture());
                if (u != null && u.getIdpersonnel() != null) {
                    result = "" + u.getIdpersonnel().getNom() + " " + u.getIdpersonnel().getPrenom();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = "";
        }
        return result;
    }

    public void updateCompte() {
        try {
            if (!this.utilisateur.getPassword().equals("") || !this.utilisateur.getPassword().equals(null)) {
                if (this.utilisateur.getPassword().equals(this.confirmPassword)) {
                    /* 454 */ this.utilisateur.setPassword((new ShaHash()).hash(this.confirmPassword));
                    /* 455 */ this.utilisateurFacadeLocal.edit(this.utilisateur);
                    /* 456 */ this.confirmPassword = "";
                    /* 457 */ RequestContext.getCurrentInstance().execute("PF('Modifier_compteDialog').hide()");
                    /* 458 */ this.routine.feedBack("information", "/resources/tool_images/success.png", this.routine.localizeMessage("operation_reussie"));
                    /* 459 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog').show()");
                } else {
                    /* 461 */ this.utilisateur = this.utilisateurFacadeLocal.find(this.utilisateur.getIdutilisateur());
                    /* 462 */ this.confirmPassword = "";
                    /* 463 */ this.routine.feedBack("avertissement", "/resources/tool_images/error.png", this.routine.localizeMessage("mot_de_passe_non_identic"));
                    /* 464 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog').show()");
                }
            } else {
                /* 467 */ this.utilisateur = this.utilisateurFacadeLocal.find(this.utilisateur.getIdutilisateur());
                /* 468 */ this.confirmPassword = "";
                /* 469 */ this.routine.feedBack("avertissement", "/resources/tool_images/error.png", this.routine.localizeMessage("saisir_mot_de_passe"));
                /* 470 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog').show()");
            }
        } catch (Exception e) {
            /* 473 */ this.utilisateur = this.utilisateurFacadeLocal.find(this.utilisateur.getIdutilisateur());
            /* 474 */ this.confirmPassword = "";
            /* 475 */ this.routine.catchException(e, this.routine.localizeMessage("echec_operation"));
            /* 476 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog').show()");
        }
    }

    public Utilisateur getUtilisateur() {
        /* 481 */ return this.utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        /* 485 */ this.utilisateur = utilisateur;
    }

    public Object getUser() {
        /* 489 */ return this.utilisateur;
    }

    public void setUser(Object user) {
        /* 493 */ this.utilisateur = (Utilisateur) user;
    }
}
