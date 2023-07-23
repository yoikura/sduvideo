package com.shixun.sduvideoapi.domain.dto;

import com.shixun.sduvideoapi.domain.entity.UserInfo;
import lombok.Data;

import java.util.List;

@Data
public class UserPageDTO {
    public List<UserInfo> PageList;
    int count;
}
