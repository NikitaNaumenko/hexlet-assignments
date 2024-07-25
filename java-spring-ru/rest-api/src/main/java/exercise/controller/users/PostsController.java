package exercise.controller.users;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import exercise.model.Post;
import exercise.Data;

// BEGIN
@RestController
@RequestMapping("/api/users")
public class PostsController {
    private List<Post> posts = Data.getPosts();

    @GetMapping("{userId}/posts")
    public List<Post> index(@RequestParam(defaultValue = "10") Integer limit, @PathVariable int userId) {
        return posts.stream().filter(post -> post.getUserId() == userId).limit(limit).toList();

    }
    @PostMapping("{userId}/posts")
    public ResponseEntity<Post> addPost(@PathVariable int userId, @RequestBody Post post) {
        post.setUserId(userId);
        posts.add(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }
}
// END
