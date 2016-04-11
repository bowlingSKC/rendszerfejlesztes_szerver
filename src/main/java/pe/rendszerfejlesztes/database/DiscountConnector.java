package pe.rendszerfejlesztes.database;


import pe.rendszerfejlesztes.modell.Discount;

import javax.ejb.Local;
import java.util.List;

@Local
public interface DiscountConnector {
    boolean updateDiscount(Discount discount, Integer id);

    List<Discount> getAllDiscount();
}
