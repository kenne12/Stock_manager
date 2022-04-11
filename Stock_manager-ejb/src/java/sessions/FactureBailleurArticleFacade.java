package sessions;

import entities.FactureBailleurArticle;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class FactureBailleurArticleFacade
        extends AbstractFacade<FactureBailleurArticle>
        implements FactureBailleurArticleFacadeLocal {

    @PersistenceContext(unitName = "Stock_manager-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        /* 28 */ return this.em;
    }

    public FactureBailleurArticleFacade() {
        /* 32 */ super(FactureBailleurArticle.class);
    }

    @Override
    public Long nextVal() {
        /* 37 */ Query query = this.em.createQuery("SELECT MAX(f.idFactureBailleurArticle) FROM FactureBailleurArticle f");
        /* 38 */ Long result = (Long) query.getSingleResult();
        /* 39 */ if (result == null) {
            /* 40 */ result = Long.valueOf(1L);
        } else {
            /* 42 */ result = Long.valueOf(result.longValue() + 1L);
        }
        /* 44 */ return result;
    }

    @Override
    public List<FactureBailleurArticle> findByFacture(Long facturebailleur) throws Exception {
        /* 49 */ Query query = this.em.createQuery("SELECT f FROM FactureBailleurArticle f WHERE f.idfactureBailleur.idFactureBailleur=:facture");
        /* 50 */ return query.setParameter("facture", facturebailleur).getResultList();
    }
}
