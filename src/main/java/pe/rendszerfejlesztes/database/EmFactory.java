package pe.rendszerfejlesztes.database;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EmFactory {

    private static final String SERVER_UNIT = "serverUnit";

    public static EntityManager getEntityManager() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory( SERVER_UNIT );
        return entityManagerFactory.createEntityManager();
    }

}
