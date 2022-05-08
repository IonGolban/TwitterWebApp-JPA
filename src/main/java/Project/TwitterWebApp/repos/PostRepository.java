package Project.TwitterWebApp.repos;

import Project.TwitterWebApp.model.entity.PostEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends CrudRepository<PostEntity,Integer> {

    @Override
    Optional<PostEntity> findById(Integer integer);

    @Override
    List<PostEntity> findAll();

    @Override
    <S extends PostEntity> S save(S entity);

    @Query(value = "SELECT * FROM T_POST WHERE USER_ID = :userId ", nativeQuery =  true)
    List<PostEntity> getPostsByUserId (Integer userId);
}
