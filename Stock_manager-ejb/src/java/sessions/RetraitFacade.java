package sessions;

import entities.Client;
import entities.Retrait;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class RetraitFacade
        extends AbstractFacade<Retrait>
        implements RetraitFacadeLocal {

    @PersistenceContext(unitName = "Stock_manager-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        /* 29 */ return this.em;
    }

    public RetraitFacade() {
        /* 33 */ super(Retrait.class);
    }

    @Override
    public Long nextLongVal() {
        /* 38 */ Query query = this.em.createQuery("SELECT MAX(r.idretrait) FROM Retrait r");
        /* 39 */ Long result = (Long) query.getSingleResult();
        /* 40 */ if (result == null) {
            /* 41 */ result = Long.valueOf(1L);
        } else {
            /* 43 */ result = Long.valueOf(result.longValue() + 1L);
        }
        /* 45 */ return result;
    }

    @Override
    public List<Retrait> find(Client client) {
        /* 50 */ List<Retrait> retraits = null;
        /* 51 */ Query query = this.em.createQuery("SELECT r FROM Retrait r WHERE r.idclient.idclient =:client");
        /* 52 */ query.setParameter("client", client.getIdclient());
        /* 53 */ retraits = query.getResultList();
        /* 54 */ return retraits;
    }

    @Override
    public List<Retrait> find(Client client, Date datedebut, Date datefin) {
        /* 59 */ List<Retrait> retraits = null;
        /* 60 */ Query query = this.em.createQuery("SELECT r FROM Retrait r WHERE r.idclient.idclient=:client AND r.date BETWEEN :datedebut AND :datefin");
        /* 61 */ query.setParameter("datedebut", datedebut).setParameter("datefin", datefin);
        /* 62 */ query.setParameter("client", client.getIdclient());
        /* 63 */ retraits = query.getResultList();
        /* 64 */ return retraits;
    }

    @Override
    public List<Retrait> find(Client client, Date date) {
        /* 69 */ List<Retrait> retraits = null;
        /* 70 */ Query query = this.em.createQuery("SELECT r FROM Retrait r WHERE r.idclient.idclient=:client AND r.date=:date");
        /* 71 */ query.setParameter("date", date);
        /* 72 */ query.setParameter("client", client.getIdclient());
        /* 73 */ retraits = query.getResultList();
        /* 74 */ return retraits;
    }
}
