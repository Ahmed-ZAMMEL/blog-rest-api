package com.blog.mapper;

import com.blog.entity.Post;
import com.blog.payload.PostDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostMapper {
    PostMapper MAPPER = Mappers.getMapper(PostMapper.class);
    PostDto mapToPostDto(Post post);
    Post mapToPost(PostDto postDto);

}
