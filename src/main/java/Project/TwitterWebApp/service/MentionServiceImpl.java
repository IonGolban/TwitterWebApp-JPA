package Project.TwitterWebApp.service;

import Project.TwitterWebApp.advice.exception.NotFoundException;
import Project.TwitterWebApp.repos.util.converter.LikeConverter;
import Project.TwitterWebApp.repos.util.converter.MentionConverter;
import Project.TwitterWebApp.repos.util.converter.PostConverter2;
import Project.TwitterWebApp.model.dto.MentionDto;
import Project.TwitterWebApp.model.dto.PostDto;
import Project.TwitterWebApp.model.entity.MentionEntity;
import Project.TwitterWebApp.model.entity.ReplyEntity;
import Project.TwitterWebApp.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MentionServiceImpl implements MentionService{

    @Autowired
    private MentionRepository mentionRepository;

    @Autowired
    private LikeRepository likeRepository ;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @Override
    public void addMention(Integer userId, Integer postId) {

        if(postRepository.findById(postId).isEmpty()){
            throw new NotFoundException("Post not found");
        }

        MentionEntity mention = MentionEntity.builder()
                .userEntity(userRepository.findById(userId).get())
                .postEntity(postRepository.findById(postId).get())
                .build();
        mentionRepository.save(mention);
    }

    @Override
    public List<MentionDto> getMentionOfCurrentUser(Integer userId) {
        List<MentionDto> list = mentionRepository.getMentionByUserId(userId).stream()
                .map(MentionConverter::toMentionDto)
                .collect(Collectors.toList());
        list.forEach(mentionDto -> {
            addLikesAndReplies(mentionDto.getPostDto());
        });

        return list ;
    }

    private void addLikesAndReplies(PostDto postDto){


            postDto.setLikeDtoList(likeRepository.findLikesByPostId(postDto.getId()).stream()
                    .map(LikeConverter::toLikeDto)
                    .collect(Collectors.toList()));

            List<PostDto> replies = replyRepository.findReplyByParentPostId(postDto.getId()).stream()
                    .map(ReplyEntity::getPost)
                    .map(PostConverter2::toPostDto)
                    .collect(Collectors.toList());

            replies.forEach(this::addLikesAndReplies);//recursion for setting the replies which have replies

            postDto.setReplies(replies);

    }
}
