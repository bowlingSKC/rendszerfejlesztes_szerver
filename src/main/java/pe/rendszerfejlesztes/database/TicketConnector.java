package pe.rendszerfejlesztes.database;

import pe.rendszerfejlesztes.modell.Ticket;

import java.util.List;

public interface TicketConnector {

    List<Ticket> getTicketsByUserId(Integer userId);
    Ticket bookTicket(Ticket ticket);
    void deleteTicket(Ticket ticket);
    void setTicketPaid(Ticket ticket);

}
