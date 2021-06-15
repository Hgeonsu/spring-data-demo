package me.geonsu.springdatademo;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/*
 *NoRepositoryBean 어노테이션은 이 인터페이스가 Repository 용도로서 사용되는 것이 아닌
 * 단지 Repository의 메서드를 정의하는 인터페이스라는 정보를 부여한다.
 */
@NoRepositoryBean
public interface MyRepository<T, Id extends Serializable> extends Repository<T, Id> {
    //파라미터에 적용하는 Null annotation
    <E extends T> E save(@NonNull E entity); // T의 하위타입을 저장하는 것도 지원하고 싶으면

    //spring data jpa가 지원하는 repository의 collection type은 아무것도 없더라도 null이 아니라 비어있는 collection instance를 반환함
    List<T> findAll();

    long count();
    // 메소드에 적용하는 Null annotation -> return 값에 적용
    @Nullable
    <E extends T> Optional<E> findById(Id id); // Optional로 감쌌기때문에 null이 나올 수 없음
    // <E extends T> E findById(Id id); // 이런식으로 Optional로 감싸지 않았다면 null이 나올 수 있음

}
