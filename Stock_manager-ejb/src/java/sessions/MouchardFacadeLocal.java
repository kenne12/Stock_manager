package sessions;

import entities.Mouchard;
import java.util.List;
import javax.ejb.Local;

@Local
public interface MouchardFacadeLocal {

    void create(Mouchard mouchard);

    void edit(Mouchard mouchard);

    void remove(Mouchard mouchard);

    Mouchard find(Object id);

    List<Mouchard> findAll();

    List<Mouchard> findRange(int[] id);

    int count();

    Long nextVal();
}
