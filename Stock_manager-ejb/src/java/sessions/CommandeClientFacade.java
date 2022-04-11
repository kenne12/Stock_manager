package sessions;

import entities.CommandeClient;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class CommandeClientFacade extends AbstractFacade<CommandeClient> implements CommandeClientFacadeLocal {

    @PersistenceContext(unitName = "Stock_manager-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }

    public CommandeClientFacade() {
        super(CommandeClient.class);
    }

    @Override
    public Long nextLongVal() {
        Query query = this.em.createQuery("SELECT MAX(c.idCommandeClient) FROM CommandeClient c");
        Long result = (Long) query.getSingleResult();
        if (result == null) {
            result = (1L);
        } else {
            result = (result + 1L);
        }
        return result;
    }

    @Override
    public List<CommandeClient> findAllRange() {
        Query query = this.em.createQuery("SELECT c FROM CommandeClient c ORDER BY c.idmois.idannee.nom DESC,c.idmois.idmois.numero DESC, c.code DESC");
        return query.getResultList();
    }

    @Override
    public List<CommandeClient> findAllDate(Date date) throws Exception {
        Query query = this.em.createQuery("SELECT c FROM CommandeClient c WHERE c.dateCommande=:date ORDER BY c.code DESC");
        query.setParameter("date", date);
        return query.getResultList();
    }

    @Override
    public List<CommandeClient> findByInterval(Date dateDebut, Date dateFin) throws Exception {
        Query query = this.em.createQuery("SELECT c FROM CommandeClient c WHERE c.dateCommande BETWEEN :datedebut AND :datefin ORDER BY c.code DESC");
        query.setParameter("datedebut", dateDebut).setParameter("datefin", dateFin);
        return query.getResultList();
    }

    @Override
    public List<CommandeClient> findByLivre(boolean livree) throws Exception {
        Query query = this.em.createQuery("SELECT c FROM CommandeClient c WHERE c.livre=:livre ORDER BY c.dateCommande DESC");
        query.setParameter("livre", (livree));
        return query.getResultList();
    }
}
