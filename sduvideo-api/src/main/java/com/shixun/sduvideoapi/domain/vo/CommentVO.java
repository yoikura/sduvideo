package com.shixun.sduvideoapi.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class CommentVO {
    private Integer id;
    private String nickName;
    private String avatar;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    private String content;
}
