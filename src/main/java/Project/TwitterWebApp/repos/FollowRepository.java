package Project.TwitterWebApp.repos;

import Project.TwitterWebApp.model.entity.FollowEntity;
import Project.TwitterWebApp.model.entity.FollowId;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends CrudRepository<FollowEntity, FollowId> {
    @Override
    <S extends FollowEntity> S save(S entity);

    @Override
    void deleteById(FollowId followId);

    @Query(value = "SELECT * FROM T_FOLLOW WHERE USER_ID = :userId", nativeQuery = true)
    List<FollowEntity> getFollowByUserId(Integer userId);

    @Query(value = "SELECT * FROM T_FOLLOW WHERE USER_ID = :userId AND FOLLOWING_USER_ID = :followingUserId",nativeQuery = true)
    Optional<FollowEntity> getFollowIfPresent(Integer userId, Integer followingUserId);
}
