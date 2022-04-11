package sessions;

import entities.LigneFactBailleur;
import java.util.List;
import javax.ejb.Local;

@Local
public interface LigneFactBailleurFacadeLocal {

    void create(LigneFactBailleur paramLigneFactBailleur);

    void edit(LigneFactBailleur paramLigneFactBailleur);

    void remove(LigneFactBailleur paramLigneFactBailleur);

    LigneFactBailleur find(Object paramObject);

    List<LigneFactBailleur> findAll();

    List<LigneFactBailleur> findRange(int[] paramArrayOfint);

    int count();

    Long nextVal();

    List<LigneFactBailleur> findByFacture(Long paramLong) throws Exception;
}
