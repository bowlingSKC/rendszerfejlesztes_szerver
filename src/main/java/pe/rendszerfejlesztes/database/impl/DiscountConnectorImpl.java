package pe.rendszerfejlesztes.database.impl;

import pe.rendszerfejlesztes.database.DiscountConnector;
import pe.rendszerfejlesztes.database.EmFactory;
import pe.rendszerfejlesztes.modell.Discount;
import pe.rendszerfejlesztes.modell.Ticket;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class DiscountConnectorImpl implements DiscountConnector{
    EntityManager em = EmFactory.getEntityManager();

    @Override
    public boolean updateDiscount(Discount discount, Integer id){
        //Ticket found = (Ticket)em.createQuery("SELECT t FROM Ticket t WHERE t.id LIKE :ticid").setParameter("ticid",id).getSingleResult();
        Ticket old = em.find(Ticket.class, id);
        if(old != null){
            System.out.println(discount);
            System.out.println(old);
            em.getTransaction().begin();
            //em.remove(old);
            old.setDiscount(discount);
            System.out.println(old);
            //em.persist(old);
            //em.refresh(discount);
            //em.refresh(old);
            em.merge(old);
            em.flush();
            em.getTransaction().commit();
            return true;
        }

        return false;
    }

    @Override
    public List<Discount> getAllDiscount(){
        List<Discount> discounts = em.createQuery("SELECT d FROM Discount d").getResultList();
        return discounts;
    }
}
