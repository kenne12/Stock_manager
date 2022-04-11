package sessions;

import entities.AnneeMois;
import entities.Inventaire;
import java.util.List;
import javax.ejb.Local;

@Local
public interface InventaireFacadeLocal {

    void create(Inventaire paramInventaire);

    void edit(Inventaire paramInventaire);

    void remove(Inventaire paramInventaire);

    Inventaire find(Object paramObject);

    List<Inventaire> findAll();

    List<Inventaire> findRange(int[] paramArrayOfint);

    int count();

    Long nextVal();

    Long nextVal(AnneeMois paramAnneeMois);

    List<Inventaire> findAllRange() throws Exception;
}
