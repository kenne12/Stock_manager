package sessions;

import entities.AnneeMois;
import entities.Personnel;
import entities.Salaire;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class SalaireFacade
        extends AbstractFacade<Salaire>
        implements SalaireFacadeLocal {

    @PersistenceContext(unitName = "Stock_manager-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        /* 29 */ return this.em;
    }

    public SalaireFacade() {
        /* 33 */ super(Salaire.class);
    }

    @Override
    public Long nextVal() {
        /* 38 */ Query query = this.em.createQuery("SELECT MAX(s.idsalaire) FROM Salaire s");
        /* 39 */ Long result = (Long) query.getSingleResult();
        /* 40 */ if (result == null) {
            /* 41 */ result = Long.valueOf(1L);
        } else {
            /* 43 */ result = Long.valueOf(result.longValue() + 1L);
        }
        /* 45 */ return result;
    }

    @Override
    public List<Salaire> findAllRange() {
        /* 50 */ List<Salaire> salaires = null;
        /* 51 */ Query query = this.em.createQuery("SELECT s FROM Salaire s WHERE s.idmois.idannee.etat=true ORDER BY s.idmois.idmois.idmois,s.dateSalaire");
        /* 52 */ salaires = query.getResultList();
        /* 53 */ return salaires;
    }

    @Override
    public List<Salaire> findByPersonnel(Personnel personnel, AnneeMois anneeMois) throws Exception {
        /* 58 */ List<Salaire> salaires = null;
        /* 59 */ Query query = this.em.createQuery("SELECT s FROM Salaire s WHERE s.idpersonnel.idpersonnel=:personnel AND s.idmois.idAnneeMois=:mois");
        /* 60 */ query.setParameter("personnel", personnel.getIdpersonnel()).setParameter("mois", anneeMois.getIdAnneeMois());
        /* 61 */ salaires = query.getResultList();
        /* 62 */ return salaires;
    }

    @Override
    public List<Salaire> findByMois(AnneeMois anneeMois) throws Exception {
        /* 67 */ List<Salaire> salaires = null;
        /* 68 */ Query query = this.em.createQuery("SELECT s FROM Salaire s WHERE s.idmois.idAnneeMois=:mois ORDER BY s.dateSalaire");
        /* 69 */ query.setParameter("mois", anneeMois.getIdAnneeMois());
        /* 70 */ salaires = query.getResultList();
        /* 71 */ return salaires;
    }
}
