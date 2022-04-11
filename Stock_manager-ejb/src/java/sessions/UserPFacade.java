package sessions;

import entities.UserP;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class UserPFacade extends AbstractFacade<UserP> implements UserPFacadeLocal {

    @PersistenceContext(unitName = "Stock_manager-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }

    public UserPFacade() {
        super(UserP.class);
    }

    @Override
    public Integer nextVal() {
        Query query = this.em.createQuery("SELECT MAX(u.iduserP) FROM UserP u");
        Integer result = (Integer) query.getSingleResult();
        if (result == null) {
            result = (1);
        } else {
            result = (result + 1);
        }
        return result;
    }

    @Override
    public List<UserP> findAllRange() {
        Query query = this.em.createQuery("SELECT u FROM UserP u ORDER BY u.nom");
        return query.getResultList();
    }
}
