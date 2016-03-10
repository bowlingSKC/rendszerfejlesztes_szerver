package pe.rendszerfejlesztes;

/**
 * A rendszerben lévő konstansok definiálására szolgáló interfész.
 * Az interfészen belüli változók értékeit nem lehet megváltoztatni.
 */
public interface Constants {

    /**
     * A felhasználói szintű interfész biztonsítása. Ez az érték szerepel az adatbázisban.
     */
    int USER_PRIVILAGE_ID = 1;

    /**
     * Adminisztrátori szintű interfész biztonsítása. Ez az érték szerepel az adatbázisban.
     */
    int ADMIN_PRIVILAGE_ID = 2;

}
