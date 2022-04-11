package sessions;

import entities.Privilege;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class PrivilegeFacade extends AbstractFacade<Privilege> implements PrivilegeFacadeLocal {

    @PersistenceContext(unitName = "Stock_manager-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }

    public PrivilegeFacade() {
        super(Privilege.class);
    }

    @Override
    public List<Privilege> findAll1() {
        Query query = this.em.createQuery("SELECT p FROM Privilege p ORDER BY p.idutilisateur.nom, p.idutilisateur.prenom,p.idmenu.idmenu DESC");
        return query.getResultList();
    }

    @Override
    public Long nextVal() {
        Query query = this.em.createQuery("SELECT MAX(p.idprivilege) FROM Privilege p");
        Long result = (Long) query.getSingleResult();
        if (result == null) {
            result = (1L);
        } else {
            result = (result + 1L);
        }
        return result;
    }

    @Override
    public List<Privilege> findByUser(int utilisateur) {
        Query query = this.em.createQuery("SELECT p FROM Privilege p WHERE p.idutilisateur.idutilisateur=:utilisateur");
        query.setParameter("utilisateur", (utilisateur));
        return query.getResultList();
    }

    @Override
    public Privilege findByUser(int utilisateur, int menu) {
        Privilege privilege = null;
        Query query = this.em.createQuery("SELECT p FROM Privilege p WHERE p.idutilisateur.idutilisateur=:utilisateur AND p.idmenu.idmenu=:menu");
        query.setParameter("utilisateur", (utilisateur)).setParameter("menu", (menu));
        if (!query.getResultList().isEmpty()) {
            privilege = (Privilege) query.getResultList().get(0);
        }
        return privilege;
    }

    @Override
    public void delete(int menu, int utilisateur) {
        try {
            Query query = this.em.createQuery("DELETE FROM Privilege p WHERE p.idmenu.idmenu=:menu AND p.idutilisateur.idutilisateur=:utilisateur");
            query.setParameter("menu", (menu)).setParameter("utilisateur", (utilisateur));
            query.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
