package sessions;

import entities.Caisse;
import java.util.List;
import javax.ejb.Local;

@Local
public interface CaisseFacadeLocal {

    void create(Caisse paramCaisse);

    void edit(Caisse paramCaisse);

    void remove(Caisse paramCaisse);

    Caisse find(Object paramObject);

    List<Caisse> findAll();

    List<Caisse> findRange(int[] paramArrayOfint);

    int count();

    Integer nextVal();
}
