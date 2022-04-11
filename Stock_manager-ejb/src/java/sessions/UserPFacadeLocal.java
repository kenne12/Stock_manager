package sessions;

import entities.UserP;
import java.util.List;
import javax.ejb.Local;

@Local
public interface UserPFacadeLocal {

    void create(UserP paramUserP);

    void edit(UserP paramUserP);

    void remove(UserP paramUserP);

    UserP find(Object paramObject);

    List<UserP> findAll();

    List<UserP> findRange(int[] paramArrayOfint);

    int count();

    Integer nextVal();

    List<UserP> findAllRange();
}
