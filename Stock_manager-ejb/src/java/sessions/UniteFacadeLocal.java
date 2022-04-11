package sessions;

import entities.Unite;
import java.util.List;
import javax.ejb.Local;

@Local
public interface UniteFacadeLocal {

    void create(Unite paramUnite);

    void edit(Unite paramUnite);

    void remove(Unite paramUnite);

    Unite find(Object paramObject);

    List<Unite> findAll();

    List<Unite> findRange(int[] paramArrayOfint);

    int count();
}
