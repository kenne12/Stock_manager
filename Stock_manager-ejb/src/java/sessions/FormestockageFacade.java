package sessions;

import entities.Formestockage;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class FormestockageFacade extends AbstractFacade<Formestockage> implements FormestockageFacadeLocal {

    @PersistenceContext(unitName = "Stock_manager-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }

    public FormestockageFacade() {
        super(Formestockage.class);
    }
}
