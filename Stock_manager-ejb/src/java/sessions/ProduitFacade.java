package sessions;

import entities.Famille;
import entities.Produit;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class ProduitFacade extends AbstractFacade<Produit> implements ProduitFacadeLocal {

    @PersistenceContext(unitName = "Stock_manager-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }

    public ProduitFacade() {
        super(Produit.class);
    }

    @Override
    public Long nextVal() {
        Query query = this.em.createQuery("SELECT MAX(p.idproduit) FROM Produit p");
        Long result = (Long) query.getSingleResult();
        if (result == null) {
            result = (1L);
        } else {
            result = (result + 1L);
        }
        return result;
    }

    @Override
    public List<Produit> findByCode(String code) throws Exception {
        Query query = this.em.createQuery("SELECT p FROM Produit p WHERE p.code=:code");
        return query.setParameter("code", code).getResultList();
    }

    @Override
    public List<Produit> findAllRange() {
        Query query = this.em.createQuery("SELECT p FROM Produit p ORDER BY p.nom");
        return query.getResultList();
    }

    @Override
    public List<Produit> findAllRange(boolean etat) {
        Query query = this.em.createQuery("SELECT p FROM Produit p WHERE p.etat=:etat ORDER BY p.nom");
        query.setParameter("etat", (etat));
        return query.getResultList();
    }

    @Override
    public List<Produit> findAllRange1() {
        Query query = this.em.createQuery("SELECT p FROM Produit p WHERE p.quantite>0D ORDER BY p.nom");
        return query.getResultList();
    }

    @Override
    public List<Produit> findAllRangeNom() {
        Query query = this.em.createQuery("SELECT p FROM Produit p ORDER BY p.nom");
        return query.getResultList();
    }

    @Override
    public List<Produit> findByFamille(Famille famille) throws Exception {
        Query query = this.em.createQuery("SELECT p FROM Produit p WHERE p.idfamille.idfamille=:famille ORDER BY p.nom");
        query.setParameter("famille", famille.getIdfamille());
        return query.getResultList();
    }

    @Override
    public List<Produit> findByFamille(Famille famille, boolean positif) throws Exception {
        Query query = this.em.createQuery("SELECT p FROM Produit p WHERE p.idfamille.idfamille=:famille AND p.quantite>0D ORDER BY p.nom");
        query.setParameter("famille", famille.getIdfamille());
        return query.getResultList();
    }

    @Override
    public List<Produit> findSousStock() {
        List<Produit> produits = null;
        Query query = this.em.createQuery("SELECT p FROM Produit p WHERE p.quantite<=p.stockCritique ORDER BY p.nom");
        produits = query.getResultList();
        return produits;
    }

    @Override
    public List<Produit> findByPerissable(boolean etat) throws Exception {
        Query query = this.em.createQuery("SELECT p FROM Produit p WHERE p.perissable = true AND p.etat=:etat ORDER BY p.nom");
        query.setParameter("etat", (etat));
        return query.getResultList();
    }
}
