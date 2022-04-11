package sessions;

import entities.Journee;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class JourneeFacade extends AbstractFacade<Journee> implements JourneeFacadeLocal {

    @PersistenceContext(unitName = "Stock_manager-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }

    public JourneeFacade() {
        super(Journee.class);
    }

    @Override
    public Long nextVal() {
        Query query = this.em.createQuery("SELECT MAX(j.idjournee) FROM Journee j");
        Long result = (Long) query.getSingleResult();
        if (result == null) {
            result = (1L);
        } else {
            result = (result + 1L);
        }
        return result;
    }

    @Override
    public List<Journee> find(Date date) {
        Query query = this.em.createQuery("SELECT j FROM Journee j WHERE  j.dateJour=:date");
        query.setParameter("date", date);
        return query.getResultList();
    }

    @Override
    public List<Journee> findByIdmois(int idmois) {
        Query query = this.em.createQuery("SELECT j FROM Journee j WHERE  j.idmois.idAnneeMois=:idmois ORDER BY j.dateJour");
        query.setParameter("idmois", (idmois));
        return query.getResultList();
    }

    @Override
    public List<Journee> findByIdInterval(Date dateDebut, Date dateFin) {
        Query query = this.em.createQuery("SELECT j FROM Journee j WHERE j.dateJour BETWEEN :dateDebut AND :dateFin ORDER BY j.dateJour");
        query.setParameter("dateDebut", dateDebut).setParameter("dateFin", dateFin);
        return query.getResultList();
    }
}
