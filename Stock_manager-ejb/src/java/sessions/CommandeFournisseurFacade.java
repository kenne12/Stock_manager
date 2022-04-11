package sessions;

import entities.CommandeFournisseur;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class CommandeFournisseurFacade extends AbstractFacade<CommandeFournisseur> implements CommandeFournisseurFacadeLocal {

    @PersistenceContext(unitName = "Stock_manager-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }

    public CommandeFournisseurFacade() {
        super(CommandeFournisseur.class);
    }

    @Override
    public Long nextLongVal() {
        Query query = this.em.createQuery("SELECT MAX(c.idCmdeFournisseur) FROM CommandeFournisseur c");
        Long result = (Long) query.getSingleResult();
        if (result == null) {
            result = 1L;
        } else {
            result = result + 1L;
        }
        return result;
    }

    @Override
    public List<CommandeFournisseur> findAllRange() {
        Query query = this.em.createQuery("SELECT c FROM CommandeFournisseur c ORDER BY c.idmois.idannee.nom DESC,c.idmois.idmois.numero DESC, c.code DESC");
        return query.getResultList();
    }

    @Override
    public List<CommandeFournisseur> findAllDate(Date date) throws Exception {
        Query query = this.em.createQuery("SELECT c FROM CommandeFournisseur c WHERE c.dateCmde=:date ORDER BY c.code DESC");
        query.setParameter("date", date);
        return query.getResultList();
    }

    @Override
    public List<CommandeFournisseur> findByInterval(Date dateDebut, Date dateFin) throws Exception {
        Query query = this.em.createQuery("SELECT c FROM CommandeFournisseur c WHERE c.dateCmde BETWEEN :datedebut AND :datefin ORDER BY c.code DESC");
        query.setParameter("datedebut", dateDebut).setParameter("datefin", dateFin);
        return query.getResultList();
    }

    @Override
    public List<CommandeFournisseur> findByLivre(boolean livree) throws Exception {
        Query query = this.em.createQuery("SELECT c FROM CommandeFournisseur c WHERE c.livre=:livre ORDER BY c.dateCmde DESC");
        query.setParameter("livre", livree);
        return query.getResultList();
    }
}
