package com.shixun.sduvideoapi.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class MessageVO {
    private Integer fromUid;
    private String fromUname;
    private Integer toUid;
    private String toUname;
    private String content;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
