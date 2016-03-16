package pe.rendszerfejlesztes.modell;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.List;

/**
 * Egy eseményt leíró osztály.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "event")
public class Event {

    /**
     * Az eseményhez tartozó elsődleges kulcs az adatbázisban.
     */
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * Az esemény neve.
     * Nem vehet fel null értéket adatbázisba való beszúrás előtt.
     */
    @Basic(optional = false)
    @NotNull
    @Column(name = "name")
    private String name;

    /**
     * Az esemény kezdési időpontja.
     * Nem vehet fel null értéket adatbázisba való beszúrás előtt.
     */
    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start")
    private Date start;

    /**
     * Az esemény hossza percben.
     * Nem vehet fel null értéket adatbázisba való beszúrás előtt.
     */
    @Basic(optional = false)
    @NotNull
    @Column(name = "duration")
    private Integer duration;

    /**
     * Az eseményhez tartozó leírás.
     */
    @Basic(optional = false)
    @Column(name = "description")
    private String description;

    /**
     * Az eseményhez tartozó ár.
     * Nem vehet fel null értéket adatbázisba való beszúrás előtt.
     */
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private Integer price;

    /**
     * Az eseményhez tarzozó helyszín.
     */
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    @ManyToOne
    private Location location;

    /**
     * Az eseményhez tartozó előadó.
     */
    @JoinColumn(name = "performer_id", referencedColumnName = "id")
    @ManyToOne
    private Performer performer;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "event")
    private List<Sector> sectorList;

    /**
     * Paraméter nélküli konstruktor.
     * Semmilyen értéket nem állít be az adattagoknak.
     */
    public Event() {
    }

    /**
     * Konstruktor.
     * @param name az esemény neve
     * @param start az esemény kezdési időpontja
     * @param duration az esemény hossza percben
     * @param description az eseményhez tartozó leírás
     * @param price az eseményhez tartozó ár
     */
    public Event(String name, Date start, Integer duration, String description, Integer price) {
        this.name = name;
        this.start = start;
        this.duration = duration;
        this.description = description;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Performer getPerformer() {
        return performer;
    }

    public void setPerformer(Performer performer) {
        this.performer = performer;
    }


    public List<Sector> getSectorList() {
        return sectorList;
    }

    public void setSectorList(List<Sector> sectorList) {
        this.sectorList = sectorList;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", start=" + start +
                ", duration=" + duration +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }

}
