package sessions;

import entities.Formestockage;
import java.util.List;
import javax.ejb.Local;

@Local
public interface FormestockageFacadeLocal {

    void create(Formestockage paramFormestockage);

    void edit(Formestockage paramFormestockage);

    void remove(Formestockage paramFormestockage);

    Formestockage find(Object paramObject);

    List<Formestockage> findAll();

    List<Formestockage> findRange(int[] paramArrayOfint);

    int count();
}
