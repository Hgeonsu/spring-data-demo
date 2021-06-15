package me.geonsu.springdatademo;

import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;

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
}
