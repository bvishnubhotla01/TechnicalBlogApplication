package technicalblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import technicalblog.model.Post;
import technicalblog.repository.PostRepository;

import javax.persistence.*;
import java.sql.*;
import java.util.*;
import java.util.Date;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public PostService() {
        System.out.println("*** Post Service ***");
    }

    public List<Post> getAllPosts() {
        return postRepository.getAllPosts();
    }

    public Post getOnePost() {
        return postRepository.getLatestPost();
    }

    public void createPost(Post newPost) {
        newPost.setDate(new Date());
        Post post = postRepository.createPost(newPost);
    }

    public Post getPost(Integer postId) {
        return postRepository.getPost(postId);
    }

    public void updatePost(Post updatedPost) {
        updatedPost.setDate(new Date());
        postRepository.updatePost(updatedPost);
    }

    public void deletePost(Integer postId) {
        postRepository.deletePost(postId);
    }
}
