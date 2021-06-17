package me.geonsu.springdatademo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

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

//        Optional<Comment> byId = commentRepository.findById(100l);
//        assertThat(byId).isEmpty(); // Optional이니까 null아니고, 없더라도 비어있다고 나온다.

        /*
         * Optional에 들어있는 값이 있다면 좌측처럼 사용하고,
         * 없으면 예외를 던진다
         */
//        Comment comment = byId.orElseThrow(IllegalArgumentException::new); // method reference

        createComment(1, "spring data jpa");
        createComment(100, "spring boot");

        List<Comment> comments = commentRepository.findByCommentContainsIgnoreCaseAndLikeCountGreaterThan("Spring", 10);
        assertThat(comments.size()).isEqualTo(1);

        List<Comment> comments1 = commentRepository.findByCommentContainsIgnoreCaseOrderByLikeCountDesc("Spring");
        assertThat(comments1.size()).isEqualTo(2);
        assertThat(comments1.get(0).getLikeCount()).isGreaterThan(comments1.get(1).getLikeCount());
        assertThat(comments1).first().hasFieldOrPropertyWithValue("likeCount", 100);

        // PageRequest가 pageable 타입임, static factory method 제공
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "LikeCount"));
        Page<Comment> commentPage = commentRepository.findByCommentContainsIgnoreCase("Spring", pageRequest);
        assertThat(commentPage.getNumberOfElements()).isEqualTo(2);
        assertThat(commentPage).first().hasFieldOrPropertyWithValue("likeCount", 100);


        try(Stream<Comment> comments2 = commentRepository.findByCommentContains("spring", pageRequest)) {
            Comment firstComment = comments2.findFirst().get();
            assertThat(firstComment.getLikeCount()).isEqualTo(100);
        }
    }

    private void createComment(int likeCount, String s) {
        Comment comment = new Comment();
        comment.setLikeCount(likeCount);
        comment.setComment(s);
        commentRepository.save(comment);
        /*
        save를 한다고 insert를 하는 게 아님. persistent context가 될뿐,
        실제 insert는 DB에서 값을 꺼내서 사용해야하는 상황에 발생
         */
    }
}