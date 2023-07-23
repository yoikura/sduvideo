package com.shixun.sduvideoapi.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class VideoInfo {
        private Integer vid;
        private String vname;
        private String description;
        private Integer uid;
        private Integer likeNum;
        private Integer commentNum;
        private Integer favoriteNum;
        private String  address;
        private Integer tid;
        private String  cover;
        private Integer  duration;
        private String  playNum;
        private Integer check;
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
        private Date createTime;
        private int examine;
}
