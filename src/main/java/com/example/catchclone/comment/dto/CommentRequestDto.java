package com.example.catchclone.comment.dto;

public record CommentRequestDto(String commentContents,boolean isChild,Long parentId) {

}
