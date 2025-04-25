package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {


    private final EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id); //Member.class = type id =primary key
    }

    public List<Member> findAll() {

        return em.createQuery("select m from Member m", Member.class)   //qpql 책 참조
                .getResultList();
    }  //회원 전체 조회

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name" ,Member.class)
                .setParameter("name",name)
                .getResultList();  //이름으로 회원조회
    }
}
