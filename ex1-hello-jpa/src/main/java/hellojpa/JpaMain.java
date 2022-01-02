package hellojpa;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Team team1 = new Team();
            team1.setName("팀A");
            em.persist(team1);
            Team team2 = new Team();
            team2.setName("팀B");
            em.persist(team2);
            Team team3 = new Team();
            team3.setName("팀C");
            em.persist(team3);

            Member member1 = new Member();
            member1.setUsername("멤버1");
            member1.setTeam(team1);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("멤버2");
            member2.setTeam(team1);
            em.persist(member2);

            Member member3 = new Member();
            member3.setUsername("멤버3");
            member3.setTeam(team2);
            em.persist(member3);

            em.flush();
            em.clear();

            String jpql = "select m from Member m";
            List<Member> resultList = em.createQuery(jpql, Member.class).getResultList();

            for (Member member : resultList) {
                System.out.println("member.getUsername() + member.getTeam().getName() = " + member.getUsername() +"    " +  member.getTeam().getName());
            }
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        emf.close();
    }
}
