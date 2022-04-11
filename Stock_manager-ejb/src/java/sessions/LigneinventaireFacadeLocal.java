package sessions;

import entities.Ligneinventaire;
import java.util.List;
import javax.ejb.Local;

@Local
public interface LigneinventaireFacadeLocal {

    void create(Ligneinventaire paramLigneinventaire);

    void edit(Ligneinventaire ligneinventaire);

    void remove(Ligneinventaire ligneinventaire);

    Ligneinventaire find(Object id);

    List<Ligneinventaire> findAll();

    List<Ligneinventaire> findRange(int[] range);

    int count();

    Long nextVal();

    List<Ligneinventaire> findByInventaire(Long paramLong) throws Exception;

    void deleteByIdInventaire(long idInventaire);
}
