package sessions;

import entities.Utilisateur;
import java.util.List;
import javax.ejb.Local;

@Local
public interface UtilisateurFacadeLocal {

    void create(Utilisateur paramUtilisateur);

    void edit(Utilisateur paramUtilisateur);

    void remove(Utilisateur paramUtilisateur);

    Utilisateur find(Object paramObject);

    List<Utilisateur> findAll();

    List<Utilisateur> findRange(int[] paramArrayOfint);

    int count();

    Integer nextVal();

    Utilisateur login(String paramString1, String paramString2) throws Exception;

    List<Utilisateur> findByActif(Boolean paramBoolean);
}
