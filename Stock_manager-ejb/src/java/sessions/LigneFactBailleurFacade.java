package sessions;

import entities.LigneFactBailleur;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class LigneFactBailleurFacade extends AbstractFacade<LigneFactBailleur> implements LigneFactBailleurFacadeLocal {

    @PersistenceContext(unitName = "Stock_manager-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }

    public LigneFactBailleurFacade() {
        super(LigneFactBailleur.class);
    }

    @Override
    public Long nextVal() {
        Query query = this.em.createQuery("SELECT MAX(l.idLigneFactBailleur) FROM LigneFactBailleur l");
        Long result = (Long) query.getSingleResult();
        if (result == null) {
            result = (1L);
        } else {
            result = (result + 1L);
        }
        return result;
    }

    @Override
    public List<LigneFactBailleur> findByFacture(Long facturebailleur) throws Exception {
        Query query = this.em.createQuery("SELECT l FROM LigneFactBailleur l WHERE l.idfactureBailleur.idFactureBailleur=:facture");
        return query.setParameter("facture", facturebailleur).getResultList();
    }
}
