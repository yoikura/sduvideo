package com.shixun.sduvideoapi.domain.dto;

import lombok.Data;

@Data
public class VideoType {
    private Integer tid;
    private String typeName;
    private Integer cover;
    private Integer isDeleted;//增加一行
}
