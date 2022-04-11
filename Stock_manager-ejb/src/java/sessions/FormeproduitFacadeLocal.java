package sessions;

import entities.Formeproduit;
import java.util.List;
import javax.ejb.Local;

@Local
public interface FormeproduitFacadeLocal {

    void create(Formeproduit paramFormeproduit);

    void edit(Formeproduit paramFormeproduit);

    void remove(Formeproduit paramFormeproduit);

    Formeproduit find(Object paramObject);

    List<Formeproduit> findAll();

    List<Formeproduit> findRange(int[] paramArrayOfint);

    int count();
}
