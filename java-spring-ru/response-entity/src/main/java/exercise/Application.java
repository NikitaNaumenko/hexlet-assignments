package exercise;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import exercise.model.Post;

@SpringBootApplication
@RestController
public class Application {
    // Хранилище добавленных постов
    private List<Post> posts = Data.getPosts();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // BEGIN
    @GetMapping("/posts")
    public ResponseEntity<List<Post>> index(@RequestParam(defaultValue = "10") Integer limit) {
        List<Post> listPosts = posts.stream().limit(limit).toList();
        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(posts.size()))
                .body(listPosts);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> show(@PathVariable String id) {
        Optional<Post> post = posts.stream().filter(p -> p.getId().equals(id)).findFirst();

        return ResponseEntity.of(post);
    }

    @PostMapping("/posts")
    public ResponseEntity<Post> post(@RequestBody Post post) {
        posts.add(post);
        return ResponseEntity.status(201)
                .body(post);
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<Post> update(@PathVariable String id, @RequestBody Post data) {
        Optional<Post> maybePost = posts.stream().filter(p -> p.getId().equals(id))
                .findFirst();
        if(maybePost.isPresent()) {
            Post post = maybePost.get();
            post.setId(id);
            post.setTitle(data.getTitle());
            post.setBody(data.getBody());
            return ResponseEntity.ok().body(post);
        }
        return ResponseEntity.noContent().build();
    }

    // END
    @DeleteMapping("/posts/{id}")
    public void destroy(@PathVariable String id) {
        posts.removeIf(p -> p.getId().equals(id));
    }
}
