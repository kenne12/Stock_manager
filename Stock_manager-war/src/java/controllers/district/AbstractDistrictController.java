package controllers.district;

import entities.District;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import sessions.DistrictFacadeLocal;
import sessions.MouchardFacadeLocal;
import utils.Routine;

public class AbstractDistrictController {

    @EJB
    protected DistrictFacadeLocal districtFacadeLocal;
    protected District district;
    /*  25 */    protected List<District> districts = new ArrayList<>();

    @EJB
    protected MouchardFacadeLocal mouchardFacadeLocal;

    /*  30 */    protected Routine routine = new Routine();

    /*  32 */    protected String fileName = "";

    /*  34 */    protected Boolean detail = Boolean.valueOf(true);
    /*  35 */    protected Boolean modifier = Boolean.valueOf(true);
    /*  36 */    protected Boolean consulter = Boolean.valueOf(true);
    /*  37 */    protected Boolean imprimer = Boolean.valueOf(true);
    /*  38 */    protected Boolean supprimer = Boolean.valueOf(true);

    /*  40 */    protected String mode = "";

    public District getDistrict() {
        /*  43 */ return this.district;
    }

    public void setDistrict(District district) {
        /*  47 */ this.modifier = this.supprimer = this.detail = Boolean.valueOf((district == null));
        /*  48 */ this.district = district;
    }

    public List<District> getDistricts() {
        /*  52 */ this.districts = this.districtFacadeLocal.findAllRange();
        /*  53 */ return this.districts;
    }

    public void setDistricts(List<District> districts) {
        /*  57 */ this.districts = districts;
    }

    public Boolean getDetail() {
        /*  61 */ return this.detail;
    }

    public void setDetail(Boolean detail) {
        /*  65 */ this.detail = detail;
    }

    public Boolean getModifier() {
        /*  69 */ return this.modifier;
    }

    public void setModifier(Boolean modifier) {
        /*  73 */ this.modifier = modifier;
    }

    public Boolean getConsulter() {
        /*  77 */ return this.consulter;
    }

    public void setConsulter(Boolean consulter) {
        /*  81 */ this.consulter = consulter;
    }

    public Boolean getImprimer() {
        /*  85 */ this.imprimer = Boolean.valueOf(this.districtFacadeLocal.findAllRange().isEmpty());
        /*  86 */ return this.imprimer;
    }

    public void setImprimer(Boolean imprimer) {
        /*  90 */ this.imprimer = imprimer;
    }

    public Boolean getSupprimer() {
        /*  94 */ return this.supprimer;
    }

    public void setSupprimer(Boolean supprimer) {
        /*  98 */ this.supprimer = supprimer;
    }

    public String getFileName() {
        /* 102 */ return this.fileName;
    }

    public void setFileName(String fileName) {
        /* 106 */ this.fileName = fileName;
    }

    public Routine getRoutine() {
        /* 110 */ return this.routine;
    }
}


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-war.war!\WEB-INF\classes\controllers\district\AbstractDistrictController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
