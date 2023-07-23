package com.shixun.sduvideoapi.domain.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CommentDTO {
    private Integer uid;
    private Integer vid;
    private String content;
    private Date createTime;
}
