package sessions;

import entities.LigneCmdeFournisseur;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class LigneCmdeFournisseurFacade extends AbstractFacade<LigneCmdeFournisseur> implements LigneCmdeFournisseurFacadeLocal {

    @PersistenceContext(unitName = "Stock_manager-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }

    public LigneCmdeFournisseurFacade() {
        super(LigneCmdeFournisseur.class);
    }

    @Override
    public Long nextVal() {
        Query query = this.em.createQuery("SELECT MAX(l.idLigneCmdeFournisseur) FROM LigneCmdeFournisseur l");
        Long result = (Long) query.getSingleResult();
        if (result == null) {
            result = 1L;
        } else {
            result = result + 1L;
        }
        return result;
    }

    @Override
    public List<LigneCmdeFournisseur> findByCommande(Long idCmdeFournisseur) throws Exception {
        Query query = this.em.createQuery("SELECT l FROM LigneCmdeFournisseur l WHERE l.idCmdeFournisseur.idCmdeFournisseur=:idcommande ORDER BY l.idproduit.nom");
        query.setParameter("idcommande", idCmdeFournisseur);
        return query.getResultList();
    }

    @Override
    public List<LigneCmdeFournisseur> findByDate(Date date) throws Exception {
        Query query = this.em.createQuery("SELECT l FROM LigneCmdeFournisseur l WHERE l.idCmdeFournisseur.dateCmde=:date ORDER BY l.idproduit.nom");
        query.setParameter("date", date);
        return query.getResultList();
    }
}
