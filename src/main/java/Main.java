import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


public class Main {
    public static void main(String[] args) throws Exception{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("NetCFMySQL");
        EntityManager em = emf.createEntityManager();

        em.close();

    }
}
