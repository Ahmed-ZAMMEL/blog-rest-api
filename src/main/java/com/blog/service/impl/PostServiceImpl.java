package com.blog.service.impl;

import com.blog.entity.Post;
import com.blog.exception.ResourceNotFoundException;
import com.blog.mapper.PostMapper;
import com.blog.payload.PostDto;
import com.blog.repository.PostRepository;
import com.blog.service.PostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {

        //Convert DTO to entity
        Post post = PostMapper.MAPPER.mapToPost(postDto);
        Post newPost = postRepository.save(post);
        //Convert entity to DTO

        return PostMapper.MAPPER.mapToPostDto(newPost);
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(PostMapper.MAPPER::mapToPostDto).collect(Collectors.toList());
    }

    @Override
    public PostDto getPostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        return PostMapper.MAPPER.mapToPostDto(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        post = Post.builder()
                .id(postDto.getId())
                .title(postDto.getTitle())
                .description(postDto.getDescription())
                .content(postDto.getContent())
                .build();
        Post updatedPost = postRepository.save(post);
        return PostMapper.MAPPER.mapToPostDto(updatedPost);

    }

    @Override
    public void deletePostById(long id) {
        //TODO: we can create a private method  Post findById(long id).
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        postRepository.delete(post);
    }
}
