package controllers.recette;

import entities.Facture;
import entities.Journee;
import entities.Stock;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
import utils.JsfUtil;
import utils.PrintUtils;
import utils.SessionMBean;
import utils.Utilitaires;

@ManagedBean
@ViewScoped
public class RecetteController extends AbstractRecetteController implements Serializable {

    @PostConstruct
    private void init() {
        this.password.add("momo1234");
        this.password.add("kenne1234");
    }

    public void updateMois() {
        this.listMois.clear();
        try {
            if (this.annee.getIdannee() != null) {
                this.listMois = this.anneeMoisFacadeLocal.findByAnnee(this.annee);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void notifyError(String message) {
        RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').hide()");
        this.routine.feedBack("avertissement", "/resources/tool_images/warning.jpeg", this.routine.localizeMessage(message));
        RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
    }

    public void notifySuccess() {
        RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').hide()");
        this.routine.feedBack("information", "/resources/tool_images/success.png", this.routine.localizeMessage("operation_reussie"));
        RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
    }

    public void notifyFail(Exception e) {
        RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').hide()");
        this.routine.catchException(e, this.routine.localizeMessage("echec_operation"));
        RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
    }

    public void checkSession() {
        try {
            if (!"".equals(this.sessionPassword)) {
                if (this.password.contains(this.sessionPassword)) {
                    HttpSession session1 = SessionMBean.getSession();
                    session1.setAttribute("session", (true));
                    this.session = false;
                } else {
                    JsfUtil.addErrorMessage("Mot de passe incorrect");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void afficher() {
        this.journees.clear();
        if (this.anneeMois.getIdAnneeMois() != null) {
            this.anneeMois = this.anneeMoisFacadeLocal.find(this.anneeMois.getIdAnneeMois());
            this.journees = this.journeeFacadeLocal.findByIdmois(this.anneeMois.getIdAnneeMois());
        }
    }

    public void conciliate() {
        try {
            if (this.anneeMois.getIdAnneeMois() != null) {

                this.anneeMois = this.anneeMoisFacadeLocal.find(this.anneeMois.getIdAnneeMois());

                List<Journee> list = this.journeeFacadeLocal.findByIdmois(this.anneeMois.getIdAnneeMois());
                for (Journee j : list) {
                    List<Facture> factures = this.factureFacadeLocal.findAllDate(j.getDateJour());
                    List<Stock> stocks = this.stockFacadeLocal.findAllDate(j.getDateJour());

                    Double montantSortie = 0.0D;
                    Double bord = 0d;

                    for (Facture f : factures) {
                        montantSortie += f.getMontantTtc();
                        bord += f.getBord();
                    }

                    j.setMontant(montantSortie);
                    j.setBord(bord);

                    Double montantEntree = 0.0D;
                    for (Stock s : stocks) {
                        montantEntree += s.getMontant();
                    }

                    j.setMontantEntree(montantEntree);
                    this.journeeFacadeLocal.edit(j);
                }

                this.journees = this.journeeFacadeLocal.findByIdmois(this.anneeMois.getIdAnneeMois());
            }
            RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').hide()");
        } catch (Exception e) {
            notifyFail(e);
        }
    }

    public void printRecette() {
        try {
            if (!Utilitaires.isAccess(32L)) {
                notifyError("acces_refuse");
                return;
            }
            this.fileName1 = PrintUtils.printRecette(this.anneeMois, this.journees);
            RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').hide()");
            RequestContext.getCurrentInstance().execute("PF('StockImprimerDialog').show()");
        } catch (Exception e) {
            notifyFail(e);
        }
    }
}
