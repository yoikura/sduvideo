package com.shixun.sduvideoapi.domain.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;


@Data
public class FollowDTO {

    private Integer followedUid;
    private Integer followingUid;
    private Date createTime;
}
