package Project.TwitterWebApp.service;

import Project.TwitterWebApp.repos.PostRepository;
import Project.TwitterWebApp.repos.ReplyRepository;
import Project.TwitterWebApp.repos.UserRepository;
import Project.TwitterWebApp.model.dto.ReplyCreateDto;
import Project.TwitterWebApp.model.entity.PostEntity;
import Project.TwitterWebApp.model.entity.ReplyEntity;
import Project.TwitterWebApp.model.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class ReplyServiceImpl implements ReplyService{

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void replyPost(ReplyCreateDto replyCreateDto, Integer postId) {

        UserEntity userEntity = userRepository.findById(replyCreateDto.getUserId()).get();

        PostEntity parentPost = postRepository.findById(postId).get();

        PostEntity post = PostEntity.builder()
                .message(replyCreateDto.getMessage())
                .userEntity(userEntity)
                .time(new Timestamp(System.currentTimeMillis()))
                .build();

        postRepository.save(post);

        replyRepository.save(ReplyEntity.builder()
                .post(post)
                .parentPost(parentPost)
                .isPublic(replyCreateDto.getIsPublic())
                .build());

    }
}
