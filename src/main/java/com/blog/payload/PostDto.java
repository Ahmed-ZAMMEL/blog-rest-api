package com.blog.payload;

import lombok.Data;

@Data
public class PostDto {

    //TODO: DTO should be a record.
    private long id;
    private String title;
    private String description;
    private String content;

}
