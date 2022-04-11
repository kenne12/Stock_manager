package sessions;

import entities.FactureBailleurArticle;
import java.util.List;
import javax.ejb.Local;

@Local
public interface FactureBailleurArticleFacadeLocal {

    void create(FactureBailleurArticle paramFactureBailleurArticle);

    void edit(FactureBailleurArticle paramFactureBailleurArticle);

    void remove(FactureBailleurArticle paramFactureBailleurArticle);

    FactureBailleurArticle find(Object paramObject);

    List<FactureBailleurArticle> findAll();

    List<FactureBailleurArticle> findRange(int[] paramArrayOfint);

    int count();

    Long nextVal();

    List<FactureBailleurArticle> findByFacture(Long paramLong) throws Exception;
}
