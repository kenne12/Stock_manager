package sessions;

import entities.MvtStock;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class MvtStockFacade extends AbstractFacade<MvtStock> implements MvtStockFacadeLocal {

    @PersistenceContext(unitName = "Stock_manager-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }

    public MvtStockFacade() {
        super(MvtStock.class);
    }

    @Override
    public Long nextLongVal() {
        Query query = this.em.createQuery("SELECT MAX(m.idMvtStock) FROM MvtStock m");
        Long result = (Long) query.getSingleResult();
        if (result == null) {
            result = (1L);
        } else {
            result = (result + 1L);
        }
        return result;
    }
    
    @Override
    public void deleteMvtById(long id){
        em.createQuery("DELETE FROM MvtStock m WHERE m.idMvtStock=:idMvt")
                .setParameter("idMvt", id)
                .executeUpdate();
    }
}
