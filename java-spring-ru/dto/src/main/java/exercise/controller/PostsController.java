package exercise.controller;

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.exception.ResourceNotFoundException;
import exercise.dto.PostDTO;
import exercise.dto.CommentDTO;

// BEGIN
@RestController
@RequestMapping("/posts")
public class PostsController {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("")
    public List<PostDTO> getAllPosts() {
        var comments = commentRepository.findAll();

        return postRepository.findAll()
                .stream()
                .map(post -> {
                    PostDTO postDTO = new PostDTO();
                    postDTO.setId(post.getId());
                    postDTO.setBody(post.getBody());
                    postDTO.setTitle(post.getTitle());
                    var commentsDto = comments.stream()
                            .filter(c -> c.getPostId() == post.getId())
                            .map((c) -> {
                                CommentDTO comment = new CommentDTO();
                                comment.setId(c.getId());
                                comment.setBody(c.getBody());
                                return comment;
                            })
                            .toList();
                    postDTO.setComments(commentsDto);
                    return postDTO;
                })
                .toList();
    }

    @GetMapping("/{id}")
    public PostDTO getPostById(@PathVariable long id) {
        var post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));

        var comments = commentRepository.findByPostId(post.getId())
                .stream()
                .map((c) -> {
                    CommentDTO comment = new CommentDTO();
                    comment.setId(c.getId());
                    comment.setBody(c.getBody());
                    return comment;
                })
                .toList();

        var postDto = new PostDTO();

        postDto.setId(post.getId());
        postDto.setBody(post.getBody());
        postDto.setTitle(post.getTitle());
        postDto.setComments(comments);

        return postDto;
    }
}
// END
