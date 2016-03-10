package pe.rendszerfejlesztes.modell;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

/**
 * Egy előadót leíró osztály.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
@Entity
@Table(name = "performer")
public class Performer {

    /**
     * Az előadóhoz tartozó elsődleges kulcs az adatbáziban.
     */
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * Az előadó neve.
     * Nem vehet fel null értéket adatbázisba való beszúrás előtt.
     */
    @Basic(optional = false)
    @NotNull
    @Column(name = "name")
    private String name;

    /**
     * Az előadóhoz tartozó leírás.
     * Nem vehet fel null értéket adatbázisba való beszúrás előtt.
     */
    @Basic(optional = false)
    @Column(name = "description")
    private String description;

    /**
     * Az előadóhoz tartozó fellépések listája.
     * Nem kerül szerializálásra a válaszban.
     */
    @XmlTransient
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "performer")
    private List<Event> events;

    /**
     * Paraméter nélküli konstruktor.
     * Semmilyen értéket nem állít be az adattagoknak.
     */
    public Performer(){

    }

    /**
     * Konstruktor.
     * @param name az előadó neve
     * @param description az előadóhoz tartozó leírás
     */
    public Performer(String name, String description){
        this.name = name;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    @Override
    public String toString() {
        return "Performer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}
