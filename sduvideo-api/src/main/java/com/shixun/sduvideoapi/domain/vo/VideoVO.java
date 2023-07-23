package com.shixun.sduvideoapi.domain.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class VideoVO {
    private Integer id;
    private Integer uid;
    private String name;
    private String author;
    private String avatar;
    private String type;
    private String img;
    private String description;
    private Integer playNum;
    private Integer duration;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date createTime;
    private Date hCreateTime;
    private boolean showWord = true;
}
