package sessions;

import entities.AnneeMois;
import entities.Commande;
import entities.Facture;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

@Local
public interface CommandeFacadeLocal {

    void create(Commande commande);

    void edit(Commande commande);

    void remove(Commande commande);

    Commande find(Object id);

    List<Commande> findAll();

    List<Commande> findRange(int[] range);

    int count();

    Long nextVal();

    List<Commande> findByFacture(Facture facture);

    List<Commande> findByDate(Date date) throws Exception;

    List<Commande> findByMois(AnneeMois anneeMois) throws Exception;

    List<Commande> findByLot(Long idLot) throws Exception;

    List<Commande> findByFamilleIntervalNotCount(int idfamille, int idbailleur, Date date_debut, Date date_fin) throws Exception;
    
    void deleteByIdfacture(Long idFacture);
}
