package sessions;

import entities.LigneCommandeClient;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class LigneCommandeClientFacade extends AbstractFacade<LigneCommandeClient> implements LigneCommandeClientFacadeLocal {

    @PersistenceContext(unitName = "Stock_manager-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }

    public LigneCommandeClientFacade() {
        super(LigneCommandeClient.class);
    }

    @Override
    public Long nextVal() {
        Query query = this.em.createQuery("SELECT MAX(l.idLigneCmdeClient) FROM LigneCommandeClient l");
        Long result = (Long) query.getSingleResult();
        if (result == null) {
            result = (1L);
        } else {
            result = result + 1L;
        }
        return result;
    }

    @Override
    public List<LigneCommandeClient> findByCommande(Long idCommanceClient) throws Exception {
        List<LigneCommandeClient> ligneCommandeClients = null;
        Query query = this.em.createQuery("SELECT l FROM LigneCommandeClient l WHERE l.idCmdeClient.idCommandeClient=:idcommande ORDER BY l.idproduit.nom");
        query.setParameter("idcommande", idCommanceClient);
        ligneCommandeClients = query.getResultList();
        return ligneCommandeClients;
    }

    @Override
    public List<LigneCommandeClient> findByDate(Date date) throws Exception {
        Query query = this.em.createQuery("SELECT l FROM LigneCommandeClient l WHERE l.idCmdeClient.dateCommande=:date ORDER BY l.idproduit.nom");
        query.setParameter("date", date);
        return query.getResultList();
    }

    @Override
    public List<LigneCommandeClient> findByMois(int idmois) throws Exception {

        Query query = this.em.createQuery("SELECT l FROM LigneCommandeClient l WHERE l.idCmdeClient.idmois.idAnneeMois=:mois ORDER BY l.idCmdeClient.dateCommande,l.idproduit.nom");
        query.setParameter("mois", idmois);
        return query.getResultList();
    }

    @Override
    public List<LigneCommandeClient> findByLot(Long idlot) throws Exception {
        Query query = this.em.createQuery("SELECT l FROM LigneCommandeClient l WHERE l.idlot.idlot=:lot ORDER BY l.idCmdeClient.dateCommande");
        query.setParameter("lot", idlot);
        return query.getResultList();
    }
}
