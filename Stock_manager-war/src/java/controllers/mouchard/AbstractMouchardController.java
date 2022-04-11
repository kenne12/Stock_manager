package controllers.mouchard;

import entities.Mouchard;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import sessions.MouchardFacadeLocal;

public class AbstractMouchardController {

    @EJB
    protected MouchardFacadeLocal mouchardFacadeLocal;
    protected Mouchard mouchard;
    /* 23 */    protected List<Mouchard> mouchards = new ArrayList<>();

    public List<Mouchard> getMouchards() {
        /* 26 */ this.mouchards = this.mouchardFacadeLocal.findAll();
        /* 27 */ return this.mouchards;
    }

    public void setMouchards(List<Mouchard> mouchards) {
        /* 31 */ this.mouchards = mouchards;
    }
}
