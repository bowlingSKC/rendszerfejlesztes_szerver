package pe.rendszerfejlesztes.modell;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

/**
 * Egy helyszínt leíró osztály.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "location")
public class Location {

    /**
     * A helyszínhez tatozó elsődleges kulcs értéke az adatbázisban.
     */
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * A helyszín neve.
     * Nem vehet fel null értéket adatbázisba való beszúrás előtt.
     */
    @Basic(optional = false)
    @NotNull
    @Column(name = "name")
    private String name;

    /**
     * A helyszín pontos címe.
     * Nem vehet fel null értéket adatbázisba való beszúrás előtt.
     */
    @Basic(optional = false)
    @NotNull
    @Column(name = "address")
    private String address;

    /**
     * A helyszín koordinátájának hosszúsági értéke.
     * Nem vehet fel null értéket adatbázisba való beszúrás előtt.
     */
    @Basic(optional = false)
    @Column(name = "longitude")
    private Double longitude;

    /**
     * A helyszín koordinátájának szélességi értéke.
     * Nem vehet fel null értéket adatbázisba való beszúrás előtt.
     */
    @Basic(optional = false)
    @Column(name = "latitude")
    private Double latitude;

    /**
     * A helyszínhez tartozó események listája.
     * Nem kerül szerializására a válaszban.
     */
    @XmlTransient
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "location")
    private List<Event> events;

    /**
     * Paraméter nélküli konstruktor.
     * Semmilyen értéket nem állít be az adattagoknak.
     */
    public Location() {
    }

    /**
     * Konstruktor.
     * @param name a hely neve
     * @param address a hely pontos címe
     * @param longitude a helyhez tartozó hosszúsági érték
     * @param latitude a helyhez tartozó szélességi érték
     */
    public Location(String name, String address, Double longitude, Double latitude) {
        this.latitude = latitude;
        this.name = name;
        this.address = address;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                '}';
    }

}
