package Project.TwitterWebApp.controller;

import Project.TwitterWebApp.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/follow")
public class FollowController {

    @Autowired
    private FollowService followService;

    @PostMapping (value = "/{userId}/{followingUserId}", produces = "appplication/json")
    public void followUser(@PathVariable Integer userId, @PathVariable Integer followingUserId){

        followService.followUser(userId,followingUserId);

    }

    @DeleteMapping(value = "/{userId}/{followingUserId}/delete",produces = "appplication/json")
    public void unfollowUser(@PathVariable Integer userId, @PathVariable Integer followingUserId){

        followService.unfollowUser(userId,followingUserId);

    }

}
