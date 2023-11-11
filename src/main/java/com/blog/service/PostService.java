package com.blog.service;

import com.blog.payload.PostDto;

import java.util.List;

public interface PostService {
PostDto createPost(PostDto postDto);
List<PostDto> getAllPosts();
PostDto getPostById(long id);
PostDto updatePost(PostDto postDto, long id);
void deletePostById(long id);

}
