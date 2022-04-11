package sessions;

import entities.Bailleur;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class BailleurFacade extends AbstractFacade<Bailleur> implements BailleurFacadeLocal {

    @PersistenceContext(unitName = "Stock_manager-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }

    public BailleurFacade() {
        super(Bailleur.class);
    }

    @Override
    public Integer nextVal() {
        Query query = this.em.createQuery("SELECT MAX(b.idbailleur) FROM Bailleur b");
        Integer result = (Integer) query.getSingleResult();
        if (result == null) {
            result = 1;
        } else {
            result = result + 1;
        }
        return result;
    }

    @Override
    public List<Bailleur> findAllRange() {
        Query query = this.em.createQuery("SELECT b FROM Bailleur b ORDER BY b.nom");
        return query.getResultList();
    }
}
