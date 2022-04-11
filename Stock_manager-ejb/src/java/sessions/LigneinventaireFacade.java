package sessions;

import entities.Ligneinventaire;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class LigneinventaireFacade extends AbstractFacade<Ligneinventaire> implements LigneinventaireFacadeLocal {

    @PersistenceContext(unitName = "Stock_manager-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }

    public LigneinventaireFacade() {
        super(Ligneinventaire.class);
    }

    @Override
    public Long nextVal() {
        Query query = this.em.createQuery("SELECT MAX(l.idLigneinventaire) FROM Ligneinventaire l");
        Long result = (Long) query.getSingleResult();
        if (result == null) {
            result = (1L);
        } else {
            result = (result + 1L);
        }
        return result;
    }

    @Override
    public List<Ligneinventaire> findByInventaire(Long idInventaire) throws Exception {
        Query query = this.em.createQuery("SELECT l FROM Ligneinventaire l WHERE l.idInventaire.idinventaire=:idventaire ORDER BY l.idlot.idproduit.nom,l.idlot.numeroLot");
        query.setParameter("idventaire", idInventaire);
        return query.getResultList();
    }

    @Override
    public void deleteByIdInventaire(long idInventaire) {
        Query query = em.createQuery("DELETE FROM Ligneinventaire l WHERE l.idInventaire.idinventaire=:idInventaire");
        query.setParameter("idInventaire", idInventaire);
        query.executeUpdate();
    }
}
