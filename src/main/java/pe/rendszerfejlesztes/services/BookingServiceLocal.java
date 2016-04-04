package pe.rendszerfejlesztes.services;

import pe.rendszerfejlesztes.modell.Sector;
import pe.rendszerfejlesztes.modell.Ticket;
import pe.rendszerfejlesztes.modell.User;

import javax.ejb.Local;
import java.util.List;

/**
 * Az foglalások adatbázisműveletét támogató interfész.
 * Az összes implementációnak tartalmaznia kell ezeket a műveleteket a teljes működéshez.
 * @see pe.rendszerfejlesztes.modell.Ticket
 */
@Local
public interface BookingServiceLocal {

    /**
     * A paraméterben kapott felhasználó összes foglalt jegyeit keresi meg az adatbázisban.
     * @param user felhasználó
     * @return a felhasználóhoz tartozó összes jegy
     */
    List<Ticket> getUserTicket(User user);

    /**
     * Jegyfoglalás elmentése az adatbázisba. Sikertelen foglalás esetén null értékkel tér vissza.
     * @param ticket jegy
     * @return a perzisztens jegy objektum
     */
    Ticket bookTicket(Ticket ticket);

    /**
     * Foglalás törlése az adatbázisból.
     */
    boolean deleteTicket(Ticket ticket);

    /**
     * Adatbázisból kikeresi a jegyhez tartozó szektort.
     * @param ticket jegy
     * @return a jegyhez tartozó szektor
     */
    Sector getSectorByTicket(Ticket ticket);

}
