package sessions;

import entities.MvtStock;
import java.util.List;
import javax.ejb.Local;

@Local
public interface MvtStockFacadeLocal {

    void create(MvtStock mvtStock);

    void edit(MvtStock mvtStock);

    void remove(MvtStock mvtStock);

    MvtStock find(Object id);

    List<MvtStock> findAll();

    List<MvtStock> findRange(int[] range);

    int count();

    Long nextLongVal();
    
    void deleteMvtById(long id);
}
