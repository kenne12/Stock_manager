package sessions;

import entities.AnneeMois;
import entities.Personnel;
import entities.Salaire;
import java.util.List;
import javax.ejb.Local;

@Local
public interface SalaireFacadeLocal {
  void create(Salaire paramSalaire);
  
  void edit(Salaire paramSalaire);
  
  void remove(Salaire paramSalaire);
  
  Salaire find(Object paramObject);
  
  List<Salaire> findAll();
  
  List<Salaire> findRange(int[] paramArrayOfint);
  
  int count();
  
  Long nextVal();
  
  List<Salaire> findAllRange();
  
  List<Salaire> findByPersonnel(Personnel paramPersonnel, AnneeMois paramAnneeMois) throws Exception;
  
  List<Salaire> findByMois(AnneeMois paramAnneeMois) throws Exception;
}


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-ejb.jar!\sessions\SalaireFacadeLocal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */