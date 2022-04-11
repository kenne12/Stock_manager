package sessions;

import entities.FactureBailleur;
import java.util.List;
import javax.ejb.Local;

@Local
public interface FactureBailleurFacadeLocal {
  void create(FactureBailleur paramFactureBailleur);
  
  void edit(FactureBailleur paramFactureBailleur);
  
  void remove(FactureBailleur paramFactureBailleur);
  
  FactureBailleur find(Object paramObject);
  
  List<FactureBailleur> findAll();
  
  List<FactureBailleur> findRange(int[] paramArrayOfint);
  
  int count();
  
  Long nextVal();
  
  List<FactureBailleur> findAllRange();
}


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-ejb.jar!\sessions\FactureBailleurFacadeLocal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */