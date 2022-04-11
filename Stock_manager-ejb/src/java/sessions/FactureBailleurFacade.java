package sessions;

import entities.FactureBailleur;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class FactureBailleurFacade
        extends AbstractFacade<FactureBailleur>
        implements FactureBailleurFacadeLocal {

    @PersistenceContext(unitName = "Stock_manager-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        /* 27 */ return this.em;
    }

    public FactureBailleurFacade() {
        /* 31 */ super(FactureBailleur.class);
    }

    @Override
    public Long nextVal() {
        /* 36 */ Query query = this.em.createQuery("SELECT MAX(f.idFactureBailleur) FROM FactureBailleur f");
        /* 37 */ Long result = (Long) query.getSingleResult();
        /* 38 */ if (result == null) {
            /* 39 */ result = Long.valueOf(1L);
        } else {
            /* 41 */ result = Long.valueOf(result.longValue() + 1L);
        }
        /* 43 */ return result;
    }

    @Override
    public List<FactureBailleur> findAllRange() {
        /* 48 */ Query query = this.em.createQuery("SELECT f FROM FactureBailleur f ORDER BY f.dateFacturation");
        /* 49 */ return query.getResultList();
    }
}
