package sessions;

import entities.LigneCommandeClient;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

@Local
public interface LigneCommandeClientFacadeLocal {

    void create(LigneCommandeClient paramLigneCommandeClient);

    void edit(LigneCommandeClient paramLigneCommandeClient);

    void remove(LigneCommandeClient paramLigneCommandeClient);

    LigneCommandeClient find(Object paramObject);

    List<LigneCommandeClient> findAll();

    List<LigneCommandeClient> findRange(int[] paramArrayOfint);

    int count();

    Long nextVal();

    List<LigneCommandeClient> findByDate(Date paramDate) throws Exception;

    List<LigneCommandeClient> findByCommande(Long paramLong) throws Exception;

    List<LigneCommandeClient> findByMois(int paramInt) throws Exception;

    List<LigneCommandeClient> findByLot(Long paramLong) throws Exception;
}
