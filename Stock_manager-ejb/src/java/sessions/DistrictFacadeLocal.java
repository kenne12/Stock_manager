package sessions;

import entities.District;
import java.util.List;
import javax.ejb.Local;

@Local
public interface DistrictFacadeLocal {
  void create(District paramDistrict);
  
  void edit(District paramDistrict);
  
  void remove(District paramDistrict);
  
  District find(Object paramObject);
  
  List<District> findAll();
  
  List<District> findRange(int[] paramArrayOfint);
  
  int count();
  
  Integer nextVal();
  
  List<District> findAllRange();
}


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-ejb.jar!\sessions\DistrictFacadeLocal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */