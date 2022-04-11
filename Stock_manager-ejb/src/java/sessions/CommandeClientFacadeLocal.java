package sessions;

import entities.CommandeClient;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

@Local
public interface CommandeClientFacadeLocal {

    void create(CommandeClient paramCommandeClient);

    void edit(CommandeClient paramCommandeClient);

    void remove(CommandeClient paramCommandeClient);

    CommandeClient find(Object paramObject);

    List<CommandeClient> findAll();

    List<CommandeClient> findRange(int[] paramArrayOfint);

    int count();

    Long nextLongVal();

    List<CommandeClient> findAllRange();

    List<CommandeClient> findAllDate(Date date) throws Exception;

    List<CommandeClient> findByInterval(Date dateDebut, Date dateFin) throws Exception;

    List<CommandeClient> findByLivre(boolean livree) throws Exception;
}
