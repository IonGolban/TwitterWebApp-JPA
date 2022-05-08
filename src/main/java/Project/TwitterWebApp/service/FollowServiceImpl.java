package Project.TwitterWebApp.service;

import Project.TwitterWebApp.advice.exception.NotFoundException;
import Project.TwitterWebApp.advice.exception.NotUniqueException;
import Project.TwitterWebApp.repos.FollowRepository;
import Project.TwitterWebApp.repos.UserRepository;
import Project.TwitterWebApp.model.entity.FollowEntity;
import Project.TwitterWebApp.model.entity.FollowId;
import Project.TwitterWebApp.model.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.NoSuchElementException;

@Service
public class FollowServiceImpl implements FollowService {

    @Autowired
    private FollowRepository followRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void followUser(Integer userId, Integer followingUserId){

        if(followRepository.getFollowIfPresent(userId,followingUserId).isPresent())
            throw new NotUniqueException("You already follow this user");

        UserEntity currentUser =userRepository.findById(userId).get();

        UserEntity followingUser;
        try{followingUser =userRepository.findById(followingUserId).get();}

        catch (NoSuchElementException e){
            throw new NotFoundException("User with id= "+followingUserId+" not found");
        }

        FollowEntity followEntity = FollowEntity.builder()
                .id(FollowId.builder()
                        .userId(userId)
                        .followingUserId(followingUserId)
                        .build())
                .user(currentUser)
                .followingUser(followingUser)
                .time(new Timestamp(System.currentTimeMillis()))
                .build();

        followRepository.save(followEntity);
    }

    @Override
    public void unfollowUser(Integer userId, Integer followingUserId) {

        if(followRepository.getFollowIfPresent(userId,followingUserId).isEmpty())
            throw new NotUniqueException("You didn't follow this user");


        UserEntity currentUser =userRepository.findById(userId).get();
        try{
            UserEntity followingUser =userRepository.findById(followingUserId).get();
        }
        catch (NoSuchElementException e){
            throw new NotFoundException("User with id= "+followingUserId+" not found");
        }


        FollowId followId = FollowId.builder()
                .userId(userId)
                .followingUserId(followingUserId)
                .build();
        followRepository.deleteById(followId);

    }
}
