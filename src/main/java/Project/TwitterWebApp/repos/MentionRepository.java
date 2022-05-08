package Project.TwitterWebApp.repos;

import Project.TwitterWebApp.model.entity.MentionEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MentionRepository extends CrudRepository<MentionEntity,Integer> {
    @Override
    <S extends MentionEntity> S save(S entity);

    @Query(value = "SELECT * FROM T_MENTION WHERE USER_ID = :userId",nativeQuery = true)
    List<MentionEntity> getMentionByUserId(Integer userId);

}
