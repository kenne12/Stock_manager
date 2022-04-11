package sessions;

import entities.AnneeMois;
import entities.Depense;
import java.util.List;
import javax.ejb.Local;

@Local
public interface DepenseFacadeLocal {
  void create(Depense paramDepense);
  
  void edit(Depense paramDepense);
  
  void remove(Depense paramDepense);
  
  Depense find(Object paramObject);
  
  List<Depense> findAll();
  
  List<Depense> findRange(int[] paramArrayOfint);
  
  int count();
  
  Long nextVal();
  
  List<Depense> findAllRange();
  
  List<Depense> findBymois(AnneeMois paramAnneeMois) throws Exception;
}


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-ejb.jar!\sessions\DepenseFacadeLocal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */