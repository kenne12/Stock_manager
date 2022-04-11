package sessions;

import entities.Journee;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

@Local
public interface JourneeFacadeLocal {

    void create(Journee journee);

    void edit(Journee journee);

    void remove(Journee journee);

    Journee find(Object id);

    List<Journee> findAll();

    List<Journee> findRange(int[] range);

    int count();

    Long nextVal();

    List<Journee> find(Date date);

    List<Journee> findByIdmois(int paramInt);

    List<Journee> findByIdInterval(Date dateDebut, Date dateFin);
}
