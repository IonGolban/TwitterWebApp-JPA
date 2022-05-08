package Project.TwitterWebApp.repos;

import Project.TwitterWebApp.model.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {
    @Override
    List<UserEntity> findAll();

    @Override
    Optional<UserEntity> findById(Integer integer);

    @Query(value = "Select * from T_USER WHERE USER_NAME = :userName " , nativeQuery = true )
    Optional<UserEntity> findByUserName(String userName);


    @Query(value = "Select * from T_USER WHERE FIRST_NAME = :firstName " , nativeQuery = true )
    List<UserEntity> findByFirstName(String firstName);


    @Query(value = "Select * from T_USER WHERE LAST_NAME = :lastName " , nativeQuery = true )
    List<UserEntity> findByLastName(String lastName);

    @Override
    void deleteById(Integer integer);

    @Override
    <S extends UserEntity> S save(S entity);
}
