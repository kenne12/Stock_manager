package sessions;

import entities.AnneeMois;
import entities.Commande;
import entities.Facture;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

@Stateless
public class CommandeFacade extends AbstractFacade<Commande> implements CommandeFacadeLocal {

    @PersistenceContext(unitName = "Stock_manager-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }

    public CommandeFacade() {
        super(Commande.class);
    }

    @Override
    public Long nextVal() {
        Query query = this.em.createQuery("SELECT MAX(c.idcommande) FROM Commande c");
        Long result = (Long) query.getSingleResult();
        if (result == null) {
            result = (1L);
        } else {
            result = (result + 1L);
        }
        return result;
    }

    @Override
    public List<Commande> findByFacture(Facture facture) {
        List<Commande> commandes = null;
        Query query = this.em.createQuery("SELECT c FROM Commande c WHERE c.idfacture.idfacture=:facture ORDER BY c.idproduit.nom");
        query.setParameter("facture", facture.getIdfacture());
        commandes = query.getResultList();
        return commandes;
    }

    @Override
    public List<Commande> findByDate(Date date) throws Exception {
        List<Commande> commandes = null;
        Query query = this.em.createQuery("SELECT c FROM Commande c WHERE c.idfacture.dateAchat=:date ORDER BY c.idproduit.nom");
        query.setParameter("date", date);
        commandes = query.getResultList();
        return commandes;
    }

    @Override
    public List<Commande> findByMois(AnneeMois anneeMois) throws Exception {
        List<Commande> commandes = null;
        Query query = this.em.createQuery("SELECT c FROM Commande c WHERE c.idfacture.idAnneeMois.idAnneeMois=:mois ORDER BY c.idfacture.dateAchat,c.idproduit.nom");
        query.setParameter("mois", anneeMois.getIdAnneeMois());
        commandes = query.getResultList();
        return commandes;
    }

    @Override
    public List<Commande> findByLot(Long idlot) throws Exception {
        List<Commande> commandes = null;
        Query query = this.em.createQuery("SELECT c FROM Commande c WHERE c.idlot.idlot=:lot ORDER BY c.idfacture.dateAchat");
        query.setParameter("lot", idlot);
        commandes = query.getResultList();
        return commandes;
    }

    @Override
    public List<Commande> findByFamilleIntervalNotCount(int idfamille, int idbailleur, Date date_debut, Date date_fin) throws Exception {
        Query query = this.em.createQuery("SELECT c FROM Commande c WHERE c.idproduit.idfamille.idfamille=:idfamille AND c.idlot.idbailleur.idbailleur=:idbailleur AND c.comptabilise=false AND c.idfacture.dateAchat BETWEEN :date_debut AND :date_fin");
        query.setParameter("idfamille", (idfamille)).setParameter("date_debut", date_debut).setParameter("date_fin", date_fin).setParameter("idbailleur", (idbailleur));
        return query.getResultList();
    }

    @Override
    public void deleteByIdfacture(Long idFacture) {
        em.createQuery("DELETE FROM Commande c WHERE c.idfacture.idfacture=:idFacture")
                .setParameter("idFacture", idFacture)
                .executeUpdate();
    }
}
