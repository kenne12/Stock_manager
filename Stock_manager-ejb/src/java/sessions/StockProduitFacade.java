package sessions;

import entities.Stock;
import entities.StockProduit;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class StockProduitFacade extends AbstractFacade<StockProduit> implements StockProduitFacadeLocal {

    @PersistenceContext(unitName = "Stock_manager-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }

    public StockProduitFacade() {
        super(StockProduit.class);
    }

    @Override
    public Long nextVal() {
        Query query = this.em.createQuery("SELECT MAX(s.idStockProduit) FROM StockProduit s");
        try {
           return  ((Long) query.getResultList().get(0) + 1);
        } catch (Exception e) {
            return 1l;
        }
        
    }

    @Override
    public List<StockProduit> findByStock(Stock stock) {
        Query query = this.em.createQuery("SELECT s FROM StockProduit s WHERE s.idstock.idstock=:stock ORDER BY s.idproduit.nom");
        query.setParameter("stock", stock.getIdstock());
        return query.getResultList();
    }

    @Override
    public List<StockProduit> findAllRange() {
        Query query = this.em.createQuery("SELECT s FROM StockProduit s ORDER BY s.idstock.dateAchat,s.idproduit.nom");
        return query.getResultList();
    }

    @Override
    public List<StockProduit> findByLot(Long idlot) throws Exception {
        Query query = this.em.createQuery("SELECT s FROM StockProduit s WHERE s.idlot.idlot=:idlot ORDER BY s.idstock.dateAchat");
        query.setParameter("idlot", idlot);
        return query.getResultList();
    }

    @Override
    public List<StockProduit> findByMois(int idmois) throws Exception {
        Query query = this.em.createQuery("SELECT s FROM StockProduit s WHERE s.idstock.idAnneeMois.idAnneeMois=:idmois ORDER BY s.idstock.dateAchat");
        query.setParameter("idmois", idmois);
        return query.getResultList();
    }

    @Override
    public void deleteByIdStock(long idStock) {
        em.createQuery("DELETE FROM StockProduit s WHERE s.idstock.idstock=:idStock")
                .setParameter("idStock", idStock)
                .executeUpdate();
    }
}
