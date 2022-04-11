package sessions;

import entities.AnneeMois;
import entities.Depense;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class DepenseFacade
        extends AbstractFacade<Depense>
        implements DepenseFacadeLocal {

    @PersistenceContext(unitName = "Stock_manager-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        /* 28 */ return this.em;
    }

    public DepenseFacade() {
        /* 32 */ super(Depense.class);
    }

    @Override
    public Long nextVal() {
        /* 37 */ Query query = this.em.createQuery("SELECT MAX(d.iddepense) FROM Depense d");
        /* 38 */ Long result = (Long) query.getSingleResult();
        /* 39 */ if (result == null) {
            /* 40 */ result = (1L);
        } else {
            /* 42 */ result = (result + 1L);
        }
        /* 44 */ return result;
    }

    @Override
    public List<Depense> findAllRange() {
        /* 49 */ List<Depense> depenses = null;
        /* 50 */ Query query = this.em.createQuery("SELECT d FROM Depense d WHERE d.idmois.etat=true ORDER BY d.idmois.idmois.idmois,d.dateDepense");
        /* 51 */ depenses = query.getResultList();
        /* 52 */ return depenses;
    }

    @Override
    public List<Depense> findBymois(AnneeMois anneeMois) throws Exception {
        /* 57 */ List<Depense> depenses = null;
        /* 58 */ Query query = this.em.createQuery("SELECT d FROM Depense d WHERE d.idmois.idAnneeMois=:mois ORDER BY d.dateDepense");
        /* 59 */ query.setParameter("mois", anneeMois.getIdAnneeMois());
        /* 60 */ depenses = query.getResultList();
        /* 61 */ return depenses;
    }
}
