package controllers.bailleur;

import entities.Bailleur;
import entities.District;
import entities.Profession;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import sessions.BailleurFacadeLocal;
import sessions.DistrictFacadeLocal;
import sessions.MouchardFacadeLocal;
import sessions.ProfessionFacadeLocal;
import utils.Routine;

public class AbstractBailleurController {

    @EJB
    protected BailleurFacadeLocal bailleurFacadeLocal;
    protected Bailleur bailleur;
    /*  29 */    protected List<Bailleur> bailleurs = new ArrayList<>();

    @EJB
    protected MouchardFacadeLocal mouchardFacadeLocal;

    @EJB
    protected ProfessionFacadeLocal professionFacadeLocal;
    /*  36 */    protected List<Profession> professions = new ArrayList<>();

    @EJB
    protected DistrictFacadeLocal districtFacadeLocal;
    /*  40 */    protected District district = new District();
    /*  41 */    protected List<District> districts = new ArrayList<>();

    /*  43 */    protected Routine routine = new Routine();

    /*  45 */    protected String fileName = "";

    /*  47 */    protected Boolean detail = Boolean.valueOf(true);
    /*  48 */    protected Boolean modifier = Boolean.valueOf(true);
    /*  49 */    protected Boolean consulter = Boolean.valueOf(true);
    /*  50 */    protected Boolean imprimer = Boolean.valueOf(true);
    /*  51 */    protected Boolean supprimer = Boolean.valueOf(true);

    /*  53 */    protected String mode = "";

    public Bailleur getBailleur() {
        /*  56 */ return this.bailleur;
    }

    public void setBailleur(Bailleur bailleur) {
        /*  60 */ this.modifier = this.supprimer = this.detail = Boolean.valueOf((bailleur == null));
        /*  61 */ this.bailleur = bailleur;
    }

    public List<Bailleur> getBailleurs() {
        /*  65 */ this.bailleurs = this.bailleurFacadeLocal.findAllRange();
        /*  66 */ return this.bailleurs;
    }

    public void setBailleurs(List<Bailleur> bailleurs) {
        /*  70 */ this.bailleurs = bailleurs;
    }

    public Boolean getDetail() {
        /*  74 */ return this.detail;
    }

    public void setDetail(Boolean detail) {
        /*  78 */ this.detail = detail;
    }

    public Boolean getModifier() {
        /*  82 */ return this.modifier;
    }

    public void setModifier(Boolean modifier) {
        /*  86 */ this.modifier = modifier;
    }

    public Boolean getConsulter() {
        /*  90 */ return this.consulter;
    }

    public void setConsulter(Boolean consulter) {
        /*  94 */ this.consulter = consulter;
    }

    public Boolean getImprimer() {
        /*  98 */ this.imprimer = Boolean.valueOf(this.bailleurFacadeLocal.findAllRange().isEmpty());
        /*  99 */ return this.imprimer;
    }

    public void setImprimer(Boolean imprimer) {
        /* 103 */ this.imprimer = imprimer;
    }

    public List<Profession> getProfessions() {
        /* 107 */ this.professions = this.professionFacadeLocal.findAll();
        /* 108 */ return this.professions;
    }

    public void setProfessions(List<Profession> professions) {
        /* 112 */ this.professions = professions;
    }

    public Boolean getSupprimer() {
        /* 116 */ return this.supprimer;
    }

    public void setSupprimer(Boolean supprimer) {
        /* 120 */ this.supprimer = supprimer;
    }

    public String getFileName() {
        /* 124 */ return this.fileName;
    }

    public void setFileName(String fileName) {
        /* 128 */ this.fileName = fileName;
    }

    public Routine getRoutine() {
        /* 132 */ return this.routine;
    }

    public District getDistrict() {
        /* 136 */ return this.district;
    }

    public void setDistrict(District district) {
        /* 140 */ this.district = district;
    }

    public List<District> getDistricts() {
        /* 144 */ this.districts = this.districtFacadeLocal.findAll();
        /* 145 */ return this.districts;
    }

    public void setDistricts(List<District> districts) {
        /* 149 */ this.districts = districts;
    }
}


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-war.war!\WEB-INF\classes\controllers\bailleur\AbstractBailleurController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
