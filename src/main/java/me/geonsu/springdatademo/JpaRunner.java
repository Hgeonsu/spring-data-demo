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
        Post post = new Post();
        post.setTitle("spring data jpa 언제나오나");

        Comment comment = new Comment();
        comment.setComment("언제나옴?");
        post.addComment(comment);

        Comment comment2 = new Comment();
        comment2.setComment("곧나옴");
        post.addComment(comment2);

        Session session = entityManager.unwrap(Session.class);
        session.save(post);

//        Post post1 = session.get(Post.class, 1l);
//        session.delete(post1);
    }
}
