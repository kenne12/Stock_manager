package sessions;

import entities.District;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class DistrictFacade extends AbstractFacade<District> implements DistrictFacadeLocal {

    @PersistenceContext(unitName = "Stock_manager-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }

    public DistrictFacade() {
        super(District.class);
    }

    @Override
    public Integer nextVal() {
        Query query = this.em.createQuery("SELECT MAX(d.iddistrict) FROM District d");
        Integer result = (Integer) query.getSingleResult();
        if (result == null) {
            result = (1);
        } else {
            result = (result + 1);
        }
        return result;
    }

    @Override
    public List<District> findAllRange() {
        Query query = this.em.createQuery("SELECT d FROM District d ORDER BY d.nom");
        return query.getResultList();
    }
}
