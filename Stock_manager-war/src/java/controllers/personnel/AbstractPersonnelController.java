package controllers.personnel;

import entities.Personnel;
import entities.Profession;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import sessions.MouchardFacadeLocal;
import sessions.PersonnelFacadeLocal;
import sessions.ProfessionFacadeLocal;
import utils.Routine;

public class AbstractPersonnelController {

    @EJB
    protected PersonnelFacadeLocal personnelFacadeLocal;
    protected Personnel personnel;
    /*  27 */    protected List<Personnel> personnels = new ArrayList<>();

    @EJB
    protected MouchardFacadeLocal mouchardFacadeLocal;

    @EJB
    protected ProfessionFacadeLocal professionFacadeLocal;
    /*  34 */    protected Profession profession = new Profession();
    /*  35 */    protected List<Profession> professions = new ArrayList<>();

    /*  37 */    protected Routine routine = new Routine();

    /*  39 */    protected String fileName = "";

    /*  41 */    protected Boolean detail = Boolean.valueOf(true);
    /*  42 */    protected Boolean modifier = Boolean.valueOf(true);
    /*  43 */    protected Boolean consulter = Boolean.valueOf(true);
    /*  44 */    protected Boolean imprimer = Boolean.valueOf(true);
    /*  45 */    protected Boolean supprimer = Boolean.valueOf(true);

    /*  47 */    protected String mode = "";

    public Personnel getPersonnel() {
        /*  50 */ return this.personnel;
    }

    public void setPersonnel(Personnel personnel) {
        /*  54 */ this.modifier = this.supprimer = this.detail = Boolean.valueOf((personnel == null));
        /*  55 */ this.personnel = personnel;
    }

    public List<Personnel> getPersonnels() {
        /*  59 */ this.personnels = this.personnelFacadeLocal.findAllRange();
        /*  60 */ return this.personnels;
    }

    public void setPersonnels(List<Personnel> personnels) {
        /*  64 */ this.personnels = personnels;
    }

    public Boolean getDetail() {
        /*  68 */ return this.detail;
    }

    public void setDetail(Boolean detail) {
        /*  72 */ this.detail = detail;
    }

    public Boolean getModifier() {
        /*  76 */ return this.modifier;
    }

    public void setModifier(Boolean modifier) {
        /*  80 */ this.modifier = modifier;
    }

    public Boolean getConsulter() {
        /*  84 */ return this.consulter;
    }

    public void setConsulter(Boolean consulter) {
        /*  88 */ this.consulter = consulter;
    }

    public Boolean getImprimer() {
        /*  92 */ return this.imprimer;
    }

    public void setImprimer(Boolean imprimer) {
        /*  96 */ this.imprimer = imprimer;
    }

    public List<Profession> getProfessions() {
        /* 100 */ this.professions = this.professionFacadeLocal.findAll();
        /* 101 */ return this.professions;
    }

    public void setProfessions(List<Profession> professions) {
        /* 105 */ this.professions = professions;
    }

    public Boolean getSupprimer() {
        /* 109 */ return this.supprimer;
    }

    public void setSupprimer(Boolean supprimer) {
        /* 113 */ this.supprimer = supprimer;
    }

    public String getFileName() {
        /* 117 */ return this.fileName;
    }

    public void setFileName(String fileName) {
        /* 121 */ this.fileName = fileName;
    }

    public Profession getProfession() {
        /* 125 */ return this.profession;
    }

    public void setProfession(Profession profession) {
        /* 129 */ this.profession = profession;
    }

    public Routine getRoutine() {
        /* 133 */ return this.routine;
    }

    public String getMode() {
        /* 137 */ return this.mode;
    }
}
