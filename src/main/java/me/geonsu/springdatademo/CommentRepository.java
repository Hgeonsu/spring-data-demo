package me.geonsu.springdatademo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;
import java.util.stream.Stream;

public interface CommentRepository extends MyRepository<Comment, Long> {

    //MyRepository 덕분에 필요 없어진 부분
//    /*
//    내가 제공하고 싶은 기능만 제공
//    그런데 이런식으로 개발하면, 다른 repository에서 save나 findAll 기능을 구현하고 싶으면, 매번 이렇게 작성해야 하니까 불편
//    따라서 공통으로 관리할 수 있는 인터페이스를 작성한다.(MyRepository)
//    (보통은 그냥 JpaRepository 갖다 쓰면 되는데, 사용하지 않는 기능까지 들어오는 게 싫으면 이렇게 하면 된다     */
//
//    Comment save(Comment comment);
//
//    List<Comment> findAll();

    //메소드 이름으로 생성 못하면, 쿼리문 사용. 뒤의 옵션이 있어야 SQL로 입력 가능
//    @Query(value = "SELECT c FROM Comment AS c", nativeQuery = true)
    List<Comment> findByCommentContainsIgnoreCaseAndLikeCountGreaterThan(String keyword, int likeCount);

    List<Comment> findByCommentContainsIgnoreCaseOrderByLikeCountDesc(String keyword);

    // 정렬 조건대신에 pageable을 준다
    Page<Comment> findByCommentContainsIgnoreCase(String keyword, Pageable pageable);

    Stream<Comment> findByCommentContains(String keyword, Pageable pageable);

    Page<Comment> findByLikeCountGreaterThanAndPost(int likeCount, Post post, Pageable pageable);
}
