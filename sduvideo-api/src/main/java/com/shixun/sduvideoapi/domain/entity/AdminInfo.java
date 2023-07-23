package com.shixun.sduvideoapi.domain.entity;

import lombok.Data;

@Data
public class AdminInfo {
    private int aid;
    private String adminName;
    private String password;
    private String salt;
}
