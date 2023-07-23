package com.shixun.sduvideoapi.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class UserInfo {
    private Integer uid;
    private String userName;
    private String tel;
    private String avatar;
    private String signature;
    private String nickName;
    private Integer gender;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date birthday;
    private String email;
    private String province;
    private String city;
    private String area;
    private Integer following;
    private Integer followed;
    private String password;
}
