package sessions;

import entities.Parametrage;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ParametrageFacade
        extends AbstractFacade<Parametrage>
        implements ParametrageFacadeLocal {

    @PersistenceContext(unitName = "Stock_manager-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }

    public ParametrageFacade() {
        super(Parametrage.class);
    }
}
