package sessions;

import entities.Boutique;
import java.util.List;
import javax.ejb.Local;

@Local
public interface BoutiqueFacadeLocal {

    void create(Boutique paramBoutique);

    void edit(Boutique paramBoutique);

    void remove(Boutique paramBoutique);

    Boutique find(Object paramObject);

    List<Boutique> findAll();

    List<Boutique> findRange(int[] paramArrayOfint);

    int count();
}
