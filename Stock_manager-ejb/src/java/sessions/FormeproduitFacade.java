package sessions;

import entities.Formeproduit;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class FormeproduitFacade extends AbstractFacade<Formeproduit> implements FormeproduitFacadeLocal {

    @PersistenceContext(unitName = "Stock_manager-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }

    public FormeproduitFacade() {
        super(Formeproduit.class);
    }
}
