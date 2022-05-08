package Project.TwitterWebApp.controller;

import  Project.TwitterWebApp.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/like")
public class LikeController {

    @Autowired
    private LikeService likeService;

    @PostMapping(value = "/{userId}/post/{postId}", produces = "application/json")
        public void likePost(@PathVariable Integer userId,@PathVariable Integer postId){
            likeService.likePost(userId,postId);
        }

    @DeleteMapping(value = "/{userId}/post/{postId}/remove", produces = "application/json")
        public void removeLike(@PathVariable Integer userId,@PathVariable Integer postId){
            likeService.removeLikeByPostId(userId,postId);
        }

}
