package sessions;

import entities.Boutique;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class BoutiqueFacade extends AbstractFacade<Boutique> implements BoutiqueFacadeLocal {

    @PersistenceContext(unitName = "Stock_manager-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }

    public BoutiqueFacade() {
        super(Boutique.class);
    }
}
