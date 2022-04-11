package sessions;

import entities.AnneeMois;
import entities.Facture;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

@Local
public interface FactureFacadeLocal {

    void create(Facture facture);

    void edit(Facture facture);

    void remove(Facture facture);

    Facture find(Object id);

    List<Facture> findAll();

    List<Facture> findRange(int[] range);

    int count();

    Long nextLongVal();

    Long nextVal(AnneeMois paramAnneeMois);

    List<Facture> findAllRange();

    List<Facture> findAllDate(Date paramDate) throws Exception;

    List<Facture> findByInterval(Date paramDate1, Date paramDate2) throws Exception;

    List<Facture> findByMois(int paramInt) throws Exception;

    List<Facture> findByAnnee(int paramInt) throws Exception;

    List<Facture> findClientDate(int paramInt, Date paramDate) throws Exception;

    List<Facture> findClientInterval(int paramInt, Date paramDate1, Date paramDate2) throws Exception;

    List<Facture> findNonregle() throws Exception;
    
    void deleteFactureById(long idFacture);
}
