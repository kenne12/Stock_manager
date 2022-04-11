package sessions;

import entities.Parametrage;
import java.util.List;
import javax.ejb.Local;

@Local
public interface ParametrageFacadeLocal {

    void create(Parametrage paramParametrage);

    void edit(Parametrage paramParametrage);

    void remove(Parametrage paramParametrage);

    Parametrage find(Object paramObject);

    List<Parametrage> findAll();

    List<Parametrage> findRange(int[] paramArrayOfint);

    int count();
}
