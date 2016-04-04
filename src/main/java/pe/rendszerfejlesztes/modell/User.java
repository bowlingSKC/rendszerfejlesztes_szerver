package pe.rendszerfejlesztes.modell;

import pe.rendszerfejlesztes.Constants;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * A rendszerben lévő felhasználók osztálya.
 * Felhasználó lehet a belépett vendég és az adminisztrátor.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
@Entity
@Table(name = "user")
public class User {

    /**
     * Az adatbázisban található elsődleges kulcs értéke.
     */
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * A felhasználó neve szóköz(ökkel) elválasztva.
     * Nem vehet fel null értéket adatbázisba való beszúrás előtt.
     */
    @Basic(optional = false)
    @NotNull
    @Column(name = "name")
    private String name;

    /**
     *A felhasználó E-mail címe. Az E-mail cím ellenőrzés kliens oldalon van ellenőrizve.
     * Nem vehet fel null értéket adatbázisba való beszúrás előtt.
     */
    @Basic(optional = false)
    @NotNull
    @Column(name = "email")
    private String email;

    /**
     * A felhasználó jelszava SHA-512 kódolással hash-elve.
     * Nem vehet fel null értéket adatbázisba való beszúrás előtt.
     */
    @Basic(optional = false)
    @NotNull
    @Column(name = "password")
    private String password;

    /**
     * A felhasználó jogosultsági szintje.
     * Az értékeket a {@link pe.rendszerfejlesztes.Constants} interfészben található adattagok definiálják.
     * Nem vehet fel null értéket adatbázisba való beszúrás előtt.
     */
    @Basic(optional = false)
    @NotNull
    @Column(name = "privilage")
    private Integer privilage;

    /**
     * A felhasználóhoz tartozó foglalt jegyek.
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Ticket> tickets;

    /**
     * Paraméter nélküli konstruktor.
     * Semmilyen értéket nem állít be az adattagoknak.
     */
    public User() {

    }

    public User(int userId) {
        this.id = userId;
    }

    /**
     * Konsuktor.
     * Felhasználói szintű jogosultságot állít be.
     * @param name a felhasználó neve szóköz(ökkel) elválasztva
     * @param email a felhasználó E-mail címe
     * @param password a felhasználó titkosított jelszava
     */
    public User(String name, String email, String password) {
        this(name, email, password, Constants.USER_PRIVILAGE_ID);
    }

    /**
     * Jogosultsági szinttel bővített konstruktor.
     * @param name a felhasználó neve szóköz(ökkel) elválasztva
     * @param email a felhasználó E-mail címe
     * @param password a felhasználó titkosított jelszava
     * @param privilage jogosultsági szint, amit a {@link pe.rendszerfejlesztes.Constants} interfész definiál
     */
    public User(String name, String email, String password, Integer privilage) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.privilage = privilage;
    }

    /**
     * Az objektum adatbázisban lévő eldősleges kulcsát adja vissza.
     * @return adatbázisban lévő eldősleges kulcs
     */
    public Integer getId() {
        return id;
    }

    /**
     * Elődleges kulcs értékét álltja be.
     * Adatbázisba történő beszúráskor nem kell használni, automatikusan beszúródik a következő érték.
     * @param id elsődleges kulcs
     */
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPrivilage() {
        return privilage;
    }

    public void setPrivilage(Integer privilage) {
        this.privilage = privilage;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", privilage=" + privilage +
                '}';
    }

}
