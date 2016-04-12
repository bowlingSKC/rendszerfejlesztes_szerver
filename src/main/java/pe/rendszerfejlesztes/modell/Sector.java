package pe.rendszerfejlesztes.modell;


import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

/**
 * Egy eseményhez tartozó szektor leírása.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "sector")
public class Sector {

    /**
     * A szektorhoz tartozó elsődleges kulcs.
     */
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * A szektorban lévő sorok száma.
     */
    @Basic(optional = false)
    @Column(name = "num_of_rows")
    private Integer numOfRows;

    /**
     * A szektorban lévő oszlopok száma.
     */
    @Basic(optional = false)
    @Column(name = "num_of_cols")
    private Integer numOfCols;

    /**
     * A szektorhoz tartozó ár.
     */
    @Basic(optional = false)
    @Column(name = "price")
    private Integer price;

    /**
     * Mely eseményhez tartozik a szektor.
     */
    @XmlTransient
    @JoinColumn(name = "event_id", referencedColumnName = "id")
    @ManyToOne
    private Event event;

    /**
     * A szektor a színpadtól való távolsága.
     */
    @Basic(optional = false)
    @Column(name = "depth")
    private Integer depth;

    /**
     * A szektorhoz tartozó jegyek listája.
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sector")
    private List<Ticket> tickets;

    /**
     * Paraméter nélküli konstruktor.
     * Semmilyen értéket nem állít be az adattagoknak.
     */
    public Sector() {

    }

    public Sector(int sectorId) {
        this.id = sectorId;
    }

    /**
     * Konstruktor.
     * @param numOfRows sorok száma
     * @param numOfCols oszlopok száma
     * @param price ár
     * @param event mely eseményhez tartozik
     * @param depth milyen messze van a színpadtól
     */
    public Sector(Integer numOfRows, Integer numOfCols, Integer price, Event event, Integer depth) {
        this.numOfRows = numOfRows;
        this.numOfCols = numOfCols;
        this.price = price;
        this.event = event;
        this.depth = depth;
    }

    public boolean isFull() {
        if( tickets.size() == numOfCols * numOfRows ) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isSeatReserved(int col, int row) {
        if( event.isSeats() ) {
            for(Ticket ticket : tickets) {
                if( ticket.getCol().equals(col) && ticket.getRow().equals(row) ) {
                    return true;
                }
            }
        }
        return false;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumOfRows() {
        return numOfRows;
    }

    public void setNumOfRows(Integer numOfRows) {
        this.numOfRows = numOfRows;
    }

    public Integer getNumOfCols() {
        return numOfCols;
    }

    public void setNumOfCols(Integer numOfCols) {
        this.numOfCols = numOfCols;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Integer getDepth() {
        return depth;
    }

    public void setDepth(Integer depth) {
        this.depth = depth;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public String toString() {
        return "Sector{" +
                "id=" + id +
                ", numOfRows=" + numOfRows +
                ", numOfCols=" + numOfCols +
                ", price=" + price +
                ", event=" + event +
                ", depth=" + depth +
                ", tickets=" + tickets +
                '}';
    }
}
