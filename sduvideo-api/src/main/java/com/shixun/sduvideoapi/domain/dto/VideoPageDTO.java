package com.shixun.sduvideoapi.domain.dto;

import com.shixun.sduvideoapi.domain.entity.VideoInfo;
import lombok.Data;

import java.util.List;

@Data
public class VideoPageDTO {
    public List<VideoInfo> PageList;
    int count;
}
