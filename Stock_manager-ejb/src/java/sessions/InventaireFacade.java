package sessions;

import entities.AnneeMois;
import entities.Inventaire;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class InventaireFacade
        extends AbstractFacade<Inventaire>
        implements InventaireFacadeLocal {

    @PersistenceContext(unitName = "Stock_manager-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }

    public InventaireFacade() {
        super(Inventaire.class);
    }

    @Override
    public Long nextVal() {
        Query query = this.em.createQuery("SELECT MAX(i.idinventaire) FROM Inventaire i");
        Long result = (Long) query.getSingleResult();
        if (result == null) {
            result = (1L);
        } else {
            result = (result + 1L);
        }
        return result;
    }

    @Override
    public Long nextVal(AnneeMois anneeMois) {
        Query query = this.em.createQuery("SELECT COUNT(i.idinventaire) FROM Inventaire i WHERE i.idmois.idAnneeMois=:mois");
        query.setParameter("mois", anneeMois.getIdAnneeMois());
        Long result = (Long) query.getSingleResult();
        if (result == null) {
            result = (1L);
        } else {
            result = (result + 1L);
        }
        return result;
    }

    @Override
    public List<Inventaire> findAllRange() throws Exception {
        Query query = this.em.createQuery("SELECT i FROM Inventaire i ORDER BY i.dateInventaire DESC");
        return query.getResultList();
    }
}
