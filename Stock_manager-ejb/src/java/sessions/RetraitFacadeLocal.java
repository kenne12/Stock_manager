package sessions;

import entities.Client;
import entities.Retrait;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

@Local
public interface RetraitFacadeLocal {
  void create(Retrait paramRetrait);
  
  void edit(Retrait paramRetrait);
  
  void remove(Retrait paramRetrait);
  
  Retrait find(Object paramObject);
  
  List<Retrait> findAll();
  
  List<Retrait> findRange(int[] paramArrayOfint);
  
  int count();
  
  Long nextLongVal();
  
  List<Retrait> find(Client paramClient);
  
  List<Retrait> find(Client paramClient, Date paramDate1, Date paramDate2);
  
  List<Retrait> find(Client paramClient, Date paramDate);
}


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-ejb.jar!\sessions\RetraitFacadeLocal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */