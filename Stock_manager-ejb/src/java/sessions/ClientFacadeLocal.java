package sessions;

import entities.Client;
import java.util.List;
import javax.ejb.Local;

@Local
public interface ClientFacadeLocal {

    void create(Client paramClient);

    void edit(Client paramClient);

    void remove(Client paramClient);

    Client find(Object paramObject);

    List<Client> findAll();

    List<Client> findRange(int[] paramArrayOfint);

    int count();

    Integer nextVal();

    List<Client> findAllRange();
}
