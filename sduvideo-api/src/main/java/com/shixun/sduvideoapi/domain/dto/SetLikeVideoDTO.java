package com.shixun.sduvideoapi.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SetLikeVideoDTO {
    private Integer uid;
    private Integer vid;
    @JsonProperty(value = "isLike")
    private boolean isLike;
}
