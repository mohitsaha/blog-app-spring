package com.mohit.blog.payloads;

import lombok.Data;

@Data
public class CommentDto {
  private int id;
  private String content;
  
//  @ManyToOne
//  private Post post;
  
}
