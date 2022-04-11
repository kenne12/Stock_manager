package sessions;

import entities.AnneeMois;
import entities.Stock;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class StockFacade extends AbstractFacade<Stock> implements StockFacadeLocal {

    @PersistenceContext(unitName = "Stock_manager-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }

    public StockFacade() {
        super(Stock.class);
    }

    @Override
    public Long nextLongVal() {
        Query query = this.em.createQuery("SELECT MAX(s.idstock) FROM Stock s");
        try {
            return ((Long) query.getResultList().get(0) + 1);
        } catch (Exception e) {
            return 1l;
        }
    }

    @Override
    public Long nextVal(AnneeMois anneeMois) {
        Query query = this.em.createQuery("SELECT COUNT(s.idstock) FROM Stock s WHERE s.idAnneeMois.idAnneeMois=:mois");
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
    public List<Stock> findAllRange() {
        Query query = this.em.createQuery("SELECT s FROM Stock s WHERE s.idAnneeMois.etat=true ORDER BY s.idAnneeMois.idmois.idmois,s.code");
        return query.getResultList();
    }

    @Override
    public List<Stock> findByMois(AnneeMois anneeMois) throws Exception {
        Query query = this.em.createQuery("SELECT s FROM Stock s WHERE s.idAnneeMois.idAnneeMois=:mois ORDER BY s.dateAchat DESC , s.code");
        query.setParameter("mois", anneeMois.getIdAnneeMois());
        return query.getResultList();
    }

    @Override
    public List<Stock> findAllDate(Date date) throws Exception {
        Query query = this.em.createQuery("SELECT s FROM Stock s WHERE s.dateAchat=:date ORDER BY s.code DESC");
        query.setParameter("date", date);
        return query.getResultList();
    }

    @Override
    public List<Stock> findByInterval(Date dateDebut, Date dateFin) throws Exception {
        Query query = this.em.createQuery("SELECT s FROM Stock s WHERE s.dateAchat BETWEEN :datedebut AND :datefin ORDER BY s.code DESC");
        query.setParameter("datedebut", dateDebut).setParameter("datefin", dateFin);
        return query.getResultList();
    }

    @Override
    public void deleteStockById(long id) {
        em.createQuery("DELETE FROM Stock s WHERE s.idstock=:id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
