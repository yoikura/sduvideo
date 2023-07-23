package com.shixun.sduvideoapi.domain.entity;

import lombok.Data;

@Data
public class VideoType {
    private Integer tid;
    private String typeName;
    private String cover;
    private Integer isDeleted;//增加一行
}
