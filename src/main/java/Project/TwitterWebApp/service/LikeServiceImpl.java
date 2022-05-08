package Project.TwitterWebApp.service;

import Project.TwitterWebApp.advice.exception.NotFoundException;
import Project.TwitterWebApp.advice.exception.NotUniqueException;
import Project.TwitterWebApp.repos.LikeRepository;
import Project.TwitterWebApp.repos.PostRepository;
import Project.TwitterWebApp.repos.UserRepository;
import Project.TwitterWebApp.model.entity.LikeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class LikeServiceImpl implements LikeService{

    @Autowired
    private LikeRepository likeRepository ;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void likePost(Integer userId, Integer postId) {

        if(postRepository.findById(postId).isEmpty()){
            throw new NotFoundException("Post not found");
        }

        if(likeRepository.findLikeByUserIdAndPostId(userId,postId).isPresent())throw new NotUniqueException("You already like this post");

        likeRepository.save(LikeEntity.builder()
                .userEntity(userRepository.findById(userId).get())
                .postEntity(postRepository.findById(postId).get())
                .time(new Timestamp(System.currentTimeMillis()))
                .build());
    }

    @Override
    public void removeLikeByPostId(Integer userId,Integer postId) {

        if(postRepository.findById(postId).isEmpty()){
            throw new NotFoundException("Post not found");
        }

        likeRepository.delete(likeRepository.findLikeByUserIdAndPostId(userId,postId).get());

    }

}
