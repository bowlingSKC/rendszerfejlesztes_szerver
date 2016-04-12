package pe.rendszerfejlesztes.database;

import pe.rendszerfejlesztes.modell.Ticket;

import javax.ejb.Local;
import java.util.List;

@Local
public interface TicketConnector {

    List<Ticket> getTicketsByUserId(Integer userId);
    Ticket bookTicket(Ticket ticket);
    void deleteTicket(Ticket ticket);
    void setTicketPaid(Ticket ticket);
    List<Ticket> getAllTicket();

}
