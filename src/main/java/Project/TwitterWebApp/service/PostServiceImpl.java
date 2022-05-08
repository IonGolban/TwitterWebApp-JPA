package Project.TwitterWebApp.service;

import Project.TwitterWebApp.advice.exception.NotFoundException;
import Project.TwitterWebApp.repos.util.converter.LikeConverter;
import Project.TwitterWebApp.repos.util.converter.PostConverter2;
import Project.TwitterWebApp.model.dto.PostCreateDto;
import Project.TwitterWebApp.model.dto.PostDto;
import Project.TwitterWebApp.model.entity.FollowEntity;
import Project.TwitterWebApp.model.entity.PostEntity;
import Project.TwitterWebApp.model.entity.ReplyEntity;
import Project.TwitterWebApp.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private FollowRepository followRepository;
    @Override
    public List<PostDto> getAllPosts() {

        List<PostEntity>listOfPostEntities= postRepository.findAll();

        List<PostDto> list = listOfPostEntities.stream()
                .map(PostConverter2::toPostDto)
                .collect(Collectors.toList());

        addLikesAndReplies(list);

        return list ;

    }

    @Override
    public void addPost(PostCreateDto postDto) {

        postRepository.save(PostEntity.builder()
                .message(postDto.getMessage())
                .userEntity(userRepository.findById(postDto.getUserId()).get())
                .time(new Timestamp(System.currentTimeMillis()))
                .build());
    }

    @Override
    public List<PostDto> getPostByUserId(Integer id) {
        if(postRepository.getPostsByUserId(id).isEmpty()) throw new NotFoundException("This user don't has any posts");
        List<PostDto> list =
                postRepository.getPostsByUserId(id).stream()
                .map(PostConverter2::toPostDto)
                .collect(Collectors.toList());

        addLikesAndReplies(list);
        return list ;
    }

    @Override
    public void deletePostById(Integer id) {
        if(postRepository.findById(id).isEmpty()){
            throw new NotFoundException("Post not found");
        }
        postRepository.deleteById(id);
    }

    @Override
    public List<PostDto> getFeed(Integer userId) {

        List<FollowEntity> followEntityList =followRepository.getFollowByUserId(userId);

        List<PostEntity> feed = new ArrayList<>();

        followEntityList.forEach(followEntity -> {
            feed.addAll(postRepository.getPostsByUserId(followEntity.getFollowingUser().getId()));
        });

        if(feed.isEmpty()) throw new NotFoundException("This user doesn't have feed");

        List<PostDto> feedDto = feed.stream()
                .map(PostConverter2::toPostDto)
                .collect(Collectors.toList());

        addLikesAndReplies(feedDto);
        return feedDto ;
    }

    @Override
    public void copyAnExistingPost(Integer currentUserId, Integer postId) {

        if(postRepository.findById(postId).isEmpty()){
            throw new NotFoundException("Post not found");

        }
        PostEntity postEntity = postRepository.findById(postId).get();

        postRepository.save(PostEntity.builder()
                .message(postEntity.getMessage())
                .time(new Timestamp(System.currentTimeMillis()))
                .userEntity( userRepository.findById(currentUserId).get())
                .build());
    }

    private void addLikesAndReplies(List<PostDto> list){

        list.forEach(postDto -> {

            postDto.setLikeDtoList(likeRepository.findLikesByPostId(postDto.getId()).stream()
                    .map(LikeConverter::toLikeDto)
                    .collect(Collectors.toList()));

            List<PostDto> replies = replyRepository.findReplyByParentPostId(postDto.getId()).stream()
                    .map(ReplyEntity::getPost)
                    .map(PostConverter2::toPostDto)
                    .collect(Collectors.toList());

            addLikesAndReplies(replies);//recursion for setting the replies which have replies

            postDto.setReplies(replies);
        });
    }
}
