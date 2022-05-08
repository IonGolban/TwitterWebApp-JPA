package Project.TwitterWebApp.controller;

import Project.TwitterWebApp.model.dto.PostCreateDto;
import Project.TwitterWebApp.model.dto.PostDto;
import Project.TwitterWebApp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService ;

    @GetMapping(value = "",produces = "application/json")
    public ResponseEntity<List<PostDto>> getAllPosts(){
        return new ResponseEntity<>(postService.getAllPosts(), HttpStatus.OK);
    }

    @PostMapping(value = "", produces = "application/json")
    public void addPost(@RequestBody PostCreateDto post ){
         postService.addPost(post);

    }

    @GetMapping(value = "/{userId}/mine",produces = "application/json")
    public ResponseEntity<List<PostDto>> getPostsByUserId(@PathVariable Integer userId){
        return new ResponseEntity<>(postService.getPostByUserId(userId), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public HttpStatus deletePostById(@PathVariable Integer id ){
        postService.deletePostById(id);
        return HttpStatus.OK;
    }

    @GetMapping(value = "/{userId}/feed", produces = "application/json")
    public ResponseEntity<List<PostDto>> getFeed(@PathVariable Integer userId){
        return new ResponseEntity<>(postService.getFeed(userId),HttpStatus.OK);
    }

    @PostMapping(value = "{userId}/repost/{postId}", produces = "application/json")
    public void addAnExistingPost(@PathVariable Integer userId,@PathVariable Integer postId){
        postService.copyAnExistingPost(userId,postId);

    }

}
