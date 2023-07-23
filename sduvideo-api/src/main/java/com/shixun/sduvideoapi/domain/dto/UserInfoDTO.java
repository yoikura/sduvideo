package com.shixun.sduvideoapi.domain.dto;

import lombok.Data;

@Data
public class UserInfoDTO {
    private Integer uid;
    private String userName;
    private String tel;
    private String avatar;
    private String signature;
    private String nickName;
    private Integer gender;
    private String birthday;
    private String email;
    private String province;
    private String city;
    private String area;
    private String password;
}
