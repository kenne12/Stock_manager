package sessions;

import entities.Personnel;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class PersonnelFacade extends AbstractFacade<Personnel> implements PersonnelFacadeLocal {

    @PersistenceContext(unitName = "Stock_manager-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }

    public PersonnelFacade() {
        super(Personnel.class);
    }

    @Override
    public Integer nextVal() {
        Query query = this.em.createQuery("SELECT MAX(p.idpersonnel) FROM Personnel p");
        Integer result = (Integer) query.getSingleResult();
        if (result == null) {
            result = (1);
        } else {
            result = (result + 1);
        }
        return result;
    }

    @Override
    public List<Personnel> findByEtat(boolean etat) throws Exception {
        Query query = this.em.createQuery("SELECT p FROM Personnel p WHERE p.etat=:etat ORDER BY p.nom,p.prenom").setParameter("etat", Boolean.valueOf(etat));
        return query.getResultList();
    }

    @Override
    public List<Personnel> findAllRange() {
        Query query = this.em.createQuery("SELECT p FROM Personnel p ORDER BY p.nom,p.prenom");
        return query.getResultList();
    }
}
