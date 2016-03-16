package pe.rendszerfejlesztes.modell;

import pe.rendszerfejlesztes.Constants;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.Date;

/**
 * Egy eseményhez tartozó jegy, amit a felhasználó foglal.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
@Entity
@Table(name = "ticket")
public class Ticket {

    /**
     * A jegyhez tartozó elsődleges kulcs az adatbázisban.
     */
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * Melyik szektorhoz tartozik a jegy.
     * A szektoron keresztül megtudható az esemény neve is.
     */
    @XmlTransient
    @JoinColumn(name = "sector_id", referencedColumnName = "id")
    @ManyToOne
    private Sector sector;

    /**
     * Melyik felhasználóhoz tartozik jegy.
     */
    @XmlTransient
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
    private User user;

    /**
     * Mikor lett foglalva a jegy.
     */
    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "booked_time")
    private Date bookedTime;

    /**
     * Ki lett-e fizetve a jegy.
     */
    @Basic(optional = false)
    @Column(name = "paid")
    private boolean paid;

    /**
     * A szektor melyik sorához tartozik a jegy.
     */
    @Basic(optional = false)
    @Column(name = "seat_row")
    private Integer row;

    /**
     * A szektor melyik oszlopához tartozik a jegy.
     */
    @Basic(optional = false)
    @Column(name = "set_col")
    private Integer col;

    /**
     * A jegy állapota.
     */
    @Basic(optional = false)
    @Column(name = "status")
    private Integer status;

    /**
     * Paraméter nélküli konstruktor.
     * Semmilyen értéket nem állít be az adattagoknak.
     */
    public Ticket() {

    }

    /**
     * Konstruktor.
     * @param sector mely szektorhoz tartozik
     * @param user ki foglalt
     * @param bookedTime mikor foglalta
     * @param paid ki lett-e fizetve
     * @param row sor
     * @param col oszlop
     * @param status jegy állapota
     */
    public Ticket(Sector sector, User user, Date bookedTime, boolean paid, Integer row, Integer col, Integer status) {
        this.sector = sector;
        this.user = user;
        this.bookedTime = bookedTime;
        this.paid = paid;
        this.row = row;
        this.col = col;
        this.status = status;
    }

    /**
     * Konstruktor.
     * @param sector mely szektorhoz tartozik
     * @param user ki foglalta
     * @param row sor
     * @param col osztlop
     */
    public Ticket(Sector sector, User user, Integer row, Integer col) {
        this.sector = sector;
        this.user = user;
        this.bookedTime = new Date();
        this.paid = false;
        this.row = row;
        this.col = col;
        this.status = Constants.TICKET_OPTIONAL;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getBookedTime() {
        return bookedTime;
    }

    public void setBookedTime(Date bookedTime) {
        this.bookedTime = bookedTime;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getCol() {
        return col;
    }

    public void setCol(Integer col) {
        this.col = col;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
