package sessions;

import entities.Famille;
import java.util.List;
import javax.ejb.Local;

@Local
public interface FamilleFacadeLocal {

    void create(Famille paramFamille);

    void edit(Famille paramFamille);

    void remove(Famille paramFamille);

    Famille find(Object paramObject);

    List<Famille> findAll();

    List<Famille> findRange(int[] paramArrayOfint);

    int count();

    Integer nextVal();

    List<Famille> findAllRange();
}
