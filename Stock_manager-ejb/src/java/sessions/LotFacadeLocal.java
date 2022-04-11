package sessions;

import entities.Lot;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

@Local
public interface LotFacadeLocal {

    void create(Lot paramLot);

    void edit(Lot paramLot);

    void remove(Lot paramLot);

    Lot find(Object paramObject);

    List<Lot> findAll();

    List<Lot> findRange(int[] paramArrayOfint);

    int count();

    Long nextLongVal();

    List<Lot> findAllRange();

    List<Lot> findAllRange(boolean paramBoolean);

    List<Lot> findByArticle1(Long paramLong, boolean paramBoolean, Date paramDate) throws Exception;

    List<Lot> findByArticle(Long paramLong, boolean paramBoolean) throws Exception;

    List<Lot> findByArticle(Long paramLong, boolean paramBoolean, Date paramDate) throws Exception;

    List<Lot> findByArticle(Long paramLong, boolean paramBoolean1, Date paramDate, boolean paramBoolean2) throws Exception;

    List<Lot> findByIdbailleur(int paramInt, boolean paramBoolean) throws Exception;

    List<Lot> findByIdbailleurIdfamille(int paramInt1, int paramInt2, boolean paramBoolean) throws Exception;
}
