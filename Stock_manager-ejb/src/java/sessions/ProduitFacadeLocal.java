package sessions;

import entities.Famille;
import entities.Produit;
import java.util.List;
import javax.ejb.Local;

@Local
public interface ProduitFacadeLocal {

    void create(Produit paramProduit);

    void edit(Produit paramProduit);

    void remove(Produit paramProduit);

    Produit find(Object paramObject);

    List<Produit> findAll();

    List<Produit> findRange(int[] paramArrayOfint);

    int count();

    Long nextVal();

    List<Produit> findByCode(String paramString) throws Exception;

    List<Produit> findAllRange();

    List<Produit> findAllRange(boolean paramBoolean);

    List<Produit> findAllRange1();

    List<Produit> findAllRangeNom();

    List<Produit> findByFamille(Famille paramFamille) throws Exception;

    List<Produit> findByFamille(Famille paramFamille, boolean paramBoolean) throws Exception;

    List<Produit> findSousStock();

    List<Produit> findByPerissable(boolean paramBoolean) throws Exception;
}
