package Project.TwitterWebApp.controller;

import Project.TwitterWebApp.model.dto.MentionDto;
import Project.TwitterWebApp.service.MentionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mention")
public class MentionController {
    @Autowired
    private MentionService mentionService;

    @PostMapping (value = "/{userId}/{postId}", produces = "application/json" )
    public void addMention(@PathVariable Integer userId,@PathVariable Integer postId){
        mentionService.addMention(userId,postId);
    }

    @GetMapping(value = "/{userId}/posts",produces = "application/json" )
    public List<MentionDto> getMentionOfCurrentUser(@PathVariable Integer userId){
        return mentionService.getMentionOfCurrentUser(userId);
    }

}
