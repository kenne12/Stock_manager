package sessions;

import entities.Stock;
import entities.StockProduit;
import java.util.List;
import javax.ejb.Local;

@Local
public interface StockProduitFacadeLocal {

    void create(StockProduit stockProduit);

    void edit(StockProduit stockProduit);

    void remove(StockProduit stockProduit);

    StockProduit find(Object object);

    List<StockProduit> findAll();

    List<StockProduit> findRange(int[] arrayOfint);

    int count();

    Long nextVal();

    List<StockProduit> findByStock(Stock stock);

    List<StockProduit> findAllRange();

    List<StockProduit> findByLot(Long idlot) throws Exception;

    List<StockProduit> findByMois(int idmois) throws Exception;

    void deleteByIdStock(long idStock);
}
