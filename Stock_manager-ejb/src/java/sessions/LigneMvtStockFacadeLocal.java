package sessions;

import entities.LigneMvtStock;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

@Local
public interface LigneMvtStockFacadeLocal {

    void create(LigneMvtStock ligneMvtStock);

    void edit(LigneMvtStock ligneMvtStock);

    void remove(LigneMvtStock ligneMvtStock);

    LigneMvtStock find(Object id);

    List<LigneMvtStock> findAll();

    List<LigneMvtStock> findRange(int[] range);

    int count();

    Long nextLongVal();

    List<LigneMvtStock> findByMvt(Long idMvt) throws Exception;

    LigneMvtStock findByMvtIdLot(Long idMvt, Long idLot) throws Exception;

    List<LigneMvtStock> findByIntervale(Date date_debut, Date date_fin) throws Exception;

    List<LigneMvtStock> findByDate(Date date) throws Exception;

    List<LigneMvtStock> findByIdArticleIntervale(long idArticle, Date date_debut, Date date_fin) throws Exception;

    void deleteByIdMvtStock(long idMtStock);
}
