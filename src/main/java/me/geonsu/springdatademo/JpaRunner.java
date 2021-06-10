package me.geonsu.springdatademo;

import org.hibernate.Session;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
@Transactional
public class JpaRunner implements ApplicationRunner {

    @PersistenceContext
    EntityManager entityManager; //JPA의 핵심이 되는 class. spring의 핵심인 ApplicationContext처럼

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Account account = new Account();
        account.setUsername("geon");
        account.setPassword("jpa");

        Study study = new Study();
        study.setName("Spring Data JPA");
        // study.setOwner(account); 관계의 주인이 study였을 때
        //account.getStudies().add(study); //관계를 account에 설정할 때

        //양뱡향 관계일 때
        account.addStudy(study);

        //entityManager.persist(account);
        Session session = entityManager.unwrap(Session.class);
        session.save(account);
        session.save(study); //save를 하더라도 DB에 insert 되는 게 아님. Entity가 persistent 상태가 되며 캐싱됨

        /*
         * dirty checking: 특정 객체의 변경 사항을 계속 감지한다는 것
         * write behind: 객체의 상태의 변화를 DB에 최대한 늦게(필요한 시점에) 반영한다는 것
         */
        Account geonsu = session.load(Account.class, account.getId()); //캐싱된 것을 가져옴. 즉, select 쿼리가 실행되지 않음
        geonsu.setUsername("joeygeon");
        geonsu.setUsername("geon"); //바뀔 필요가 없으니까, update 쿼리가 실행되지도 않는다.
        System.out.println("===================");
        System.out.println(geonsu.getUsername());
    }
}
