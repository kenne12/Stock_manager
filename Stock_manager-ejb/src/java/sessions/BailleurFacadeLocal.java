package sessions;

import entities.Bailleur;
import java.util.List;
import javax.ejb.Local;

@Local
public interface BailleurFacadeLocal {

    void create(Bailleur paramBailleur);

    void edit(Bailleur paramBailleur);

    void remove(Bailleur paramBailleur);

    Bailleur find(Object paramObject);

    List<Bailleur> findAll();

    List<Bailleur> findRange(int[] paramArrayOfint);

    int count();

    Integer nextVal();

    List<Bailleur> findAllRange();
}
