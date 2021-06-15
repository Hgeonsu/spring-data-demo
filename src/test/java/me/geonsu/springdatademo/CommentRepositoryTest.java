package me.geonsu.springdatademo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @Test
    public void crud() {
//        Comment comment = new Comment();
//        comment.setComment("Hello Comment");
//        commentRepository.save(comment);
//
//        List<Comment> all = commentRepository.findAll();
//        assertThat(all.size()).isEqualTo(1);
//
//        long count = commentRepository.count();
//        assertThat(count).isEqualTo(1);

        Optional<Comment> byId = commentRepository.findById(100l);
        assertThat(byId).isEmpty(); // Optional이니까 null아니고, 없더라도 비어있다고 나온다.

        /*
         * Optional에 들어있는 값이 있다면 좌측처럼 사용하고,
         * 없으면 예외를 던진다
         */
//        Comment comment = byId.orElseThrow(IllegalArgumentException::new); // method reference

    }
}