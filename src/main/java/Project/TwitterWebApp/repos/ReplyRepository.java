package Project.TwitterWebApp.repos;

import Project.TwitterWebApp.model.entity.ReplyEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReplyRepository extends CrudRepository<ReplyEntity,Integer> {

    @Override
    <S extends ReplyEntity> S save(S entity);

    @Query(value = "SELECT * FROM T_REPLY WHERE PARENT_POST_ID = :parentId",nativeQuery = true)
    List<ReplyEntity> findReplyByParentPostId(Integer parentId);

}
