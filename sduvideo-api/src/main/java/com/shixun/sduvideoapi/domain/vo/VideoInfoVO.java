package com.shixun.sduvideoapi.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class VideoInfoVO {
    private Integer id;
    private Integer uid;
    private String name;
    private String author;
    private String avatar;
    private String type;
    private String img;
    private String description;
    private String address;
    private String signature;
    private Integer playNum;
    private Integer likeNum;
    private Integer commentNum;
    private Integer favoriteNum;
    private Integer duration;
    private Integer following;
    private Integer followed;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
