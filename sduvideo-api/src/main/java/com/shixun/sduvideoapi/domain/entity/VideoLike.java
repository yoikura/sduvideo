package com.shixun.sduvideoapi.domain.entity;

import lombok.Data;

import java.util.Date;

@Data
public class VideoLike {
    private Integer uid;
    private Integer vid;
    private Date createTime;
}
