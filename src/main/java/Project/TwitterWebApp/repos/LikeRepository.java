package Project.TwitterWebApp.repos;

import Project.TwitterWebApp.model.entity.LikeEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends CrudRepository<LikeEntity, Integer> {

    @Query(value = "SELECT * FROM T_LIKE WHERE POST_ID = :postId",nativeQuery = true)
    List<LikeEntity> findLikesByPostId(Integer postId);

    @Override
    <S extends LikeEntity> S save(S entity);

    @Query(value = "SELECT * FROM T_LIKE WHERE POST_ID = :postId AND USER_ID = :userId", nativeQuery = true)
    Optional<LikeEntity> findLikeByUserIdAndPostId(Integer postId,Integer userId);
}

