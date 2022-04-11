package sessions;

import entities.Lot;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class LotFacade extends AbstractFacade<Lot> implements LotFacadeLocal {

    @PersistenceContext(unitName = "Stock_manager-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }

    public LotFacade() {
        super(Lot.class);
    }

    @Override
    public Long nextLongVal() {
        try {
            Query query = this.em.createQuery("SELECT MAX(l.idlot) FROM Lot l");
            Long result = (Long) query.getSingleResult();
            if (result == null) {
                result = (1L);
            } else {
                result = (result + 1L);
            }
            return result;
        } catch (Exception e) {
            return (1L);
        }
    }

    @Override
    public List<Lot> findAllRange() {
        Query query = this.em.createQuery("SELECT l FROM Lot l ORDER BY l.idproduit.nom , l.numeroLot");
        return query.getResultList();
    }

    @Override
    public List<Lot> findAllRange(boolean perissable) {
        Query query = this.em.createQuery("SELECT l FROM Lot l WHERE l.idproduit.perissable=true ORDER BY l.idproduit.nom , l.numeroLot");
        return query.getResultList();
    }

    @Override
    public List<Lot> findByArticle1(Long idacrticle, boolean order, Date date) throws Exception {
        Query query = null;
        if (order) {
            query = this.em.createQuery("SELECT l FROM Lot l WHERE l.idproduit.idproduit=:idproduit AND l.datePeremption>:date ORDER BY l.datePeremption");
            query.setParameter("date", date);
        } else {
            query = this.em.createQuery("SELECT l FROM Lot l WHERE l.idproduit.idproduit=:idproduit");
        }
        query.setParameter("idproduit", idacrticle);
        return query.getResultList();
    }

    @Override
    public List<Lot> findByArticle(Long idacrticle, boolean order) throws Exception {
        Query query = null;
        if (order) {
            query = this.em.createQuery("SELECT l FROM Lot l WHERE l.idproduit.idproduit=:idproduit ORDER BY l.datePeremption");
        } else {
            query = this.em.createQuery("SELECT l FROM Lot l WHERE l.idproduit.idproduit=:idproduit");
        }
        query.setParameter("idproduit", idacrticle);
        return query.getResultList();
    }

    @Override
    public List<Lot> findByArticle(Long idacrticle, boolean order, Date date) throws Exception {
        Query query = null;
        if (order) {
            query = this.em.createQuery("SELECT l FROM Lot l WHERE l.idproduit.idproduit=:idproduit AND l.quantite>0D AND l.datePeremption>:date ORDER BY l.datePeremption");
            query.setParameter("date", date);
        } else {
            query = this.em.createQuery("SELECT l FROM Lot l WHERE l.idproduit.idproduit=:idproduit AND l.quantite>0D");
        }
        query.setParameter("idproduit", idacrticle);
        return query.getResultList();
    }

    @Override
    public List<Lot> findByArticle(Long idacrticle, boolean order, Date date, boolean desc) throws Exception {
        Query query = null;
        if (order) {
            query = this.em.createQuery("SELECT l FROM Lot l WHERE l.idproduit.idproduit=:idproduit AND l.quantite>0D AND l.datePeremption>:date ORDER BY l.datePeremption DESC");
            query.setParameter("date", date);
        } else {
            query = this.em.createQuery("SELECT l FROM Lot l WHERE l.idproduit.idproduit=:idproduit AND l.quantite>0D");
        }
        query.setParameter("idproduit", idacrticle);
        return query.getResultList();
    }

    @Override
    public List<Lot> findByIdbailleur(int idbailleur, boolean etat) throws Exception {
        Query query = this.em.createQuery("SELECT l FROM Lot l WHERE l.idbailleur.idbailleur=:idbailleur AND l.etat=:etat");
        query.setParameter("idbailleur", (idbailleur)).setParameter("etat", (etat));
        return query.getResultList();
    }

    @Override
    public List<Lot> findByIdbailleurIdfamille(int idbailleur, int idfamille, boolean etat) throws Exception {
        Query query = this.em.createQuery("SELECT l FROM Lot l WHERE l.idbailleur.idbailleur=:idbailleur AND l.idproduit.idfamille.idfamille=:idfamille AND l.etat=:etat");
        query.setParameter("idbailleur", (idbailleur)).setParameter("etat", (etat)).setParameter("idfamille", (idfamille));
        return query.getResultList();
    }
}
