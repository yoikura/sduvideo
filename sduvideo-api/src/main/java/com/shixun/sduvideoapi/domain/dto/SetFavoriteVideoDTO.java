package com.shixun.sduvideoapi.domain.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class SetFavoriteVideoDTO {
    private Integer uid;
    private Integer vid;
    @JsonProperty(value = "isFavorite")
    private boolean isFavorite;
    private Date createTime;
}
