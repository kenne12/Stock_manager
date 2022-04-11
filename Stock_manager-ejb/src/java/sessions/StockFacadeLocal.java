package sessions;

import entities.AnneeMois;
import entities.Stock;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

@Local
public interface StockFacadeLocal {

    void create(Stock stock);

    void edit(Stock stock);

    void remove(Stock stock);

    Stock find(Object id);

    List<Stock> findAll();

    List<Stock> findRange(int[] range);

    int count();

    Long nextLongVal();

    Long nextVal(AnneeMois anneeMois);

    List<Stock> findAllRange();

    List<Stock> findByMois(AnneeMois anneeMois) throws Exception;

    List<Stock> findAllDate(Date date) throws Exception;

    List<Stock> findByInterval(Date dateDebut, Date dateFin) throws Exception;

    void deleteStockById(long id);
}
