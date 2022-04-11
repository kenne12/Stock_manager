package sessions;

import entities.Client;
import entities.Versement;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

@Local
public interface VersementFacadeLocal {

    void create(Versement paramVersement);

    void edit(Versement paramVersement);

    void remove(Versement paramVersement);

    Versement find(Object paramObject);

    List<Versement> findAll();

    List<Versement> findRange(int[] paramArrayOfint);

    int count();

    Long nextVal();

    Long nextVal(Date paramDate1, Date paramDate2) throws Exception;

    List<Versement> find(Client paramClient);

    List<Versement> find(Client paramClient, Date paramDate1, Date paramDate2);

    List<Versement> find(Client paramClient, Date paramDate);

    List<Versement> findAllRange(int paramInt);
}
