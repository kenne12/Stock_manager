package sessions;

import entities.Fournisseur;
import java.util.List;
import javax.ejb.Local;

@Local
public interface FournisseurFacadeLocal {

    void create(Fournisseur paramFournisseur);

    void edit(Fournisseur paramFournisseur);

    void remove(Fournisseur paramFournisseur);

    Fournisseur find(Object paramObject);

    List<Fournisseur> findAll();

    List<Fournisseur> findRange(int[] paramArrayOfint);

    int count();

    Integer nextVal();

    List<Fournisseur> findAllRange();
}
