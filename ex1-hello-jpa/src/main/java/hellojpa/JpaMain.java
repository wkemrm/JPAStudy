package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member1 = new Member(150L, "A");
            Member member2 = new Member(160L, "B");

            //1차캐시 등록 후 sql저장소에 쿼리를 쌓는다.
            em.persist(member1);
            em.persist(member2);

            System.out.println("============");
            //이때 db에 sql저장소에 있는 쿼리를 날린다.
            //버펄링을 모아서 write하는 이점을 얻을 수 있다.
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
