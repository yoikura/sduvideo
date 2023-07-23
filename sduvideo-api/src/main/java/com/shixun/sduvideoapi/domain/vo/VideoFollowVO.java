package com.shixun.sduvideoapi.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class VideoFollowVO {
    private Integer id;
    private Integer uid;
    private String name;
    private String author;
    private String avatar;
    private String img;
    private String description;
    private Integer playNum;
    private Integer likeNum;
    private Integer duration;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    private Integer commentNum;
    private Integer favoriteNum;
}
