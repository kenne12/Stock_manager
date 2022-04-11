package sessions;

import entities.LigneMvtStock;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class LigneMvtStockFacade extends AbstractFacade<LigneMvtStock> implements LigneMvtStockFacadeLocal {

    @PersistenceContext(unitName = "Stock_manager-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }

    public LigneMvtStockFacade() {
        super(LigneMvtStock.class);
    }

    @Override
    public Long nextLongVal() {
        Query query = this.em.createQuery("SELECT MAX(l.idLigneMvtStock) FROM LigneMvtStock l");
        Long result = (Long) query.getSingleResult();
        if (result == null) {
            result = (1L);
        } else {
            result = (result + 1L);
        }
        return result;
    }

    @Override
    public List<LigneMvtStock> findByMvt(Long idMvt) throws Exception {
        Query query = this.em.createQuery("SELECT l FROM LigneMvtStock l WHERE l.idMvtStock.idMvtStock=:idmvt ORDER BY l.idlot.idproduit.nom");
        query.setParameter("idmvt", idMvt);
        return query.getResultList();
    }

    @Override
    public LigneMvtStock findByMvtIdLot(Long idMvt, Long idLot) throws Exception {
        Query query = this.em.createQuery("SELECT l FROM LigneMvtStock l WHERE l.idMvtStock.idMvtStock=:idmvt AND l.idlot.idlot=:idLot");
        query.setParameter("idmvt", idMvt).setParameter("idLot", idLot);
        List list = query.getResultList();
        if (!list.isEmpty()) {
            return (LigneMvtStock) list.get(0);
        }
        return null;
    }

    @Override
    public List<LigneMvtStock> findByIntervale(Date date_debut, Date date_fin) throws Exception {
        Query query = this.em.createQuery("SELECT l FROM LigneMvtStock l WHERE l.idMvtStock.dateMvt BETWEEN :date_debut AND :date_fin ORDER BY l.idLigneMvtStock");
        query.setParameter("date_debut", date_debut).setParameter("date_fin", date_fin);
        return query.getResultList();
    }

    @Override
    public List<LigneMvtStock> findByDate(Date date) throws Exception {
        Query query = this.em.createQuery("SELECT l FROM LigneMvtStock l WHERE l.idMvtStock.dateMvt =:date ORDER BY l.idLigneMvtStock");
        query.setParameter("date", date);
        return query.getResultList();
    }

    @Override
    public List<LigneMvtStock> findByIdArticleIntervale(long idArticle, Date date_debut, Date date_fin) throws Exception {
        Query query = this.em.createQuery("SELECT l FROM LigneMvtStock l WHERE l.idlot.idproduit.idproduit=:idProduit AND l.idMvtStock.dateMvt BETWEEN :date_debut AND :date_fin ORDER BY l.idLigneMvtStock");
        query.setParameter("date_debut", date_debut).setParameter("date_fin", date_fin).setParameter("idProduit", idArticle);
        return query.getResultList();
    }

    @Override
    public void deleteByIdMvtStock(long idMtStock) {
        Query query = em.createQuery("DELETE  FROM LigneMvtStock l WHERE l.idMvtStock.idMvtStock=:idMtStock");
        query.setParameter("idMtStock", idMtStock);
        query.executeUpdate();
    }
}
