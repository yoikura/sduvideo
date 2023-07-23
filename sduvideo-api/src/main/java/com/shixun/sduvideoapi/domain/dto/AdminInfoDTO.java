package com.shixun.sduvideoapi.domain.dto;

import lombok.Data;

@Data
public class AdminInfoDTO {
    private String aid;
    private String adminName;
    private String password;
    private String salt;
}
